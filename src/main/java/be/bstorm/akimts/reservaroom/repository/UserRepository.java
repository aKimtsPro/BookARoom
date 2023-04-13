package be.bstorm.akimts.reservaroom.repository;

import be.bstorm.akimts.reservaroom.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
