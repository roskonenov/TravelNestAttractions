package bg.softuni.travelNestAccount.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class AttractionDTO {

    private UUID id;

    private String title;

    private String city;

    private String address;

    private String pictureUrl;

    private BigDecimal price;

    private String description;

    private boolean isPaid;

    private LocalDate startDate;

    private LocalDate endDate;

    private LocalTime time;

    public AttractionDTO setCity(String city) {
        this.city = city;
        return this;
    }
}
