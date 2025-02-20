package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "Lessor")
public class Lessor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Lessor_Id")
    Integer lessorId;

    @Column(name = "Lessor_Name", nullable = false)
    String lessorName;

    @Column(name = "Lessor_Email", nullable = false, unique = true)
    String lessorEmail;

    @Column(name = "Lessor_Address", nullable = false)
    String lessorAddress;

    @Column(name = "Lessor_Password", nullable = false)
    String lessorPassword;

    @Column(name = "Revenue")
    Float revenue;

    @Column(name = "Lessor_Status")
    String lessorStatus;

    @OneToMany(mappedBy = "lessor", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    List<Product> products;
}
