package Attitude_Collection.AttitudeCollection.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SessionResponse {
    private Integer id;
    private String userName;
    private String img;
}
