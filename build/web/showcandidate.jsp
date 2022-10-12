
<%@page contentType="text/html" pageEncoding="UTF-8" import="java.util.*,evoting.dto.CandidateInfo"%>
<html>
    <head>
        
        <script src="jsscript/vote.js"></script>
        <script src="jsscript/jquery.js"></script>
        <link href="stylesheet/showcandidate.css" type="text/css" rel="stylesheet">
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <title>Show candidate</title>
    </head>
    <body>
        <%
           String userid=(String)session.getAttribute("userid");
            if(userid==null)
            {
                response.sendRedirect("accesdenied.html");
                return;
            }
            StringBuffer displayBlock= new StringBuffer("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"
                    + "<div class='subcandidate'>Cast Vote</div><br><br>");
            displayBlock.append("<div class='logout'><a href='login.html'>logout</a></div></div></div><div class='buttons'>");
            ArrayList<CandidateInfo> candidate=(ArrayList)request.getAttribute("candidateList");
            for(CandidateInfo c: candidate)
            {
                displayBlock.append("<input id='"+c.getCandidateId()+"' value='"+c.getCandidateId()+"' name='flat' type='radio' onclick='addvote()'/>");
                displayBlock.append("<label for='"+c.getCandidateId()+"'><img src='data:image/jpg;base64,"+c.getSymbol()+"' style='width:300px;height:200px;'/></label>"
                        + "<br/><div class='candidateprofile'><p>Candidate Id:"+c.getCandidateId()+"<br/>"
                                                               +"Candidate Name:"+c.getCandidateName()+"<br/>"
                                                               +"Party:"+c.getParty()+"</label><br></div>");
            }
            out.println(displayBlock);
        %>
    </div>
    </div>
    </body>
</html>