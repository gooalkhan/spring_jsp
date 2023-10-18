package com.example.spring_jsp.shop.bookkeeping;

import com.example.spring_jsp.notification.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BookkeepingServiceImpl implements BookkeepingService {

    private final BookkeepingMapper bookkeepingMapper;
    private final NotificationService notificationService;

    //구매 처리
    public int bookkeepingInsert(BookkeepingDTO bookkeepingDTO) {
        int result = bookkeepingMapper.bookkeepingInsert(bookkeepingDTO);

        String sid = bookkeepingDTO.getUserid();

        if (result == 1) {
            if (bookkeepingDTO.getAddedPoint() > 0) {
                notificationService.send(sid, "포인트 구입에 성공했습니다");
            } else {
                notificationService.send(sid, "포인트 사용에 성공했습니다");
            }

        } else {
            notificationService.send(sid, "에러가 발생했습니다");
        }

        return result;
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
