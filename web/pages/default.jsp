<%-- 
    Document   : default
    Created on : Dec 8, 2017, 3:18:47 PM
    Author     : ridmi_g
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   <head>
       
       <script type="text/javascript">
           function loadonstart(){
               window.location="${pageContext.request.contextPath}/logOut.action?message=success1";
           }
           window.onload=loadonstart(); 
       </script>
   </head>
   <body>
       
   </body>
</html>