package com.example.spring_jsp.shop.campaign;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CampaignServiceImpl {
    List<CampaignDTO> campaignList = new ArrayList<>();

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
