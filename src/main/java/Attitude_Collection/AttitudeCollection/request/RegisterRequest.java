package Attitude_Collection.AttitudeCollection.request;

import Attitude_Collection.AttitudeCollection.Enum.Gender;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String firstName;
    private String lastName;
    private String email;

    private String dateOfBirth;
    private Gender gender;
   private String profile;

}
