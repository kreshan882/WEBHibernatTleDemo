<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"  %>  
<%@taglib  uri="/struts-jquery-tags" prefix="sj"%>
<%@taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<html>
    <head>
        <title>Epic TLE</title>
        <!-- System theme includes begin -->
        <sj:head locale="en" jqueryui="true" jquerytheme="mytheme" customBasepath="resources/template/themes"/>
        <link href="${pageContext.request.contextPath}/resources/theme/darknight/css/app.css" type="text/css" rel="stylesheet" media="all" />
        <link href="${pageContext.request.contextPath}/resources/theme/darknight/assets/fonts/fontawesome/css/font-awesome.min.css" type="text/css" rel="stylesheet" media="all" />
        <!-- End -->

        <script type="text/javascript" >
            var _PATH = '${pageContext.request.contextPath}';
            
            window.history.forward();

            function noBack() {
                window.history.forward();
            }

            function clearCookieOnHome() {
                $.cookie("selectedpage", null, {path: "/"});
                $.cookie("selectedsec", null, {path: "/"});
            }
            function clearCookie() {
                updateFavLinks();
                clearCookieOnHome();
                $.cookie("user", null, {path: "/"});
            }

            $(document).ready(function () {
                
                function CheckSession() {
                    var session = '<%=request.getSession().getAttribute("modulePageList") != null%>';
                    alert(session);
//                    if (session == false) {
//                        alert("Your Session has expired");
//                        window.location = "login.aspx";
//                    }
//                    else {
//                        alert(session);
//                    }
                    return session;
                }


                function echoStatus() {
//                    alert("Hello");
                    if (CheckSession()) {
                        $.get('${pageContext.request.contextPath}/EchooprMng');
                        setTimeout(echoStatus, 10000);
                    }
                }
//                setTimeout(echoStatus, 10000);
//                echoStatus();

                var idpage = $.cookie("selectedpage");
                var idpageval = "#" + idpage;
                console.log("--------idpageval--------" + idpageval)
                var idsec = $.cookie("selectedsec");
                var idsecval = "#" + idsec;
                console.log("--------idsecval--------" + idsecval)
//                $(idpageval).addClass('activex');

                var stdiv = "<div id='pin'></div>"
                $(stdiv).insertBefore(idpageval);

                $(idsecval).slideDown();
                $("#accordian h3").removeClass("mystyle");
                $("#accordian h3").click(function () {
                    $("ul li").removeClass("mystyle");
                    $(this).addClass("mystyle");


                    if (!$(this).next().is(":visible")) {
                        $("#accordian h3").next().slideUp();
                        $(this).next().slideDown();
                        $.cookie("selectedsec", $(this).next().attr("id"), {path: "/", expires: 1});
                    } else {
                        $(this).next().slideUp();
                    }
                })
            });


            $(function () {
                $('.sub-nav li').click(function () {
                    $.cookie("selectedpage", $(this).find('a').attr("id"), {path: "/", expires: 1});
                });
                
                var _ele = "#"+$.cookie("selectedpage");
                $(_ele).addClass('menuselect'); 
                $(_ele).parent().parent().slideDown('fast', function() {
                    $(this).prev('a').addClass('expand');
                });
            });

            
        </script>
    </head>
</html>

