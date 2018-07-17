<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<html>
    <head>
        <title>Epic TLE</title>

        <!-- System theme includes begin -->
        <link href="${pageContext.request.contextPath}/resources/theme/darknight/css/login.css" type="text/css" rel="stylesheet" media="all" />
        <!-- End -->
    </head>

    <body>
        <!-- TLE branding begin -->
        <div class="brand">
            <a href="#" class="logo do-nothing"></a>
            <span class="slogon">Encryption Server for POS Terminal Line Encryption</span>
        </div>
        <!-- End -->

        <!-- Login form begin -->
        <div class="login-box">
            <h1 style="border-right: 0px;width: 100%;text-align: center;font-size: 20px;margin-bottom: 10px">Setup your new password</h1>
          <div class="msg-panel error-login-msg">
                <s:actionerror/>
                <s:actionmessage/>
            </div>
            <div class="login-form" style="margin-bottom: 50px;">
                <s:form id="forgetpassfrm" action="forgerPassword" namespace="/" theme="simple">
                    <s:password placeholder="New Password" id="newPassword" name="newPassword" cssClass="txt-input width-35"/>
                    <s:password placeholder="Repeat Password" id="repeatPassword" name="repeatPassword" cssClass="txt-input width-35"/>
                    <sj:submit  value="Submit"  button="true"  cssClass="btn-login"  style="width: 47%;margin-top: 20px;"/>

                </s:form>

            </div>
            <div style="text-align: center;">
                <a href="${pageContext.request.contextPath}/" style="font-size: 12px;
                   color: #32a9c5;
                   font-weight: bold;
                   font-family: arial;">Back to Login</a>
            </div>
        </div>

        <!-- End -->


        <!-- Login page footer begin -->
        <footer>
            <div class="sys-time"></div>
            <div class="footer">&copy; Epic TLE | 2017 All rights reserved. Powered by <a href="http://www.epictechnology.lk/" target="_blank" title="Epic Technologies Group">Epic Technologies Group</a>.</div>
            <div class="quick-links">
                <a href="#" class="lnk-about do-nothing"></a> <a href="#" class="lnk-sys do-nothing"></a>

                <div class="system-infor-panel">
                    <h1>&nbsp;TLE System Info <a href="#" class="close-pop do-nothing" title="Close">X</a></h1>
                    <ul> 
                        <li><label>Web Version</label><span>V3.06</span></li>
                        <li><label>Build Number</label><span>B20171106</span></li>
                        <li><label>Database</label><span><s:property value="Database"/></span></li>
                        <li><label>Switch Version</label><span>5.03</span></li>
                    </ul>
                </div>
                <div class="down-arrow sys-down-arrow"></div>

                <div class="tle-infor-panel">
                    <h1>&nbsp;About Epic TLE <a href="#" class="close-pop do-nothing" title="Close">X</a></h1>
                    <p>EPIC TLE is an extremely secured communication channel encryption solution that offers increased security from the EDC/POS terminal to the bank’s acquirer host when transferring payment transaction data online.</p>
                    <p>&nbsp;</p>
                    <p>Epic TLE system is geared to protect online transactions from all kinds of vulnerabilities and threats that originate as a result of unsecured communication channels from the terminal to the acquirer host.</p>
                </div>
                <div class="down-arrow tle-down-arrow"></div>
            </div>
        </footer>
        <!-- End -->


        <!-- Client side script includes -->
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/jquery-3.1.1.min.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/login_manager.js"></script>
        <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/submitForm.js"></script>
        <!-- End -->
    </body>
</html>