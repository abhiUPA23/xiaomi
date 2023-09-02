package com.project.xiaomi.DBObjects;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "warehouse_details")
public class WarehouseDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="warehouse_id")
    int warehouseSkUid;
    String region;
    int partSkUid;
    String partName;
    String modelName;
    int quantity;

}
