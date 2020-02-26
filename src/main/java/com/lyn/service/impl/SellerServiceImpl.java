package com.lyn.service.impl;

import com.lyn.dataobject.SellerInfo;
import com.lyn.repository.SellerInfoRepository;
import com.lyn.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    private SellerInfoRepository sellerInfoRepository;
    @Override
    public SellerInfo findSellerInfoByOpenId(String openid) {
        return sellerInfoRepository.findByOpenid(openid);
    }
}
