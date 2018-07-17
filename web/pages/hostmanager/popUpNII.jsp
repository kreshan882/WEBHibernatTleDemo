<%-- 
    Document   : popUpAssignBin
    Created on : Mar 28, 2017, 3:37:44 PM
    Author     : thilina_t
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"  %>  
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%> 

<!DOCTYPE html>
<html>
    <script type="text/javascript">


        function fdeleteformatter(cellvalue, options, rowObject) {
            return "<a href='#' onClick='fdeleteInit(&#34;" + cellvalue + "&#34;,&#34;" + rowObject.niiName + "&#34;,&#34;" + rowObject.mapNii + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/delete_icon.png' /></a>";
        }

        function fdeleteInit(id, name, map) {
            $("#confirmdialogboxf").data('keyval1', id).dialog('open');
            $("#confirmdialogboxf").data('keyname', name).dialog('open');
            $("#confirmdialogboxf").data('keymap', map).dialog('open');
            $("#confirmdialogboxf").html('<br><b><font size="3" color="red"><center>Please confirm to delete NII : ' + name);

            return false;
        }

        function fdeleteNow(id, name, map) {
            $.ajax({
                url: '${pageContext.request.contextPath}/Deletenii',
                data: {group: id, niiName: name, mapNii: map},
                dataType: "json",
                type: "POST",
                success: function (data) {
                    if (data.isDeleted == true) {
                        $("#dialogboxf").dialog('open');
                        $("#dialogboxf").html('<br><b><font size="3" color="green"><center>' + data.dmessage + ' ');
                    } else {
                        $("#dialogboxf").dialog('open');
                        $("#dialogboxf").html('<br><b><font size="3" color="red"><center>' + data.dmessage + ' ');
                    }
                    jQuery("#gridtable1").trigger("reloadGrid");
                },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
            });

        }

        function clearField() {

            $('#Nii').val("");
            $('#mapNii').val("");
            $('#fdivmsg').empty();
            jQuery("#gridtable1").trigger("reloadGrid");

        }

    </script>
    <body>

        <div class="contentcenter">

            <div class="message">         
                <s:div id="fdivmsg">
                    <i style="color: red"> <s:actionerror theme="jquery"/></i>
                    <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                </s:div>         
            </div>
            <s:set var="fvupdate"><s:property value="vupdate" default="true"/></s:set>
            <s:set id="fvadd" var="vadd"><s:property value="vadd" default="true"/></s:set>

            <s:form id="addForm1" theme="simple">
                <table border="0">
                    <tr>
                        <td class="lable">NII<span class="mandatory">*</span></td>
                        <td class="lable">:</td>
                        <td colspan="2"><s:textfield name="Nii" id="Nii" cssClass="text_field" maxLength="3"/></td>
                        <s:hidden name="NiiId" id="NiiId"/>
                    </tr>
                    <tr>
                        <td class="lable">Map NII<span class="mandatory">*</span></td>
                        <td class="lable">:</td>
                        <td colspan="2"><s:textfield name="mapNii" id="mapNii" cssClass="text_field" maxLength="3"/></td>
                    </tr>
                </table>
                <br/>

                <table class="form_table">
                    <s:url var="addurl1" action="Addnii"/>
                    <tr>
                        <td>
                            <sj:submit button="true" href="%{addurl1}" value="Add"   targets="fdivmsg"  cssClass="add" disabled="#fvadd"/> 
                        </td>
                        <td>
                            <sj:a href="#" name="Reset" button="true" onclick="clearField()"  cssClass="add" cssStyle="font-weight: normal;">Reset</sj:a>
                            </td>
                            <td>
                            <sj:a href="#" name="back" button="true" onclick="facilityPopupClose()"  cssClass="add" cssStyle="font-weight: normal;">Back</sj:a>
                            </td>
                        </tr>
                    </table>
            </s:form>

            <s:url var="listur" action="listnii">
                <s:param name="NiiId"><s:property value="NiiId"/></s:param>
            </s:url>

            <div class="viewuser_tbl">
                <div id="tablediv">

                    <sj:dialog 
                        id="confirmdialogboxf" 
                        buttons="{ 
                        'OK':function() { fdeleteNow($(this).data('keyval1'),$(this).data('keyname'),$(this).data('keymap'));$( this ).dialog( 'close' ); },
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
                        caption="NII"
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

                        <sjg:gridColumn name="group" index="group" title="id"   hidden="true" />
                        <sjg:gridColumn name="niiName" index="id.nii" title="NII"  align="center" width="25"  sortable="true"/>
                        <sjg:gridColumn name="mapNii" index="id.mapNii" title="MAP NII"  align="center" width="25"  sortable="true"/>
                        <sjg:gridColumn name="group" index="group" title="Delete"  width="25" align="center" formatter="fdeleteformatter" sortable="false"/>
                    </sjg:grid> 
                </div>
            </div>

        </div>

    </body>
</html>
