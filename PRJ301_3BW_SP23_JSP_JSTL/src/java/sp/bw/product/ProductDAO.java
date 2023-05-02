/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.bw.product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sp.bw.shopping.Cart;
import sp.bw.user.UserDTO;
import sp.bw.utils.DBUtils;

/**
 *
 * @author thanh
 */
public class ProductDAO {
    private static final String SEARCH_V1 = "SELECT name, price,quantity FROM tblProducts where productID = ? ";
   private static final String UPDATE_CHECKOUT = "UPDATE tblProducts SET quantity = ? where productID =?";
   private static final String SEARCH = "SELECT productID , name, price,quantity FROM tblProducts ";
    private static final String GET_PRODUCT = "SELECT  name, price FROM tblProducts WHERE productID = ? ";
     public Product checkOrdersDB(String productID) throws SQLException {
        Product pro = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(SEARCH_V1);
            ptm.setString(1, productID);

            //    ptm = conn.prepareStatement(LOGIN);
            rs = ptm.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                double price = rs.getFloat("price");
                int quantity = rs.getInt("quantity");
                pro = new Product(productID, name, price, quantity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }

        return pro;
    }
    
  
   public boolean CheckOutQuantity(Cart cart) throws SQLException {
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;

        try {
            conn = DBUtils.getConnection();

            for (Product pro : cart.getCart().values()) {
                String productID = pro.getProductID();
                int quantity = pro.getQuantity();
                Product proDB = checkOrdersDB(productID);
                int CheckOutQuantity = proDB.getQuantity() - quantity;
                
                if (conn != null) {
                    ptm = conn.prepareStatement(UPDATE_CHECKOUT);
                    ptm.setInt(1, CheckOutQuantity);
                    ptm.setString(2, productID);
                    check = ptm.executeUpdate() > 0 ? true : false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            if (ptm != null) {
                ptm.close();
            }
            if (conn != null) {
                conn.close();
            }

        }

        return check;
    }
public List<Product> getListProduct() throws SQLException {
       List<Product>  listProduct = new ArrayList();
        Product product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if(conn != null){
                ptm = conn.prepareStatement(SEARCH);
                rs= ptm.executeQuery();
                while (rs.next()) {                    
                    String productID = rs.getString("productID");
                    String name = rs.getString("name");
                    double price = Double.parseDouble(rs.getString("price"));
                    int quantity = Integer.parseInt(rs.getString("quantity"));
                    listProduct.add(new Product(productID, name, price, quantity));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(rs != null) rs.close();
            if(ptm != null) ptm.close();
            if(conn != null) conn.close();
        }
        return listProduct;   
    }
    
    public static void main(String[] args) throws SQLException {
        ProductDAO pdao = new ProductDAO();
        List<Product> list = pdao.getListProduct();
        for (Product o : list) {
            System.out.println(o);
        }
    }

    public Product getProduct(String productID, int quantity) throws SQLException {
        Product product = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(GET_PRODUCT);
                ptm.setString(1, productID);
                rs = ptm.executeQuery();
                if (rs.next()) {
                    String name = rs.getString("name");
                    double price = Double.parseDouble(rs.getString("price"));
                    product = new Product(productID, name, price, quantity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(rs != null) rs.close();
            if(ptm != null) ptm.close();
            if(conn != null) conn.close();
        }
        
        return product;
    }
}
