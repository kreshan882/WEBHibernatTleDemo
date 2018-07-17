<%-- 
    Document   : editAndViewUserProfile
    Created on : Oct 31, 2014, 9:14:31 AM
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

            .ui-jqgrid-sortable{
                font-weight: normal !important;
            }

            .ui-button-text-only .ui-button-text {
                padding: 0;
            }
            .ui-button, ui-widget ui-state-default, ui-button-text-only {
                font-family: arial;
                font-size: 13px;
                margin-top: 5px;
                font-weight: normal !important; 
                text-decoration: none;
            }
            .ui-button, ui-widget ui-state-default, ui-button-text-only {
                font-family: arial; 
                /* font-size: 13px; */
                /*margin-top: 5px;*/
                font-weight: normal !important; 
                text-decoration: none;
                border-radius: 14px;
                border:0px solid #1d5cd6 !important;
                font-weight:normal !important;
                padding-top:3px !important;
            }

            .comboBox{
                border: 1px solid #656363;
                height: 220px;
                width: 220px;
                box-shadow: 0 0 8px #e4e2e1 inset;
                /*transition: 500ms all ease;*/
                padding: 3px 3px 3px 3px;
                margin-top: 3px;
                margin-bottom: 3px;
            }
            .comboBox:hover,
            .comboBox:focus{
                box-shadow: 0 0 8px #ffffff inset;
                box-shadow: 0 0 8px #9f9e9e;
            }
        </style>
        <script type="text/javascript">

            function deleteformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='deleteUserProfileInit(&#34;" + cellvalue + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/delete_icon.png' /></a>";
            }

            function editformatter(cellvalue, options, rowObject) {
//                alert(cellvalue);
                return "<a href='#' onClick='javascript:editUserProfile(&#34;" + cellvalue + "&#34;)'><img src ='${pageContext.request.contextPath}/resources/images/edit_icon.png' /></a>";
            }


            function imageformatter(cellvalue, options, rowObject) {
                if (cellvalue == 1) {
                    var html = "<img src='${pageContext.request.contextPath}/resources/images/iconActive.png' />";
                } else {
                    var html = "<img src= '${pageContext.request.contextPath}/resources/images/iconInactive.png' />";
                }

                return html;
            }

            //load update user form
            function editUserProfile(keyval) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/LoadUserProfileEditView',
                    data: {upProfileID: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $('#message').empty();
                        $('#userProfileSearchForm').hide();
                        $('#userProfileEditForm').show();
                        $('#upProfileID').val(data.upProfileID);
                        $('#upName').val(data.upName);
                        $('#name').val(data.upName);
                        $('#upStatus').val(data.upStatus);
                        $('#upmoduleList').empty();
                        $('#upmoduleList').append($('<option></option>').val("").html("--Select Module--"));

                        $.each(data.modulesMap, function (key, value) {
                            $('#upmoduleList').append($('<option></option>').val(key).html(value));
                        });


                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });
            }

            function toleft2() {
                $("#newBox2 option:selected").each(function () {
                    $("#currentBox2").append($('<option>', {
                        value: $(this).val(),
                        text: $(this).text()
                    }));
                    $(this).remove();
                });
            }
            ;
            function toright2() {
                $("#currentBox2 option:selected").each(function () {

                    $("#newBox2").append($('<option>', {
                        value: $(this).val(),
                        text: $(this).text()
                    }));
                    $(this).remove();
                });
            }
            function toleftall2() {
                $("#newBox2 option").each(function () {

                    $("#currentBox2").append($('<option>', {
                        value: $(this).val(),
                        text: $(this).text()
                    }));
                    $(this).remove();
                });
            }
            function torightall2() {
                $("#currentBox2 option").each(function () {

                    $("#newBox2").append($('<option>', {
                        value: $(this).val(),
                        text: $(this).text()
                    }));
                    $(this).remove();
                });
            }

            function loadPageModule(keyval) {
                if (keyval == null) {
                    keyval = $('#upmoduleList').val();
                    $('#upmoduleList').val("--Select Module--");
                    var temp = true;
                }
                $('#newBox2 option').prop('selected', true);
                $('#currentBox2 option').prop('selected', true);
                var upProfileID = $('#upProfileID').val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/getPageMapUserProfileEditView',
                    data: {upProfileID: upProfileID, selectModule: keyval, selectedpages: $('#newBox2').val()},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $('#currentBox2').empty();
                        $('#newBox2').empty();

                        $('#upProfileID').val(data.upProfileID);

                        $.each(data.allPageMap, function (val, text) {
                            $('#currentBox2').append($('<option></option>').val(val).html(text));
                        });
                        $.each(data.alreadyAcsessPageMap, function (val, text) {
                            $('#newBox2').append($('<option></option>').val(val).html(text));
                        });
                        jQuery("#gridtable").trigger("reloadGrid");
                        if (temp) {
                            $('#upmoduleList').val("--Select Module--");
                            $("#currentBox2 option").remove();
                            $("#newBox2 option").remove();
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


            function backToMain() {
                $('#message').empty();
                $('#userProfileEditForm').hide();
                $('#userProfileSearchForm').show();
                $("#currentBox2 option").remove();
                $("#newBox2 option").remove();
                jQuery("#gridtable").trigger("reloadGrid");
            }

            //reset form
            function resetForm() {

                $('#upName').val("");
                $('#upmoduleList').append($('<option></option>').val("").html("--Select Module--"));
                $('#upStatus').val("-1");
                $('#currentBox2').empty();
                $('#newBox2').empty();
                $('#userProfileSearchForm').hide();
                $('#userProfileEditForm').show();
                $( "#formUpdate" ).load( "pages/userprofile/editAndViewUserProfile" );
                jQuery("#gridtable").trigger("reloadGrid");
            }


            function resetUpdateForm() {
                var upProfileID = $('#upProfileID').val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/LoadUserProfileEditView',
                    data: {upProfileID: upProfileID},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $('#message').empty();
                        $('#userProfileSearchForm').hide();
                        $('#userProfileEditForm').show();
                        $('#upProfileID').val(data.upProfileID);
                        $('#upName').val(data.upName);
                        $('#name').val(data.upName);
                        $('#upStatus').val(data.upStatus);
                        $('#upmoduleList').empty();
                        $('#upmoduleList').append($('<option></option>').val("").html("--Select Module--"));

                        $.each(data.modulesMap, function (key, value) {
                            $('#upmoduleList').append($('<option></option>').val(key).html(value));
                        });
                        $("#currentBox2 option").remove();
                        $("#newBox2 option").remove();


                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });

            }

            function deleteUserProfileInit(keyval) {
                $('#message').empty();
                $("#deletedialog").data('keyval', keyval).dialog('open');
                $("#deletedialog").html('<br><br><b><font size="3" color="red"><center>Please confirm deletion of Profile Id : ' + keyval + ' .');
                return false;
            }


            function deleteUserProfile(keyval) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeleteUserProfileEditView',
                    data: {profileID: keyval},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        if (data.delsuccess == 1) {
                            $("#deletesuccdialog").dialog('open');
                            $("#deletesuccdialog").html(data.message);
                        } else {
                            $("#deleteerrordialog").dialog('open');
                            $("#deleteerrordialog").html(data.message);
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

            //send data to table
            $.subscribe('onclicksearch', function (event, data) {
                var searchName = $('#searchName').val();

                $("#gridtable").jqGrid('setGridParam', {postData: {searchName: searchName}});
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
                <div class="heading"><div class="underline">Edit And View User Profile</div></div>
                <div class="content">
                    <div class="content_highlight"></div>
                    <div class="message">
                        <s:div id="message">
                            <i style="color: red">  <s:actionerror theme="jquery"/></i>
                            <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                        </s:div>
                    </div>
                    <div class="display">
                        <s:form id="userProfileSearchForm" theme="simple">         
                            <table border="0">
                                <tr>
                                    <td class="lable">Name</td>
                                    <td class="lable">:</td>
                                    <td><s:textfield name="searchName" id="searchName" cssClass="text_field"/></td>

                                    <td align="right"><sj:a 
                                            id="searchbut" 
                                            button="true" 
                                            onClickTopics="onclicksearch"  cssClass="search" cssStyle="padding-top:4px;padding-bottom:4px;margin:0px;padding-left: 15px; font-weight:normal !important;"                                     
                                            >Search</sj:a></td>
                                    </tr>
                                </table>
                        </s:form>
                        
                        
                        
                    </div>
                    <div id="formUpdate"  class="display">
                        <s:form  theme="simple"  action="UpdateUserProfileEditView" method="post" id="userProfileEditForm" cssClass="form_hidden">

                            <table border="0">
                                <s:hidden name="upProfileID" id="upProfileID" value="1"/>
                                <tr>
                                    <td class="lable">Name<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:textfield name="upName" id="upName" cssClass="text_field"/></td>
                                </tr>
                                <s:hidden name="name" id="name" value="1"/>

                                <tr>
                                    <td class="lable">Status<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td colspan="2">
                                        <s:select id="upStatus" name="upStatus" headerKey="-1" headerValue="---Select---"  
                                                  list="%{upStatusMap}" cssClass="dropdown"/></td>
                                </tr>
                                <tr>
                                    <td class="lable">Modules<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td colspan="2">
                                        <s:select id="upmoduleList" name="upmoduleList" headerKey="" headerValue="---Select---"  
                                                  list="%{modulesMap}" 
                                                  onchange="loadPageModule(this.value)" cssClass="dropdown"/></td>
                                </tr>
                            </table>

                            <table border="0" style="margin-left: 47px;white-space: nowrap;">

                                <tr>
                                    <td rowspan="4" align="right"><s:select multiple="true" name="currentBox2" list="%{allPageMap}"  id="currentBox2" ondblclick="toright2()"   cssClass="comboBox" /></td>
                                    <td valign="bottom" width="20px"><sj:a  id="right2" onClick="toright2()" button="true"  cssClass="arrow" cssStyle="padding: 4px 0px;"> > </sj:a> </td>
                                    <td rowspan="4" colspan="3"><s:select multiple="true"  name="newBox2" id="newBox2" list="%{alreadyAcsessPageMap}" ondblclick="toleft2()"  cssClass="comboBox" /></td>
                                </tr>
                                <tr>
                                    <td height="24px"><sj:a  id="rightall2" onClick="torightall2()" button="true"  cssClass="arrow" cssStyle="padding: 4px 0px;"> >> </sj:a></td>
                                    </tr>
                                    <tr>
                                        <td height="24px"><sj:a  id="left2" onClick="toleft2()" button="true"  cssClass="arrow" cssStyle="padding: 4px 0px;"><</sj:a></td>
                                    </tr>
                                    <tr>
                                        <td valign="top"><sj:a  id="leftall2" onClick="toleftall2()" button="true"  cssClass="arrow" cssStyle="padding: 4px 0px;"><<</sj:a></td>
                                    </tr>
                                    <tr>
                                        <td colspan="5" style="padding-left:20px;"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                    </tr>
                                    <tr>
                                        <td></td>
                                        <td colspan="2" align="right">
                                        <s:url var="updateurl" action="UpdateUserProfileEditView"/>                                       
                                        <sj:submit 
                                            id="updatebutton"
                                            button="true"
                                            href="%{updateurl}"
                                            value="Update" 
                                            targets="message"  
                                            cssClass="add"
                                            onclick="loadPageModule()"
                                            cssStyle="font-weight: normal;"
                                            />
                                    </td>
                                    <td align="left" width="20px">
                                        <sj:a href="#" name="back" button="true" onclick="backToMain()"  cssClass="add" cssStyle="font-weight: normal;">Back</sj:a> 
                                        </td>
                                        <td align="right" width="20px">
                                        <sj:submit button="true" value="Reset" onClick="resetUpdateForm()" cssClass="add"  cssStyle="font-weight: normal;"/>
                                    </td>

                                </tr>
                            </table>

                        </s:form>
                    </div>

                    <div class="table">
                        <div id="tablediv">
                            <sj:dialog 
                                id="deletedialog" 
                                buttons="{ 
                                'OK':function() { deleteUserProfile($(this).data('keyval'));$( this ).dialog( 'close' ); },
                                'Cancel':function() { $( this ).dialog( 'close' );} 
                                }" 
                                autoOpen="false" 
                                modal="true" 
                                title="Delete User Profile."
                                width="400"
                                height="200"
                                position="center"
                                />

                            <sj:dialog 
                                id="deletesuccdialog" 
                                buttons="{
                                'OK':function() { $( this ).dialog( 'close' );}
                                }"  
                                autoOpen="false" 
                                modal="true" 
                                title="User Profile Deleted Successfully." 
                                width="400"
                                height="150"
                                position="center"
                                />
                            <!-- End delete successfully dialog box -->
                            <!-- Start delete error dialog box -->
                            <sj:dialog 
                                id="deleteerrordialog" 
                                buttons="{
                                'OK':function() { $( this ).dialog( 'close' );}                                    
                                }" 
                                autoOpen="false" 
                                modal="true" 
                                title="User Profile Delete Error. "
                                width="400"
                                height="150"
                                position="center"
                                />
                            <s:url var="listurl" action="ListUserProfileEditView"/>
                            <sjg:grid
                                id="gridtable"
                                caption="Edit and View User Profile"
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
                                <sjg:gridColumn name="profileID" index="PROFILE_ID" title="Profile ID" align="center" width="20" hidden="true"/>                    
                                <sjg:gridColumn name="name" index="description" title="Name"  align="left" width="60"  sortable="true"/>
                                <sjg:gridColumn name="status" index="STATUS" title="Status" align="center" width="30" sortable="true" />   
                                <sjg:gridColumn name="regDate" index="createdate" title="Create Date" align="left"  width="60"  sortable="true"/>
                                <sjg:gridColumn name="profileID" index="profileID" title="Edit" align="center" width="30" align="center"  formatter="editformatter" sortable="false" />
                                <sjg:gridColumn name="profileID" index="profileID" title="Delete" align="center" width="30" align="center"   formatter="deleteformatter" sortable="false" />

                            </sjg:grid>
                        </div>   
                    </div>
                </div>
            </div>
            <!--End of Body Content-->


            <jsp:include page="../../footer.jsp" />
        </div><!--End of Wrapper-->
    </body>
</html>
