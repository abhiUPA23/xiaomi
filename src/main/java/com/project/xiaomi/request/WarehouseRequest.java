package com.project.xiaomi.request;

import lombok.Data;

@Data
public class WarehouseRequest {
    int serviceCenterSkUid;
    String model_name;
    String part_name;
    String region;
}
