package com.project.xiaomi.repositories;

import com.project.xiaomi.DBObjects.ServiceCenterDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface ServiceCenterDetailRepository extends JpaRepository<ServiceCenterDetail,Integer> {

    /*@Query(value = "select * from service_center_details sd where sd.model_name = :modelName and sd.part_name = :partName", nativeQuery = true)
    int getQuantity(String modelName, String partName);*/

    ServiceCenterDetail findByModelNameAndPartName(String modelName, String partName);
}
