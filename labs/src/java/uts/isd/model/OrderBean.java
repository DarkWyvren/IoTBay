package uts.isd.model;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author Max
 */
public class OrderBean implements Serializable{
    private CustomerBean customer;
    private ProductBean product;
    
    private int orderId, customerId, productId, productQuanity;
    private double productPrice, totalPrice;
    private String address, status, productName;
    private Date DOO;

    public CustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    public ProductBean getProduct() {
        return product;
    }

    public void setProduct(ProductBean product) {
        this.product = product;
    }
    
    public Date getDOO() {
        return DOO;
    }

    public void setDOO(Date DOO) {
        this.DOO = DOO;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
    
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }
    
    public int getProductQuanity() {
        return productQuanity;
    }

    public void setProductQuanity(int productQuanity) {
        this.productQuanity = productQuanity;
    }
    
    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "OrderBean{" + "orderId=" + orderId + ", customerId=" + customerId + ", address=" + address + ", status=" + status + ", productId=" + productId + ", quanity=" + productQuanity + ", DOO=" + DOO + '}';
    }

    
}