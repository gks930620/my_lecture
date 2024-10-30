package entity;


import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
//@Setter  //r금지
@AllArgsConstructor
@NoArgsConstructor //기본적으로 필요..
public class Address {

    private String city;
    private String street;
    private  String zipcode;

}
