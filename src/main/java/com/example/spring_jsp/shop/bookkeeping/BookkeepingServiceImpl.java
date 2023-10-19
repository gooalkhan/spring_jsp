package com.example.spring_jsp.shop.bookkeeping;

import com.example.spring_jsp.notification.NotificationService;
import com.example.spring_jsp.shop.campaign.CampaignDTO;
import com.example.spring_jsp.shop.campaign.CampaignServiceImpl;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class BookkeepingServiceImpl implements BookkeepingService {

    private final BookkeepingMapper bookkeepingMapper;
    private final NotificationService notificationService;
    private final CampaignServiceImpl campaignServiceImpl;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //구매 처리
    public int bookkeepingInsert(BookkeepingDTO bookkeepingDTO) {
        return bookkeepingMapper.bookkeepingInsert(bookkeepingDTO);
    }

    public int[] purchasePoint(BookkeepingDTO bookkeepingDTO) {
        String sid = bookkeepingDTO.getUserid();
        int result = -1;
        int result2 = -1;
        String uuid = UUID.randomUUID().toString();

        CampaignDTO campaignDTO = campaignServiceImpl.selectCampaign(bookkeepingDTO.getCampaignUID());

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

            result = bookkeepingInsert(toInsertBookkeepingDTO);
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

                result2 = bookkeepingInsert(additionalPointBookkeepingDTO);
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

    //현재 잔여 포인트 가져오기
    public int getPoint(String userid) {
        int point = 0;
        int used = 0;
        List<BookkeepingDTO> list = bookkeepingMapper.bookkeepingSelect(userid);
        for (BookkeepingDTO bookkeepingDTO : list) {
            point += bookkeepingDTO.getAddedPoint();
            used += bookkeepingDTO.getUsedPoint();
        }
        return point - used;
    }

}
