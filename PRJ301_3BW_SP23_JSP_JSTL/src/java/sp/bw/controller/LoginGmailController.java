/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.bw.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import sp.bw.user.UserDAO;
import sp.bw.user.UserDTO;
import sp.bw.user.UserGoogleDTO;
import sp.bw.utils.Constants;

/**
 *
 * @author thanh
 */
public class LoginGmailController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    private static final String US = "US";
    private static final String US_PAGE = "user.jsp";
    private static final String AD = "AD";
    private static final String AD_PAGE = "admin.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;

        try {
            String code = request.getParameter("code");

            String accessToken = getToken(code);

            UserGoogleDTO userGmail = getUserInfo(accessToken);

            String[] userGG = userGmail.getEmail().split("@");
            String userID = userGG[0];
            String fullName = userGG[0];
            //String roleID = "US";
            String password = userGG[0];

            UserDAO dao = new UserDAO();
            boolean checkExisted = dao.chechExisted(userID);
            if (checkExisted) {
                UserDTO loginUser = dao.checkLogin(userID, password);
                if (loginUser == null) {
                    request.setAttribute("ERROR", "Incorrect userID or password");

                } else {
                    // phan quyen
                    // 
                    HttpSession sesstion = request.getSession();
                    sesstion.setAttribute("LOGIN_USER", loginUser);

                    String roleID = loginUser.getRoleID();
                    if (US.equals(roleID)) {
                        url = US_PAGE;
                        request.setAttribute("loginUser", userID);
                    } else if (AD.equals(roleID)) {
                        url = AD_PAGE;
                        request.setAttribute("loginUser", userID);

                    } else {
                        request.setAttribute("ERROR", "Your role is not support!");
                    }
                }
            } else {
                String roleID = "US";
                UserDTO loginUser = new UserDTO(userID, fullName, roleID, password);

                //boolean checkInserd = dao.insert(user);
                boolean checkInserd = dao.insert(loginUser);
                if (checkInserd) {
                    HttpSession sesstion = request.getSession();
                    sesstion.setAttribute("LOGIN_USER", loginUser);
                    url = US_PAGE;
                    request.setAttribute("loginUser", userID);
                } else {
                    request.setAttribute("ERROR", "Unknow error!");
                }
            }

        } catch (Exception e) {
            log("Error at loginGmailController: " + e.toString());
        } finally {
            request.getRequestDispatcher(url).forward(request, response);
        }

    }

    public static String getToken(String code) throws ClientProtocolException, IOException, ServletException {
        // call api to get token
        String response = Request.Post(Constants.GOOGLE_LINK_GET_TOKEN)
                .bodyForm(Form.form().add("client_id", Constants.GOOGLE_CLIENT_ID)
                        .add("client_secret", Constants.GOOGLE_CLIENT_SECRET)
                        .add("redirect_uri", Constants.GOOGLE_REDIRECT_URI).add("code", code)
                        .add("grant_type", Constants.GOOGLE_GRANT_TYPE).build())
                .execute().returnContent().asString();

        JsonObject jobj = new Gson().fromJson(response, JsonObject.class);
        String accessToken = jobj.get("access_token").toString().replaceAll("\"", "");
        return accessToken;
    }

    public static UserGoogleDTO getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
        String link = Constants.GOOGLE_LINK_GET_USER_INFO + accessToken;
        String response = Request.Get(link).execute().returnContent().asString();

        UserGoogleDTO googlePojo = new Gson().fromJson(response, UserGoogleDTO.class);

        return googlePojo;
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
