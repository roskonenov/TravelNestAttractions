package bg.softuni.travelNestAccount.model;


import bg.softuni.travelNestAccount.model.base.BaseEntityUuid;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "attractions")
@DiscriminatorColumn(name = "type")
public class Attraction extends BaseEntityUuid {

    private static final String Type = "LOCAL_ATTRACTION";

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

    @Column(nullable = false)
    private boolean isPaid;

    public Attraction(String title, CityEntity city, String address, BigDecimal price, String pictureUrl, String description, boolean isPaid) {
        setType(type);
        this.title = title;
        this.city = city;
        this.address = address;
        this.price = price;
        this.pictureUrl = pictureUrl;
        this.description = description;
        this.isPaid = isPaid;
    }
}
