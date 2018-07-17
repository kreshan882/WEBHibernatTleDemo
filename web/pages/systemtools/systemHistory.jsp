<%-- 
    Document   : systemHistory
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
                var usr = $('#usname').val();
                var serial = $('#sid').val();
                var selectNode =$('#selectNode').val();
                var searchUserName =$('#searchUserName').val();
                var selectMod =$('#selectMod').val();
                var searchTask =$('#searchTask').val();                
                var operationType = $('#operationType').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {operationType: operationType, usrName: usr, serial: serial, fromdate: fromdate, todate: todate,selectNode:selectNode, search: true, selectMod: selectMod, searchUserName : searchUserName, searchTask: searchTask }});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            });
            
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
                $('#selectMod').val("-1");
                $('#searchTask').val("-1");
                $('#searchUserName').val(""); 
                $('#fromdate').val($today);
                $('#todate').val($today);
                $('#searchbut').click();
            }
            function NIIAssignformatter(cellvalue, options, rowObject) {
                if(rowObject.operation==="Update" || rowObject.operation==="Delete"){
                    if(rowObject.section==="Change Password"){
                        return "";
                    }
                    else{
                        return "<a href='#' title='View Details' onClick='javascript:viewNII(&#34;" + cellvalue + "&#34;,&#34;" + rowObject.sid + "&#34;)'><i class='fa fa-share-square-o' aria-hidden='true'></i></a>";
                    }
                }
                else{
                    return "";
                }
            }
            function viewNII(id, Sid) {
                $("#viewdialog").data('Id', id);
                $("#viewdialog").data('History Id', Sid).dialog('open');                
            }
            
            $.subscribe('openview', function (event, data) {
                utilityManager.resetMessage();
                $('.errorpanel').hide();
//                resetData();
                var $led = $("#viewdialog");
                var $token=$( "input[name='RequstToken']" ).val();
                $led.load("ViewDetsysHist?RequstToken="+$token+"&sidx=" + $led.data('Id'));
//                alert($led.data('Id').replace(/ /g,"_"));
                //$led.load("AssignNIIchanMng?RequstToken="+token+"&ChannelId=" + $led.data('Id') + "&cchannel=" + $led.data('ChannelName').replace(/ /g, "_"));
            });
            $.subscribe('closeview', function (event, data) {
                utilityManager.resetMessage();
                $('.errorpanel').hide();
            });
        </script>
    </head>

    <body>
        <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
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
                    <s:form id="systemHistoryForm"  action="XSLcreatsysHist" name="systemHistoryForm" theme="simple" method="post">
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
                                    <sj:datepicker id="fromdate" name="fromdate" readonly="true" value="today" changeYear="true" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="txt-input width-35" maxDate="today"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">End Date</label>
                                <div class="col-2 form-field">
                                    <sj:datepicker id="todate" name="todate" readonly="true" value="today"   changeYear="true" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="txt-input width-35" maxDate="today"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">User</label>
                                <div class="col-2 form-field">
                                    <s:textfield id="searchUserName" name="searchUserName" cssClass="txt-input width-35" maxLength="20"/>
                                </div>
                            </div>
                                
                            <div class="d-row">
                                <label class="col-1 form-label">Module</label>
                                <div class="col-2 form-field">
                                    <s:select name="selectMod" id="selectMod" list="%{moduleMap}" headerKey="-1" headerValue="---Select---" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                                
                            <div class="d-row">
                                <label class="col-1 form-label">Task</label>
                                <div class="col-2 form-field">
                                    <s:select name="searchTask" id="searchTask" list="%{taskMap}" headerKey="-1" headerValue="---Select---" 
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
                <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                <div class="content-section">
                    <div class="content-data">
                        <h2 class="section-title">All Audit Records</h2>
                    </div>

                    <div id="tablediv" class="custom-grid">
                        <sj:dialog 
                                id="viewdialog" 
                                buttons="{
                                'OK':function() { utilityManager.resetMessage();$( this ).dialog('close');}                                    
                                }" 
                                autoOpen="false" 
                                modal="true"                            
                                width="1000"
                                height="500"
                                position="center"
                                title="View Details"
                                onOpenTopics="openview"
                                onCloseTopics="closeview"
                                loadingText="Loading .."
                        />
                        <s:url var="listurl" action="ListsysHist"/>
                        <sjg:grid
                            id="gridtable"
                            caption="Audit Records"
                            dataType="json"
                            href="%{listurl}"
                            pager="true"
                            gridModel="gridModel"
                            rowList="10,15,20"
                            rowNum="10"
                            autowidth="true"
                            rownumbers="true"
                            onCompleteTopics="completetopics"
                            rowTotal="false"
                            viewrecords="true"

                            >
                            <sjg:gridColumn name="sid" index="sid" title="ID" hidden="true" />
                            <sjg:gridColumn name="webUser" index="epicTleUser.username" title="Web User"  align="left" width="20" sortable="true"/>
                            <sjg:gridColumn name="node" index="epicTleNodetype.code" title="Server Node"  align="left" width="15" sortable="true"/>
                            <sjg:gridColumn name="location" index="location" title="Location"  align="left" width="20" sortable="true"/>
                            <sjg:gridColumn name="module" index="module" title="Module" align="left" width="20" sortable="true"/>                    
                             <sjg:gridColumn name="section" index="section" title="Section" align="left" width="20" sortable="true"/>                    
                            <sjg:gridColumn name="operation" index="operation" title="Task"  align="left" width="20" sortable="false"/>
                            <sjg:gridColumn name="comment" index="comment" title="Comment"  align="left" width="20" sortable="false"/>
                            <sjg:gridColumn name="dateTime" index="datetime" title="Date/Time" align="left" width="20" sortable="true"/>                                               
                            <sjg:gridColumn name="sid" title="Veiw Details" align="Center" width="20" formatter="NIIAssignformatter" frozen="true" sortable="false" cssClass="action-col" />
                        </sjg:grid> 
                          

                    </div>

                </div>

            </div>

        </div>
        <!--End of Body Content-->

        <jsp:include page="../../footer.jsp" />
    </div><!--End of Wrapper-->
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
