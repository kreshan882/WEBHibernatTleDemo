<%-- 
    Document   : blockBinProfile
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

            function BinAssignformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='javascript:viewBinProfile(&#34;" + cellvalue + "&#34;,&#34;" + rowObject.binProfileDes + "&#34;)'><i class='fa fa-share-square-o' aria-hidden='true'></i></a>";
            }

            function Statusformatter(cellvalue) {
                if (cellvalue == 1) {
                    return "<i class='fa fa-circle active' aria-hidden='true'></i>";
                } else {
                    return "<i class='fa fa-circle' aria-hidden='true'></i>";
                }
            }

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' disabled='#vupdate' onClick='javascript:editBinProfile(&#34;" + cellvalue + "&#34;)'><i class='fa fa-pencil' aria-hidden='true' title='Edit Block BIN'></i></a> <a href='#' disabled='#vdelete' onClick='javascript:deleteRefTerProfile(&#34;" + cellvalue + "&#34;,&#34;" + rowObject.name + "&#34;)'><i class='fa fa-trash-o' aria-hidden='true' title='Delete Block BIN'></i></a>";
            }

            function editBinProfile(keyval) {
                var token=$( "input[name='RequstToken']" ).val();
                $('.lnk-back').removeClass('hide-element');
                utilityManager.resetMessage();
                $('#task').empty();
                var text = ' Edit Terminal Risk Profile';
                $('#task').append(text);
                $.ajax({
                    url: '${pageContext.request.contextPath}/LoadrefBPrf',
                    data: {upProfileID: keyval, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $( "input[name='RequstToken']" ).val($stoken);
                        utilityManager.resetMessage();
                        $('#addForm').hide();
                        $('#blockBSearchForm').hide();
                        $("#Updateform").show();
                        $('#upProfileID').val(data.upProfileID);
                        $('#upName').val(data.upName);
                        $('#upminAmount').val(data.upminAmount);
                        $('#upmaxAmount').val(data.upmaxAmount);
                        $('#upfrom').val(data.upfrom);
                        $('#upto').val(data.upto);
                        $('#upswipeStatus').val(data.upswipeStatus);
                        $('#upfallBackStatus').val(data.upfallBackStatus);
                        $('#upnfcBasedStatus').val(data.upnfcBasedStatus);
                        $('#upPinPerformStatus').val(data.upPinPerformStatus);
                        $('#upStatus').val(data.upStatus);
                        jQuery("#gridtable").trigger("reloadGrid");
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
                var upProfileID = $('#upProfileID').val();
                editBinProfile(upProfileID);

            }

            function confrmDeleteBinProfile(keyval) {
                var token=$( "input[name='RequstToken']" ).val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeleterefBPrf',
                    data: {id: keyval, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        if (data.isDeleted === true) {
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


            function BinProfilePopupClose() {
                $("#viewdialog").dialog('close');
            }
            function viewBinProfile(id, bin) {
                $("#viewdialog").data('Id', id);
                $("#viewdialog").data('BinProfileDes', bin).dialog('open');
            }
            $.subscribe('openview', function (event, data) {
                utilityManager.resetMessage();
//                resetData();
                var $led = $("#viewdialog");
                var $stoken=$( "input[name='RequstToken']" ).val();
//                alert($led.data('Id').replace(/ /g,"_"));
                $led.load("AssignBinblockBPrf?RequstToken="+$stoken+"&binId=" + $led.data('Id') + "&cBinProfile=" + $led.data('BinProfileDes').replace(/ /g, "_"));
            });


            $.subscribe('onclicksearch', function (event, data) {
                var searchName = $('#searchName').val();

                $("#gridtable").jqGrid('setGridParam', {postData: {searchName: searchName}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");

            });

            function deleteRefTerProfile(id, uname) {
                utilityManager.resetMessage();
                $("#deleteConfirmDialog").data('uname', id);
                $("#deleteConfirmDialog").data('id', id).dialog('open');
                $("#deleteConfirmDialog").html('<p>Please confirm delete : ' + uname + "</p>");
                return false;
            }

            function backToMain() {
                utilityManager.resetMessage();
                $('#Updateform').hide();
                $('#addForm').hide();
                $('#blockBSearchForm').show();
                $('#task').empty();
                jQuery("#gridtable").trigger("reloadGrid");
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
                    var text = ' Add Terminal Risk Profile';
                    $('#task').append(text);
                })

            })

            function resetForm() {
                $('#binName').val("");
                $('#BinProfileDes').val("");
                jQuery("#gridtable").trigger("reloadGrid");
                jQuery("#gridtable1").trigger("reloadGrid");
            }
            function resetAddForm() {
                $('#name').val("");
                $('#minAmount').val("");
                $('#maxAmount').val("");
                $('#from').val("-1");
                $('#to').val("-1");
                $('#swipeStatus').val("1");
                $('#fallBackStatus').val("1");
                $('#nfcBasedStatus').val("1");
                $('#PinPerformStatus').val("1");
                jQuery("#gridtable").trigger("reloadGrid");
                utilityManager.resetMessage();
            }

            //Allow to enter only numeric
            function isNumber(evt) {
                evt = (evt) ? evt : window.event;
                var charCode = (evt.which) ? evt.which : evt.keyCode;
                if (charCode > 31 && (charCode < 48 || charCode > 57)) {
                    return false;
                }
                return true;
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
                    <s:property value="Module"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i><s:property value="Section"/><i class="fa fa-angle-double-right" aria-hidden="true"></i><span id="task" class="active">Search Terminal Risk Profile</span>
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
                                <label class="left-col form-label">Terminal Risk Profile</label>
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
                            <div class="d-row">
                                <label class="left-col form-label">Risk Profile Name<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield name="name" id="name" cssClass="txt-input width-35"/>
                                </div>                                
                            </div>
                            <div class="d-row">   
                                <label class="left-col form-label">Minimum amount per transaction<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield name="minAmount" id="minAmount" cssClass="txt-input width-35" onkeypress="return isNumber(event)" maxLength="9"/>
                                </div>                                
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Maximum amount per transaction<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield name="maxAmount" id="maxAmount" cssClass="txt-input width-35" onkeypress="return isNumber(event)" maxLength="9"/>
                                </div>                                
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Day operation hours<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <label class="inline-fields">From </label> <s:select  name="from" headerKey="1"                                               
                                               listKey="key" listValue="value"
                                               list="%{fromMap}" id="from" cssClass="ddl-input txt-input width-10 text-right" /> <label class="inline-fields"> &nbsp; &nbsp;</label>
                                    <label class="inline-fields">To </label> <s:select  name="to" headerKey="1"                                             
                                               listKey="key" listValue="value"
                                               list="%{toMap}" id="to" cssClass="ddl-input txt-input width-10 text-right" />
                                </div>                                
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Perform non-EMV transaction<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select  name="swipeStatus" headerKey="1"                                               
                                               listKey="key" listValue="value"
                                               list="%{swipeTarMap}" id="swipeStatus" cssClass="ddl-input" />
                                </div>                                
                            </div>                                
                            <div class="d-row">
                                <label class="left-col form-label">Perform fall-back transaction<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select  name="fallBackStatus" headerKey="1"                                              
                                               listKey="key" listValue="value"
                                               list="%{fallBackMap}" id="fallBackStatus" cssClass="ddl-input" />
                                </div>                                
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Perform NFC-based transaction<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select  name="nfcBasedStatus" headerKey="1"                                              
                                               listKey="key" listValue="value"
                                               list="%{nfcBasedMap}" id="nfcBasedStatus" cssClass="ddl-input" />
                                </div>                                
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Request PIN to Perform transaction<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select  name="PinPerformStatus" headerKey="1"                                              
                                               listKey="key" listValue="value"
                                               list="%{PinPerformkMap}" id="PinPerformStatus" cssClass="ddl-input" />
                                </div>                                
                            </div>

                            <!-- Two colum form row begin -->


                            <!-- End -->

                            <div class="d-row cpanel">
                                <label class="left-col">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="saveurl" action="AddrefBPrf"/>                                   
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
                            <!--Error and success message panel begin--> 
                            <div class="msg-panel add-form-msg">
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                            </div>
                            <!--End--> 

                            <div class="d-row">
                                <s:hidden name="upProfileID" id="upProfileID" value="1"/>
                                <label class="left-col form-label">Risk Profile Name<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield name="upName" id="upName" cssClass="txt-input width-35"/>
                                </div>                               
                            </div>                                
                            <div class="d-row">    
                                <label class="left-col form-label">Minimum amount per transaction<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield name="upminAmount" id="upminAmount" cssClass="txt-input width-35"/>
                                </div>                                
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Maximum amount per transaction<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield name="upmaxAmount" id="upmaxAmount" cssClass="txt-input width-35"/>
                                </div>                                
                            </div>                                
                            <div class="d-row">
                                <label class="left-col form-label">Day operation hours<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <label class="inline-fields">From </label> <s:select  name="upfrom" headerKey="1"                                                
                                               listKey="key" listValue="value"
                                               list="%{fromMap}" id="upfrom" cssClass="ddl-input txt-input width-10 text-right" /> <label class="inline-fields"> &nbsp; &nbsp;</label>
                                    <label class="inline-fields">To </label> <s:select  name="upto" headerKey="1"                                               
                                               listKey="key" listValue="value"
                                               list="%{toMap}" id="upto" cssClass="ddl-input txt-input width-10 text-right" />
                                </div>                                
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Perform non-EMV transaction<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select  name="upswipeStatus" headerKey="1"                                                
                                               listKey="key" listValue="value"
                                               list="%{swipeTarMap}" id="upswipeStatus" cssClass="ddl-input" />
                                </div>                                
                            </div>                                
                            <div class="d-row">
                                <label class="left-col form-label">Perform fall-back transaction<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select  name="upfallBackStatus" headerKey="1"                                               
                                               listKey="key" listValue="value"
                                               list="%{fallBackMap}" id="upfallBackStatus" cssClass="ddl-input" />
                                </div>                                
                            </div>

                            <div class="d-row">
                                <label class="left-col form-label">Perform NFC-based transaction<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select  name="upnfcBasedStatus" headerKey="1"                                              
                                               listKey="key" listValue="value"
                                               list="%{nfcBasedMap}" id="upnfcBasedStatus" cssClass="ddl-input" />
                                </div>                                
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Request PIN to Perform transaction<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select  name="upPinPerformStatus" headerKey="1"                                              
                                               listKey="key" listValue="value"
                                               list="%{PinPerformkMap}" id="upPinPerformStatus" cssClass="ddl-input" />
                                </div>                                
                            </div>

                            <div class="d-row">
                                <label class="left-col form-label">Risk Profile Status<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select  name="upStatus" headerKey="1"                                                
                                               listKey="key" listValue="value"
                                               list="%{upStatusMap}" id="upStatus" cssClass="ddl-input" />
                                </div>                                

                            </div>

                            <!-- Two colum form row begin -->

                            <div class="d-row cpanel">
                                <label class="left-col">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="updateuserurl" action="updaterefBPrf"/>                                   
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
                        <h2 class="section-title">ALL TERMINAL RISK PROFILE</h2>
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
                            title="Risk Terminal Profile"
                            onOpenTopics="openview" 
                            loadingText="Loading .."
                            />

                        <sj:dialog 
                            id="deleteConfirmDialog" 
                            buttons="{ 
                            'OK':function() { confrmDeleteBinProfile($(this).data('id'));$( this ).dialog( 'close' ); },
                            'Cancel':function() { $( this ).dialog( 'close' );} 
                            }" 
                            autoOpen="false" 
                            modal="true" 
                            title="Delete Terminal Risk Profile."
                            width="400"
                            height="200"
                            position="center"
                            />

                        <!-- End delete successfully dialog box -->
                        <!-- Start delete error dialog box -->

                        <s:url var="listurl" action="listrefBPrf" />

                        <!--caption="Edit and View User Details"-->
                        <sjg:grid
                            id="gridtable"                                
                            caption="All Block Bin Profile"
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
                            <sjg:gridColumn name="id" index="id" title="id"   hidden="true" />
                            <sjg:gridColumn name="name" index="name" title="Risk Profile Name" align="left" width="15" sortable="true"  /> 
                            <sjg:gridColumn name="minAmount" index="minAmount" title="Min Amount Per Trans" align="center" width="35" sortable="true"/>
                            <sjg:gridColumn name="maxAmount" index="maxAmount" title="Max Amount Per Trans"  align="center" width="10"   sortable="false"/>
                            <sjg:gridColumn name="from" index="from" title="Operation Hours From"  align="center" width="10"   sortable="false"/>
                            <sjg:gridColumn name="to" index="to" title="Operation Hours To"  align="center" width="10"   sortable="false"/>
                            <sjg:gridColumn name="swipeStatus" index="swipeStatus" title="Perform non-EMV transaction"  align="center" width="10"   sortable="false"/>
                            <sjg:gridColumn name="fallBackStatus" index="fallBackStatus" title="Perform fall-back transaction"  align="center" width="10"   sortable="false"/>
                            <sjg:gridColumn name="nfcBasedStatus" index="nfcBasedStatus" title="Perform NFC-based transaction"  align="center" width="10"   sortable="false"/>
                            <sjg:gridColumn name="pinPerformStatus" index="pinPerformStatus" title="Request PIN to Perform transaction"  align="center" width="10"   sortable="false"/>
                            <sjg:gridColumn name="status"  title="Status"  formatter="Statusformatter" align="center" width="10"   sortable="false"/>
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
                var text = 'Search Terminal Risk Profile';
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
