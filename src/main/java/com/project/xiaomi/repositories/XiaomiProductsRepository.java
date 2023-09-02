package com.project.xiaomi.repositories;

import com.project.xiaomi.DBObjects.XiaomiProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface XiaomiProductsRepository extends JpaRepository<XiaomiProducts,Integer> {

    @Query(value = "select * from xiaomi_products p where p.product_model = :modelName and p.part_name = :partName", nativeQuery = true)
    XiaomiProducts getProductDetail(String modelName, String partName);

}
