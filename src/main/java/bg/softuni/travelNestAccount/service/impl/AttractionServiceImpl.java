package bg.softuni.travelNestAccount.service.impl;

import bg.softuni.travelNestAccount.exception.ObjectNotFoundException;
import bg.softuni.travelNestAccount.model.dto.AttractionDTO;
import bg.softuni.travelNestAccount.model.dto.TicketDTO;
import bg.softuni.travelNestAccount.model.entity.Ticket;
import bg.softuni.travelNestAccount.repository.AttractionRepository;
import bg.softuni.travelNestAccount.repository.TicketRepository;
import bg.softuni.travelNestAccount.service.AttractionService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AttractionServiceImpl implements AttractionService {
    private final AttractionRepository attractionRepository;
    private final ModelMapper modelMapper;
    private final TicketRepository ticketRepository;

    public AttractionServiceImpl(AttractionRepository attractionRepository, ModelMapper modelMapper, TicketRepository ticketRepository) {
        this.attractionRepository = attractionRepository;
        this.modelMapper = modelMapper;
        this.ticketRepository = ticketRepository;
    }

    @Override
    public List<AttractionDTO> getAllAttractions() {
        return attractionRepository.findAll()
                .stream()
                .filter(attraction -> attraction.getType().equals("Attraction"))
                .map(attraction -> {
                    AttractionDTO map = modelMapper.map(attraction, AttractionDTO.class);
                    map.setCityName(attraction.getCity().getName());
                    return map;
                }).toList();

    }

    @Override
    public AttractionDTO getById(UUID attractionId) {
        return attractionRepository
                .findById(attractionId)
                .map(attraction -> {
                    AttractionDTO map = modelMapper.map(attraction, AttractionDTO.class);
                    map.setCityName(attraction.getCity().getName());
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
}
