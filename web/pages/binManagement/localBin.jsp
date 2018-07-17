<%-- 
    Document   : LocalBin
    Created on : Mar 21, 2017, 10:49:39 AM
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

            function editformatter(cellvalue, options, rowObject) {
                if (vdelete == 'false') {
                    return "<a href='#' disabled='#vdelete' onClick='javascript:deleteBinProfile(&#34;" + cellvalue + "&#34;,&#34;" + rowObject.bin + "&#34;,&#34;" + rowObject.category_code + "&#34;)'><i class='fa fa-trash-o' aria-hidden='true' title='Delete Bank BIN'></i></a>";
                } else {
                    return "";
                }

            }

            function confrmDeleteBinProfile(keyval, category_code) {
                var token = $("input[name='RequstToken']").val();
                $('.add-form-msg').hide();
                $('.add-form-msg1').hide();
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeletelocalBPrf',
                    data: {Bin: keyval, category_code: category_code, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken = data.token;
                        if (data.isDeleted === true) {
                            if (data.category_code === 2) {
                                $('.add-form-msg').hide();
                                $('.add-form-msg1').hide();
                                utilityManager.showMessage('.del-user-msg1', data.dmessage, 'successmsg', $stoken);
                                resetForm1();
                            } else {
                                $('.del-user-msg1').hide();
                                $('.add-form-msg').hide();
                                $('.add-form-msg1').hide();
                                utilityManager.showMessage('.del-user-msg', data.dmessage, 'successmsg', $stoken);
                                resetAddForm();
                            }

//                            resetForm();
                        } else {
                            if (data.category_code === 2) {
                                $('.add-form-msg').hide();
                                $('.add-form-msg1').hide();
                                utilityManager.showMessage('.del-user-msg1', data.dmessage, 'successmsg', $stoken);
                                resetAddForm1();
                            } else {
                                $('.del-user-msg1').hide();
                                $('.add-form-msg').hide();
                                $('.add-form-msg1').hide();
                                utilityManager.showMessage('.del-user-msg', data.dmessage, 'errormsg', $stoken);
                                resetForm1();
                            }
                        }
//                        resetForm();
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        if (xhr.responseText.includes("csrfError.jsp")) {
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                        }
                    }
                });

            }


            $.subscribe('onclicksearch', function (event, data) {
                var searchName = $('#SearchLocalBIN').val();

                $("#gridtable").jqGrid('setGridParam', {postData: {SearchLocalBIN: searchName}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");

            });
            $.subscribe('onclicksearch1', function (event, data) {
                var searchName = $('#SearchLocalBIN1').val();

                $("#gridtable2").jqGrid('setGridParam', {postData: {SearchLocalBIN: searchName}});
                $("#gridtable2").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable2").trigger("reloadGrid");

            });

            function deleteBinProfile(uname, bin, category_code) {
                utilityManager.resetMessage();
                $("#deleteConfirmDialog").data('uname', uname);
                $("#deleteConfirmDialog").data('category_code', category_code);
                $("#deleteConfirmDialog").data('id', bin).dialog('open');
                $("#deleteConfirmDialog").html('<p>Please confirm delete : ' + uname + "</p>");
                return false;
            }

            function hideInit() {
                $("#addForm").hide();
                $("#Updateform").hide();
                $("#addForm1").hide();
            }

            $(document).ready(function () {
                hideInit();
                $("#Addbtn").click(function () {
                       $('.del-user-msg1').hide();
                    $("#lb").click();
                    $('#lb1').removeClass('hide-element');
                    utilityManager.resetMessage();
                    $("#blockBSearchForm").hide();
                    $("#Updateform").hide();
                    $("#addForm").show();
                    $('#task').empty();
                    var text = ' Add Bank BIN';
                    $('#task').append(text);
                });
                $("#Addbtn1").click(function () {
                       $('.del-user-msg1').hide();
                    $("#lb1").click();
                    $('#lb').removeClass('hide-element');
                    utilityManager.resetMessage();
                    $("#blockBSearchForm1").hide();
                    $("#Updateform").hide();
                    $("#addForm1").show();
                    $('#task').empty();
                    var text = ' Add System Block BIN';
                    $('#task').append(text);
                });

            });

            function resetForm() {
  utilityManager.resetMessage();
                resetForm1();
                $('#Bin').val("");
                jQuery("#gridtable").trigger("reloadGrid");
            }
            function resetForm1() {
                $('#Bin1').val("");
                jQuery("#gridtable2").trigger("reloadGrid");
                utilityManager.resetMessage();
            }
            function resetAddForm() {
                $('#Bin').val("");
                jQuery("#gridtable").trigger("reloadGrid");
//                utilityManager.resetMessage();
            }
            function resetAddForm1() {
                $('#Bin1').val("");
                jQuery("#gridtable2").trigger("reloadGrid");
                utilityManager.resetMessage();
            }
            function backToMain() {
                utilityManager.resetMessage();
                $('#Updateform').hide();
                $('#addForm').hide();
                $('#blockBSearchForm').show();
                $('#task').empty();
                jQuery("#gridtable").trigger("reloadGrid");
            }
            function backToMain1() {
                utilityManager.resetMessage();
//                $('#Updateform').hide();
                $('#addForm1').hide();
                $('#blockBSearchForm1').show();
//                $('#task').empty();
                jQuery("#gridtable2").trigger("reloadGrid");
            }

        </script>

    </head>

    <body style="overflow:hidden">
        <s:set id="vadd" var="vadd"><s:property  value="add" default="true"/></s:set>
        <s:set id="vdelete" var="vdelete"><s:property value="delete" default="true"/></s:set>
        <s:set id="vview" var="vview"><s:property value="view" default="true"/></s:set>
            <script>
                var vadd = '${vadd}';
                var vdelete = '${vdelete}';
        </script>
        <section class="app-content">
            <jsp:include page="../../header.jsp" />             

            <div class="content innerpage">
                <!-- Breadcrumb begin -->
                <div class="breadcrumb">
                    <s:property value="Module"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i><s:property value="Section"/><i class="fa fa-angle-double-right" aria-hidden="true"></i><span id="task" class="active">Search Bank BIN Profile</span>
                </div>
                <!-- End -->

                <!-- Page title begin -->
                <h1 class="page-title"><s:property value="Section"/><a href="#" id="lb1" class="lnk-back hide-element do-nothing"><i class="fa fa-arrow-left" aria-hidden="true"></i> back</a></h1>
                <!-- End -->

                <!-- Search form begin -->
                <div class="content-section search-form" id="blockBSearchForm">
                    <s:form id="SearchForm" theme="simple">
                        <div class="content-data">
                            <h2 class="section-title">Search</h2>
                            <div class="d-row singlecol-row">
                                <label class="left-col form-label">Bank BIN Profile</label>
                                <div class="right-col form-field">
                                    <s:textfield name="SearchLocalBIN" maxLength="6" id="SearchLocalBIN" cssClass="txt-input width-35"/>
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
                                <label class="left-col form-label">Local BIN Name<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield name="Bin" id="Bin" maxLength="6" cssClass="txt-input width-35"/>
                                </div>
                            </div>
                            <!-- End -->

                            <div class="d-row cpanel">
                                <label class="left-col">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="saveurl" action="AddlocalBPrf"/>                                   
                                    <div class="btn-wrap lnk-match"><i class="fa fa-floppy-o" aria-hidden="true"></i><sj:submit  href="%{saveurl}" targets="divmsg" value="Save" disabled="#vadd" button="true" cssClass="btn default-button" /></div>  

                                    <div class="btn-wrap lnk-match"><i class="fa fa-times" aria-hidden="true"></i><sj:submit button="true" value="Reset"  cssClass="btn reset-button" onclick="resetAddForm()"  /></div>
                                </div>
                            </div>

                        </div>
                        <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>               
                    </s:form>
                </div>

                <!-- Grid data begin -->
                <div class="content-section">
                    <div class="content-data">
                        <h2 class="section-title">All Bank Bin</h2>
                        <!-- Error and success message panel begin -->
                        <div class="msg-panel del-user-msg" >
                            <div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                        </div>
                        <!-- End -->
                    </div>


                    <div id="tablediv1" class="custom-grid">

                        <sj:dialog 
                            id="deleteConfirmDialog" 
                            buttons="{ 
                            'OK':function() { confrmDeleteBinProfile($(this).data('id'),$(this).data('category_code'));$( this ).dialog( 'close' ); },
                            'Cancel':function() { $( this ).dialog( 'close' );} 
                            }" 
                            autoOpen="false" 
                            modal="true" 
                            title="Delete Bank Bin."
                            width="400"
                            height="200"
                            position="center"
                            />

                        <!-- End delete successfully dialog box -->
                        <!-- Start delete error dialog box -->
                        <s:url var="listurl" action="listlocalBPrf" />

                        <!--caption="Edit and View User Details"-->
                        <sjg:grid
                            id="gridtable"                                
                            caption="All Bank Bin"
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
                            <sjg:gridColumn name="bin" index="bin" title="BIN Profile" align="left" width="15" sortable="false"  /> 
                            <sjg:gridColumn name="category" index="category" title="BIN Category" align="left" width="15" sortable="false"  /> 
                            <sjg:gridColumn name="category_code" index="category_code" title="category Code" align="left" width="15" sortable="false" hidden="true" /> 
                            <sjg:gridColumn name="datetime" index="timestamp" title="Date" align="center" width="35" sortable="true"/>
                            <sjg:gridColumn name="bin" title="Delete"  width="10" align="center" formatter="editformatter" sortable="false"/>
                        </sjg:grid> 

                    </div> 

                </div>

                <!--///////////////////////////////////////////////////////////////-->
                <h1 class="page-title">System Block BIN<a id="lb" href="#" class="lnk-back hide-element do-nothing"><i class="fa fa-arrow-left" aria-hidden="true"></i> back</a></h1>
                <div class="content-section search-form" id="blockBSearchForm1">
                    <s:form id="SearchForm" theme="simple">
                        <div class="content-data">
                            <h2 class="section-title">Search</h2>
                            <div class="d-row singlecol-row">
                                <label class="left-col form-label">System Block BIN </label>
                                <div class="right-col form-field">
                                    <s:textfield name="SearchLocalBIN1" maxLength="6" id="SearchLocalBIN1" cssClass="txt-input width-35"/>
                                    <sj:a 
                                        id="searchbut1" 
                                        button="true" 
                                        onClickTopics="onclicksearch1" 
                                        cssClass="btn default-button" 
                                        ><i class="fa fa-search" aria-hidden="true"></i> Search</sj:a>
                                    <sj:a 
                                        disabled="#vadd"
                                        id="Addbtn1" 
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
                <div class="content-section data-form" id="addForm1">
                    <s:form id="addFillForm1" theme="simple">
                        <div class="content-data">

                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg1" id="tt">
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i> <span id="divmsg1"></span></div>
                            </div>
                            <!-- End -->

                            <!-- Two colum form row begin -->
                            <div class="d-row">
                                <label class="left-col form-label">System Block BIN Name<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield name="Bin" id="Bin1" maxLength="6" cssClass="txt-input width-35"/>
                                </div>
                            </div>
                            <!-- End -->

                            <div class="d-row cpanel">
                                <label class="left-col">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="saveurl" action="AddSystemBloclBPrf"/>                                   
                                    <div class="btn-wrap lnk-match"><i class="fa fa-floppy-o" aria-hidden="true"></i><sj:submit  href="%{saveurl}" targets="divmsg1" value="Save" disabled="#vadd" button="true" cssClass="btn default-button" /></div>  

                                    <div class="btn-wrap lnk-match"><i class="fa fa-times" aria-hidden="true"></i><sj:submit button="true" value="Reset"  cssClass="btn reset-button" onclick="resetAddForm1()"  /></div>
                                </div>
                            </div>

                        </div>
                        <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>               
                    </s:form>
                </div>
                <div class="content-section">
                    <div class="content-data">
                        <h2 class="section-title">All System Block Bins</h2>
                        <!-- Error and success message panel begin -->
                        <div class="msg-panel del-user-msg1" >
                            <div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                        </div>
                        <!-- End -->
                    </div>


                    <div id="tablediv" class="custom-grid">

                        <sj:dialog 
                            id="deleteConfirmDialog" 
                            buttons="{ 
                            'OK':function() { confrmDeleteBinProfile($(this).data('id'),$(this).data('category_code'));$( this ).dialog( 'close' ); },
                            'Cancel':function() { $( this ).dialog( 'close' );} 
                            }" 
                            autoOpen="false" 
                            modal="true" 
                            title="Delete Bank Bin."
                            width="400"
                            height="200"
                            position="center"
                            />

                        <!-- End delete successfully dialog box -->
                        <!-- Start delete error dialog box -->
                        <s:url var="listurl" action="listSystemBlocBPrf" />

                        <!--caption="Edit and View User Details"-->
                        <sjg:grid 
                            id="gridtable2"                                
                            caption="All System Block Bins"
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
                            <sjg:gridColumn name="bin" index="bin" title="System Block BIN" align="left" width="15" sortable="false"  /> 
                            <sjg:gridColumn name="category" index="category" title="BIN Category" align="left" width="15" sortable="false"  /> 
                            <sjg:gridColumn name="category_code" index="category_code" title="category Code" align="left" width="15" sortable="false" hidden="true" /> 
                            <sjg:gridColumn name="datetime" index="timestamp" title="Date" align="center" width="35" sortable="true"/>
                            <sjg:gridColumn name="bin" title="Delete"  width="10" align="center" formatter="editformatter" sortable="false"/>
                        </sjg:grid> 

                    </div> 

                </div>

                <!--/////////////////////////////////////////////////////-->




            </div>
            <!-- End -->

            <!--End of Body Content-->


            <jsp:include page="../../footer.jsp" />
        </section>

        <script type="text/javascript">
            $(document).ready(function () {
                //Back button event
                $('#lb1').on('click', function () {
                    backToMain();
                    $('#task').empty();
                    $('.lnk-back').addClass('hide-element');
                    var text = 'Search Bank BIN Profile';
                    $('#task').append(text);
                    return false;
                });
                $('#lb').on('click', function () {
                    backToMain1();
                    $('#task').empty();
                    $('#lb').addClass('hide-element');
                    var text = 'Search Bank BIN Profile';
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
