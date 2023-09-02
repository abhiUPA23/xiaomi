package com.project.xiaomi.response;

import lombok.Data;

@Data
public class ServiceCenterResponse {
    String name;
    String email;
    String phone;

    String modelName;
    String partName;

    int expectedPartTime;
    int expectedTransportTime;
    int expectedPrice;
}
