package com.example.spring_jsp.shop.bookkeeping;

import java.util.List;

public interface BookkeepingService {

    public int bookkeepingInsert(BookkeepingDTO bookkeepingDTO);

    //현재 잔여 포인트 가져오기
    public int getPoint(String userid);

    public List<String> getUnlockedUID(String userid);
}
