
package evoting.controller;

import evoting.dao.CandidateDAO;
import evoting.dao.VoteDAO;
import evoting.dto.AddCandidateDTO;
import evoting.dto.CandidateDetails;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;


public class AddNewCandidateControllerServlet extends HttpServlet {

    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd=null;
        try
        {
            DiskFileItemFactory df= new DiskFileItemFactory();
            ServletFileUpload sfu=new ServletFileUpload(df);
            ServletRequestContext srq=new ServletRequestContext(request);
            List <FileItem> multiList=sfu.parseRequest(srq);
            ArrayList<String> objValues=new ArrayList<>();
            InputStream inp=null;
            String city="",party="";
            for(FileItem fit:multiList)
            {
                if(fit.isFormField())
                {
                    String fname=fit.getFieldName();
                    String value=fit.getString();
                    /*System.out.println("Inside If");
                    System.out.println(fname+":"+value);*/
                    objValues.add(value);
                    if(fname.equalsIgnoreCase("uid"))
                    {
                        ArrayList<String> usid=CandidateDAO.getUserId();
                        for(String userId:usid)
                        {
                            if(value.equalsIgnoreCase(userId))
                            {
                                rd=request.getRequestDispatcher("candidatepresent.jsp");
                                return;
                            }
                        }
                    }
                    if(fname.equalsIgnoreCase("city"))
                    {
                        city=value;
                    }
                    else if(fname.equalsIgnoreCase("party"))
                    {
                        party=value;
                    }
                }
                else
                {
                    inp=fit.getInputStream();
                    String key=fit.getFieldName();
                    String fileName=fit.getName();
                    /*System.out.println("Inside else");
                    System.out.println(key+":"+fileName);*/
                }
            }
             Map <String,String> uniqueparty=CandidateDAO.getParty();
             Set s=uniqueparty.entrySet();
             Iterator it=s.iterator();
              LinkedHashMap<String,String> uparty=new LinkedHashMap();
             while(it.hasNext())
             {
                  Map.Entry<String,String> e=(Map.Entry)it.next();
                  uparty.put(e.getKey(),e.getValue());
                 if((city.equalsIgnoreCase(e.getKey())) && (party.equalsIgnoreCase(e.getValue())))
                 {
                     request.setAttribute("result",uparty);
                     rd=request.getRequestDispatcher("cannotelect.jsp");
                     return;
                 }
             }
            AddCandidateDTO candidate=new AddCandidateDTO(objValues.get(0),objValues.get(3),objValues.get(4),objValues.get(1),inp);
            System.out.println(candidate.toString());
            boolean result=CandidateDAO.addCandidate(candidate);
            if(result)
                rd=request.getRequestDispatcher("success.jsp");
            else
                rd=request.getRequestDispatcher("failure.jsp");
        }
        catch(Exception ex)
        {
            System.out.println("Exception in AddNewCandidateController");
            ex.printStackTrace();
        }
        finally
        {
            if(rd!=null)
                rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
