package bg.softuni.travelNestAccount.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TicketDTO {

    private String username;

    private int count;

    public TicketDTO setUsername(String username) {
        this.username = username;
        return this;
    }
}
