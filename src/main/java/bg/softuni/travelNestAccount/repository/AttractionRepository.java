package bg.softuni.travelNestAccount.repository;

import bg.softuni.travelNestAccount.model.Attraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AttractionRepository extends JpaRepository<Attraction, UUID> {
}
