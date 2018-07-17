<%-- 
    Document   : systemProcess
    Created on : Sep 24, 2015, 10:00:31 AM
    Author     : thilina_t
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
            .width-10{
                width: 15% !important;
            }
        </style>
        <script>
            $.subscribe('onclicksearch', function (event, data) {
                var fromdate = $('#fromdate').val();
                var todate = $('#todate').val();
                var from = $('#from').val();
                var to = $('#to').val();
                var tid = $('#TransTID').val();
                var selectNode = $('#selectNode').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {from: from, to: to, fromdate: fromdate, todate: todate, TransTID: tid, selectNode: selectNode, search: true}});
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

            function changeHours(event) {

                $('#to').find('option').remove();

                for (var i = parseInt(event.value) + 1; i <= 25; i++) {
                    $('#to')
                            .append('<option value="' + i + '">' + i + '</option>')
                            .val(parseInt(event.value) + 1)
                }
                $('#to').append('<option value=26>26+</option>')
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
                $('#selectNode').val("-1");
                $('#fromdate').val($today);
                $('#todate').val($today);
                $('#TransTID').val("");
                $('#from').val("");
                $('#to').val("");
                $('#searchbut').click();
            }

        </script>

    </head>

    <body>

        <s:set id="vdownload" var="vdownload"><s:property  value="vdownload" default="true"/></s:set>
        <s:set id="vview" var="vview"><s:property value="view" default="true"/></s:set>

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
                    <s:form id="TransactionProcessForm"  action="XSLcreattarPro" name="TransactionProcessForm" theme="simple" method="post">
                        <div class="content-data">

                            <div class="d-row">
                                <label class="col-1 form-label">Start Date</label>
                                <div class="col-2 form-field">
                                    <sj:datepicker id="fromdate" name="fromdate" readonly="true"  value="today" changeYear="true" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="txt-input width-15" maxDate="today"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">End Date</label>
                                <div class="col-2 form-field">
                                    <sj:datepicker id="todate" name="todate" readonly="true"  value="today" changeYear="true" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="txt-input width-15" maxDate="today"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">TID</label>
                                <div class="col-2 form-field">
                                    <s:textfield id="TransTID" name="TransTID" cssClass="txt-input width-15" maxLength="30"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Server Node</label>
                                <div class="col-2 form-field">
                                    <s:select name="selectNode" id="selectNode" list="%{nodes}" headerKey="-1" headerValue="---Select---" 
                                              listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Total Time Range(ms)</label>
                                <div class="col-2 form-field">
                                    <label class="inline-fields">From </label> <s:textfield placeholder="01" id="from" name="from" cssClass="txt-input width-10 text-right" maxLength="5" /> <label class="inline-fields"> &nbsp; &nbsp;</label>
                                    <label class="inline-fields">To </label> <s:textfield placeholder="01" id="to" name="to" cssClass="txt-input width-10 text-right" maxLength="5" />
                                </div>
                            </div>
                               
                            <div class="d-row cpanel four-col">
                                <label class="col-1">&nbsp;</label>
                                <div class="right-col">
                                    <sj:a 
                                        id="searchbut" 
                                        button="true"                                        
                                        onClickTopics="onclicksearch"  cssClass="btn default-button"   role="button" aria-disabled="false"                                  
                                        ><i class="fa fa-search" aria-hidden="true"></i> Search</sj:a>
                                    <div class="btn-wrap lnk-match"><i class="fa fa-file-excel-o" aria-hidden="true"></i><s:submit id="exportXLSbutton" name="exportXLSbutton" value="Export" disabled="#vdownload" cssClass="btn default-button" /></div>
                                    <sj:a button="true" onclick="resetSearchForm()" cssClass="btn reset-button"><i class="fa fa-times" aria-hidden="true"></i> Reset</sj:a>
                                </div>
                            </div>
                        </s:form>
                    </div>
                </div>

                <div class="content-section">
                    <div class="content-data">
                        <h2 class="section-title">All System Transaction</h2>
                    </div>

                    <div id="tablediv" class="custom-grid">

                        <s:url var="listurl" action="ListtarPro"/>
                        <sjg:grid
                            id="gridtable"
                            caption="System Transaction"
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
                            <sjg:gridColumn name="responseCode" index="responseCode" title="Response" align="LEFT" width="200" sortable="true"/>
                            <sjg:gridColumn name="node" index="epicTleNodetype.code" title="Server Node" align="LEFT" width="100" sortable="true"/>
                            <sjg:gridColumn name="tid" index="tid" title="TID" align="left" width="100" sortable="true"/>  
                            <sjg:gridColumn name="tleTime" index="tleTime" title="TLE Time(ms)" align="CENTER" width="100" sortable="true"/>  
                            <sjg:gridColumn name="hostTime" index="hostTime" title="Host Time(ms)" align="CENTER" width="100" sortable="true"/>  
                            <sjg:gridColumn name="totalTime" index="totalTime" title="Total Time(ms)" align="CENTER" width="100" sortable="true"/>  
                            <sjg:gridColumn name="epicTleTxntypes" index="epicTleTxntypes" title="TXN Type" align="LEFT" width="100" sortable="true"/>  
                            <sjg:gridColumn name="traceNo" index="traceNo" title="Trace No." align="LEFT" width="100" sortable="true"/>  
                            <sjg:gridColumn name="bin" index="bin" title="BIN" align="LEFT" width="100" sortable="true"/>  
                            <sjg:gridColumn name="epicTleStatus" index="epicTleStatus" title="TLE Status" align="center" width="80" formatter="Statusformatter" sortable="true"/>  
                            <sjg:gridColumn name="datetime" index="datetime" title="Date/Time" align="left" width="150" sortable="true"/>         

                        </sjg:grid> 


                    </div> 

                </div>


            </div>
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
