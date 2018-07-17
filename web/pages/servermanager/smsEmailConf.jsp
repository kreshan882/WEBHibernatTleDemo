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
                document.getElementById("smsip").disabled = false;
                document.getElementById("smsport").disabled = false;
                document.getElementById("smstimeout").disabled = false;
                document.getElementById("emailip").disabled = false;
                document.getElementById("emailport").disabled = false;
                document.getElementById("emaildomain").disabled = false;
                $('#btnedit').hide();
                $('#btnsave').show();
                $('#btnreset').show();
                $('#btncancel').show();

            }
            function resetSmsEmailConfForm() {
                $.ajax({
                    url: '${pageContext.request.contextPath}/ResetDatasmseConf',
                    data: {},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $('#smsip').val(data.smsip);
                        $('#smsport').val(data.smsport);
                        $('#smstimeout').val(data.smstimeout);
                        $('#emailip').val(data.emailip);
                        $('#emailport').val(data.emailport);
                        $('#emaildomain').val(data.emaildomain);
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
                document.getElementById("smsip").disabled = true;
                document.getElementById("smsport").disabled = true;
                document.getElementById("smstimeout").disabled = true;
                document.getElementById("emailip").disabled = true;
                document.getElementById("emailport").disabled = true;
                document.getElementById("emaildomain").disabled = true;
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

                            <s:form   theme="simple" id="loadbalanceid" method="POST">
                                <table border="0" style="white-space: nowrap;">
                                    <tr>
                                        <td class="lable">SMS IP<span class="mandatory">*</span></td>
                                        <td class="lable">:</td>
                                        <td colspan="2"><s:textfield id="smsip" name="smsip" cssClass="text_field"  disabled="true" /></td>
                                    </tr>
                                    <tr>
                                        <td class="lable">SMS Port<span class="mandatory">*</span></td>
                                        <td class="lable">:</td>
                                        <td colspan="2"><s:textfield id="smsport" name="smsport" cssClass="text_field" disabled="true"/></td>
                                    </tr>
                                    <tr>
                                        <td class="lable">SMS Time Out<span class="mandatory">*</span></td>
                                        <td class="lable">:</td>
                                        <td colspan="2"><s:textfield id="smstimeout" name="smstimeout" cssClass="text_field" disabled="true"/></td>
                                    </tr>      
                                    <tr>
                                        <td class="lable">Email IP<span class="mandatory">*</span></td>
                                        <td class="lable">:</td>
                                        <td colspan="2"><s:textfield id="emailip" name="emailip" cssClass="text_field" disabled="true" /></td>
                                    </tr>
                                    <tr>
                                        <td class="lable">Email Port<span class="mandatory">*</span></td>
                                        <td class="lable">:</td>
                                        <td colspan="2"><s:textfield id="emailport" name="emailport" cssClass="text_field" disabled="true"/></td>
                                    </tr>
                                    <tr>
                                        <td class="lable">Email Domain<span class="mandatory">*</span></td>
                                        <td class="lable">:</td>
                                        <td colspan="2"><s:textfield id="emaildomain" name="emaildomain" cssClass="text_field" disabled="true"/></td>
                                    </tr>  
                                    <tr>
                                        <td colspan="4"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"></td>
                                        <td align="left">
                                            <s:url var="inserturl"/>
                                            <sj:submit href="%{inserturl}" button="true" label="Edit"  value="Edit" id="btnedit" cssClass="add"  onclick="fieldEnable()"/>

                                            <s:url action="UpdatesmseConf" var="inserturl"/>
                                            <sj:submit href="%{inserturl}" button="true" label="Save" targets="divmsg" value="Save" id="btnsave" cssClass="add" />

                                            <sj:submit button="true" value="Reset" id="btnreset" onClick="resetSmsEmailConfForm(); return false;" cssClass="add"/>

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
