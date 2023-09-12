
package com.mycart.Servlet;

import com.mycart.dao.UserDao;
import com.mycart.entites.User;
import com.mycart.helper.FactoryProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;



public class LoginServlet extends HttpServlet {

   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
            String email = request.getParameter("email");
            String password =request.getParameter("password");
            
            //validation
            
            //authenticating user
            UserDao userdao = new UserDao(FactoryProvider.getFactory());
            User u = userdao.getUserByEmailAndPassword(email, password);
            System.out.println(u);
            
            HttpSession session =request.getSession();
                    
            if(u==null){
                session.setAttribute("message","Invalid Details !! try with another one");
                response.sendRedirect("login.jsp");
                return;
            }else{
                out.println("<h1> Welcome "+u.getUserName() + " </h1>");
                session.setAttribute("current-user", u);
                
                if(u.getUserType().equals("admin")){
//                    admin : admin.jsp
                    response.sendRedirect("admin.jsp");
                }else if(u.getUserType().equals("normal")){
//                    normal : normal.jsp
                    response.sendRedirect("normal.jsp");
                }else{
                    out.println("We have not identified user type");
                }
            }
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
