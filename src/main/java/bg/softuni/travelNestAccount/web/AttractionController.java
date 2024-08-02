package bg.softuni.travelNestAccount.web;

import bg.softuni.travelNestAccount.dto.AttractionDTO;
import bg.softuni.travelNestAccount.model.enums.City;
import bg.softuni.travelNestAccount.repository.CityRepository;
import bg.softuni.travelNestAccount.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("attractions")
public class AttractionController {

    private final CityRepository cityRepository;
    private final AttractionService attractionService;


    @GetMapping("/cities")
    public ResponseEntity<List<String>> getCities(){
        return ResponseEntity.ok(Arrays.stream(City.values())
                .map(City::toString)
                .toList());
    }

    @GetMapping("/list")
    public ResponseEntity<List<AttractionDTO>> getAttractions(){
        return ResponseEntity.ok(attractionService.getAllAttractions());
    }
}
