package com.lyn.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "seller_info")
@Data
@DynamicUpdate
public class SellerInfo {
    @Id
    private String sellerId;

    private String username;

    private String password;

    private String openid;

    private String createTime;

    private String updateTime;
}
