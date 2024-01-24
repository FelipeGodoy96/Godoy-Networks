package br.com.bookinghub.api.repository;

import br.com.bookinghub.api.model.Housing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingRepository extends JpaRepository<Housing, Long> {
}
