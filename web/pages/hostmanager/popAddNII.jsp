<%-- 
    Document   : popAddNII
    Created on : Jun 1, 2017, 5:09:32 PM
    Author     : thilina_t
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
            $('#nii').val("");
            $('#mapNii').val("");
            $('#fdivmsg').empty();
            utilityManager.resetMessage();
            jQuery("#gridtable1").trigger("reloadGrid");
        }

        function Statusformatter(cellvalue) {
            if (cellvalue == 1) {
                return "<i class='fa fa-circle active' aria-hidden='true'></i>";
            } else {
                return "<i class='fa fa-circle' aria-hidden='true'></i>";
            }
        }

        function fdeleteformatter(cellvalue, options, rowObject) {
            return "<a href='#' onClick='fdeleteInit(&#34;" + rowObject.id + "&#34;,&#34;" + rowObject.nii + "&#34;)'><i class='fa fa-trash-o' aria-hidden='true' title='Delete Block BIN'></i></a>";
        }

        function fdeleteInit(id, name) {
            $("#confirmdialogboxf").data('keyval1', id).dialog('open');
            $("#confirmdialogboxf").data('keyname', name).dialog('open');
            $("#confirmdialogboxf").html('<p>Please confirm to delete NII : ' + name+'</p>');

            return false;
        }

        function fdeleteNow(id, name) {
            var token=$( "input[name='RequstToken']" ).val();
            $.ajax({
                url: '${pageContext.request.contextPath}/DeleteniiConf',
                data: {id: id, nii: name, RequstToken: token},
                dataType: "json",
                type: "POST",
                success: function (data) {
                    $stoken=data.token;
                    if (data.isDeleted == true) {
                        utilityManager.showMessage('.del-user-msg', data.dmessage, 'successmsg',$stoken);
                    } else {
                        utilityManager.showMessage('.del-user-msg', data.dmessage, 'errormsg', $stoken);
                    }
                    jQuery("#gridtable1").trigger("reloadGrid");
                },
                error: function (data) {
                        window.location = "${pageContext.request.contextPath}/LogoutloginCall";
                        }
                });

        }

        $('#binName').keypress(function (e) {
            var a = [];
            var k = e.which;

            for (i = 48; i < 58; i++)
                a.push(i);

            if (!($.inArray(k, a) >= 0))
                e.preventDefault();
        });

    </script>
    <body>
        <section class="app-content popup-window">
            <div class="content innerpage">
                <s:set var="fvupdate"><s:property value="vupdate" default="true"/></s:set>
                <s:set id="fvadd" var="vadd"><s:property value="vadd" default="true"/></s:set>

                    <div class="content-section data-form">
                    <s:form id="addForm1" theme="simple">
                        <div class="content-data">
                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg">
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i> <span id="fdivmsg"></span></div>
                            </div>
                            <!-- End -->

                            <!-- Two colum form row begin -->
                            <div class="d-row">
                                <label class="left-col form-label">NII<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield name="nii" id="nii" cssClass="txt-input width-35" maxLength="3" />
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">MAP NII<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield name="mapNii" id="mapNii" cssClass="txt-input width-35" maxLength="3" />
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">TLE Status<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:select  name="tlestatus" id="tlestatus" list="%{astatusMap}" headerKey="1" 
                                               listKey="key" listValue="value" cssClass="ddl-input"/>
                                </div>
                            </div>
                            <!-- End -->
                            <div class="d-row cpanel">
                                <label class="left-col">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="addurl1" action="AddniiConf"/>                                   
                                    <sj:submit button="true" href="%{addurl1}" value="Add"   targets="fdivmsg"  cssClass="btn default-button" disabled="#fvadd"/> 
                                    <sj:submit id="resetidf" button="true" value="Reset" onclick="resetData1()"   cssClass="btn reset-button" disabled="false" />
                                </div>
                            </div>
                            <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                        </s:form>

                        <s:url var="listur" action="listniiConf">
                            <s:param name="ChannelID"><s:property value="ChannelId"/></s:param>
                        </s:url>

                        <!-- Grid data begin -->
                        <div class="content-section">
                            <div class="content-data">
                                <h2 class="section-title">NII List</h2>
                                <!-- Error and success message panel begin -->
                                <div class="msg-panel del-user-msg" >
                                    <div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                                </div>
                                <!-- End -->
                            </div>
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
                                    id="gridtable1"                                
                                    caption="Bin Profiles"
                                    dataType="json"
                                    href="%{listur}"
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
                                    <sjg:gridColumn name="nii" index="epicTleBinProfile.id" title="NII"  align="center" width="25"  sortable="true"/>
                                    <sjg:gridColumn name="mapNii" index="epicTleBinProfile.id" title="MAP NII"  align="center" width="25"  sortable="true"/>
                                    <sjg:gridColumn name="tlestatus"  title="Status"  width="25" align="center" formatter="Statusformatter" sortable="false"/>
                                    <sjg:gridColumn name="datetime" index="datetime" title="Date" align="center" width="35" sortable="true"/>
                                    <sjg:gridColumn name="id"  title="Delete"  width="25" align="center" formatter="fdeleteformatter" sortable="false" cssClass="action-col" />
                                </sjg:grid> 
                            </div>
                        </div>
                    </div>
                    <!-- End -->

                </div>
            </div>
        </section>
    </body>
</html>
