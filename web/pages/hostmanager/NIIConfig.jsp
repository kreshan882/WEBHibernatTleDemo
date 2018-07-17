<%-- 
    Document   : NIIConfig
    Created on : Apr 7, 2017, 5:28:30 PM
    Author     : thilina_t
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"  %>  
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<html>
    <head>

        <jsp:include page="../../Styles.jsp" />

        <script>

            function NIIConfigformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='javascript:viewniiConf(&#34;" + cellvalue + "&#34;,&#34;" + rowObject.niiConfName + "&#34;)'><i class='fa fa-plus' aria-hidden='true'></i></a>";
            }

            function Statusformatter(cellvalue) {
                if (cellvalue == 1) {
                    return "<i class='fa fa-circle active' aria-hidden='true'></i>";
                } else {
                    return "<i class='fa fa-circle' aria-hidden='true'></i>";
                }
            }

            function deleteformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='javascript:deleteniiConf(&#34;" + cellvalue + "&#34;,&#34;" + rowObject.niiConfName + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/delete_icon.png' /></a>";
            }

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='javascript:editniiConf(&#34;" + cellvalue + "&#34;)'><img src ='${pageContext.request.contextPath}/resources/images/edit_icon.png' /></a>";
            }

            function editniiConf(keyval) {
                var token=$( "input[name='RequstToken']" ).val();
                $('#task').empty();
                var symb = '<i class="fa fa-angle-double-right" aria-hidden="true"></i>';
                var text = ' Edit NII Configuration';
                $('#task').append(symb, text);
`
                $.ajax({
                    url: '${pageContext.request.contextPath}/LoadniiConf',
                    data: {upid: keyval, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $( "input[name='RequstToken']" ).val($stoken);
                        $('#message').empty();
                        $('#upChannel').empty();
                        $("#addForm").hide();
                        $('#NIISearchForm').hide();
                        $("#Updateform").show();
                        $('#upid').val(data.upid);
                        $('#upniiConfName').val(data.upniiConfName);
                        $('#upniiConfDes').val(data.upniiConfDes);                       
                        $('#upStatus').val(data.upStatus);
                        $('#upChannel').append($('<option></option>').val("-1").html("---Select---"));
                        $.each(data.upChannelMap, function (key, value) {
                            $('#upChannel').append($('<option></option>').val(key).html(value));
                        });
                        $('#upChannel').val(data.upChannel);
//                        jQuery("#gridtable").trigger("reloadGrid");
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });
            }

            function facilityPopupClose() {
                $("#viewdialog").dialog('close');
            }

            function resetUpdateForm() {
                var token=$( "input[name='RequstToken']" ).val();
                var upProfileID = $('#upid').val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/LoadniiConf',
                    data: {upid: upProfileID, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $( "input[name='RequstToken']" ).val($stoken);
                        jQuery("#gridtable").trigger("reloadGrid");
//                        $('#message').empty();
                        $('#upniiConfName').val(data.upniiConfName);
                        $('#upniiConfDes').val(data.upniiConfDes);
                        $('#upChannel').val(data.upChannel);
                        $('#upStatus').val(data.upStatus);

//                        $.each(data.ChannelMap, function (key, value) {
//                            $('#upChannel').append($('<option></option>').val(key).html(value));
//                        });
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });

            }

            function confrmDeleteniiConf(keyval) {
                var token=$( "input[name='RequstToken']" ).val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeleteniiConf',
                    data: {DbinId: keyval, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $( "input[name='RequstToken']" ).val($stoken);
                        if (data.isDeleted === true) {
                            $("#deletesuccdialog").dialog('open');
                            $("#deletesuccdialog").html('<b><font size="3" color="green"><center>' + data.dmessage + '</center></font></b>');
                        } else {
                            $("#deleteerrordialog").dialog('open');
                            $("#deleteerrordialog").html('<b><font size="3" color="red"><center>' + data.dmessage + '</center></font></b>');
                        }
                        loadDropdown();
                        jQuery("#gridtable").trigger("reloadGrid");

                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });

            }

            function BinProfilePopupClose() {
                $("#viewdialog").dialog('close');
            }
            function viewniiConf(id, Nii) {
                $("#viewdialog").data('Id', id);
                $("#viewdialog").data('NiiName', Nii).dialog('open');
            }
            $.subscribe('openview', function (event, data) {
                $('#divmsg').empty();
//                resetData();
                var $led = $("#viewdialog");
//                alert($led.data('Id').replace(/ /g,"_"));
                $led.load("AssignniiConf?NiiId=" + $led.data('Id') + "&NiiName=" + $led.data('NiiName').replace(/ /g, "_"));
            });


            $.subscribe('onclicksearch', function (event, data) {
                var searchName = $('#searchName').val();

                $("#gridtable").jqGrid('setGridParam', {postData: {searchName: searchName}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");

            });

            function deleteniiConf(id, name) {
                $("#deleteConfirmDialog").data('id', id);
                $("#deleteConfirmDialog").data('name', name).dialog('open');
                $("#deleteConfirmDialog").html('<br><br><b><font size="3" color="red"><center>Please confirm delete : ' + name);
                return false;
            }


            function backToMain() {
                $('#divmsg').empty();
                $('#Updateform').hide();
                $('#addForm').hide();
                $('#NIISearchForm').show();
                $('#task').empty();
                jQuery("#gridtable").trigger("reloadGrid");
            }

            function clearAddForm() {
                $('#niiConfName').val("");
                $('#niiConfDes').val("");
                $('#channelType').val(-1);
                jQuery("#gridtable").trigger("reloadGrid");
                $("#divmsg").empty();
            }

            function hideInit() {
                $("#addForm").hide();
                $("#Updateform").hide();
            }
            $(document).ready(function () {
               
                hideInit();
                $("#Addbtn").click(function () {
                    $("#NIISearchForm").hide();
                    $("#Updateform").hide();
                    $("#addForm").show();
                    $('#task').empty();
                    var symb = '<i class="fa fa-angle-double-right" aria-hidden="true"></i>';
                    var text = ' Add NII Configuration';
                    $('#task').append(symb, text);
                    loadDropdown();                  
                

            })
            
            })

            function loadDropdown(){
                var token=$( "input[name='RequstToken']" ).val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/LoadDropdownniiConf',
                    data: {load:'load', RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $( "input[name='RequstToken']" ).val($stoken);
                        $('#Channel').empty();                        
                        $('#Channel').append($('<option></option>').val("-1").html("---Select---"));
                        $.each(data.channelMap, function (key, value) {
                            $('#Channel').append($('<option></option>').val(key).html(value));
                        });                        
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                    
                });
            
            }
            function resetForm() {
                $("#niiConfName").val("");
                $("#niiConfDes").val("");
                $("#Channel").val("");
                jQuery("#gridtable").trigger("reloadGrid");
                loadDropdown();
                
                $('#Nii').val("");
                $('#mapNii').val("");
                jQuery("#gridtable1").trigger("reloadGrid");
            }



        </script>

    </head>

    <body style="overflow:hidden">
        <div class="wrapper">

            <jsp:include page="../../header.jsp" /> 

            <!--Body Content-->
            <div class="body_right">
                <div class="heading">
                    <div>
                        <ul class="breadcrumb">
                            <li><s:property value="Module"/></li>
                            <li><i class="fa fa-angle-double-right" aria-hidden="true"></i> 
                                <s:property value="Section"/></li>
                            <li id="task"></li>
                        </ul>
                    </div>
                    <div class="content">
                        <div class="content_highlight"></div>
                        <div class="message">
                            <s:div id="divmsg">
                                <i style="color: red"> <s:actionerror theme="jquery"/></i>
                                <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                            </s:div>
                        </div>

                        <div class="display">
                            <s:form id="NIISearchForm" theme="simple">         
                                <table border="0">
                                    <tr>
                                        <td class="lable">Name</td>
                                        <td class="lable">:</td>
                                        <td><s:textfield name="searchName" id="searchName" cssClass="text_field"/></td>

                                        <td align="right">
                                            <sj:a 
                                                id="searchbut" 
                                                button="true" 
                                                onClickTopics="onclicksearch"  cssClass="search" cssStyle="padding-top:4px;padding-bottom:4px;margin:0px;padding-left: 15px; font-weight:normal !important;"                                     
                                                >Search</sj:a>
                                            <sj:a 
                                                id="Addbtn" 
                                                button="true" 
                                                cssClass="search" cssStyle="padding-top:4px;padding-bottom:4px;margin:0px;padding-left: 15px; font-weight:normal !important;"                                     
                                                >Add</sj:a>
                                            </td>
                                        </tr>
                                    </table>
                                <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                            </s:form>


                            <s:form id="addForm" theme="simple">
                                <table border="0">
                                    <tr>
                                        <td class="lable">NII<span class="mandatory">*</span></td>
                                        <td class="lable">:</td>
                                        <td colspan="2"><s:textfield name="nii" id="nii" cssClass="text_field"/></td>
                                    </tr>
                                    <tr>
                                        <td class="lable">MAP NII<span class="mandatory">*</span></td>
                                        <td class="lable">:</td>
                                        <td colspan="2"><s:textfield name="mapNii" id="mapNii" cssClass="text_field"/></td>
                                    </tr>
                                    <tr>
                                        <td class="lable">TLE Status<span class="mandatory">*</span></td>
                                        <td class="lable">:</td>
                                        <td colspan="2"><s:select  name="tlestatus" id="tlestatus" list="%{tlestatusMap}" headerKey="1" 
                                                   listKey="key" listValue="value" cssClass="dropdown"/></td>
                                    </tr>
                                    
                                </table>

                                <table border="0" style="margin-left: 70px;">

                                    <tr>
                                        <td colspan="4"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"></td>
                                        <td align="right">
                                            <s:url var="saveurl" action="AddniiConf"/>                                   
                                            <sj:submit  href="%{saveurl}" targets="divmsg"  value="Save" button="true" cssClass="add" />  
                                        </td>
                                        <td align="left" width="25px"><s:reset button="true" value="Reset"  cssClass="add" onclick="clearAddForm()"></s:reset></td>
                                        <td align="left" width="25px"><sj:a href="#" name="back" button="true" onclick="backToMain()"  cssClass="add" cssStyle="font-weight: normal;">Back</sj:a> </td>
                                        </tr>
                                    </table>
                                <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                            </s:form>
                        </div>

                        <div id="Updateformdiv"  them="simple">
                            <s:form  theme="simple"  method="post" id="Updateform" cssClass="form_hidden">
                                <table border="0">
                                    <s:hidden name="upid" id="upid" />
                                    <tr>
                                        <td class="lable">Name<span class="mandatory">*</span></td>
                                        <td class="lable">:</td>
                                        <td colspan="2"><s:textfield name="upniiConfName" id="upniiConfName" cssClass="text_field"/></td>
                                    </tr>

                                    <tr>
                                        <td class="lable">Description<span class="mandatory">*</span></td>
                                        <td class="lable">:</td>
                                        <td colspan="2"><s:textfield name="upniiConfDes" id="upniiConfDes" cssClass="text_field"/></td>
                                    </tr>
                                    <tr>
                                        <td class="lable">Channel Name<span class="mandatory">*</span></td>
                                        <td class="lable">:</td>
                                        <td colspan="2"><s:select id="upChannel"  name="upChannel"  headerKey="-1"  headerValue="---Select---"  list="%{upChannelMap}" cssClass="dropdown" /> </td>
                                    </tr>
                                   
                                    <tr>
                                        <td colspan="4"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"></td>
                                        <td align="right">
                                            <s:url var="updateuserurl" action="updateniiConf"/>                                   
                                            <sj:submit  href="%{updateuserurl}"  targets="divmsg" value="Update" button="true" cssClass="add" />
                                            
                                        </td>
                                        <td align="left" width="25px"><s:reset button="true" value="Reset"  cssClass="add" onclick="resetUpdateForm()"></s:reset></td>
                                        <td align="left" width="25px"><sj:a href="#" name="back" button="true" onclick="backToMain()"  cssClass="add" cssStyle="font-weight: normal;">Back</sj:a> </td>
                                        </tr>
                                    </table>
                                <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                            </s:form>
                        </div>

                        <div class="table" style="margin-right:50px;">
                            <div id="tablediv">

                                <sj:dialog 
                                    id="viewdialog" 
                                    buttons="{
                                    'OK':function() { $( this ).dialog( 'close' );}                                    
                                    }" 
                                    autoOpen="false" 
                                    modal="true"                            
                                    width="1000"
                                    height="500"
                                    position="center"
                                    title="NII Assign"
                                    onOpenTopics="openview" 
                                    loadingText="Loading .."
                                    />

                                <sj:dialog 
                                    id="deleteConfirmDialog" 
                                    buttons="{ 
                                    'OK':function() { confrmDeleteniiConf($(this).data('id'));$( this ).dialog( 'close' ); },
                                    'Cancel':function() { $( this ).dialog( 'close' );} 
                                    }" 
                                    autoOpen="false" 
                                    modal="true" 
                                    title="Delete NII Configuration."
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
                                    title="NII Configuration Deleted Successfully." 
                                    width="400"
                                    height="150"
                                    position="center"
                                    />
                                <!-- End delete successfully dialog box -->
                                <!-- Start delete error dialog box -->


                                <s:url var="listurl" action="listniiConf" />

                                <!--caption="Edit and View User Details"-->
                                <sjg:grid
                                    id="gridtable"                                
                                    caption="All NII Configuration"
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
                                    <sjg:gridColumn name="id" index="id" title="id"   hidden="true" />
                                    <sjg:gridColumn name="nii" index="nii" title="NII" align="left" width="150" sortable="true"  /> 
                                    <sjg:gridColumn name="mapNii" index="mapNii" title="MAP NII" align="left" width="200" sortable="true"  /> 
                                    <sjg:gridColumn name="datetime" index="datetime" title="Date" align="center" width="180" sortable="true"/>
                                    <sjg:gridColumn name="id"  title="Assign NII"  width="80" align="center" formatter="NIIConfigformatter" sortable="false"/>
                                    <sjg:gridColumn name="status" index="status" title="Status"  align="center" width="80" formatter="Statusformatter"  sortable="false"/>
                                    <sjg:gridColumn name="id"  title="Edit"  width="80" align="center" formatter="editformatter" sortable="false"/>
                                    <sjg:gridColumn name="id"  title="Delete"  width="80" align="center" formatter="deleteformatter" sortable="false"/>
                                </sjg:grid> 

                            </div> 
                        </div> 
                    </div>
                </div>
            </div>
            <!--End of Body Content-->

            <jsp:include page="../../footer.jsp" />
        </div><!--End of Wrapper-->
    </body>
</html>
