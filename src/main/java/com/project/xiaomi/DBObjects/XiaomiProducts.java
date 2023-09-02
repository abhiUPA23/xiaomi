package com.project.xiaomi.DBObjects;

import jakarta.persistence.*;
import lombok.Data;

import java.awt.*;

@Data
@Entity
@Table(name = "xiaomi_products")
@IdClass(ProductsId.class)
public class XiaomiProducts {

    @Id
    @Column(name = "product_model")
    String modelName;
    @Id
    @Column(name = "part_name")
    String partName;

    @Column(name = "part_id")
    int partId;
    @Column(name = "price")
    int price;
    @Column(name = "expected_time")
    int expectedTime;
}
