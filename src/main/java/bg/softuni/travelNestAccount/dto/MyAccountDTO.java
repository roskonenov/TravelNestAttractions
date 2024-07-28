package bg.softuni.travelNestAccount.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MyAccountDTO {

    private List<PropertyDTO> favoriteHousing;
}
