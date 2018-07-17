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
        <script type="text/javascript">

            $(document).ready(function () {
                $('#btnsave').hide();
                $('#btnreset').hide();
                $('#btncancel').hide();
            });
            function fieldEnable() {
                document.getElementById("status").disabled = false;
                document.getElementById("ip").disabled = false;
                document.getElementById("port").disabled = false;
                document.getElementById("percenntage").disabled = false;
                $('#btnedit').hide();
                $('#btnsave').show();
                $('#btnreset').show();
                $('#btncancel').show();

            }
            function resetLoadBalanceForm() {
                $.ajax({
                    url: '${pageContext.request.contextPath}/ResetDataloadConf',
                    data: {},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $('#status').val(data.status);
                        $('#ip').val(data.ip);
                        $('#port').val(data.port);
                        $('#percenntage').val(data.percenntage);
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
                document.getElementById("status").disabled = true;
                document.getElementById("ip").disabled = true;
                document.getElementById("port").disabled = true;
                document.getElementById("percenntage").disabled = true;
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
                            <li>Server Management</li>
                            <li><i class="fa fa-angle-double-right" aria-hidden="true"></i> 
                                Load Balance Configuration</li>
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

                        <s:form   theme="simple" id="loadbalanceid" method="POST">
                            <table border="0" style="white-space: nowrap;">
                                <tr>
                                    <td class="lable"align="left">Load Balance Status</td>
                                    <td class="lable">:</td>                                  
                                    <td align="left"><s:select  name="status"                                              
                                               listKey="key" listValue="value"
                                               list="%{statusMap}" id="status" cssClass="dropdown"  disabled="true"/></td>
                                </tr>
                                <tr>
                                    <td class="lable">Load Balance Node Ip<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td><s:textfield id="ip" name="ip" cssClass="text_field" disabled="true" /></td>
                                </tr>
                                <tr>
                                    <td class="lable">Load Balance Node Port<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td ><s:textfield id="port" name="port" cssClass="text_field" disabled="true"/></td>
                                </tr>
                                <tr>
                                    <td class="lable">Load Balance Sharing Percentage<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td ><s:textfield id="percenntage" name="percenntage" cssClass="text_field" disabled="true"/></td>
                                </tr>                                
                                <tr>
                                    <td colspan="4"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                </tr>
                                <tr>
                                    <td colspan="2"></td>
                                    <td align="left">
                                        <s:url var="inserturl"/>
                                        <sj:submit href="%{inserturl}" button="true" label="Edit"  value="Edit" id="btnedit" cssClass="add"  onclick="fieldEnable()" />

                                        <s:url action="UpdateloadConf" var="inserturl"/>
                                        <sj:submit href="%{inserturl}" button="true" label="Save" targets="divmsg" value="Save" id="btnsave" cssClass="add"/>
                                    
                                        <sj:submit button="true" value="Reset" id="btnreset" onClick="resetLoadBalanceForm(); return false;" cssClass="add"/>
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
