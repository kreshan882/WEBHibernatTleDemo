<%-- 
    Document   : keyInjection
    Created on : Sep 16, 2015, 2:49:14 PM
    Author     : ravideva
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
                ping: 0;
            }
            .ui-state-default, .ui-widget-content .ui-state-default, .ui-widget-header .ui-state-default {
                font-weight: normal; 
            }
        </style>
        <script type="text/javascript">
            function resetForm() {
//                $('#terminalKI').val("");
                $('#terminalKM').val("");

//                $("#assignbut").click();
            }
            function resetFormKey() {
                utilityManager.resetMessage();
                $('#terminalKI').val("");
                $('#terminalKM').val("");

            }
            function addForm() {
                utilityManager.resetMessage();
                var terminalKM = $('#terminalKM').val();
                $.ajax({
                    url: '${pageContext.request.contextPath}/addKeyMailkeyField',
                    data: {terminalKM: terminalKM},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
//                        resetForm();
                        if (data.successmsg) {

                            $('#bankaname').val(data.bankaname);
                            $('#terminalid').val(data.terminalid);
                            $('#merchantname').val(data.merchantname);
                            $('#mek').val(data.mek);
                            $('#issuedDate').val(data.issuedDate);
                            $('#keyid').val(data.keyid);
                            $('#tmk').val(data.tmk);
                            utilityManager.showMessage('.add-form-msg', data.message, 'successmsg');
                            $("#assignbut").click();

                        } else {
                            utilityManager.showMessage('.add-form-msg', data.message, 'errormsg');
                        }

                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }

                });
            }
            function NotWorking() {
                utilityManager.showMessage('.add-form-msg', "This Function not working yet.", 'errormsg');
            }


        </script>
        <s:set id="vadd" var="vadd"><s:property  value="vadd" default="true"/></s:set>
        <s:set id="vdownload" var="vdownload"><s:property  value="vdownload" default="true"/></s:set>


        </head>
        <body>
            <section class="app-content">
            <jsp:include page="../../header.jsp" />
            <!-- Page content begin -->
            <div class="content innerpage">
                <!-- Breadcrumb begin -->
                <div class="breadcrumb">
                    Field Engineer Management <i class="fa fa-angle-double-right" aria-hidden="true"></i><span class="active"> Terminal Key Injection</span>
                </div>
                <!-- End -->

                <!-- Data form begin -->
                <div class="content-section data-form" id="addEngineer" style="diaplay:none;">

                    <s:form id="terkeyinject" theme="simple" method="post" >
                        <div class="content-data">
                            <!-- Error and success message panel begin -->
                            <div class="msg-panel add-form-msg" id="addMessage" > 
                                <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                            </div>
                            <div class="d-row">
                                <label class="left-col form-label">Terminal ID<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="terminalKI" name="terminalKI" maxLength="8" cssClass="txt-input width-35"/>
                                </div>
                            </div>
                            <div class="d-row cpanel">
                                <label class="left-col">&nbsp;</label>
                                <div class="right-col">
                                    <s:url var="saveurl" action="addKeyInjectkeyField"/>                                   
                                    <!--<div class="btn-wrap"><i class="fa fa-eject" aria-hidden="true"></i><sj:submit  href="%{saveurl}"  targets="divmsg" value="Inject"  button="true"   cssClass="btn default-button" /></div>-->
                                    <sj:a id="sbutton" button="true" disabled="#vadd"  value="Submit" cssClass="btn default-button"><i class="fa fa-eject" aria-hidden="true"></i> Submit</sj:a>
                                    <sj:a button="true" onclick="resetFormKey()" value="Reset" cssClass="btn reset-button"><i class="fa fa-times" aria-hidden="true"></i> Reset</sj:a>
                                        <!--<button type="reset" class="btn reset-button"><i class="fa fa-times" aria-hidden="true"></i> reset</button>-->
                                        <!--onclick="NotWorking()"-->
                                    </div>
                                </div>
                            </div>

                    </s:form>

                </div>
                <h1></h1>
                <div class="content-section data-form" id="addEngineer">
                    <s:form id="terkeymailer" theme="simple" method="post" >
                        <div class="content-data">
                            <!-- Error and success message panel begin -->
                            <!--                            <div class="msg-panel add-form-msg" id="addMessage" > 
                                                            <label>&nbsp;</label><div><i class="fa fa-times" aria-hidden="true"></i><span id="divmsg"></span></div>
                                                        </div>-->
                            <div class="d-row">
                                <label class="left-col form-label">Terminal ID<sup class="required">*</sup></label>
                                <div class="right-col form-field">
                                    <s:textfield id="terminalKM" name="terminalKM" maxLength="8" cssClass="txt-input width-35"/>
                                </div>
                            </div>
                            <div class="d-row cpanel">
                                <label class="left-col">&nbsp;</label>
                                <div class="right-col">

                                    <s:url var="mailurl" action="addKeyMailkeyField"/>                                   
                                    <!--<div class="btn-wrap"><i class="fa fa-print" aria-hidden="true"></i><sj:submit  href="%{mailurl}"  targets="divmsg" value="Print"  button="true"   cssClass="btn default-button" /></div>-->
                                    <sj:a id="dbutton"
                                          disabled="#vdownload"
                                          button="true" 

                                          cssClass="btn default-button" ><i class="fa fa-print" aria-hidden="true"></i> Print</sj:a>
                                    <sj:a button="true" onclick="resetFormKey()" value="Reset" cssClass="btn reset-button"><i class="fa fa-times" aria-hidden="true"></i> Reset</sj:a>
                                    </div>
                                </div>
                            </div>
                            <!--onclick="addForm()"--> 
                    </s:form>
                </div>

            </div>
            <s:form action="downloadpdfkeyField" theme="simple" >
                <s:hidden id="bankaname" name="bankaname"/>
                <s:hidden id="terminalid" name="terminalid" />
                <s:hidden id="merchantname" name="merchantname"/>
                <s:hidden id="mek" name="mek" />
                <s:hidden id="issuedDate" name="issuedDate" />
                <s:hidden id="keyid" name="keyid" />
                <s:hidden id="tmk" name="tmk" />
                <s:submit button="true"  id="assignbut" cssStyle="display: none; visibility: hidden;"  />
            </s:form>
            <!--End of Body Content-->

            <jsp:include page="../../footer.jsp" />

        </section>
        <script type="text/javascript">
            $(document).ready(function () {
                $('#sbutton').click(function () {
                    if ($('#sbutton').attr('disabled') != undefined) {
                        return false;
                    } else {
                        NotWorking();
                    }

                });
                $('#dbutton').click(function () {
                    if ($('#dbutton').attr('disabled') != undefined) {
                        return false;
                    } else {
                        addForm();
                    }

                });



//                alert($('#dbutton').attr('disabled'));
                //Show form validations button event
                $('#btnSubmit').on('click', function () {
                    utilityManager.showMessage('.add-form-msg', 'User name is required', 'errormsg');
                    return false;
                });

                //Add data button event
                $('#btnAdd').on('click', function () {
                    $('.search-form').removeClass('hide-element').removeClass('show-element').addClass('hide-element');
                    $('.data-form').eq(0).removeClass('hide-element');
                    $('.lnk-back').removeClass('hide-element');
                    return false;
                });

                //Back button event
                $('.lnk-back').on('click', function () {
                    $('.search-form').removeClass('hide-element');
                    $('.data-form').eq(0).addClass('hide-element');
                    $('.lnk-back').addClass('hide-element');
                    $('.add-form-msg').hide();
                    return false;
                });
            });


        </script>

    </body>

</html>

