/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.token.service;

/**
 *
 * @author chandana_l
 */
public class TokenFactory {
    private TokenInf tokenInf=null;
    public TokenFactory() {
        if(tokenInf==null){
            this.tokenInf=new TokenService();
        }
    }
    public TokenInf getTokenInf() {
        return this.tokenInf;
    }
    
}
