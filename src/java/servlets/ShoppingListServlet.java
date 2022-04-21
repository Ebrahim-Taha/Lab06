package servlets;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ShoppingListServlet", urlPatterns = {"/ShoppingListServlet"})

public class ShoppingListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        if (username != null) {   
            String action = request.getParameter("action");

            if (action != null && action.equals("logout")){
                session.invalidate();
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
            
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
            return;
        }else {
            getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        
        if (action.equals("register")){
            String username = request.getParameter("username");
            
            if (username != null){
                session.setAttribute("username", username);
                response.sendRedirect("ShoppingList");
                return;
            }else{
                getServletContext().getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
                return;
            }
        }
        else if (action.equals("add")) {
            ArrayList<String> itemList = (ArrayList<String>) session.getAttribute("shoppinglist");
            
            if (itemList == null){
                itemList = new ArrayList<>();
            }
            
            if (!request.getParameter("additem").isEmpty()){
                
                String item = request.getParameter("additem");
                itemList.add(item);
                session.setAttribute("shoppinglist", itemList);
                
            }
            
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
            return;
            
        }else if (action.equals("delete")) {   
            
            ArrayList<String> itemList = (ArrayList<String>) session.getAttribute("shoppinglist");
            String delete = request.getParameter("items");
            
            if (delete != null){
                itemList.remove(delete);
            }
            
            session.setAttribute("shoppinglist", itemList);
            getServletContext().getRequestDispatcher("/WEB-INF/shoppinglist.jsp").forward(request, response);
            return;
        }
    }

}