<%-- 
    Document   : addNewUserProfile
    Created on : Oct 30, 2014, 1:50:20 PM
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
                padding: 4;
            }
            .ui-button, ui-widget ui-state-default, ui-button-text-only {
                font-family: arial;
                font-size: 13px;
                margin-top: 5px;
                border:0px solid #1d5cd6 !important;
                text-decoration: none;
                font-weight:normal !important;
            }

            .comboBox{
                border: 1px solid #656363;
                height: 220px;
                width: 220px;
                box-shadow: 0 0 8px #e4e2e1 inset;
                /*transition: 500ms all ease;*/
                padding: 3px 3px 3px 3px;
                margin-top: 3px;
                margin-bottom: 3px;
            }
            .comboBox:hover,
            .comboBox:focus{
                box-shadow: 0 0 8px #ffffff inset;
                box-shadow: 0 0 8px #9f9e9e;
            }

        </style>
        <script type="text/javascript">
            
            
            function resetForm(){
//                $('#message').empty();
                $('#profilename').val("");
//                $('#description').empty();
                $('#modules').val("-1");
                
            }
            function loadModulePage(selectedModule) {

                if (selectedModule == null) {
                    var temp = true;
                }
                $('#newBox2 option').prop('selected', true);
                $('#currentBox2 option').prop('selected', true);
                $.ajax({
                    url: '${pageContext.request.contextPath}/loadPagesUserProfileAdd.action',
                    data: {modules: selectedModule, selecterpages: $('#newBox2').val(), unselecterpages: $('#currentBox2').val()
                    },
                    dataType: "json",
                    type: "POST",
                    success: function (data) {

                        $('#currentBox2').empty();
                        $('#newBox2').empty();

                        $.each(data.pagesMap, function (val, text) {
                            $('#currentBox2').append($('<option></option>').val(val).html(text));
                        });
                        $.each(data.pagesFinalMap, function (val, text) {
                            $('#newBox2').append($('<option></option>').val(val).html(text));
                        });
                        if (temp) {
                            $("#assignbut").click();
                        }
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }

                });    //ajex end
            }          //function end





            function toleft2() {
                $("#newBox2 option:selected").each(function () {

                    $("#currentBox2").append($('<option>', {
                        value: $(this).val(),
                        text: $(this).text()
                    }));
                    $(this).remove();
                });
            }
            ;
            function toright2() {
                $("#currentBox2 option:selected").each(function () {

                    $("#newBox2").append($('<option>', {
                        value: $(this).val(),
                        text: $(this).text()
                    }));
                    $(this).remove();
                });
            }
            function toleftall2() {
                $("#newBox2 option").each(function () {

                    $("#currentBox2").append($('<option>', {
                        value: $(this).val(),
                        text: $(this).text()
                    }));
                    $(this).remove();
                });
            }
            function torightall2() {
                $("#currentBox2 option").each(function () {

                    $("#newBox2").append($('<option>', {
                        value: $(this).val(),
                        text: $(this).text()
                    }));
                    $(this).remove();
                });
            }


            function resetButton() {
                $('#message').empty();
                $('#profilename').empty();
//                $('#description').empty();
                $('#modules').val("-1");
            }

        </script>
    </head>

    <body style="overflow:hidden">
        <div class="wrapper">
            <jsp:include page="../../header.jsp" /> 

            <!--Body Content-->
            <div class="body_right">
                <div class="heading"><div class="underline">User Profile Management</div></div>
                <div class="content">
                    <div class="content_highlight">
                        <div class="message">
                            <s:div id="message">
                                <i style="color: red">  <s:actionerror theme="jquery"/></i>
                                <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                            </s:div>
                        </div>
                    </div>
                    <div class="display">
                        <s:form action="submitUserProfileAdd" theme="simple">
                            <table border="0">
                                <tr>
                                    <td class="lable">Name<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td colspan="2"><s:textfield name="profilename" id="profilename" cssClass="text_field"/></td>
                                </tr>
                                <tr>
                                    <td class="lable">Modules<span class="mandatory">*</span></td>
                                    <td class="lable">:</td>
                                    <td colspan="2">
                                        <s:select id="modules" name="modules" headerKey="0" headerValue="---Select---"  
                                                  list="%{modulesMap}" 
                                                  onchange="loadModulePage(this.value)" cssClass="dropdown"/></td>
                                </tr>
                            </table>

                            <table border="0" style="margin-left: 70px;">

                                <tr>
                                    <td rowspan="4"><s:select multiple="true" name="currentBox2" id="currentBox2" list="pagesMap" ondblclick="toright2()"  cssClass="comboBox" /></td>
                                    <td valign="bottom"><sj:a  id="right2" onClick="toright2()" button="true"  cssClass="arrow" cssStyle="padding: 0px 0px;"> > </sj:a> </td>
                                    <td rowspan="4" colspan="2"><s:select multiple="true"  name="newBox2" id="newBox2" list="newList2" ondblclick="toleft2()" cssClass="comboBox" /></td>
                                </tr>
                                <tr>
                                    <td height="24px"><sj:a  id="rightall2" onClick="torightall2()" button="true"  cssClass="arrow" cssStyle="padding: 0px 0px;"> >> </sj:a></td>
                                    </tr>
                                    <tr>
                                        <td height="24px"><sj:a  id="left2" onClick="toleft2()" button="true"  cssClass="arrow" cssStyle="padding: 0px 0px;"><</sj:a></td>
                                    </tr>
                                    <tr>
                                        <td valign="top"><sj:a  id="leftall2" onClick="toleftall2()" button="true"  cssClass="arrow" cssStyle="padding: 0px 0px;"><<</sj:a></td>
                                    </tr>
                                    <tr>
                                        <td colspan="4"><span class="mandatory_text">Mandatory fields are marked with</span><span class="mandatory">*</span></td>
                                    </tr>
                                    <tr>
                                        <td colspan="2"></td>
                                        <td align="right"><s:submit button="true"  id="assignbut" cssStyle="display: none; visibility: hidden;" cssClass="add" />
                                        <sj:a button="true" onclick="loadModulePage()" cssClass="add" cssStyle="padding:1px 16px;font-weight: normal !important;">Save</sj:a></td>
                                    <td align="left" width="25px"><s:reset button="true" value="Reset"  cssClass="add" onclick="resetButton()"></s:reset></td>
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