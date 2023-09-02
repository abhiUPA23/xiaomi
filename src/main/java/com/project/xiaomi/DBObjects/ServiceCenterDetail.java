package com.project.xiaomi.DBObjects;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "service_center_details")
public class ServiceCenterDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "service_center_id")
    int serviceCenterSkUid;
    int partSkUid;
    String location;
    String partName;
    String modelName;
    String repair_time;
    int quantity;
}
