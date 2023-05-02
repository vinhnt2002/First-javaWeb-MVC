/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.bw.order;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import sp.bw.product.Product;
import sp.bw.shopping.Cart;
import sp.bw.utils.DBUtils;

/**
 *
 * @author thanh
 */
public class OrdersDAO {

    private static final String INSERT_ORDERS = "INSERT INTO tblOrders(userID, orderDate) values(?,?)";
    private static final String SELECT_ORDERS_CURRENT = "select top 1 orderID from tblOrders order by orderID desc";
    private static final String INSERT_DETAIL_ORDER = "INSERT INTO tblOrderDetail(orderID, productID, quantity, price) VALUES(?,?,?,?)";
    private static final String SEARCH_ORDERS = "SELECT orderID FROM tblOrders WHERE userID = ?";
    private static final String SEARCH_ORDER_DETAIL = "SELECT orderID, productID, quantity, price FROM tblOrderDetail WHERE orderID=?";

    public static int addOrder(String userID, Cart cart) throws SQLException, ClassNotFoundException {
        Orders orders = new Orders();
        boolean check = false;
        Connection conn = null;
        PreparedStatement ptm = null;
        int kq = 0;
        ResultSet rs = null;

        Date date = new Date(System.currentTimeMillis());
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                ptm = conn.prepareStatement(INSERT_ORDERS);
                ptm.setString(1, userID);
                ptm.setDate(2,date);
                kq= ptm.executeUpdate();
//                check = ptm.executeUpdate() > 0 ? true : false;
            
            if(kq >0){
                ptm = conn.prepareStatement(SELECT_ORDERS_CURRENT);
                rs = ptm.executeQuery();
                
                if(rs != null && rs.next()){
                    int orderID = rs.getInt("orderID");
                    if(cart != null && cart.cart.size() >0){
                        for(Product obj: cart.getCart().values()){
                            String productID = obj.getProductID();
                            int quantity = obj.getQuantity();
                            double price = obj.getPrice();
                            ptm = conn.prepareStatement(INSERT_DETAIL_ORDER);
                            ptm.setInt(1, orderID);
                            ptm.setString(2, productID);
                            ptm.setInt(3, quantity);
                            ptm.setDouble(4, price);
                            kq = ptm.executeUpdate();
                        }
                    }
                }
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
        return kq;
    }
    
    public ArrayList<Integer> checkOrder(String userID) throws SQLException {
        ArrayList<Integer> list_Order = new ArrayList<>();
        Connection cn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
            cn = DBUtils.getConnection();
            ptm = cn.prepareStatement(SEARCH_ORDERS);
            ptm.setString(1, userID);
            rs = ptm.executeQuery();
            while (rs.next()) {
                list_Order.add(rs.getInt("orderID"));
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
            if (cn != null) {
                cn.close();
            }
        }
        return list_Order;
    }

    public ArrayList<OrderDetail> checkOrderDetail(int orderID) throws SQLException {
       ArrayList<OrderDetail> listOrders = new ArrayList<>();
        OrderDetail OrderDetail = null;
        Connection conn = null;
        PreparedStatement ptm = null;
        ResultSet rs = null;
        try {
             conn = DBUtils.getConnection();
            ptm = conn.prepareStatement(SEARCH_ORDER_DETAIL);
            ptm.setInt(1, orderID);
            rs = ptm.executeQuery();
            while (rs.next()) {
                String productID = rs.getString("productID");
                int quantity = rs.getInt("quantity");
                double price = rs.getDouble("price");
                OrderDetail = new OrderDetail(orderID, productID, price, quantity);
                listOrders.add(OrderDetail);
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
        return listOrders;
        
    }

}

