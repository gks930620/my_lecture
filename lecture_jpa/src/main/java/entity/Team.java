package entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class Team  extends  BaseEnntity{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "team_id")
    private  Long id;

    @Setter
    private String name;

    @OneToMany(mappedBy = "team")
    private List<Member> members=new ArrayList<>();


}
