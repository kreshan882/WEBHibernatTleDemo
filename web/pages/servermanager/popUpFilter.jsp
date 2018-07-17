<%-- 
    Document   : popUpFilter
    Created on : Feb 15, 2018, 4:18:56 PM
    Author     : ridmi_g
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"  %>  
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html>
<html>
    <script type="text/javascript">
        
        $(document).ready(function(){
            var $stoken = '${RequstToken}';
            $( "input[name='RequstToken']" ).val($stoken);
        });

        function resetData1() {
            $('#message').val("");
           
            $('#fdivmsg1').empty();
            utilityManager.resetMessage();
            jQuery("#gridtable11").trigger("reloadGrid");
        }
        function resetForm(){
//            $('#fdivmsg1').empty();

            utilityManager.resetMessage();
            jQuery("#gridtable11").trigger("reloadGrid");
            
            jQuery("#gridtablesms").trigger("reloadGrid");
             $('.del-user-msg').hide();
        }

        function fdeleteformatter(cellvalue, options, rowObject) {
            return "<a href='#' onClick='fdeleteInit(&#34;" + rowObject.id + "&#34;,&#34;" + rowObject.message + "&#34;)' title='Delete Message'><i class='fa fa-trash-o' aria-hidden='true' title='Delete Message'></i></a>";
        }

        function fdeleteInit(id, name) {
            $("#confirmdialogboxf").data('keyval1', id).dialog('open');
            $("#confirmdialogboxf").data('keyname', name).dialog('open');
            $("#confirmdialogboxf").html('<p>Please confirm to delete message : ' + name);

            return false;
        }

        function fdeleteNow(id, name) {
            var token=$( "input[name='RequstToken']" ).val();
             $('.add-form-msg').hide();
            $.ajax({
                url: '${pageContext.request.contextPath}/DeleteMessage',
                data: {id: id, message: name, RequstToken: token},
                dataType: "json",
                type: "POST",
                success: function (data) {
                    $stoken=data.token;
                    if (data.isDeleted == true) {
                        utilityManager.showMessage('.del-user-msg', data.dmessage, 'successmsg', $stoken);
                    } else {
                        utilityManager.showMessage('.del-user-msg', data.dmessage, 'errormsg', $stoken);
                    }
                    jQuery("#gridtable11").trigger("reloadGrid");
                },
                error: function (data) {
                    window.location = "${pageContext.request.contextPath}/LogoutloginCall";
                }
            });

        }



    </script>
    <body>
        <section class="app-content popup-window">
            <div class="content innerpage">
                <s:set var="fvupdate"><s:property value="vupdate" default="true"/></s:set>
                <s:set id="fvadd" var="vadd"><s:property value="vadd" default="true"/></s:set>
                    <div class="content-section data-form">
                    <s:form id="addForm11" theme="simple">
                        <div class="content-data">
                            
                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg">
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i> <span id="fdivmsg1"></span></div>
                            </div>
                            <!-- End -->

                            <!-- Two colum form row begin -->
                            <div class="d-row">
                                <label class="col-1 form-label">Message<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:textfield name="message"  id="message" cssClass="txt-input width-35" />
                                </div>
                            </div>
                                
                            <div class="d-row cpanel four-col">
                                <label class="col-1">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="addfilterurl1" action="AddSMS"/>                                   
                                    <div class="btn-wrap lnk-match"><i class="fa fa-plus" aria-hidden="true"></i><sj:submit button="true" href="%{addfilterurl1}" value="Add"   targets="fdivmsg1"  cssClass="btn default-button" disabled="#fvadd"/></div> 
                                    <div class="btn-wrap lnk-match"><i class="fa fa-times" aria-hidden="true"></i><sj:submit id="resetidf" button="true" value="Reset" onclick="resetData1()"   cssClass="btn reset-button" disabled="false" /></div>
                                        <%--<sj:submit id="backidf" button="true" value="Back" onclick="facilityPopupClose()"   cssClass="button_aback" disabled="false" />--%> 
                                </div>
                                <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                            </s:form>

                            <s:url var="listu" action="listMessage">
                                <s:param name="id"><s:property value="binId"/></s:param>
                            </s:url>

                            <div class="content-section">
                                <div class="content-data">
                                    <h2 class="section-title">Filter Message</h2>
                                    <!-- Error and success message panel begin -->
                                    <div class="msg-panel del-user-msg" >
                                        <div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                                    </div>
                                    <!-- End -->
                                </div>
                                <div class="viewuser_tbl">
                                    <div id="tablediv" class="custom-grid">

                                        <sj:dialog 
                                            id="confirmdialogboxf" 
                                            buttons="{ 
                                            'OK':function() { fdeleteNow($(this).data('keyval1'),$(this).data('keyname'));$( this ).dialog( 'close' ); },
                                            'Cancel':function() { $( this ).dialog( 'close' );} 
                                            }" 
                                            autoOpen="false" 
                                            modal="true" 
                                            title="Confirm Message"
                                            width="400"
                                            height="175"
                                            position="center"
                                            />

                                        <sj:dialog 
                                            id="dialogboxf" 
                                            buttons="{
                                            'OK':function() { $( this ).dialog( 'close' );}
                                            }"  
                                            autoOpen="false" 
                                            modal="true" 
                                            title="Delete Message" 
                                            width="400"
                                            height="150"
                                            position="center"
                                            />

                                        <sjg:grid
                                            id="gridtable11"                                
                                            caption="Bin Profiles"
                                            dataType="json"
                                            href="%{listu}"
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

                                            <sjg:gridColumn name="id" index="id" title="id"   hidden="true" />
                                            <sjg:gridColumn name="message" index="message" title="Message"  align="center" width="25" />
                                            <sjg:gridColumn name="date" index="date" title="Date" align="center" width="35" sortable="true"/>
                                            <sjg:gridColumn name="id"  title="Delete"  width="25" align="center" formatter="fdeleteformatter" sortable="false" cssClass="action-col" />
                                        </sjg:grid> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

    </body>
</html>
