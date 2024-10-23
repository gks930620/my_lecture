package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
@ToString(exclude = "members")   //순환참조방지
public class Team  extends  BaseEnntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private  Long id;

    @Setter
    private String name;

    @OneToMany(mappedBy = "team" , fetch = FetchType.LAZY) //기본이 LAZY만 확실하게..
    private List<Member> members=new ArrayList<>();


}
