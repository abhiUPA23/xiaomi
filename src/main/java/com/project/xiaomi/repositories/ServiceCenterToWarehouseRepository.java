package com.project.xiaomi.repositories;

import com.project.xiaomi.DBObjects.ServiceCenterToWarehouse;
import com.project.xiaomi.DBObjects.XiaomiProducts;
import com.project.xiaomi.response.ServiceCenterToWarehouseResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ServiceCenterToWarehouseRepository extends JpaRepository<ServiceCenterToWarehouse,Integer> {


    @Query(value="select s from ServiceCenterToWarehouse s where s.serviceCenterSkUid=:serviceCenterSkUid")
    ServiceCenterToWarehouse findByServiceCenterSkUid(Integer serviceCenterSkUid);

}
