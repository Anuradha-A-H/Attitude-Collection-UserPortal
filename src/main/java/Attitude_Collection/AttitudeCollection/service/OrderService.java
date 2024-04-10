package Attitude_Collection.AttitudeCollection.service;

import Attitude_Collection.AttitudeCollection.Enum.OrderStatus;
import Attitude_Collection.AttitudeCollection.Enum.PaymentStatus;
import Attitude_Collection.AttitudeCollection.entity.OrderItem;
import Attitude_Collection.AttitudeCollection.entity.Orders;
import Attitude_Collection.AttitudeCollection.entity.User;
import Attitude_Collection.AttitudeCollection.repository.OrderItemRepository;
import Attitude_Collection.AttitudeCollection.repository.OrderRepository;
import Attitude_Collection.AttitudeCollection.repository.UserRepository;
import Attitude_Collection.AttitudeCollection.request.OrderRequest;
import Attitude_Collection.AttitudeCollection.response.ResponseOrder;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    private OrderItemRepository orderItemRepo;

    @Autowired
    private OrderRepository orderRepo;
    @Autowired
    private UserRepository userRepo;

    public void placeOrder(OrderRequest request, HttpSession session)
    {
        System.out.println(request.getOrdersfromLocalStorage());
        ObjectMapper objectMapper = new ObjectMapper();
        List<OrderItem> orderItems = null;
        try {
            orderItems = objectMapper.readValue(request.getOrdersfromLocalStorage(), new TypeReference<List<OrderItem>>(){});
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            List<String> listing = new ArrayList<>();
               listing.add("Attitude Collection Office");
            Integer id = (Integer) session.getAttribute("userId");
            if(id == null)
                throw new Exception("Login to Order");
            Optional<User> userd = userRepo.findById(id);
            if(userd.isEmpty())
                throw new Exception("User not found");
            User userdtl = userd.get();
            Orders orderdtl = Orders.builder().orderDate(new Date())
                    .deliveredDate(dateFormat.parse(request.getDeliveredDate()))
                    .phoneNo(Long.parseLong(request.getPhoneNo()))
                    .totalAmount(request.getTotalAmount())
                    .paymentStatus(PaymentStatus.INPROCESS)
                    .address(request.getAddress())
                    .pincode(request.getPincode())
                    .orderstatus(OrderStatus.PLACED)
                    .user(userdtl)
                    .trackOrderPlace(listing)
                    .build();
            orderdtl= orderRepo.save(orderdtl);
            List<OrderItem> orderItemList = new ArrayList<>();
            for( OrderItem item : orderItems)
            {
                orderItemList.add(orderItemRepo.save(OrderItem.builder()
                                .productId(item.getProductId())
                                .quantity(item.getQuantity())
                                .orders(orderdtl)
                        .build()));

            }

            orderdtl.setOrderItemList(orderItemList);
            Orders order = orderRepo.save(orderdtl);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getTotalEarningMonthly()
    {
        return orderRepo.getTotalEarningMonthly();
    }

    public Integer getTotalEarningAnnual(){
        return orderRepo.getTotalEarningAnnual();
    }
    public Integer getTotalOrders(){
        return orderRepo.getTotalOrders();
    }
    public Integer getPendingOrders(){
        return orderRepo.getPendingOrders();
    }

    public List<ResponseOrder> getResentOrderslist(){
        List<Orders> ordersList =  orderRepo.getRecent5Orders();
        List<ResponseOrder> list = new ArrayList<>();
        for(Orders r : ordersList)
        {
            list.add(new ResponseOrder(r.getOrderId(),r.getOrderstatus(),Integer.parseInt(r.getTotalAmount())));
        }
        return list;
    }
}
