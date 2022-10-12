
<%@page import="evoting.dto.UserDetails"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.json.JSONObject,java.util.ArrayList" %>
<%
    String userid=(String)session.getAttribute("userid");
    if(userid==null)
    {
        response.sendRedirect("accessdenied.html");
        return;
    }
    String result=(String)request.getAttribute("result");
    StringBuffer displayBlock=new StringBuffer();
    if(result!=null && result.equalsIgnoreCase("userIdList"))
    {
        ArrayList<String> userId=(ArrayList<String>)request.getAttribute("userId");
        displayBlock.append("<option value=' '>Choose Id</option>");
        for(String s:userId)
        {
            displayBlock.append("<option value='"+s+"'>"+s+"</option>");
        }
        JSONObject json=new JSONObject();
        json.put("uid", displayBlock.toString());
        out.println(json);
        //System.out.println("In adminremoveuser");
        //System.out.println(displayBlock);
    }
    else if(result!=null && result.equals("details"))
    {
        UserDetails userdet=(UserDetails)request.getAttribute("user");
        displayBlock.append("<table>");
        displayBlock.append("<tr><th>Username:</th><td>"+userdet.getUsername()+"</td></tr>");
        displayBlock.append("<tr><th>Email:</th><td>"+userdet.getEmail()+"</td></tr>");
        displayBlock.append("<tr><th>Mobile No.:</th><td>"+userdet.getMobile()+"</td></tr>");
        displayBlock.append("<tr><th>Address:</th><td>"+userdet.getAddress()+"</td></tr>");
        displayBlock.append("<tr><th>City:</th><td>"+userdet.getCity()+"</td></tr>");
        displayBlock.append("<tr><td colspan='2' style='text-align:center'><input type='button' value='Confirm' onclick='deleteuser()'></td><tr>");
        displayBlock.append("</table>");
        JSONObject json=new JSONObject();
        json.put("subdetails", displayBlock.toString());
        out.println(json);
        //System.out.println("In adminremoveuser");
        //System.out.println(displayBlock);
    }
    
%>

