package bg.softuni.travelNestAccount.model.entity;

import bg.softuni.travelNestAccount.model.base.BaseEntityUuid;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "city")
public class CityEntity extends BaseEntityUuid {

    @Column(nullable = false)
    private String name;


    public CityEntity(String name) {
        this.name = name;
    }
}
