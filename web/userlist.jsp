
<%@page import="evoting.dto.UserDetails"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String userid=(String)session.getAttribute("userid");
    if(userid==null)
    {
    response.sendRedirect("accessdenied.html");
    return;
    }
    StringBuffer displayBlock=new StringBuffer("");
    displayBlock.append("<table border='1'><tr><th>User Id</th><th>Username</th><th>Address</th><th>City</th><th>Email</th><th>Mobile No.</th>");
    ArrayList<UserDetails> userlist=(ArrayList<UserDetails>)request.getAttribute("data"); 
    for(UserDetails u:userlist)
    {
      displayBlock.append("<tr><td>"+u.getUserid()+"</td><td>"+u.getUsername()+"</td><td>"+u.getAddress()+"</td><td>"+u.getCity()+"</td><td>"+u.getEmail()+"</td><td>"+u.getMobile()+"</td></tr>");
    }
    displayBlock.append("</table>");
    out.println(displayBlock);
%>

