/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.bw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sp.bw.order.OrderDetail;
import sp.bw.order.OrdersDAO;
import sp.bw.user.UserDTO;

/**
 *
 * @author thanh
 */
public class HistoryController extends HttpServlet {

    private static final String ERROR = "error.jsp";
    private static final String SUCCESS = "history.jsp";
    private static final String LOGIN_PAGE = "login.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = ERROR;
        HttpSession session = request.getSession();
        ArrayList<OrderDetail> listOrder = new ArrayList<>();

        try {
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            if (loginUser != null) {
                OrdersDAO dao = new OrdersDAO();
                ArrayList<Integer> list_orderID = dao.checkOrder(loginUser.getUserID());
                for (int orderID : list_orderID) {
                    ArrayList<OrderDetail> listOrdersDetail = dao.checkOrderDetail(orderID);
                    for (OrderDetail orderDetail : listOrdersDetail) {
                        listOrder.add(orderDetail);
                    }
                }
                session.setAttribute("listOrder", listOrder);
                url = SUCCESS;
            } else {
                url = LOGIN_PAGE;
            }
        } catch (Exception e) {
            log("ERROR at HistoryController" + e.toString());
        } finally {
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
