package Attitude_Collection.AttitudeCollection.controller;


import Attitude_Collection.AttitudeCollection.entity.Login;
import Attitude_Collection.AttitudeCollection.entity.Orders;
import Attitude_Collection.AttitudeCollection.request.RegisterRequest;
import Attitude_Collection.AttitudeCollection.response.ProductDtlResponse;
import Attitude_Collection.AttitudeCollection.response.SessionResponse;
import Attitude_Collection.AttitudeCollection.service.MainService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Controller
@RequestMapping("/attitudeCollection")
public class MainController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private MainService mainService;
    @GetMapping("/cartlist")
    public String cartlist()
    {
        return "cartlist";
    }
    @GetMapping("/")
    public String mainpage(Model m){
        String url = "http://localhost:8080/products/allProducts"; // Assuming this is the correct URL
        try {
            List<ProductDtlResponse> products = restTemplate.getForObject(url, List.class);
            m.addAttribute("pro",products);
            return "index";
        } catch (Exception e) {
            m.addAttribute("pro",HttpStatus.INTERNAL_SERVER_ERROR);
            return "404";
        }
  }

  @GetMapping("/trakeview")
    public String gettrake(HttpSession session, Model m) throws Exception {
        try{
            List<Orders> order =  mainService.getOrderdtl(session);
            m.addAttribute("orderList", order);
            return "trakeorder";
        }catch(Exception e){
            m.addAttribute("error", "Login to see order Details");
            return "login";
        }

  }

  @GetMapping("/loginv")
    public String loginv(){
        return "login";
  }
    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute RegisterRequest request, Model m,  HttpSession session){
            try{
                SessionResponse logindtl = mainService.register(request);
                if(logindtl.getId() != null)
                {
                    session.setAttribute("username",logindtl.getUserName());
                    session.setAttribute("userId",logindtl.getId());
                    session.setAttribute("profile",logindtl.getImg());
                    return "redirect:/attitudeCollection/";
                }
                m.addAttribute("error","Some thing went wrong");
                return "signup";
            }catch(Exception e){
                m.addAttribute("error",e.getMessage());
                return "signup";
            }
    }

    @PostMapping("/login")
    public String login(@ModelAttribute Login login, Model m, HttpSession session) throws Exception {
        try {
            SessionResponse logindtl = mainService.checklogin(login);
            if (logindtl.getId() != null) {
                session.setAttribute("username", logindtl.getUserName());
                session.setAttribute("userId", logindtl.getId());
                session.setAttribute("profile", logindtl.getImg());
                return "redirect:/attitudeCollection/";
            }
            m.addAttribute("error", "something went wrong");
            return "login";
        }catch(Exception e) {
            m.addAttribute("error", e.getMessage());
            return "login";
        }
    }
}
