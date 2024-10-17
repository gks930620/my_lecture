package entity;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@Setter
public  abstract  class BaseEnntity {
    private LocalDateTime createDateTime;
    private LocalDateTime lastModifiedDateTime;
    //등록한사람,수정한사람 + 기타 등등 가능하지만 일단은 시간만.
}
