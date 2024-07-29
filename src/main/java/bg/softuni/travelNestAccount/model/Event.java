package bg.softuni.travelNestAccount.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Event extends Attraction {

    private static final String Type = "EVENT";

    @Column(insertable = false, updatable = false)
    private String type;

    @Column(nullable = false, name = "start_date")
    private LocalDate startDate;

    @Column(nullable = false, name = "end_date")
    private LocalDate endDate;

    public Event(String title, CityEntity city, String address, BigDecimal price, String pictureUrl, boolean isPaid, LocalDate startDate, LocalDate endDate) {
        super(title, city, address, price, pictureUrl, isPaid);
        setType(type);
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
