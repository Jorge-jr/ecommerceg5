package controller;

import dao.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import model.Product;


public class Pagina extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        response.setContentType("text/html;charset=UTF-8");
        ProductDao pDao = new ProductDao();
        int pagina = Integer.parseInt(request.getParameter("pagina"));
        ArrayList<Product> listProducts = pDao.listarTodos((pagina - 1) * 5);
        request.setAttribute("lista", listProducts);
        
        HttpSession sessao = request.getSession();
        request.setAttribute("pagina", request.getParameter("pagina"));
        request.setAttribute("tamanhoTotal", pDao.amountOfProducts());
        request.setAttribute("qtdeDePaginas", (pDao.amountOfProducts() % 5 != 0? pDao.amountOfProducts() / 5 + 1 : pDao.amountOfProducts()/5));
        
        RequestDispatcher despachante = getServletContext().getRequestDispatcher("/index2.jsp");
        despachante.forward(request, response);
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
//            out.println(produtos.get(0).getDescription());
        }
    }
    protected String displayProducts (){
        
        return "";
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
