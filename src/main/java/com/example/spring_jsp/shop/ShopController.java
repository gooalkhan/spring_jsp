package com.example.spring_jsp.shop;

import com.example.spring_jsp.notification.NotificationService;
import com.example.spring_jsp.shop.bookkeeping.BookkeepingService;
import com.example.spring_jsp.shop.campaign.CampaignDTO;
import com.example.spring_jsp.shop.campaign.CampaignService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/*
 * 포인트 샵 뷰 컨트롤러
 */
@RequestMapping("/shop")
@RequiredArgsConstructor
@Controller
@Slf4j
public class ShopController {

    private final CampaignService campaignService;
    private final BookkeepingService bookkeepingService;
    private final NotificationService notificationService;

    @GetMapping("")
    public String shop(@RequestParam(value = "success", defaultValue = "") String isSuccess, Model model, HttpSession httpSession) {
        String sid = (String)httpSession.getAttribute("sid");

        if (sid != null) {
            List<CampaignDTO> currentCampaigns = campaignService.getCurrentCampaign();
            model.addAttribute("currentCampaigns", currentCampaigns);
            model.addAttribute("point", bookkeepingService.getPoint(sid));
            if (!isSuccess.isEmpty()) {
                //포인트 구매후 리디렉션
                model.addAttribute("success", isSuccess);
            }
        }
        return "shop/shop";
    }

    @PostMapping("/purchase")
    public String purchase(
            @RequestParam(value = "purchaseMethodUID") String purchaseMethodUID,
            @RequestParam(value = "userid") String submittedSid,
            @ModelAttribute CampaignDTO campaignDTO,
            HttpSession httpSession) {

        log.debug("start purchasing - campainDTO: {}", campaignDTO);
        String nextPage = "redirect:/shop?success=false";

        String sid = (String)httpSession.getAttribute("sid");

        //세션아이디와 폼에서 받은 아이디가 일치하는지 확인, 구매상품이 맞는지는 서비스에서 체크
        if (sid != null && sid.equals(submittedSid)) {
            log.debug("id matching success - session id: {}, form id: {}", sid, submittedSid);
            int[] result = campaignService.purchasePoint(sid, purchaseMethodUID, campaignDTO);

            if (result[0] == 1 && result[1] != 0) {
                log.debug("purchase success");
                nextPage = "redirect:/shop?success=true";
            } else {
                log.debug("purchase failed");
            }
        } else {
            notificationService.send(submittedSid, "구매에 실패했습니다. 다시 시도해주세요.");
            log.error("id matching failed - session id: {}, form id: {}", sid, submittedSid);
        }
        return nextPage;
    }
}
