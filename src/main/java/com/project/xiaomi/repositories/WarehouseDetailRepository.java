package com.project.xiaomi.repositories;

import com.project.xiaomi.DBObjects.WarehouseDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseDetailRepository extends JpaRepository<WarehouseDetail,Integer> {

//    @Query(value = "select * from warehouse_details wd wdhere wd.model_name = :modelName and wd.part_name = :partName", nativeQuery = true)
    List<WarehouseDetail> findByModelNameAndPartName(String modelName, String partName);

}
