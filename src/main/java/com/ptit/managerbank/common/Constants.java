package com.ptit.managerbank.common;

public class Constants {
    public static final String BANK_CODE="1500";
    public static final int MAX_ACCOUNT_BANK=2;
    public static final int MAX_SAVING_ACCOUNT_BANK=3;
    public static final String NHAN_VIEN_KINH_DOANH="Nhân viên kinh doanh";
    public static final double PROFIT_EACH_ACCOUNT_CREDIT=500000;
    public static final double PROFIT_EACH_ACCOUNT_SAVING=0.02;
    public static final class ERROR_CODE {
        public static final String SUCCESS = "0";
        public static final String VALIDATE_FAIL = "1";
        public static final String FAIL = "2";
        public static final String NOT_FOUND = "3";
        public static final String SYSTEM_ERROR = "-1";
    }
}
