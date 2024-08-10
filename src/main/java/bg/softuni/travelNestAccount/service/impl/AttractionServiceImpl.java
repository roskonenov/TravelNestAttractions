package bg.softuni.travelNestAccount.service.impl;

import bg.softuni.travelNestAccount.exception.ObjectNotFoundException;
import bg.softuni.travelNestAccount.model.dto.AttractionDTO;
import bg.softuni.travelNestAccount.model.dto.TicketDTO;
import bg.softuni.travelNestAccount.model.entity.Attraction;
import bg.softuni.travelNestAccount.model.entity.Event;
import bg.softuni.travelNestAccount.model.entity.Ticket;
import bg.softuni.travelNestAccount.repository.AttractionRepository;
import bg.softuni.travelNestAccount.repository.CityRepository;
import bg.softuni.travelNestAccount.repository.TicketRepository;
import bg.softuni.travelNestAccount.service.AttractionService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AttractionServiceImpl implements AttractionService {
    private final AttractionRepository attractionRepository;
    private final ModelMapper modelMapper;
    private final TicketRepository ticketRepository;
    private final CityRepository cityRepository;

    @Override
    public List<AttractionDTO> getAllAttractions(String attractionType) {
        return attractionRepository.findAll()
                .stream()
                .filter(attraction -> attraction.getType().equals(attractionType))
                .map(attraction -> {
                    AttractionDTO map = modelMapper.map(attraction, AttractionDTO.class);
                    map.setCity(attraction.getCity().getName().replaceAll("\\s+", "."));
                    return map;
                }).toList();

    }

    @Override
    public AttractionDTO getById(UUID attractionId) {
        return attractionRepository
                .findById(attractionId)
                .map(attraction -> {
                    AttractionDTO map = modelMapper.map(attraction, AttractionDTO.class);
                    map.setCity(attraction.getCity().getName().replaceAll("\\s+", "."));
                    return map;
                }).orElseThrow(ObjectNotFoundException::new);
    }

    @Override
    public TicketDTO getTickets(UUID attractionId, UserDetails userDetails) {
        List<Ticket> ticketList = ticketRepository.findAllByAttractionId(attractionId)
                .stream()
                .filter(ticket -> ticket.getUsername().equals(userDetails.getUsername()))
                .toList();

        TicketDTO ticketDTO = new TicketDTO();

        ticketList.forEach(ticket -> ticketDTO.setCount(ticketDTO.getCount() + ticket.getCount()));
        return ticketDTO.setUsername(userDetails.getUsername());
    }

    @Override
    public void buyTickets(UUID attractionId, TicketDTO ticketDTO) {
        ticketRepository.saveAndFlush(modelMapper.map(ticketDTO, Ticket.class)
                .setAttraction(attractionRepository.findById(attractionId)
                        .orElseThrow(ObjectNotFoundException::new)));
    }

    @Override
    public AttractionDTO createAttraction(AttractionDTO attractionDTO, String attractionType) {

        Attraction attraction = modelMapper.map(attractionDTO,
                "attraction".equals(attractionType) ?
                        Attraction.class :
                        Event.class);

        Attraction savedAttraction = attractionRepository
                .saveAndFlush(attraction.setCity(
                        cityRepository.findByName(attractionDTO.getCity().replaceAll("\\.", " "))));

        return modelMapper.map(savedAttraction, AttractionDTO.class)
                .setCity(attraction.getCity().getName().replaceAll("\\.", " "));
    }

    @Override
    public void deleteById(UUID attractionId, UserDetails userDetails) {
        if (userDetails.getAuthorities()
                .stream()
                .noneMatch(grantedAuthority -> grantedAuthority
                        .getAuthority()
                        .equals("ROLE_ADMIN"))) return;

        ticketRepository.deleteAll(ticketRepository.findAllByAttractionId(attractionId));
        attractionRepository.deleteById(attractionId);
    }


}
