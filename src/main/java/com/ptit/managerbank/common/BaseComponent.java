package com.ptit.managerbank.common;

import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

public class BaseComponent {

    @Autowired
    private MessageSource messageSource;


    public String getText(String key, HttpServletRequest request) {
        try {
            return getText(key, request, null);
        } catch (Exception e) {
            return key;
        }
    }

    public String getText(String key) {
        try {
            return getText(key, new String[]{});
        } catch (Exception e) {
            return key;
        }
    }

    public String getText(String key, String... params) {
        try {
            Locale locale=new Locale.Builder().setLanguage("vi").build();
            return messageSource.getMessage(key, params, Locale.getDefault());
        } catch (Exception e) {
            return key;
        }
    }

    public String getText(String key, HttpServletRequest request, String... params) {
        try {
            return messageSource.getMessage(key, params, request.getLocale());
        } catch (Exception e) {
            return key;
        }
    }
}
