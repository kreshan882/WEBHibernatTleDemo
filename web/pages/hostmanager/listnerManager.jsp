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
                $('#isofile').val("1");
                $('#tpduStatus').val("1");

//                $('#uplistName').val("");
//                $('#uplistPort').val("");
//                $('#uplistConnectType').val("1");
//                $('#uplistTimeOut').val("");
//                $('#uplistKeepLive').val("1");
//                $('#uplistHeaderFormat').val("1");
//                $('#uplistHeadSize').val("1");
//                $('#uplistStatus').val("1");
                jQuery("#gridtable").trigger("reloadGrid");
                utilityManager.resetMessage();
                $('.del-channel-msg').hide();

            }
            function resetFormFull() {
                resetForm();
//                utilityManager.resetMessage();
            }

            function Statusformatter(cellvalue) {
                if (cellvalue == 1) {
                    return "<i class='fa fa-circle active' aria-hidden='true'></i>";
                } else {
                    return "<i class='fa fa-circle' aria-hidden='true'></i>";
                }
            }
            function StatusFormatterBind(cellvalue, options, rowObject) {
//                if (cellvalue == 1) {
//                    return "<i class='fa fa-circle active' aria-hidden='true'></i>";
//                } else {
//                    return "<i class='fa fa-circle' aria-hidden='true'></i>";
//                }
                if (rowObject.listStatus == 1) {
                    if (cellvalue == 1) {
                        return "<i class='fa fa-circle active' aria-hidden='true'></i>";
                    } else if (cellvalue == 2) {
                        return "<i class='fa fa-circle critical' aria-hidden='true'></i>";
                    } else {
                        return "<i class='fa fa-circle' aria-hidden='true'></i>";
                    }
                } else {
                    return "<i class='fa fa-circle' aria-hidden='true'></i>";
                }
            }

            function changeAction() {
                var isChecked = document.getElementById("isChecked").checked;
                if (isChecked == true) {
                    document.getElementById("upNewPw").disabled = false;
                    document.getElementById("upRepetedNewPw").disabled = false;
                }
                else {
                    document.getElementById("upNewPw").disabled = true;
                    document.getElementById("upRepetedNewPw").disabled = true;
                }

            }

            $.subscribe('onclicksearch', function (event, data) {
                var searchName = $('#searchName').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {searchName: searchName}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            });

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' disabled='#vupdate' title='Edit Listner' onClick='editNiiMap(&#34;" + rowObject.listId + "&#34;)'><i class='fa fa-pencil' aria-hidden='true'></i></a> <a href='#' disabled='#vdelete' title='Delete Listner' onClick='deleteNiimapInit(&#34;" + rowObject.listId + "&#34;,&#34;" + rowObject.listName + "&#34;)'><i class='fa fa-trash-o' aria-hidden='true'></i></a>";
            }

            function deleteNiimapInit(niivalue, name) {
                $("#deleteConfirmDialog").data('listId', niivalue).dialog('open');
                $("#deleteConfirmDialog").html('<p>Please confirm delete : ' + name + "</p>");
                return false;
            }


            function deleteNiimap(niivalue) {
                var token=$( "input[name='RequstToken']" ).val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeletelistMng',
                    data: {listId: niivalue, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $( "input[name='RequstToken']" ).val($stoken);
                        $('.errorpanel').show();
                        if (data.isDeleted == true) {
                            utilityManager.showMessage('.del-channel-msg', data.dmessage, 'successmsg', $stoken);
                        } else {
                            utilityManager.showMessage('.del-channel-msg', data.dmessage, 'errormsg', $stoken);
                        }
                        jQuery("#gridtable").trigger("reloadGrid");
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });
            }

            function resetData() {
                utilityManager.resetMessage();
                $('.errorpanel').hide();
                jQuery("#gridtable").trigger("reloadGrid");
            }


            function editNiiMap(niiVal) {
                $('#addnewhost').hide();
                $('#editniimap').show();
                $('.lnk-back').removeClass('hide-element');
                $('#task').empty();
                var text = 'Edit Listener';
                $('#task').append(text);

                utilityManager.resetMessage();
                $('.errorpanel').hide();
                var token=$( "input[name='RequstToken']" ).val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/FindlistMng',
                    data: {uplistId: niiVal, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $( "input[name='RequstToken']" ).val($stoken);
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
                        $('#uptpduStatus').val(data.uptpduStatus);

                        $('#hidUplistPort').val(data.uplistProt);
                        $('#hidUplistName').val(data.uplistName);

                        $("#upisofile option:contains(" + data.upisofile + ")").prop("selected", true);
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });
            }

            function resetUpdateForm() {
                var token=$( "input[name='RequstToken']" ).val();
                utilityManager.resetMessage();
                $('.errorpanel').hide();
                var upgroupName = $('#uplistId').val();

                $.ajax({
                    url: '${pageContext.request.contextPath}/FindlistMng',
                    data: {uplistId: upgroupName, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $( "input[name='RequstToken']" ).val($stoken);
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
                        $('#uptpduStatus').val(data.uptpduStatus);
                        $("#upisofile option:contains(" + data.upisofile + ")").prop("selected", true);

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
                utilityManager.resetMessage();
                $('.errorpanel').hide();
                $('#task').empty();
                var text = 'Add Listener';
                $('#task').append(text);

                jQuery("#gridtable").trigger("reloadGrid");

            }

        </script>

    </head>

    <s:set id="vadd" var="vadd"><s:property  value="vadd" default="true"/></s:set>
    <s:set var="vupdate"><s:property value="vupdate" default="true"/></s:set>
    <s:set var="vdelete"><s:property value="vdelete" default="true"/></s:set>
    <s:set var="vview"><s:property value="view" default="true"/></s:set>

        <body >
            <section class="app-content">
            <jsp:include page="../../header.jsp" /> 

            <div class="content innerpage">
                <!-- Breadcrumb begin -->
                <div class="breadcrumb">
                    <s:property value="Module"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i> <s:property value="Section"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i><span id="task" class="active">Add Listener</span>
                </div>
                <!-- End -->

                <!-- Page title begin -->
                <h1 class="page-title"><s:property value="Section"/><a href="#" class="lnk-back hide-element do-nothing"><i class="fa fa-arrow-left" aria-hidden="true"></i> back</a></h1>
                <!-- End -->

                <div class="content-section data-form" id="addnewhost">
                    <s:form theme="simple" method="post" id="newListner" name="newListner">
                        <div class="content-data">

                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg">
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i> <span id="divmsg"></span></div>
                            </div>
                            <!-- End -->

                            <!-- Four colum form row begin -->
                            <div class="d-row">
                                <label class="col-1 form-label">Listener Name<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="listName" name="listName" cssClass="txt-input width-35" maxLength="20"/>
                                </div>
                                <label class="col-3 form-label">Port<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="listPort" name="listPort" cssClass="txt-input width-35" maxLength="5"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Timeout(ms)<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="listTimeOut" name="listTimeOut" cssClass="txt-input width-35" maxlength="10"/>
                                </div>
                                <label class="col-3 form-label">Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="listStatus" id="listStatus" list="%{liststatusMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Connection Type<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="listConnectType" id="listConnectType" list="%{listconTypeMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">Keep Alive Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="listKeepLive" id="listKeepLive" list="%{listKeepLiveMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Header Format<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="listHeaderFormat" id="listHeaderFormat" list="%{listHeaderFormatMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">Header Size<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="listHeadSize" id="listHeadSize" list="%{listHeaderSizeMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">ISO File<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="isofile" id="isofile" list="%{ISOFormat}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">TPDU Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="tpduStatus" id="tpduStatus" list="%{liststatusMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <!-- End -->

                            <div class="d-row cpanel four-col">
                                <label class="col-1">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="addnewhosturl" action="AddlistMng"/>                                   
                                    <div class="btn-wrap"><i class="fa fa-plus" aria-hidden="true"></i><sj:submit href="%{addnewhosturl}" targets="divmsg" value="Add" button="true" disabled="#vadd" cssClass="btn default-button" /></div>
                                    <div class="btn-wrap"><i class="fa fa-times" aria-hidden="true"></i><sj:submit button="true" value="Reset" onClick="resetFormFull()" cssClass="btn reset-button"/></div>
                                </div>
                            </div>
                        <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                        </s:form>
                    </div>
                </div>

                <div class="content-section data-form" id="editniimap" style="display: none;">
                    <s:form id="edithost" theme="simple" method="post" >
                        <div class="content-data">

                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg">
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i> <span id="divmsg"></span></div>
                            </div>
                            <!-- End -->

                            <!-- Four colum form row begin -->
                            <div class="d-row">
                                <s:hidden id="uplistId" name="uplistId" />
                                <label class="col-1 form-label">Listener Name<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="uplistName" name="uplistName" cssClass="txt-input width-35" maxLength="8"/>
                                    <s:hidden id="hidUplistName" name="hidUplistName" cssClass="txt-input width-35" maxLength="8"/>
                                </div>
                                <label class="col-3 form-label">Port<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="uplistPort" name="uplistProt" cssClass="txt-input width-35" maxLength="15"/>
                                    <s:hidden id="hidUplistPort" name="hidUplistPort" cssClass="txt-input width-35" maxLength="15"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Timeout(ms)<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="uplistTimeOut" name="uplistTimeOut" cssClass="txt-input width-35" maxLength="8"/>
                                </div>
                                <label class="col-3 form-label">Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="uplistStatus" id="uplistStatus" list="%{liststatusMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Connection Type<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="uplistConnectType" id="uplistConnectType" list="%{listconTypeMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">Keep Alive Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select name="uplistKeepLive" id="uplistKeepLive" list="%{listKeepLiveMap}" headerKey="1" 
                                              listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Header Format<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select name="uplistHeaderFormat" id="uplistHeaderFormat" list="%{listHeaderFormatMap}" headerKey="1" 
                                              listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">Header Size<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select name="uplistHeadSize" id="uplistHeadSize" list="%{listHeaderSizeMap}" headerKey="1" 
                                              listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">ISO File<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select name="upisofile" id="upisofile" list="%{ISOFormat}" headerKey="1" 
                                              listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">TPDU Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select name="uptpduStatus" id="uptpduStatus" list="%{liststatusMap}" headerKey="1" 
                                              listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <!-- End -->

                            <div class="d-row cpanel four-col">
                                <label class="col-1">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="updateuserurl" action="UpdatelistMng"/>                                   
                                    <div class="btn-wrap lnk-match"><i class="fa fa-pencil-square-o" aria-hidden="true"></i><sj:submit  href="%{updateuserurl}" disabled="#vupdate" targets="divmsg" value="Update"  button="true" cssClass="btn default-button" /></div>
                                    <div class="btn-wrap"><i class="fa fa-times" aria-hidden="true"></i><sj:submit button="true" value="Reset" onClick="resetUpdateForm()" cssClass="btn reset-button"/></div>
                                </div>
                            </div>
                            <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                        </s:form>
                    </div>
                </div>
                <div class="content-section errorpanel">
                    <div class="content-data">
                        <!-- Error and success message panel begin -->
                        <div class="msg-panel del-channel-msg">
                            <div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                        </div>
                    </div>
                </div>

                <!-- Grid data begin -->
                <div class="content-section">
                    <div class="content-data">
                        <h2 class="section-title">All Registered Listeners</h2>
                        <!-- Error and success message panel begin -->
                        <!--                        <div class="msg-panel del-channel-msg" >
                                                    <div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                                                </div>-->
                        <!-- End -->
                    </div>
                    <div id="tablediv" class="custom-grid">
                        <sj:dialog 
                            id="deleteConfirmDialog" 
                            buttons="{ 
                            'OK':function() { deleteNiimap($(this).data('listId'));$( this ).dialog( 'close' ); },
                            'Cancel':function() { $( this ).dialog( 'close' );} 
                            }" 
                            autoOpen="false" 
                            modal="true" 
                            title="Delete Listener Confirmation"
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
                        <s:url var="listurl" action="ListlistMng"/>
                        <sjg:grid
                            id="gridtable"
                            caption="Listener Management"
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

                            <sjg:gridColumn name="listBindStatus" index="epicTleStatusByBindStatus.description" frozen="true" title="Bind Status" align="center" formatter="StatusFormatterBind"  sortable="true"/> 
                            <sjg:gridColumn title="Listener Name" name="listId" index="listenerId" hidden="true" frozen="true" align="left"  sortable="true"/>  
                            <sjg:gridColumn name="listName" index="listenerName" title="Listener Name" align="left" frozen="true"  sortable="true"  /> 
                            <sjg:gridColumn name="listPort" index="listenerPort" title="Port" align="left" width="80" frozen="true" sortable="true"/> 
                            <sjg:gridColumn name="listTimeOut" index="listenerTimeout" title="Timeout(ms)" align="left" frozen="true"  sortable="true"/> 
                            <sjg:gridColumn name="listConnectType" index="epicTleConnectiontypes.description" title="Connection Type" align="left"  sortable="true"/> 
                            <sjg:gridColumn name="listKeepLive" index="epicTleStatusByListenerKeepAliveStatus.description" title="Keep Alive" align="center"  sortable="true"/> 
                            <sjg:gridColumn name="tpduStatus" index="epicTleStatusByTpduStatus.description" title="TPDU Status" align="center"  sortable="true"/> 
                            <sjg:gridColumn name="listStatus" index="epicTleStatusByStatus.description" title="Status" align="center" formatter="Statusformatter"  sortable="true"/> 
                            <sjg:gridColumn name="listHeaderFormat" index="epicTleHeaderFormats.description" title="Header Format" align="left"  sortable="true"/> 
                            <sjg:gridColumn name="listHeadSize" index="epicTleHeaderSize.description" title="Header Size" align="left"  sortable="true"/> 
                            <sjg:gridColumn name="isoFile" index="isoFile" title="ISO File" align="left"  sortable="true"/> 
                            <sjg:gridColumn name="listId"  title="Action"   align="center" formatter="editformatter" sortable="false" cssClass="action-col" />
                        </sjg:grid>  
                    </div> 
                </div>
                <!-- End -->
            </div>

            <jsp:include page="../../footer.jsp" />
        </section>
        <script type="text/javascript">
            $(document).ready(function () {

                //Back button event
                $('.lnk-back').on('click', function () {
                    backToMain();
                    $('#task').empty();
                    $('.lnk-back').addClass('hide-element');
                    var text = 'Add Listener';
                    $('#task').append(text);
                    return false;
                });

                setTimeout(function () {
                    $(window).trigger('resize');
                }, 100);

            });
        </script>
    </body>
</html>
