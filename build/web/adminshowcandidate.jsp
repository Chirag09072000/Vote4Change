<%@page import="org.json.JSONObject"%>
<%@page import="evoting.dto.CandidateDetails"%>
<%@page import="java.util.ArrayList"%>
<%
String userid=(String)session.getAttribute("userid");
if(userid==null)
{
    response.sendRedirect("accessdenied.html");
    return;
}
String result=(String)request.getAttribute("result");
System.out.println(result);
StringBuffer displayBlock=new StringBuffer("");
if(result.equals("candidateList"))
{
    ArrayList<String> candidateId=(ArrayList)request.getAttribute("candidateId");
    displayBlock.append("<option value='Choose Id'>Choose Id</option>");
    for(String c:candidateId)
    {
        displayBlock.append("<option value='"+c+"'>"+c+"</option>");
    }
    JSONObject json=new JSONObject();
    json.put("cids",displayBlock.toString());
    out.println(json);
}
else if(result.equals("details"))
{
    CandidateDetails cd=(CandidateDetails)request.getAttribute("candidate");
    String str="<img src='data:image/jpg;base64,"+cd.getSymbol()+"'style='width:300px;height:200px;'/>";
    displayBlock.append("<table>"
            + "<tr><th>User Id:</th><td>"+cd.getUserid()+"</td></tr>"
            + "<tr><th>Candidate Name:</th><td>"+cd.getCandidateName()+"</td></tr>"
            + "<tr><th>City:</th><td>"+cd.getCity()+"</td></tr>"
            + "<tr><th>Party:</th><td>"+cd.getParty()+"</td></tr>"
            + "<tr><th>Symbol:</th><td id='image'>"+str+"</td></tr>"
                    + "</table>");
          System.out.println(displayBlock);
          JSONObject json=new JSONObject();
          json.put("subdetails",displayBlock.toString());
          out.println(json);
}
System.out.println("In admin show candidate");
System.out.println(displayBlock);
%>


