/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.controller;

import evoting.dao.CandidateDAO;
import evoting.dto.CandidateDetails;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author lenovo
 */
public class RemoveCandidateServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd=null;
        HttpSession sess=request.getSession();
        String userid=(String)sess.getAttribute("userid");
        if(userid==null)
        {
            sess.invalidate();
            response.sendRedirect("accessdenied.html");
            return;
        }
        String data=request.getParameter("data");
        try
        {
          if(data!=null && data.equals("cid"))
          {
                ArrayList<String> candidateIdList=CandidateDAO.getCandidateId();
                //System.out.println(userIdList);
                request.setAttribute("candidateId", candidateIdList);
                request.setAttribute("result", "candidateIdList");
          }         
          else
          {
               CandidateDetails candidatedetails=CandidateDAO.getDetailsById(data);
               request.setAttribute("candidate", candidatedetails);
               request.setAttribute("result", "details");
          }
          rd=request.getRequestDispatcher("removecandidate.jsp"); 
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            rd=request.getRequestDispatcher("showexception.jsp");
            request.setAttribute("Exception", ex);
        }
        finally
        {
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
