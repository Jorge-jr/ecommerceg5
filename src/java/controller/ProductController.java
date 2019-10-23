/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProductDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author Celestino
 */
@WebServlet(name = "ProductController", urlPatterns = {"/produto"})
public class ProductController extends HttpServlet {
    
    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("create-user.jsp").forward(req, res);
        
    }

    private Product createProduct(HttpServletRequest req) {
        String name = req.getParameter("name");
        String description = req.getParameter("description");
        Double price = Double.parseDouble(req.getParameter("price"));
        String imgUrl = req.getParameter("imageUrl");
        
        Product prod = new Product(name, description, price, imgUrl);
        return prod;
    }
    
    private void deleteProduct(int productId, HttpServletRequest req ,HttpServletResponse res) 
            
            throws ServletException, IOException {
        
        ProductDao prodDao = new  ProductDao();
        //prodDao.excluir(productId);                                                                   
        prodDao.deleteByProductId(productId);                                                                   
        res.sendRedirect(getServletContext().getInitParameter("root") + "produto/listar.jsp");
        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
      
        String action = req.getParameter("action");
        PrintWriter out = res.getWriter();
        switch(action){
            case "register":
                out.print("Entrou no case");
                Product prod = createProduct(req);
                ProductDao prodDao = new ProductDao();
                Product productSaved = prodDao.save(prod);
                createProductHandler(productSaved, req, res);
                break;
                
            case "delete":
                int productId = Integer.parseInt(req.getParameter("id")) ;
                deleteProduct(productId, req, res);
                break;
                
            case "update":
                out.print("Alterando dados do produto");
                
                
                break;
                
            case "list":
                out.print("Exibindo produto");
               break;
               
            case "listAll":
                out.print("Listando todos os produtos");
                
                break;
                
            default:
                res.sendRedirect(req.getContextPath() + "/create-product.jsp"); 
                break;       
        }
        

        
    }

    
    public void createProductHandler(Product prod, HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
        if (prod == null){
            req.setAttribute("error", "Produto não cadastrado");
            req.getRequestDispatcher("create-user.jsp").forward(req, res);
        } else {
            req.setAttribute("prod", prod);
           //req.getRequestDispatcher("product-page.jsp").forward(req, res);
           // res.sendRedirect(req.getContextPath() + "/user-page.jsp");
           res.sendRedirect(req.getContextPath() + "/produto/listar.jsp");
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
