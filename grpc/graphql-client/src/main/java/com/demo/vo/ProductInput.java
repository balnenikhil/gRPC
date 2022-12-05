package com.demo.vo;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ProductInput {
    private String name;
    private String description;
    private int quantity;
    private float price;
    private String image;
    private boolean onSale;
}
