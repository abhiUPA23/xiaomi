package com.project.xiaomi.controller;

import com.project.xiaomi.DBObjects.WarehouseRequestDTO;
import com.project.xiaomi.repositories.WarehouseRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/warehouse")
@CrossOrigin
public class WarehouseController {

    @Autowired
    WarehouseRequestRepository warehouseRequestRepository;

    @PostMapping("/updatePending")
    public ResponseEntity<WarehouseRequestDTO> updatePendingStatus(@RequestBody WarehouseRequestDTO request){

        WarehouseRequestDTO warehouseRequestDTO = warehouseRequestRepository.findByRequestId(request.getRequestId());
        // crush the variables of the object found
        warehouseRequestDTO.setIsPending(false);
        try {
            warehouseRequestRepository.save(warehouseRequestDTO);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(warehouseRequestDTO,HttpStatus.OK);

    }
}
