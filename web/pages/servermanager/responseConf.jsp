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
        <style>
            .ui-button-text-only .ui-button-text {
                ping: 0;
            }
            .ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
                font-weight: normal; 
            }
        </style>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#btnsave').hide();
                $('#btnreset').hide();
                $('#btncancel').hide();
            });
            function resetForm() {
            }

            function fieldEnable() {
                document.getElementById("f_02").disabled = false;
                document.getElementById("f_03").disabled = false;
                document.getElementById("f_04").disabled = false;
                document.getElementById("f_11").disabled = false;
                document.getElementById("f_12").disabled = false;
                document.getElementById("f_13").disabled = false;
                document.getElementById("f_14").disabled = false;
                document.getElementById("f_22").disabled = false;
                document.getElementById("f_23").disabled = false;
                document.getElementById("f_24").disabled = false;
                document.getElementById("f_25").disabled = false;
                document.getElementById("f_35").disabled = false;
                document.getElementById("f_37").disabled = false;
                document.getElementById("f_38").disabled = false;
                document.getElementById("f_39").disabled = false;
                document.getElementById("f_41").disabled = false;
                document.getElementById("f_42").disabled = false;
                document.getElementById("f_45").disabled = false;
                document.getElementById("f_47").disabled = false;
                document.getElementById("f_48").disabled = false;
                document.getElementById("f_52").disabled = false;
                document.getElementById("f_54").disabled = false;
                document.getElementById("f_55").disabled = false;
                document.getElementById("f_60").disabled = false;
                document.getElementById("f_61").disabled = false;
                document.getElementById("f_62").disabled = false;
                document.getElementById("f_63").disabled = false;
                document.getElementById("f_49").disabled = false;
                $('#btnedit').hide();
                $('#btnsave').show();
                $('#btnreset').show();
                $('#btncancel').show();
            }

            function resetResponseConfForm() {
                var token=$( "input[name='RequstToken']" ).val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/ResetDataresConf',
                    data: {RequstToken: token},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $stoken=data.token;
                        $( "input[name='RequstToken']" ).val($stoken);
                        $('#f_02').val(data.f_02);
                        $('#f_03').val(data.f_03);
                        $('#f_04').val(data.f_04);
                        $('#f_11').val(data.f_11);
                        $('#f_12').val(data.f_12);
                        $('#f_13').val(data.f_13);
                        $('#f_14').val(data.f_14);
                        $('#f_22').val(data.f_22);
                        $('#f_23').val(data.f_23);
                        $('#f_24').val(data.f_24);
                        $('#f_25').val(data.f_25);
                        $('#f_35').val(data.f_35);
                        $('#f_37').val(data.f_37);
                        $('#f_38').val(data.f_38);
                        $('#f_39').val(data.f_39);
                        $('#f_41').val(data.f_41);
                        $('#f_42').val(data.f_42);
                        $('#f_45').val(data.f_45);
                        $('#f_47').val(data.f_47);
                        $('#f_48').val(data.f_48);
                        $('#f_52').val(data.f_52);
                        $('#f_54').val(data.f_54);
                        $('#f_55').val(data.f_55);
                        $('#f_60').val(data.f_60);
                        $('#f_61').val(data.f_61);
                        $('#f_62').val(data.f_62);
                        $('#f_63').val(data.f_63);
                        $('#f_49').val(data.f_49);
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
            
                resetResponseConfForm();
                
                document.getElementById("f_02").disabled = true;
                document.getElementById("f_03").disabled = true;
                document.getElementById("f_04").disabled = true;
                document.getElementById("f_11").disabled = true;
                document.getElementById("f_12").disabled = true;
                document.getElementById("f_13").disabled = true;
                document.getElementById("f_14").disabled = true;
                document.getElementById("f_22").disabled = true;
                document.getElementById("f_23").disabled = true;
                document.getElementById("f_24").disabled = true;
                document.getElementById("f_25").disabled = true;
                document.getElementById("f_35").disabled = true;
                document.getElementById("f_37").disabled = true;
                document.getElementById("f_38").disabled = true;
                document.getElementById("f_39").disabled = true;
                document.getElementById("f_41").disabled = true;
                document.getElementById("f_42").disabled = true;
                document.getElementById("f_45").disabled = true;
                document.getElementById("f_47").disabled = true;
                document.getElementById("f_48").disabled = true;
                document.getElementById("f_52").disabled = true;
                document.getElementById("f_54").disabled = true;
                document.getElementById("f_55").disabled = true;
                document.getElementById("f_60").disabled = true;
                document.getElementById("f_61").disabled = true;
                document.getElementById("f_62").disabled = true;
                document.getElementById("f_63").disabled = true;
                document.getElementById("f_49").disabled = true;
                $('#btnedit').show();
                $('#btnsave').hide();
                $('#btnreset').hide();
                $('#btncancel').hide();
                $('#divmsg').empty();
                utilityManager.resetMessage();

            }
        </script>

    </head>
    <s:set id="vadd" var="vadd"><s:property  value="vadd" default="true"/></s:set>
    <s:set id="vupdate" var="vupdate"><s:property value="vupdate" default="true"/></s:set>
    <s:set id="vdelete" var="vdelete"><s:property value="vdelete" default="true"/></s:set>
    <s:set var="vview"><s:property value="view" default="true"/></s:set>

        <body>
            <section class="app-content">
            <jsp:include page="../../header.jsp" /> 

            <!-- Page content begin -->
            <div class="content innerpage">
                <!-- Breadcrumb begin -->
                <div class="breadcrumb">
                    <s:property value="Module"/> <i class="fa fa-angle-double-right" aria-hidden="true"></i><span class="active"> <s:property value="Section"/></span>
                </div>
                <!-- End -->

                <h1 class="page-title"><s:property value="Section"/><a href="#" class="lnk-back hide-element do-nothing"><i class="fa fa-arrow-left" aria-hidden="true"></i> back</a></h1>

                <div class="content-section data-form" id="addDiv">
                    <s:form   theme="simple" id="serverConfId" method="POST">
                        <div class="content-data">
                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg" > 
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                            </div>
                            <!-- End -->
                            <div class="d-row">
                                <label class="col-1 form-label">Field 02<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="f_02"                                              
                                               listKey="key" listValue="value"
                                               list="%{f_02_Map}" id="f_02" cssClass="ddl-input"  disabled="true"/>
                                </div>                            
                                <label class="col-3 form-label">Field 03<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="f_03"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_03_Map}" id="f_03" cssClass="ddl-input"  disabled="true"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Field 04<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="f_04"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_04_Map}" id="f_04" cssClass="ddl-input"  disabled="true"/>
                                </div>                            
                                <label class="col-3 form-label">Field 11<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="f_11"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_11_Map}" id="f_11" cssClass="ddl-input"  disabled="true"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Field 12<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="f_12"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_12_Map}" id="f_12" cssClass="ddl-input"  disabled="true"/>
                                </div>                            
                                <label class="col-3 form-label">Field 13<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="f_13"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_13_Map}" id="f_13" cssClass="ddl-input"  disabled="true"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Field 14<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="f_14"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_14_Map}" id="f_14" cssClass="ddl-input"  disabled="true"/>
                                </div>                            
                                <label class="col-3 form-label">Field 22<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="f_22"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_22_Map}" id="f_22" cssClass="ddl-input"  disabled="true"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Field 23<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="f_23"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_23_Map}" id="f_23" cssClass="ddl-input"  disabled="true"/>
                                </div>

                                <label class="col-3 form-label">Field 24<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="f_24"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_24_Map}" id="f_24" cssClass="ddl-input"  disabled="true"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Field 25<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="f_25"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_25_Map}" id="f_25" cssClass="ddl-input"  disabled="true"/>
                                </div>

                                <label class="col-3 form-label">Field 35<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="f_35"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_35_Map}" id="f_35" cssClass="ddl-input"  disabled="true"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Field 37<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="f_37"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_37_Map}" id="f_37" cssClass="ddl-input"  disabled="true"/>
                                </div>

                                <label class="col-3 form-label">Field 38<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="f_38"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_38_Map}" id="f_38" cssClass="ddl-input"  disabled="true"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Field 39<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="f_39"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_39_Map}" id="f_39" cssClass="ddl-input"  disabled="true"/>
                                </div>

                                <label class="col-3 form-label">Field 41<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="f_41"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_41_Map}" id="f_41" cssClass="ddl-input"  disabled="true"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Field 42<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="f_42"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_42_Map}" id="f_42" cssClass="ddl-input"  disabled="true"/>
                                </div>

                                <label class="col-3 form-label">Field 45<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="f_45"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_45_Map}" id="f_45" cssClass="ddl-input"  disabled="true"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Field 47<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="f_47"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_47_Map}" id="f_47" cssClass="ddl-input"  disabled="true"/>
                                </div>

                                <label class="col-3 form-label">Field 48<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="f_48"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_48_Map}" id="f_48" cssClass="ddl-input"  disabled="true"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Field 49<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="f_49"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_49_Map}" id="f_49" cssClass="ddl-input"  disabled="true"/>
                                </div>

                                <label class="col-3 form-label">Field 52<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="f_52"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_52_Map}" id="f_52" cssClass="ddl-input"  disabled="true"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Field 54<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="f_54"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_54_Map}" id="f_54" cssClass="ddl-input"  disabled="true"/>
                                </div>

                                <label class="col-3 form-label">Field 55<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="f_55"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_55_Map}" id="f_55"  cssClass="ddl-input"  disabled="true"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Field 60<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="f_60"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_60_Map}" id="f_60" cssClass="ddl-input"  disabled="true"/>
                                </div>

                                <label class="col-3 form-label">Field 61<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="f_61"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_61_Map}" id="f_61" cssClass="ddl-input"  disabled="true"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="col-1 form-label">Field 62<sup class="required">*</sup></label>
                                <div class="col-2 form-field">
                                    <s:select  name="f_62"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_62_Map}" id="f_62" cssClass="ddl-input"  disabled="true"/>
                                </div>

                                <label class="col-3 form-label">Field 63<sup class="required">*</sup></label>
                                <div class="col-4 form-field">
                                    <s:select  name="f_63"                                               
                                               listKey="key" listValue="value"
                                               list="%{f_63_Map}" id="f_63" cssClass="ddl-input"  disabled="true"/>
                                </div>
                            </div>
                            <div class="d-row cpanel four-col">
                                <label class="col-1">&nbsp;</label>
                                <div class="right-col">

                                    <s:url var="inserturl"/>
                                    <div class="btn-wrap" id="btnedit"><i class="fa fa-pencil" aria-hidden="true"></i><sj:submit href="%{inserturl}" button="true" disabled="#vupdate" cssClass="btn default-button" label="Edit"  value="Edit"  onclick="fieldEnable()"  /></div>

                                    <s:url action="UpdateresConf" var="inserturl"/>
                                    <div class="btn-wrap" id="btnsave" ><i class="fa fa-floppy-o" aria-hidden="true"></i><sj:submit href="%{inserturl}" button="true" label="Save" disabled="#vupdate" cssClass="btn default-button searchicon" targets="divmsg" value="Save" /></div>
                                     <div class="btn-wrap" id="btnreset"> <i class="fa fa-times" aria-hidden="true"></i><sj:submit button="true" value="Reset" cssClass="btn reset-button" onClick="resetResponseConfForm(); return false;"/></div>
                                    <div class="btn-wrap" id="btncancel" ><i class="fa fa-reply" aria-hidden="true"></i><sj:submit button="true" value="Cancel" cssClass="btn default-button" onclick="cancelBtn()" /></div>
                                   
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                    </s:form>

                </div>
            </div>
            <jsp:include page="../../footer.jsp" />

        </section>

        <script type="text/javascript">
            $(document).ready(function () {
                //Back button event
                $('.lnk-back').on('click', function () {
                    utilityManager.resetMessage();
                    $('#webuserEditForm').hide();
                    $('#searchdiv').show();
                    $('.add-form-msg').hide();
                    $('#addDiv').hide();
                    $('#task').empty();
                    jQuery("#gridtable").trigger("reloadGrid");
                    $('.lnk-back').addClass('hide-element');
                    var text = ' Search User';
                    $('#task').append(text);
                    return false;
                });
            });


        </script>
        <!-- End -->
    </body>

</html>
