package com.fanwe.model;

import lombok.Data;

@Data
public class LocalUserModel {
    private String user_id;
    private String supplier_id;
    private String account_name;
    private String account_password;
    private String qr_code;
}
