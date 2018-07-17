/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.token.service;

import com.epic.tle.token.bean.TokenBean;
import com.opensymphony.xwork2.ActionSupport;
import java.util.UUID;

/**
 *
 * @author chandana_l
 */
public class TokenService implements TokenInf {

    @Override
    public boolean validateToken(TokenBean tokenBean) throws Exception {
        return (tokenBean.getRequestToken().equals(tokenBean.getSessionToken()));
    }

    @Override
    public boolean generateToken(TokenBean tokenBean) throws Exception {
        tokenBean.setSessionToken("");
        tokenBean.setSessionToken(generateUniqueToken());
        if(null!=tokenBean.getSessionToken()&&(!tokenBean.getSessionToken().equals(""))){
            return true;
        }
        return false;              
    }
    
    private String generateUniqueToken(){
        return UUID.randomUUID().toString();
    }   
}
