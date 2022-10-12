<%-- 
    Document   : showexception
    Created on : May 2, 2021, 8:02:35 PM
    Author     : lenovo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
   Exception ex=(Exception)request.getAttribute("Exception");
   System.out.println("Exception is:"+ex);
   out.println("Some exception occurred:"+ ex.getMessage());
%>
