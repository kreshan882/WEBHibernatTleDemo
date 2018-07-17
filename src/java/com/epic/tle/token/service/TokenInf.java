/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.token.service;

import com.epic.tle.token.bean.TokenBean;

/**
 *
 * @author chandana_l
 */
public interface TokenInf{
    public boolean validateToken(TokenBean tokenBean) throws Exception;
    public boolean generateToken(TokenBean tokenBean) throws Exception;
}
