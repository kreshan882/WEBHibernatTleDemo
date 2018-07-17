<%-- 
    Document   : channelManager
    Created on : Sep 16, 2015, 2:51:13 PM
    Author     : ravideva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"  %>  
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>   
<html>
    <head>

        <jsp:include page="../../Styles.jsp" />
        <style>
            .ui-button-text-only .ui-button-text {
                padding: 0;
            }
            .ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
                font-weight: normal; 
            }
        </style>
        <script>
            function resetForm() {
                $('#listName').val("");
                $('#listPort').val("");
                $('#listConnectType').val("1");
                $('#listTimeOut').val("");
                $('#listKeepLive').val("1");
                $('#listHeaderFormat').val("1");
                $('#listHeadSize').val("1");
                $('#listStatus').val("1");
                $('#listBindStatus').val("1");

                $('#uplistName').val("");
                $('#uplistPort').val("");
                $('#uplistConnectType').val("1");
                $('#uplistTimeOut').val("");
                $('#uplistKeepLive').val("1");
                $('#uplistHeaderFormat').val("1");
                $('#uplistHeadSize').val("1");
                $('#uplistStatus').val("1");
                $('#uplistBindStatus').val("1");
                jQuery("#gridtable").trigger("reloadGrid");
            }
            function resetFormOpr() {
                $("#listOpr").val(0);
                utilityManager.resetMessage();
                $("#oprMessage").hide()
            }
            function resetFormFull() {
                resetForm();
                $('#divmsg').empty();
                utilityManager.resetMessage();
            }

            function changeAction() {
                var isChecked = document.getElementById("isChecked").checked;
                if (isChecked == true) {
                    document.getElementById("upNewPw").disabled = false;
                    document.getElementById("upRepetedNewPw").disabled = false;
                } else {
                    document.getElementById("upNewPw").disabled = true;
                    document.getElementById("upRepetedNewPw").disabled = true;
                }

            }

