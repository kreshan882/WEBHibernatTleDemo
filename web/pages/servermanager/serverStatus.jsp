<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <jsp:include page="../../Styles.jsp" />

        <style>

            .mainTable{

                border-collapse: collapse;
                font-family: arial; 
                font-size:15px;
                color:#323232;
                font-weight:bold;
                padding-left:5px;
                white-space: nowrap;

                /*border:2px solid #dcdfdc;*/
            }
        </style> 


        <script type="text/javascript">



            function serverStop() {




                $.ajax({
                    url: '${pageContext.request.contextPath}/SystemStopserStat',
                    data: {},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {




                    },
                    error: function(data) {


                    }
                });



            }






            function serverRestart() {




                $.ajax({
                    url: '${pageContext.request.contextPath}/SystemRestartserStat',
                    data: {},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {




                    },
                    error: function(data) {


                    }
                });



            }



            function sessionRestart() {




                $.ajax({
                    url: '${pageContext.request.contextPath}/SessionRestartserStat',
                    data: {},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {




                    },
                    error: function(data) {


                    }
                });



            }

            function sessionStop() {




                $.ajax({
                    url: '${pageContext.request.contextPath}/SessionStopserStat',
                    data: {},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {




                    },
                    error: function(data) {


                    }
                });



            }


            function sessionStart() {




                $.ajax({
                    url: '${pageContext.request.contextPath}/SessionStartserStat',
                    data: {},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {




                    },
                    error: function(data) {


                    }
                });



            }


            function vncStart() {

                 


                $.ajax({
                    url: '${pageContext.request.contextPath}/VNCServiceStartserStat',
                    data: {},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
                        
                        alert('succes');
                  


                    },
                    error: function(data) {
                        
                        alert('eror');  
                    }
                });



            }


        </script>
    </head>

    <!--<body onload="noBack();"onpageshow="if (event.persisted) noBack();" onunload="" style="overflow:hidden">-->
    <body style="overflow:hidden">   
        <div class="wrapper">
            <jsp:include page="../../header.jsp" /> 

            <!--Body Content-->
            <div class="body_right">

                <div class="heading">
                     <div>
                        <ul class="breadcrumb">
                            <li>Server Management</li>
                            <li><i class="fa fa-angle-double-right" aria-hidden="true"></i> 
                                Server Status</li>
                        </ul>
                    </div>
                   
                <div class="content" >
                    <!--<div class="content_highlight"></div>-->
                    <div class="content_highlight">  
                        <div class="message">   
                            <s:div id="divmsg">
                                <i style="color: red; background-color: black !important;">  <s:actionerror theme="jquery"/></i>
                                <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                            </s:div>
                        </div>
                    </div>

                    <div class="display">
                    <table class="mainTable" border="0px" width="60%" height="60%" >

                        <td align="left" width="15%"> </td>
                        <tr>
                            <td align="left">System Property</td>  
                        </tr>

                        <tr>
                            <td>
                                <div class="horizontalline" > </div>
                            </td>
                            <td>
                                <div class="horizontalline" > </div>
                            </td>
                            <td>
                                <div class="horizontalline" > </div>
                            </td>
                               <td>
                                <div class="horizontalline" > </div>
                            </td>

                        </tr>
                        <tr > 

                            <td colspan="1"></td>

                            <td > 
                                <a href="" onclick="serverRestart()"  class="add"  ><div >Restart Server </div></a>
                            </td>

                            <td >
                                <a href="" onclick="serverStop()"  class="add"  ><div >Server Stop </div></a>
                            </td>

                        </tr>



                        <tr>        

                            <td align="left">TLE  Property</td>
                            <td align="left" width="30%"></td>
                        </tr>
                        <tr>
                            <td>
                                <div class="horizontalline" > </div>
                            </td>
                            <td>
                                <div class="horizontalline" > </div>
                            </td>
                            <td>
                                <div class="horizontalline" > </div>
                                
                            </td>
                               <td>
                                <div class="horizontalline" > </div>
                            </td>

                        </tr>
                        <tr>
                             <td colspan="1"></td>
                            <td>
                                <a href="" onclick="sessionStart()"  class="add"  ><div >Session Start </div></a>
                            </td>
                            <td>
                                <a href="" onclick="sessionStop()"  class="add"  ><div >Session Stop </div></a></div>   
                            </td>
                            <td>
                                <div class=""><a href="" onclick="sessionRestart()"  class="add"  ><div >Session Restart </div></a></div>  
                            </td>

                        </tr>

                        <tr>

                            <td align="left">VNC Property</td>
                            <td  align="left" width="30%"></td>
                        </tr>
                        <tr>
                            <td>
                                <div class="horizontalline" > </div>
                            </td>
                            <td>
                                <div class="horizontalline" > </div>
                            </td>
                            <td>
                                <div class="horizontalline" > </div>
                            </td>
                               <td>
                                <div class="horizontalline" > </div>
                            </td>

                        </tr>
                        <tr>
                             <td colspan="1"></td>
                            <td>
                                <div class=""><a href="" onclick="vncStart()"  class="add"  ><div >VNC Start </div></a></div>  
                            </td>
                        </tr>


                    </table>


</div>





                </div>
            </div> 

            <jsp:include page="../../footer.jsp" />
    </body>
</html>
