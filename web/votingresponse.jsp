<%@page contentType="text/html" pageEncoding="UTF-8" import="evoting.dto.CandidateInfo"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link href="stylesheet/showcandidate.css" type="text/css" rel="stylesheet">
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <title>Voting Details</title>
    </head>
    <body>
        <%
        String userid=(String)session.getAttribute("userid");
        if(userid==null)
        {
          session.invalidate();
          response.sendRedirect("accessdenied.html");
         return;
        }
        CandidateInfo c=(CandidateInfo)session.getAttribute("candidate");
        StringBuffer displayBlock=new StringBuffer();
        displayBlock.append("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>");
        if(c==null)
        {
            displayBlock.append("<div class='subcandidate'>Sorry! Your vote could not be casted</div><br><br>");
            displayBlock.append("<div class='logout'><h4 id='logout'><a href='logout.jsp'>Logout</a></h4></div>");
            out.println(displayBlock);
        }
        else
        {
            displayBlock.append("<div class='logout'><h4 id='logout'><a href='logout.jsp'>Logout</a></h4></div>");
            displayBlock.append("<div class='subcandidate'>Thank you for voting!</div><br><br>");
            displayBlock.append("<br><div class='candidateprofile'>Your vote added successfully!</div>");
            displayBlock.append("<div class='candidateprofile'>");
            displayBlock.append("<strong>You voted for</strong><br><img src='data:image/jpg;base64,"+c.getSymbol()+"' style='width:300px;height:200px;'/>");
            displayBlock.append("<br><div class='candidateprofile'><p>Candidate Id:"+c.getCandidateId()+"<br>");
            displayBlock.append("Canddidate Name:"+c.getCandidateName()+"<br>");
            displayBlock.append("Party:"+c.getParty()+"<br></div>");
            out.println(displayBlock);
        }
        %>
    </body>
</html>
