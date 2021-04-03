package bg.softuni.musicWorkshop.models.repositories;

import bg.softuni.musicWorkshop.models.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
