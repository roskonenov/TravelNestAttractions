package bg.softuni.travelNestAccount.service;

import bg.softuni.travelNestAccount.model.dto.AttractionDTO;
import bg.softuni.travelNestAccount.model.dto.TicketDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.UUID;

public interface AttractionService {

    List<AttractionDTO> getAllAttractions();

    AttractionDTO getById(UUID attractionId);

    TicketDTO getTickets(UUID attractionId, UserDetails userDetails);

    void buyTickets(UUID attractionId, TicketDTO ticketDTO);

    AttractionDTO createAttraction(AttractionDTO attractionDTO);

    void deleteById(UUID attractionId, UserDetails userDetails);
}
