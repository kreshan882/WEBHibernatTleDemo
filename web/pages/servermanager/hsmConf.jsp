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
                $('#tknPin').val("");
            });
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
                                        <td>
                                            <table>
                                                <tr>
                                                    <td class="lable">Model</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="mdl" name="mdl" cssClass="text_field"  disabled="true" /></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Batch</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="batch" name="batch" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Manufacturing date</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="mdate" name="mdate" cssClass="text_field" disabled="true"/></td>
                                                </tr>      
                                                <tr>
                                                    <td class="lable">Serial Number</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="srlNo" name="srlNo" cssClass="text_field" disabled="true" /></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Adapter Clock</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="adpClock" name="adpClock" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Board Revision</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="brdRev" name="brdRev" cssClass="text_field" disabled="true"/></td>
                                                </tr>                                 
                                                <tr>
                                                    <td class="lable">Firmware Version</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="firmVersion" name="firmVersion" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Cprov Version</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="cprVersion" name="cprVersion" cssClass="text_field" disabled="true"/></td>
                                                </tr> 
                                                <tr>
                                                    <td class="lable">Hardware Status</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="hwstatus" name="hwstatus" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Free Memory</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="freeMemo" name="freeMemo" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                            </table>
                                        </td>
                                        <td colspan="2"></td>
                                        <td>
                                            <table>
                                                <tr>
                                                    <td class="lable">SM Size Free/Total</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="total" name="total" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Security Mode</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="secuMode" name="secuMode" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Transport Mode</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="transMode" name="transMode" cssClass="text_field" disabled="true"/></td>
                                                </tr>                                     
                                                <tr>
                                                    <td class="lable">FM Support</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="fmsupport" name="fmsupport" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">FM Status</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="fmstatus" name="fmstatus" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Open Session Count</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="osesCount" name="osesCount" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">Number of Slots</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="nofSlots" name="nofSlots" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                                <tr>
                                                    <td class="lable">RTC Adjustment Access Control</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="rtcAdj" name="rtcAdj" cssClass="text_field" disabled="true"/></td>
                                                </tr>                                            
                                                <tr>
                                                    <td class="lable">Extended HW infot</td>
                                                    <td class="lable">:</td>
                                                    <td colspan="2"><s:textfield id="hwinfo" name="hwinfo" cssClass="text_field" disabled="true"/></td>
                                                </tr>
                                            </table>  
                                        </td>
                                    </tr>                                 
                                    <tr>
                                        <td class="lable ">Token PIN Number</td>
                                        <td class="lable">:</td>
                                        <td colspan="2"><s:password id="tknPin" name="tknPin" cssClass="text_field" /></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"></td>
                                        <td align="left"> 
                                            <s:url action="UpdatehsmConf" var="inserturl"/>
                                            <sj:submit href="%{inserturl}" button="true" label="Enter" targets="divmsg" value="Enter" id="btnsave" cssClass="add" />
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
