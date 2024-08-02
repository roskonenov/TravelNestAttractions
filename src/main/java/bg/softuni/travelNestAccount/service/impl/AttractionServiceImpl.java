package bg.softuni.travelNestAccount.service.impl;

import bg.softuni.travelNestAccount.dto.AttractionDTO;
import bg.softuni.travelNestAccount.repository.AttractionRepository;
import bg.softuni.travelNestAccount.service.AttractionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttractionServiceImpl implements AttractionService {
    private final AttractionRepository attractionRepository;
    private final ModelMapper modelMapper;

    public AttractionServiceImpl(AttractionRepository attractionRepository, ModelMapper modelMapper) {
        this.attractionRepository = attractionRepository;
        this.modelMapper = modelMapper;
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
}