//            $.subscribe('onclicksearch', function (event, data) {
//                var searchName = $('#searchName').val();
//                $("#gridtable").jqGrid('setGridParam', {postData: {searchName: searchName}});
//                $("#gridtable").jqGrid('setGridParam', {page: 1});
//                jQuery("#gridtable").trigger("reloadGrid");
//            });

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='editNiiMap(&#34;" + rowObject.listId + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/edit_icon.png'  /></a>";
            }
            function deleteformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='deleteNiimapInit(&#34;" + rowObject.listId + "&#34;,&#34;" + rowObject.listName + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/delete_icon.png'  /></a>";
            }

            function deleteNiimapInit(niivalue, name) {
                $("#deleteConfirmDialog").data('listId', niivalue).dialog('open');
                $("#deleteConfirmDialog").html('<br><br><b><font size="3" color="red"><center>Please confirm delete : ' + name);
                return false;
            }

            function removeStyle(data, tableid) {
                $.each(data, function (rowNo, gridData) {
                    $(tableid).find('tr:eq(' + (rowNo + 1) + ')').removeAttr('style');
                    $("#gridtable").find('tr:eq(' + (rowNo + 1) + ')').children().first().next().next().removeAttr('style');
                    $("#gridtable").find('tr:eq(' + (rowNo + 1) + ')').children().first().next().next().next().removeAttr('style');
                })
            }

            $.subscribe('rowselect', function (event, data) {
                removeStyle(jQuery(data).jqGrid('getRowData'), '#gridtable');

                var grid = event.originalEvent.grid;
                var sel_id = grid.jqGrid('getGridParam', 'selrow');

                var printStyle = $("#" + sel_id).children(1).attr("style");
                var style = $('#gridtable').find('tr:eq(' + (sel_id) + ')').children().first().attr("style").split(";");
//                alert(style);
                $.each(jQuery(data).jqGrid('getRowData'), function (rowNo, gridData) {
                    if ($('#gridtable').find('tr:eq(' + (rowNo + 1) + ')').attr("id") == sel_id) {
                        if (rowNo == 0) {
                            if (style.toString().length !== 32) {
                                var css = style[2].split(":");

                                $(".ui-state-highlight").css("background-color", css[1]);
                                $(".ui-state-highlight").css("color", "rgb(0,0,0)");
                            }
                        } else {
                            if (style.toString().length !== 19) {
                                var css = style[1].split(":");

                                if (css.toString().indexOf("rgb(255, 255, 255)") > 0) {
                                    css = style[2].split(":");
                                }

                                $(".ui-state-highlight").css("background-color", css[1])
                                $(".ui-state-highlight").css("color", "rgb(0,0,0)");
                            }
                        }

                    }
                });

            });

            $.subscribe('completetopics', function (event, data) {

                $.each(jQuery(data).jqGrid('getRowData'), function (rowNo, gridData) {
                    $.each(jQuery(data).jqGrid('getRowData'), function (rowNo, gridData) {

                        if ((gridData.colorCode === '1')) {
                            $('#gridtable').find('tr:eq(' + (rowNo + 1) + ')').children().first().css({'background-color': '#E50606', 'color': '#fff'});
                        } else if ((gridData.colorCode === '2')) {
                            $('#gridtable').find('tr:eq(' + (rowNo + 1) + ')').children().first().css({'background-color': 'rgb(125, 218, 153)', 'color': '#fff'});
                        } else if ((gridData.colorCode === '3')) {
                            $('#gridtable').find('tr:eq(' + (rowNo + 1) + ')').children().first().css({'background-color': '#ffa500', 'color': '#fff'});
                        } else if ((gridData.colorCode === '4')) {
                            $('#gridtable').find('tr:eq(' + (rowNo + 1) + ')').children().first().css({'background-color': '#87CEFA', 'color': '#fff'});
                        } else {
                            $('#gridtable').find('tr:eq(' + (rowNo + 1) + ')').css('color', '#FFA500');
                        }
                    });
                });

            });

            function deleteNiimap(niivalue) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeletelistMng',
                    data: {listId: niivalue},
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
            }

            function resetData() {
                $('#divmsg').empty();
                utilityManager.resetMessage();
                jQuery("#gridtable").trigger("reloadGrid");
            }


            function editNiiMap(niiVal) {
                $('#addnewhost').hide();
                $('#editniimap').show();
                $('#divmsg').empty();
                utilityManager.resetMessage();
                $.ajax({
                    url: '${pageContext.request.contextPath}/FindlistMng',
                    data: {uplistId: niiVal},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $('#editniimap').show();
                        $('#addnewhost').hide();
                        $('#uplistName').val(data.uplistName);
                        $('#uplistId').val(data.uplistId);
                        $('#uplistPort').val(data.uplistProt);
                        $('#uplistConnectType').val(data.uplistConnectType);
                        $('#uplistTimeOut').val(data.uplistTimeOut);
                        $('#uplistKeepLive').val(data.uplistKeepLive);
                        $('#uplistHeaderFormat').val(data.uplistHeaderFormat);
                        $('#uplistHeadSize').val(data.uplistHeadSize);
                        $('#uplistStatus').val(data.uplistStatus);
                        $('#uplistBindStatus').val(data.uplistBindStatus);
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });
            }

            function resetUpdateForm() {
                $('#divmsg').empty();
                utilityManager.resetMessage();
                var upgroupName = $('#upgroupName').val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/getMessageoprMng',
                    data: {upgroupName: upgroupName},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $('#upgroupName').val(data.upgroupName).attr('readOnly', true);
                        $('#uphostName').val(data.uphostName);
                        $('#upip').val(data.upip);
                        $('#upport').val(data.upport);
                        $('#uptimeout').val(data.uptimeout);
                        $('#upstatus').val(data.upstatus);
                        $('#upconType').val(data.upconType);
                        $('#upforwerdMathod').val(data.upforwerdMathod);
                        $('#uptestPackStatus').val(data.uptestPackStatus);
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }

                });
                jQuery("#gridtable").trigger("reloadGrid");
            }

            function backToMain() {
                $('#editniimap').hide();
                $('#addnewhost').show();
                $('#divmsg').empty();
                utilityManager.resetMessage();
                jQuery("#gridtable").trigger("reloadGrid");
            }
            function Statusformatter(cellvalue) {
                if (cellvalue == 1) {
                    return "<i class='fa fa-circle active' aria-hidden='true'></i>";
                } else {
                    return "<i class='fa fa-circle' aria-hidden='true'></i>";
                }
            }
            $(document).ready(function () {
                $("#oprMessage").hide();
                $("#listOpr").on('change', function () {
                    if ($(this).val() > 0) {

                        $.ajax({
                            url: '${pageContext.request.contextPath}/getMessageoprMng',
                            data: {listOpr: $(this).val()},
                            dataType: "json",
                            type: "POST",
                            success: function (data) {
                                if (data.colorCode === '1') {
                                    $('#oprMessage').css({'color': '#E50606'}).html(data.oprMessage);
                                } else if (data.colorCode === '2') {
                                    $('#oprMessage').css({'color': 'rgb(125, 218, 153)'}).html(data.oprMessage);
                                } else if (data.colorCode === '3') {
                                    $('#oprMessage').css({'color': '#ffa500'}).html(data.oprMessage);
                                } else if (data.colorCode === '4') {
                                    $('#oprMessage').css({'color': '#87CEFA'}).html(data.oprMessage);
                                }

                            },
                            error:function(xhr, textStatus, errorThrown){
                                if(xhr.responseText.includes("csrfError.jsp")){
                                    window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                                 }
                             }
                        })
                        $("#oprMessage").show();
                    } else {
                        $("#oprMessage").hide()
                    }
                })
            })
            function downloadOperaiton() {

                form = document.getElementById('systemHistoryForm');
                form.action = 'DownloadoprMng';
                form.submit();
            }


        </script>
        <script>
            $.subscribe('onclicksearch', function (event, data) {

                var fromdate = $('#fromdate').val();
                var todate = $('#todate').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {fromdate: fromdate, todate: todate, search: true}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            });
            $.subscribe('onrefresh', function (event, data) {
                $("#gridtable").jqGrid('setGridParam', {postData: {search: false}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
                $("#fromdate").datepicker().datepicker("setDate", new Date());
                $("#todate").datepicker().datepicker("setDate", new Date());
            });
        </script>

    </head>
    <s:set id="update" var="update"><s:property  value="update" default="true"/></s:set>
    <%--<s:set id="send" var="send"><s:property  value="send" default="true"/></s:set>--%>
    <s:set var="vupdate"><s:property value="update" default="true"/></s:set>
    <s:set var="vdelete"><s:property value="delete" default="true"/></s:set>
    <s:set var="vview"><s:property value="view" default="true"/></s:set>


        <body>
            <section class="app-content">
            <jsp:include page="../../header.jsp" /> 

            <!-- Page content begin -->
            <div class="content innerpage">
                <!-- Breadcrumb begin -->
                <div class="breadcrumb">
                    <s:property value="Module"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i><span id="task" class="active"><s:property value="Section"/></span>
                </div>
                <!-- End -->

                <h1 class="page-title"><s:property value="Section"/><a href="#" class="lnk-back hide-element do-nothing"><i class="fa fa-arrow-left" aria-hidden="true"></i> back</a></h1>

                <div class="content-section " id="searchdiv">

                    <s:form theme="simple" id="systemHistoryForm"  action="XSLcreatsysHist" name="systemHistoryForm" method="post" >
                        <div class="content-data">
                            <h2 class="section-title">Search</h2>
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
                            <div class="d-row cpanel four-col">
                                <label class="col-1">&nbsp;</label>
                                <div class="right-col">                                     
                                    <sj:a 
                                        id="searchbut" 
                                        button="true"                                        
                                        onClickTopics="onclicksearch"  cssClass="btn default-button"   
                                        role="button" aria-disabled="false"                                   
                                        ><i class="fa fa-search" aria-hidden="true"></i> Search</sj:a>
                                    <sj:a 
                                        id="refresh" 
                                        button="true"                                        
                                        onClickTopics="onrefresh"  
                                        cssClass="btn default-button"   
                                        role="button" aria-disabled="false"                                    
                                        ><i class="fa fa-refresh" aria-hidden="true"></i> Refresh</sj:a>
                                        <div class="btn-wrap lnk-match">
                                            <i class="fa fa-reply-all" aria-hidden="true"></i>
                                        <sj:submit   button="true" id="exportPdfbutton" name="exportPdfbutton"  disabled="#update"  value="Export PDF"   onclick="downloadOperaiton()" cssClass="btn default-button"/>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </s:form>
                </div>


                <!-- Data form begin -->
                <div class="content-section data-form" id="addDiv" >
                      <%--<s:url var="addurl" action="AddoprMng"/>--%>
                    <s:form id="addnewhost" theme="simple" method="post" >
                        <div class="content-data">
                            <h2 class="section-title">Operation</h2>
                            <div class="msg-panel add-form-msg" > 
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Operation</label>
                                <div class="col-2 form-field">
                                    <s:select  name="listOpr" id="listOpr" list="%{operationList}" headerKey="0" headerValue="---Select---" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                                <div id="oprMessage" class="col-4 form-label" style="width: 50%;"></div>
                            </div>
                            <div class="d-row cpanel four-col">
                                <label class="col-1">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="addurl" action="AddoprMng"/>
                                    <h1>${addurl}</h1>
                                    <div class="btn-wrap"><i class="fa fa-paper-plane" aria-hidden="true"></i><sj:submit href="%{addurl}"  value="Send"  button="true"   cssClass="btn default-button"   cssStyle="height:25px"/></div>
                                    <div class="btn-wrap"><i class="fa fa-times" aria-hidden="true"></i><sj:submit  button="true" id="resetForm" onclick="resetFormOpr()"  value="Reset" cssClass="btn reset-button"/></div>

                                </div>
                            </div>
                        </div>
                    </s:form>  
                </div>
                <!-- End -->
                <!-- Grid data begin -->

                <div class="content-section">
                    <div class="content-data">
                        <h2 class="section-title">Operation Alerts</h2>
                        <ul class="legends-grid">
                            <li><span class="item-yellow"></span>Session Restart</li>
                            <li><span class="item-green"></span>Service Start</li>
                            <li><span class="item-red"></span>Service Stop</li>
                            <li><span class="item-red"></span>Server Reboot</li>
                            <li><span class="item-blue"></span>Log Backup</li>
                            <li><span class="item-red"></span>Service Restart</li>
                        </ul>
                    </div>

                    <sj:dialog 
                        id="deleteConfirmDialog" 
                        buttons="{ 
                        'OK':function() { deleteNiimap($(this).data('listId'));$( this ).dialog( 'close' ); },
                        'Cancel':function() { $( this ).dialog( 'close' );} 
                        }" 
                        autoOpen="false" 
                        modal="true" 
                        title="Delete nii Confirmation"
                        width="400"
                        height="200"
                        position="center"
                        />
                    <sj:dialog 
                        id="deleteMessageDialog" 
                        buttons="{
                        'OK':function() { $(this).data('dmessage'); $( this ).dialog( 'close' );}
                        }"  
                        autoOpen="false" 
                        modal="true" 
                        title="Delete nii" 
                        width="400"
                        height="150"
                        position="center"
                        />

