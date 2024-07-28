package br.com.bookinghub.api.repository;

import br.com.bookinghub.api.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingRepository extends JpaRepository<Room, Long> {
}
