package com.project.xiaomi.DBObjects;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "warehouse_requests")
public class WarehouseRequestDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="request_id")
    int requestId;

    int warehouseSkUid;
    String region;
    String modelName;
    String partName;
    Boolean isPending;
    int serviceCenterSkUid;
    Integer quantityRequired;
    Integer priority;

}
