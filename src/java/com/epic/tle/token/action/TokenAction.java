/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.token.action;

import com.epic.tle.token.bean.TokenBean;
import com.epic.tle.util.AccessControlService;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 *
 * @author chandana_l
 */
public class TokenAction extends ActionSupport implements AccessControlService, ModelDriven<TokenBean>{
    TokenBean inputBean = new TokenBean();
    
    @Override
    public TokenBean getModel() {
        return inputBean;
    }
    
    @Override
    public String execute() {
        if (inputBean.getMessage()!= null && !inputBean.getMessage().isEmpty()) {
            String message = inputBean.getMessage();
            if (message.equals("csrfError")) {
                addActionError("csrfError");
            }
        }
        addActionError("csrfError");
        return "redirect";
    }
    
    @Override
    public boolean checkAccess(String method, int userRole) {
        return true;
    }
    
}
