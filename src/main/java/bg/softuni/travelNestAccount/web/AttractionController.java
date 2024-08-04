package bg.softuni.travelNestAccount.web;

import bg.softuni.travelNestAccount.model.dto.AttractionDTO;
import bg.softuni.travelNestAccount.model.dto.TicketDTO;
import bg.softuni.travelNestAccount.model.enums.City;
import bg.softuni.travelNestAccount.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/attractions")
public class AttractionController {

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

    @GetMapping("/details/{uuid}")
    public ResponseEntity<AttractionDTO> getAttractionDetails(@PathVariable("uuid") UUID attractionId){

        return ResponseEntity.ok(attractionService.getById(attractionId));
    }

    @GetMapping("/tickets/{uuid}")
    public ResponseEntity<TicketDTO> getTickets(@PathVariable("uuid") UUID attractionId,
                                                @AuthenticationPrincipal UserDetails userDetails){
        return ResponseEntity.ok(attractionService.getTickets(attractionId, userDetails));
    }

    @PostMapping("/details/{uuid}")
    public void buyTickets(@PathVariable("uuid") UUID attractionId,
                                                @RequestBody TicketDTO ticketDTO){

        attractionService.buyTickets(attractionId, ticketDTO);

    }

    @PostMapping("/add")
    public ResponseEntity<AttractionDTO> addAttraction(
            @RequestBody AttractionDTO attractionDTO
    ){
        return ResponseEntity.ok(attractionService.createAttraction(attractionDTO));
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<AttractionDTO> deleteAttraction(@PathVariable("uuid") UUID attractionId,
                                                          @AuthenticationPrincipal UserDetails userDetails){
        attractionService.deleteById(attractionId, userDetails);
       return ResponseEntity
               .noContent()
               .build();
    }
}
