package com.project.xiaomi.controller;

import com.project.xiaomi.DBObjects.*;
import com.project.xiaomi.repositories.*;
import com.project.xiaomi.request.ServiceCenterRequest;
import com.project.xiaomi.response.ServiceCenterResponse;
import com.project.xiaomi.response.ServiceCenterToWarehouseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Service
@RestController
@RequestMapping("/service")
@CrossOrigin
@Slf4j
public class CreateRequestController {

    @Autowired
    ServiceCenterDetailRepository serviceCenterDetailRepository;
    @Autowired
    XiaomiProductsRepository xiaomiProductsRepository;
    @Autowired
    ServiceCenterToWarehouseRepository serviceCenterToWarehouseRepository;
    @Autowired
    WarehouseDetailRepository warehouseDetailRepository;
    @Autowired
    ServiceCenterRequestRepository serviceCenterRequestRepository;
    @Autowired
    WarehouseRequestRepository warehouseRequestRepository;

    @PostMapping("/createRequest")
    public ResponseEntity<ServiceCenterResponse> handleRequest(@RequestBody ServiceCenterRequest request){
        // save in service center request;
        // if model present in service center data, then return details.

        ServiceCenterResponse response=new ServiceCenterResponse();


        String modelName = request.getModel_name();
        Integer partId = request.getPartId();
        String partName = request.getPart_name();
        Integer serviceCenterSkUid = request.getServiceCenterSkUid();

        XiaomiProducts productDetails = xiaomiProductsRepository.getProductDetail(modelName, partName);

        int partExpectedTime = productDetails.getExpectedTime();
        int partExpectedPrice = productDetails.getPrice();

        response.setEmail(request.getCustomer_email());
        response.setName(request.getCustomer_name());
        response.setPhone(request.getCustomer_phone());
        response.setModelName(request.getModel_name());
        response.setPartName(request.getPart_name());
        response.setExpectedPartTime(partExpectedTime);
        response.setExpectedTransportTime(0);
        response.setExpectedPrice(partExpectedPrice);

        ServiceCenterDetail serviceCenterDetail = serviceCenterDetailRepository.findByModelNameAndPartName(modelName, partName);

        Integer priority = request.getPriority();
        if(serviceCenterDetail.getQuantity()==0){
            int expectedTransportTime = transportTime(serviceCenterSkUid,modelName,partName, priority);
            response.setExpectedTransportTime(expectedTransportTime);
        }

        // create service center request;
        ServiceCenterRequestDTO serviceCenterRequest = new ServiceCenterRequestDTO();
        serviceCenterRequest.setServiceCenterSkUid(serviceCenterSkUid);
        serviceCenterRequest.setLocation(serviceCenterDetail.getLocation());
        serviceCenterRequest.setPartSkUid(partId);
        serviceCenterRequest.setPart_name(partName);
        serviceCenterRequest.setModel_name(modelName);
        serviceCenterRequest.setPending(true);
        serviceCenterRequest.setCustomerName(request.getCustomer_name());
        serviceCenterRequest.setCustomerEmail(request.getCustomer_email());
        serviceCenterRequest.setRemarks(request.getRemarks());
        serviceCenterRequest.setPriority(priority);

        serviceCenterRequestRepository.save(serviceCenterRequest);

        return ResponseEntity.ok(response);
    }

    public int transportTime(Integer serviceCenterSkUid, String modelName, String partName, Integer priority){
        int distance = getNearestValidWarehouseDistance(serviceCenterSkUid,modelName,partName, priority);
        if(distance <=100){
            return 2;
        }
        else if(distance<=400){
            return 3;
        }
        else if(distance<=1000){
                return 4;
        }
        else{
                return 5;
        }

    }

    public int getNearestValidWarehouseDistance(Integer serviceCenterSkUid, String modelName, String partName, Integer priority){
        ServiceCenterToWarehouse distanceDetails = serviceCenterToWarehouseRepository.findByServiceCenterSkUid(serviceCenterSkUid);
        List<WarehouseDetail> warehouseDetails = warehouseDetailRepository.findByModelNameAndPartName(modelName, partName);
        String region=null;
        Integer distance=null;
        Integer warehouseSkUid=null;
        for(WarehouseDetail warehouseDetail : warehouseDetails){
            String warehouseDetailRegion = warehouseDetail.getRegion();
            if (Objects.equals(warehouseDetailRegion, "East")) {
                Integer dist = distanceDetails.getDistEast();
                if(warehouseDetail.getQuantity()>0){
                    if(distance==null){
                        distance=dist;
                        region="East";
                        warehouseSkUid = warehouseDetail.getWarehouseSkUid();
                    }
                    else{
                        if(distance > dist){
                            distance=dist;
                            region="East";
                            warehouseSkUid = warehouseDetail.getWarehouseSkUid();
                        }
                    }
                }
            }
            else if (Objects.equals(warehouseDetailRegion, "West")) {
                int dist = distanceDetails.getDistWest();
                if(warehouseDetail.getQuantity()>0){
                    if(distance==null){
                        distance=dist;
                        region="West";
                        warehouseSkUid = warehouseDetail.getWarehouseSkUid();
                    }
                    else{
                        if(distance > dist){
                            distance=dist;
                            region="West";
                            warehouseSkUid = warehouseDetail.getWarehouseSkUid();
                        }
                    }
                }
            }
            else if(Objects.equals(warehouseDetailRegion, "North")){
                int dist = distanceDetails.getDistNorth();
                if(warehouseDetail.getQuantity()>0){
                    if(distance==null){
                        distance=dist;
                        region="North";
                        warehouseSkUid = warehouseDetail.getWarehouseSkUid();
                    }
                    else{
                        if(distance > dist){
                            distance=dist;
                            region="North";
                            warehouseSkUid = warehouseDetail.getWarehouseSkUid();
                        }
                    }
                }
            }
            else{
                int dist = distanceDetails.getDistSouth();
                if(warehouseDetail.getQuantity()>0){
                    if(distance==null){
                        distance=dist;
                        region="South";
                        warehouseSkUid = warehouseDetail.getWarehouseSkUid();
                    }
                    else{
                        if(distance > dist){
                            distance=dist;
                            region="South";
                            warehouseSkUid = warehouseDetail.getWarehouseSkUid();
                        }
                    }
                }
            }
        }

        WarehouseRequestDTO warehouseRequest = new WarehouseRequestDTO();

        warehouseRequest.setWarehouseSkUid(warehouseSkUid);
        warehouseRequest.setModelName(modelName);
        warehouseRequest.setPartName(partName);
        warehouseRequest.setIsPending(true);
        warehouseRequest.setQuantityRequired(null);
        warehouseRequest.setPriority(priority);
        warehouseRequest.setRegion(region);
        warehouseRequest.setServiceCenterSkUid(serviceCenterSkUid);

        log.info("warehouseRequest ={}",warehouseRequest);
        warehouseRequestRepository.save(warehouseRequest);

        return distance;
    }

}
