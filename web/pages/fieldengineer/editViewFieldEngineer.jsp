<%-- 
    Document   : editViewFieldEngineer
    Created on : Sep 16, 2015, 2:48:21 PM
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
            function resetForm(){
                $('#upstatus').val("-1");
                $('#upserialNumber').val("");
                $('#upfldEngName').val("");
                $('#upbankName').val("");
                $('#uplocation').val("");
                $('#upmaxKeyDown').val("");

                jQuery("#gridtable").trigger("reloadGrid");
            }

            $.subscribe('onclicksearch', function(event, data) {
                var searchSerial = $('#searchSerial').val();
                $("#gridtable").jqGrid('setGridParam', {postData: {searchSerial: searchSerial}});
                $("#gridtable").jqGrid('setGridParam', {page: 1});
                jQuery("#gridtable").trigger("reloadGrid");
            });

            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='editFieldEng(&#34;" + cellvalue + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/edit_icon.png'  /></a>";
            }
            function deleteformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='deleteFieldEngInit(&#34;" + cellvalue + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/delete_icon.png'  /></a>";
            }
            function pinresetformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='resetPinInit(&#34;" + cellvalue + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/delete_icon.png'  /></a>";
            }

            function resetPinInit(serialNumber) {
                $("#pinresetConfirmDialog").data('serialNumber', serialNumber).dialog('open');
                $("#pinresetConfirmDialog").html('<br><br><b><font size="3" color="red"><center>Please confirm Reset Pin : '+serialNumber);
                return false;
            }
            function resetPin(serialNumber) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/PinResetFieldEngineerEditView',
                    data: {pinserialNumber: serialNumber},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
                        alert("pin reset sucess"+data.messagefe+":"+data.successmsg+":"+data.pdfActive);
