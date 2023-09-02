package com.project.xiaomi.DBObjects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "service_center_to_warehouse")
public class ServiceCenterToWarehouse {
    @Id
    @Column(name = "service_center_id")
    Integer serviceCenterSkUid;


    @Column(name = "Distance_north")
    Integer distNorth;
    @Column(name = "Distance_east")
    Integer distEast;
    @Column(name = "Distance_west")
    Integer distWest;
    @Column(name = "Distance_south")
    Integer distSouth;
}
