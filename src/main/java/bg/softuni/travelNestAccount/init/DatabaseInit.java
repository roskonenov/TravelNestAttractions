package bg.softuni.travelNestAccount.init;

import bg.softuni.travelNestAccount.model.entity.Attraction;
import bg.softuni.travelNestAccount.model.entity.CityEntity;
import bg.softuni.travelNestAccount.model.entity.Event;
import bg.softuni.travelNestAccount.model.enums.City;
import bg.softuni.travelNestAccount.repository.AttractionRepository;
import bg.softuni.travelNestAccount.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.swing.text.DateFormatter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DatabaseInit implements CommandLineRunner {

    private static final String ATTRACTIONS_INPUT_FILE_PATH = "src/main/resources/attractions.txt";
    
    private static final String EVENTS_INPUT_FILE_PATH = "src/main/resources/events.txt";
    
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInit.class);

    private final CityRepository cityRepository;

    private final PasswordEncoder passwordEncoder;
    private final AttractionRepository attractionRepository;


    @Override
    public void run(String... args) throws IOException {
        cityInit();
//        rolesInit();
//        usersInit();
        attractionsInit();
        eventsInit();
        LOGGER.info("DATABASE INITIATED!");
    }

    private void cityInit() {
        if (cityRepository.count() != Arrays.stream(City.values()).count()) {

            Arrays.stream(City.values())
                    .map(City::toString)
                    .filter(cityName -> !cityRepository.findAll()
                            .stream()
                            .map(CityEntity::getName)
                            .toList().contains(cityName))
                    .forEach(newCity -> cityRepository.saveAndFlush(new CityEntity(newCity)) );
        }
    }
//
//    private void rolesInit() {
//        if (roleRepository.count() != Arrays.stream(RoleEnum.values()).count()) {
//
//                    Arrays.stream(RoleEnum.values())
//                            .map(RoleEnum::toString)
//                            .filter(role -> !roleRepository.findAll()
//                                    .stream()
//                                    .map(Role::getRole)
//                                    .map(RoleEnum::toString)
//                                    .toList().contains(role))
//                            .forEach(role -> roleRepository.saveAndFlush(new Role(RoleEnum.valueOf(role))));
//        }
//    }
//
//    private void usersInit() {
//        if (userRepository.count() != 0) return;
//
//        userRepository.saveAllAndFlush(List.of(
//                new User("roskonenov",
//                        "roskonenov@gmail.com",
//                        passwordEncoder.encode(initConfig.getPassword()))
//                        .setRoles(List.of(roleRepository.findByRole(RoleEnum.ADMIN))),
//
//                new User("elena_jelyazkova",
//                        "elena_jelyazkova@gmail.com",
//                        passwordEncoder.encode(initConfig.getPassword()))
//                        .setRoles(List.of(roleRepository.findByRole(RoleEnum.USER))),
//
//                new User("georgi79",
//                        "georgi79@gmail.com",
//                        passwordEncoder.encode(initConfig.getPassword()))
//                        .setRoles(List.of(roleRepository.findByRole(RoleEnum.USER))))
//        );
//    }
//
    private void attractionsInit() throws IOException {
        if (attractionRepository.count() != 0) return;

        Files.readAllLines(Path.of(ATTRACTIONS_INPUT_FILE_PATH))
                .forEach(line -> {
                    String[] fields = line.split("\\s+");
                    attractionRepository.saveAndFlush(createAttractionEntity(fields));
                });
    }

    private void eventsInit() throws IOException {
        if (attractionRepository.count() !=
        Files.readAllLines(Path.of(ATTRACTIONS_INPUT_FILE_PATH)).size()) return;

        Files.readAllLines(Path.of(EVENTS_INPUT_FILE_PATH))
                .forEach(line -> {
                    String[] fields = line.split("\\s+");
                    attractionRepository.saveAndFlush(createEventEntity(fields));
                });
    }

    private Attraction createAttractionEntity(String[] fields) {
        return new Attraction(
                getProperString(fields[0]),
                cityRepository.findByName(getProperString(fields[1])),
                getProperString(fields[2]),
                fields[3].equals("null") ? null :
                BigDecimal.valueOf(Integer.parseInt(fields[3])),
                fields[4],
                getProperString(fields[5]),
                Boolean.parseBoolean(fields[6])
        );
    }

    private Attraction createEventEntity(String[] fields) {
        return new Event(
                getProperString(fields[0]),
                cityRepository.findByName(getProperString(fields[1])),
                getProperString(fields[2]),
                fields[3].equals("null") ? null :
                        BigDecimal.valueOf(Integer.parseInt(fields[3])),
                fields[4],
                getProperString(fields[5]),
                Boolean.parseBoolean(fields[6]),
                LocalDate.parse(fields[7], DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalDate.parse(fields[8], DateTimeFormatter.ofPattern("dd/MM/yyyy")),
                LocalTime.parse(fields[9], DateTimeFormatter.ofPattern("HH:mm"))
        );
    }

    private static String getProperString(String text) {
        return text.replaceAll("_", " ");
    }
}
