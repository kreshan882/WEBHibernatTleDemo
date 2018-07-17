<%-- 
    Document   : registerUser
    Created on : Feb 13, 2017, 10:42:51 AM
    Author     : danushka_r
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"  %>  
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>   
<!DOCTYPE html>
<html>
    <head>
        <s:set id="vadd" var="vadd"><s:property  value="add" default="true"/></s:set>
        <s:set id="vupdate" var="vupdate"><s:property value="update" default="true"/></s:set>
        <s:set id="vdelete" var="vdelete"><s:property value="delete" default="true"/></s:set>
        <s:set id="vview" var="vview"><s:property value="view" default="true"/></s:set>
        <s:set id="vreset" var="vreset"><s:property value="reset" default="true"/></s:set>
        <s:set id="disst" var="disst"><s:property value="isNewMember" default="true"/></s:set>
            <script>
                var vadd = '${vadd}';
                var vupdate = '${vupdate}';
                var vdelete = '${vdelete}';
                var vreset = '${vreset}';
                var disst = '${disst}';</script>

        <jsp:include page="../../Styles.jsp" />
        <style>
            .ui-button-text-only .ui-button-text {
                ping: 0;
            }
            .ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
                font-weight: normal; 
            }
        </style>
        <script type="text/javascript">
            function resetForm() {
                jQuery("#gridtable").trigger("reloadGrid");
            }

            function resetUserForm() {
                $('#userName').val("");
                $('#password').val("");
                $('#confirmPassword').val("");
                $('#name').val("");
                $('#email').val("");
                $('#upEmail').val("");
                $('#userType').val(-1);
                $('#statusType').val(-1);
                $('#upName').val("");
                $('#upuserName').val("");
                $('#upuserTypeId').val("-1");
                $('#upNewPw').val("");
                $('#upRepetedNewPw').val("");
                jQuery("#gridtable").trigger("reloadGrid");
            }

            function resetFormWithMessage() {
                resetUserForm();
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

            $.subscribe('onclicksearch', function (event, data) {
                utilityManager.resetMessage();
                var searchName = $('#searchName').val();
                var userRole = $('#userRole').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {searchName: searchName, userRole: userRole}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            });
            function editformatter(cellvalue, options, rowObject) {
                var reset_button = "";
                if (rowObject.statusType != 2) {
                    reset_button = "<span name='" + rowObject.userName + "' id='" + rowObject.userName + "' class='test-class'><a style='margin-right:10px;' href='#' disabled='vupdate' title='Reset Password' name='" + rowObject.userName + "' id='" + rowObject.userName + "' onClick='resetPassword(&#34;" + rowObject.userName + "&#34;,&#34;" + rowObject.email + "&#34;)'><i class='fa fa-history' aria-hidden='true'></i></a></span>";
                }
                var edit_button = "<a href='#' disabled='vupdate' title='Edit User' onClick='editUser(&#34;" + cellvalue + "&#34;)'><i class='fa fa-pencil' aria-hidden='true'></i></a>";
                var delete_button = " <a href='#' disabled='vdelete' title='Delete User' onClick='deleteUserInit(&#34;" + rowObject.userName + "&#34;,&#34;" + rowObject.userTypeId + "&#34;)'><i class='fa fa-trash-o' aria-hidden='true'></i></a>";
                var result = "";
                if (vreset == "false") {
                    result = result + reset_button;
                }
                if (vupdate == "false") {
                    result = result + edit_button;
                }
                if (vdelete == "false") {
                    result = result + delete_button;
                }
                return result;
            }

            function Statusformatter(cellvalue) {
                if (cellvalue == 1) {
                    return "<i class='fa fa-circle active' aria-hidden='true'></i>";
                } else if (cellvalue == 2) {
                    return "<i class='fa fa-circle critical' aria-hidden='true'></i>";
                } else {
                    return "<i class='fa fa-circle' aria-hidden='true'></i>";
                }
            }

            function deleteUserInit(uname, utypeid) {
                utilityManager.resetMessage();
                $("#deleteConfirmDialog").data('uname', uname);
                $("#deleteConfirmDialog").data('utypeid', utypeid).dialog('open');
                $("#deleteConfirmDialog").html('<p>Please confirm delete : ' + uname + '</p>');
                return false;
            }

            function deleteUser(uname, utypeid) {
                var token = $("input[name='RequstToken']").val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/deleteusrMng',
                    data: {duserName: uname, duserTypeId: utypeid, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken = data.token;
                        if (data.isDeleted == true) {
                            utilityManager.showMessage('.del-user-msg', data.dmessage, 'successmsg', $stoken);
                        } else {
                            utilityManager.showMessage('.del-user-msg', data.dmessage, 'errormsg', $stoken);
                        }
//                        resetData();
                        resetUserForm();
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        if (xhr.responseText.includes("csrfError.jsp")) {
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                        }
                    }
                });
            }

            function resetData() {
                utilityManager.resetMessage();
                jQuery("#gridtable").trigger("reloadGrid");
            }
            function resetPassword(val, email, e) {
                $("#resetConfirmDialog").data('val', val);
                $("#resetConfirmDialog").data('email', email);
                $("#resetConfirmDialog").data('e', e);
                $("#resetConfirmDialog").html('<p>Are you sure..? do you want to reset the password of user ' + val + '</p>');
                $("#resetConfirmDialog").dialog('open');
            }
            function rp(val, email, e) {

                $('#waiting').hide();
                $('#mdiv').hide();
                var token = $("input[name='RequstToken']").val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/resetusrMng',
                    data: {userName: val, email: email, RequstToken: token},
                    dataType: "json",
                    type: "GET",
                    beforeSend: function () {
                        $('#waiting').show();
                        $("span[name='" + val + "']").empty();
                        $("span[name='" + val + "']").append("<label class='tt-lbl' style='color:red;background-color:#e2e1e1;padding:2px;margin-top:3px;'>Please wait..!</label>");
                    },
                    complete: function () {
                        $('#waiting').hide();
                        $('.test-class > a').css({"display": "inline"});
                        resetForm();
                    },
                    success: function (data) {
                        $stoken = data.token;
                        $("input[name='RequstToken']").val($stoken);
                        if (data.isTempPassSent) {
                            $('#waiting').hide();
                            $('#mdiv').show();
                            $('#mdiv').html('<span style="position:relative;top:5px;left:5px;"><span class="fa fa-check"></span> Temporary password successfully sent to user ' + val + "'" + 's email address</span>')
                            $('#mdiv').css({"background-color": "green", "font-family": "arial"});
                            $('.test-class > a').css({"display": "inline"});
                            resetForm();
                            setTimeout(function () {
                                $('#mdiv').hide();
                            }, 30000);
                        } else {
                            $('#waiting').hide();
                            $('#mdiv').show();
                            $('#mdiv').html('<span style="position:relative;top:5px;left:5px;"><span class="fa fa-remove"></span> ' + data.mailMessage === null ? "" : data.mailMessage + ' Failed to sent temporary password...plase try again later</span>')
                            $('#mdiv').css({"background-color": "red", "font-family": "arial"});
                            $('.test-class > a').css({"display": "inline"});
                            resetForm();
                            setTimeout(function () {
                                $('#mdiv').hide();
                            }, 30000);
                        }
                    },
                    error: function (data) {
                        $('#waiting').hide();
                        $stoken = data.token;
                        $("input[name='RequstToken']").val($stoken);
                        $('#mdiv').show();
                        $('#mdiv').html('<span style="position:relative;top:5px;left:5px;"><span class="fa fa-remove"></span> ' + data.mailMessage + ' Failed to sent temporary password...plase try again later</span>');
                        $('#mdiv').css({"background-color": "red", "font-family": "arial"});
                        $('.test-class > a').css({"display": "inline"});
                        resetForm();
                        setTimeout(function () {
                            $('#mdiv').hide();
                        }, 30000);
                    }
                });
            }
            function editUser(keyval) {
//                alert(disst);

                $('#webuserEditForm').show();
                $('.lnk-back').removeClass('hide-element');
                $('#searchdiv').hide();
                $('#addDiv').hide();
                utilityManager.resetMessage();
                $('#task').empty();
                var text = ' Edit User';
                $('#task').append(text);
                var token = $("input[name='RequstToken']").val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/findusrMng',
                    data: {upuserName: keyval, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $('#upstatus').removeAttr("disabled");
                        $('#upstatus').attr("disabled", data.isNewMember);
                        $stoken = data.token;
                        $("input[name='RequstToken']").val($stoken);
                        $('#webuserEditForm').show();
                        $('#searchdiv').hide();
                        $('#upName').val(data.upName);
                        $('#upuserName').attr('readOnly', true);
                        $('#upuserName').val(data.upuserName);
                        $('#upusrName').val(data.upuserName);
                        $('#upEmail').val(data.upEmail);
                        $('#upuserTypeId').val(data.upuserTypeId);
                        $('#upstatus').val(data.upstatus);
                    },
                    error: function (data) {
                        window.location = "${pageContext.request.contextPath}/logOut.action";
                    }
                });
            }

            function resetUpdateForm() {
                var token = $("input[name='RequstToken']").val();
                utilityManager.resetMessage();
                var keyval = $('#upusrName').val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/findusrMng',
                    data: {upuserName: keyval, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken = data.token;
                        $("input[name='RequstToken']").val($stoken);
                        $('#upName').val(data.upName);
                        $('#upuserName').attr('readOnly', true);
                        $('#upuserName').val(data.upuserName);
                        $('#upuserTypeId').val(data.upuserTypeId);
                        $('#upEmail').val(data.upEmail);
                        $('#upstatus').val(data.upstatus);
                        $('#upNewPw').val("");
                        $('#upRepetedNewPw').val("");
                        document.getElementById("isChecked").checked = false;
                        document.getElementById("upNewPw").disabled = true;
                        document.getElementById("upRepetedNewPw").disabled = true;
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        if (xhr.responseText.includes("csrfError.jsp")) {
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                        }
                    }

                });
                jQuery("#gridtable").trigger("reloadGrid");
            }

            function addUser() {
                utilityManager.resetMessage();
                $('.lnk-back').removeClass('hide-element');
                $('#addDiv').show();
                $('#searchdiv').hide();
                $('#task').empty();
                var text = ' Add User';
                $('#task').append(text);
            }


            $(function () {
                //$('#searchdiv').hide();
                $('#a1').click(function () {
                    $('#searchdiv').show();
                });
            });
            //focus on Enter 
            function keyPress(e) {
                if (e.keyCode === 13) {
                    e.preventDefault(); // Ensure it is only this code that rusn
                    $("#searchbut").click(); // returning false will prevent the event from bubbling up. // returning false will prevent the event from bubbling up.
                }
            }

            function resetSearchForm() {
                $('#searchName').val("");
                $('#userRole').val("")
                $('#divmsg').empty();
                jQuery("#gridtable").trigger("reloadGrid");
            }

        </script>

    </head>

    <body>
        <section class="app-content">
            <jsp:include page="../../header.jsp" /> 

            <!-- Page content begin -->
            <div class="content innerpage">
                <!-- Breadcrumb begin -->
                <div class="breadcrumb">
                    <s:property value="Module"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i> <s:property value="Section"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i><span id="task" class="active">Search User</span>
                </div>
                <!-- End -->




                <h1 class="page-title"><s:property value="Section"/><a href="#" class="lnk-back hide-element do-nothing"><i class="fa fa-arrow-left" aria-hidden="true"></i> back</a></h1>

                <div class="content-section search-form" id="searchdiv">

                    <s:form theme="simple" >
                        <div class="content-data">

                            <h2 class="section-title">Search</h2>

                            <!--                            <div class="d-row singlecol-row">
                                                            <label class="left-col form-label">Name</label>
                                                            <div class="right-col form-field">
                            <s:textfield name="searchName" id="searchName" cssClass="txt-input width-35" onkeypress="keyPress(event)"/>

                            <sj:a 
                                id="searchbut" 
                                button="true"                                             
                                onClickTopics="onclicksearch"    
                                cssClass="btn default-button"
                                onfocus="true"
                                ><i class="fa fa-search" aria-hidden="true"></i> Search</sj:a>

                            <sj:a 
                                disabled="#vadd"
                                id="btnAdd" 
                                button="true"
                                onclick="addUser()"   
                                cssClass="btn default-button"
                                onfocus="true"
                                ><i class="fa fa-plus" aria-hidden="true"></i> Add</sj:a>

                            </div>
                        </div>-->


                                <div class="d-row singlecol-row">
                                    <label class="left-col form-label">Name</label>
                                    <div class="right-col form-field">
                                    <s:textfield name="searchName" id="searchName" cssClass="txt-input width-35" onkeypress="keyPress(event)"/>
                                </div>
                            </div>
                            <div class="d-row singlecol-row">
                                <label class="left-col form-label">User Role</label>
                                <div class="right-col form-field">
                                    <s:textfield name="userRole" id="userRole" cssClass="txt-input width-35" onkeypress="keyPress(event)"/>
                                </div>
                            </div>

                            <div class="d-row cpanel">
                                <label class="left-col">&nbsp;</label>
                                <div class="right-col">
                                    <sj:a 
                                        id="searchbut" 
                                        button="true"                                             
                                        onClickTopics="onclicksearch"    
                                        cssClass="btn default-button"
                                        onfocus="true"
                                        ><i class="fa fa-search" aria-hidden="true"></i> Search</sj:a>

                                    <sj:a 
                                        disabled="#vadd"
                                        id="btnAdd" 
                                        button="true"
                                        onclick="addUser()"   
                                        cssClass="btn default-button"
                                        onfocus="true"
                                        ><i class="fa fa-plus" aria-hidden="true"></i> Add</sj:a>

                                        <button type="submit" value="Reset" onClick="resetSearchForm()" style="height: 26px;" class="btn reset-button"><i class="fa fa-times" aria-hidden="true"></i> Reset </button>
                                    </div>
                                </div>

                            </div>
                            <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>               
                    </s:form>
                </div>


                <!-- Data form begin -->
                <div class="content-section data-form" id="addDiv" style="display: none">

                    <s:form theme="simple" method="post" name="adduserkk" id="adduserkkk" >
                        <div class="content-data">

                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg" > 
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                            </div>
                            <!-- End -->

                            <!-- Two colum form row begin -->
                            <div class="d-row">
                                <label class="left-col form-label">Name<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="name" name="name" cssClass="txt-input width-35" maxLength="30"/>
                                </div>
                            </div>
                            <!-- End -->

                            <div class="d-row">
                                <label class="left-col form-label">User Name<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="userName" name="userName" cssClass="txt-input width-35" maxLength="20"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Email <sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="email" name="email" cssClass="txt-input width-35" maxLength="200"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Password<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:password id="password" name="password" cssClass="txt-input width-35"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Repeat Password<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:password id="confirmPassword" name="confirmPassword" cssClass="txt-input width-35"/>
                                </div>
                            </div>	
                            <div class="d-row">
                                <label class="left-col form-label">User Profile<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select id="userType"  name="userType"  headerKey="-1"  headerValue="---Select---"  list="%{userTypeMap}" cssClass="ddl-input" />
                                </div>
                            </div>

                            <!-- Tow column form button panel begin -->		
                            <div class="d-row cpanel">
                                <label class="left-col">&nbsp;</label>
                                <div class="right-col">
                                    <!--<button id="btnSubmit" type="submit" class="btn default-button"><i class="fa fa-floppy-o" aria-hidden="true"></i> save</button>-->
                                    <s:url var="saveurl" action="addusrMng"/>                                   
                                    <div class="btn-wrap lnk-match"><i class="fa fa-floppy-o" aria-hidden="true"></i><sj:submit  href="%{saveurl}"  targets="divmsg" value="Save" button="true" cssClass="btn default-button searchicon" disabled="#vadd"/></div>
                                    <div class="btn-wrap lnk-match"><i class="fa fa-times" aria-hidden="true"></i><sj:submit  button="true" onclick="resetFormWithMessage()"  value="Reset" cssClass="btn reset-button"/></div>
                                </div>
                            </div>
                            <!-- End -->
                        </div>
                        <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                    </s:form>
                </div>

                <s:form theme="simple" method="post" id="webuserEditForm" cssClass="content-section data-form" cssStyle="display: none">
                    <div class="content-data">
                        <!-- Error and success message panel begin -->
                        <div class="msg-panel add-form-msg" >
                            <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                        </div>
                        <!-- End -->

                        <!-- Two colum form row begin -->
                        <div class="d-row">
                            <label class="left-col form-label">Name<sup class="required">*</sup></label>
                            <div class="right-col form-field">
                                <s:textfield name="upName" id="upName" cssClass="txt-input width-35"/>
                            </div>
                        </div>
                        <div class="d-row">
                            <label class="left-col form-label">User Name<sup class="required">*</sup></label>
                            <div class="right-col form-field">
                                <s:hidden name="upusrName" id="upusrName" cssClass="txt-input width-35"/>
                                <s:textfield name="upuserName" id="upuserName" cssClass="txt-input width-35"/>
                            </div>
                        </div>
                        <div class="d-row">
                            <label class="left-col form-label">Email<sup class="required">*</sup></label>
                            <div class="right-col form-field">
                                <s:textfield name="upEmail" id="upEmail" cssClass="txt-input width-35"/>
                            </div>
                        </div>
                        <div class="d-row">
                            <label class="left-col form-label">User Profile<sup class="required">*</sup></label>
                            <div class="right-col form-field">
                                <s:select  name="upuserTypeId" headerKey="-1" 
                                           headerValue="---Select User Type ---" listKey="key" listValue="value"
                                           list="%{userTypeMap}" id="upuserTypeId" cssClass="ddl-input"/>
                            </div>
                        </div>
                        <!--                        <div class="d-row">
                                                    <label class="left-col form-label">Password Change<sup class="required">*</sup></label>
                                                    <div class="right-col form-field">
                        <%--<s:checkbox label="checkboxpw" name="isChecked" id="isChecked" onclick="changeAction()" />--%> 
                    </div>
                </div>-->
                        <!--                        <div class="d-row">
                                                    <label class="left-col form-label">New Password<sup class="required">*</sup></label>
                                                    <div class="right-col form-field">
                        <%--<s:password name="upNewPw" id="upNewPw" cssClass="txt-input width-35" disabled="true"/>--%>
                    </div>
                </div>-->
                        <!--                        <div class="d-row">
                                                    <label class="left-col form-label">Repeat New Password<sup class="required">*</sup></label>
                                                    <div class="right-col form-field">
                        <%--<s:password name="upRepetedNewPw" id="upRepetedNewPw" cssClass="txt-input width-35" disabled="true"/>--%>
                    </div>
                </div>-->
                        <div class="d-row">
                            <label class="left-col form-label">Status<sup class="required">*</sup></label>
                            <div class="right-col form-field">
                                <s:select id="upstatus"  name="upstatus"  headerKey="-1"  
                                          headerValue="---Select---"  
                                          list="%{statusTypeMap}" 
                                          cssClass="ddl-input" />
                            </div>
                        </div>
                        <div class="d-row">
                            <label class="left-col form-label">(Mandatory fields are marked with<sup class="required">*</sup>)</label>
                        </div>

                        <div class="d-row cpanel">
                            <label class="left-col">&nbsp;</label>
                            <div class="right-col">
                                <s:url var="updateuserurl" action="updateusrMng"/>                                   
                                <div class="btn-wrap"><i class="fa fa-pencil-square-o" aria-hidden="true"></i><sj:submit  href="%{updateuserurl}"  targets="divmsg" value="Update" button="true" cssClass="btn default-button" disabled="#vupdate"/></div>
                                <div class="btn-wrap"><i class="fa fa-times" aria-hidden="true"></i><sj:submit button="true" value="Reset" onClick="resetUpdateForm()" cssClass="btn reset-button"/></div>
                            </div>
                        </div>
                    </div>

                    <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                </s:form>
                <!-- End -->
                <!-- Grid data begin -->
                <div class="content-section">
                    <div id="mdiv" style="height: 25px;margin: 10px;border-radius: 4px;font-size: 15px;"></div>
                    <div id="waiting" style="font-family: arial;margin: 10px;border-radius: 4px;height: 25px;font-size: 15px;background-color: #32a9c5"><span style="top:5px;position: relative;left: 5px;"><span class="fa fa-hourglass-end" ></span>  Please wait...</span></div>
                    <div class="content-data">
                        <h2 class="section-title">All registered users</h2>
                        <!-- Error and success message panel begin -->



                        <div class="msg-panel del-user-msg" >
                            <div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                        </div>
                        <!-- End -->
                    </div>
                    <sj:dialog 
                        id="resetConfirmDialog" 
                        buttons="{ 
                        'OK':function() { rp($(this).data('val'),$(this).data('email'),$(this).data('e'));$( this ).dialog( 'close' ); },
                        'Cancel':function() { $( this ).dialog( 'close' );} 
                        }" 
                        autoOpen="false" 
                        modal="true" 
                        title="User Password Reset Confirmation message"
                        width="400"
                        position="center"
                        />

                    <sj:dialog 
                        id="deleteConfirmDialog" 
                        buttons="{ 
                        'OK':function() { deleteUser($(this).data('uname'),$(this).data('utypeid'));$( this ).dialog( 'close' ); },
                        'Cancel':function() { $( this ).dialog( 'close' );} 
                        }" 
                        autoOpen="false" 
                        modal="true" 
                        title="Delete User Confirmation"
                        width="400"
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

                    <div id="tablediv" class="custom-grid">

                        <s:url var="listurl" action="listusrMng"/>
                        <!--caption="Edit and View User Details"-->
                        <sjg:grid
                            id="gridtable"                                
                            caption="All Registered User"
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
                            <sjg:gridColumn name="userTypeId" title="userTypeId" hidden="true"/>
                            <sjg:gridColumn name="name" index="name" title="Name" align="left" sortable="true"  /> 
                            <sjg:gridColumn name="userName" index="username" title="User Name" align="left" sortable="true"/>                    
                            <sjg:gridColumn name="email" index="email" title="Email" align="left" sortable="true"/>                    
                            <sjg:gridColumn name="userType" index="epicTleUserProfile.description" title="User Role"  align="left" sortable="true"/>
                            <sjg:gridColumn name="statusType" index="epicTleStatus" title="Status" align="center" formatter="Statusformatter" width="30" sortable="true"/>
                            <sjg:gridColumn name="date" index="createdate" title="Date" align="center" width="100" sortable="true"/>
                            <sjg:gridColumn name="userName"  title="Action" align="center" formatter="editformatter" sortable="false" width="150" cssClass="action-col"/>
                            <%--<sjg:gridColumn name="userName"  title="Delete"  width="10" align="center" formatter="deleteformatter" sortable="true"/>--%>
                        </sjg:grid> 
                    </div>

                </div>
                <!-- End -->

                <!--End of Body Content-->
            </div>
            <jsp:include page="../../footer.jsp" />

        </section>

        <script type="text/javascript">
            $(document).ready(function () {
                $('#mdiv').hide();
                $('#waiting').hide();
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
        </script>
        <!-- End -->
    </body>
</html>