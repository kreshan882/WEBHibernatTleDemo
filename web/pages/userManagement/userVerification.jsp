<%-- 
    Document   : userVerification
    Created on : Feb 13, 2017, 10:43:15 AM
    Author     : danushka_r
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
            function confirmformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='confirmWebUserInit(&#34;" + rowObject.userName + "&#34;,&#34;" + rowObject.userTypeId + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/edit_icon.png'  /></a>";
            }

            function confirmWebUserInit(uname) {
                $("#userConfConfirmDialog").data('uname', uname).dialog('open');
                $("#userConfConfirmDialog").html('<br><br><b><font size="3" color="red"><center>Please confirm to the user activate  : '+uname);
                return false;
            }


            function confirmWebUser(uname) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/activeusrVerify',
                    data: {userName: uname},
                    dataType: "json",
                    type: "POST",
                    success: function(data) {
                        if (data.isConfirmed == true) {
                            $("#confirmeMessageDialog").dialog('open');
                            $("#confirmeMessageDialog").html('<br><br><b><font size="3" color="green"><center>'+data.cmessage);
                        } else {
                            $("#confirmeMessageDialog").dialog('open');
                            $("#confirmeMessageDialog").html('<br><br><b><font size="3" color="red"><center>'+data.cmessage);
                        }
                        resetData();
                    }
                });
            }
            
            function deleteformatter(cellvalue, options, rowObject) {
                return "<a href='#' onClick='deleteWebUserInit(&#34;" + rowObject.userName + "&#34;)'><img src='${pageContext.request.contextPath}/resources/images/delete_icon.png'  /></a>";
            }

            function deleteWebUserInit(uname) {
                $("#deleteConfirmDialog").data('uname', uname).dialog('open');
                $("#deleteConfirmDialog").html('<br><br><b><font size="3" color="red"><center>Please confirm delete : ' + uname);
                return false;
            }


            function deleteWebUser(uname, utypeid) {
                $.ajax({
                    url: '${pageContext.request.contextPath}/deleteusrVerify',
                    data: {userName: uname},
                    dataType: "json",
                    type: "POST",
                    success: function (data) {
                        if (data.isDeleted == true) {
                            $("#deleteMessageDialog").dialog('open');
                            $("#deleteMessageDialog").html('<br><br><b><font size="3" color="green"><center>' + data.dmessage);
                        } else {
                            $("#deleteMessageDialog").dialog('open');
                            $("#deleteMessageDialog").html('<br><br><b><font size="3" color="red"><center>' + data.dmessage);
                        }
                        resetData();
                    },
                    error:function(xhr, textStatus, errorThrown){
                        if(xhr.responseText.includes("csrfError.jsp")){
                            window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
                         }
                     }
                });
            }

            function resetData() {
                $('#divmsg').empty();
                jQuery("#gridtable").trigger("reloadGrid");
            }



        </script>

    </head>

    <body style="overflow:hidden">
        <div class="wrapper">
            <jsp:include page="../../header.jsp" /> 

            <!--Body Content-->
            <div class="body_right">
                <div class="heading"><div class="underline">User Verification</div></div>
                <div class="content">

                    <div class="content_highlight">  
                        <div class="message">   
                            <s:div id="divmsg">
                                <i style="color: red; background-color: black !important;">  <s:actionerror theme="jquery"/></i>
                                <i style="color: green"> <s:actionmessage theme="jquery"/></i>
                            </s:div>
                        </div>
                    </div>
                    <div class="table" style="margin-right:50px;">
                        <div id="tablediv">
                            
                            <sj:dialog 
                                id="deleteConfirmDialog" 
                                buttons="{ 
                                'OK':function() { deleteWebUser($(this).data('uname'));$( this ).dialog( 'close' ); },
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
                            <sj:dialog 
                                id="userConfConfirmDialog" 
                                buttons="{ 
                                'OK':function() { confirmWebUser($(this).data('uname'));$( this ).dialog( 'close' ); },
                                'Cancel':function() { $( this ).dialog( 'close' );} 
                                }" 
                                autoOpen="false" 
                                modal="true" 
                                title="Activation User Confirmation"
                                width="400"
                                height="200"
                                position="center"
                                />
                            <sj:dialog 
                                id="confirmeMessageDialog" 
                                buttons="{
                                'OK':function() { $(this).data('cmessage'); $( this ).dialog( 'close' );}
                                }"  
                                autoOpen="false" 
                                modal="true" 
                                title="Active User" 
                                width="400"
                                height="150"
                                position="center"
                                />
                            <s:url var="listurl" action="listusrVerify"/>
                            <sjg:grid
                                id="gridtable"
                                caption="Inactive User Details"
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
                                <sjg:gridColumn name="name"     index="name"        title="Name"            align="left"           width="10" sortable="true"  /> 
                                <sjg:gridColumn name="userName" index="username"    title="User Name"       align="left"           width="10" sortable="true"/>                    
                                <sjg:gridColumn name="userType" index="epicTleUserProfile.description" title="User Profile"  align="left" width="20"  sortable="true"/>
                                <sjg:gridColumn name="datetime" index="createdate"    title="Date/Time"        align="left" width="30"   sortable="true"/>
                                <sjg:gridColumn name="userName"                     title="Confirm"        align="center" formatter="confirmformatter"width="10"   sortable="false"/>
                                <sjg:gridColumn name="userName"                     title="Delete"         align="center" formatter="deleteformatter"  width="10"  sortable="false"/>
                            </sjg:grid> 
                        </div> 
                    </div>
                </div>                                       

            </div>





            <jsp:include page="../../footer.jsp" />
        </div><!--End of Wrapper-->
    </body>
</html>

