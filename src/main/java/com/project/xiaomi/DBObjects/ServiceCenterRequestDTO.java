package com.project.xiaomi.DBObjects;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "service_center_request")
public class ServiceCenterRequestDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "request_id")
    int requestId;

    int serviceCenterSkUid;
    String location;
    int partSkUid;
    String part_name;
    String model_name;
    boolean isPending;
    String customerName;
    String customerEmail;
    Integer priority;
    String remarks;

}
