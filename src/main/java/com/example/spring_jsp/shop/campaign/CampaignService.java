package com.example.spring_jsp.shop.campaign;

import java.util.List;

public interface CampaignService {

    public CampaignDTO selectCampaign(String uid);

    public List<CampaignDTO> getCurrentCampaign();

    public int[] purchasePoint(String sid, String purchaseMethod, CampaignDTO campaignDTO);
}
