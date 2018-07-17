<%-- 
    Document   : editViewTerminal
    Created on : Sep 16, 2015, 2:50:04 PM
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
                $('#tid').val("");
                $('#mid').val("");
                $('#serialNo').val("");
                $('#terminalBrand').val("");
                $('#bank').val("");
                $('#name').val("");
                $('#location').val("");
                $('#nonEncryptionTransaction').val("-1");
                $('#encryptionType').val("-1");
                $('#status').val("-1");
                jQuery("#gridtable").trigger("reloadGrid");
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

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='editTerminalUser(&#34;" + cellvalue + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/edit_icon.png'  /></a>";
            }
            function deleteformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='deleteTerminalUserInit(&#34;" + rowObject.tid + "&#34;,&#34;" + rowObject.tid + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/delete_icon.png'  /></a>";
            }

            function deleteTerminalUserInit(tid, tid) {
                $("#deleteConfirmDialog").data('tid', tid);
                $("#deleteConfirmDialog").data('tid', tid).dialog('open');
                $("#deleteConfirmDialog").html('<br><br><b><font size="3" color="red"><center>Please confirm delete : ' + tid);
                return false;
            }
            
            
            function deleteTerminalUser(tid) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeleteTerminalEditView',
                    data: {tid: tid},
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
                jQuery("#gridtable").trigger("reloadGrid");
            }


            function editTerminalUser(keyval) {
                $('#terminaluserEditForm').show();
                $('#terminalusersearchForm').hide();
                $('#divmsg').empty();
                $.ajax({
                    url: '${pageContext.request.contextPath}/FindTerminalEditView',
                    data: {tid: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $('#terminaluserEditForm').show();
                        $('#terminalusersearchForm').hide();
                        $('#tid').attr('readOnly', true).val(data.tid);
                        $('#mid').val(data.mid);
                        $('#serialNo').val(data.serialNo);
                        $('#terminalBrand').val(data.terminalBrand);
                        $('#bank').val(data.bank);
                        $('#name').val(data.name);
                        $('#location').val(data.location);
                        $('#nonEncryptionTransaction').val(data.nonEncryptionTransaction);
                        $('#encryptionType').val(data.encryptionType);
                        $('#status').val(data.status);
                    },
                    error: function (data) {
                           window.location = "${pageContext.request.contextPath}/logOut.action";
                       }
                });
            }

            function resetUpdateForm() {
                $('#divmsg').empty();
                var keyval = $('#upuserName').val();

                $.ajax({
                    url: '${pageContext.request.contextPath}/FindUserEditView',
                    data: {upuserName: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $('#tid').attr('readOnly', true).val(data.tid);
                        $('#mid').val(data.mid);

                        $('#serialNo').val(data.serialNo);
                        $('#name').val(data.name);
                        $('#bank').val(data.bank);
                        $('#location').val(data.location);

                        $('#terminalBrand').val(data.terminalBrand);
                        $('#status').val(data.status);
                        $('#encryptionType').val(data.encryptionType);
                        $('#nonEncryptionTransaction').val(data.nonEncryptionTransaction);
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
                $('#terminaluserEditForm').hide();
                $('#terminalusersearchForm').show();
                $('#divmsg').empty();
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
            });


        </script>

    </head>

    <body style="overflow:hidden">
        <div class="wrapper">
            <jsp:include page="../../header.jsp" /> 

            <!--Body Content-->
            <div class="body_right">
                <div class="heading"><div class="underline">Edit & View Terminal</div></div>
                <div class="content">

                    <div class="content_highlight">  
                        <div class="message">   
                            <s:div id="divmsg">
                                <i style="color: red; background-color: black !important;">  <s:actionerror theme="jquery"/></i>
                                <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                            </s:div>
                        </div>
                    </div>

                    <div class="display">              
                        <s:form id="terminaluserEditForm" theme="simple" method="post" cssStyle="display: none">
                            <table border="0">
                                <tr>
                                    <td class="lable" colspan="2" align="left">Terminal ID<span class="mandatory"></span></td>
                                    <td class="lable" width="5px">:</td>
                                    <td colspan="3" align="left"><s:textfield name="tid" id="tid" cssClass="text_field" /></td>
                                </tr>
                                <tr>
                                    <td class="lable" colspan="2" align="left">Merchant ID<span class="mandatory">*</span></td>
                                    <td class="lable" width="5px">:</td>
                                    <td colspan="3" align="left"><s:textfield name="mid" id="mid" cssClass="text_field" /></td>
                                </tr>

                                <tr>
                                    <td class="lable" colspan="2" align="left">Serial No<span class="mandatory">*</span></td>
                                    <td class="lable" width="5px">:</td>
                                    <td colspan="3" align="left"><s:textfield name="serialNo" id="serialNo" cssClass="text_field" /></td>
                                </tr>
                                <tr>
                                    <td class="lable" colspan="2" align="left">Name<span class="mandatory">*</span></td>
                                    <td class="lable" width="5px">:</td>
                                    <td colspan="3" align="left"><s:textfield name="name" id="name" cssClass="text_field" /></td>
                                </tr>
                                <tr>
                                    <td class="lable" colspan="2" align="left">Bank Name<span class="mandatory">*</span></td>
                                    <td class="lable" width="5px">:</td>
                                    <td colspan="3" align="left"><s:textfield name="bank" id="bank" cssClass="text_field" /></td>
                                </tr>
                                <tr>
                                    <td class="lable" colspan="2" align="left">Location<span class="mandatory">*</span></td>
                                    <td class="lable" width="5px">:</td>
                                    <td colspan="3" align="left"><s:textfield name="location" id="location" cssClass="text_field" /></td>
                                </tr>
                                <tr>
                                    <td class="lable" colspan="2" align="left">Terminal Brand<span class="mandatory">*</span></td>
                                    <td class="lable" width="5px">:</td>
                                    <td colspan="3" align="left"><s:textfield name="terminalBrand" id="terminalBrand" cssClass="text_field" /></td>
                                </tr>
                                <tr>
                                    <td class="lable" colspan="2" align="left">Status<span class="mandatory">*</span></td>
                                    <td class="lable" width="5px">:</td>
                                    <td colspan="2"><s:select  name="status" headerKey="-1" headerValue="---Select Status ---" 
                                               listKey="key" listValue="value" 
                                               list="%{statusMap}" id="status" cssClass="dropdown"/></td>
                                </tr>
                                <tr>
                                    <td class="lable" colspan="2" align="left">Encryption Type<span class="mandatory">*</span></td>
                                    <td class="lable" width="5px">:</td>
                                    <td colspan="2"><s:select  name="encryptionType" headerKey="-1" headerValue="---Select Encryption Status ---" 
                                               listKey="key" listValue="value" 
                                               list="%{encryptionStatusMap}" id="encryptionType" cssClass="dropdown"/></td>
                                </tr>
                                <tr>
                                    <td class="lable" colspan="2" align="left">Non Encryption Transactions<span class="mandatory">*</span></td>
                                    <td class="lable" width="5px">:</td>
                                    <td colspan="2"><s:select  name="nonEncryptionTransaction" headerKey="-1" headerValue="---Select Non-Encryption Status ---"
                                               listKey="key" listValue="value"
                                               list="%{nonEncryptionStatusMap}" id="nonEncryptionTransaction" cssClass="dropdown"/></td>
                                </tr>

                                <tr>
                                    <td colspan="5" align="left"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td colspan="3" align="right">
                                        <s:url var="updateterminalurl" action="UpdateTerminalEditView"/>                                   
                                        <sj:submit  href="%{updateterminalurl}"  targets="divmsg" value="Update"  button="true"   cssClass="add" />
                                    </td>
                                    <td align="center" width="60px">
                                        <sj:a href="#" name="back" button="true" onclick="backToMain()" cssClass="add" >Back</sj:a>     
                                        </td>
                                        <td align="right" width="60px">
                                        <sj:submit button="true" value="Reset" onClick="resetForm()" formatter="editformatter" cssClass="add"/>
                                    </td>
                                </tr>
                            </table>

                        </s:form>     

                    </div>


                    <div class="display" id="searchdiv">
                        <s:form   id="terminalusersearchForm"  action="XSLcreatTerminalEditView" name="terminalusersearchForm" theme="simple" method="post" >
                            <table border="0">

                                <tr>
                                    <td class="lable">Terminal ID<span class="mandatory"></span></td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:textfield id="terminalId" name="terminalId" cssClass="text_field" /></td>
                                </tr>
                                <tr>
                                    <td class="lable">Status</td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:select  name="statusValue" headerKey="" headerValue="---Select Status ---"
                                               listKey="key" listValue="value"
                                               list="%{statusMap}" id="statusValue" cssClass="dropdown"/></td>
                                </tr>
                                <tr>
                                    <td class="lable">Encryption Status</td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:select  name="encryptionStatusValue" headerKey="" headerValue="---Select Encryption Status ---"
                                               listKey="key" listValue="value"
                                               list="%{encryptionStatusMap}" id="encryptionStatusValue" cssClass="dropdown"/></td>
                                </tr>
                                <tr>
                                    <td class="lable">Non-Encryption Status</td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:select  name="nonEncryptionStatusValue" headerKey="" headerValue="---Select Non-Encryption Status ---"
                                               listKey="key" listValue="value"
                                               list="%{nonEncryptionStatusMap}" id="nonEncryptionStatusValue" cssClass="dropdown"/></td>
                                </tr>
                                <tr>
                                    <td align="left" ></td>
                                    <td align="left" ></td>
                                    <td align="left" >
                                        <s:submit   id="exportXLSbutton" name="exportXLSbutton" value="Export XSL"  cssClass="add" cssStyle="width:100px;" />
                                        <sj:a id="searchbut"  button="true" onClickTopics="onclicksearch"
                                              cssClass="search"   role="button" aria-disabled="false" cssStyle="padding-left: 15px; font-weight:normal !important;margin-top: 0px;" >
                                            Search</sj:a></td>
                                        </td>
                                        <td align="left">



                                        </td>
                                    </tr>
                                </table>
                        </s:form>
                    </div>

                    <div class="table" style="margin-right:50px;">
                        <div id="tablediv">

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
                            <s:url var="listurl" action="ListTerminalEditView"/>
                            <sjg:grid
                                id="gridtable"
                                caption="Registerd Terminals"
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
                                <sjg:gridColumn name="sid" title="sId"   hidden="true" />
                                <sjg:gridColumn name="tid" index="tid" title="TID" align="left" width="15" sortable="false"  /> 
                                <sjg:gridColumn name="mid" index="mid" title="MID" align="left" width="35" sortable="false"/>                    
                                <sjg:gridColumn name="serialNo" index="serialNo" title="Serial No"  align="left" width="25"  sortable="false"/>
                                <sjg:gridColumn name="terminalBrand"  index="terminalBrand" title="Terminal Brand"  width="25" align="center" sortable="false"/>
                                <sjg:gridColumn name="bank"  title="Bank"  width="25" align="center" sortable="false"/>
                                <sjg:gridColumn name="name" title="Name" index="name" align="left" width="15" sortable="false" />
                                <sjg:gridColumn name="location" index="location" title="Location" align="left" width="15" sortable="false"  /> 
                                <sjg:gridColumn name="registerDate" index="registerDate" title="Register Date" align="left" width="35" sortable="false"/>                    
                                <sjg:gridColumn name="encryptionStatus" index="epicTleStatusByStatus.description" title="Encryption Status" align="left" width="25"  sortable="false"/>
                                <sjg:gridColumn name="status"  title="Status"  width="25" align="center" sortable="false"/>
                                <sjg:gridColumn name="tid"  title="Edit"  width="25" align="center" formatter="editformatter"   sortable="false"/>
                                <sjg:gridColumn name="tid"  title="Delete"  width="25" align="center" formatter="deleteformatter"   sortable="false"/>
                            </sjg:grid> 
                        </div> 
                    </div>
                </div>
            </div>

            <jsp:include page="../../footer.jsp" />
        </div><!--End of Wrapper-->
    </body>
</html>
