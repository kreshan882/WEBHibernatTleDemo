<%-- 
    Document   : smsProfile
    Created on : Feb 13, 2018, 9:48:19 AM
    Author     : ridmi_g
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"  %>  
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html>
<html>
    <head>

        <jsp:include page="../../Styles.jsp" />

        <script>

            function Assignformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='javascript:viewSMSProfile(&#34;" + cellvalue + "&#34;)'><i class='fa fa-share-square-o' aria-hidden='true'></i></a>";
            }

            function filtersms(cellvalue, options, rowObject) {
                return "<a href='#' onClick='javascript:viewFilterSMS(&#34;" + cellvalue + "&#34;)'><i class='fa fa-share-square-o' aria-hidden='true'></i></a>";
            }

            function Statusformatter(cellvalue) {
                if (cellvalue == 1) {
                    return "<i class='fa fa-circle active' aria-hidden='true'></i>";
                } else {
                    return "<i class='fa fa-circle' aria-hidden='true'></i>";
                }
            }

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' disabled='#vupdate' onClick='javascript:editSMSProfile(&#34;" + cellvalue + "&#34;)'><i class='fa fa-pencil' aria-hidden='true' title='Edit Alert Group'></i></a> <a href='#' disabled='#vdelete' onClick='javascript:deleteBinProfile(&#34;" + cellvalue + "&#34;,&#34;" + rowObject.gn + "&#34;)'><i class='fa fa-trash-o' aria-hidden='true' title='Delete Alert Group'></i></a>";
            }

            function editSMSProfile(keyval) {
                $('.lnk-back').removeClass('hide-element');
                utilityManager.resetMessage();
                $('#task').empty();
                var text = ' Edit Alert Group Profile';
                $('#task').append(text);
                var token = $("input[name='RequstToken']").val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/LoadSMSPrf',
                    data: {upProfileID: keyval, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken = data.token;
                        $("input[name='RequstToken']").val($stoken);
                        utilityManager.resetMessage();
                        $('#addForm').hide();
                        $('#blockBSearchForm').hide();
                        $("#Updateform").show();
                        $('#upProfileID').val(data.upProfileID);
                        $('#upName').val(data.upName);
                        $('#upStatus').val(data.upStatus);
                        jQuery("#gridtablesms").trigger("reloadGrid");
                     
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        if (xhr.responseText.includes("csrfError.jsp")) {
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                        }
                    }
                });
              
                resetForm();
            }

            function facilityPopupClose() {
                $("#viewdialog").dialog('close');
            }

            function resetUpdateForm() {
                var token = $("input[name='RequstToken']").val();
                var upProfileID = $('#upProfileID').val();

                $.ajax({
                    url: '${pageContext.request.contextPath}/LoadSMSPrf',
                    data: {upProfileID: upProfileID, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken = data.token;
                        $("input[name='RequstToken']").val($stoken);
                        jQuery("#gridtablesms").trigger("reloadGrid");
                        utilityManager.resetMessage();
                        $('#userProfileSearchForm').hide();
                        $('#userProfileEditForm').show();
                        $('#upProfileID').val(data.upProfileID);
                        $('#upName').val(data.upName);
                        $('#name').val(data.upName);
                        $('#upStatus').val(data.upStatus);
                        $('#upmoduleList').empty();
                        $('#upmoduleList').append($('<option></option>').val("").html("--Select Module--"));
//                        $("#upName").val("");
//                        $("#upStatus").val(-1);

//                        $.each(data.modulesMap, function (key, value) {
//                            $('#upmoduleList').append($('<option></option>').val(key).html(value));
//                        });
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        if (xhr.responseText.includes("csrfError.jsp")) {
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                        }
                    }
                });

            }

            function confrmDeleteBinProfile(keyval) {
                var token = $("input[name='RequstToken']").val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeleteSMSPrf',
                    data: {DbinId: keyval, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken = data.token;
                        if (data.isDeleted === true) {
                            utilityManager.showMessage('.del-user-msg', data.dmessage, 'successmsg', $stoken);
                        } else {
                            utilityManager.showMessage('.del-user-msg', data.dmessage, 'errormsg', $stoken);
                        }
//                        resetForm();
                        jQuery("#gridtablesms").trigger("reloadGrid");
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        if (xhr.responseText.includes("csrfError.jsp")) {
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                        }
                    }
                });
                resetForm();
            }


            function BinProfilePopupClose() {
                $("#viewdialog").dialog('close');
            }
            function viewSMSProfile(id) {
                $("#viewdialog").data('Id', id).dialog('open');
            }
            $.subscribe('openview', function (event, data) {
                utilityManager.resetMessage();
                var $stoken = $("input[name='RequstToken']").val();
//                resetData();
                var $led = $("#viewdialog");
//                alert($led.data('Id').replace(/ /g,"_"));
                $led.load("AssignInfo?RequstToken=" + $stoken + "&id=" + $led.data('Id'));
            });

            function viewFilterSMS(id) {
                $("#viewdialog1").data('Id', id).dialog('open');
            }
            $.subscribe('openview1', function (event, data) {
                utilityManager.resetMessage();
                var $stoken = $("input[name='RequstToken']").val();
//                resetData();
                var $led = $("#viewdialog1");
//                alert($led.data('Id').replace(/ /g,"_"));
                $led.load("FilterSMS?RequstToken=" + $stoken + "&id=" + $led.data('Id'));
            });


            $.subscribe('onclicksearch', function (event, data) {
                var searchName = $('#searchName').val();

                $("#gridtablesms").jqGrid('setGridParam', {postData: {searchName: searchName}});
                $("#gridtablesms").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtablesms").trigger("reloadGrid");

            });

            function deleteBinProfile(uname, id) {
                utilityManager.resetMessage();
                $("#deleteConfirmDialog1").data('uname', uname);
                $("#deleteConfirmDialog1").data('id', uname).dialog('open');
                $("#deleteConfirmDialog1").html('<p>Please confirm delete : ' + id + "</p>");
                return false;
            }


            function backToMain() {
                utilityManager.resetMessage();
                $('#Updateform').hide();
                $('#addForm').hide();
                $('#blockBSearchForm').show();
                $('#task').empty();
                jQuery("#gridtablesms").trigger("reloadGrid");
            }

            function hideInit() {
                $("#addForm").hide();
                $("#Updateform").hide();
            }
            $(document).ready(function () {
                hideInit();
                $("#Addbtn").click(function () {
                    $('.lnk-back').removeClass('hide-element');
                    utilityManager.resetMessage();
                    $("#blockBSearchForm").hide();
                    $("#Updateform").hide();
                    $("#addForm").show();
                    $('#task').empty();
                    var text = ' Add Alert Group Profile';
                    $('#task').append(text);
                })

            })

            function resetForm() {
             
                $('#gn').val("");
               jQuery("#gridtablesms").trigger("reloadGrid");
             
                jQuery("#gridtable1").trigger("reloadGrid");
            }
            function resetAddForm() {
                $('#gn').val("");
                jQuery("#gridtablesms").trigger("reloadGrid");
                utilityManager.resetMessage();
            }

        </script>

    </head>

    <body style="overflow:hidden">
        <s:set id="vadd" var="vadd"><s:property  value="add" default="true"/></s:set>
        <s:set id="vupdate" var="vupdate"><s:property value="update" default="true"/></s:set>
        <s:set id="vdelete" var="vdelete"><s:property value="delete" default="true"/></s:set>
        <s:set id="vview" var="vview"><s:property value="view" default="true"/></s:set>

            <section class="app-content">
            <jsp:include page="../../header.jsp" />             

            <div class="content innerpage">
                <!-- Breadcrumb begin -->
                <div class="breadcrumb">
                    <s:property value="Module"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i><s:property value="Section"/><i class="fa fa-angle-double-right" aria-hidden="true"></i><span id="task" class="active">Search Alert Group Profile</span>
                </div>
                <!-- End -->

                <!-- Page title begin -->
                <h1 class="page-title"><s:property value="Section"/><a href="#" class="lnk-back hide-element do-nothing"><i class="fa fa-arrow-left" aria-hidden="true"></i> back</a></h1>
                <!-- End -->

                <!-- Search form begin -->
                <div class="content-section search-form" id="blockBSearchForm">
                    <s:form id="SearchForm" theme="simple">
                        <div class="content-data">
                            <h2 class="section-title">Search</h2>
                            <div class="d-row singlecol-row">
                                <label class="left-col form-label">Alert Group Profile</label>
                                <div class="right-col form-field">
                                    <s:textfield name="searchName" id="searchName" cssClass="txt-input width-35"/>
                                    <sj:a 
                                        id="searchbut" 
                                        button="true" 
                                        onClickTopics="onclicksearch" 
                                        cssClass="btn default-button" 
                                        ><i class="fa fa-search" aria-hidden="true"></i> Search</sj:a>
                                    <sj:a 
                                        disabled="#vadd"
                                        id="Addbtn" 
                                        button="true" 
                                        cssClass="btn default-button" 
                                        ><i class="fa fa-plus" aria-hidden="true"></i> Add</sj:a>

                                    </div>
                                </div>
                            </div>
                            <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                    </s:form>
                </div>
                <!-- End -->

                <!-- Data form begin -->
                <div class="content-section data-form" id="addForm">
                    <s:form id="addFillForm" theme="simple">
                        <div class="content-data">
                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg">
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i> <span id="divmsg"></span></div>
                            </div>
                            <!-- End -->

                            <!-- Two colum form row begin -->
                            <div class="d-row">
                                <label class="left-col form-label">Group Name<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield name="gn" id="gn" cssClass="txt-input width-35"/>
                                </div>
                            </div>
                            <!-- End -->

                            <div class="d-row cpanel">
                                <label class="left-col">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="saveurl" action="Addgroup"/>                                   
                                    <div class="btn-wrap lnk-match"><i class="fa fa-floppy-o" aria-hidden="true"></i><sj:submit  href="%{saveurl}" targets="divmsg" value="Save" disabled="#vadd" button="true" cssClass="btn default-button" /></div>  

                                    <div class="btn-wrap lnk-match"><i class="fa fa-times" aria-hidden="true"></i><sj:submit button="true" value="Reset"  cssClass="btn reset-button" onclick="resetAddForm()"  /></div>
                                </div>
                            </div>

                        </div>
                        <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                    </s:form>
                </div>

                <div class="content-section data-form"  id="Updateform" style="display: none;">
                    <s:form  theme="simple"  method="post" id="Updateformdiv" >
                        <div class="content-data">
                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg">
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                            </div>
                            <!-- End -->

                            <!-- Two colum form row begin -->
                            <div class="d-row">
                                <s:hidden name="upProfileID" id="upProfileID" value="1"/>
                                <label class="left-col form-label">Alert Group Profile Name<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield name="upName" id="upName" cssClass="txt-input width-35"/>
                                </div>
                            </div>

                            <div class="d-row">
                                <s:hidden name="Upname" id="Upname" value="1"/>
                                <label class="left-col form-label">Status<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select id="upStatus"  name="upStatus"  headerKey="-1"  headerValue="---Select---"  list="%{upBinStatusMap}" cssClass="ddl-input" />
                                </div>
                            </div>
                            <!-- End -->

                            <div class="d-row cpanel">
                                <label class="left-col">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="updateuserurl" action="updateSMSPrf"/>                                   
                                    <div class="btn-wrap"><i class="fa fa-pencil-square-o" aria-hidden="true"></i><sj:submit  href="%{updateuserurl}" targets="divmsg" disabled="#vupdate" value="Update" button="true" cssClass="btn default-button"/></div>
                                    <div class="btn-wrap"><i class="fa fa-times" aria-hidden="true"></i><sj:submit button="true" value="Reset"  cssClass="btn reset-button" onclick="resetUpdateForm()"  /></div>
                                </div>
                            </div>

                        </div>
                        <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                    </s:form>
                </div>

                <!-- Grid data begin -->
                <div class="content-section">
                    <div class="content-data">
                        <h2 class="section-title">All Alert Group  Profile</h2>
                        <!-- Error and success message panel begin -->
                        <div class="msg-panel del-user-msg" >
                            <div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                        </div>
                        <!-- End -->
                    </div>

                    <div id="tablediv" class="custom-grid">
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
                            title="Group Information"
                            onOpenTopics="openview" 
                            loadingText="Loading .."
                            />
                        <sj:dialog 
                            id="viewdialog1" 
                            buttons="{
                            'OK':function() { $( this ).dialog( 'close' );}                                    
                            }" 
                            autoOpen="false" 
                            modal="true"                            
                            width="1000"
                            height="500"
                            position="center"
                            title="Filter SMS"
                            onOpenTopics="openview1" 
                            loadingText="Loading .."
                            />

                        <sj:dialog 
                            id="deleteConfirmDialog1" 
                            buttons="{ 
                            'OK':function() { confrmDeleteBinProfile($(this).data('id'));$( this ).dialog( 'close' ); },
                            'Cancel':function() { $( this ).dialog( 'close' );} 
                            }" 
                            autoOpen="false" 
                            modal="true" 
                            title="Delete Alert Group Profile."
                            width="400"
                            height="200"
                            position="center"
                            />

                        <s:url var="listurl" action="listsmsProf"/>
                        <!--caption="Edit and View User Details"-->
                        <sjg:grid
                            id="gridtablesms"                                
                            caption="All Added Alert Group Profiles"
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
                            <sjg:gridColumn name="id" title="Id" hidden="true"/>
                            <sjg:gridColumn name="gn" index="profileName" title="Group Name" align="left" width="15" sortable="true"  /> 
                            <sjg:gridColumn name="datetime" index="ceratedDate" title="Created Date" align="center" width="35" sortable="true"/>                    
                            <sjg:gridColumn name="status" index="status" title="Status"  align="center" width="10" formatter="Statusformatter"  sortable="false"/>  
                            <sjg:gridColumn name="id"  title="Add Details"  width="10" align="center" formatter="Assignformatter" sortable="false"/>
                            <sjg:gridColumn name="id"  title="Filter SMS"  width="10" align="center" formatter="filtersms" sortable="false"/>
                            <sjg:gridColumn name="id"  title="Action"  width="10" align="center" formatter="editformatter" sortable="false" cssClass="action-col"/>
                        </sjg:grid> 
                    </div>


                </div>
                <!-- End -->

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
                var text = 'Search Alert Group Profile';
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
