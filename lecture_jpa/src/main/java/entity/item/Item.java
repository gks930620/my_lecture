package entity.item;


import entity.BaseEnntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@DiscriminatorColumn(name = "DTYPE")
//@Inheritance(strategy = InheritanceType.JOINED)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public  abstract class Item  extends BaseEnntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name ="item_id")
    private Long id;

    private  String name;
    private  int  price;

    //dtype에는 따로 필드 필요없음


}
