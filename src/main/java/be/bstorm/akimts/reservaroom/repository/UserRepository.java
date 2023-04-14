package be.bstorm.akimts.reservaroom.repository;

import be.bstorm.akimts.reservaroom.models.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

//    @Query("SELECT u from User u WHERE u.username = :username")
    Optional<User> findByUsername(String username);

}
