package com.example.spring_jsp.shop.campaign;

import java.util.List;

public interface CampaignService {

    CampaignDTO selectCampaign(String uid);

    List<CampaignDTO> getCurrentCampaign();

    int[] purchasePoint(String sid, String purchaseMethod, CampaignDTO campaignDTO);
}
