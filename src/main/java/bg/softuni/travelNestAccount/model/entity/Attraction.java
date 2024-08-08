package bg.softuni.travelNestAccount.model.entity;


import bg.softuni.travelNestAccount.model.base.BaseEntityUuid;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "attractions")
@DiscriminatorColumn(name = "type")
@DiscriminatorValue(value = "attraction")
public class Attraction extends BaseEntityUuid {

    @Column(insertable = false, updatable = false)
    private String type;

    @Column(nullable = false)
    private String title;

    @ManyToOne
    @JoinColumn(name = "city_id", referencedColumnName = "id", nullable = false)
    private CityEntity city;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String address;

    private BigDecimal price;

    @Column(name = "picture_url", columnDefinition = "TEXT", nullable = false)
    private String pictureUrl;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "is_paid", nullable = false)
    private boolean isPaid;

    @OneToMany(mappedBy = "attraction")
    private Set<Ticket> tickets;

    public Attraction(
            String title,
            CityEntity city,
            String address,
            BigDecimal price,
            String pictureUrl,
            String description,
            boolean isPaid
    ) {
        this.title = title;
        this.city = city;
        this.address = address;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.isPaid = isPaid;
        this.tickets = new HashSet<>();
    }

    public Attraction setCity(CityEntity city) {
        this.city = city;
        return this;
    }

    public String getType() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }
}
