package com.mycart.Servlet;

import com.mycart.dao.CategoryDao;
import com.mycart.dao.ProductDao;
import com.mycart.entites.Category;
import com.mycart.entites.Product;
import com.mycart.helper.FactoryProvider;
import com.mycart.helper.PicSaveFile;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@MultipartConfig
public class ProductOperationServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            String operation = request.getParameter("operation");

            if (operation.trim().equals("addcategory")) {

//            add category data
                String cattitle = request.getParameter("catTitle");
                String catdescription = request.getParameter("catDescription");

                Category ct = new Category();
                ct.setCatTitle(cattitle);
                ct.setCatDescription(catdescription);

                CategoryDao ctd = new CategoryDao(FactoryProvider.getFactory());
                int catId = ctd.saveCategory(ct);

//                out.println("Category saved");
                HttpSession sht = request.getSession();
                sht.setAttribute("message", "Category added successfully : " + catId);
                response.sendRedirect("admin.jsp");
                return;

            } else if (operation.trim().equals("addproduct")) {
//                add product
                String pname = request.getParameter("pname");
                String pdesc = request.getParameter("pDesc");
                int catId = Integer.parseInt(request.getParameter("catId"));
                int pprice = Integer.parseInt(request.getParameter("pprice"));
                int pdiscount = Integer.parseInt(request.getParameter("pdiscount"));
                int pquentity = Integer.parseInt(request.getParameter("pquentity"));
//                Part part = request.getPart("pphoto");

                List<String> list = new ArrayList<>();
                PicSaveFile psf = new PicSaveFile();
                for (Part prt : request.getParts()) {
                   String filename = prt.getSubmittedFileName();
                    if(filename!=null){ 
                        
                    list.add(filename);
                    String path=request.getSession().getServletContext().getRealPath("Img")+File.separator+"Products"+File.separator+prt.getSubmittedFileName();
                    InputStream is = prt.getInputStream();
                    psf.SaveFile(is, path);
                    
                    System.out.println(path);
                    }
                }

                CategoryDao cdao = new CategoryDao(FactoryProvider.getFactory());
                Category cs = cdao.getCategoryById(catId);

                Product pt = new Product(pname, pdesc, list, pprice, pdiscount, pquentity, cs);

                ProductDao pd = new ProductDao(FactoryProvider.getFactory());
                boolean f = pd.SaveProduct(pt);
                if (f == true) {
                    HttpSession sht = request.getSession();
                    sht.setAttribute("message", "product added successfully !!");
                    response.sendRedirect("admin.jsp");
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
