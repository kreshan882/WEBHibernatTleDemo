<%-- 
    Document   : addFieldEngineer
    Created on : Sep 16, 2015, 2:48:01 PM
    Author     : ravideva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>

<html>
    <head>

        <jsp:include page="../../Styles.jsp" />
        <script type="text/javascript">
            function resetForm(){
                $('#divmsg').empty();
                $('#deviceType').val("-1");
                $('#serialNo').val("");
                $('#fldEngName').val("");
                $('#bankName').val("");
                $('#location').val("");
                $('#maxKeyDown').val("");
                $('#algo').val("-1");
                $('#pinVerType').val("-1");
 
         
            }
            
            
            function loaddeviceType(keyval) {
                if(keyval==1){
                    $('#serialNo').val("");
                    document.getElementById("serialNo").disabled = true;
                }else{
                    document.getElementById("serialNo").disabled = false;
                }
            }
            
            function addForm() {
                //$('#divmsg').empty();
                var deviceType = $('#deviceType').val();
                var serialNo = $('#serialNo').val();
                var fldEngName = $('#fldEngName').val();
                var bankName = $('#bankName').val();
                var location = $('#location').val();
                var maxKeyDown = $('#maxKeyDown').val();
                var algo = $('#algo').val();
                var pinVerType = $('#pinVerType').val();

                $.ajax({
                    url: '${pageContext.request.contextPath}/addFieldEngineerAdd',
                    data: {deviceType: deviceType,serialNo: serialNo,fldEngName:fldEngName,bankName:bankName,location:location,maxKeyDown:maxKeyDown,algo:algo,pinVerType:pinVerType},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
                        $('#messagefe').val(data.messagefe);
                        $('#pdfActive').val(data.pdfActive);
                        $('#successmsg').val(data.successmsg);
                        if(data.successmsg){
                            if(data.pdfActive==true){
                                $('#pdfbankname').val(data.pdfbankname);
                                $('#pdfmerchantname').val(data.pdfmerchantname);
                                $('#pdfcardSerialNo').val(data.pdfcardSerialNo);
                                $('#pdfuserPin').val(data.pdfuserPin);
                                
                                $("#downloadpdfDialog").dialog('open');
                                $("#downloadpdfDialog").html('<br><br><b><font size="3" color="green"><center> '+data.messagefe);
          
                            }else{
                                resetForm();
                                $("#assignbut").click();
                            }
                            
                        }else{
                            $('#deviceType').val("-1");
                            $("#assignbut").click();
                        }
                        

                    }

                });
            }
          
            function downloadpdfnow(){
                resetForm();
                $("#assignbut").click();
            }
          
        </script>

    </head>

    <body style="overflow:hidden">
        <div class="wrapper">
            <jsp:include page="../../header.jsp" /> 

            <!--Body Content-->
            <div class="body_right">
                <div class="heading"><div class="underline">Add Field Engineer</div></div>
                <div class="content">
                    <div class="content_highlight">  
                        <div id="message"> 
                            <div class="message">   
                                <s:div id="divmsg">
                                    <i style="color: red; background-color: black !important;">  <s:actionerror theme="jquery"/></i>
                                    <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                                </s:div>
                            </div>
                        </div>
                    </div>

                    <div class="display">
                        <s:form action="downloadpdfFieldEngineerAdd" theme="simple" >
                            <table border="0" style="white-space: nowrap;">
                                <s:hidden id="pdfActive" name="pdfActive" />
                                <s:hidden id="successmsg" name="successmsg" />
                                <s:hidden id="messagefe" name="messagefe" />

                                <s:hidden id="pdfbankname" name="pdfbankname"/>
                                <s:hidden id="pdfmerchantname" name="pdfmerchantname" />
                                <s:hidden id="pdfcardSerialNo" name="pdfcardSerialNo"/>
                                <s:hidden id="pdfuserPin" name="pdfuserPin" />
                                <tr>
                                    <td class="lable">Issuing Method<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:select id="deviceType"  name="deviceType"  headerKey="-1"  headerValue="---Select---" 
                                              onchange="loaddeviceType(this.value)" list="%{deviceTypeMap}" cssClass="dropdown" /></td>
                                </tr>
                                <tr>
                                    <td class="lable">Serial Number<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:textfield id="serialNo" name="serialNo" cssClass="text_field" disabled="false" /></td>
                                </tr>
                                <tr>
                                    <td class="lable">Field Engineer Name<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:textfield id="fldEngName" name="fldEngName" cssClass="text_field"/></td>
                                </tr>
                                <tr>
                                    <td class="lable">Bank Name<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:textfield id="bankName" name="bankName" cssClass="text_field"/></td>
                                </tr>
                                <tr>
                                    <td class="lable">Location<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:textfield id="location" name="location" cssClass="text_field"/></td>
                                </tr>
                                <tr>
                                    <td class="lable">Maximum Key Download<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:textfield id="maxKeyDown" name="maxKeyDown" cssClass="text_field"/></td>
                                </tr>
                                <tr>
                                    <td class="lable">Algorithm<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:select id="algo"  name="algo"  headerKey="-1"  headerValue="---Select---"  list="%{algoMap}" cssClass="dropdown" /></td>
                                </tr>
                                <tr>
                                    <td class="lable">Pin Verification<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:select id="pinVerType"  name="pinVerType"  headerKey="-1"  headerValue="---Select---"  list="%{pinVerTypeMap}" cssClass="dropdown" /></td>
                                </tr>
<!--                                <tr>
                                    <td class="lable">BDK Index<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:select id="pinVerType"  name="pinVerType"  headerKey="-1"  headerValue="---Select---"  list="%{pinVerTypeMap}" cssClass="dropdown" /></td>
                                </tr>-->
                                <tr>
                                    <td colspan="4"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                </tr>
                                <tr>
                                    <td colspan="2"></td>
                                    <td align="right">

                                        <s:submit button="true"  id="assignbut" cssStyle="display: none; visibility: hidden;" cssClass="button_asave" />
                                        <sj:a button="true" onclick="addForm()" cssClass="add" >Save</sj:a>
                                        </td>

                                        <td align="left" width="25px;">
                                        <s:url var="reseturl"/>
                                        <sj:submit  href="%{reseturl}"  button="true"   onclick="resetForm()"  value="Reset" cssClass="add"/>
                                    </td>

                                </tr>
                            </table>
                        </s:form>
                    </div>
                </div>

                <sj:dialog 
                    id="downloadpdfDialog" 
                    buttons="{ 
                    'OK':function() { downloadpdfnow();$( this ).dialog( 'close' ); },
                    'Cancel':function() { $( this ).dialog( 'close' );} 
                    }" 
                    autoOpen="false" 
                    modal="true" 
                    title="PDF download Confirmation"
                    width="400"
                    height="200"
                    position="center"
                    />

            </div>
            <!--End of Body Content-->


            <jsp:include page="../../footer.jsp" />
        </div><!--End of Wrapper-->
    </body>
</html>

