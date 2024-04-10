package Attitude_Collection.AttitudeCollection.response;

import Attitude_Collection.AttitudeCollection.Enum.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.nio.file.Path;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDtlResponse {


    private String productId;
    private String productName;
    private Integer productQuantity;
    private Integer price;
    private Status status;
    private String description;
    private Integer rating;
    private Path image;
    private String subcategoryName;
    private String categoryName;

}
