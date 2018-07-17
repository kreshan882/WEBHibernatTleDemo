<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<!DOCTYPE html>
<script>
    var stoken = "<%=session.getAttribute("SessionToken")%>";
    var binVal = '<s:property value="binvalue" />';
</script>
<s:if test="hasActionMessages()">
    <script type="text/javascript">resetForm();
       <s:iterator value="actionMessages">
        if (binVal.length!=0 && binVal != null && binVal == 2) {
            
            utilityManager.showMessage('.add-form-msg1', '<s:property escapeJavaScript="true" />', 'successmsg', stoken);
        } else {
            utilityManager.showMessage('.add-form-msg', '<s:property escapeJavaScript="true" />', 'successmsg', stoken);
        }
        </s:iterator>
    </script>
    <s:actionmessage theme="jquery"/>
</s:if>
<s:if test="hasActionErrors()">
    <script type="text/javascript">resetForm();
        <s:iterator value="actionErrors" var="actioError">
            <s:if test="#actioError=='csrfError'">
        window.location.replace("${pageContext.request.contextPath}/pages/csrfError.jsp");
            </s:if>
            <s:else>
        if (binVal.length!=0 && binVal != null && binVal == 2) {
            resetForm();
            utilityManager.showMessage('.add-form-msg1', '<s:property escapeJavaScript="true" />', 'errormsg', stoken);
        } else {
            utilityManager.showMessage('.add-form-msg', '<s:property escapeJavaScript="true" />', 'errormsg', stoken);
        }

            </s:else>
        </s:iterator>
    </script>
    <s:actionerror theme="jquery"/>
</s:if>

