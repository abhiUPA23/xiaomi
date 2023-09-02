package com.project.xiaomi.repositories;

import com.project.xiaomi.DBObjects.ServiceCenterDetail;
import com.project.xiaomi.DBObjects.ServiceCenterRequestDTO;
import org.hibernate.query.criteria.JpaFetchParent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceCenterRequestRepository extends JpaRepository<ServiceCenterRequestDTO,Integer> {
}
