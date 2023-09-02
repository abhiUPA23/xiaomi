package com.project.xiaomi.request;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class ServiceCenterRequest {

    String customer_name;
    String customer_email;
    String customer_phone;

    String model_name;
    String part_name;
    int partId;
    Integer serviceCenterSkUid;
    String remarks;
    Integer priority;
}
