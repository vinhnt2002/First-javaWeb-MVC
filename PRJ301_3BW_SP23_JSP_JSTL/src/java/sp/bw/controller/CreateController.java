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
import sp.bw.user.UserDAO;
import sp.bw.user.UserDTO;
import sp.bw.user.UserError;

/**
 *
 * @author thanh
 */
public class CreateController extends HttpServlet {

    private static final String ERROR = "createUser.jsp";
    private static final String SUCCESS = "login.html";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        UserError userError = new UserError();
        String url = ERROR;
        try {
            String userID = request.getParameter("userID");
            String fullName = request.getParameter("fullName");
            String roleID = request.getParameter("roleID");
            String password = request.getParameter("password");
            String confirm = request.getParameter("confirm");
            
            // check validate theo cách truyền thống
            boolean checkValidation = true;
            if(userID.length() < 2 || userID.length() > 6){
                userError.setUserIDError("UserID must in range [2,6]");
                checkValidation = false;
            }
            UserDAO dao = new UserDAO();
//            boolean checkDuplicate = false;
//            checkDuplicate = dao.checkDuplicate(userID);
//            if(checkDuplicate){
//                userError.setErrorError("Duplicate userID");
//                checkValidation = false;
//            }
            if(fullName.length() < 5 || fullName.length() > 20){
                userError.setFullNameError("UserID must in range [5,20]");
                checkValidation = false;
            }
            if(!password.equals(confirm)){
                userError.setConfirmError("Hai password khong giong nhau");
                checkValidation = false;
            }
            if (checkValidation) {
                UserDTO newUser = new UserDTO(userID, fullName, roleID, password);
//                boolean checkInsert = dao.insert(newUser);
                boolean checkInsert = dao.insertV2(newUser);
                if (checkInsert) {
                    url = SUCCESS;
                } else {
                    url = ERROR;
                    userError.setErrorError("Undefine Error");
                    request.setAttribute("USER_ERROR", userError);

                }
            } else {
                url = ERROR;
                request.setAttribute("USER_ERROR", userError);
            }
            
        } catch (Exception e) {
            log("Error at CreateController" + e.toString());
            if(e.toString().contains("duplicate")){
                userError.setUserIDError("Trung ID roi kia");
                request.setAttribute("USER_ERROR", userError);
            }
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
