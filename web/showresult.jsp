<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import='evoting.dto.CandidateDetails' %>
<%@page import='java.util.*'%>
<%
  LinkedHashMap<CandidateDetails,Integer>result=(LinkedHashMap)request.getAttribute("result");
  double vote=(int)request.getAttribute("vote");
   Set s=result.entrySet();
   StringBuffer sb=new StringBuffer();
               CandidateDetails cd=null;
               Iterator it=s.iterator();
               sb.append("<table>");
   sb.append("<tr><th>candidate id</th>");
   sb.append("<<th>user id</th>");
   sb.append("<th>Candidate name</th>");
   sb.append("<th>city</th>");
   sb.append("<th>party</th>");
   sb.append("<th>image</th>");
   sb.append("<th>Voting Count</th>");
   sb.append("<th>vote percentage</th></tr>");
               while(it.hasNext()){
                 Map.Entry<CandidateDetails,Integer>e=(Map.Entry)it.next();
                 cd=e.getKey();
                 double voteCount=e.getValue();
                 String name=cd.getCandidateName();
                 String city=cd.getCity();
                 String userid=cd.getUserid();
                String party=cd.getParty();
                String cid=cd.getCandidateId();
              double votePer=voteCount/vote*100;
               
            String image="<img src='data:image/jpg;base64,"+cd.getSymbol()+"'style='width:300px;height:200px;'/>";
    sb.append("<tr><td>"+cid+"</td>");
    sb.append("<td>"+userid+"</td>");
   sb.append("<td>"+name+"</td>");
   sb.append("<td>"+city+"</td>");
   sb.append("<td>"+party+"</td>");
   sb.append("<td>"+image+"</td>");
   sb.append("<td>"+(int)voteCount+"</td>");
   sb.append("<td>"+votePer+"</td></tr>");
               }
  sb.append("</table>");
  out.println(sb);
    %>
  