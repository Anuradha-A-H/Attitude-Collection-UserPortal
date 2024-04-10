package Attitude_Collection.AttitudeCollection.controller;



import Attitude_Collection.AttitudeCollection.request.OrderRequest;
import Attitude_Collection.AttitudeCollection.response.ResponseOrder;
import Attitude_Collection.AttitudeCollection.service.OrderService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/attitudeCollection")
public class OrderController {

    @Autowired
    private OrderService orderService;
    @GetMapping("orderit")
    public String orderview(){
        return "order";
    }

    @PostMapping("/placeorder")
    public String placeorder(@ModelAttribute OrderRequest request, HttpSession session){
        orderService.placeOrder(request, session);

        return "order";
    }

    @GetMapping("/totalEarningMonthly")
    public ResponseEntity<Integer> totalEarningMonthly(){
        return new ResponseEntity<>(orderService.getTotalEarningMonthly(), HttpStatus.OK);
    }
    @GetMapping("/totalEarningAnnual")
    public ResponseEntity<Integer> totalEarningAnnual(){
        return new ResponseEntity<>(orderService.getTotalEarningAnnual(),HttpStatus.OK);
    }
    @GetMapping("/totalOrders")
    public ResponseEntity<Integer> totalOrders(){
        return new ResponseEntity<>(orderService.getTotalOrders(),HttpStatus.OK);
    }
    @GetMapping("/pendingOrders")
    public ResponseEntity<Integer> pendingOrders(){
        return new ResponseEntity<>(orderService.getPendingOrders(),HttpStatus.OK);
    }
    @GetMapping("/resentOrderslist")
    public ResponseEntity<List<ResponseOrder>> resentOrderslist(){
        return new ResponseEntity<>(orderService.getResentOrderslist(),HttpStatus.OK);
    }





}
