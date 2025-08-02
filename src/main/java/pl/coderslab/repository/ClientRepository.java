package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Client;
import pl.coderslab.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

	Client findByNameIgnoreCase(String name);

	List<Client> findByNameContainingIgnoreCaseOrderByNameAsc(String name);

	List<Client> findByUser(User user);

	List<Client> findByUserOrderByNameAsc(User user);

	List<Client> findByUserInOrderByIdAsc(List<User> users);

	// Search between two dates (inclusive)
	List<Client> findByApproachTimeBetween(LocalDate startDate, LocalDate endDate);

	// Only after a specific date
	List<Client> findByApproachTimeGreaterThanEqual(LocalDate startDate);

	// Only before a specific date
	List<Client> findByApproachTimeLessThanEqual(LocalDate endDate);

	@Query("SELECT c FROM Client c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :clientName, '%')) AND c.user = :user ORDER BY c.name")
	List<Client> findByNameAndUserContainingIgnoreCase(@Param("clientName") String clientName, @Param("user") User user);

	List<Client> findByStatusAndUserOrderByNameAsc(String status, User user);

    List<Client> findByUserOrderByIdAsc(User user);
}
