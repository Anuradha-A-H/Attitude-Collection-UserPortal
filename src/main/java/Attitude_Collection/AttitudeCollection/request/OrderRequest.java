package Attitude_Collection.AttitudeCollection.request;

import Attitude_Collection.AttitudeCollection.Enum.PaymentStatus;
import Attitude_Collection.AttitudeCollection.entity.OrderItem;
import jakarta.persistence.Column;
import lombok.Data;

import java.util.Date;
import java.util.List;


@Data
public class OrderRequest {
    private String deliveredDate;
    private String phoneNo;
    private String totalAmount;
    private String address;
    private Integer pincode;
    public String description;
    public String ordersfromLocalStorage;
}
