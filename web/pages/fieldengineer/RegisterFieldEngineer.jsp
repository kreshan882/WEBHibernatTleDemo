<%-- 
    Document   : addFieldEngineer
    Created on : Sep 16, 2015, 2:48:01 PM
    Author     : ravideva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib uri="/struts-tags" prefix="s"  %>  
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<html>
    <head>

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

            $(document).ready(function () {
                $("#addEngineer").hide();

            });

            function resetForm() {
                utilityManager.resetMessage();
                $('#deviceType').val("-1");
                $('#serialNo').val("");
                $('#fldEngName').val("");
                $('#bankName').val("");
                $('#location').val("");
                $('#maxKeyDown').val("");
                $('#algo').val("-1");
                $('#pinVerType').val("-1");
                $('#bdkindex').val("-1");
                document.getElementById("serialNo").disabled = false;
                utilityManager.resetMessage();
                jQuery("#gridtable").trigger("reloadGrid");
            }


            function loaddeviceType(keyval) {
                if (keyval == 1) {
                    $('#serialNo').val("");
                    document.getElementById("serialNo").disabled = true;
                } else {
                    document.getElementById("serialNo").disabled = false;
                }
            }

            function addForm() {
                utilityManager.resetMessage();
//                addEngineer
//                var deviceType = $('#deviceType').text();
                var deviceType = $('div#addEngineer select#deviceType').val();
                
                
//                alert($("#deviceType option:selected").val());
                var serialNo = $('#serialNo').val();
                var fldEngName = $('#fldEngName').val();
                var bankName = $('#bankName').val();
                var location = $('#location').val();
                var maxKeyDown = $('#maxKeyDown').val();
                var algo = $('#algo').val();
                var pinVerType = $('#pinVerType').val();
                var bdkindex = $('#bdkindex1').val();
                var token=$( "input[name='RequstToken']" ).val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/AddregField',
                    data: {deviceType: deviceType, serialNo: serialNo, fldEngName: fldEngName, bankName: bankName, location: location, maxKeyDown: maxKeyDown, algo: algo, pinVerType: pinVerType,bdkindex:bdkindex, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $('#messagefe').val(data.messagefe);
                        $('#pdfActive').val(data.pdfActive);
                        $('#successmsg').val(data.successmsg);
//                        resetForm();
                        if (data.successmsg) {
                            if (data.pdfActive == true) {
                                $('#pdfbankname').val(data.pdfbankname);
                                $('#pdfmerchantname').val(data.pdfmerchantname);
                                $('#pdfcardSerialNo').val(data.pdfcardSerialNo);
                                $('#pdfuserPin').val(data.pdfuserPin);
                                utilityManager.showMessage('.add-form-msg', data.messagefe, 'successmsg',$stoken);
                                $("#assignbut").click();
                            } else {
                                utilityManager.showMessage('.add-form-msg', data.messagefe, 'successmsg',$stoken);
                            }

                        } else {
                            utilityManager.showMessage('.add-form-msg', data.messagefe, 'errormsg',$stoken);
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

            function showEditView() {
                $("#EditViewEngineer").show();
                $("#addEngineer").hide();
                $('#task').empty();
            }

            function downloadpdfnow() {
                resetForm();
                $("#assignbut").click();
            }

        </script>


        <!--Edit View Field Engineer Scripts-->

        <style>
            .ui-button-text-only .ui-button-text {
                padding: 0;
            }
            .ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
                font-weight: normal; 
            }
        </style>
        <script>
            function resetFormUp() {
                $('.add-form-msg').hide();
                $('#upstatus').val("-1");
                $('#upserialNumber').val("");
                $('#upfldEngName').val("");
                $('#upbankName').val("");
                $('#uplocation').val("");
                $('#upmaxKeyDown').val("");

                jQuery("#gridtable").trigger("reloadGrid");
            }

            $.subscribe('onclicksearch', function (event, data) {
                var searchSerial = $('#searchSerial').val();
//                var selectedDevice = $('#selectedDevice').val();
                  var selectedDevice = $('div#searchdiv select#deviceType').val();
                var officerName = $('#officerName').val();
                var locations = $('#locations').val();
                var algorithm = $('#algorithm').val();
                var bdkindex = $('#bdkindex').val();
                
//                var algo = $('#algo').val();
//                alert(algo);
                var pinVerification = $('#pinVerification').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {searchSerial: searchSerial, selectedDevice: selectedDevice, officerName: officerName, locations: locations, algorithm: algorithm, pinVerification: pinVerification,bdkindex:bdkindex}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            });

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' disabled='#vupdate' title='Edit Field Engineer' onClick='editFieldEng(&#34;" + cellvalue + "&#34;)'><i class='fa fa-pencil' aria-hidden='true'></i></a> <a href='#' disabled='#vdelete' title='Delete Field Engineer' onClick='deleteFieldEngInit(&#34;" + cellvalue + "&#34;)'><i class='fa fa-trash-o' aria-hidden='true'></i></a>";
            }
            function pinresetformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='resetPinInit(&#34;" + cellvalue + "&#34;)'><i class='fa fa-retweet' aria-hidden='true'></i></a>";
            }
            function Statusformatter(cellvalue) {
                if (cellvalue == 1) {
                    return "<i class='fa fa-circle active' aria-hidden='true'></i>";
                } else {
                    return "<i class='fa fa-circle' aria-hidden='true'></i>";
                }
            }

            function resetPinInit(serialNumber) {
                $("#pinresetConfirmDialog").data('serialNumber', serialNumber).dialog('open');
                $("#pinresetConfirmDialog").html('<p>Please confirm Reset Pin : ' + serialNumber + '</p>');
                return false;
            }
            function resetPin(serialNumber) {
                var token=$( "input[name='RequstToken']" ).val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/PinResetregField',
                    data: {pinserialNumber: serialNumber, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $( "input[name='RequstToken']" ).val($stoken);
                        $("#pinreset").dialog('option', 'title', 'PIN reset message');
                        $("#pinreset").dialog('open');
                        $("#pinreset").html('<p> ' + data.messagefe + '</p>');
//                        alert("pin reset sucess" + data.messagefe + ":" + data.successmsg + ":" + data.pdfActive);

                        $('#messagefe').val(data.messagefe);
                        $('#pdfActive').val(data.pdfActive);
                        $('#successmsg').val(data.successmsg);
                        if (data.successmsg) {

                            if (data.pdfActive == true) {
                                $('#pdfbankname').val(data.pdfbankname);
                                $('#pdfmerchantname').val(data.pdfmerchantname);
                                $('#pdfcardSerialNo').val(data.pdfcardSerialNo);
                                $('#pdfuserPin').val(data.pdfuserPin);

                                $("#downloadpdfDialog").html('<p> ' + data.messagefe + '</p>');
                                $("#downloadpdfDialog").dialog('open');
//                                $("#assignbut").click();

                            } else {
                                $('#divmsg').append('<p>' + data.messagefe + '</i>')
                                $('#divmsg').show();
                                resetForm();
////                                $("#assignbut").click();
                            }

                        } else {
                            $('#deviceType').val("-1");
//                            $("#assignbut").click();
                        }
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });
            }

            function deleteFieldEngInit(serialNumber) {
                $("#deleteConfirmDialog").data('serialNumber', serialNumber).dialog('open');
                $("#deleteConfirmDialog").html('<p>Please confirm delete : ' + serialNumber + '</p>');
                return false;
            }


            function deleteFieldEng(serialNumber) {
                var token=$( "input[name='RequstToken']" ).val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeleteregField',
                    data: {dserialNumber: serialNumber, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $('.errorpanel').show();
                        if (data.isDeleted == true) {
                            utilityManager.showMessage('.del-user-msg', data.dmessage, 'successmsg',$stoken);
                        } else {
                            utilityManager.showMessage('.del-user-msg', data.dmessage, 'errormsg',$stoken);
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
                jQuery("#gridtable").trigger("reloadGrid");
                $('.add-form-msg').hide();
                $('.errorpanel').hide();
            }


            function editFieldEng(serialNumber) {
                utilityManager.resetMessage();
                $('#fieldengEditForm').show();
                $('#searchdiv').hide();
                $('.lnk-back').removeClass('hide-element');
                $('#task').empty();
                var text = ' Edit Field Engineer';
                $('#task').append(text);

                if ($("#addEngineer").show()) {
                    $("#addEngineer").hide();

                }
                if ($("#EditViewEngineer").show())
                    $("#EditViewEngineer").hide();
                var token=$( "input[name='RequstToken']" ).val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/FindregField',
                    data: {upserialNumber: serialNumber, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $( "input[name='RequstToken']" ).val($stoken);
                        $('#upserialNumber').attr('readOnly', true);
                        $('#upserialNumber').val(data.upserialNumber);
                        $('#upfldEngName').val(data.upfldEngName);
                        $('#upbankName').val(data.upbankName);
                        $('#uplocation').val(data.uplocation);
                        $('#upmaxKeyDown').val(data.upmaxKeyDown);
                        $('#upstatus').val(data.upstatus);
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });
            }

            function resetUpdateForm() {
                utilityManager.resetMessage();
                var upserialNumber = $('#upserialNumber').val();
                var token=$( "input[name='RequstToken']" ).val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/FindregField',
                    data: {upserialNumber: upserialNumber,RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $( "input[name='RequstToken']" ).val($stoken);
                        $('#upserialNumber').attr('readOnly', true);
                        $('#upserialNumber').val(data.upserialNumber);
                        $('#upfldEngName').val(data.upfldEngName);
                        $('#upbankName').val(data.upbankName);
                        $('#uplocation').val(data.uplocation);
                        $('#upmaxKeyDown').val(data.upmaxKeyDown);
                        $('#upstatus').val(data.upstatus);

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
                $('#fieldengEditForm').hide();
                $('#searchdiv').show();
                utilityManager.resetMessage();
                $("#addEngineer").hide();
                $("#EditViewEngineer").show();
                $('#task').empty();
                jQuery("#gridtable").trigger("reloadGrid");

            }

//            function downloadpdfnow() {
//                resetForm();
//                $("#assignbut").click();
//            }

            function showAddFieldEngineer() {
                $('.lnk-back').removeClass('hide-element');
                $("#searchdiv").hide();
                $("#EditViewEngineer").hide();
                $("#addEngineer").show();
                $('#task').empty();
                var text = ' Add Field Engineer';
                $('#task').append(text);
            }
            
            function resetSearchForm() {               
                $('#searchSerial').val("");
                $('#selectedDevice').val("");
                $('#officerName').val("");
                $('#locations').val("");
                $('#algorithm').val("-1");
                $('#pinVerification').val("-1");
                $('#searchbut').click();
            }
            
        </script>
    </head>
    <s:set id="vadd" var="vadd"><s:property  value="vadd" default="true"/></s:set>
    <s:set id="vupdate" var="vupdate"><s:property value="vupdate" default="true"/></s:set>
    <s:set id="vdelete" var="vdelete"><s:property value="vdelete" default="true"/></s:set>
    <s:set id="vdownload" var="vdownload"><s:property value="vdownload" default=""/></s:set>
    <s:set id="vview" var="vview"><s:property value="view" default="true"/></s:set>

        <body>

            <section class="app-content">
            <jsp:include page="../../header.jsp" />
            <!-- Page content begin -->
            <div class="content innerpage">
                <!-- Breadcrumb begin -->
                <div class="breadcrumb">
                    <s:property value="Module"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i> <s:property value="Section"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i><span id="task" class="active">Search Field Engineer</span>
                </div>
                <!-- End -->
                <h1 class="page-title"><s:property value="Section"/><a href="#" class="lnk-back hide-element do-nothing"><i class="fa fa-arrow-left" aria-hidden="true"></i> back</a></h1>

                <div class="content-section search-form " id="searchdiv">   
                    <s:form  id="fieldengSearchForm"  action="XSLcreatregField"  theme="simple">
                        <div class="content-data">
                            <h2 class="section-title">Search</h2>
                            <!--                            <div class="d-row singlecol-row">
                                                            <label class="left-col form-label">Serial Number</label>
                                                            <div class="right-col form-field">
                            <s:textfield name="searchSerial" id="searchSerial" cssClass="txt-input width-35"/>
                            <sj:a   
                                id="searchbut"   
                                button="true"    
                                onClickTopics="onclicksearch"  
                                cssClass="btn default-button" 
                                onfocus="true"
                                ><i class="fa fa-search" aria-hidden="true"></i> Search</sj:a>
                           
                            <sj:a disabled="#vdownload" id="exportBtn" button="true" onclick="utilityManager.anchorTagSubmit('#exportBtn','#fieldengSearchForm')"
                                  cssClass="btn default-button" role="button" aria-disabled="false" cssStyle="padding-left: 15px; font-weight:normal !important;margin-top: 0px;width:80px;" >
                                <i class="fa fa-reply-all" aria-hidden="true"></i>
                                Export</sj:a>
                            </div>

                        </div>-->
                                <div class="d-row singlecol-row">
                                    <label class="left-col form-label">Serial Number</label>
                                    <div class="right-col form-field">
                                    <s:textfield name="searchSerial" id="searchSerial" cssClass="txt-input width-35"/>
                                </div>

                            </div>
                            <div class="d-row singlecol-row">
                                <label class="left-col form-label">Officer Name</label>
                                <div class="right-col form-field">
                                    <s:textfield name="officerName" id="officerName" cssClass="txt-input width-35"/>
                                </div>

                            </div>

                            <div class="d-row singlecol-row">
                                <label class="left-col form-label">Location</label>
                                <div class="right-col form-field">
                                    <s:textfield name="locations" id="locations" cssClass="txt-input width-35"/>
                                </div>

                            </div>
                                
                            <div class="d-row singlecol-row">
                                <label class="left-col form-label">Selected Device</label>
                                <div class="right-col form-field">
                                    <s:select id="deviceType"  name="deviceType"  headerKey="-1"  headerValue="---Select---" 
                                              onchange="loaddeviceType(this.value)" list="%{deviceTypeMap}" cssClass="ddl-input" />
                                </div>

                            </div>
                            <div class="d-row singlecol-row">
                                <label class="left-col form-label">Algorithm</label>
                                <div class="right-col form-field">
                                    <%--<s:textfield name="algorithm" id="algorithm" cssClass="txt-input width-35"/>--%>
                                     <%--<s:select id="algo"  name="algo"  headerKey="-1"  headerValue="---Select---"  list="%{algoMap}" cssClass="ddl-input" />--%>
                                      <s:select id="algorithm"  name="algorithm"  headerKey="-1"  headerValue="---Select---"  list="%{algoMap}" cssClass="ddl-input" />
                                </div>

                            </div>

                            <div class="d-row singlecol-row">
                                <label class="left-col form-label">Pin Verification</label>
                                <div class="right-col form-field">
                                    <%--<s:textfield name="pinVerification" id="pinVerification" cssClass="txt-input width-35"/>--%>
                                    <s:select id="pinVerification"  name="pinVerification"  headerKey="-1"  headerValue="---Select---"  list="%{pinVerTypeMap}" cssClass="ddl-input" />
                                </div>

                            </div>
                                
                            <div class="d-row singlecol-row">
                                <label class="left-col form-label">BDK Index</label>
                                <div class="right-col form-field">
                                    <%--<s:textfield name="pinVerification" id="pinVerification" cssClass="txt-input width-35"/>--%>
                                    <s:select id="bdkindex"  name="bdkindex"  headerKey="-1"  headerValue="---Select---"  list="%{bdkindexMap}" cssClass="ddl-input" />
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



                                        <!--<div class="btn-wrap lnk-match">-->
                                        <!--<i class="fa fa-plus" aria-hidden="true" style="text-align: right;padding-left: 10px"></i>--> 

                                    <sj:a 
                                        button="true"
                                        id="btnAdd" 
                                        name="btnAdd"
                                        disabled="#vadd" 
                                        value="Add"

                                        cssClass="btn default-button" ><i class="fa fa-plus" aria-hidden="true"></i> Add</sj:a>

                                        <!--</div>-->


                                        <div class="btn-wrap lnk-match">
                                            <i class="fa fa-reply-all" aria-hidden="true" style="text-align: right;padding-left: 10px"></i> 
                                        <sj:submit
                                            button="true"
                                            id="exportBtn" 
                                            name="exportBtn"
                                            disabled="#vdownload" 
                                            value="Export"
                                            onclick="utilityManager.anchorTagSubmit('#exportBtn','#fieldengSearchForm')"
                                            cssClass="btn default-button"
                                            />
                                    </div>
                                    <sj:a button="true" onclick="resetSearchForm()" cssClass="btn reset-button"><i class="fa fa-times" aria-hidden="true"></i> Reset</sj:a>
                                </div>
                            </div>  
                        </div>
                        <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                    </s:form>
                </div>

                <!-- Data form begin -->
                <div class="content-section data-form" id="addEngineer" style="display: none">
                    <s:form  action="downloadpdfregField" theme="simple" >
                        <div class="content-data">
                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg" id="addMessage" > 
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                            </div>
                            <!-- End -->
                            <!-- Two colum form row begin -->
                            <div class="d-row">
                                <label class="left-col form-label">Issuing Method<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select id="deviceType"  name="deviceType"  headerKey="-1"  headerValue="---Select---" 
                                              onchange="loaddeviceType(this.value)" list="%{deviceTypeMap}" cssClass="ddl-input" />
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Serial Number<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="serialNo" name="serialNo" cssClass="txt-input width-35" disabled="false" />
                                </div>
                            </div>    
                            <div class="d-row">
                                <label class="left-col form-label">Field Engineer Name<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="fldEngName" name="fldEngName" maxLength="40" cssClass="txt-input width-35"/>
                                </div>
                            </div>    
                            <div class="d-row">
                                <label class="left-col form-label">Bank Name<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="bankName" name="bankName" maxLength="40" cssClass="txt-input width-35"/>
                                </div>
                            </div>  

                            <div class="d-row">
                                <label class="left-col form-label">Location<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="location" name="location" maxLength="40" cssClass="txt-input width-35"/>
                                </div>
                            </div>    
                            <div class="d-row">
                                <label class="left-col form-label">Maximum Key Download<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="maxKeyDown" name="maxKeyDown" cssClass="txt-input width-35"/>
                                </div>
                            </div>    
                            <div class="d-row">
                                <label class="left-col form-label">Algorithm<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select id="algo"  name="algo"  headerKey="-1"  headerValue="---Select---"  list="%{algoMap}" cssClass="ddl-input" />
                                </div>
                            </div>  
                            <div class="d-row">
                                <label class="left-col form-label">Pin Verification<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select id="pinVerType"  name="pinVerType"  headerKey="-1"  headerValue="---Select---"  list="%{pinVerTypeMap}" cssClass="ddl-input" />
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">BDK index<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select id="bdkindex1"  name="bdkindex1"  headerKey="-1"  headerValue="---Select---"  list="%{bdkindexMap}" cssClass="ddl-input" />
                                </div>
                            </div>
                                
                            <!-- End -->
                            <div class="d-row cpanel">
                                <label class="left-col">&nbsp;</label>
                                <div class="right-col">
                                    <sj:a 
                                        disabled="#vadd"
                                        button="true" 
                                        onclick="addForm()" 
                                        cssClass="btn default-button" ><i class="fa fa-floppy-o" aria-hidden="true"></i> Save</sj:a>

                                    <sj:a button="true" onclick="resetForm()" value="Reset" cssClass="btn reset-button"><i class="fa fa-times" aria-hidden="true"></i> Reset</sj:a>
                                    </div>
                                </div> 
                            </div>
                        <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>                    
                    </s:form>
                </div>

                <s:form id="fieldengEditForm" theme="simple" method="post" cssClass="content-section data-form" cssStyle="display: none">
                    <div class="content-data">
                        <!-- Error and success message panel begin -->
                        <div class="msg-panel add-form-msg" >
                            <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                        </div>
                        <div class="d-row">
                            <label class="left-col form-label">Serial Number<sup class="required">*</sup></label>
                            <div class="right-col form-field">
                                <s:textfield id="upserialNumber" name="upserialNumber" cssClass="txt-input width-35"  disabled="false" />
                            </div>
                        </div> 
                        <div class="d-row">
                            <label class="left-col form-label">Field Engineer Name<sup class="required">*</sup></label>
                            <div class="right-col form-field">
                                <s:textfield id="upfldEngName" name="upfldEngName" cssClass="txt-input width-35"/>
                            </div>
                        </div> 
                        <div class="d-row">
                            <label class="left-col form-label">Bank Name<sup class="required">*</sup></label>
                            <div class="right-col form-field">
                                <s:textfield id="upbankName" name="upbankName" cssClass="txt-input width-35"/>
                            </div>
                        </div> 
                        <div class="d-row">
                            <label class="left-col form-label">Location<sup class="required">*</sup></label>
                            <div class="right-col form-field">
                                <s:textfield id="uplocation" name="uplocation" cssClass="txt-input width-35"/>
                            </div>
                        </div> 
                        <div class="d-row">
                            <label class="left-col form-label">Maximum Key Download<sup class="required">*</sup></label>
                            <div class="right-col form-field">
                                <s:textfield id="upmaxKeyDown" name="upmaxKeyDown" cssClass="txt-input width-35"/>
                            </div>
                        </div> 
                        <div class="d-row">
                            <label class="left-col form-label">Status<sup class="required">*</sup></label>
                            <div class="right-col form-field">
                                <s:select id="upstatus"  name="upstatus"  headerKey="-1"  headerValue="---Select---"  list="%{upstatusMap}" cssClass="ddl-input" />
                            </div>
                        </div>
                        <!-- Tow column form button panel begin -->		
                        <div class="d-row cpanel">
                            <label class="left-col">&nbsp;</label>
                            <div class="right-col">
                                <!--<button id="btnSubmit" type="submit" class="btn default-button"><i class="fa fa-floppy-o" aria-hidden="true"></i> save</button>-->
                                <s:url var="updateuserurl" action="UpdateregField"/>                                   
                                <div class="btn-wrap"><i class="fa fa-pencil-square-o" aria-hidden="true"></i><sj:submit  href="%{updateuserurl}" disabled="#vupdate" targets="divmsg" value="Update"  button="true" cssClass="btn default-button" /></div>
                                <div class="btn-wrap"><i class="fa fa-pencil-square-o" aria-hidden="true"></i><sj:submit  onclick="resetUpdateForm()" disabled="#vupdate"  value="Reset"  button="true" cssClass="btn reset-button" /></div>

                            </div>
                        </div>
                        <!-- End -->    
                    </div>
                    <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                </s:form> 

                <s:form action="downloadpdfregField" theme="simple" >

                    <s:hidden id="pdfActive" name="pdfActive" />
                    <s:hidden id="successmsg" name="successmsg" />
                    <s:hidden id="messagefe" name="messagefe" />

                    <s:hidden id="pdfbankname" name="pdfbankname"/>
                    <s:hidden id="pdfmerchantname" name="pdfmerchantname" />
                    <s:hidden id="pdfcardSerialNo" name="pdfcardSerialNo"/>
                    <s:hidden id="pdfuserPin" name="pdfuserPin" />
                    <s:submit button="true" disabled="#vdownload" id="assignbut" cssStyle="display: none; visibility: hidden;"  />
                </s:form>

                <div class="content-section errorpanel">
                    <div class="content-data">
                        <!-- Error and success message panel begin -->
                        <div class="msg-panel del-user-msg">
                            <div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                        </div>
                    </div>
                </div>


                <div class="content-section">
                    <div class="content-data">
                        <h2 class="section-title">All  Registered Field Engineer</h2>
                        <!-- Error and success message panel begin -->
                        <!--                        <div class="msg-panel del-user-msg" >
                                                    <div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                                                </div>-->
                        <!-- End -->
                        <sj:dialog 
                            id="pinreset" 
                            buttons="{ 
                            'OK':function() { $( this ).dialog( 'close' ); },
                            'Cancel':function() { $( this ).dialog( 'close' );} 
                            }" 
                            autoOpen="false" 
                            modal="true" 
                            title="PDF download Confirmation"
                            width="400"
                            height="200"
                            position="center"
                            />


                        <sj:dialog 
                            id="downloadpdfDialog" 
                            buttons="{ 
                            'OK':function() { $( this ).dialog( 'close' );downloadpdfnow(); },
                            'Cancel':function() { $( this ).dialog( 'close' );} 
                            }" 
                            autoOpen="false" 
                            modal="true" 
                            title="PDF download Confirmation"
                            width="400"
                            height="200"
                            position="center"
                            />

                        <sj:dialog 
                            id="pinresetConfirmDialog" 
                            buttons="{ 
                            'OK':function() { resetPin($(this).data('serialNumber'));$( this ).dialog( 'close' ); },
                            'Cancel':function() { $( this ).dialog( 'close' );} 
                            }" 
                            autoOpen="false" 
                            modal="true" 
                            title="Reset Pin"
                            width="400"
                            height="200"
                            position="center"
                            />
                        <sj:dialog 
                            id="pinresetMessageDialog" 
                            buttons="{
                            'OK':function() { $(this).data('pinmessage'); $( this ).dialog( 'close' );}
                            }"  
                            autoOpen="false" 
                            modal="true" 
                            title="Reset Pin" 
                            width="400"
                            height="150"
                            position="center"
                            />
                        <sj:dialog 
                            id="deleteConfirmDialog" 
                            buttons="{ 
                            'OK':function() { deleteFieldEng($(this).data('serialNumber'));$( this ).dialog( 'close' ); },
                            'Cancel':function() { $( this ).dialog( 'close' );} 
                            }" 
                            autoOpen="false" 
                            modal="true" 
                            title="Delete Field Engineer"
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
                            title="Delete Field Engineer" 
                            width="400"
                            height="150"
                            position="center"
                            />
                        <div id="tablediv" class="custom-grid">
                            <s:url var="listurl" action="ListregField"/>
                            <sjg:grid
                                id="gridtable"
                                caption="All  Registered Field Engineer"
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

                                <sjg:gridColumn name="sid" index="sid" title="ID" align="left" width="15" hidden="true" frozen="true"  sortable="true"  /> 
                                <sjg:gridColumn name="serialno" index="serialno" title="Serial Number" align="left" frozen="true"  width="100"sortable="true"/>                    
                                <sjg:gridColumn name="selecteddevice" index="epicTleSelecteddevice.description" frozen="true" title="Selected Device" align="left" width="180" sortable="true"/> 
                                <sjg:gridColumn name="officername" index="officername" title="Officer Name" frozen="true" align="left" width="100" sortable="true"/> 
                                <sjg:gridColumn name="bankname" index="bankname" title="Bank Name" align="left"  width="100" sortable="true"/> 
                                <sjg:gridColumn name="location" index="location" title="Location" align="left" width="100" sortable="true"/> 
                                <sjg:gridColumn name="regdate" index="regdate" title="Registered Date" align="center" width="150" sortable="true"/> 
                                <sjg:gridColumn name="maxtmkdownlod" index="maxtmkdownlod" title="Max Key Download" align="left" width="150" sortable="true"/> 
                                <sjg:gridColumn name="avaliabletmkdownlod" index="maxcountor" title="Available Key Download" align="left" width="150" sortable="true"/> 
                                <sjg:gridColumn name="algorithem" index="epicTleAlgorithem" title="Algorithm" align="left" width="150" sortable="true"/> 
                                <sjg:gridColumn name="pinVerfi" index="epicTlePinverficationmethod" title="Pin Verification" align="left" width="150" sortable="true"/> 
                                <sjg:gridColumn name="bdkindex" index="bdkindex" title="BDK index" align="left" width="150" sortable="true"/> 
                                <sjg:gridColumn name="status" index="epicTleStatus.description" title="Status"  align="center" width="80" formatter="Statusformatter"  sortable="true"/>
                                <sjg:gridColumn name="serialno"  title="Pin Reset"  width="80" align="center" formatter="pinresetformatter"   sortable="false" cssClass="action-col"/>
                                <sjg:gridColumn name="serialno"  title="Action"  width="80" align="center" formatter="editformatter" sortable="false" cssClass="action-col"/>
                            </sjg:grid> 

                        </div>
                    </div>
                </div>
            </div>
            <!--End of Body Content-->

            <jsp:include page="../../footer.jsp" />

        </section>
        <script type="text/javascript">
            $(document).ready(function () {
//                $("#btnAdd").hover(function (event) {
//                    alert("done");
                if ($('#btnAdd').attr('disabled') != undefined) {
                    $("#btnAdd").css(
                            {
                                "background": "#595a5b",
                                "border": "1px solid #595a5b"
                            }
                    );
                } else {
//                    $("#btnAdd").css(
//                            {
//                                "background": "#12b2d8",
//                                "border": "1px solid #005265"
//                            }
//                    );
                }
//
//                });
                $('#btnAdd').click(function () {
                    if ($('#btnAdd').attr('disabled') != undefined) {
                        return false;
                    } else {
                        showAddFieldEngineer();
                    }

                });



                //Back button event
                $('.lnk-back').on('click', function () {
                    backToMain();
                    $('#task').empty();
                    $('.lnk-back').addClass('hide-element');
                    var text = 'Search Field Engineer';
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

