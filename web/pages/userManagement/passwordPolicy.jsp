<%-- 
    Document   : oasswordPolicy
    Created on : Feb 13, 2017, 10:43:29 AM
    Author     : danushka_r
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"  %>  
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<html>
    <head>

        <jsp:include page="../../Styles.jsp" />
        <script type="text/javascript">
            function resetForm() {
            }

            $(document).ready(function () {
//                var maxLnth= '<%=session.getAttribute("max_length").toString() %>';
//                alert(maxLnth);
//                document.getElementById("max_len").maxlength5;
                $('#btnsave').hide();
                $('#btnreset').hide();
                $('#btncancel').hide();
            });
            function fieldEnable() {
                document.getElementById("max_len").disabled = false;
                document.getElementById("min_len").disabled = false;
                document.getElementById("allowspecialcharacters").disabled = false;
                document.getElementById("min_spcl_chars").disabled = false;
                document.getElementById("max_spcl_chars").disabled = false;
                document.getElementById("min_upercase").disabled = false;
                document.getElementById("min_lowcase").disabled = false;
                document.getElementById("min_numerics").disabled = false;
                $('#btnedit').hide();
                $('#btnsave').show();
                $('#btnreset').show();
                $('#btncancel').show();

            }
            function resetPwdPolicyForm() {
//                var keyval = $('#sid').val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/resetDatausrPolicy',
                    data: {},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        $('#max_len').val(data.max_len);
                        $('#min_len').val(data.min_len);
                        $('#allowspecialcharacters').val(data.allowspecialcharacters);
                        $('#min_spcl_chars').val(data.min_spcl_chars);
                        $('#max_spcl_chars').val(data.max_spcl_chars);
                        $('#min_upercase').val(data.min_upercase);
                        $('#min_lowcase').val(data.min_lowcase);
                        $('#min_numerics').val(data.min_numerics);
                        $('#divmsg').empty();
                        $('.add-form-msg').hide();
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }

                });
            }

            function cancelBtn() {
                resetPwdPolicyForm();
                document.getElementById("max_len").disabled = true;
                document.getElementById("min_len").disabled = true;
                document.getElementById("allowspecialcharacters").disabled = true;
                document.getElementById("min_spcl_chars").disabled = true;
                document.getElementById("max_spcl_chars").disabled = true;
                document.getElementById("min_upercase").disabled = true;
                document.getElementById("min_lowcase").disabled = true;
                document.getElementById("min_numerics").disabled = true;
                $('#btnedit').show();
                $('#btnsave').hide();
                $('#btnreset').hide();
                $('#btncancel').hide();
                $('#divmsg').empty();
                $('.add-form-msg').hide();

            }
        </script>

    </head>
    <s:set id="vupdate" var="vupdate"><s:property value="update" default="true"/></s:set>

        <body style="overflow:hidden">
            <section class="app-content">

            <jsp:include page="../../header.jsp" /> 

            <div class="content innerpage">
                <!-- Breadcrumb begin -->
                <div class="breadcrumb">
                    User Management <i class="fa fa-angle-double-right" aria-hidden="true"></i><span class="active"> Password Policy </span> 
                </div>
                <!-- End -->


                <!-- Page title begin -->
                <h1 class="page-title">Password Policy<a href="#" class="lnk-back hide-element do-nothing"><i class="fa fa-arrow-left" aria-hidden="true"></i> back</a></h1>
                <!-- End -->
               
                <div class="content-section data-form">
                    <div class="content-data">
                        <s:form theme="simple" id="pwdpolicyId" method="POST">

                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg">
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i> <span id="divmsg"></span></div>
                            </div>
                            <!-- End -->

                            <!-- Two colum form row begin -->
                            <div class="d-row">
                                <label class="left-col form-label">Minimum Special Characters<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="min_spcl_chars" name="min_spcl_chars" cssClass="txt-input width-10 text-right" disabled="true" maxLength="10"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Maximum Special Characters<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="max_spcl_chars" name="max_spcl_chars" cssClass="txt-input width-10 text-right" disabled="true" maxLength="10"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Allow Special Characters<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="allowspecialcharacters" name="allowspecialcharacters" cssClass="txt-input width-10 text-right" disabled="true" maxLength="10"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Minimum Uppercase<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="min_upercase" name="min_upercase" cssClass="txt-input width-10 text-right" disabled="true" maxLength="10"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Minimum Lowercase<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="min_lowcase" name="min_lowcase" cssClass="txt-input width-10 text-right" disabled="true" maxLength="10"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Minimum Numeric Characters<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="min_numerics" name="min_numerics" cssClass="txt-input width-10 text-right" disabled="true" maxLength="10"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Minimum Length<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="min_len" name="min_len" cssClass="txt-input width-10 text-right" disabled="true" maxLength="10"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <s:hidden name="sid" id="sid"/>
                                <label class="left-col form-label">Maximum Length<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="max_len" name="max_len" cssClass="txt-input width-10 text-right" disabled="true" maxLength="%{#session.max_length}"/>
                                </div>
                            </div>

                            <!-- End -->

                            <!-- Tow column form button panel begin -->		
                            <div class="d-row cpanel">
                                <label class="left-col">&nbsp;</label>
                                <div class="right-col">

                                    <s:url var="inserturl1"/>
                                    <div class="btn-wrap" id="btnedit"><i class="fa fa-pencil" aria-hidden="true"></i><sj:submit href="%{inserturl1}" label="Edit"  value="Edit" cssClass="btn default-button" disabled="#vupdate" onclick="fieldEnable()"/></div>

                                    <s:url action="updateusrPolicy" var="inserturl"/>
                                    <div class="btn-wrap" id="btnsave"><i class="fa fa-floppy-o" aria-hidden="true"></i><sj:submit href="%{inserturl}" button="true" targets="divmsg" value="Save" cssClass="btn default-button" /></div>

                                    <div class="btn-wrap" id="btnreset"> <i class="fa fa-times" aria-hidden="true"></i><sj:submit button="true" value="Reset" onClick="resetPwdPolicyForm(); return false;" cssClass="btn reset-button"/></div>

                                    <div class="btn-wrap" id="btncancel"><i class="fa fa-reply" aria-hidden="true"></i><sj:submit button="true" value="Cancel" onclick="cancelBtn()" cssClass="btn default-button"/></div>

                                </div>
                            </div>
                            <!-- End -->
                            <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                        </s:form>
                    </div>
                </div>
            </div>
            <!--End of Body Content-->


            <jsp:include page="../../footer.jsp" />
        </div><!--End of Wrapper-->
    </section>
</body>
</html>
