package com.example.spring_jsp.shop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/shop")
@Controller
public class ShopController {

    @GetMapping("")
    public String shop() {
        return "shop/shop";
    }
}
