<%@page import="com.mycart.entites.User" %>
<%
    User user2 = (User)session.getAttribute("current-user"); 
    if(user2 == null){
    session.setAttribute("message","you are not logged in !! logged first");
    response.sendRedirect("login.jsp");
    return;
    }
    else{
    if(user2.getUserType().equals("admin")){
    session.setAttribute("message","Do not access this page");
    response.sendRedirect("login.jsp");
    }
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Normal : Panel</title>
        <%@include file="Components/Common_css_js.jsp" %>
    </head>
    <body>
        <%@include file="Components/navbar.jsp" %>
        <h1>this is normal user panel</h1>
        
        <%@include file="Components/common_modals.jsp" %>
    </body>
</html>
