package com.example.spring_jsp.shop.bookkeeping;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 구매처리 서비스
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class BookkeepingServiceImpl implements BookkeepingService {

    private final BookkeepingMapper bookkeepingMapper;

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

    //구매한 분석정보 가져오기
    public List<String> getUnlockedUID(String userid) {
        List<String> list =  bookkeepingMapper.getUnlockedUID(userid);
        while (list.remove(null));

        return list;
    }

}
