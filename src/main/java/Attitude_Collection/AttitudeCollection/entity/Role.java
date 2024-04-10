package Attitude_Collection.AttitudeCollection.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer roleId;

    @Column(nullable = false, unique = true)
    private String roleName;


//    @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
//    private List<User> userList = new ArrayList<>();



}
