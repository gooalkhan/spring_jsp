package com.example.spring_jsp.analysis;

import com.example.spring_jsp.notification.NotificationService;
import com.example.spring_jsp.shop.bookkeeping.BookkeepingService;
import com.example.spring_jsp.shop.product.ProductDTO;
import com.example.spring_jsp.shop.product.ProductService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/analysis")
public class AnalysisController {

    private final ProductService productService;
    private final NotificationService notificationService;
    private final AnalysisService analysisService;
    private final BookkeepingService bookkeepingService;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Map<String, String> analysisMap = Map.ofEntries(
            entry("keyword", "키워드"),
            entry("favorite", "선호작품")
    );

    //TODO: 구매 체크 부분 서비스로 빼기
    @GetMapping("")
    public ModelAndView getAnalysisAjax(HttpSession session,
                                        @RequestParam(value = "bookid", defaultValue = "0") long bookid,
                                        @RequestParam(value = "productid", defaultValue = "") String productid) {

        ModelAndView result = new ModelAndView();
        result.setViewName("book/analysis/blank");

        logger.debug("keywordAnalysis - bookid: {}, productid: {}", bookid, productid);

        //param check
        if (bookid != 0 && !productid.isEmpty()) {

            //세션 체크
            if (session.getAttribute("sid") != null) {
                String sid = (String) session.getAttribute("sid");

                //구매여부 체크
                List<ProductDTO> unlockedProducts = productService.productsUnlockedByCondition(bookid, productid, sid);
                if (!unlockedProducts.isEmpty()) {
                    if (unlockedProducts.size() > 1) {
                        log.error("구입한 제품이 2개 이상입니다. sid: {}, bookid: {}, analysis: {}", sid, bookid, productid);
                    }
                    ProductDTO purchased = unlockedProducts.get(0);
                    switch (purchased.getProductid()) {
                        case "키워드":
                            return analysisService.getKeywordAnalysis(sid, purchased.getBookid(), purchased.getProductid());
                        case "선호작품":
                            return analysisService.getFavoriteAnalysis(sid, purchased.getBookid(), purchased.getProductid());
                    }
                }
                notificationService.send(sid, "구매하지 않은 상품입니다.");
                logger.debug("not purchased");
            } else {
                logger.debug("session error");
            }
        } else {
            logger.debug("param error");
        }
        return result;
    }

    @PostMapping(value = {"", "/"})
    @ResponseBody
    public String purchaseAnalysis(HttpSession session, ProductDTO productDTO) {
        logger.debug("purchaseAnalysis - productDTO: {}", productDTO.toString());
        //세션 체크
        if (session.getAttribute("sid") != null) {
            String sid = (String) session.getAttribute("sid");

            //세션 아이디와 폼에서 받은 아이디가 일치하는지 확인
            if (sid.equals(productDTO.getUserid())) {
                //제품 구매
                int result = productService.purchaseProduct(sid, productDTO);
                if (result == 1) {
                    logger.debug("purchase success");
                    return "{\"status\": \"success\", \"current_point\":" + bookkeepingService.getPoint(sid) + "}";
                } else {
                    logger.debug("purchase failed");
                    return "{\"status\": \"fail\"}";
                }
            }
        }
        logger.debug("purchase failed - session error");
        return "{\"status\": \"fail\"}";
    }

    @GetMapping("/{analysis}")
    public ModelAndView anal_keyword(HttpSession httpSession,
                                     @PathVariable String analysis,
                                     @RequestParam("bookid") long bookid,
                                     Model model) {

        ModelAndView mav = new ModelAndView();
        model.addAttribute("analysis", analysisMap.get(analysis));

        model.addAttribute("bookid", bookid);

        //세션 체크
        if (httpSession.getAttribute("sid") == null) {
            mav.setViewName("book/analysis/nosession");
            return mav;
        }

        //이미 구매한 경우
        String sid = httpSession.getAttribute("sid").toString();

        List<ProductDTO> unlockedProducts = productService.productsUnlockedByCondition(bookid, analysisMap.get(analysis), sid);
        if (!unlockedProducts.isEmpty()) {
            if (unlockedProducts.size() > 1) {
                log.error("구입한 제품이 2개 이상입니다. sid: {}, bookid: {}, analysis: {}", sid, bookid, analysis);
            }
                ProductDTO purchased = unlockedProducts.get(0);
                switch (purchased.getProductid()) {
                    case "키워드":
                        return analysisService.getKeywordAnalysis(sid, purchased.getBookid(), purchased.getProductid());
                    case "선호작품":
                        return analysisService.getFavoriteAnalysis(sid, purchased.getBookid(), purchased.getProductid());
                }
        }

        //구매 안한 경우 판매 가능한 제품이 이미 있는지 확인하고 없으면 새로 생성
        List<ProductDTO> list = productService.selectProductCountByBookUserProduct(bookid, analysisMap.get(analysis), sid);

        ProductDTO productDTO;
        if (list.isEmpty()) {
            productDTO = productService.buildInsertProduct(bookid, analysisMap.get(analysis), sid);
        } else productDTO = list.get(0);
        logger.debug("productDTO inserted: {}", productDTO.toString());
        mav.addObject("currentPoint", bookkeepingService.getPoint(sid));
        mav.addObject("productDTO", productDTO);

        mav.setViewName("book/analysis/withsession");
        return mav;
    }
}