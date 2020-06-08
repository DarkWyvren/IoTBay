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
    
    private int orderId, customerId, productQuantity;
    private String shippingAddress, productName;
    private Date DOO;

    public OrderBean(CustomerBean customer, ProductBean product, int orderId, int customerId, Date DOO, String shippingAddress, String productName, int productQuantity) {
        this.customer = customer;
        this.product = product;
        this.orderId = orderId;
        this.customerId = customerId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.shippingAddress = shippingAddress;
        this.DOO = DOO;
    }
    
    public OrderBean(int orderId, int customerId, Date DOO, String shippingAddress, String productName, int productQuantity) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.productName = productName;
        this.productQuantity = productQuantity;
        this.shippingAddress = shippingAddress;
        this.DOO = DOO;
    }
    
    public OrderBean(int orderId, int customerId, Date DOO) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.DOO = DOO;
    }
    
    public OrderBean() {
        customer = null;
        product = null;
        orderId = 0;
        customerId = 0;
        productName = null;
        productQuantity = 0;
        shippingAddress = null;
        DOO = null;
    }

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

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }
    
    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    @Override
    public String toString() {
        return "OrderBean{" + "orderId=" + orderId + ", customerId=" + customerId + ", address=" + shippingAddress + ", productName=" + productName + ", quantity=" + productQuantity + ", DOO=" + DOO + '}';
    }

    
}