<!--                    <div id="tablediv" class="custom-grid">

                        <%--<s:url var="listurl" action="ListoprMng"/>--%>
                        <%--<sjg:grid--%>
                            id="gridtable"
                            caption="Operation Alerts"
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
                            onSelectRowTopics="rowselect"
                            >

                            <%--<sjg:gridColumn name="colorCode" index="colorCode" title="id"   hidden="true" />--%>  
                            <%--<sjg:gridColumn title="Operaiton" name="operaiton" index="operaiton"  align="left" width="20" sortable="true"/>--%>  
                            <%--<sjg:gridColumn name="username" index="username" title="Username" align="left" width="25" sortable="true"  />--%> 
                            <%--<sjg:gridColumn name="ip" index="ip" title="IP" align="left" width="20" sortable="true"/>--%> 
                            <%--<sjg:gridColumn name="message" index="message" title="Message" align="left" width="20" sortable="true"/>--%> 
                            <%--<sjg:gridColumn name="statusStr" index="epicTleStatus.code" title="Status" align="center" width="20" sortable="true" />--%> 
                            <%--<sjg:gridColumn name="dateTime" index="datetime" title="Date/Time" align="left" width="20" sortable="true"/>--%> 
                        <%--</sjg:grid>--%> 
                    </div>-->

                </div>
                <!-- End -->

                <!--End of Body Content-->
            </div>
            <jsp:include page="../../footer.jsp" />

        </section>

        <script type="text/javascript">
            $(document).ready(function () {
                //Back button event
                $('.lnk-back').on('click', function () {
                    $('#webuserEditForm').hide();
                    $('#searchdiv').show();
                    utilityManager.resetMessage();
                    $('#addDiv').hide();
                    $('#task').empty();
                    jQuery("#gridtable").trigger("reloadGrid");
                    $('.lnk-back').addClass('hide-element');
                    var text = ' Search User';
                    $('#task').append(text);
                    return false;
                });

                $(document).ready(function () {

                    setTimeout(function () {
                        $(window).trigger('resize');
                    }, 500);

                });

            });


            $(document).ready(function () {
                $.get('${pageContext.request.contextPath}/chartDatamonitorCall', function (callback) {
                    if (callback.serviceStatus === "true") {
                        $("#listOpr option[value='2']").remove();
                    } else {
                        $("#listOpr option[value='3']").remove();
                    }
                });
            });


        </script>
        <!-- End -->
    </body>

</html>
