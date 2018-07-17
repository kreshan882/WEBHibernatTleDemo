<%-- 
    Document   : csrfError
    Created on : Dec 27, 2017, 12:18:24 PM
    Author     : chandana_l
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <title>Epic TLE</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <jsp:include page="../Styles.jsp" />
        <style>
            body #msg_div .msg-panel {
                width: 100%;
                margin: 10px 0px 4px 0px;
                text-align: left;
                font-family: arial;
                font-size: 1em;
                float: left;
                font-weight: bold;
              }
              body #msg_div .msg-panel.error-csrf-msg {
                text-align: center;
                color: #d9534f;
              }
              
              .actionMessage{
                color: green;
               }
               img 
               {
                 max-width: 100%;
                 max-height: 100%;
               }
               body #msg_div .square 
               {
                 margin: auto;
                 width: 50%;
                 text-align: center;
                 height: 10%;
                 width: 10%;
               }        
        </style>
    </head>
    <body> 
        <section class="app-content">
            <jsp:include page="../header.jsp" /> 
                <div class="content innerpage">
                    <div class="content-section" id="msg_div">
                        <div class="msg-panel error-csrf-msg">
                            <div class="square">
                                <img src="${pageContext.request.contextPath}/resources/images/Stop.png" alt="Access Denied">
                            </div>
                            <span>Action terminated. Failed to validate access token.</span>
                         </div>
                    </div>
                    <jsp:include page="../footer.jsp" />
                </div>
        </section>
    </body>
</html>
