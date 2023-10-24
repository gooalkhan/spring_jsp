package com.example.spring_jsp.analysis;

import com.example.spring_jsp.notification.NotificationService;
import com.example.spring_jsp.shop.bookkeeping.BookkeepingServiceImpl;
import com.example.spring_jsp.shop.product.ProductDTO;
import com.example.spring_jsp.shop.product.ProductServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

import static java.util.Map.entry;


@RestController
@RequiredArgsConstructor
@RequestMapping("/analysis")
public class AnalysisController {

    private final ProductServiceImpl productServiceImpl;
    private final NotificationService notificationService;
    private final AnalysisServiceImpl analysisServiceImpl;
    private final BookkeepingServiceImpl bookkeepingServiceImpl;
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
                List<String> unlockedList = bookkeepingServiceImpl.getUnlockedUID(sid);

                ProductDTO purchased;
                for (String unlockedUID : unlockedList) {
                    purchased = productServiceImpl.selectProduct(unlockedUID);
//                    if (purchased.getBookid() == bookid && purchased.getProductid().equals("키워드")) {
                    if (purchased.getBookid() ==bookid) {
                        switch (purchased.getProductid()) {
                            case "키워드":
                                return analysisServiceImpl.getKeywordAnalysis(sid, purchased.getBookid(), purchased.getProductid());
                            case "선호작품":
                                return analysisServiceImpl.getFavoriteAnalysis();
                        }
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
                int result = productServiceImpl.purchaseProduct(sid, productDTO);
                if (result == 1) {
                    logger.debug("purchase success");
                    return "{\"status\": \"success\", \"current_point\":" + bookkeepingServiceImpl.getPoint(sid) + "}";
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
        if (httpSession.getAttribute("sid") == null) {
            mav.setViewName("book/analysis/nosession");
            return mav;
        }

        //이미 구매한 경우
        String sid = httpSession.getAttribute("sid").toString();
        List<String> unlockedList = bookkeepingServiceImpl.getUnlockedUID(sid);

        ProductDTO purchased;
        for (String unlockedUID : unlockedList) {
            purchased = productServiceImpl.selectProduct(unlockedUID);
            if (purchased.getBookid() == bookid && purchased.getProductid().equals(analysisMap.get(analysis))) {
                switch (purchased.getProductid()) {
                    case "키워드":
                        return analysisServiceImpl.getKeywordAnalysis(sid, purchased.getBookid(), purchased.getProductid());
                    case "선호작품":
                        return analysisServiceImpl.getFavoriteAnalysis();
                }
            }
        }

        //구매 안한 경우
        List<ProductDTO> list = productServiceImpl.selectProductCountByBookUserProduct(bookid, analysisMap.get(analysis), sid);

        ProductDTO productDTO;
        if (list.isEmpty()) {
            productDTO = productServiceImpl.buildInsertProduct(bookid, analysisMap.get(analysis), sid);
        } else productDTO = list.get(0);
        logger.debug("productDTO inserted: {}", productDTO.toString());
        mav.addObject("currentPoint", bookkeepingServiceImpl.getPoint(sid));
        mav.addObject("productDTO", productDTO);

        mav.setViewName("book/analysis/withsession");
        return mav;
    }
}