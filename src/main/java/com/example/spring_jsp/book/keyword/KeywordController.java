package com.example.spring_jsp.book.keyword;

import com.example.spring_jsp.notification.NotificationService;
import com.example.spring_jsp.shop.bookkeeping.BookkeepingServiceImpl;
import com.example.spring_jsp.shop.product.ProductDTO;
import com.example.spring_jsp.shop.product.ProductServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/keyword")
public class KeywordController {

    private final ProductServiceImpl productServiceImpl;
    private final NotificationService notificationService;
    private final KeywordServiceImpl keywordServiceImpl;
    private final BookkeepingServiceImpl bookkeepingServiceImpl;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("")
    public ModelAndView keywordAnalysis(HttpSession session,
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
                    if (purchased.getBookid() == bookid && purchased.getProductid().equals("키워드")) {
                        return keywordServiceImpl.getKeywordAnalysis();
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
    public String purchaseKeywordAnalysis(HttpSession session, ProductDTO productDTO) {
        logger.debug("purchaseKeywordAnalysis - productDTO: {}", productDTO.toString());
        //세션 체크
        if (session.getAttribute("sid") != null) {
            String sid = (String) session.getAttribute("sid");

            //세션 아이디와 폼에서 받은 아이디가 일치하는지 확인
            if (sid.equals(productDTO.getUserid())) {
                //제품 구매
                int result = productServiceImpl.purchaseProduct(sid, productDTO);
                if (result == 1) {
                    logger.debug("purchase success");
                    return "{\"status\": \"success\"}";
                } else {
                    logger.debug("purchase failed");
                    return "{\"status\": \"fail\"}";
                }
            }
        }
        logger.debug("purchase failed - session error");
        return "{\"status\": \"fail\"}";
    }
}