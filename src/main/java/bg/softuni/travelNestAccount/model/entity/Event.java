package bg.softuni.travelNestAccount.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@DiscriminatorValue(value = "event")
public class Event extends Attraction {

    @Column(insertable = false, updatable = false)
    private String type;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column
    private LocalTime time;

    public Event(
            String title,
            CityEntity city,
            String address,
            BigDecimal price,
            String pictureUrl,
            String description,
            boolean isPaid,
            LocalDate startDate,
            LocalDate endDate,
            LocalTime time
    ) {
        super(title, city, address, price, pictureUrl, description, isPaid);
        this.startDate = startDate;
        this.endDate = endDate;
        this.time = time;
    }

    public String getType() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }
}
