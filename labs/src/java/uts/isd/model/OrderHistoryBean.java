package uts.isd.model;

import java.util.Date;

/**
 *
 * @author Forever
 */
public class OrderHistoryBean {
    private OrderBean order;
    private CustomerBean customer;
    
    private int orderId, customerId;
    private double originalPrice, paidMoney, savedMoney;
    private String status, paymentMethod;
    private Date DOO;

    public OrderHistoryBean(OrderBean order, CustomerBean customer, int orderId, int customerId, Date DOO, String status, String paymentMethod, double originalPrice, double paidMoney, double savedMoney) {
        this.order = order;
        this.customer = customer;
        this.orderId = orderId;
        this.customerId = customerId;
        this.originalPrice = originalPrice;
        this.paidMoney = paidMoney;
        this.savedMoney = savedMoney;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.DOO = DOO;
    }
    
        public OrderHistoryBean(int orderId, int customerId, Date DOO, String status, String paymentMethod, double originalPrice, double paidMoney, double savedMoney) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.originalPrice = originalPrice;
        this.paidMoney = paidMoney;
        this.savedMoney = savedMoney;
        this.status = status;
        this.paymentMethod = paymentMethod;
        this.DOO = DOO;
    }

    public OrderHistoryBean(){
        order = null;
        customer = null;
        orderId = 0;
        customerId = 0;
        originalPrice = -1;
        paidMoney = -1;
        savedMoney = -1;
        status = null;
        paymentMethod = null;
        DOO = null;
    }
    public OrderBean getOrder() {
        return order;
    }

    public void setOrder(OrderBean order) {
        this.order = order;
    }

    public CustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
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

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public double getPaidMoney() {
        return paidMoney;
    }

    public void setPaidMoney(double paidMoney) {
        this.paidMoney = paidMoney;
    }

    public double getSavedMoney() {
        return savedMoney;
    }

    public void setSavedMoney(double savedMoney) {
        this.savedMoney = savedMoney;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Date getDOO() {
        return DOO;
    }

    public void setDOO(Date DOO) {
        this.DOO = DOO;
    }
}
