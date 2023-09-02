package com.project.xiaomi.response;

import lombok.Data;

@Data
public class ServiceCenterToWarehouseResponse {
    int serviceCenterSkUid;
    int distanceEast;
    int distanceWest;
    int distanceNorth;
    int distanceSouth;
}
