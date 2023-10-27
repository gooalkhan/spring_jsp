package com.example.spring_jsp.shop.product;

import java.util.List;

public interface ProductService {

    List<ProductDTO> selectProductAll();

    ProductDTO selectProduct(String uid);

    ProductDTO buildInsertProduct(long bookid, String productid, String userid);

    List<ProductDTO> selectProductCountByBookUserProduct(long bookid, String productid, String userid);

    //분석을 구매했는지 확인
    List<ProductDTO> productsUnlockedByCondition(long bookid, String productid, String userid);

    int insertProduct(ProductDTO productDTO);

    int purchaseProduct(String sid, ProductDTO productDTO);

}
