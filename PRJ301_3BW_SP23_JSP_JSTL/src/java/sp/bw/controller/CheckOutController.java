/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.bw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sp.bw.order.OrderDetail;
import sp.bw.order.Orders;
import sp.bw.order.OrdersDAO;
import sp.bw.product.Product;
import sp.bw.product.ProductDAO;
import sp.bw.shopping.Cart;
import sp.bw.user.UserDTO;

/**
 *
 * @author thanh
 */
public class CheckOutController extends HttpServlet {

    private static final String ERROR = "viewCart.jsp";
    private static final String SUCCESS = "CheckOut.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        try {
            HttpSession session = request.getSession();
            Cart cart = (Cart) session.getAttribute("CART");
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
    
            if (loginUser == null) {
                request.setAttribute("ERROR", "Vui lòng login để Check Out!");
                url = "login.html";
            } else {
                boolean checkDB = false;
                ProductDAO dao = new ProductDAO();
                Product proDB = null;
                for (Product pro : cart.getCart().values()) {
                    proDB = dao.checkOrdersDB(pro.getProductID());
                    int quantityDB = proDB.getQuantity();
                    int quantityCart = pro.getQuantity();
                    if (quantityCart > quantityDB) {
                        request.setAttribute("ERROR1", "The quantity of " + pro.getName()
                                + " in your cart exceeds the available quantity.");
                        url = ERROR;
                        break; // exit the loop
                    }
                    if (quantityDB > 0) {
                        checkDB = true;
                    } else {
                        checkDB = false;
                    }

                }
                if (checkDB) {

                    String userID = loginUser.getUserID();
                    int kq = OrdersDAO.addOrder(userID, cart);

                    boolean check = dao.CheckOutQuantity(cart);
                    if (kq > 0 && check) {
                        url = SUCCESS;
                        session.removeAttribute("CART");
                    }
                }else{
                    request.setAttribute("MESSAGE1", "Quantity max: "
                        + "" + proDB.getQuantity());
                    url = ERROR;
                }

            }
        } catch (Exception e) {
            log("Error at CheckoutController" + e.toString());
        }finally{
            request.getRequestDispatcher(url).forward(request, response);
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
