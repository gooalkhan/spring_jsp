package com.example.spring_jsp.shop;

import com.example.spring_jsp.shop.bookkeeping.BookkeepingDTO;
import com.example.spring_jsp.shop.bookkeeping.BookkeepingServiceImpl;
import com.example.spring_jsp.shop.campaign.CampaignDTO;
import com.example.spring_jsp.shop.campaign.CampaignServiceImpl;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/shop")
@RequiredArgsConstructor
@Controller
public class ShopController {

    private final CampaignServiceImpl campaignServiceImpl;
    private final BookkeepingServiceImpl bookkeepingServiceImpl;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("")
    public String shop(@RequestParam(value = "success", defaultValue = "") String isSuccess, Model model, HttpSession httpSession) {
        String sid = (String)httpSession.getAttribute("sid");

        if (sid != null) {
            List<CampaignDTO> currentCampaigns = campaignServiceImpl.getCurrentCampaign();
            model.addAttribute("currentCampaigns", currentCampaigns);
            model.addAttribute("point", bookkeepingServiceImpl.getPoint(sid));
            if (!isSuccess.isEmpty()) {
                //포인트 구매후 리디렉션
                model.addAttribute("success", isSuccess);
            }
        }
        return "shop/shop";
    }

    @PostMapping("/purchase")
    public String purchase(BookkeepingDTO bookkeepingDTO, HttpSession httpSession) {
        logger.debug("start purchasing - bookkeepingDTO: {}", bookkeepingDTO);
        String nextPage = "redirect:/shop?success=false";

        //TODO: handlerintercepter로 처리!
        String sid = (String)httpSession.getAttribute("sid");

        //세션아이디와 폼에서 받은 아이디가 일치하는지 확인
        if (sid != null && sid.equals(bookkeepingDTO.getUserid())) {
            logger.debug("id matching success - session id: {}, form id: {}", sid, bookkeepingDTO.getUserid());
            int[] result = bookkeepingServiceImpl.purchasePoint(bookkeepingDTO);

            if (result[0] == 1 && result[1] != 0) {
                logger.debug("purchase success");
                nextPage = "redirect:/shop?success=true";
            } else {
                logger.debug("purchase failed");
            }
        }
        return nextPage;
    }

}
