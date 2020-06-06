package uts.isd.model;

import java.io.Serializable;
import java.util.Date;
/**
 *
 * @author Max
 */
public class OrderBean implements Serializable{
    private String address, status, quanity, orderId;
    private Date DOO;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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

    public String getQuanity() {
        return quanity;
    }

    public void setQuanity(String quanity) {
        this.quanity = quanity;
    }

    public Date getDOO() {
        return DOO;
    }

    public void setDOO(Date DOO) {
        this.DOO = DOO;
    }

    @Override
    public String toString() {
        return "OrderBean{" + "address=" + address + ", status=" + status + ", quanity=" + quanity + ", orderId=" + orderId + ", DOO=" + DOO + '}';
    }
}