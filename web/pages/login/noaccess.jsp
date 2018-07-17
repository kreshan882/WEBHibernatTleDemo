<%-- 
    Document   : noaccess
    Created on : Nov 19, 2014, 10:13:21 AM
    Author     : kreshan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <jsp:include page="../../Styles.jsp" />
        




    </head>

    <!--<body onload="noBack();"onpageshow="if (event.persisted) noBack();" onunload="" style="overflow:hidden">-->
    <body>   
        <div class="wrapper">
            <jsp:include page="../../header.jsp" /> 

            <!--Body Content-->
            <div class="body_right">
                <div class="heading"></div>
                <div class="content">
                    <div class="content_highlight"></div>
                    <div class="contentcenter" style="color: #cc0000;">
                        <h2>You don't have access to this page !</h2>
                    </div>
                    
                </div>
            </div>
        </div>
        <!--End of Body Content-->


        <jsp:include page="../../footer.jsp" />
    </div><!--End of Wrapper-->
</body>
</html>
