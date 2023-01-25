package my.pledge.ticket.prolongation.repo;

import my.pledge.ticket.prolongation.models.User;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepo extends CrudRepository<User,Long> {
}
