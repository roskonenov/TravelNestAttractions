package bg.softuni.travelNestAccount.repository;

import bg.softuni.travelNestAccount.model.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    List<Ticket> findAllByAttractionId(UUID attractionId);
}
