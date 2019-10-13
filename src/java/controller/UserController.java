package controller;

import dao.UserDao;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

@WebServlet(name = "UserController", urlPatterns = {"/user"})
public class UserController extends HttpServlet {

    protected void processRequest(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=UTF-8");
        req.getRequestDispatcher("create-user.jsp").forward(req, res);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    private User createUser(HttpServletRequest req) {
        String cpf = req.getParameter("cpf");
        String email = req.getParameter("email");
        String fullName = req.getParameter("fullName");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User user = new User(cpf, email, fullName, username, password);
        return user;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        User user = createUser(req);
        UserDao userDao = new UserDao();
        User userSaved = userDao.save(user);
        createUserHandler(userSaved, req, res);
    }

    protected void createUserHandler(User user, HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        if (user == null){
            req.setAttribute("error", "Usuário não cadastrado");
            req.getRequestDispatcher("create-user.jsp").forward(req, res);
        } else {
            req.setAttribute("user", user);
            req.getRequestDispatcher("user-page.jsp").forward(req, res);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
