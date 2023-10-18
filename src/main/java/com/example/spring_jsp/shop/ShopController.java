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

import java.util.UUID;

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
            String uuid = UUID.randomUUID().toString();

            //폼에서 받은 판매정보가 서버의 판매정보와 일치하는지 확인
            CampaignDTO campaignDTO =  campaignServiceImpl.selectCampaign(bookkeepingDTO.getCampaignUID());
            if (campaignDTO != null) {
                BookkeepingDTO toInsertBookkeepingDTO = new BookkeepingDTO();
                toInsertBookkeepingDTO.setUUID(uuid);
                toInsertBookkeepingDTO.setReferenceUUID(uuid);
                toInsertBookkeepingDTO.setUserid(sid);
                toInsertBookkeepingDTO.setAddedPoint(campaignDTO.getPoint());
                toInsertBookkeepingDTO.setUsedPoint(0);
                toInsertBookkeepingDTO.setPurchaseMethodUID(bookkeepingDTO.getPurchaseMethodUID());
                toInsertBookkeepingDTO.setCampaignUID(campaignDTO.getUid());
                toInsertBookkeepingDTO.setUnlockedUID(null);

                int result = bookkeepingServiceImpl.bookkeepingInsert(toInsertBookkeepingDTO);
                logger.debug("bookkeeping insert result: {}", result);

                //포인트 증정 이벤트가 있을 경우 처리
                int result2 = -1;
                if (campaignDTO.getAdditionalPoint() > 0) {
                    String uuid2 = UUID.randomUUID().toString();
                    BookkeepingDTO additionalPointBookkeepingDTO = new BookkeepingDTO();
                    additionalPointBookkeepingDTO.setUUID(uuid2);
                    additionalPointBookkeepingDTO.setReferenceUUID(uuid);
                    additionalPointBookkeepingDTO.setUserid(sid);
                    additionalPointBookkeepingDTO.setAddedPoint(campaignDTO.getAdditionalPoint());
                    additionalPointBookkeepingDTO.setUsedPoint(0);
                    additionalPointBookkeepingDTO.setPurchaseMethodUID(bookkeepingDTO.getPurchaseMethodUID());
                    additionalPointBookkeepingDTO.setCampaignUID(campaignDTO.getUid());
                    additionalPointBookkeepingDTO.setUnlockedUID(null);

                    result2 = bookkeepingServiceImpl.bookkeepingInsert(additionalPointBookkeepingDTO);
                    logger.debug("bookkeeping insert result2: {}", result2);
                }

                //httpSession.setAttribute("point", bookkeepingServiceImpl.getPoint(sid));

                //포인트 증정 없을 경우
                if (result == 1 && result2 == -1) {
                    nextPage = "redirect:/shop?success=true";

                    //포인트 증정 있을 경우
                } else if (result == 1 && result2 == 1) {
                    nextPage = "redirect:/shop?success=true";
                }
            } else {
                logger.debug("cannot find campaign");
            }
        }
        return nextPage;
    }

}
