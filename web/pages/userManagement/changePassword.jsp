<%-- 
    Document   : changePassword
    Created on : Feb 13, 2017, 10:43:57 AM
    Author     : danushka_r
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
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
        <script type="text/javascript">
            function resetForm() {
                $('#oldPassword').val("");
                $('#newPassword').val("");
                $('#repeatPassword').val("");
            }
            function resetMessage() {
                resetForm();
                $('.add-form-msg').hide();
            }

        </script>
    </head>

    <s:set id="vupdate" var="vupdate"><s:property value="update" default="true"/></s:set>

        <body>
            <section class="app-content">
            <jsp:include page="../../header.jsp" /> 

            <div class="content innerpage">
                <!-- Breadcrumb begin -->
                <div class="breadcrumb">
                    User Management <i class="fa fa-angle-double-right" aria-hidden="true"></i><span class="active">Change Password</span>  
                </div>
                <!-- End -->

                <!-- Page title begin -->
                <h1 class="page-title">Change Password<a href="#" class="lnk-back hide-element do-nothing"><i class="fa fa-arrow-left" aria-hidden="true"></i> back</a></h1>

                <div class="content-section data-form">
                    <s:form id="changepass" name="changepass" method="post" theme="simple">
                        <div class="content-data">

                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg" >
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                            </div>
                            <!-- End -->

                            <!-- Two colum form row begin -->
                            <div class="d-row">
                                <label class="left-col form-label">Old Password<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:password id="oldPassword" name="oldPassword" cssClass="txt-input width-35"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">New Password<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:password id="newPassword" name="newPassword" cssClass="txt-input width-35"/>
                                </div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Repeat Password<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:password id="repeatPassword" name="repeatPassword" cssClass="txt-input width-35"/>
                                </div>
                            </div>

                            <div class="d-row cpanel">
                                <label class="left-col">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="saveurl" action="changePasswordusrChange"/>                                   
                                    <div class="btn-wrap"><i class="fa fa-exchange" aria-hidden="true"></i><sj:submit  href="%{saveurl}"  targets="divmsg" value="Change"  button="true" disabled="#vupdate" cssClass="btn default-button" /></div>
                                    <div class="btn-wrap"><i class="fa fa-times" aria-hidden="true"></i><sj:submit  button="true" onclick="resetMessage()"  value="Reset" cssClass="btn reset-button"/></div>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="RequstToken" class="RequstToken" value='<%=session.getAttribute("SessionToken")%>'/>
                    </s:form>
                </div>
                <!--End of Body Content-->

                <jsp:include page="../../footer.jsp" />
        </section>
    </body>
</html>

