<%-- 
    Document   : header
    Created on : Aug 12, 2014, 6:09:22 PM
    Author     : kreshan
--%>

<%@page import="com.epic.tle.util.SectionComparator"%>
<%@page import="com.epic.tle.util.ModuleComparator"%>
<%@page import="java.util.TreeSet"%>
<%@page import="com.epic.tle.login.bean.PageBean"%>
<%@page import="com.epic.tle.login.bean.ModuleBean"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!--Banner-->
<div class="banner">
    <!-- App header begin -->
    <header>
        <a href='homeCall.action' class="brand"></a>
        <div class="service-status">
            <div id="node"></div><div id="serviceStatus" class="active"></div>
        </div>
        <div class="top-nav">
            <div class="right-links">
                <a href='logOut.action' id="logout"  class="lnk-logout" title="Log Out"></a>
                <a href='homeCall.action' id="home" class="lnk-home" title="Home"></a>
            </div>
            <div class="user-info">
                <h2></h2>
                <h1>Welcome</h1>
                <div>
                    <a href="#">${SessionObject.userName}</a><br />
                    <span>${SessionObject.userRole}</span>
                </div>
            </div>
        </div>
    </header>
    <!-- End -->

</div>
<!--End of Banner-->

<!-- Left side navigation begin -->
<div class="left-nav">
    <ul class="navigation">

        <%
            try {
                System.out.println("server request " + request.getSession());
                HashMap<ModuleBean, List<PageBean>> sectionPageList = (HashMap<ModuleBean, List<PageBean>>) request.getSession().getAttribute("modulePageList");

                ModuleComparator sec1 = new ModuleComparator();
                Set<ModuleBean> section = new TreeSet<ModuleBean>(sec1);
                Set<ModuleBean> section1 = sectionPageList.keySet();
                for (ModuleBean sec2 : section1) {
                    section.add(sec2);
                }

                int secId = 1;
                int pageId = 1000;

                for (ModuleBean sec : section) {
                    out.println("<li class='parentnav'><a href='#' class='dropmenu do-nothing'><i class='fa fa-chevron-right' aria-hidden='true'></i>" + sec.getMODULE_NAME() + "</a>");
                    out.println("<ul id=\"" + secId + "\" class='sub-nav'>");

                    List<PageBean> pageList = sectionPageList.get(sec);
                    SectionComparator sectionCm = new SectionComparator();
                    Set<PageBean> sortPageList = new TreeSet<PageBean>(sectionCm);
                    for (PageBean sec2 : pageList) {
                        sortPageList.add(sec2);
                    }
                    for (PageBean pageBean : sortPageList) {
                        pageId++;
                        out.println("<li>");
                        out.println("<a id= " + pageId + " href=\'" + request.getContextPath() + "/" + pageBean.getPAGE_URL() + ".action\'>");
                        out.println("<i class='fa fa-angle-right' aria-hidden='true'></i>" + pageBean.getPAGE_NAME());
                        out.println("</a>");
                        out.println("</li>");
                    }

                    out.println(" </ul>");
                    secId++;
                }
                out.println("</a></li>");
            } catch (Exception ee) {
                ee.printStackTrace();
            }
        %>

    </ul>
</div>
<!-- End -->
<script>
    $(function () {
        var SessionObject = '<s:property value="#session.SessionObject"/>';
        var i = ${pageContext.session.maxInactiveInterval};
        var session_time_out = i * 1000;
     
        setInterval(function () {
            
            sesseionTimeOut();
        }, session_time_out);
        function sesseionTimeOut() {
            $.ajax({
                url: '${pageContext.request.contextPath}/removeSessionAttribute',
                dataType: "json",
                type: "POST",
                success: function (data) {
                    window.location = "${pageContext.request.contextPath}/logOut.action";
                },
                error: function (errorThrown) {
                    window.location = "${pageContext.request.contextPath}/logOut.action";
                }
            });
        }

        timeoutChart();


        $('#logout').click(function () {
            $.removeCookie('selectedpage', {path: '/'});
            window.location = $(this).attr('hrefa');
        });
        $('#home').click(function () {
            $.removeCookie('selectedpage', {path: '/'});
            window.location = $(this).attr('href');
        });

//        logout(){
//            var cookies = $.cookie();
//            for (var cookie in cookies) {
//                $.removeCookie(cookie);
//            }
//        }

        var idpage = $.cookie("selectedpage");
        console.log(" cookie----- " + idpage)
        var idpageval = "#" + idpage;
        $(idpageval).addClass('menuselect');
    })
    function timeoutChart() {
        if ("${SessionObject.userName}" != "") {
            $('#graph').empty();
            chart();
            setTimeout(timeoutChart, 10000);
        }
    }
    function chart() {

        var lineChart = "";
        var data = [];
        $.get('${pageContext.request.contextPath}/chartDatamonitorCall', function (callback) {
            $("#serviceStatus").removeClass("active");
            $("#serviceStatus").removeClass("inactive");
            $("#serviceStatus").html("")
            $("#serviceStatus").text("")
            if (callback.serviceStatus === "true") {
                $("#node").html("NODE " + callback.serverNode);
                $("#serviceStatus").addClass("active");
                $("#serviceStatus").html(" ONLINE");
                $("#serviceStatus").text(" ONLINE");

            } else {
                $("#node").html("NODE " + callback.serverNode);
                $("#serviceStatus").addClass("inactive");
                $("#serviceStatus").html(" OFFLINE");
                $("#serviceStatus").text(" OFFLINE");
            }

//            if (callback.chartMap != null || callback.chartMap != "") {
//                $.each(callback.chartMap, function (key, value) {
//                    var arrval = {datetime: value.datetime, totalTime: value.totalTime};
//                    data.push(arrval);
//                });
//            } else {
//                var arrval = {datetime: new Date(), totalTime: 0};
//                data.push(arrval);
//            }
            if (callback.chartMap.length != 0) {
                $.each(callback.chartMap, function (key, value) {
                    var arrval = {datetime: value.datetime, totalTime: value.totalTime};
                    data.push(arrval);
                });
            } else {
                var datas = {datetime: new Date().toDateString(), totalTime: 0}
                data.push(datas);
            }

            //console.log(data)
            //                var config = {
            //                    data: data,
            //                    element: 'graph',
            //                    xkey: 'datetime',
            //                    ykeys: ['hostTime', 'tleTime', 'totalTime'],
            //                    labels: ['Host Time', 'TLE Time', 'Total Time'],
            //                    fillOpacity: 0.6,
            //                    hideHover: 'auto',
            //                    behaveLikeLine: true,
            //                    resize: true,
            //                    pointFillColors: ['#ffffff'],
            //                    pointStrokeColors: ['black'],
            //                    lineColors: ['#7c7676', '#aa0808', '#091382']
            //                };
            var config = {
                data: data,
                element: 'graph',
                xkey: 'datetime',
                ykeys: ['totalTime'],
                labels: ['TLE Time'],
                fillOpacity: 0.6,
                hideHover: 'auto',
                behaveLikeLine: true,
                resize: true,
                pointFillColors: ['#ffffff'],
                pointStrokeColors: ['black'],
                lineColors: ['#32a9c5']
            };

            lineChart = new Morris.Line(config);

        });
        return lineChart;

    }
</script>
<!--End of Left Menu-->


