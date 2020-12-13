package com.ptit.managerbank.controller;

import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.config.JwtTokenProvider;
import com.ptit.managerbank.dto.LoginRequest;
import com.ptit.managerbank.dto.LoginResponse;
import com.ptit.managerbank.model.CustomUserDetails;
import io.swagger.models.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;
    @PostMapping
    public ResponseEntity<ResponseData> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        // Xác thực thông tin người dùng Request lên
       try{
           Authentication authentication = authenticationManager.authenticate(
                   new UsernamePasswordAuthenticationToken(
                           loginRequest.getUsername(),
                           loginRequest.getPassword()
                   )
           );
           // Set thông tin authentication vào Security Context
           SecurityContextHolder.getContext().setAuthentication(authentication);
           String jwt = tokenProvider.generateToken((CustomUserDetails) authentication.getPrincipal());
           return ResponseEntity.ok(ResponseData.ofSuccess("login success",new LoginResponse(jwt)));
       } catch (Exception e){
           return ResponseEntity.ok(ResponseData.ofFail("sai tai khoan hoac mat khau"));
       }



    }
    @PostMapping("/check-login")
    public ResponseEntity<ResponseData> checkLogin(@RequestBody String jwt){
        boolean check=tokenProvider.validateToken(jwt);
        if(check){
         return      ResponseEntity.ok(ResponseData.ofSuccess("jwt vẫn còn hạn"));

        }else {
            return   ResponseEntity.ok(ResponseData.ofFail("jwt đã hết hạn"));
        }
    }
}
