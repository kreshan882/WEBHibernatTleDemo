<%-- 
    Document   : channelManager
    Created on : Sep 16, 2015, 2:51:13 PM
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
                $('#agroupName').val("");
                $('#ahostName').val("");
                $('#contime').val("");
                $('#readtime').val("");
                $('#aip').val("");
                $('#channelName').val("");
                $('#aport').val("");
                $('#atimeout').val("");
                $('#astatus').val("1");
                $('#aconType').val("1");
                $('#aforwerdMathod').val("1");
                $('#atestPackStatus').val("1");
                $('#headerFormat').val("1");
                $('#loadBalance').val("2");
                $('#isoFile').val("1");
                $('#sip').val("");
                $('#sport').val("");
                $('#sstatus').val("1");
                $('#tpdu').val("1");
                $('#PinTrans').val("1");
                $('#ecnryptZPK').val("");
                document.getElementById('ecnryptZPK').disabled = false;
                jQuery("#gridtable").trigger("reloadGrid");

                $('#nii').val("");
                $('#mapNii').val("");
                $('#tlestatus').val("1");

//                $('#upchannelId').val("");
//                $('#upchannelName').val("");
//                $('#upip').val("");
//                $('#upport').val("");
//                $('#upenzpk').val("");
//                $('#uptimeout').val("");
//                $('#upstatus').val("1");
//                $('#upconType').val("1");
//                $('#upforwerdMathod').val("1");
//                $('#uptestPackStatus').val("1");
//                $('#upcontime').val("");
//                $('#upreadtime').val("");

                jQuery("#gridtable1").trigger("reloadGrid");

                document.getElementById('sip').disabled = true;
                document.getElementById('sport').disabled = true;
                document.getElementById('operMethod').disabled = true;
            }
            function resetFormFull() {                
                resetForm();
                utilityManager.resetMessage();
                $('.errorpanel').hide();
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

            $.subscribe('onclicksearch', function (event, data) {
                var searchName = $('#searchName').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {searchName: searchName}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            });

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' disabled='#vupdate' title='Edit Channel' onClick='editNiiMap(&#34;" + rowObject.channelId + "&#34;)'><i class='fa fa-pencil' aria-hidden='true'></i></a> <a href='#' disabled='#vdelete' title='Delete Channel' onClick='deleteNiimapInit(&#34;" + rowObject.channelId + "&#34;,&#34;" + rowObject.channelName + "&#34;)'><i class='fa fa-trash-o' aria-hidden='true'></i></a>";
            }

            function deleteNiimapInit(niivalue, name) {
                $("#deleteConfirmDialog").data('channelId', niivalue).dialog('open');
                $("#deleteConfirmDialog").html('<p">Please confirm delete : ' + name + "</p>");
                return false;
            }

            function BindStatusformatter(cellvalue, options, rowObject) {
                if (rowObject.status == 1) {
                    if (cellvalue == 1) {
                        return "<i class='fa fa-circle active' aria-hidden='true'></i>";
                    } else if(cellvalue == 2){
                        return "<i class='fa fa-circle critical' aria-hidden='true'></i>";
                    }else {
                        return "<i class='fa fa-circle' aria-hidden='true'></i>";
                    }
                } else {
                    return "<i class='fa fa-circle' aria-hidden='true'></i>";
                }
            }

            function Statusformatter(cellvalue) {
                if (cellvalue == 1) {
                    return "<i class='fa fa-circle active' aria-hidden='true'></i>";
                } else {
                    return "<i class='fa fa-circle' aria-hidden='true'></i>";
                }
            }

            function deleteNiimap(niivalue) {
                var token=$( "input[name='RequstToken']" ).val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeletechanMng',
                    data: {channelId: niivalue, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $('.errorpanel').show();
                        if (data.isDeleted == true) {
                            utilityManager.showMessage('.del-user-msg', data.dmessage, 'successmsg', $stoken);
                        } else {
                            utilityManager.showMessage('.del-user-msg', data.dmessage, 'errormsg', $stoken);
                        }

//                        utilityManager.showMessage('.del-user-msg', 'data.dmessage', 'errormsg');
//                        $('.frozen-div.ui-state-default.ui-jqgrid-hdiv').attr('style','top: 68px !important');
//                        $('.frozen-bdiv.ui-jqgrid-bdiv').attr('style','top: 93px !important');
                        //$(".del-user-msg").show();
                        //jQuery("#gridtable").trigger("reloadGrid");
                        //$('.frozen-bdiv').css({'top':'61px'});
                        //$('#gridtable_frozen').css({'top':'86px'});

                        jQuery("#gridtable").trigger("reloadGrid");
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });
            }


            function editNiiMap(niiVal) {
                var token=$( "input[name='RequstToken']" ).val();
                $('#addnewhost').hide();
                $('#editniimap').show();
                $('#task').empty();
                var text = ' Edit Channel';
                $('#task').append(text);
                $('.lnk-back').removeClass('hide-element');
                utilityManager.resetMessage();
                $('.errorpanel').hide();
                $.ajax({
                    url: '${pageContext.request.contextPath}/FindchanMng',
                    data: {upchannelId: niiVal, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $( "input[name='RequstToken']" ).val($stoken);
                        $('#editniimap').show();
                        $('#addnewhost').hide();
                        $('#upgroupName').val(data.upgroupName).attr('readOnly', true);
                        $('#upchannelName').val(data.upchannelName);
                        $('#upip').val(data.upip);
                        $('#upport').val(data.upport);
                        $('#upchannelId').val(data.upchannelId);
                        $('#uptimeout').val(data.uptimeout);
                        $('#upcontime').val(data.upcontime);
                        $('#upreadtime').val(data.upreadtime);
                        $('#upstatus').val(data.upstatus);
                        $('#upconType').val(data.upconType);
                        $('#upforwerdMathod').val(data.upforwerdMathod);
                        $('#uptestPackStatus').val(data.uptestPackStatus);
                        $('#upenzpk').val(data.upenzpk);

                        $("#upisoFile option:contains(" + data.upisoFile + ")").prop("selected", true);
                        $('#upTPDUStatus').val(data.upTPDUStatus);
                        $('#uploadBalance').val(data.uploadBalance);
                        $('#upsecIp').val(data.upsecIp);
                        $('#upsecPort').val(data.upsecPort);
                        $('#upsecStatus').val(data.upsecStatus);
                        $('#upheaderFormat').val(data.upheaderFormat);
                        $('#upTPDUStatus').val(data.upTPDUStatus);
                        $('#upheaderSize').val(data.upheaderSize);
                        $('#upPinTrans').val(data.upPinTrans);
                        $('#upKeepAlive').val(data.upKeepAlive);
                        
                        $('#HidUpName').val(data.upchannelName);
                        $('#HidUpip').val(data.upip);
                        $('#hidUpPort').val(data.upport);
                        $('#HidUpsecIp').val(data.upsecIp);
                        $('#HidUpsecPort').val(data.upsecPort);
                        secondaryValues();
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
                $('.errorpanel').hide();
                var upgroupid = $('#upchannelId').val();
                editNiiMap(upgroupid);
            }

            function backToMain() {
                $('#editniimap').hide();
                $('#addnewhost').show();
                utilityManager.resetMessage();
                $('.errorpanel').hide();
                $('#task').empty();
                var symb = '<i class="fa fa-angle-double-right" aria-hidden="true"></i>';
                var text = ' Add Channel';
                $('#task').append(symb, text);
                jQuery("#gridtable").trigger("reloadGrid");

            }
            function secondaryValues() {
                var status = $('#loadBalance').val();
                var upstatus = $('#uploadBalance').val();
                var upload = $('#upconType').val();
                var load = $('#aconType').val();
                var pinTrans = $('#PinTrans').val();
                var upPinTrans = $('#upPinTrans').val();
                if (status == 1) {
                    document.getElementById('sip').disabled = false;
                    document.getElementById('sport').disabled = false;
                    document.getElementById('operMethod').disabled = false;
                } else {
                    document.getElementById('sip').disabled = true;
                    document.getElementById('sport').disabled = true;
                    document.getElementById('operMethod').disabled = true;
                }
                if (upstatus == 1) {
                    document.getElementById('upsecIp').disabled = false;
                    document.getElementById('upsecPort').disabled = false;
                    document.getElementById('upoperMethod').disabled = false;
                } else {
                    document.getElementById('upsecIp').disabled = true;
                    document.getElementById('upsecPort').disabled = true;
                    document.getElementById('upoperMethod').disabled = true;
                }
                if (load == 1) {
                    document.getElementById('loadBalance').disabled = false;
                } else {
                    document.getElementById('loadBalance').disabled = true;
                    document.getElementById('sip').disabled = true;
                    document.getElementById('sport').disabled = true;
                    document.getElementById('operMethod').disabled = true;
                }
                if (upload == 1) {
                    document.getElementById('uploadBalance').disabled = false;
                } else {
                    document.getElementById('uploadBalance').disabled = true;
                    document.getElementById('upsecIp').disabled = true;
                    document.getElementById('upsecPort').disabled = true;
                    document.getElementById('upoperMethod').disabled = true;
                }
                if (pinTrans == 1) {
                    document.getElementById('ecnryptZPK').disabled = false;
                } else {
                    document.getElementById('ecnryptZPK').disabled = true;
                }
                if (upPinTrans == 1) {
                    document.getElementById('upenzpk').disabled = false;
                } else {
                    document.getElementById('upenzpk').disabled = true;
                }

            }

            function NIIAssignformatter(cellvalue, options, rowObject) {
                return "<a href='#' title='Assign NII' onClick='javascript:viewNII(&#34;" + cellvalue + "&#34;,&#34;" + rowObject.channelName + "&#34;)'><i class='fa fa-share-square-o' aria-hidden='true'></i></a>";
            }
            function viewNIIPopupClose() {
                $("#viewdialog").dialog('close');
            }
            function viewNII(id, Channel) {
                $("#viewdialog").data('Id', id);
                $("#viewdialog").data('ChannelName', Channel).dialog('open');
            }

            function facilityPopupClose() {
                $("#viewdialog").dialog('close');
            }
            

            $.subscribe('openview', function (event, data) {
                utilityManager.resetMessage();
                $('.errorpanel').hide();
//                resetData();
                var $led = $("#viewdialog");
                var token=$( "input[name='RequstToken']" ).val();
//                alert($led.data('Id').replace(/ /g,"_"));
                $led.load("AssignNIIchanMng?RequstToken="+token+"&ChannelId=" + $led.data('Id') + "&cchannel=" + $led.data('ChannelName').replace(/ /g, "_"));
            });
            $.subscribe('closeview', function (event, data) {
                utilityManager.resetMessage();
                $('.errorpanel').hide();
            });

        </script>

    </head>

    <s:set id="vadd" var="vadd"><s:property  value="vadd" default="true"/></s:set>
    <s:set var="vupdate"><s:property value="vupdate" default="true"/></s:set>
    <s:set var="vdelete"><s:property value="vdelete" default="true"/></s:set>
    <s:set var="vview"><s:property value="view" default="true"/></s:set>

        <body onload="resetFormFull()">
            <section class="app-content">
            <jsp:include page="../../header.jsp" /> 

            <div class="content innerpage">
                <!-- Breadcrumb begin -->
                <div class="breadcrumb">
                    <s:property value="Module"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i> <s:property value="Section"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i><span id="task" class="active">Add Channel</span>
                </div>
                <!-- End -->


                <!-- Page title begin -->
                <h1 class="page-title"><s:property value="Section"/><a href="#" class="lnk-back hide-element do-nothing"><i class="fa fa-arrow-left" aria-hidden="true"></i> back</a></h1>
                <!-- End -->

                <div class="content-section data-form" id="addnewhost">
                    <s:form theme="simple" method="post" id="AddForm" name="AddForm">
                        <div class="content-data">

                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg">
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i> <span id="divmsg"></span></div>
                            </div>
                            <!-- End -->

                            <!-- Four colum form row begin -->
                            <div class="d-row">
                                <label class="col-1 form-label">Channel Name<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="channelName" name="channelName" cssClass="txt-input width-35" maxLength="50"/>
                                </div>
                                <label class="col-3 form-label">IP<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="aip" name="aip" cssClass="txt-input width-35" maxLength="15"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Port<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="aport" name="aport" cssClass="txt-input width-35" maxLength="5"/>
                                </div>
                                <label class="col-3 form-label">Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="astatus" id="astatus" list="%{astatusMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Connection Type<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="aconType" id="aconType" list="%{aconTypeMap}" headerKey="1" 
                                               listKey="key" listValue="value" onchange="secondaryValues()" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">Forward Method<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="aforwerdMathod" id="aforwerdMathod" list="%{aforwerdMathodMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Header Format<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="headerFormat" id="headerFormat" list="%{headerFormatMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">Header Size<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="headerSize" id="headerSize" list="%{headerSizeMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Connection Timeout(ms)<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="contime" name="contime" cssClass="txt-input width-35" maxLength="8"/>
                                </div>
                                <label class="col-3 form-label">Read Timeout(ms)<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="readtime" name="readtime" cssClass="txt-input width-35" maxLength="8"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">ISO File<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="isoFile" id="isoFile" list="%{isoTypeMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">TPDU Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="tpdu" id="tpdu" list="%{astatusMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">PIN Translate Status<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="PinTrans" id="PinTrans" list="%{astatusMap}" headerKey="1"
                                               listKey="key" listValue="value" onchange="secondaryValues()" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">Encrypt ZPK<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="ecnryptZPK" name="ecnryptZPK" cssClass="txt-input width-35" maxLength="32"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Keep Alive Status<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="KeepAlive" id="KeepAlive" list="%{keepAliveStatus}" headerKey="1"
                                               listKey="key" listValue="value" cssClass="ddl-input"  onchange="secondaryValues()"/>
                                </div>
                                <label class="col-3 form-label">Load Balance<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="loadBalance" id="loadBalance" list="%{astatusMap}" headerKey="1"
                                               listKey="key" listValue="value" cssClass="ddl-input"  onchange="secondaryValues()"/>
                                </div>

                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Secondary IP<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="sip" name="sip" cssClass="txt-input width-35" maxLength="15"/>
                                </div>
                                <label class="col-3 form-label">Secondary Port<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="sport" name="sport" cssClass="txt-input width-35" maxLength="15"/>
                                </div>

                            </div>
                            <div class="d-row">

                                <label class="col-1 form-label">Operation Method<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="operMethod" id="operMethod" list="%{operMethodMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>

                            </div>
                            <!-- End -->

                            <div class="d-row cpanel four-col">
                                <label class="col-1">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="addnewhosturl" action="AddchanMng"/>                                   
                                    <div class="btn-wrap lnk-match"><i class="fa fa-plus" aria-hidden="true"></i><sj:submit href="%{addnewhosturl}" targets="divmsg" value="Add"  button="true" disabled="#vadd" cssClass="btn default-button" /></div>

                                    <div class="btn-wrap lnk-match"><i class="fa fa-times" aria-hidden="true"></i><sj:submit button="true" value="Reset" onClick="resetFormFull()" cssClass="btn reset-button"/></div>
                                </div>
                            </div>

                        </div>
                    <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                    </s:form>     
                </div>

                <div class="content-section data-form" id="editniimap" style="display: none;">
                    <s:form theme="simple" method="post" id="UpdateForm" name="UpdateForm">
                        <div class="content-data">

                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg">
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                            </div>
                            <!-- End -->

                            <!-- Four colum form row begin -->
                            <div class="d-row">
                                <s:hidden id="upchannelId" name="upchannelId"  />
                                <label class="col-1 form-label">Channel Name<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="upchannelName" name="upchannelName" cssClass="txt-input width-35" />
                                    <s:hidden id="HidUpName" name="HidUpName" cssClass="txt-input width-35" />
                                </div>
                                <label class="col-3 form-label">IP<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="upip" name="upip" cssClass="txt-input width-35" maxLength="15"/>
                                    <s:hidden id="HidUpip" name="HidUpip" cssClass="txt-input width-35" maxLength="15"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Port<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="upport" name="upport" cssClass="txt-input width-35" maxLength="15"/>
                                    <s:hidden id="hidUpPort" name="hidUpPort" cssClass="txt-input width-35" maxLength="15"/>
                                </div>
                                <label class="col-3 form-label">Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="upstatus" id="upstatus" list="%{astatusMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Connection Type<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="upconType" id="upconType" list="%{aconTypeMap}" headerKey="1" 
                                               listKey="key" listValue="value" onchange="secondaryValues()" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">Forward Method<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="upforwerdMathod" id="upforwerdMathod" list="%{aforwerdMathodMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Header Format<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="upheaderFormat" id="upheaderFormat" list="%{headerFormatMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">Header Size<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="upheaderSize" id="upheaderSize" list="%{headerSizeMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Connection Timeout(ms)<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="upcontime" name="upcontime" cssClass="txt-input width-35" maxLength="15"/>
                                </div>
                                <label class="col-3 form-label">Read Timeout(ms)<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="upreadtime" name="upreadtime" cssClass="txt-input width-35" maxLength="8"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">ISO File<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="upisoFile" id="upisoFile" list="%{isoTypeMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">TPDU Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="upTPDUStatus" id="upTPDUStatus" list="%{astatusMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">PIN Translate Status<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="upPinTrans" id="upPinTrans" list="%{astatusMap}" headerKey="1"
                                               listKey="key" listValue="value" onchange="secondaryValues()" cssClass="ddl-input"/>
                                </div>
                                <label class="col-3 form-label">Encrypt ZPK<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="upenzpk" name="upenzpk" cssClass="txt-input width-35" maxLength="32"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Keep Alive Status<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="upKeepAlive" id="upKeepAlive" list="%{keepAliveStatus}" headerKey="1"
                                               listKey="key" listValue="value" cssClass="ddl-input"  onchange="secondaryValues()"/>
                                </div>
                                <label class="col-3 form-label">Load Balance<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="uploadBalance" id="uploadBalance" list="%{astatusMap}" headerKey="1"
                                               listKey="key" listValue="value" cssClass="ddl-input"  onchange="secondaryValues()"/>
                                </div>

                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Secondary IP<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield id="upsecIp" name="upsecIp" cssClass="txt-input width-35" maxLength="15"/>
                                    <s:hidden id="HidUpsecIp" name="HidUpsecIp" cssClass="txt-input width-35" maxLength="15"/>
                                </div>
                                <label class="col-3 form-label">Secondary Port<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:textfield id="upsecPort" name="upsecPort" cssClass="txt-input width-35" maxLength="15"/>
                                    <s:hidden id="HidUpsecPort" name="HidUpsecPort" cssClass="txt-input width-35" maxLength="15"/>
                                </div>

                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Operation Method<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="upoperMethod" id="upoperMethod" list="%{operMethodMap}" headerKey="1" 
                                               listKey="key" listValue="value"  cssClass="ddl-input"/>
                                </div>

                            </div>
                            <!-- End -->

                            <div class="d-row cpanel four-col">
                                <label class="col-1">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="updateuserurl" action="UpdatechanMng"/>                                   
                                    <div class="btn-wrap lnk-match"><i class="fa fa-pencil-square-o" aria-hidden="true"></i><sj:submit  href="%{updateuserurl}"  targets="divmsg" value="Update"  button="true" disabled="#vupdate" cssClass="btn default-button" /></div>
                                    <div class="btn-wrap lnk-match"><i class="fa fa-times" aria-hidden="true"></i><sj:submit button="true" value="Reset" onClick="resetUpdateForm()" cssClass="btn reset-button"/></div>
                                </div>
                            </div>

                        </div>
                    <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                    </s:form>     
                </div>

                <div class="content-section errorpanel">
                    <div class="content-data">
                        <!-- Error and success message panel begin -->
                        <div class="msg-panel del-user-msg">
                            <div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                        </div>
                    </div>
                </div>

                <!-- Grid data begin -->
                <div class="content-section">
                    <div class="content-data">
                        <h2 class="section-title">All Registered Channels</h2>
                        <!-- Error and success message panel begin -->
                        <!-- <div class="msg-panel del-user-msg">
                            <div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                        </div> -->
                        <!-- End -->

                        <div id="tablediv" class="custom-grid" style="position: relative;">

                            <sj:dialog 
                                id="viewdialog" 
                                buttons="{
                                'OK':function() { utilityManager.resetMessage();$( this ).dialog('close');}                                    
                                }" 
                                autoOpen="false" 
                                modal="true"                            
                                width="1000"
                                height="500"
                                position="center"
                                title="Add NII"
                                onOpenTopics="openview"
                                onCloseTopics="closeview"
                                loadingText="Loading .."
                                />

                            <sj:dialog 
                                id="deleteConfirmDialog" 
                                buttons="{ 
                                'OK':function() { deleteNiimap($(this).data('channelId'));$( this ).dialog( 'close' ); },
                                'Cancel':function() { $( this ).dialog( 'close' );} 
                                }" 
                                autoOpen="false" 
                                modal="true" 
                                title="Delete Channel Confirmation"
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
                                title="Delete nii" 
                                width="400"
                                height="150"
                                position="center"
                                />
                            <s:url var="listurl" action="ListchanMng"/>
                            <sjg:grid
                                id="gridtable"
                                caption="Channel Management"
                                dataType="json"
                                href="%{listurl}"
                                pager="true"
                                gridModel="gridModel"
                                rowList="10,15,20"
                                rowNum="10"
                                shrinkToFit="false"
                                autowidth="true"
                                rownumbers="true"
                                onCompleteTopics="completetopics"
                                rowTotal="false"
                                viewrecords="true"
                               
                                >

                                <sjg:gridColumn name="bindStatus" index="epicTleStatusByBindStatus.description" title="Primary Status" align="center" width="100" formatter="BindStatusformatter" frozen="true" sortable="true"/> 
                                <sjg:gridColumn name="secStatus" index="epicTleStatusByBindSecondaryStatus.description" title="Secondary Status" align="center" width="120" formatter="BindStatusformatter" frozen="true" sortable="true"/> 
                                <sjg:gridColumn title="Channel Name" name="channelId" index="channelId" hidden="true" align="left" width="100" frozen="true" sortable="true"/>  
                                <sjg:gridColumn name="channelName" index="channelName" title="Channel Name" align="left" width="100" frozen="true" sortable="true"  /> 
                                <sjg:gridColumn name="channelId" title="Assign NII" align="Center" width="100" formatter="NIIAssignformatter" frozen="true" sortable="false" cssClass="action-col" /> 
                                <sjg:gridColumn name="ip" index="hostip" title="IP"  align="left" width="100" frozen="true" sortable="true"/>
                                <sjg:gridColumn name="port" index="hostport" title="Port" align="left" width="100" sortable="true"/> 
                                <sjg:gridColumn name="connectiontype" index="connectedtype" title="Connection Type" align="left" width="150" sortable="true"/> 
                                <sjg:gridColumn name="contime" index="conTimeout" title="Connection Timeout (ms)" align="left" width="150" sortable="true"/> 
                                <sjg:gridColumn name="readtime" index="readTimeout" title="Read Timeout (ms)" align="left" width="150" sortable="true"/> 
                                <sjg:gridColumn name="keepAlive" index="epicTleStatusByKeepAliveStatus.description" title="Keep Alive Status" align="center" width="150" sortable="true"/> 
                                <sjg:gridColumn name="routemethod" index="epicTleForwardmethod.description" title="Route Method" align="left" width="150" sortable="true"/> 
                                <sjg:gridColumn name="isoFile" index="isoFile" title="ISO File" align="left" width="80" sortable="true"/> 
                                <sjg:gridColumn name="TPDUStatus" index="epicTleStatusByTpduStatus.description" title="TPDU Status" align="center" width="100" sortable="true"/> 
                                <sjg:gridColumn name="loadBalance" index="epicTleStatusByLoadbalancestatus.description" title="Load Balance" align="center" width="100" sortable="true"/> 
                                <sjg:gridColumn name="pinTranslate" index="epicTleStatusByPintranslateStatus.description" title="PIN Translate" align="center" width="100" sortable="true"/> 
                                <sjg:gridColumn name="operMethod" index="epicTleChannelOperationMethod.code" title="Operation Method" align="left" width="100" sortable="true"/> 
                                <sjg:gridColumn name="secIp" index="secondaryIp" title="Secondary IP" align="left" width="100" sortable="true"/> 
                                <sjg:gridColumn name="secPort" index="secondaryPort" title="Secondary Port" align="left" width="100" sortable="true"/> 
                                <sjg:gridColumn name="status" index="epicTleStatusByStatus.description" title="Status" align="center" width="80" formatter="Statusformatter" sortable="true"/> 
                                <sjg:gridColumn name="groupName"  title="Action"  width="80" align="center" formatter="editformatter" sortable="false" cssClass="action-col"/>

                            </sjg:grid> 
                        </div>
                    </div>
                </div>
                <!-- End -->

            </div>
            <jsp:include page="../../footer.jsp" />
        </section>

        <script type="text/javascript">
            $(document).ready(function () {
                //Back button event
                $('.lnk-back').on('click', function () {
                    backToMain();
                    $('#task').empty();
                    $('.lnk-back').addClass('hide-element');
                    var text = 'Add Channel';
                    $('#task').append(text);
                    return false;
                });

                setTimeout(function () {
                    $(window).trigger('resize');
                }, 500);

            });



        </script>

    </body>
</html>
