<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <head>
        <jsp:include page="../../Styles.jsp" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/morris.css">
    </head>
    <body>
        <section class="app-content">
            <jsp:include page="../../header.jsp" /> 

            <div class="content dashboard">
                <h1 class="page-title">System Dashboard</h1>

                <!-- Dashboard widget begin -->
                <div class="d-col">
                    <h2 class="d-col-title"><i class="fa fa-bolt" aria-hidden="true"></i> Transaction Monitor</h2>
                    <div class="d-col-widget">
                        <a class="maxmin do-nothing gridtable" href="#" title="Maximize">
                            <i class="fa fa-expand" aria-hidden="true"></i>
                        </a>

                        <div id="tablediv1" class="custom-grid">

                            <s:url var="listurl" action="ListTranssysAlert"/>
                            <sjg:grid
                                id="gridtable"
                                width="575"
                                caption="Transaction Monitor"
                                dataType="json"
                                href="%{listurl}"
                                pager="true"
                                gridModel="gridModel"
                                rowList="10,15,20"
                                rowNum="10"
                                autowidth="true"
                                rownumbers="false"
                                rowTotal="false"
                                viewrecords="true"
                                >

                                <sjg:gridColumn name="risklevl" index="epicTleRiskLevel.code" title="Risk Level" align="center" width="100" formatter="Riskformatter" sortable="true"/>  
                                <sjg:gridColumn name="node" index="epicTleNodetype.description" title="Node" align="LEFT" width="100" sortable="true"/>  
                                <sjg:gridColumn name="alerts" index="alertinformation" title="Alerts" align="LEFT" width="300" sortable="true"/>  
                                <sjg:gridColumn name="datetime" index="datetime" title="Date/Time" align="left" width="150" sortable="true"/>         

                            </sjg:grid> 

                        </div>
                    </div>


                </div>
                <!-- End -->

                <div class="d-col">
                    <h2 class="d-col-title"><i class="fa fa-bell-o" aria-hidden="true"></i> System Monitor</h2>
                    <div class="d-col-widget">
                        <a class="maxmin do-nothing gridtable2" href="#" title="Maximize"><i class="fa fa-expand" aria-hidden="true"></i></a>
                        <div id="tablediv2" class="custom-grid">
                            <s:url var="Loadingurl" action="LoadingsysAlert"/>
                            <sjg:grid
                                id="gridtable2"
                                width="575"
                                caption="System Monitor"
                                dataType="json"
                                href="%{Loadingurl}"
                                pager="true"
                                gridModel="gridModel"
                                rowList="10,15,20"
                                rowNum="10"
                                autowidth="true"
                                rownumbers="false"
                                rowTotal="false"
                                viewrecords="true"
                                >

                                <sjg:gridColumn name="risklevl" index="epicTleRiskLevel.code" title="Risk Level" align="center" width="100" formatter="Riskformatter" sortable="true"/>  
                                <sjg:gridColumn name="node" index="epicTleNodetype.description" title="Node" align="LEFT" width="100" sortable="true"/>  
                                <sjg:gridColumn name="alerts" index="alertinformation" title="Alerts" align="LEFT" width="300" sortable="true"/>  
                                <sjg:gridColumn name="datetime" index="datetime" title="Date/Time" align="left" width="150" sortable="true"/>         

                            </sjg:grid> 
                        </div>
                    </div>
                </div>



                <div class="d-col">
                    <h2 class="d-col-title"><i class="fa fa-cog" aria-hidden="true"></i> operation Monitor</h2>
                    <div class="d-col-widget">
                        <a class="maxmin do-nothing gridtable3" href="#" title="Maximize"><i class="fa fa-expand" aria-hidden="true"></i></a>

                        <div id="tablediv3" class="custom-grid">
                            <s:url var="listopr" action="ListoprMng"/>
                            <sjg:grid
                                id="gridtable3"
                                width="575"
                                caption="Operation Alerts"
                                dataType="json"
                                href="%{listopr}"
                                pager="true"
                                gridModel="gridModel"
                                rowList="10,15,20"
                                rowNum="10"
                                shrinkToFit="true"
                                autowidth="true"
                                rownumbers="true"
                                onCompleteTopics="completetopics"
                                rowTotal="false"
                                viewrecords="true"
                                >

                                <sjg:gridColumn name="colorCode" index="colorCode" title="id"   hidden="true" />  
                                <sjg:gridColumn title="Operaiton" name="operaiton" index="operationId"  align="left" sortable="true"/>  
                                <sjg:gridColumn name="node" index="epicTleNodetype.description" title="Node" align="LEFT" width="100" sortable="true"/>  
                                <sjg:gridColumn name="username" index="username" title="Username" align="left" sortable="true"  /> 
                                <sjg:gridColumn name="ip" index="ip" title="IP" align="left" sortable="true"/> 
                                <sjg:gridColumn name="message" index="message" title="Message" align="left" sortable="true"/> 
                                <sjg:gridColumn name="statusStr" index="epicTleStatus.code" title="Status" align="center" sortable="true" /> 
                                <sjg:gridColumn name="dateTime" index="datetime" title="Date/Time" align="left" sortable="true"/> 
                            </sjg:grid> 
                        </div>
                    </div>
                </div>

                <div class="d-col">
                    <h2 class="d-col-title"><i class="fa fa-line-chart" aria-hidden="true"></i> system processing time 
                        <div class="d-col-options hide-element">
                            <a href="#" class="ddl-daterange do-nothing">Date range <i class="fa fa-angle-down" aria-hidden="true"></i></a>
                            <div class="rangepopup">
                                <div class="error-msg">Empty data</div>
                                <label>From: </label><input type="text" />
                                <label>To: </label><input type="text" />
                                <label>&nbsp;</label><button>Show</button>
                            </div>
                        </div>
                    </h2>
                    <div class="d-col-widget">
                        <a class="maxmin do-nothing" href="#" title="Maximize"><i class="fa fa-expand" aria-hidden="true"></i></a>

                        <div id="graph" class="graph-panel" style="width:100%; height:95%; float:left; margin-top:1%;"></div>

                    </div>
                </div>

            </div>
            <!-- End -->

        </section>
        <jsp:include page="../../footer.jsp" />
        <script src="https://cdnjs.cloudflare.com/ajax/libs/raphael/2.2.7/raphael.min.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/morris.js"></script>
        <script src="${pageContext.request.contextPath}/resources/js/GridRowCount.js"></script>
        <script>






            $(document).ready(function () {


                setTimeout(function () {
                    GridRowCount.JqGridRows(0, 'gridtable');
                    GridRowCount.JqGridRows(1, 'gridtable2');
                    GridRowCount.JqGridRows(2, 'gridtable3');
                }, 100);

                function refreshTable() {
                    if ("${SessionObject.userName}" != "") {
                        jQuery("#gridtable").trigger("reloadGrid");
                        jQuery("#gridtable2").trigger("reloadGrid");
                        jQuery("#gridtable3").trigger("reloadGrid");
                        setTimeout(refreshTable, 10000);
                    }
                }
                //                setTimeout(echoStatus, 10000);
                refreshTable();

                //var chart_record = chart().redraw();
                //
                // var chart_record = chart().redraw();

                /*$("#toChartDate").on('change', function () {
                 
                 var fromDate = $('#fromChartDate').val();
                 var toDate = $(this).val();
                 
                 var filterData = [];
                 
                 $.ajax({
                 url: '${pageContext.request.contextPath}/FiltertDatamonitorCall',
                 data: {FromDate: fromDate, ToDate: toDate},
                 dataType: "json",
                 type: "POST",
                 success: function (data) {
                 $('#graph_msg').empty();
                 if (data.chartMap.length == 0) {
                 $("#colorlegend").prop('style', 'margin-left: 60px;');
                 $('#graph_msg').append('<div class="ui-widget actionMessage">' +
                 '<div class="ui-state-error ui-corner-all" style="padding: 0.3em 0.7em; margin-top: 20px;">' +
                 '<p><span class="ui-icon ui-icon-info" style="float: left; margin-right: 0.3em;"></span>' +
                 '<span>Empty Data</span></p>' +
                 '</div>' +
                 '</div>');
                 } else {
                 $('#graph_msg').empty();
                 $("#colorlegend").prop('style', 'margin-left: 177px;')
                 $.each(data.chartMap, function (key, value) {
                 var arrval = {datetime: value.datetime, hostTime: value.hostTime, tleTime: value.tleTime, totalTime: value.totalTime};
                 filterData.push(arrval);
                 });
                 chart().setData(filterData);
                 chart().redraw();
                 }
                 
                 }
                 });
                 
                 });*/
//                window.m = chart();

            });



            /*$(window).on('resize', function() {
             $('#graph').empty();
             window.m = chart();
             });*/

            //Chart drawing functionality with resizing feature
            //$(function () {
            //Donut chart
            /*window.m = Morris.Donut({
             element: 'graph',
             resize: true,
             data: [
             {label: "Download Sales", value: 12},
             {label: "In-Store Sales", value: 30},
             {label: "Mail-Order Sales", value: 20}
             ]
             });
             $(window).on('resize', function() {
             if (!window.recentResize) {
             window.m.redraw();
             window.recentResize = true;
             setTimeout(function(){ window.recentResize = false; }, 200);
             }
             });*/

            //Area chart
            /*var data = [];
             var CHART;
             $.get('${pageContext.request.contextPath}/chartDatamonitorCall', function (callback) {
             $.each(callback.chartMap, function (key, value) {
             var arrval = {datetime: value.datetime, hostTime: value.hostTime, tleTime: value.tleTime, totalTime: value.totalTime};
             data.push(arrval);
             });
             
             var config = {
             data: data,
             element: 'graph',
             xkey: 'datetime',
             ykeys: ['hostTime', 'tleTime', 'totalTime'],
             labels: ['Host Time', 'TLE Time', 'Total Time'],
             fillOpacity: 0.6,
             hideHover: 'auto',
             behaveLikeLine: true,
             resize: true,
             pointFillColors: ['#ffffff'],
             pointStrokeColors: ['black'],
             lineColors: ['#7c7676', '#aa0808', '#091382']
             };
             
             _w = $('.d-col-widget').eq(2).width()-11;
             _h = $('.d-col-widget').eq(2).height()-11;
             $('.graph-panel').width(_w);
             $('.graph-panel').height(_h);
             
             CHART = Morris.Line(config);
             
             $(window).on('resize', function() {
             if (!window.recentResize) {
             _w = $('.d-col-widget').eq(2).width()-11;
             _h = $('.d-col-widget').eq(2).height()-11;
             $('.graph-panel').width(_w);
             $('.graph-panel').height(_h);
             CHART.redraw();
             window.recentResize = true;
             setTimeout(function(){ window.recentResize = false; }, 200);
             console.log('resized');
             }
             });
             //lineChart = new Morris.Line(config);
             
             });*/



            /*window.m = Morris.Line({
             element: 'graph',
             resize: true,
             data: [
             { y: '2006', a: 100, b: 90 },
             { y: '2007', a: 75,  b: 65 },
             { y: '2008', a: 50,  b: 40 },
             { y: '2009', a: 75,  b: 65 },
             { y: '2010', a: 50,  b: 40 },
             { y: '2011', a: 75,  b: 65 },
             { y: '2012', a: 100, b: 90 }
             ],
             xkey: 'y',
             ykeys: ['a', 'b'],
             labels: ['Series A', 'Series B']
             });
             
             $(window).on('resize', function() {
             if (!window.recentResize) {
             window.m.redraw();
             window.recentResize = true;
             setTimeout(function(){ window.recentResize = false; }, 200);
             }
             });*/
            //});

            function Riskformatter(cellvalue) {
                if (cellvalue == 1) {
                    return "<i class='fa fa-circle concern' aria-hidden='true'></i>";
                } else if (cellvalue == 2) {
                    return "<i class='fa fa-circle warning' aria-hidden='true'></i>";
                } else {
                    return "<i class='fa fa-circle critical' aria-hidden='true'></i>";
                }
            }

            function Statusformatter(cellvalue) {
                if (cellvalue == 1) {
                    return "<i class='fa fa-circle active' aria-hidden='true'></i>";
                } else {
                    return "<i class='fa fa-circle' aria-hidden='true'></i>";
                }
            }

            $.subscribe('completetopics', function (event, data) {

                $.each(jQuery(data).jqGrid('getRowData'), function (rowNo, gridData) {
                    $.each(jQuery(data).jqGrid('getRowData'), function (rowNo, gridData) {

                        if ((gridData.colorCode === '1')) {
                            $('#gridtable3').find('tr:eq(' + (rowNo + 1) + ')').children().first().css({'background-color': '#E50606', 'color': '#fff'});
                        } else if ((gridData.colorCode === '2')) {
                            $('#gridtable3').find('tr:eq(' + (rowNo + 1) + ')').children().first().css({'background-color': 'rgb(125, 218, 153)', 'color': '#fff'});
                        } else if ((gridData.colorCode === '3')) {
                            $('#gridtable3').find('tr:eq(' + (rowNo + 1) + ')').children().first().css({'background-color': '#ffa500', 'color': '#fff'});
                        } else if ((gridData.colorCode === '4')) {
                            $('#gridtable3').find('tr:eq(' + (rowNo + 1) + ')').children().first().css({'background-color': '#87CEFA', 'color': '#fff'});
                        } else {
                            $('#gridtable3').find('tr:eq(' + (rowNo + 1) + ')').css('color', '#FFA500');
                        }
                    });
                });

            });

        </script>
    </body>

</html>
