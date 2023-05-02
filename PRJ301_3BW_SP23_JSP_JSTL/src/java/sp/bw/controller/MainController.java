/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.bw.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author thanh
 */
public class MainController extends HttpServlet {

    private final static String ERROR = "error.jsp";
    private final static String WELCOME = "login.html";

    private final static String LOGIN = "Login";
    private final static String LOGIN_CONTROLLER = "LoginController";
    private final static String SEARCH = "Search";
    private final static String SEARCH_CONTROLLER = "SearchController";
    private final static String LOG_OUT = "Logout";
    private final static String LOG_OUT_CONTROLLER = "LogoutController";
    private final static String DELETE = "Delete";
    private final static String DELETE_CONTROLLER = "DeleteController";
    private final static String UPDATE = "Update";
    private final static String UPDATE_CONTROLLER = "UpdateController";
    private static final String CREATE_USER = "create User";
    private static final String CREATE_USER_PAGE = "createUser.html";
    private final static String CREATE = "Create";
    private final static String CREATE_CONTROLLER = "CreateController";
    private final static String GET_ALL = "Get all";
    private final static String GET_ALL_CONTROLLER = "GetAllController";
    private final static String ADD = "Add";
    private final static String ADD_CONTROLLER = "AddController";
    private static final String SHOPPING = "shopping";
    private static final String SHOPPING_PAGE = "shopping.html";
    private final static String VIEW_CART = "View cart";
    private final static String VIEW_CART_PAGE = "viewCart.jsp";
    private final static String EDIT = "Edit";
    private final static String EDIT_CONTROLLER = "EditController";
    private final static String REMOVE = "Remove";
    private final static String REMOVE_CONTROLLER = "RemoveController";
    private final static String CHECK_OUT = "Check out";
    private final static String CHECK_OUT_CONTROLLER = "CheckOutController";
    private final static String HISTORY = "History";
    private final static String HISTORY_CONTROLLER = "HistoryController";
    private final static String SELECT = "select";
    private final static String SELECT_CONTROLLER = "SelectController";
    private static final String HOME = "Home";
    private static final String HOME_CONTROLLER = "HomeController";
    private static final String LOGIN_GMAIL = "Login With Google";
    private static final String LOGIN_GMAIL_PAGE = "https://accounts.google.com/o/oauth2/auth?scope=email&redirect_uri="
            + "http://localhost:8084/PRJ301_3BW_SP23_JSP_JSTL/LoginGmailController&response_type=code"
            + "&client_id=54791590240-7htu3al8gcvn5tk60sj390jle1q74cnc.apps.googleusercontent.com"
            + "&approval_prompt=force";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String url = ERROR;

        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = WELCOME;
            } else if (LOGIN.equals(action)) {
                url = LOGIN_CONTROLLER;
            } else if (SEARCH.equals(action)){
                url = SEARCH_CONTROLLER;
            } else if (LOG_OUT.equals(action)){
                url = LOG_OUT_CONTROLLER;
            } else if (DELETE.equals(action)){
                url = DELETE_CONTROLLER;
            } else if (UPDATE.equals(action)){
                url = UPDATE_CONTROLLER;
            } else if(CREATE_USER.equals(action)){
                url = CREATE_USER_PAGE;
            }else if (CREATE.equals(action)){
                url = CREATE_CONTROLLER;
            } else if (GET_ALL.equals(action)){
                url = GET_ALL_CONTROLLER;
            } else if (ADD.equals(action)){
                url = ADD_CONTROLLER;
            } else if (SHOPPING.equals(action)){
                url = SHOPPING_PAGE;
            }else if (VIEW_CART.equals(action)){
                url = VIEW_CART_PAGE;
            } else if(EDIT.equals(action)){
                url = EDIT_CONTROLLER;
            } else if(REMOVE.equals(action)){
                url = REMOVE_CONTROLLER;
            } else if(CHECK_OUT.equals(action)){
                url = CHECK_OUT_CONTROLLER;
            } else if(HISTORY.equals(action)){
                url = HISTORY_CONTROLLER;
            } else if (LOGIN_GMAIL.equals(action)) {
                response.setStatus(response.SC_MOVED_TEMPORARILY);
                response.setHeader("Location", LOGIN_GMAIL_PAGE);
            } else if(SELECT.equals(action)){
                url = SELECT_CONTROLLER;
            }   else if(HOME.equals(action)){
                url = HOME_CONTROLLER;
            }
        } catch (Exception e) {
            log("Error at MainController: " + e.toString());
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