//                        if (data.isPinReset == true) {
//                            $("#pinresetMessageDialog").dialog('open');
//                            $("#pinresetMessageDialog").html('<br><br><b><font size="3" color="green"><center>'+data.pinmessage);
//                        } else {
//                            $("#pinresetMessageDialog").dialog('open');
//                            $("#pinresetMessageDialog").html('<br><br><b><font size="3" color="red"><center>'+data.pinmessage);
//                        }
                        
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

            function deleteFieldEngInit(serialNumber) {
                $("#deleteConfirmDialog").data('serialNumber', serialNumber).dialog('open');
                $("#deleteConfirmDialog").html('<br><br><b><font size="3" color="red"><center>Please confirm delete : '+serialNumber);
                return false;
            }


            function deleteFieldEng(serialNumber) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/DeleteFieldEngineerEditView',
                    data: {dserialNumber: serialNumber},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
                        if (data.isDeleted == true) {
                            $("#deleteMessageDialog").dialog('open');
                            $("#deleteMessageDialog").html('<br><br><b><font size="3" color="green"><center>'+data.dmessage);
                        } else {
                            $("#deleteMessageDialog").dialog('open');
                            $("#deleteMessageDialog").html('<br><br><b><font size="3" color="red"><center>'+data.dmessage);
                        }
                        resetData();
                    }
                });
            }

            function resetData() {
                $('#divmsg').empty();
                jQuery("#gridtable").trigger("reloadGrid");
            }


            function editFieldEng(serialNumber) {
                        $('#divmsg').empty();
                        $('#fieldengEditForm').show();
                        $('#fieldengSearchForm').hide();
                $.ajax({
                    url: '${pageContext.request.contextPath}/FindFieldEngineerEditView',
                    data: {upserialNumber: serialNumber},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
                        $('#upserialNumber').attr('readOnly', true);
                        $('#upserialNumber').val(data.upserialNumber);
                        $('#upfldEngName').val(data.upfldEngName);
                        $('#upbankName').val(data.upbankName);
                        $('#uplocation').val(data.uplocation);
                        $('#upmaxKeyDown').val(data.upmaxKeyDown);
                        $('#upstatus').val(data.upstatus);
                    }
                });
            }

            function resetUpdateForm() {
                $('#divmsg').empty();
                var upserialNumber = $('#upserialNumber').val();

                $.ajax({
                    url: '${pageContext.request.contextPath}/FindFieldEngineerEditView',
                    data: {upserialNumber: upserialNumber},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
                        $('#upserialNumber').attr('readOnly', true);
                        $('#upserialNumber').val(data.upserialNumber);
                        $('#upfldEngName').val(data.upfldEngName);
                        $('#upbankName').val(data.upbankName);
                        $('#uplocation').val(data.uplocation);
                        $('#upmaxKeyDown').val(data.upmaxKeyDown);
                        $('#upstatus').val(data.upstatus);

                    }

                });
                jQuery("#gridtable").trigger("reloadGrid");


            }

            function backToMain() {
                $('#fieldengEditForm').hide();
                $('#fieldengSearchForm').show();
                $('#divmsg').empty();
                jQuery("#gridtable").trigger("reloadGrid");

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
                <div class="heading"><div class="underline">Edit & View Users</div></div>
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
                        <s:form  id="fieldengSearchForm"  action="XSLcreatFieldEngineerEditView"  theme="simple">
                            <table border="0">
                                <tr>
                                    <td class="lable">Serial Number </td>
                                    <td class="lable">:</td>
                                    <td><s:textfield name="searchSerial" id="searchSerial" cssClass="text_field"/></td>
                                    <td align="right">
                                        <sj:a   id="searchbut"   button="true"    onClickTopics="onclicksearch"  cssClass="search" onfocus="true"
                                            cssStyle="margin-top:0px;padding-left: 15px; font-weight:normal !important;">Search</sj:a>    
                                    </td>
                                    <td >
                                        <s:submit   value="Export XSL"  cssClass="add" cssStyle="width:100px;" />
                                        </td>
                                    </tr>
                                </table>
                        </s:form>
                    </div>

                    <div class="display">              
                        <s:form id="fieldengEditForm" theme="simple" method="post" cssStyle="display: none">

                            <table border="0">


                                <tr>
                                    <td class="lable">Serial Number</td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:textfield id="upserialNumber" name="upserialNumber" cssClass="text_field"  disabled="false" /></td>
                                </tr>
                                <tr>
                                    <td class="lable">Field Engineer Name</td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:textfield id="upfldEngName" name="upfldEngName" cssClass="text_field"/></td>
                                </tr>
                                <tr>
                                    <td class="lable">Bank Name</td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:textfield id="upbankName" name="upbankName" cssClass="text_field"/></td>
                                </tr>
                                <tr>
                                    <td class="lable">Location</td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:textfield id="uplocation" name="uplocation" cssClass="text_field"/></td>
                                </tr>
                                <tr>
                                    <td class="lable">Maximum Key Download</td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:textfield id="upmaxKeyDown" name="upmaxKeyDown" cssClass="text_field"/></td>
                                </tr>
                                <tr>
                                    <td class="lable">Status</td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:select id="upstatus"  name="upstatus"  headerKey="-1"  headerValue="---Select---"  list="%{upstatusMap}" cssClass="dropdown" /></td>
                                </tr>
                          

                                <tr>
                                    <td colspan="5" align="left"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                </tr>
                                <tr>
                                    <td></td>
                                    <td colspan="3" align="right">
                                        <s:url var="updateuserurl" action="UpdateFieldEngineerEditView"/>                                   
                                        <sj:submit  href="%{updateuserurl}"  targets="divmsg" value="Update"  button="true"   cssClass="add" />
                                    </td>
                                    <td align="center" width="60px">
                                        <sj:a href="#" name="back" button="true" onclick="backToMain()" cssClass="add" >Back</sj:a>     
                                    </td>
                                    <td align="right" width="60px">
                                        <sj:submit button="true" value="Reset" onClick="resetUpdateForm()" cssClass="add"/>
                                    </td>
                                </tr>
                            </table>

                        </s:form>     

                    </div>

                    <s:form action="downloadpdfFieldEngineerEditView" theme="simple" >
                            
                                <s:hidden id="pdfActive" name="pdfActive" />
                                <s:hidden id="successmsg" name="successmsg" />
                                <s:hidden id="messagefe" name="messagefe" />

                                <s:hidden id="pdfbankname" name="pdfbankname"/>
                                <s:hidden id="pdfmerchantname" name="pdfmerchantname" />
                                <s:hidden id="pdfcardSerialNo" name="pdfcardSerialNo"/>
                                <s:hidden id="pdfuserPin" name="pdfuserPin" />
                                <s:submit button="true"  id="assignbut" cssStyle="display: none; visibility: hidden;"  />
                        </s:form>
                    <div class="table" style="margin-right:50px;">
                        <div id="tablediv">
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
                            <s:url var="listurl" action="ListFieldEngineerEditView"/>
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
                               
                                <sjg:gridColumn name="sid" index="sid" title="ID" align="left" width="15" sortable="true"  /> 
                                <sjg:gridColumn name="serialno" index="serialno" title="Serial Number" align="left" width="35" sortable="true"/>                    
                                <sjg:gridColumn name="selecteddevice" index="epicTleSelecteddevice.description" title="Selected Device" align="left" width="35" sortable="true"/> 
                                <sjg:gridColumn name="officername" index="officername" title="Officer Name" align="left" width="35" sortable="true"/> 
                                <sjg:gridColumn name="bankname" index="bankname" title="Bank Name" align="left" width="35" sortable="true"/> 
                                <sjg:gridColumn name="location" index="location" title="Location" align="left" width="35" sortable="true"/> 
                                <sjg:gridColumn name="regdate" index="regdate" title="Registered Date" align="left" width="35" sortable="true"/> 
                                <sjg:gridColumn name="maxtmkdownlod" index="maxtmkdownlod" title="Max Key Download" align="left" width="35" sortable="true"/> 
                                <sjg:gridColumn name="avaliabletmkdownlod" index="maxcountor" title="Avaliable Key Download" align="left" width="35" sortable="true"/> 
                                <sjg:gridColumn name="status" index="epicTleStatus.description" title="Status"  align="left" width="25"  sortable="true"/>
                                 
                                <sjg:gridColumn name="serialno"  title="Pin Reset"  width="25" align="center" formatter="pinresetformatter"   sortable="false"/>
                                <sjg:gridColumn name="serialno"  title="Edit"  width="25" align="center" formatter="editformatter"   sortable="false"/>
                                <sjg:gridColumn name="serialno"  title="Delete"  width="25" align="center" formatter="deleteformatter"   sortable="false"/>
                            </sjg:grid> 
                        </div> 
                    </div>
                </div>



            </div>





            <jsp:include page="../../footer.jsp" />
        </div><!--End of Wrapper-->
    </body>
</html>