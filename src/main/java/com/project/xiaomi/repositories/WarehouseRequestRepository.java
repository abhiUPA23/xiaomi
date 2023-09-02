package com.project.xiaomi.repositories;

import com.project.xiaomi.DBObjects.WarehouseRequestDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WarehouseRequestRepository extends JpaRepository<WarehouseRequestDTO,Integer> {

    public List<WarehouseRequestDTO> findByIsPending(Boolean isPending);

    public WarehouseRequestDTO findByRequestId(int requestId);

}
