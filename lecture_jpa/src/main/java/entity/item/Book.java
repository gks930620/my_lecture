package entity.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@DiscriminatorValue("B")
@Getter
@Setter
public class Book  extends  Item{
    private String author;
    private String isbn;
}
