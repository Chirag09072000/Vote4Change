<%@page import="java.util.Map"%>
<%@page contentType="text/html" pageEncoding="UTF-8" import="evoting.dto.Party,java.util.LinkedHashMap,java.util.Iterator"%>
<
<%
 String userid=(String)session.getAttribute("userid");
 if(userid==null)
 {
     session.invalidate();
     response.sendRedirect("accessdenied.html");
     return;
 }
 LinkedHashMap <String,String> resultDetails=(LinkedHashMap)request.getAttribute("result");
Iterator it=resultDetails.entrySet().iterator();
StringBuffer displayBlock=new StringBuffer("");
while(it.hasNext())
{
    Map.Entry<String,String> e=(Map.Entry)it.next();
    String party=e.getValue();
    String city=e.getKey();
    displayBlock.append("Sorry you cannot elect with"+party+" from "+city);
}
out.println(displayBlock);

%>