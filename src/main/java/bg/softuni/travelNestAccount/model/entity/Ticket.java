package bg.softuni.travelNestAccount.model.entity;

import bg.softuni.travelNestAccount.model.base.BaseEntityUuid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tickets")
public class Ticket extends BaseEntityUuid {

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private int count;

    @ManyToOne
    private Attraction attraction;

    public Ticket setAttraction(Attraction attraction) {
        this.attraction = attraction;
        return this;
    }
}
