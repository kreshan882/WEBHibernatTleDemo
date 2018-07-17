<%-- 
    Document   : systemHistoryPopup
    Created on : Jan 9, 2018, 10:50:19 AM
    Author     : chandana_l
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib uri="/struts-tags" prefix="s"  %>  
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <style>
        .displayTableFrame {
        overflow-y: scroll;
        height: 85%;
        width: 50%;
        display: block;
        float: left;
        } 
        .displayTableFrame2 {
        overflow-y: scroll;
        height: 85%;
        width: 50%;
        display: block;
        } 
        
        .sample {
           color: white;
           width: 100%;
        }
        th {
            padding-top: 10px;
            padding-left:10px;           
            text-align: left;
            height: 30px;
            background-color: #7e7e7e;
            width: auto;          
        }
        tr{
           margin-left: 10px;
           height: 25px; 
        }
        td{
           padding-top: 5px;
           padding-left:10px; 
        }
        
        .changed{
            background-color: #2e7db2;
            font-weight: bold;
            /*color: yellow;*/
        }
    </style>
    <body>
       <section class="app-content popup-window">
        <div class="content innerpage">
            <div class="content-data">
                <h2 class="section-title">Detailed History Records</h2>
            </div>
                <div class="content-section data-form">
                <div class="displayTableFrame" style="margin-top: 10px; padding-top: 10px;">  
                    <table class="sample" sortable="true">
                        <div class="nonScrole">
                        <thead>
                            <tr><th>Column Name</th>
                            <th>Old Value</th>
                        </thead>
                        </div>
                        <div class="tableBody">
                        <tbody>
                            <s:iterator value="oldValueMap" status="">
                              <tr class="<s:property value="value[1]"/>">
                                 <td><s:property value="key"/></td>
                                 <td><s:property value="value[0]"/></td>
                              </tr>
                            </s:iterator>
                        </tbody>
                        </div>
                    </table>
                </div>
                <div class="displayTableFrame2" style="margin-top: 10px; padding-top: 10px;">            
                    <table class="sample">
                        <thead>
                            <tr><th>Column Name</th>
                                <th>New Value</th>
                            </tr>
                        </thead>
                        <tbody> 
                            <s:iterator value="newValueMap">
                              <tr class="<s:property value="value[1]"/>">
                                 <td><s:property value="key"/></td>
                                 <td><s:property value="value[0]"/></td>
                              </tr>
                            </s:iterator>
                        </tbody>
                    </table>   
                </div>
            </div>
        </div>
       </section>
    </body>
</html>
