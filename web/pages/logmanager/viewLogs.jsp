<%-- 
    Document   : viewLogs
    Created on : Sep 21, 2015, 11:17:25 AM
    Author     : nipun_t
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

        <script type="text/javascript" >

            function downloadformatter(cellvalue, options, rowObject) {

                return "<a href='${pageContext.request.contextPath}/DownloadlogView?filePath=" + cellvalue + "' disabled='#vdownload' title='Download'><i class='fa fa-download' aria-hidden='true'></i></a>";
            }
            function deleteformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='deleteLogInit(&#34;" + rowObject.logFileType + "&#34;,&#34;" + rowObject.logFileName + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/delete_icon.png'  /></a>";
            }


            function deleteLogInit(logFileType, logFileName) {
                $("#deleteConfirmDialog").data('logFileName', logFileName).dialog('open');
                $("#deleteConfirmDialog").data('logFileType', logFileType).dialog('open');
                $("#deleteConfirmDialog").html('<p>Please confirm delete : ' + logFileName + '</p>');
                return false;
            }
            function deleteLog(logFileType, logFileName) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeletelogView',
                    data: {logTypes: logFileType, fileName: logFileName},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        if (data.isDeleted == true) {
                            $("#deleteMessageDialog").dialog('open');
                            $("#deleteMessageDialog").html('<br><br><b><font size="3" color="green"><center>' + data.dmessage);
                        } else {
                            $("#deleteMessageDialog").dialog('open');
                            $("#deleteMessageDialog").html('<br><br><b><font size="3" color="red"><center>' + data.dmessage);
                        }
                        resetData();
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });
                function resetData() {
                    $('#divmsg').empty();
                    jQuery("#gridtable").trigger("reloadGrid");
                }

            }
            function viewformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='viewLoglogView(&#34;" + rowObject.tid + "&#34;,&#34;" + rowObject.tid + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/view_icon.png'  /></a>";
            }

            $.subscribe('onclicksearch', function (event, data) {
                var logTypes = $('#logTypes').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {logTypes: logTypes}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            });


            function clearLog(logTypes) {
                clearAllLogInit(logTypes);
            }

            function clearAllLogInit(logTypes) {
                $("#clearAllLogConfirmDialog").data('logTypes', logTypes).dialog('open');
                $("#clearAllLogConfirmDialog").data('logTypes', logTypes).dialog('open');
                $("#clearAllLogConfirmDialog").html('<br><br><b><font size="3" color="red"><center>Please confirm clear all select logs : ' + logTypes);
                return false;
            }
            function clearAllLog(logTypes) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/ClearLoglogView',
                    data: {logTypes: logTypes},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        if (data.isDeleted == true) {
                            $("#clearAllLogMessageDialog").dialog('open');
                            $("#clearAllLogMessageDialog").html('<br><br><b><font size="3" color="green"><center>' + data.dmessage);
                        } else {
                            $("#clearAllLogMessageDialog").dialog('open');
                            $("#clearAllLogMessageDialog").html('<br><br><b><font size="3" color="red"><center>' + data.dmessage);
                        }
                        resetData();

                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });
                function resetData() {
                    $('#divmsg').empty();
                    jQuery("#gridtable").trigger("reloadGrid");
                }
            }

        </script>

    </head>

    <body style="overflow:hidden">
        <s:set id="vdownload" var="vdownload"><s:property  value="download" default="true"/></s:set>
        <s:set id="vview" var="vview"><s:property value="view" default="true"/></s:set>

            <section class="app-content">
            <jsp:include page="../../header.jsp" /> 
            <div class="content innerpage">

<!--                <div class="breadcrumb">
                    <span class="active"> Log File Management </span> 
                </div>-->
                <!-- End -->
                <h1 class="page-title">Log File Management</h1>
                
                <div class="content-section">
                    <div class="content-data">
                        <h2 class="section-title">Log File Management</h2>
                    </div>
                    <sj:dialog 
                        id="deleteConfirmDialog" 
                        buttons="{ 
                        'OK':function() { deleteLog($(this).data('logFileType'),$(this).data('logFileName'));$( this ).dialog( 'close' ); },
                        'Cancel':function() { $( this ).dialog( 'close' );} 
                        }" 
                        autoOpen="false" 
                        modal="true" 
                        title="Delete Log File Confirmation"
                        width="400"
                        height="200"
                        position="center"
                        />
                    <sj:dialog 
                        id="clearAllLogMessageDialog" 
                        buttons="{
                        'OK':function() { $(this).data('dmessage'); $( this ).dialog( 'close' );}
                        }"  
                        autoOpen="false" 
                        modal="true" 
                        title="Delete Log File" 
                        width="400"
                        height="150"
                        position="center"
                        />
                    <sj:dialog 
                        id="clearAllLogConfirmDialog" 
                        buttons="{ 
                        'OK':function() { clearAllLog($(this).data('logTypes'));$( this ).dialog( 'close' ); },
                        'Cancel':function() { $( this ).dialog( 'close' );} 
                        }" 
                        autoOpen="false" 
                        modal="true" 
                        title="Clear All Log File Confirmation"
                        width="400"
                        height="200"
                        position="center"
                        />
                    <sj:dialog 
                        id="#clearAllLogMessageDialog" 
                        buttons="{
                        'OK':function() { $(this).data('dmessage'); $( this ).dialog( 'close' );}
                        }"  
                        autoOpen="false" 
                        modal="true" 
                        title="Clear All Log File" 
                        width="400"
                        height="150"
                        position="center"
                        />

                    <div id="tablediv" class="custom-grid">

                        <s:url var="listurl" action="ListlogView"/>
                        <sjg:grid
                            id="gridtable"
                            caption="Log File Management"
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

                            <sjg:gridColumn name="logFileName" index="logFileName" title="Log File Name" align="left" sortable="false"/>
                            <sjg:gridColumn name="size" index="size" title="Size" align="center" sortable="false"/>   
                            <sjg:gridColumn name="date" index="date" title="Date"  align="center"  sortable="true"/>
                            <sjg:gridColumn name="path" index="path" title="Download"  align="center" width="50"  formatter="downloadformatter" cssClass="action-col" sortable="false"/>
                        </sjg:grid> 

                    </div> 

                </div>

                <!--End of Body Content-->


                <jsp:include page="../../footer.jsp" />
            </div><!--End of Wrapper-->
        </section>
    </body>
</html>
