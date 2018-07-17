<%-- 
    Document   : sessionConf
    Created on : Sep 16, 2015, 2:51:53 PM
    Author     : ravideva
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"  %>  
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<html>
    <head>
        <jsp:include page="../../Styles.jsp" />  
        <script type="text/javascript">

            $(document).ready(function () {
                $('#btnsave').hide();
                $('#btnreset').hide();
                $('#btncancel').hide();
            });
            function fieldEnable() {
                document.getElementById("max_pin_counter").disabled = false;
                document.getElementById("replyAtchLvl").disabled = false;
                document.getElementById("altNotificationStatus").disabled = false;
                document.getElementById("txnFailNotificationStatus").disabled = false;
                document.getElementById("host_pro_timeout").disabled = false;
                document.getElementById("logLevel").disabled = false;
                document.getElementById("log_fname").disabled = false;
                document.getElementById("hsizeListeners").disabled = false;
                document.getElementById("hsizeChannels").disabled = false;
                document.getElementById("pkt_size_listeners").disabled = false;
                document.getElementById("pkt_size_channels").disabled = false;
                document.getElementById("requestTPDU").disabled = false;
                document.getElementById("responseTPDU").disabled = false;
                document.getElementById("monitor_ip").disabled = false;
                document.getElementById("monitor_port").disabled = false;
                document.getElementById("monitor_timeout").disabled = false;
                document.getElementById("channel_timeout").disabled = false;
                document.getElementById("htest_time_period").disabled = false;
                document.getElementById("tpduModifyOption").disabled = false;
                document.getElementById("tpduModifyPosition").disabled = false;
                $('#btnedit').hide();
                $('#btnsave').show();
                $('#btnreset').show();
                $('#btncancel').show();
            }
            function resetSessionConfForm() {
                $.ajax({
                    url: '${pageContext.request.contextPath}/ResetDatasessConf',
                    data: {},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $('#max_pin_counter').val(data.max_pin_counter);
                        $('#replyAtchLvl').val(data.replyAtchLvl);
                        $('#altNotificationStatus').val(data.altNotificationStatus);
                        $('#txnFailNotificationStatus').val(data.txnFailNotificationStatus);
                        $('#host_pro_timeout').val(data.host_pro_timeout);
                        $('#logLevel').val(data.logLevel);
                        $('#log_fname').val(data.log_fname);
                        $('#hsizeListeners').val(data.hsizeListeners);
                        $('#hsizeChannels').val(data.hsizeChannels);
                        $('#pkt_size_listeners').val(data.pkt_size_listeners);
                        $('#pkt_size_channels').val(data.pkt_size_channels);
                        $('#requestTPDU').val(data.requestTPDU);
                        $('#responseTPDU').val(data.responseTPDU);
                        $('#monitor_ip').val(data.monitor_ip);
                        $('#monitor_port').val(data.monitor_port);
                        $('#monitor_timeout').val(data.monitor_timeout);
                        $('#channel_timeout').val(data.channel_timeout);
                        $('#htest_time_period').val(data.htest_time_period);
                        $('#tpduModifyOption').val(data.tpduModifyOption);
                        $('#tpduModifyPosition').val(data.tpduModifyPosition);
                        $('#divmsg').empty();
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }

                });

            }
            function cancelBtn() {
                document.getElementById("max_pin_counter").disabled = true;
                document.getElementById("replyAtchLvl").disabled = true;
                document.getElementById("altNotificationStatus").disabled = true;
                document.getElementById("txnFailNotificationStatus").disabled = true;
                document.getElementById("host_pro_timeout").disabled = true;
                document.getElementById("logLevel").disabled = true;
                document.getElementById("log_fname").disabled = true;
                document.getElementById("hsizeListeners").disabled = true;
                document.getElementById("hsizeChannels").disabled = true;
                document.getElementById("pkt_size_listeners").disabled = true;
                document.getElementById("pkt_size_channels").disabled = true;
                document.getElementById("requestTPDU").disabled = true;
                document.getElementById("responseTPDU").disabled = true;
                document.getElementById("monitor_ip").disabled = true;
                document.getElementById("monitor_port").disabled = true;
                document.getElementById("monitor_timeout").disabled = true;
                document.getElementById("channel_timeout").disabled = true;
                document.getElementById("htest_time_period").disabled = true;
                document.getElementById("tpduModifyOption").disabled = true;
                document.getElementById("tpduModifyPosition").disabled = true;
                $('#btnedit').show();
                $('#btnsave').hide();
                $('#btnreset').hide();
                $('#btncancel').hide();
                $('#divmsg').empty();

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
                        </ul>
                    </div>
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

                            <s:form   theme="simple" id="sessionConfId" method="POST">                            
                                <table border="0" style="white-space: nowrap;">
                                    <tr>
                                        <td>
                                            <table>
                                                <tr>
                                                    <td class="lable">Max Pin Counter<span class="mandatory">*</span></td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="max_pin_counter" name="max_pin_counter" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable" align="left">Replay Attack Level<span class="mandatory"></span></td>
                                                    <td class="lable">:</td>
                                                    <td align="left"><s:select  name="replyAtchLvl" headerKey="1" 
                                                               listKey="key" listValue="value"
                                                               list="%{replyAtchLvlMap}" id="replyAtchLvl" cssClass="dropdown" disabled="true"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="lable" align="left">Alert Notification Status<span class="mandatory"></span></td>
                                                    <td class="lable">:</td>
                                                    <td align="left"><s:select  name="altNotificationStatus" headerKey="1" 
                                                               listKey="key" listValue="value"
                                                               list="%{altNotificationStatusMap}" id="altNotificationStatus" disabled="true" cssClass="dropdown"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="lable" align="left">Transacting Fail Notification Status<span class="mandatory"></span></td>
                                                    <td class="lable">:</td>
                                                    <td align="left"><s:select  name="txnFailNotificationStatus" headerKey="1" 
                                                               listKey="key" listValue="value"
                                                               list="%{txnFailNotificationStatusMap}" id="txnFailNotificationStatus" disabled="true" cssClass="dropdown"/>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Host Processing Timeout(ms)<span class="mandatory">*</span></td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="host_pro_timeout" name="host_pro_timeout" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable" align="left">Log Level<span class="mandatory"></span></td>
                                                    <td class="lable">:</td>
                                                    <td align="left"><s:select  name="logLevel" headerKey="1" 
                                                               listKey="key" listValue="value"
                                                               list="%{logLevelMap}" id="logLevel" disabled="true" cssClass="dropdown"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Log File Name<span class="mandatory">*</span></td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="log_fname" name="log_fname" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable" align="left">Header Size Listeners<span class="mandatory"></span></td>
                                                    <td class="lable">:</td>
                                                    <td align="left"><s:select  name="hsizeListeners" headerKey="1" 
                                                               listKey="key" listValue="value"
                                                               list="%{hsizeListenersMap}" id="hsizeListeners" disabled="true" cssClass="dropdown"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable" align="left">Header Size Channels<span class="mandatory"></span></td>
                                                    <td class="lable">:</td>
                                                    <td align="left"><s:select  name="hsizeChannels" headerKey="1" 
                                                               listKey="key" listValue="value"
                                                               list="%{hsizeChannelsMap}" id="hsizeChannels" disabled="true" cssClass="dropdown"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Packet Size Listeners<span class="mandatory">*</span></td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="pkt_size_listeners" name="pkt_size_listeners" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td style="width: 25px"></td>
                                        <td>
                                            <table>


                                                <tr>
                                                    <td class="lable">Packet Size Channels<span class="mandatory">*</span></td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="pkt_size_channels" name="pkt_size_channels" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable" align="left">Request TPDU<span class="mandatory"></span></td>
                                                    <td class="lable">:</td>
                                                    <td align="left"><s:select  name="requestTPDU" headerKey="1" 
                                                               listKey="key" listValue="value"
                                                               list="%{requestTPDUMap}" id="requestTPDU" disabled="true" cssClass="dropdown"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable" align="left">Response TPDU<span class="mandatory"></span></td>
                                                    <td class="lable">:</td>
                                                    <td align="left"><s:select  name="responseTPDU" headerKey="1" 
                                                               listKey="key" listValue="value"
                                                               list="%{responseTPDUMap}" id="responseTPDU" disabled="true" cssClass="dropdown"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Monitor IP<span class="mandatory">*</span></td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="monitor_ip" name="monitor_ip" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Monitor Port<span class="mandatory">*</span></td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="monitor_port" name="monitor_port" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Monitor Timeout(ms)<span class="mandatory">*</span></td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="monitor_timeout" name="monitor_timeout" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Channel Time Out<span class="mandatory">*</span></td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="channel_timeout" name="channel_timeout" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Host Test Time Period(ms)<span class="mandatory">*</span></td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="htest_time_period" name="htest_time_period" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable" align="left">TPDU modify option<span class="mandatory"></span></td>
                                                    <td class="lable">:</td>
                                                    <td align="left"><s:select  name="tpduModifyOption" headerKey="1" 
                                                               listKey="key" listValue="value"
                                                               list="%{tpduModifyOptionMap}" id="tpduModifyOption" disabled="true" cssClass="dropdown"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable" align="left">TPDU modify position<span class="mandatory"></span></td>
                                                    <td class="lable">:</td>
                                                    <td align="left"><s:select  name="tpduModifyPosition" headerKey="1" 
                                                               listKey="key" listValue="value"
                                                               list="%{tpduModifyPositionMap}" id="tpduModifyPosition" disabled="true" cssClass="dropdown"/></td>
                                                </tr>  
                                            </table>
                                        </td>
                                    </tr>


                                    <tr>
                                        <td colspan="4"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                    </tr>
                                    <tr>                                    
                                        <td align="left">
                                            <s:url var="inserturl"/>
                                            <sj:submit href="%{inserturl}" button="true" label="Edit"  value="Edit" id="btnedit" onclick="fieldEnable()" />

                                            <s:url action="" var="inserturl" action="UpdatesessConf"/>
                                            <sj:submit href="%{inserturl}" button="true" label="Save" targets="divmsg" value="Save" id="btnsave" />

                                            <sj:submit button="true" value="Reset" id="btnreset" onClick="resetSessionConfForm(); return false;" />
                                            <sj:submit button="true" value="Cancel" id="btncancel" onclick="cancelBtn()" />
                                        </td> 
                                    </tr>
                                </table>
                            </s:form>
                        </div>
                    </div>

                </div>
                <!--End of Body Content-->


                <jsp:include page="../../footer.jsp" />
            </div><!--End of Wrapper-->
    </body>
</html>
