<%-- 
    Document   : passwordPolicy
    Created on : Jun 9, 2015, 2:55:08 PM
    Author     : kreshan
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
                ping: 0;
            }
            .ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
                font-weight: normal; 
            }
            .test-tl{
                float: left
            }
            /*            .wi{
                            width: 70%;
                        }*/
        </style>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#btnsave').hide();
                $('#btnreset').hide();
                $('#btncnsl').hide();
                $('#btncancel1').hide();

                resetServerConfForm()
                disableAll()
//                disableSession()
            });
            function disableSession() {
                $("#sessionConf input[type=hidden]").attr('disabled', true);
                $("#sessionConf input[type=text]").each(function () {
                    $(this).attr('disabled', true)
                })
                $("#sessionConf select").each(function () {
                    $(this).attr('disabled', true)
                })
                $("#sessionConf input[type=password]").each(function () {
                    $(this).attr('disabled', true)
                })
            }
            function enableSession() {
                $("#sessionConf input[type=hidden]").attr('disabled', false);
                $("#sessionConf input[type=text]").each(function () {
                    $(this).attr('disabled', false)
                })
                $("#sessionConf select").each(function () {
                    $(this).attr('disabled', false)
                })
                $("#sessionConf input[type=password]").each(function () {
                    $(this).attr('disabled', false)
                })
            }
            function disableAll() {
                utilityManager.resetMessage();
                $(".server input[type=hidden]").attr('disabled', true);
                document.getElementById("isChecked").checked = false;
                document.getElementById("isCheckedsms").checked = false;
                disablepasswordFileds();
                $("#isChecked").attr('disabled', true);
                $("#isCheckedsms").attr('disabled', true);
                $(".server input[type=text]").each(function () {
                    $(this).attr('disabled', true)
                })
                $(".server select").each(function () {
                    $(this).attr('disabled', true)
                })
                $(".server input[type=password]").each(function () {
                    $(this).attr('disabled', true)
                })
            }

            function enableAll() {
                $(".server input[type=hidden]").attr('disabled', false);
                $("#isChecked").attr('disabled', false);
                $("#isCheckedsms").attr('disabled', false);

                $(".server input[type=text]").each(function () {
                    $(this).attr('disabled', false)
                })
                $(".server select").each(function () {
                    $(this).attr('disabled', false)
                })
            }
            function changeActionEmail() {
                var isChecked = document.getElementById("isChecked").checked;
                if (isChecked === true) {
                    $('#emailgwpassword').attr('disabled', false);
                } else {
                    $('#emailgwpassword').attr('disabled', true);
                }

            }
            function changeActionSms() {
                var isChecked = document.getElementById("isCheckedsms").checked;
                if (isChecked === true) {
                    $('#smsPassword').attr('disabled', false);
                } else {
                    $('#smsPassword').attr('disabled', true);
                }

            }
            /**
             * enable password
             */
            function enablepasswordFileds() {
                $(".server input[type=password]").each(function () {
                    $(this).attr('disabled', false);
                });
            }
            function disablepasswordFileds() {
                $(".server input[type=password]").each(function () {
                    $(this).val("");
                    $(this).attr('disabled', true);
                });
            }
            function fieldEnable() {
                enableAll()
                disableSession()
                $('#btnsave1').hide();
                $('#btnreset1').hide();
                $('#btncancel1').hide();
                $('#btnedit1').show();

                $('#btnedit').hide();
                $('#btnsave').show();
                $('#btnreset').show();
                $('#btncnsl').show();

            }
            function fieldEnableSession() {
                resetServerConfForm()
                //                disableAll()

                $('#btncnsl').hide();
                $('#btnedit').show();
                enableAll();

                $('#btnedit1').hide();
                $('#btnsave').show();
                $('#btnreset1').show();
                $('#btncancel1').show();
                $('#btnreset').show();
            }
            function resetServerConfForm() {
                var token = $("input[name='RequstToken']").val();
                utilityManager.resetMessage();
                $.ajax({
                    url: '${pageContext.request.contextPath}/ResetDataservConf',
                    data: {RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                      
                        $stoken = data.token;
                        $("input[name='RequstToken']").val($stoken);
                        $('#thredMaxPool').val(data.thredMaxPool);
                        $('#thredMinPool').val(data.thredMinPool);
                        $('#backLogSize').val(data.backLogSize);
                        $('#maxQueueSize').val(data.maxQueueSize);
                        $('#vipStatus').val(data.vipStatus);
                        $('#vip').val(data.vip);
                        $('#iv').val(data.iv);
                        $('#hostFailStatus').val(data.hostFailStatus);
                        $('#replayAttackLevel').val(data.replayAttackLevel);
                        $('#log').val(data.log);
                        $('#configuration').val(data.configuration);
                        $('#logLevel').val(data.logLevel);
                        $('#numLogFiles').val(data.numLogFiles);
                        $('#logPath').val(data.logPath);
                        $('#logBackupStatus').val(data.logBackupStatus);
                        $('#logFileName').val(data.logFileName);
                        $('#coreDebugStatus').val(data.coreDebugStatus);
                        $('#psgPassword').val(data.psgPassword);
                        $('#hsmSlot').val(data.hsmSlot);
                        $('#esmStatus').val(data.esmStatus);
                        $('#monIp').val(data.monIp);
                        $('#monStatus').val(data.monStatus);
                        $('#port').val(data.port);
                        $('#timeOut').val(data.timeOut);
                        $('#smsUrl').val(data.smsUrl);
                        $('#emailgwurl').val(data.emailgwurl);
                        $('#emailgwport').val(data.emailgwport);
                        $('#servicePort').val(data.servicePort);
                        $('#smsUsername').val(data.smsUsername);
                        $('#smsPassword').val(data.smsPassword);
                        $('#clientTimeout').val(data.clientTimeout);
                        $('#emailgwuser').val(data.emailgwuser);
                        $('#emailgwpassword').val(data.emailgwpassword);
                        $('#pinCounter').val(data.pinCounter);

                        $('#adUrl').val(data.adUrl);
                        $('#adUsername').val(data.adUsername);
                        $('#adPassword').val(data.adPassword);
                        $('#numOfHistory').val(data.numOfHistory);
                        $('#adVerifyStatus').val(data.adVerifyStatus);
                        $('#autoRegStatus').val(data.autoRegStatus);
                        $('#smsNotifyStatus').val(data.smsNotifyStatus);
                        $('#ukptStat').val(data.ukptStat);

                        $('#buffer').val(data.buffer);
                        $('#smsTimeout').val(data.smsTimeout);
                        $('#smsPort').val(data.smsPort)


                        $('#divmsg').empty();
                    },
                    error: function (xhr, textStatus, errorThrown) {
                        if (xhr.responseText.includes("csrfError.jsp")) {
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                        }
                    }

                });
            }
            function resetForm() {
            }
            function cancelBtn() {
                disableAll()
                resetServerConfForm();
                $('#btnedit').show();
                $('#btnedit1').show();
                $('#btnsave').hide();
                $('#btnreset').hide();
                $('#btncancel1').hide();
                $('#divmsg').empty();
                utilityManager.resetMessage();

            }
            function cancelBtnsession() {
                utilityManager.resetMessage();
                disableSession()
                $('#btnedit1').show();
                $('#btnsave1').hide();
                $('#btnreset1').hide();
                $('#btncancel1').hide();
                $('#divmsg').empty();


            }
        </script>

    </head>

    <body>

        <s:set id="vadd" var="vadd"><s:property  value="vadd" default="true"/></s:set>
        <s:set id="vupdate" var="vupdate"><s:property value="vupdate" default="true"/></s:set>
        <s:set id="vdelete" var="vdelete"><s:property value="vdelete" default="true"/></s:set>
        <s:set id="vview" var="vview"><s:property value="vview" default="true"/></s:set>

            <section class="app-content">
            <jsp:include page="../../header.jsp" /> 

            <!-- Page content begin -->
            <div class="content innerpage">
                <!-- Breadcrumb begin -->
                <div class="breadcrumb">
                    <s:property value="Module"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i><span id="task" class="active"> <s:property value="Section"/> </span>
                </div>
                <!-- End -->

                <h1 class="page-title"><s:property value="Section"/><a href="#" class="lnk-back hide-element do-nothing"><i class="fa fa-arrow-left" aria-hidden="true"></i> back</a></h1>

                <s:form   theme="simple" id="serverConfId" method="POST" cssClass="server">
                    <div class="content-section data-form" id="addDiv" >
                        <s:hidden name="formtag" id="formtag" value="server" />
                        <div class="content-data">
                            <h2 class="section-title">Common Configuration</h2>
                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg" > 
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                            </div>
                            <!-- End -->
                            <div class="d-row">
                                <label class="col-1 form-label">Thread MAX Pool<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="thredMaxPool" name="thredMaxPool" cssClass="txt-input width-35 text-right"/>
                                </div>
                                <label class="col-3 form-label">Thread MIN Pool<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="thredMinPool" name="thredMinPool" cssClass="txt-input width-35 text-right"/>
                                </div> 
                            </div>                            
                            <div class="d-row">
                                <label class="col-1 form-label">MAX Queue Size<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="maxQueueSize" name="maxQueueSize" cssClass="txt-input width-35 text-right"/>
                                </div>                                                         
                                <label class="col-3 form-label">Backlog Size<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="backLogSize" name="backLogSize" cssClass="txt-input width-35 text-right"/>
                                </div>                                
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">VIP Status<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="vipStatus" headerKey="0" headerValue="--Select--"                                               
                                               listKey="key" listValue="value"
                                               list="%{vipStatusMap}" id="vipStatus" cssClass="ddl-input" />
                                </div>                                                          
                                <label class="col-3 form-label">AD Verify Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="adVerifyStatus" headerKey="0" headerValue="--Select--"                                               
                                               listKey="key" listValue="value"
                                               list="%{vipStatusMap}" id="adVerifyStatus" cssClass="ddl-input" />
                                </div>                                
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">VIP<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="vip" name="vip" cssClass="txt-input width-35 text-right"/>
                                </div>                                                          
                                <label class="col-3 form-label">IV<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="iv" name="iv" maxLength="16" cssClass="txt-input width-35 text-right"/>
                                </div>                                
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Host Fail Alert Status<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="hostFailStatus" headerKey="0" headerValue="--Select--"                                               
                                               listKey="key" listValue="value"
                                               list="%{hostFailStatusMap}" id="hostFailStatus" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">Replay Attack Level<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="replayAttackLevel" headerKey="0" headerValue="--Select--"                                               
                                               listKey="key" listValue="value"
                                               list="%{replayAttackLevelMap}" id="replayAttackLevel" cssClass="ddl-input"/>
                                </div>                                
                            </div> 
                            <div class="d-row">
                                <label class="col-1 form-label">Auto Registry Status<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="autoRegStatus" headerKey="0" headerValue="--Select--"                                               
                                               listKey="key" listValue="value"
                                               list="%{serviceStatusMap}" id="autoRegStatus" cssClass="ddl-input"/>
                                </div>
                                <label class="col-1 form-label">Maximum Buffer Size<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="buffer" name="buffer" cssClass="txt-input width-35 text-right"/>
                                </div>

                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">SMS/Email Notify Status<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="smsNotifyStatus" headerKey="0" headerValue="--Select--"                                               
                                               listKey="key" listValue="value"
                                               list="%{smsNotify}" id="smsNotifyStatus" cssClass="ddl-input"/>
                                </div>

                            </div> 
                        </div>    
                    </div>
                    <div class="content-section data-form"  >
                        <div class="content-data">
                            <h2 class="section-title">Log Configuration</h2>
                            <!-- Error and success message panel begin -->

                            <div class="d-row">
                                <label class="col-1 form-label">Log Level<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="logLevel" headerKey="0" headerValue="--Select--"                                               
                                               listKey="key" listValue="value"
                                               list="%{logLevelMap}" id="logLevel" cssClass="ddl-input"/>
                                </div>                                                          
                                <label class="col-3 form-label">Log Backup Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="logBackupStatus" headerKey="0" headerValue="--Select--"                                               
                                               listKey="key" listValue="value"
                                               list="%{logBackupStatusMap}" id="logBackupStatus" cssClass="ddl-input"/>
                                </div>                                
                            </div> 
                            <div class="d-row">
                                <label class="col-1 form-label">Log Backup Path<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="logPath" name="logPath" cssClass="txt-input width-35" />
                                </div>                                                         
                                <label class="col-3 form-label">Number of History Records<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="numOfHistory" name="numOfHistory" cssClass="txt-input width-35 text-right" />
                                </div>                                
                            </div> 
                            <div class="d-row">
                                <label class="col-1 form-label">Core Debug Status<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="coreDebugStatus" headerKey="0" headerValue="--Select--"                                               
                                               listKey="key" listValue="value"
                                               list="%{coreDebugStatusMap}" id="coreDebugStatus" cssClass="ddl-input" />
                                </div>                                

                                <label class="col-3 form-label">Log File Name<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="logFileName" name="logFileName" cssClass="txt-input width-35" />
                                </div>                                
                            </div> 
                            <div class="d-row">
                                <label class="col-1 form-label">No of Log Backup Files<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="numLogFiles" name="numLogFiles" cssClass="txt-input width-35 text-right" />
                                </div>                                
                            </div> 
                        </div>
                    </div>
                    <div class="content-section data-form"  >
                        <div class="content-data">
                            <h2 class="section-title">HSM Configuration</h2>
                            <!-- Error and success message panel begin -->
                            <!--                            <div class="d-row">
                                                            <label class="col-1 form-label">HSM PIN<sup class="required">*</sup></label>
                                                            <div class="col-2 form-field">
                            <s:password id="psgPassword" name="psgPassword" cssClass="txt-input width-35" />
                        </div>                                

                        <label class="col-3 form-label">HSM Slot<sup class="required">*</sup></label>
                        <div class="col-4 form-field">
                            <s:select  name="hsmSlot" headerKey="-1" headerValue="--Select--"                                               
                                       listKey="key" listValue="value"
                                       list="%{hsmSlotMap}" id="hsmSlot" cssClass="ddl-input text-right" />
                        </div>                                
                    </div>-->
                            <div class="d-row">
                                <label class="col-1 form-label">HSM Provider<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="esmStatus" headerKey="0" headerValue="--Select--"                                               
                                               listKey="key" listValue="value"
                                               list="%{monStatusMap}" id="esmStatus" cssClass="ddl-input" />
                                </div>         
                                <label class="col-3 form-label">UKPT Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="ukptStat" headerKey="-1" headerValue="--Select--"                                               
                                               listKey="key" listValue="value"
                                               list="%{ukptStatus}" id="ukptStat" cssClass="ddl-input" />
                                </div>
                            </div> 

                        </div>

                    </div>
                    <div class="content-section data-form"  >
                        <div class="content-data">
                            <h2 class="section-title">Dashboard Configuration</h2>
                            <!-- Error and success message panel begin -->
                            <div class="d-row">
                                <label class="col-1 form-label">IP<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="monIp" name="monIp" cssClass="txt-input width-35 text-right" />
                                </div>                                

                                <label class="col-3 form-label">Port<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="port" name="port" cssClass="txt-input width-35 text-right" />
                                </div>                                
                            </div>                           
                            <div class="d-row">
                                <label class="col-1 form-label">Timeout (ms)<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="timeOut" name="timeOut" cssClass="txt-input width-35 text-right" />
                                </div>                                

                                <label class="col-3 form-label">Monitor Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="monStatus" headerKey="0" headerValue="--Select--"                                               
                                               listKey="key" listValue="value"
                                               list="%{monStatusMap}" id="monStatus" cssClass="ddl-input" />
                                </div>                                
                            </div>                           

                        </div>

                    </div>
                    <div class="content-section data-form"  >
                        <s:hidden name="formtag" id="formtag" value="session" />
                        <div class="content-data">
                            <h2 class="section-title">Other Configuration</h2>
                            <!-- Error and success message panel begin -->
                            <!--                            <div class="d-row">
                                                            <label class="col-1 form-label">SMS Gateway URL<sup class="required">*</sup></label>
                                                            <div class="col-2 form-field">
                            <s:textfield id="smsUrl" name="smsUrl" cssClass="txt-input width-35" />
                        </div>                                

                        <label class="col-3 form-label">SMS Username<sup class="required">*</sup></label>
                        <div class="col-4 form-field">
                            <s:textfield id="smsUsername" name="smsUsername" cssClass="txt-input width-35" />
                        </div>                                
                    </div>                           
                    <div class="d-row">
                        <label class="col-1 form-label">SMS Password<sup class="required">*</sup></label>
                        <div class="col-2 form-field">
                            <s:password  id="smsPassword" name="smsPassword" cssClass="txt-input width-35" />
                        </div>                                

                        <label class="col-3 form-label">AD URL<sup class="required">*</sup></label>
                        <div class="col-4 form-field">
                            <s:textfield id="adUrl" name="adUrl" cssClass="txt-input width-35"  />
                        </div>                                
                    </div>                           
                            -->                            <div class="d-row">
                                <label class="col-1 form-label">SMS Service URL<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield  id="smsUrl" name="smsUrl" cssClass="txt-input width-35" />
                                </div>                                

                                <label class="col-3 form-label">SMS Service Timeout<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield  id="smsTimeout" name="smsTimeout" cssClass="txt-input width-35" />
                                </div>                                
                            </div>

                            <div class="d-row">
                                <label class="col-1 form-label">SMS Service User Name<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield  id="smsUsername" name="smsUsername" cssClass="txt-input width-35" />
                                </div>
                                
                                <label class="col-1 form-label">SMS Port<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield  id="smsPort" name="smsPort" cssClass="txt-input width-35" />
                                </div>
                            </div>

                            <div class="d-row">
                                <label class="col-1 form-label">Maximum Pin Counter<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="pinCounter" name="pinCounter" cssClass="txt-input width-35 text-right" />
                                </div>                                

                                <label class="col-3 form-label">Service Port<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="servicePort" name="servicePort" cssClass="txt-input width-35 text-right" />
                                </div>                                
                            </div>     
                            <div class="d-row">
                                <label class="col-1 form-label">Service Client Timeout (ms)<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="clientTimeout" name="clientTimeout" cssClass="txt-input width-35 text-right" />
                                </div>    
                                <label class="col-1 form-label">Email Gateway URL<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="emailgwurl" name="emailgwurl" cssClass="txt-input width-35 text-right" />
                                </div>   
                            </div>     
                            <div class="d-row">
                                <label class="col-1 form-label">Email Gateway port<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="emailgwport" name="emailgwport" cssClass="txt-input width-35 text-right" />
                                </div>     
                                <label class="col-1 form-label">Email Gateway User name<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="emailgwuser" name="emailgwuser" cssClass="txt-input width-35 text-right" />
                                </div> 
                            </div>     
                            <div class="d-row" style="margin-top: 15px;">
                                <label class="col-1 form-label" style="width: 220px;">Change Password(Email Gateway)<sup class="required">*</sup></label>

                                <div class="col-2 form-field" style="position: relative;top: -5px;">
                                    <s:checkbox label="checkboxpw" name="isChecked" id="isChecked" onclick="changeActionEmail()" cssClass="test-tl"/>
                                    <s:textfield type="password" id="emailgwpassword" name="emailgwpassword" cssClass="txt-input width-55 text-right test-tl wi" />
                                </div>                                
                            </div>     
                            <div class="d-row">
                                <label class="col-1 form-label" style="width: 220px;">Change Password (SMS Gateway)<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:checkbox label="checkboxpw" name="isCheckedsms" id="isCheckedsms" onclick="changeActionSms()" cssClass="test-tl"/>
                                    <s:textfield type="password" id="smsPassword" name="smsPassword" cssClass="txt-input width-55 text-right test-tl wi" />
                                </div>                              
                            </div>     
                            <div class="d-row cpanel four-col">
                                <label class="col-1">&nbsp;</label>
                                <div class="right-col">

                                    <s:url var="inserturl"/>
                                    <div class="btn-wrap" id="btnedit1">
                                        <i class="fa fa-pencil" aria-hidden="true"></i>
                                        <sj:submit href="%{inserturl}" 
                                                   button="true" disabled="#vupdate" 
                                                   cssClass="btn default-button" label="Edit"  
                                                   value="Edit"  onclick="fieldEnableSession()"  />
                                    </div>                                    

                                    <div class="btn-wrap" id="btnsave">
                                        <s:url  var="inserturl" action="UpdateservConf"/>
                                        <i class="fa fa-floppy-o" aria-hidden="true"></i>
                                        <sj:submit href="%{inserturl}" 
                                                   cssClass="btn default-button"
                                                   button="true" label="Save" 
                                                   targets="divmsg" value="Save" 
                                                   />
                                    </div>




                                    <div class="btn-wrap" id="btnreset"> 
                                        <i class="fa fa-times" aria-hidden="true"></i>
                                        <sj:submit button="true" 
                                                   cssClass="btn reset-button" 
                                                   value="Reset" id="btnreset1" 
                                                   onClick="resetServerConfForm(); return false;" />
                                    </div>

                                    <div class="btn-wrap" id="btncancel1" >
                                        <i class="fa fa-reply" aria-hidden="true"></i>
                                        <sj:submit button="true" 
                                                   value="Cancel" cssClass="btn default-button" 
                                                   onclick="cancelBtn()" />
                                    </div>

                                </div>
                            </div>    
                        </div>    
                    </div>

                </div>
                <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
            </s:form>
            <!--End of Body Content-->
        </div>
        <jsp:include page="../../footer.jsp" />

    </section>

    <script type="text/javascript">
        $(document).ready(function () {
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
        });


    </script>
</body>
</html>
