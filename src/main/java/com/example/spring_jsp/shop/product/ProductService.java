package com.example.spring_jsp.shop.product;

import java.util.List;

public interface ProductService {

    public List<ProductDTO> selectProductAll();

    public ProductDTO selectProduct(String uid);

    public ProductDTO buildInsertProduct(long bookid, String productid, String userid);

    public List<ProductDTO> selectProductCountByBookUserProduct(long bookid, String productid, String userid);

    //분석을 구매했는지 확인
    public List<ProductDTO> productsUnlockedByCondition(long bookid, String productid, String userid);

    public int insertProduct(ProductDTO productDTO);

    public int purchaseProduct(String sid, ProductDTO productDTO);

}
