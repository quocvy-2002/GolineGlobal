package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_Id")
    Integer productId;

    @Column(name = "Product_Name", nullable = false)
    String productName;

    @Column(name = "Product_Type", nullable = false)
    String productType;

    @Column(name = "Price_Short", nullable = false)
    Float priceShort;

    @Column(name = "Price_Long", nullable = false)
    Float priceLong;

    @Column(name = "Product_Status", nullable = false)
    String productStatus;

    @Column(name = "Address", nullable = false)
    String address;

    @Column(name = "Acreage", nullable = false)
    Float acreage;

    @Column(name = "Describes", nullable = false, columnDefinition = "TEXT")
    String describes;

    @ManyToOne
    @JoinColumn(name = "Lessor_Id", nullable = false)
    Lessor lessor;
}
