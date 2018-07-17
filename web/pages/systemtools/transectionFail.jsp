<%-- 
    Document   : transectionFail
    Created on : Sep 24, 2015, 10:00:31 AM
    Author     : dimuthu
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>    
<html>
    <head>

        <jsp:include page="../../Styles.jsp" />

        <style>
            .ui-jqgrid-sortable{
                font-weight: normal !important;
            }
            .add{
                width:auto !important;
            }
        </style>  
        <script>
            $.subscribe('onclicksearch', function (event, data) {
                var tid = $('#tid').val();
                var fromdate = $('#fromdate').val();
                var todate = $('#todate').val();
                var serial = $('#serial').val();
                var respCode = $('#respCode').val();
                var operationType = $('#operationType').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {tid: tid, fromdate: fromdate, todate: todate, serial: serial, respCode: respCode}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            });
        </script>

    </head>

    <body style="overflow:hidden">
        <section class="app-content">
            <jsp:include page="../../header.jsp" /> 

            <div class="content innerpage">
                <!-- Breadcrumb begin -->
                <div class="breadcrumb">
                    <s:property value="Module"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i> <span class="active"><s:property value="Section"/> </span>
                </div>
                <!-- End -->

                <!-- Page title begin -->
                <h1 class="page-title"><s:property value="Section"/><a href="#" class="lnk-back hide-element do-nothing"><i class="fa fa-arrow-left" aria-hidden="true"></i> back</a></h1>
                <!-- End -->

                <div class="content">
                    <div class="content_highlight"></div>
                    <div class="display">
                        <s:form id="transactionFailingForm"  action="XSLcreattransFail" name="transactionFailingForm" theme="simple" method="post">
                            <table border="0">                              

                                <tr>
                                    <td class="lable">Terminal ID</td>
                                    <td class="lable">:</td>
                                    <td><s:textfield name="tid" id="tid" cssClass="text_field" /></td>

                                    <td class="lable">Start Date</td>
                                    <td class="lable">:</td>
                                    <td><sj:datepicker id="fromdate" name="fromdate" readonly="true"  value="today" changeYear="true" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="text_field" cssStyle="margin-right:5px;"/></td>
                                </tr> 
                                <tr>
                                    <td class="lable">Serial No</td>
                                    <td class="lable">:</td>
                                    <td><s:textfield name="serial" id="serial" cssClass="text_field" /></td>

                                    <td class="lable">End Date</td>
                                    <td class="lable">:</td>
                                    <td><sj:datepicker id="todate" name="todate" readonly="true"  value="today" changeYear="true" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="text_field" cssStyle="margin-right:5px;"/></td>
                                </tr> 
                                <tr>
                                    <td class="lable">Response Code</td>
                                    <td class="lable">:</td>
                                    <td><s:textfield name="respCode" id="respCode" cssClass="text_field" /></td>
                                </tr> 
                                <tr>
                                    <td colspan="3" align="right">
                                        <s:submit   id="exportXLSbutton" name="exportXLSbutton" value="Export XSL"  cssClass="add"  />
                                        <sj:a 
                                            id="searchbut" 
                                            button="true"                                        
                                            onClickTopics="onclicksearch"  cssClass="search"   role="button" aria-disabled="false" cssStyle="padding-left: 15px; font-weight:normal !important;margin-top: 0px;"                                   
                                            >Search</sj:a>
                                        </td>
                                    </tr>
                                </table>
                        </s:form>

                    </div>
                    <div class="table">

                        <s:url var="listurl" action="ListtransFail"/>
                        <sjg:grid
                            id="gridtable"
                            caption="Registered Terminals"
                            dataType="json"
                            href="%{listurl}"
                            pager="true"
                            gridModel="gridModel"
                            rowList="10,15,20"
                            rowNum="10"
                            shrinkToFit = "false"
                            autowidth="true"
                            rownumbers="true"
                            onCompleteTopics="completetopics"
                            rowTotal="false"
                            viewrecords="true"

                            >

                            <sjg:gridColumn name="tid"       index="tid"            title="TID"              align="left" width="100"   sortable="true" frozen="true"/>                            
                            <sjg:gridColumn name="serialno"  index="seriano"        title="Serial No"        align="left" width="100"   sortable="false" frozen="true"/>
                            <sjg:gridColumn name="datetime"  index="datetime"       title="Date/Time"        align="left" width="140"   sortable="true" frozen="true"/>
                            <sjg:gridColumn name="description"index="description"   title="Description"      align="left" width="220"   sortable="false" frozen="true"/> 
                            <sjg:gridColumn name="tnxtpe"    index="tnxtpe"         title="Transaction Type" align="left" width="100"   sortable="false"/>
                            <sjg:gridColumn name="responsecode"index="responsecode" title="Response Code"    align="left" width="120"   sortable="true"/>
                            <sjg:gridColumn name="encmode"   index="encmode"        title="Encryption Mode"  align="left" width="100"   sortable="false"/>
                            <sjg:gridColumn name="encalgo"   index="encalgo"        title="Encryption Algo"  align="left" width="100"   sortable="false"/>
                            <sjg:gridColumn name="diviceip"  index="diviceip"       title="Divice IP"        align="left" width="100"   sortable="false"/>
                            <sjg:gridColumn name="connectionip"index="connectionip" title="Connection IP"    align="left" width="100"   sortable="false"/>   

                        </sjg:grid> 


                    </div>    
                </div>

            </div>
            <!--End of Body Content-->

            <jsp:include page="../../footer.jsp" />
        </section>
    </body>
</html>
