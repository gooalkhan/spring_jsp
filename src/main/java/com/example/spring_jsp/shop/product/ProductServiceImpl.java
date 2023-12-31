package com.example.spring_jsp.shop.product;

import com.example.spring_jsp.notification.NotificationService;
import com.example.spring_jsp.shop.bookkeeping.BookkeepingDTO;
import com.example.spring_jsp.shop.bookkeeping.BookkeepingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/*
 * 분석정보 구매 서비스
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final Map<String, ProductDTO> productDTOMap = new HashMap<>();
    private final BookkeepingService bookkeepingService;
    private final NotificationService notificationService;

    public List<ProductDTO> selectProductAll() {
        return new ArrayList<>(productDTOMap.values());
    }

    public ProductDTO selectProduct(String uid) {
        return productDTOMap.get(uid);
    }

    public ProductDTO buildInsertProduct(long bookid, String productid, String userid) {
        ProductBuilder productBuilder = new ProductBuilder(bookid, userid);
        ProductDTO productDTO = productBuilder.addProduct(productid).build();
        productDTOMap.put(productDTO.getUid(), productDTO);

        return productDTO;
    }

    //구매된 제품이 아닌 판매 가능한 제품만 조회한다는 점에 주의할것
    public List<ProductDTO> selectProductCountByBookUserProduct(long bookid, String productid, String userid) {
        List<ProductDTO> result = new ArrayList<>();
        for (Map.Entry<String, ProductDTO> entry : productDTOMap.entrySet()) {
            ProductDTO productDTO = entry.getValue();
            if (productDTO.getBookid() == bookid && productDTO.getUserid().equals(userid) && productDTO.getProductid().equals(productid)) {
                result.add(productDTO);
            }
        }
        return result;
    }

    //분석을 구매했는지 확인
    public List<ProductDTO> productsUnlockedByCondition(long bookid, String productid, String userid) {
        List<ProductDTO> result = new ArrayList<>();

        List<String> unlockedUID = bookkeepingService.getUnlockedUID(userid);
        for (String uid : unlockedUID) {
            ProductDTO productDTO = productDTOMap.get(uid);
            if (productDTO.getBookid() == bookid && productDTO.getProductid().equals(productid)) {
                result.add(productDTO);
            }
        }
        return result;
    }

    public int insertProduct(ProductDTO productDTO) {
        productDTOMap.put(productDTO.getUid(), productDTO);
        log.debug("insertProduct: " + productDTO);
        return 1;
    }

    public int purchaseProduct(String sid, ProductDTO productDTO) {
        int result = -1;

        //해당 제품이 있는지 확인
        ProductDTO toBePurchaseProductDTO = selectProduct(productDTO.getUid());
        if (toBePurchaseProductDTO != null) {

            //포인트가 충분한지 확인
            if (bookkeepingService.getPoint(sid) >= toBePurchaseProductDTO.getUsedPoint()) {

                //구매
                String uuid = UUID.randomUUID().toString();
                BookkeepingDTO bookkeepingDTO = new BookkeepingDTO();
                bookkeepingDTO.setUUID(uuid);
                bookkeepingDTO.setReferenceUUID(uuid);
                bookkeepingDTO.setUserid(sid);
                bookkeepingDTO.setAddedPoint(0);
                bookkeepingDTO.setUsedPoint(toBePurchaseProductDTO.getUsedPoint());
                bookkeepingDTO.setUnlockedUID(toBePurchaseProductDTO.getUid());

                int subResult = bookkeepingService.bookkeepingInsert(bookkeepingDTO);

                if (subResult == 1) {
                    //구매 성공
                    result = 1;
                    notificationService.send(sid, "구매에 성공했습니다.");
                    log.debug("purchase success for sid: {} bookid: {} productid: {}", sid, productDTO.getBookid(), productDTO.getProductid());
                } else {
                    //구매 실패
                    notificationService.send(sid, "구매에 실패했습니다.");
                    log.debug("purchase failed for sid: {} bookid: {} productid: {}", sid, productDTO.getBookid(), productDTO.getProductid());
                }

            } else {
                //포인트 부족
                notificationService.send(sid, "포인트가 부족합니다.");
                log.debug("not enough point purchase failed for sid: {} bookid: {} productid: {}", sid, productDTO.getBookid(), productDTO.getProductid());
            }
        }
        return result;
    }
}
