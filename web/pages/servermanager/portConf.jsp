
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
            function resetForm() {
                $('#datarate').val("-1");
                $('#databit').val("-1");
                $('#parity').val("-1");
                $('#stopbit').val("-1");
                $('#port').val("-1");
                $('#status').val("-1");
                jQuery("#gridtable").trigger("reloadGrid");
            }
            function resetUpdateForm() {
                var token=$( "input[name='RequstToken']" ).val();
                utilityManager.resetMessage();
                $('#divmsg').empty();
                var keyval = $('#sid').val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/FindportConf',
                    data: {sid: keyval, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $( "input[name='RequstToken']" ).val($stoken);
                        $('#datarate').val(data.datarate);
                        $('#databit').val(data.databit);
                        $('#parity').val(data.parity);
                        $('#stopbit').val(data.stopbit);
                        $('#port').val(data.port);
                        $('#status').val(data.status);
                        $('#sid').val(data.sid);
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }

                });

            }


            function editformatter(cellvalue, options, rowObject) {
                return "<a href='#' disabled='#vupdate' onClick='editPortConf(&#34;" + cellvalue + "&#34;)'><i class='fa fa-pencil' aria-hidden='true'></i></a>";
            }


            function editPortConf(keyval) {
                var token=$( "input[name='RequstToken']" ).val();
                $('.lnk-back').removeClass('hide-element');
                $('#portConfEditForm').parent().show();
                $('#divmsg').empty();
                $.ajax({
                    url: '${pageContext.request.contextPath}/FindportConf',
                    data: {sid: keyval, RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $( "input[name='RequstToken']" ).val($stoken);
                        $('#portConfEditForm').show();
                        $('#datarate').val(data.datarate);
                        $('#databit').val(data.databit);
                        $('#parity').val(data.parity);
                        $('#stopbit').val(data.stopbit);
                        $('#port').val(data.port);
                        $('#status').val(data.status);
                        $('#sid').val(data.sid);
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });
            }

            function backToMain() {
                $('#portConfEditForm').parent().hide();
                $('#divmsg').empty();
                jQuery("#gridtable").trigger("reloadGrid");

            }

        </script>

    </head>
    <s:set id="vadd" var="vadd"><s:property  value="vadd" default="true"/></s:set>
    <s:set var="vupdate"><s:property value="vupdate" default="true"/></s:set>
    <s:set var="vdelete"><s:property value="vdelete" default="true"/></s:set>
    <s:set var="vview"><s:property value="view" default="true"/></s:set>

        <body>
            <section class="app-content">
            <jsp:include page="../../header.jsp" /> 

            <!-- Page content begin -->
            <div class="content innerpage">
                <!-- Breadcrumb begin -->
                <div class="breadcrumb">
                    <s:property value="Module"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i> <span id="task" class="active"><s:property value="Section"/> </span>
                </div>
                <!-- End -->

                <h1 class="page-title"><s:property value="Section"/><a href="#" class="lnk-back hide-element do-nothing"><i class="fa fa-arrow-left" aria-hidden="true"></i> back</a></h1>
                <!-- Data form begin -->
                <div class="content-section data-form" id="addDiv" style="display: none">                    

                    <s:form id="portConfEditForm" theme="simple" method="post">
                        <s:hidden name="sid" id="sid" cssClass="text_field" />
                        <div class="content-data">

                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg" > 
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                            </div>
                            <!-- End -->

                            <!-- Two colum form row begin -->
                            <div class="d-row">
                                <label class="col-1 form-label form-label">Data Rate<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="datarate" headerKey="-1" headerValue="--Select Data Rate--"                                               
                                               listKey="key" listValue="value"
                                               list="%{datarateMap}" id="datarate" cssClass="ddl-input text-right"/>
                                </div>
                          
                                <label class="col-3 form-label">Data Bit<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="databit" headerKey="-1" headerValue="--Select Data Bit--"                                               
                                               listKey="key" listValue="value"
                                               list="%{databitMap}" id="databit" cssClass="ddl-input text-right"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Parity<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="parity" headerKey="-1" headerValue="--Select Parity--"
                                               listKey="key" listValue="value"
                                               list="%{parityMap}" id="parity" cssClass="ddl-input "/>
                                </div>
                            
                                <label class="col-3 form-label">Stop Bit<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="stopbit" headerKey="-1" headerValue="--Select Stop Bit--"                                               
                                               listKey="key" listValue="value"
                                               list="%{stopbitMap}" id="stopbit" cssClass="ddl-input text-right"/>
                                </div>
                            </div>	
                            <div class="d-row">
                                <label class="col-1 form-label">Port<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="port" headerKey="-1" headerValue="--Select Port--"                                              
                                               listKey="key" listValue="value"
                                               list="%{portMap}" id="port" cssClass="ddl-input"/>
                                </div>
                            
                                <label class="col-3 form-label">Status<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select name="status" headerKey="-1" headerValue="--Select Status--"
                                              listKey="key" listValue="value"
                                              list="%{statusMap}" id="status" cssClass="ddl-input" />
                                </div>
                            </div>	

                            <!-- Tow column form button panel begin -->		
                            <div class="d-row cpanel four-col">
                                <label class="col-1">&nbsp;</label>
                                <div class="right-col">
                                    <!--<button id="btnSubmit" type="submit" class="btn default-button"><i class="fa fa-floppy-o" aria-hidden="true"></i> save</button>-->
                                    
                                    <s:url var="updateportconfurl" action="UpdateportConf"/>                                   
                                    <div class="btn-wrap"><i class="fa fa-pencil-square-o" aria-hidden="true"></i><sj:submit  href="%{updateportconfurl}" disabled="#vupdate" targets="divmsg" value="Update"  button="true"   cssClass="btn default-button searchicon" /></div>
                                    <div class="btn-wrap"><i class="fa fa-times" aria-hidden="true"></i><sj:submit button="true" value="Reset" onClick="resetUpdateForm()" cssClass="btn reset-button"/></div>
                                </div>
                            </div>
                            <!-- End -->
                        </div>
                    <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                    </s:form>                    
                </div>

                <!-- Grid data begin -->
                <div class="content-section">
                    <div class="content-data">
                        <h2 class="section-title">All Registered Ports</h2>
                    </div>

                    <sj:dialog 
                        id="deleteConfirmDialog" 
                        buttons="{ 
                        'OK':function() { deleteUser($(this).data('uname'),$(this).data('utypeid'));$( this ).dialog( 'close' ); },
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

                    <div id="tablediv" class="custom-grid">

                        <s:url var="listurl" action="ListportConf"/>
                                <sjg:grid
                                    id="gridtable"
                                    caption="Port Details"
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
                                    <sjg:gridColumn name="description" index="description" title="Name"      align="left" width="35" sortable="true"  /> 
                                    <sjg:gridColumn name="datarate"    index="datarate"    title="Data Rate" align="left" width="20" sortable="true"/>                    
                                    <sjg:gridColumn name="databit"     index="databits"     title="Data Bits" align="left" width="20" sortable="true"/>
                                    <sjg:gridColumn name="parity"      index="parity"      title="Parity"    align="left" width="20" sortable="true"/>
                                    <sjg:gridColumn name="stopbit"     index="stopbits"     title="Stop Bits" align="left" width="20" sortable="true"/>
                                    <sjg:gridColumn name="port"        index="port"        title="Port"      align="left" width="20" sortable="true" />
                                    <sjg:gridColumn name="status"      index="status"      title="Status"    align="left" width="20" sortable="true"  /> 
                                    <sjg:gridColumn name="sid"         index="sid"         title="Edit"      align="center" width="20" formatter="editformatter"   sortable="false" cssClass="action-col"/>
                                </sjg:grid>  
                    </div>

                </div>
                <!-- End -->

                <!--End of Body Content-->
            </div>
            <jsp:include page="../../footer.jsp" />

        </section>

        <script type="text/javascript">
            $(document).ready(function () {
                //Back button event
                $('.lnk-back').on('click', function () {
                    $('#portConfEditForm').hide();
                    $('#searchdiv').show();
                    utilityManager.resetMessage();
                    $('#addDiv').hide();
                    $('#task').empty();
                    jQuery("#gridtable").trigger("reloadGrid");
                    $('.lnk-back').addClass('hide-element');
                    var text = ' Port Configuration';
                    $('#task').append(text);
                    return false;
                });
            });


        </script>
        <!-- End -->
    </body>





    
</html>
