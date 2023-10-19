package com.example.spring_jsp.shop.product;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProductBuilder {
    private String uid;
    private long bookid;
    private String userid;
    private String productid;
    private int usedPoint = 0;
    private LocalDateTime productEndDate;

    public ProductBuilder(long bookid, String userid) {
        this.bookid = bookid;
        this.userid = userid;
        this.uid = UUID.randomUUID().toString();
        this.productEndDate = LocalDateTime.now().plusYears(1);
    }

    public ProductBuilder addProduct(String productid) {
        if (this.productid != null) {
            throw new IllegalStateException("productid is already set");
        }
        this.productid = productid;

        switch (productid) {
            case "키워드":
                this.usedPoint += 50;
                break;
            case "댓글":
                this.usedPoint += 200;
                break;
            default:
                break;
        }
        return this;
    }

    public ProductDTO build() {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setUid(this.uid);
        productDTO.setBookid(this.bookid);
        productDTO.setUserid(this.userid);
        productDTO.setProductid(this.productid);
        productDTO.setUsedPoint(this.usedPoint);
        productDTO.setProductEndDate(this.productEndDate);
        return productDTO;
    }
}
