<%-- 
    Document   : nonFunctionTerminal
    Created on : Sep 16, 2015, 2:50:27 PM
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

            $.subscribe('onclicksearch', function (event, data) {
                var fromdate = $('#fromdate').val();
                var todate = $('#todate').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {fromdate: fromdate, todate: todate}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            });

        </script>

    </head>

    <body style="overflow:hidden">
        <div class="wrapper">
            <jsp:include page="../../header.jsp" /> 

            <!--Body Content-->
            <div class="body_right">
                <div class="heading"><div class="underline">Non Function Terminal</div></div>
                <div class="content">

                    <div class="content_highlight">  
                        <div class="message">   
                            <s:div id="divmsg">
                                <i style="color: red; background-color: black !important;">  <s:actionerror theme="jquery"/></i>
                                <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                            </s:div>
                        </div>
                    </div>




                    <div class="display" id="searchdiv">
                        <s:form  id="nftsearchForm"  action="XSLcreatnonTerm" name="nftsearchForm" theme="simple" method="post">
                            <table border="0"> 
                                <tr>
                                    <td class="lable">From</td>
                                    <td class="lable">:</td>
                                    <td colspan="2">
                                        <sj:datepicker id="fromdate" name="fromdate" readonly="true" value="today"   changeYear="true" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="text_field" cssStyle="margin-right:5px;"/></td>
                                </tr>
                                <tr>
                                    <td class="lable">To</td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><sj:datepicker id="todate" name="todate" readonly="true" value="today"   changeYear="true" buttonImageOnly="true" displayFormat="yy-mm-dd" cssClass="text_field" cssStyle="margin-right:5px;"/></td>
                                </tr>
                                <tr>
                                    <td colspan="2"></td>
                                    <td align="left">
                                        <s:submit   id="exportXLSbutton" name="exportXLSbutton" value="Export XSL"  cssClass="add" cssStyle="width:100px;" />
                                        <sj:a id="searchbut"  button="true" onClickTopics="onclicksearch"
                                              cssClass="search"   role="button" aria-disabled="false" cssStyle="padding-left: 15px; font-weight:normal !important;margin-top: 0px;" >
                                            Search</sj:a></td>
                                       
                                    </td>

                                   
                                </tr>
                            </table>
                        </s:form>


                    </div>

                    <div class="table" style="margin-right:50px;">
                        <div id="tablediv">

                            <sj:dialog 
                                id="deleteConfirmDialog" 
                                buttons="{ 
                                'OK':function() { deleteWebUser($(this).data('uname'),$(this).data('utypeid'));$( this ).dialog( 'close' ); },
                                'Cancel':function() { $( this ).dialog( 'close' );} 
                                }" 
                                autoOpen="false" 
                                modal="true" 
                                title="Delete User Confirmation"
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
                                title="Delete User" 
                                width="400"
                                height="150"
                                position="center"
                                />
                            <s:url var="listurl" action="ListnonTerm"/>
                            <sjg:grid
                                id="gridtable"
                                caption="User Details"
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
                                <sjg:gridColumn name="sid" title="sId"   hidden="true" />
                                <sjg:gridColumn name="tid" index="tid" title="TID" align="left" width="15" sortable="false"  /> 
                                <sjg:gridColumn name="mid" index="mid" title="MID" align="left" width="35" sortable="false"/>                    
                                <sjg:gridColumn name="serialNo" index="serialNo" title="Serial No"  align="left" width="25"  sortable="false"/>
                                <sjg:gridColumn name="terminalBrand"  index="terminalBrand" title="Terminal Brand"  width="25" align="center" sortable="false"/>
                                <sjg:gridColumn name="name" title="Name" index="name" align="left" width="15" sortable="false" />
                                <sjg:gridColumn name="bank"  title="Bank"  width="25" align="center" sortable="false"/>
                                <sjg:gridColumn name="location" index="location" title="Location" align="left" width="15" sortable="false"  /> 
                                <sjg:gridColumn name="registerDate" index="registerDate" title="Register Date" align="left" width="35" sortable="false"/>                    
                                <sjg:gridColumn name="lastTransDate" index="lastTransDate" title="Last Transaction Date"  align="left" width="25"  sortable="false"/>

                            </sjg:grid> 
                        </div> 
                    </div>
                </div>

                <div id="width_more"> 
                    <div id="inner"></div>


                    <div class="a1" id="a1" style="background-image: url(/EpicTLE/resources/images/inner_search.png);">

                        <div class="b1">Search Web User</div>
                    </div>


                    <a  href="<s:url action="WebUserRegisterWebUser"/>">
                        <div class="a2" style="background-image: url(/EpicTLE/resources/images/inner_add.png);">
                            <div class="b2">Add Web User</div>
                        </div>
                    </a>



                    <a  href="<s:url action="WebUserPasswordPolicy"/>">
                        <div class="a3" style="background-image: url(/EpicTLE/resources/images/inner_password.png);">
                            <div class="b3">Password Policy</div>
                        </div>
                    </a>

                    <a href="${pageContext.request.contextPath}/pages/EpicTLE/WebUserUserConfirmation.action">
                        <div class="a4" style="background-image: url(/EpicTLE/resources/images/inner_confirm.png);">
                            <div class="b4">User Confirmation</div>
                        </div>
                    </a>



                </div>                           

            </div>





            <jsp:include page="../../footer.jsp" />
        </div><!--End of Wrapper-->
    </body>
</html>

