package entity;

import jakarta.persistence.*;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

@Entity
//@Table(name = "기본 Entity이름" , schema = "스키마" ,
//        uniqueConstraints = {
//            @UniqueConstraint(name ="name_lengh2" , columnNames ={"name"})
//
//})
public class Member {

    @Id
    //기본키 매핑   1. 직접할당 (userID,주민등록번호처럼 String으로 직접 입력해야하는곳  @GeneratedValue 사용안함.
    //2.AUTO : JPA가 IDENTIY, SEQUENCE, TABLE 중 알아서 선택
    //3. INDENTIY, SEQUENCE, TABLE 각각의 특징은 잘 알아야함. 이건 책 참고
    private String id;
    @Column(nullable = false, length =10 )  //제약조건은 DB에서 만드는게 좋지만, 개발자가 바로 제약조건을 확인할 수 있는 장점이 있다.
    private String name;
    //private String tell;  추가된다면??
    @Deprecated
    private String grade;
    @Enumerated(EnumType.STRING)  // 무조건 String
    private  Authority authority;


    LocalDateTime createDate;

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
    @Deprecated
    public String getGrade() {
        return grade;
    }
    @Deprecated
    public void setGrade(String grade) {
        this.grade = grade;
    }

    public Member() {
    }

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }
}
