package com.project.xiaomi.controller;

import com.project.xiaomi.DBObjects.LoginCredentials;
import com.project.xiaomi.DBObjects.WarehouseRequestDTO;
import com.project.xiaomi.repositories.LoginRepository;
import com.project.xiaomi.repositories.WarehouseRequestRepository;
import com.project.xiaomi.request.LoginRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@CrossOrigin(origins = "*", allowedHeaders = "*")
@CrossOrigin(origins = "http://localhost:3000")


@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    LoginRepository loginRepository;
    @Autowired
    WarehouseRequestRepository warehouseRequestRepository;


    @PostMapping("/service/login")
    public ResponseEntity<Integer> serviceLoginCredentials(@RequestBody LoginRequest request){

        String email = request.getEmail();
        String password = request.getPassword();
        log.info("reached here email={},password={}",email,password);
        LoginCredentials credentials = loginRepository.q(email, password);
        log.info("credentials={}",credentials);
        if(credentials==null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(credentials.getId(),HttpStatus.OK);
        }

    }


    @PostMapping("/warehouse/login")
    public ResponseEntity<List<WarehouseRequestDTO>> warehouseLoginCredentials(@RequestBody LoginRequest request){
        String email = request.getEmail();
        String password = request.getPassword();

        LoginCredentials credentials = loginRepository.q(email, password);
        log.info("warehouse email ={} password={}, credentials={}",email,password,credentials);
        if(credentials==null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        else{
//            return new ResponseEntity<>(skUid,HttpStatus.OK);
            List<WarehouseRequestDTO> requestList = warehouseRequestRepository.findByIsPending(true);
            log.info("requestList ={}",requestList);
            return new ResponseEntity<>(requestList,HttpStatus.OK);
        }

    }


    @PostMapping("/planning/login")
    public ResponseEntity<Integer> planningLoginCredentials(@RequestBody LoginRequest request){
        String email = request.getEmail();
        String password = request.getPassword();

        LoginCredentials credentials = loginRepository.q(email, password);

        if(credentials==null){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(credentials.getId(),HttpStatus.OK);
        }

    }

}
