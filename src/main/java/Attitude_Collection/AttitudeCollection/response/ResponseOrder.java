package Attitude_Collection.AttitudeCollection.response;

import Attitude_Collection.AttitudeCollection.Enum.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class ResponseOrder {

    private String orderId;
    private OrderStatus status;
    private Integer totalAmount;
}
