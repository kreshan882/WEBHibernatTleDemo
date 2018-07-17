<%-- 
    Document   : registerTerminal
    Created on : Feb 22, 2017, 10:33:53 AM
    Author     : danushka_r
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <style>
            body #searchdiv .msg-panel {
                width: 100%;
                margin: 10px 0px 4px 0px;
                text-align: left;
                font-family: arial;
                font-size: 0.8em;
                float: left;
            }
            body #searchdiv .msg-panel.error-login-msg {
                color: #d9534f;
            }

            .actionMessage{
                color: green;
            }


        </style>
        <jsp:include page="../../Styles.jsp" />
        <script type="text/javascript">
            function resetForm() {
                $('#tid').val("");
                $('#mid').val("");
                $('#serialno').val("");
                $('#name').val("");
                $('#bank').val("");
                $('#location').val("");
                $('#terBrand').val("");
                $('#encStatus').val("-1");
                $('#encType').val("-1");

                $('#etid').val("");
                $('#emid').val("");
                $('#eserialNo').val("");
                $('#eterminalBrand').val("");
                $('#ebank').val("");
                $('#ename').val("");
                $('#elocation').val("");
                $('#enonEncryptionTransaction').val("-1");
                $('#BinPrf').val("-1");
                $('#eencryptionType').val("-1");
                $('#estatus').val("-1");
                $('#BinPrf').val("1");
                $('#upBinPrf').val("1");
                $('#teminalRefProf').val("1");
                $('#upteminalRefProf').val("1");
                $('#upBinPrf').val("1");
                $('#upBinStatus').val("-1");
                jQuery("#gridtable").trigger("reloadGrid");

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

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' disabled='#vupdate' title='Edit Terminal' onClick='editTerminalUser(&#34;" + cellvalue + "&#34;)'><i class='fa fa-pencil' aria-hidden='true'></i></a> <a href='#' disabled='vdelete' title='Delete Terminal' onClick='deleteTerminalUserInit(&#34;" + rowObject.tid + "&#34;,&#34;" + rowObject.tid + "&#34;)'><i class='fa fa-trash-o' aria-hidden='true'></i></a>";
            }

            function deleteTerminalUserInit(tid, tid) {
                $("#deleteConfirmDialog").data('tid', tid);
                $("#deleteConfirmDialog").data('tid', tid).dialog('open');
                $("#deleteConfirmDialog").html('<p>Please confirm delete : ' + tid + "</p>");
                $('.add-form-msg').hide();
                return false;
            }
            function Statusformatter(cellvalue) {
                if (cellvalue == 1) {
                    return "<i class='fa fa-circle active' aria-hidden='true'></i>";
                } else {
                    return "<i class='fa fa-circle' aria-hidden='true'></i>";
                }
            }


            function deleteTerminalUser(tid) {
                var token = $("input[name='RequstToken']").val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeleteregTerm',
                    data: {tid: tid, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken = data.token;
                        if (data.isDeleted == true) {
                            utilityManager.showMessage('.del-user-msg', data.dmessage, 'successmsg', $stoken);
                        } else {
                            utilityManager.showMessage('.del-user-msg', data.dmessage, 'errormsg', $stoken);
                        }
                        resetForm();
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });
            }

            function resetData() {
                $('#tid').val("");
                $('#mid').val("");
                $('#serialno').val("");
                $('#name').val("");
                $('#bank').val("");
                $('#location').val("");
                $('#terBrand').val("");
                $('#encStatus').val("-1");
                $('#encType').val("-1");

                $('#etid').val("");
                $('#emid').val("");
                $('#eserialNo').val("");
                $('#eterminalBrand').val("");
                $('#ebank').val("");
                $('#ename').val("");
                $('#elocation').val("");
                $('#enonEncryptionTransaction').val("-1");
                $('#BinPrf').val("-1");
                $('#eencryptionType').val("-1");
                $('#estatus').val("-1");
                $('#BinPrf').val("1");
                $('#upBinPrf').val("-1");
                $('#upBinStatus').val("-1");
                $('.add-form-msg').hide();
                $('.del-user-msg').hide();
//                jQuery("#gridtable").trigger("reloadGrid");
            }

            function editTerminalUser(keyval) {
                $('#terminaluserEditForm').show();
                $('.lnk-back').removeClass('hide-element');
                $('#addDiv').hide();
                $('#uploadDiv').hide();
                $('#searchdiv').hide();
                $('.add-form-msg').hide();
                $('.del-user-msg').hide();
                $('#task').empty();
                var text = ' Edit Terminal';
                $('#task').append(text);
                var token = $("input[name='RequstToken']").val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/FindregTerm',
                    data: {tid: keyval, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken = data.token;
                        $("input[name='RequstToken']").val($stoken);
                        $('#terminaluserEditForm').show();
                        $('#searchdiv').hide();
                        $('#etid').attr('readOnly', true).val(data.tid);
                        $('#emid').val(data.mid);
                        $('#eserialNo').val(data.serialNo);
                        $('#eterminalBrand').val(data.terminalBrand);
                        $('#ebank').val(data.bank);
                        $('#ename').val(data.name);
                        $('#elocation').val(data.location);
                        $('#enonEncryptionTransaction').val(data.nonEncryptionTransaction);
                        $('#eencryptionType').val(data.encryptionType);
                        $('#estatus').val(data.status);
                        $('#upBinStatus').val(data.upBinStatus);
                        $('#upBinPrf').val(data.upBinPrf);
                        $('#upteminalRefProf').val(data.upteminalRefProf);
                    },
                    error: function (data) {
                           window.location = "${pageContext.request.contextPath}/logOut.action";
                       }
                });
            }

            function resetUpdateForm() {
                var keyval = $('#etid').val();
                $('.del-user-msg').hide();
                editTerminalUser(keyval);
            }

            function backToMain() {
                $('#terminaluserEditForm').hide();
                $('#searchdiv').show();
                $('#addDiv').hide();
                $('#uploadDiv').hide();
                $('.add-form-msg').hide();
                $('#task').empty();
                jQuery("#gridtable").trigger("reloadGrid");

            }

            $.subscribe('onclicksearch', function (event, data) {
                var terminalId = $('#terminalId').val();
                var statusValue = $('#statusValue').val();
                var encryptionStatusValue = $('#encryptionStatusValue').val();
               var nonEncryptionStatusValue = $('#nonEncryptionStatusValue').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {terminalId: terminalId, statusValue: statusValue, encryptionStatusValue: encryptionStatusValue, nonEncryptionStatusValue: nonEncryptionStatusValue}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
                $('.del-user-msg').hide();
            });


            $.subscribe('onAddBtnClick', function (event, data) {
                $('#addDiv').show();
                $('.lnk-back').removeClass('hide-element');
                $('#searchdiv').hide();
                $('#uploadDiv').hide();
                $('#task').empty();
                var text = ' Add Terminal';
                $('#task').append(text);
                $('.del-user-msg').hide();
            });

            $.subscribe('onClickExport', function (event, data) {
                $('#addDiv').hide();
                $('#searchdiv').hide();
                $('#uploadDiv').show();
                $('.lnk-back').removeClass('hide-element');
                $('#task').empty();
                var text = 'Import Terminal';
                $('#task').append(text);
                $('.del-user-msg').hide();
            });
            function changeStatus(value) {
                if (value == 1) {
                    $("#BinPrf").attr('disabled', false);
                    $("#upBinPrf").attr('disabled', false);
                } else {
                    $("#BinPrf").attr('disabled', true);
                    $("#upBinPrf").attr('disabled', true);
                }
            }

            //focus on Enter 
            function keyPress(e) {
                if (e.keyCode === 13) {
                    e.preventDefault(); // Ensure it is only this code that rusn
                    $("#searchbut").click(); // returning false will prevent the event from bubbling up. // returning false will prevent the event from bubbling up.
                }
            }

            function resetSearchForm() {
                $('#terminalId').val("");
                $('#statusValue').val("");
                $('#encryptionStatusValue').val("");
                $('#nonEncryptionStatusValue').val("");
                $('#divmsg').empty();
                $('.del-user-msg').hide();
                $("#gridtable").jqGrid('setGridParam', {postData: {terminalId: "", statusValue: "", encryptionStatusValue: "", nonEncryptionStatusValue: ""}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            }

            function show() {
                var inputfile = document.getElementById("upfile");
                var filesize = inputfile.files[0].size;
//                alert(filesize > 100485760);
                if (filesize > 500485760) {
                    
                     $("#fileAlert").dialog('open');
//                      $("#fileAlert").html('<p>Please select suitable file size' "</p>");
                        $("#fileAlert").html("Please select suitable file size (Less than 500MB)");
                   
                     document.getElementById("btnupload").disabled = true;
                } else {
                   
                   document.getElementById("btnupload").disabled = false;
                }
            }
            
            function saveReset(){
                $('.del-user-msg').hide();
            }
            function updateReset(){
                $('.del-user-msg').hide();
            }

        </script>

    </head>

    <s:set id="vadd" var="vadd"><s:property  value="add" default="true"/></s:set>
    <s:set id="vupdate" var="vupdate"><s:property value="update" default="true"/></s:set>
    <s:set id="vdelete" var="vdelete"><s:property value="delete" default="true"/></s:set>
    <s:set id="vupload" var="vupload"><s:property value="upload" default="true"/></s:set>
    <s:set id="vview" var="vview"><s:property value="view" default="true"/></s:set>
    <s:set id="vdownload" var="vdownload"><s:property value="download" default="true"/></s:set>

        <body style="overflow:hidden">
            <section class="app-content">
            <jsp:include page="../../header.jsp" /> 
            <div class="content innerpage">
                <!--Body Content-->

                <!-- Breadcrumb begin -->
                <div class="breadcrumb">
                    <s:property value="Module"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i> <s:property value="Section"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i><span id="task" class="active">Search Terminals</span>
                </div>
                <!-- End -->

                <h1 class="page-title"><s:property value="Section"/><a href="#" class="lnk-back hide-element do-nothing"><i class="fa fa-arrow-left" aria-hidden="true"></i> back</a></h1>

                <div class="content-section data-form" id="searchdiv">
                    <div class="msg-panel error-login-msg">
                        <s:actionerror/>
                        <s:actionmessage/>
                    </div>
                    <div class="content-data">

                        <s:form id="terminalusersearchForm"  action="XSLcreat1regField" name="terminalusersearchForm" theme="simple" method="post">
                            <div class="d-row">
                                <label class="left-col form-label">Terminal ID</label>
                                <div class="right-col form-field">
                                    <s:textfield id="terminalId" name="terminalId" cssClass="txt-input width-35" maxLength="8" onkeypress="keyPress(event)"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Status</label>
                                <div class="right-col form-field">
                                    <s:select  name="statusValue" headerKey="" headerValue="---Select---"
                                               listKey="key" listValue="value"
                                               list="%{statusMap}" id="statusValue" cssClass="ddl-input width-35" onkeypress="keyPress(event)"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Encryption Type</label>
                                <div class="right-col form-field">
                                    <s:select  name="encryptionStatusValue" headerKey="" headerValue="---Select---"
                                               listKey="key" listValue="value"
                                               list="%{encryptionStatusMap}" id="encryptionStatusValue" cssClass="ddl-input width-35" onkeypress="keyPress(event)"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Non-Encryption Status</label>
                                <div class="right-col form-field">
                                    <s:select  name="nonEncryptionStatusValue" headerKey="" headerValue="---Select---"
                                               listKey="key" listValue="value"
                                               list="%{nonEncryptionStatusMap}" id="nonEncryptionStatusValue" cssClass="ddl-input width-35" onkeypress="keyPress(event)"/>
                                </div>
                            </div>
                            <!-- Tow column form button panel begin -->	
                            <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                        </s:form>
                        <div class="d-row cpanel">
                            <label class="left-col">&nbsp;</label>
                            <div class="right-col">
                                <sj:a id="searchbut" button="true" onClickTopics="onclicksearch"
                                      cssClass="btn default-button" role="button" disabled="#vfind" aria-disabled="false" >
                                    <i class="fa fa-search" aria-hidden="true"></i>
                                    Search</sj:a>
                                <sj:a id="addBtn" button="true" onClickTopics="onAddBtnClick"
                                      cssClass="btn default-button" disabled="#vadd" role="button" aria-disabled="false" >
                                    <i class="fa fa-plus" aria-hidden="true"></i>
                                    Add</sj:a>                                
                                <sj:a id="uploadBtn" button="true" onClickTopics="onClickExport"
                                      cssClass="btn default-button" role="button" disabled="#vupload" aria-disabled="false"  >
                                    <i class="fa fa-file-o" aria-hidden="true"></i>
                                    Import</sj:a>
                                <sj:a id="exportBtn" button="true" disabled="#vdownload" onclick="utilityManager.anchorTagSubmit('#exportBtn','#terminalusersearchForm')"
                                      cssClass="btn default-button" role="button" aria-disabled="false" >
                                    <i class="fa fa-reply-all" aria-hidden="true"></i>
                                    Export</sj:a>

                                    <button type="submit" value="Reset" onClick="resetSearchForm()" style="height: 26px;" class="btn reset-button"><i class="fa fa-times" aria-hidden="true"></i> Reset </button>

                                </div>
                            </div>
                            <!-- End -->
                        </div>

                    </div>

                    <div class="content-section data-form" id="uploadDiv" style="display: none">
                    <s:form id="upfileform" action="uploadFileregTerm" theme="simple" enctype="multipart/form-data"  method="post">  
                        <div class="content-data">

                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg">
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i> <span id="divmsg"></span></div>
                            </div>
                            <!-- End -->
                            <div class="d-row">
                                <div class="right-col form-field">
                                    <!--<label class="col-1">&nbsp;</label>-->
                                    <s:file  id = "upfile" name="upfile" label="File" cssClass="fileField" accept=".txt" onchange="show()"/>
                                    <div class="btn-wrap lnk-match"><i class="fa fa-upload" aria-hidden="true"></i>

                                        <sj:submit type="submit" id="btnupload" value="IMPORT" disabled="#vupload" name="submit" method="post" cssClass="btn default-button">
                                        </sj:submit></div>   
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                    </s:form> 
                </div>

                <div class="content-section data-form" id="addDiv" style="display: none">
                    <s:form  theme="simple" method="POST" name="addterform" id="addterform">
                        <div class="content-data">

                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg">
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i> <span id="divmsg"></span></div>
                            </div>
                            <!-- End -->

                            <div class="d-row">
                                <label class="col-1 form-label">Terminal ID<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="tid" name="tid" cssClass="txt-input width-35" maxLength="8"/>
                                </div>
                                <label class="col-3 form-label">Merchant ID<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="mid" name="mid" cssClass="txt-input width-35" maxLength="15"/>
                                </div>
                            </div>

                            <div class="d-row">
                                <label class="col-1 form-label">Serial Number</label>
                                <div class="col-2 form-field">
                                    <s:textfield id="serialno" name="serialno" cssClass="txt-input width-35" maxLength="15"/>
                                </div>
                                <label class="col-3 form-label">Name</label>
                                <div class="col-4 form-field">
                                    <s:textfield id="name" name="name" cssClass="txt-input width-35" maxLength="30"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Bank</label>
                                <div class="col-2 form-field">
                                    <s:textfield id="bank" name="bank" cssClass="txt-input width-35" maxLength="40"/>
                                </div>
                                <label class="col-3 form-label">Location</label>
                                <div class="col-4 form-field">
                                    <s:textfield id="location" name="location" cssClass="txt-input width-35" maxLength="40"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Terminal Brand</label>
                                <div class="col-2 form-field">
                                    <s:textfield id="terBrand" name="terBrand" cssClass="txt-input width-35" maxLength="40"/>
                                </div>
                                <label class="col-3 form-label">Encryption Type<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="encType" id="encType" headerKey="-1"  headerValue="---Select---" 
                                               listKey="key" listValue="value" list="%{encTypeMap}" cssClass="ddl-input "/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Non-Encryption Transactions<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="encStatus" id="encStatus" headerKey="-1" listKey="key" listValue="value" 
                                               headerValue="---Select---" list="%{encStatusMap}" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">Block BIN Profile</label>
                                <div class="col-4 form-field">
                                    <s:select  name="BinPrf" id="BinPrf" headerKey="1"  headerValue="No Profiles" 
                                               listKey="key" listValue="value" list="%{BinPrfMap}" cssClass="ddl-input" />
                                </div>


                            </div>
                            <div class="d-row">

                                <label class="col-1 form-label">Terminal Risk Profile</label>
                                <div class="col-2 form-field">
                                    <s:select  name="teminalRefProf" headerKey="1" headerValue="No Profiles"
                                               listKey="key" listValue="value"
                                               list="%{teminalRefProfMap}" id="teminalRefProf" cssClass="ddl-input" />
                                </div>

                            </div>

                            <div class="d-row cpanel four-col">
                                <label class="col-1">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="saveurl" action="addTerminalregTerm"/>                                   
                                    <div class="btn-wrap lnk-match"><i class="fa fa-floppy-o" aria-hidden="true"></i><sj:submit  href="%{saveurl}" disabled="#vadd" targets="divmsg" value="Save" cssClass="btn default-button" onclick="saveReset()"/></div>  

                                    <sj:a button="true"   onclick="resetData()"  value="Reset" cssClass="btn reset-button"><i class="fa fa-times" aria-hidden="true"></i> Reset</sj:a>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                    </s:form>
                </div>


                <div class="content-section data-form" id="terminaluserEditForm" style="display: none">
                    <s:form theme="simple" method="POST" name="updateTerform" id="updateTerform">

                        <div class="content-data">
                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg">
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i> <span id="divmsg"></span></div>
                            </div>
                            <!-- End -->

                            <div class="d-row">
                                <label class="col-1 form-label">Terminal ID<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield name="tid" id="etid" cssClass="txt-input width-35" maxLength="8"/>
                                </div>
                                <label class="col-3 form-label">Merchant ID<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield name="mid" id="emid" cssClass="txt-input width-35" maxLength="15"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Serial Number</label>
                                <div class="col-2 form-field">
                                    <s:textfield name="serialNo" id="eserialNo" cssClass="txt-input width-35" maxLength="16" />
                                </div>
                                <label class="col-3 form-label">Name</label>
                                <div class="col-4 form-field">
                                    <s:textfield name="name" id="ename" cssClass="txt-input width-35" maxLength="40"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Bank</label>
                                <div class="col-2 form-field">
                                    <s:textfield name="bank" id="ebank" cssClass="txt-input width-35" maxLength="40"/>
                                </div>
                                <label class="col-3 form-label">Location</label>
                                <div class="col-4 form-field">
                                    <s:textfield name="location" id="elocation" cssClass="txt-input width-35" maxLength="40"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Terminal Brand</label>
                                <div class="col-2 form-field">
                                    <s:textfield name="terminalBrand" id="eterminalBrand" cssClass="txt-input width-35" maxLength="40"/>
                                </div>
                                <label class="col-3 form-label">Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="status" headerKey="-1" headerValue="---Select---" 
                                               listKey="key" listValue="value" 
                                               list="%{statusMap}" id="estatus" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Encryption Type<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="encryptionType" headerKey="-1" headerValue="---Select---" 
                                               listKey="key" listValue="value" 
                                               list="%{encryptionStatusMap}" id="eencryptionType" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">Non-Encryption Transactions<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="nonEncryptionTransaction" headerKey="-1" headerValue="---Select---"
                                               listKey="key" listValue="value"
                                               list="%{encStatusMap}" id="enonEncryptionTransaction" cssClass="ddl-input"/>
                                </div>
                            </div >
                            <div class="d-row">
                                <label class="col-1 form-label">Block BIN Profile</label>
                                <div class="col-2 form-field">
                                    <s:select  name="upBinPrf" headerKey="1" headerValue="No Profile"
                                               listKey="key" listValue="value"
                                               list="%{BinPrfMap}" id="upBinPrf" cssClass="ddl-input" />
                                </div>
                                <label class="col-3 form-label">Terminal Risk Profile</label>
                                <div class="col-4 form-field">
                                    <s:select  name="upteminalRefProf" headerKey="1" headerValue="No Profile"
                                               listKey="key" listValue="value"
                                               list="%{teminalRefProfMap}" id="upteminalRefProf" cssClass="ddl-input" />
                                </div>

                            </div>

                            <div class="d-row cpanel four-col">
                                <label class="col-1">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="updateterminalurl" action="UpdateregTerm"/>                                   
                                    <div class="btn-wrap lnk-match"><i class="fa fa-pencil-square-o" aria-hidden="true"></i><sj:submit href="%{updateterminalurl}" disabled="#vupdate" targets="divmsg" value="Update" button="true" cssClass="btn default-button" onclick="updateReset()"/></div>
                                    <sj:a button="true" onClick="resetUpdateForm()" cssClass="btn reset-button"><i class="fa fa-times" aria-hidden="true"></i> Reset</sj:a>
                                    </div>
                                </div>
                            </div>
                            <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                    </s:form>
                </div>

                <!-- Grid data begin -->
                <div class="content-section">
                    <div class="content-data">
                        <h2 class="section-title">All Registered Terminals</h2>
                        <!-- Error and success message panel begin -->
                        <div class="msg-panel del-user-msg" >
                            <div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                        </div>
                        <!-- End -->

                        <div id="tablediv" class="custom-grid">
                            
                            
                            
                            <sj:dialog 
                                id="fileAlert" 
                               buttons="{ 
                                'OK':function() { $( this ).dialog( 'close' );} 
                                }" 
                                autoOpen="false" 
                                modal="true" 
                                title="file size"
                                width="350"
                                height="150"
                                position="center"
                                />
                            

                            <sj:dialog 
                                id="deleteConfirmDialog" 
                                buttons="{ 
                                'OK':function() { deleteTerminalUser($(this).data('tid'));$( this ).dialog( 'close' ); },
                                'Cancel':function() { $( this ).dialog( 'close' );} 
                                }" 
                                autoOpen="false" 
                                modal="true" 
                                title="Delete Terminal User Confirmation"
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
                                title="Delete User" 
                                width="400"
                                height="150"
                                position="center"
                                />
                            <s:url var="listurl" action="ListregTerm"/>
                            <sjg:grid
                                id="gridtable"
                                caption="All Registerd Terminals"
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
                                <sjg:gridColumn name="sid" title="sId" hidden="true" />
                                <sjg:gridColumn name="tid" index="tid" title="TID" align="left" frozen="true"  sortable="true"  /> 
                                <sjg:gridColumn name="mid" index="mid" title="MID" align="left" frozen="true"  sortable="true"/>                    
                                <sjg:gridColumn name="serialNo" index="serialNo" title="Serial No" frozen="true"  align="left"  sortable="true"/>
                                <sjg:gridColumn name="terminalBrand"  index="terminalbrand" title="Terminal Brand"   align="left" sortable="true"/>
                                <sjg:gridColumn name="bank"  title="Bank"   align="left" sortable="true"/>
                                <sjg:gridColumn name="name" title="Name" index="name" align="left"  sortable="true" />
                                <sjg:gridColumn name="location" index="location" title="Location" align="left"  sortable="true"  /> 
                                <sjg:gridColumn name="registerDate" index="regdate" title="Register Date" align="center"  sortable="true"/>                    
                                <sjg:gridColumn name="encryptionStatus" index="epicTleStatusByStatus.description" title="Encryption Type"  align="left" sortable="true"/>
                                <sjg:gridColumn name="status"  title="Status" index="epicTleStatusByStatus.description"  align="center" formatter="Statusformatter" sortable="true"/>
                                <sjg:gridColumn name="blockBinProfName" index="epicTleBinProfile.description" title="Block BIN Profile"   align="left"  sortable="true"/>
                                <sjg:gridColumn name="teminalRefProf" index="epicTleTerminalRefprofile.id" title="Terminal Risk Profile"   align="left"  sortable="true"/>
                                <sjg:gridColumn name="encStatus" index="epicTleStatusByNonenctxnstatus.description" title="Non-Encription Transaction"   align="left"  sortable="true"/>
                                <sjg:gridColumn name="tid"  title="Action"  width="80" align="center" formatter="editformatter"  cssClass="action-col" sortable="false"/>
                            </sjg:grid> 
                        </div> 
                    </div>
                </div>
            </div>
            <!-- End -->




            <div class="display" id="uploadDiv" style="display: none">
                <s:form id="upfileform" action="uploadFileregTerm" theme="simple" enctype="multipart/form-data"  method="post">  
                    <table>

                        <tr>
                            <td style="padding:8px;border: 1px solid #303338;"> 
                                <s:file  id = "upfile" name="upfile" label="File" cssClass="fileField"/>
                                <s:submit type="submit" value="Upload"  name="submit" method="post" cssClass="addsubmit" ></s:submit>   
                                <sj:a name="back" onclick="backToMain()" cssClass="addsubmit" >Back</sj:a>   
                                </td>  
                            </tr>
                        </table>                            
                        <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                </s:form> 
            </div>


            <!--End of Body Content-->

            <jsp:include page="../../footer.jsp" />
        </div><!--End of Wrapper-->
    </section>
    <script type="text/javascript">
        $(document).ready(function () {
            //Back button event
            $('.lnk-back').on('click', function () {
                backToMain();
                $('#task').empty();
                $('.lnk-back').addClass('hide-element');
                var text = ' Search Terminals';
                $('#task').append(text);
                return false;
            });

            $(document).ready(function () {

                setTimeout(function () {
                    $(window).trigger('resize');
                }, 500);

            });

        });
    </script>
</body>
</html>
