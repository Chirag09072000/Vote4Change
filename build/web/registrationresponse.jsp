<%-- 
    Document   : registrationresponse
    Created on : May 2, 2021, 7:56:51 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
 boolean result=(boolean)request.getAttribute("result");
 boolean userfound=(boolean)request.getAttribute("userfound");
 if(userfound==true)
    out.println("User already present");
 else if(result==true)
    out.println("success");
 else
    out.println("error");
%>