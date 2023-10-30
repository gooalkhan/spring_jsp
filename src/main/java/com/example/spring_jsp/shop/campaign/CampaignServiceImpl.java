package com.example.spring_jsp.shop.campaign;

import com.example.spring_jsp.notification.NotificationService;
import com.example.spring_jsp.shop.bookkeeping.BookkeepingDTO;
import com.example.spring_jsp.shop.bookkeeping.BookkeepingService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CampaignServiceImpl implements CampaignService {
    private final List<CampaignDTO> campaignList = new ArrayList<>();
    private final NotificationService notificationService;
    private final BookkeepingService bookkeepingService;

    //uid로 행사 가져오기, 폼과 비교용
    public CampaignDTO selectCampaign(String uid) {
        for (CampaignDTO campaignDTO : campaignList) {
            if (campaignDTO.getUid().equals(uid)) {
                return campaignDTO;
            }
        }
        return null;
    }

    //모든 진행중인 행사 가져오기 - 테이블 안만들고 간이로 사용
    public List<CampaignDTO> getCurrentCampaign() {
        return campaignList;
    }

    public int[] purchasePoint(String sid, String purchaseMethod, CampaignDTO campaignDTO) {
        int result1 = -1;
        int result2 = -1;
        int[] result = new int[]{result1, result2};

        //해당 상품이 있는지 확인
        CampaignDTO selectedCampaignDTO = selectCampaign(campaignDTO.getUid());
        if (selectedCampaignDTO != null) {
            //있으면 금액확인 후 구매처리
            BookkeepingDTO bookkeepingDTO = new BookkeepingDTO();
            bookkeepingDTO.setUserid(sid);
            bookkeepingDTO.setAddedPoint(selectedCampaignDTO.getPoint());
            bookkeepingDTO.setCampaignUID(campaignDTO.getUid());
            bookkeepingDTO.setPurchaseMethodUID(purchaseMethod);
            bookkeepingDTO.setUsedPoint(0);
            String uuid = UUID.randomUUID().toString();
            bookkeepingDTO.setUUID(uuid);
            bookkeepingDTO.setReferenceUUID(uuid);
            result[0] = bookkeepingService.bookkeepingInsert(bookkeepingDTO);

            //포인트 증정 이벤트가 있을 경우 처리, 구매가 성공했을때만!
            if (selectedCampaignDTO.getAdditionalPoint() > 0 && result[0] == 1) {
                BookkeepingDTO additionalPointBookkeepingDTO = new BookkeepingDTO();
                additionalPointBookkeepingDTO.setUserid(sid);
                additionalPointBookkeepingDTO.setAddedPoint(selectedCampaignDTO.getAdditionalPoint());
                additionalPointBookkeepingDTO.setCampaignUID(campaignDTO.getUid());
                additionalPointBookkeepingDTO.setUsedPoint(0);
                additionalPointBookkeepingDTO.setPurchaseMethodUID("addtional");
                String uuid2 = UUID.randomUUID().toString();
                additionalPointBookkeepingDTO.setUUID(uuid2);
                additionalPointBookkeepingDTO.setReferenceUUID(uuid);
                result[1] = bookkeepingService.bookkeepingInsert(additionalPointBookkeepingDTO);
            }
        } else {
            log.error("{} 상품이 없습니다", campaignDTO.getUid());
        }

        if (result[0] == 1 && result[1] != 0) {
            log.debug("sid: {} campainUID: {} 포인트 구입에 성공했습니다", sid, selectedCampaignDTO.getUid());
            notificationService.send(sid, "포인트 구입에 성공했습니다");
        } else {
            log.debug("sid: {} campainUID: {} 포인트 구입에 실패했습니다", sid, campaignDTO.getUid());
            notificationService.send(sid, "에러가 발생했습니다 - 포인트 구매에 실패하였습니다");
        }

        return result;
    }

    @PostConstruct
    public void init() {
        CampaignDTO campaignDTO1 = new CampaignDTO();
        CampaignDTO campaignDTO2 = new CampaignDTO();
        CampaignDTO campaignDTO3 = new CampaignDTO();

        campaignDTO1.setUid(randomStringGenerator());
        campaignDTO2.setUid(randomStringGenerator());
        campaignDTO3.setUid(randomStringGenerator());

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

    //html id는 숫자로 시작하면 안되고, 공백을 포함해서는 안된다.
    public static String randomStringGenerator() {
        int leftLimit = 65; // letter 'A'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();

        return(random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 90 || i >= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString());
    }
}
