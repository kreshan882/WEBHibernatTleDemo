<%-- 
    Document   : systemAlerts
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
                
                var fromdate = $('#fromdate').val();
                var todate = $('#todate').val();
                var tid = $('#tid').val();
                var sid = $('#sid').val();
                var selectNode = $('#selectNode').val();
                
                $("#gridtable").jqGrid('setGridParam', {postData: {tid: tid, sid: sid, selectNode: selectNode, fromdate: fromdate, todate: todate, search: true}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
                
            });

            function Riskformatter(cellvalue) {
                if (cellvalue == 1) {
                    return "<i class='fa fa-circle concern' aria-hidden='true'></i>";
                } else if (cellvalue == 2) {
                    return "<i class='fa fa-circle warning' aria-hidden='true'></i>";
                } else {
                    return "<i class='fa fa-circle critical' aria-hidden='true'></i>";
                }
            }

            function Statusformatter(cellvalue) {
                if (cellvalue == 1) {
                    return "<i class='fa fa-circle active' aria-hidden='true'></i>";
                } else {
                    return "<i class='fa fa-circle' aria-hidden='true'></i>";
                }
            }
            
            function resetSearchForm() {
                $today = new Date();
                $dd = $today.getDate();
                $mm = $today.getMonth()+1;

                $yyyy = $today.getFullYear();
                if($dd<10){
                    $dd='0'+$dd;
                } 
                if($mm<10){
                    $mm='0'+$mm;
                } 
                $today =$yyyy+'-'+$mm+'-'+$dd;
                $('#fromdate').val($today);
                $('#todate').val($today);
                $('#tid').val("");
                $('#sid').val("");
                $('#selectNode').val("-1");
                $('#searchbut').click();
            }

        </script>

    </head>
    <s:set id="vdownload" var="vdownload"><s:property  value="vdownload" default="true"/></s:set>

        <body>
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


                <div class="content-section data-form" id="addnewhost">
                    <s:form id="systemHistoryForm"  action="XSLcreatsysAlert" name="systemHistoryForm" theme="simple" method="post">
                        <div class="content-data">
                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg">
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i> <span id="divmsg"></span></div>
                            </div>
                            <!-- End -->

                            <!-- Two colum form row begin -->
                            <div class="d-row">
                                <label class="col-1 form-label">Start Date</label>
                                <div class="col-2 form-field">
                                    <sj:datepicker id="fromdate" name="fromdate" readonly="true"  value="today" changeYear="true" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="txt-input width-35" maxDate="today"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">End Date</label>
                                <div class="col-2 form-field">
                                    <sj:datepicker id="todate" name="todate" readonly="true"  value="today" changeYear="true" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="txt-input width-35" maxDate="today"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Alert Type</label>
                                <div class="col-2 form-field">
                                    <s:select  name="tid" id="tid" list="%{AlertMap}" headerKey=""  headerValue="---Select---" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Risk Level</label>
                                <div class="col-2 form-field">
                                    <s:select  name="sid" id="sid" list="%{RiskMap}" headerKey="" headerValue="---Select---"
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Server Node</label>
                                <div class="col-2 form-field">
                                    <s:select name="selectNode" id="selectNode" list="%{nodes}" headerKey="-1" headerValue="---Select---" 
                                              listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <!-- End -->

                            <div class="d-row cpanel four-col">
                                <label class="col-1">&nbsp;</label>
                                <div class="right-col">

                                    <sj:a 
                                        id="searchbut" 
                                        button="true"                                        
                                        onClickTopics="onclicksearch"  cssClass="btn default-button"   role="button" aria-disabled="false"                                    
                                        ><i class="fa fa-search" aria-hidden="true"></i> Search</sj:a>
                                    <div class="btn-wrap lnk-match"><i class="fa fa-file-excel-o" aria-hidden="true"></i><s:submit id="exportXLSbutton" name="exportXLSbutton" value="Export" disabled="#vdownload" cssClass="btn default-button"  /></div>
                                    <sj:a button="true" onclick="resetSearchForm()" cssClass="btn reset-button"><i class="fa fa-times" aria-hidden="true"></i> Reset</sj:a>
                                </div>
                            </div>
                        </s:form>
                    </div>
                </div>


                <div class="content-section">
                    <div class="content-data">
                        <h2 class="section-title">All System Notifications</h2>
                    </div>

                    <div id="tablediv" class="custom-grid">

                        <s:url var="listurl" action="ListsysAlert"/>
                        <sjg:grid
                            id="gridtable"
                            caption="System Notifications"
                            dataType="json"
                            href="%{listurl}"
                            pager="true"
                            gridModel="gridModel"
                            rowList="10,15,20"
                            rowNum="10"
                            shrinkToFit="false"
                            autowidth="true"
                            rownumbers="true"
                            onCompleteTopics="completetopics"
                            rowTotal="false"
                            viewrecords="true"
                            >

                            <sjg:gridColumn name="risklevl" index="epicTleRiskLevel.code" title="Risk Level" align="center"  formatter="Riskformatter" sortable="true"/>  
                            <sjg:gridColumn name="node" index="epicTleNodetype.code" title="Server Node" align="left" width="100" sortable="true"/>  
                            <sjg:gridColumn name="alertType" index="epicTleAlertType.description" title="Alert Type" align="left"  sortable="true"/>  
                            <sjg:gridColumn name="alerts" index="alertinformation" title="Alerts" align="LEFT"  sortable="true"/>  
                            <sjg:gridColumn name="mti" index="mti" title="MTI" align="LEFT" width="100" sortable="true"/>  
                            <sjg:gridColumn name="cardBin" index="cardBin" title="Card Bin" align="LEFT"  sortable="true"/>  
                            <sjg:gridColumn name="connectionip" index="clientIp" title="Client IP" align="LEFT"  sortable="true"/>  
                            <sjg:gridColumn name="respCode" index="responseCode" title="Response Code" align="LEFT"  sortable="true"/>  
                            <sjg:gridColumn name="tleStatus" index="tleStatus" title="TLE Status" align="center"  formatter="Statusformatter" sortable="true"/>  
                            <sjg:gridColumn name="datetime" index="datetime" title="Date/Time" align="left"  sortable="true"/>         

                        </sjg:grid> 
                    </div>
                </div>
            </div>
            <!--End of Body Content-->

            <jsp:include page="../../footer.jsp" />
        </section>
        <script>
            $(document).ready(function () {

                setTimeout(function () {
                    $(window).trigger('resize');
                }, 500);

            });

        </script>
    </body>
</html>
