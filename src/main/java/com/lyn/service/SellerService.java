package com.lyn.service;

import com.lyn.dataobject.SellerInfo;

public interface SellerService {
     SellerInfo findSellerInfoByOpenId(String openid);
}
