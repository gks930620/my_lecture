package entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Member  extends  BaseEnntity{
    @Id
    @Column(name = "member_id")
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)   //즉시로딩할 때 fetch추가 , 지연로딩=LAZY
    @JoinColumn(name = "team_id")
    private Team team;


    @Column(nullable = false, length =10 )
    private String name;
    @Deprecated
    private String grade;


    @Enumerated(EnumType.STRING)
    private  Authority authority;
    LocalDateTime createDate;


    @Embedded
    private Address address;


    public void setTeam(Team team){  //연관관계 편의 메소드
        if(this.team!=null){
            this.team.getMembers().remove(this);
        }
        this.team=team;
        team.getMembers().add(this);
    }

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }


}
