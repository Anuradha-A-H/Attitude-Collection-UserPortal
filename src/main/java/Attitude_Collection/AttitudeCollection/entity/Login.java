package Attitude_Collection.AttitudeCollection.entity;

import Attitude_Collection.AttitudeCollection.entity.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Login{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String userName;
    private String password;

    @OneToOne(mappedBy = "login",cascade = CascadeType.ALL)
    private User user;


}
