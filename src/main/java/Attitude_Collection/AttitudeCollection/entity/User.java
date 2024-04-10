package Attitude_Collection.AttitudeCollection.entity;

import Attitude_Collection.AttitudeCollection.Enum.Gender;
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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;

    private String firstName;
    private String lastName;
    private Date dateOfBirth;

    @Column(unique = true,nullable = false)
    private String email;
    private Gender gender;

    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] profile;

//    @JoinColumn
//    @ManyToOne
//    private Role role;


    @JoinColumn
    @OneToOne
    private Login login;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Orders> orderList = new ArrayList<>();

}
