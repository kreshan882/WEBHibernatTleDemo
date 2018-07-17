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
        <style>
            .errorMessage{
                font-size: 14px;
                font-weight: bold;
            }
            .actionMessage{
                color: green;
            }
        </style>
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
            <h1>Login</h1><div class="form-title">Please use your username and password</div>
            <div class="msg-panel error-login-msg">
                <s:actionerror/>
                <s:actionmessage/>
            </div>
            <div class="login-form">
                <s:form id="idLoginForm" action="loginUser" namespace="/" theme="simple">
                    <s:textfield name="userName" placeholder="Username"/>
                    <s:password name="password" placeholder="Password"/>
                    <!--<input type="text" name="userName" placeholder="Username" />-->
                    <!--<input type="password" name="password" placeholder="Password" />-->
                    <input type="submit" class="btn-login" value="Sign In" />
                    <s:token />
                </s:form>

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
                        <li><label>Web Version</label><span>V3.13</span></li>
                        <li><label>Build Number</label><span>B20180702</span></li>
                        <li><label>Database</label><span><s:property value="Database"/></span></li>
                        <li><label>Switch Version</label><span>5.08</span></li>
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