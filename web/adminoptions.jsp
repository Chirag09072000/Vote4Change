<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="jsscript/adminoptions.js"></script>
        <script src="jsscript/jquery.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <link href="stylesheet/backgroundimage.css" rel="stylesheet">
        <link href="stylesheet/pageheader.css" rel="stylesheet">
        <link href="stylesheet/admin.css" rel="stylesheet">
        <link href="stylesheet/result.css" rel="stylesheet">
        <title>Admin Options</title>
    </head>
    <body>
     <%
        String userid=(String)session.getAttribute("userid");
        if(userid==null)
        {
            response.sendRedirect("accessdenied.html");
            return;
        }
        out.println("<div class='sticky'><div class='candidate'>VOTE FOR CHANGE</div><br>"
                + "<div class='subcandidate'>Admin Actions Page</div><br><br>"
                +"<div class='logout'><a href='login.html'>logout</a></div></div>"
             +"<div class='container'>"
             +"<div id='dv1' onclick='redirectadminstratorpage()'>"
                     + "<img src='images/administrator.png' height='250px' width='250px'><br>"
                     + "<h3>Admin Options</h3></div>"
             +"<div id='dv2' onclick='redirectvotingpage()'>"
                     +"<img src='images/voteadmin.jpg' height='250px' width='250px'><br>"
                     + "<h3>Voting Page</h3></div>"
                     +"</div>"
                     +"<br><br><div align='center' id='result'></div>"
                + "</div>");
            
    %>
    </body>
</html>
