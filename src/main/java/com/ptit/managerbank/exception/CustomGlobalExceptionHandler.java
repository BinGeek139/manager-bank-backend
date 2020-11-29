package com.ptit.managerbank.exception;

import com.ptit.managerbank.common.Constants;
import com.ptit.managerbank.common.ResponseData;
import com.ptit.managerbank.dto.StaffDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        BindingResult bindingResult = ex.getBindingResult();
        HashMap<String,String> fieldErrors=new HashMap<>();
      bindingResult.getFieldErrors().forEach(fieldError -> {
          fieldErrors.put(fieldError.getField(),getText(fieldError.getDefaultMessage(),request.getLocale()));
      });

        ResponseData responseData=new ResponseData();
        responseData.setMessage(getText("error.valid",request.getLocale()));
        responseData.setData(fieldErrors);
        responseData.setErrorCode(Constants.ERROR_CODE.VALIDATE_FAIL);
        return ResponseEntity.ok(responseData);

    }
        public String getText(String key,Locale locale) {
            try {
                return messageSource.getMessage(key, null, locale);
            } catch (Exception e) {
                e.printStackTrace();
                return key;

        }

}}
