package Attitude_Collection.AttitudeCollection.service;


import Attitude_Collection.AttitudeCollection.Enum.OrderStatus;
import Attitude_Collection.AttitudeCollection.entity.Login;
import Attitude_Collection.AttitudeCollection.entity.Orders;
import Attitude_Collection.AttitudeCollection.entity.User;
import Attitude_Collection.AttitudeCollection.repository.LoginRepository;
import Attitude_Collection.AttitudeCollection.repository.UserRepository;
import Attitude_Collection.AttitudeCollection.request.RegisterRequest;
import Attitude_Collection.AttitudeCollection.response.CategoryResponse;
import Attitude_Collection.AttitudeCollection.response.ProductDtlResponse;
import Attitude_Collection.AttitudeCollection.response.SessionResponse;
import Attitude_Collection.AttitudeCollection.util.ImageUtil;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MainService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    LoginRepository loginRepository;
    @Autowired
    UserRepository userRepository;

    public Object allCategory(){
        List<CategoryResponse> products = new ArrayList<>();
        String url = "http://localhost:8080/category/rest";
        return  restTemplate.getForObject(url,Object.class);
    }

    public SessionResponse register(RegisterRequest request) throws ParseException {
        String sub[] = request.getEmail().split("@");
        String username = sub[0];
        String password = sub[0] + "@123";
        Login logindtl = Login.builder()
                .userName(username)
                .password(password)
                .build();
        logindtl = loginRepository.save(logindtl);
        User newUser = User.builder()
                .dateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(request.getDateOfBirth()))
                .email(request.getEmail())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .gender(request.getGender())
                .profile(ImageUtil.compressImage(request.getProfile().getBytes()))
                .login(logindtl)
                .build();


        newUser = userRepository.save(newUser);
         logindtl.setUser(newUser);
        loginRepository.save(logindtl);
        return new SessionResponse(newUser.getUserId(),newUser.getFirstName()+" "+newUser.getLastName(), request.getProfile());

    }

    public SessionResponse checklogin(Login response) throws Exception
    {
        Optional<Login> log = loginRepository.findByUserName(response.getUserName());
        if(log.isEmpty())
            throw new Exception("invalid User Name");
        Login login = log.get();
        if(!login.getPassword().equals(response.getPassword()))
        {
            throw new Exception("Invalid password");

        }
        User userdtl = login.getUser();
        return new SessionResponse(userdtl.getUserId(),userdtl.getFirstName()+" "+userdtl.getLastName(), userdtl.getProfile().toString());
    }

    public List<Orders> getOrderdtl(HttpSession session) throws Exception {
        Integer id = (Integer) session.getAttribute("userId");
        if(id == null)
            throw new Exception("login ");
        User user  = userRepository.findById(id).get();
        List<Orders> ordersList = user.getOrderList();
        List<Orders> placedOrder = new ArrayList<>();
        for(Orders o : ordersList)
        {
            if(o.getOrderstatus() != OrderStatus.DELIVERED)
            {
                placedOrder.add(o);
            }
        }
        return placedOrder;
    }

}
