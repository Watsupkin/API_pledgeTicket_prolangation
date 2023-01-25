package my.pledge.ticket.prolongation.repo;

import my.pledge.ticket.prolongation.models.Ticket;
import org.springframework.data.repository.CrudRepository;

public interface TicketsRepo extends CrudRepository<Ticket,Long> {
}
