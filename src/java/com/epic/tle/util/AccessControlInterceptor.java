/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;

/**
 *
 * @author kreshan
 */
import com.epic.tle.login.action.ForgetPasswordAction;
import com.epic.tle.login.action.LoginAction;
import com.epic.tle.login.action.ResetPasswordAction;
import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.util.constant.SessionVariable;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.ActionProxy;
import com.opensymphony.xwork2.interceptor.Interceptor;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.StrutsStatics;

public class AccessControlInterceptor implements Interceptor {

    @Override
    public void destroy() {
        //throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void init() {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String intercept(ActionInvocation ai) throws Exception {
        String result = "";
        String INTERCEPT_LOGOUT = "noaccessPage";
        ActionProxy ap = ai.getProxy();
        String method = ap.getMethod();

        try {
            String className = ai.getAction().getClass().getName();
            if (ai.getAction() instanceof ResetPasswordAction) {
                result = ai.invoke();
            } else if (ai.getAction() instanceof LoginAction) {
                result = ai.invoke();
            } else if (ai.getAction() instanceof ForgetPasswordAction) {
                result = ai.invoke();
            } else {

                HttpServletRequest request = ServletActionContext.getRequest();
                HttpSession session = request.getSession(false);

                if (session != null) {
                    SessionUserBean sessionUser = (SessionUserBean) session.getAttribute("SessionObject");
                    if (sessionUser != null) {
                        //check user logged in another mechine
                        ServletContext sc = ServletActionContext.getServletContext();
                        HashMap<String, String> userMap = (HashMap<String, String>) sc.getAttribute(SessionVariable.USERMAP);
                        String sessionId = userMap.get(sessionUser.getUserName());
                        if (sessionId.equals(session.getId())) {
                            Object action = ai.getAction();
                            if (action instanceof AccessControlService) {
                                if (((AccessControlService) action).checkAccess(method, sessionUser.getUserLevel())) {
                                    result = ai.invoke();
                                } else {
                                    System.out.println("acces denied - interceptor log out");
                                    result = INTERCEPT_LOGOUT;
                                }

                            } else {
                            }
                        } else {
                            result = "multiaccess";
                        }
                    } else {
                        result = INTERCEPT_LOGOUT; //session user null
                    }
                } else {
                    result = INTERCEPT_LOGOUT; //session null
                }
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            result = INTERCEPT_LOGOUT;
        }
        return result;
    }

}
