<%@page import="evoting.dto.CandidateDetails"%>
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
    if(result!=null && result.equalsIgnoreCase("candidateIdList"))
    {
        ArrayList<String> candidateId=(ArrayList<String>)request.getAttribute("candidateId");
        displayBlock.append("<option value=' '>Choose Id</option>");
        for(String s:candidateId)
        {
            displayBlock.append("<option value='"+s+"'>"+s+"</option>");
        }
        JSONObject json=new JSONObject();
        json.put("cid", displayBlock.toString());
        out.println(json);
        //System.out.println("In adminremovecandidate");
        //System.out.println(displayBlock);
    }
    else if(result!=null && result.equals("details"))
    {
        CandidateDetails candidatedetails=(CandidateDetails)request.getAttribute("candidate");
        String str="<img src='data:image/jpg;base64,"+candidatedetails.getSymbol()+"'style='width:150px;height:150px;'/>";
        displayBlock.append("<table>");
        displayBlock.append("<tr><th>UserId:</th><td>"+candidatedetails.getUserid()+"</td></tr>");
        displayBlock.append("<tr><th>Candidate Name:</th><td>"+candidatedetails.getCandidateName()+"</td></tr>");
        displayBlock.append("<tr><th>City:</th><td>"+candidatedetails.getCity()+"</td></tr>");
        displayBlock.append("<tr><th>Party:</th><td>"+candidatedetails.getParty()+"</td></tr>");
        displayBlock.append("<tr><th>Symbol:</th><td>"+str+"</td></tr>");
        displayBlock.append("<tr><td colspan='2' style='text-align:center'><input type='button' value='Confirm' onclick='deletecandidate()'></td><tr>");
        displayBlock.append("</table>");
        JSONObject json=new JSONObject();
        json.put("subdetails", displayBlock.toString());
        out.println(json);
        //System.out.println("In adminremovecandidate");
        //System.out.println(displayBlock);
    }
    
%>
