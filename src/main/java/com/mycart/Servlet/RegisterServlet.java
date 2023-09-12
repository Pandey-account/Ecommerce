package com.mycart.Servlet;

import com.mycart.dao.UserDao;
import com.mycart.entites.User;
import com.mycart.helper.FactoryProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.http.HttpSession;

public class RegisterServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            try {
                String name = request.getParameter("username");
                String email = request.getParameter("useremail");
                String password = request.getParameter("userpassword");
                String phone = request.getParameter("userphone");
                String address = request.getParameter("useraddress");

//                creating user object to store data
                User u = new User(name, email, password, phone, "default", address, "normal");

                UserDao ud = new UserDao(FactoryProvider.getFactory());
                boolean f = ud.saveUser(u);
                if (f == true) {
                    HttpSession httpsession = request.getSession();
                    httpsession.setAttribute("message", "Registration Successful !! ");
                    response.sendRedirect("register.jsp");
                    return;
                } else {
                    HttpSession httpsession = request.getSession();
                    httpsession.setAttribute("message", "Something went wrong try again!! ");
                    response.sendRedirect("register.jsp");
                    return;
                }

            } catch (Exception e) {
                e.printStackTrace();
                
                HttpSession httpsession = request.getSession();
                httpsession.setAttribute("message", "Something went wrong try again!! ");
                response.sendRedirect("register.jsp");
                return;
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
