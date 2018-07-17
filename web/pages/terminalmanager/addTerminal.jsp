<%-- 
    Document   : addTerminal
    Created on : Sep 16, 2015, 2:49:49 PM
    Author     : ravideva
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<!DOCTYPE html>
<html>
    <head>

        <jsp:include page="../../Styles.jsp" />
        <script type="text/javascript">
            function resetForm() {
                $('#tid').val("");
                $('#mid').val("");
                $('#serialno').val("");
                $('#name').val("");
                $('#bank').val("");
                $('#location').val("");
                $('#terBrand').val("");
                $('#encStatus').val("-1");
                $('#encType').val("-1");

            }
        </script>


    </head>

    <body style="overflow:hidden">
        <div class="wrapper">
            <jsp:include page="../../header.jsp" /> 

            <!--Body Content-->
            <div class="body_right">
                <div class="heading"><div class="underline">Add New Terminal</div></div>
                <div class="content">
                    <div class="content_highlight">  
                        <div class="message">   
                            <s:div id="divmsg">
                                <i style="color: red; background-color: black !important;">  <s:actionerror theme="jquery"/></i>
                                <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                            </s:div>
                        </div>
                    </div>

                    <div class="display" >
                        <s:form  theme="simple" method="POST" name="addterform" id="addterform">
                            <table class="form_table" border="0px">
                                <tr>
                                    <td class="content_td formLable">Terminal ID<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="terminalId" name="terminalId" cssClass="text_field" maxLength="8"/></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Merchant ID<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="mid" name="mid" cssClass="text_field" maxLength="15"/></td>
                                </tr>
                                
                                <tr>
                                    <td class="content_td formLable">Serial Number<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="serialno" name="serialno" cssClass="text_field"/></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Name<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="name" name="name" cssClass="text_field"/></td>
                                </tr>
                                
                                <tr>
                                    <td class="content_td formLable">Bank<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="bank" name="bank" cssClass="text_field"/></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Location<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="location" name="location" cssClass="text_field"/></td>
                                </tr>
                                
                                <tr>
                                    <td class="content_td formLable">Terminal Brand<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:textfield id="terBrand" name="terBrand" cssClass="text_field"/></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable">Encryption Type<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:select  name="encType" id="encType" headerKey="-1"  headerValue="---Select Encryption Type ---" 
                                               listKey="key" listValue="value" list="%{encTypeMap}" cssClass="dropdown"/></td>
                                </tr>
                                
                                <tr>
                                    <td class="content_td formLable">Non-Encryption Transactions<span class="mandatory">*</span></td>
                                    <td class="content_td formLable">:</td>
                                    <td><s:select  name="encStatus" id="encStatus" headerKey="-1" listKey="key" listValue="value" 
                                               headerValue="---Select---" list="%{encStatusMap}" cssClass="dropdown"/></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable"></td>
                                    <td class="content_td formLable"></td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td class="content_td formLable" colspan="7"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                </tr>
                                <tr>
   
                                    <td align="left" colspan="7">
                                        <s:url var="saveurl" action="addTerminalTerminalAdd"/>                                   
                                        <sj:submit  href="%{saveurl}"  targets="divmsg" value="Save"  button="true"   cssClass="add" />
                                    
                                        <s:url var="inserturl"/>
                                        <sj:submit  href="%{inserturl}"  button="true"   onclick="resetForm()"  value="Reset" cssClass="add"/>
                                    </td>
                                </tr>
                            </table>
                                    
                        </s:form>
                        
                        <s:form id="upfileform" action="uploadFileTerminalAdd" theme="simple" enctype="multipart/form-data"  method="post">  
                            <table border="0" style="white-space: nowrap; margin-top: 50px;" cellspacing="0px;">
                                <tr>
                                    <td  style="background-color: #303338;font-size: 15px;color: #fff;padding-left: 5px;border-left: 1px solid #303338;width: 420px;">
                                        Upload Terminal List
                                    </td>
                                </tr>
                                <tr style="background:azure">
                                    <td style="padding:8px;border: 1px solid #303338;"> 
                                        <s:file  id = "upfile" name="upfile" label="File" cssClass="fileField"  />
                                        <s:submit type="submit" value="Submit"  name="submit" method="post" cssClass="addsubmit" ></s:submit>   
                                    </td>  
                                </tr>
                            </table>                            
                        </s:form> 
                    </div>
                </div>

              
            <!--End of Body Content-->


            <jsp:include page="../../footer.jsp" />
        </div><!--End of Wrapper-->
    </body>
</html>

