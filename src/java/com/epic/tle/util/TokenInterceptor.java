/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;


import com.epic.tle.token.bean.TokenBean;
import com.epic.tle.token.service.TokenFactory;
import com.epic.tle.util.constant.TokenConst;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.interceptor.Interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;


/**
 *
 * @author chandana_l
 */
public class TokenInterceptor implements Interceptor{

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void init() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        String result = "";
        String sessionToken="";
        String requestToken="";
        String INTERCEPT_LOGOUT = "invalid.token";
        TokenBean tokenBean= new TokenBean();
        TokenFactory tokenFactory=new TokenFactory();       
        try
        {           
            HttpServletRequest request = ServletActionContext.getRequest();
            HttpSession session = request.getSession(false);
            if (null != session) {
                 sessionToken =  (String)session.getAttribute(TokenConst.SESSION_TOKEN);
            }
            else{
              return INTERCEPT_LOGOUT; //session null
             }
            requestToken=(String)request.getParameter(TokenConst.REQUEST_TOKEN);
            tokenBean.setSessionToken(sessionToken);
            tokenBean.setRequestToken(requestToken);
            if(null!=tokenBean.getRequestToken() && null!=tokenBean.getSessionToken()){
                if(!tokenFactory.getTokenInf().validateToken(tokenBean)){
                    ai.setResultCode("CSRF error");
                    return INTERCEPT_LOGOUT;
                }
            }
            else{
                ai.setResultCode("CSRF error");
                return INTERCEPT_LOGOUT; //Token invalid
            }
            tokenFactory.getTokenInf().generateToken(tokenBean); 
            session.setAttribute(TokenConst.SESSION_TOKEN, tokenBean.getSessionToken());
            result=ai.invoke();
       }
       catch(Exception e){
           e.printStackTrace();
           ai.setResultCode("CSRF error");
           return INTERCEPT_LOGOUT;
       }
       return result;
    }
    
}
