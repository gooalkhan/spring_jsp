package com.example.spring_jsp.shop;

import com.example.spring_jsp.shop.campaign.CampaignDTO;
import com.example.spring_jsp.shop.campaign.CampaignService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/shop")
@RequiredArgsConstructor
@Controller
public class ShopController {

    private final CampaignService campaignService;

    @GetMapping("")
    public String shop(Model model, HttpSession httpSession) {
        String sid = (String)httpSession.getAttribute("sid");

        if (sid != null) {
            List<CampaignDTO> currentCampaigns = campaignService.getCurrentCampaign();
            model.addAttribute("currentCampaigns", currentCampaigns);
        }
        return "shop/shop";
    }
}
