package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class adminaction_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <script src=\"jsscript/adminoptions.js\"></script>\n");
      out.write("        <script src=\"jsscript/jquery.js\"></script>\n");
      out.write("        <script src=\"https://unpkg.com/sweetalert/dist/sweetalert.min.js\"></script>\n");
      out.write("        <link href=\"stylesheet/backgroundimage.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"stylesheet/pageheader.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"stylesheet/admin.css\" rel=\"stylesheet\">\n");
      out.write("        <link href=\"stylesheet/result.css\" rel=\"stylesheet\">\n");
      out.write("        <title>Admin Actions Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body>\n");
      out.write("        ");

        /*String userid=(String)session.getAttribute("userid");
        if(userid==null)
        {
            response.sendRedirect("accessdenied.html");
            return;
        }*/
        out.println("<div class='sticky'>"
                + "<div class='candidate'>VOTE FOR CHANGE</div><br>"
                + "<div class='subcandidate'>Admin Actions Page</div><br><br>"
                +"<div class='logout'><a href='login.html'>logout</a></div></div>"
                + "<div class='container'>"
+ "<div id='dv1' onclick='manageuser()'><img src='images/muser.png' height='250px' width='250px'><br><h3>Manage User</h3></div>"
+ "<div id='dv2' onclick='managecandidate()'><img src='images/ManageCandLists.jpg' height='250px' width='250px'><br><h3>Manage Candidate</h3></div>"
+ "<div id='dv3' onclick='votingresult()'><img src='images/resultgraph.jpg' height='250px' width='250px'><br><h3>Show Result</h3></div>"
+  "<br><br><div align='center' id='result'></div>"
                + "</div>");
        
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
