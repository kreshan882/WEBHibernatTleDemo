/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.FieldEngineerManagement.service;

import com.epic.tle.FieldEngineerManagement.bean.Terminal;

/**
 *
 * @author kasun_k
 */
public interface KeyInjectionServiceInf {
    public Terminal getTerminal(String terminalKI) throws Exception;
    public void getKeyInjectParems() throws Exception;
    public boolean updateTerminalKeys(Terminal terminal) throws Exception;
    public void getPinMailerParems() throws Exception;
    public void getKeyMailerParems() throws Exception;
}
