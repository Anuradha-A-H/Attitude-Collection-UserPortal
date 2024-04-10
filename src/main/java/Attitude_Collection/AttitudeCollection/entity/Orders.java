package Attitude_Collection.AttitudeCollection.entity;


import Attitude_Collection.AttitudeCollection.Enum.OrderStatus;
import Attitude_Collection.AttitudeCollection.Enum.PaymentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String orderId;
    private Date orderDate;

    private Date deliveredDate;
    @Column(nullable = false)
    private Long phoneNo;
    private Integer deliveryBoyId;
    private String totalAmount;

    private PaymentStatus paymentStatus;
    @Column(nullable = false)
    private String address;
    private Integer pincode;
    private OrderStatus orderstatus;
    private List<String> trackOrderPlace;

    @JoinColumn
    @ManyToOne
    private User user;


    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItem> orderItemList = new ArrayList<>();

}
