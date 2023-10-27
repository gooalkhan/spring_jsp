package com.example.spring_jsp.shop.bookkeeping;

import java.util.List;

public interface BookkeepingService {

    int bookkeepingInsert(BookkeepingDTO bookkeepingDTO);

    //현재 잔여 포인트 가져오기
    int getPoint(String userid);

    List<String> getUnlockedUID(String userid);
}
