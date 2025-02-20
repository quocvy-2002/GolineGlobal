package com.example.demo.dto.request;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreateProductRequest {
    String productName;
    String productType;
    Float priceShort;
    Float priceLong;
    String productStatus;
    String address;
    Float acreage;
    String describes;

}
