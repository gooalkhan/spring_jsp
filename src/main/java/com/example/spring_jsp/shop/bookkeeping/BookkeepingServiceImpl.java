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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    //구매 처리
    public int bookkeepingInsert(BookkeepingDTO bookkeepingDTO) {
        return bookkeepingMapper.bookkeepingInsert(bookkeepingDTO);
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

    public List<String> getUnlockedUID(String userid) {
        List<String> list =  bookkeepingMapper.getUnlockedUID(userid);
        while (list.remove(null));

        return list;
    }

}
