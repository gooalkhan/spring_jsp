package com.example.spring_jsp.shop.campaign;

import com.example.spring_jsp.notification.NotificationService;
import com.example.spring_jsp.shop.bookkeeping.BookkeepingDTO;
import com.example.spring_jsp.shop.bookkeeping.BookkeepingServiceImpl;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CampaignServiceImpl {
    List<CampaignDTO> campaignList = new ArrayList<>();
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final NotificationService notificationService;
    private final BookkeepingServiceImpl bookkeepingServiceImpl;

    //uid로 행사 가져오기, 폼과 비교용
    public CampaignDTO selectCampaign(String uid) {
        for (CampaignDTO campaignDTO : campaignList) {
            if (campaignDTO.getUid().equals(uid)) {
                return campaignDTO;
            }
        }
        return null;
    };

    //모든 진행중인 행사 가져오기 - 테이블 안만들고 간이로 사용
    public List<CampaignDTO> getCurrentCampaign() {
        return campaignList;
    }

    public int[] purchasePoint(BookkeepingDTO bookkeepingDTO) {
        String sid = bookkeepingDTO.getUserid();
        int result = -1;
        int result2 = -1;
        String uuid = UUID.randomUUID().toString();

        CampaignDTO campaignDTO = selectCampaign(bookkeepingDTO.getCampaignUID());

        //폼에서 받은 판매정보가 서버의 판매정보와 일치하는지 확인
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

            result = bookkeepingServiceImpl.bookkeepingInsert(toInsertBookkeepingDTO);
            logger.debug("bookkeeping insert result: {}", result);

            //포인트 증정 이벤트가 있을 경우 처리

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
            if (result == 1 && result2 != 0) {
                notificationService.send(sid, "포인트 구입에 성공했습니다");
            } else {
                notificationService.send(sid, "에러가 발생했습니다 - 포인트 구매에 실패하였습니다");
            }
        } else {
            notificationService.send(sid, "에러가 발생했습니다  - 포인트 구매에 실패하였습니다");
        }
        return new int[]{result, result2};
    }

    @PostConstruct
    public void init() {
        CampaignDTO campaignDTO1 = new CampaignDTO();
        CampaignDTO campaignDTO2 = new CampaignDTO();
        CampaignDTO campaignDTO3 = new CampaignDTO();

        campaignDTO1.setUid("hundred");
        campaignDTO2.setUid("five-hundred");
        campaignDTO3.setUid("thousand");

        campaignDTO1.setCampaignName("캠페인1");
        campaignDTO2.setCampaignName("캠페인2");
        campaignDTO3.setCampaignName("캠페인3");

        campaignDTO1.setCampaignDescription("첫 유저에게 추천");
        campaignDTO2.setCampaignDescription("5%를 추가로 적립드립니다");
        campaignDTO3.setCampaignDescription("10%를 추가로 적립드립니다");

        campaignDTO1.setPoint(100);
        campaignDTO2.setPoint(500);
        campaignDTO3.setPoint(1000);

        campaignDTO1.setAdditionalPoint(0);
        campaignDTO2.setAdditionalPoint(25);
        campaignDTO3.setAdditionalPoint(100);

        campaignList.add(campaignDTO1);
        campaignList.add(campaignDTO2);
        campaignList.add(campaignDTO3);
    }

}
