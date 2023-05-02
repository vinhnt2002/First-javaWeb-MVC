/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sp.bw.shopping;

import java.util.HashMap;
import java.util.Map;
import sp.bw.product.Product;

/**
 *
 * @author thanh
 */
public class Cart {

    public Map<String, Product> cart;

    public Cart() {
    }

    public Cart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public Map<String, Product> getCart() {
        return cart;
    }

    public void setCart(Map<String, Product> cart) {
        this.cart = cart;
    }

    public boolean add(Product p) {
        boolean check = false;
        try {
            if (this.cart == null) {
                this.cart = new HashMap<>();
            }
            if (this.cart.containsKey(p.getProductID())) {
                int currentQuantity = this.cart.get(p.getProductID()).getQuantity();
                p.setQuantity(currentQuantity + p.getQuantity());
            }
            this.cart.put(p.getProductID(), p);
            check = true;
        } catch (Exception e) {
        }

        return check;
    }

    public boolean update(String productID, Product p) {
        boolean check = false;
        if(this.cart != null){
            if(this.cart.containsKey(productID)){
                this.cart.replace(productID, p);
                check =true;
            }
        }
        return check;
    }

    public boolean remove(String productID, Product p) {
        boolean check = false;
        if(this.cart != null){
            if(this.cart.containsKey(productID)){
                this.cart.remove(productID);
                check = true;
            }
        }
        return check;
    }
}
