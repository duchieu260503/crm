package pl.coderslab.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pl.coderslab.entity.Client;
import pl.coderslab.entity.Contract;
import pl.coderslab.entity.User;
import pl.coderslab.entity.ContractType;

@Repository
public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findByClient(Client client);

    List<Contract> findByAcceptedBy(User user);

    List<Contract> findByAuthor(User user);

    List<Contract> findByAuthorInAndTypeOrderByIdAsc(List<User> bySupervisor, ContractType type);

    List<Contract> findByTypeAndAuthor(ContractType type, User user);

    List<Contract> findByAuthorAndTypeOrderByIdAsc(User user, ContractType contractType);

    List<Contract> findByType(ContractType contractType);

    List<Contract> findByAuthorInOrderByCreatedAsc(List<User> bySupervisor);

    List<Contract> findByAuthorOrderByCreatedAsc(User user);

    List<Contract> findByAuthorAndClient_NameContainingIgnoreCase(User user, String name);

	List<Contract> findByAuthorInAndClient_NameContainingIgnoreCase(List<User> bySupervisor, String name);

    List<Contract> findByAuthorInAndClient_ApproachTimeGreaterThanEqualAndClient_ApproachTimeLessThanEqualOrderByClient_ApproachTimeAsc(List<User> bySupervisor, LocalDate startDate, LocalDate endDate);

    List<Contract> findByAuthorAndClient_ApproachTimeGreaterThanEqualAndClient_ApproachTimeLessThanEqualOrderByClient_ApproachTimeAsc(User user, LocalDate startDate, LocalDate endDate);

    List<Contract> findByAuthorInAndClient_ApproachTimeGreaterThanEqualOrderByClient_ApproachTimeAsc(List<User> bySupervisor, LocalDate startDate);

    List<Contract> findByAuthorAndClient_ApproachTimeGreaterThanEqualOrderByClient_ApproachTimeAsc(User user, LocalDate startDate);

    List<Contract> findByAuthorInAndClient_ApproachTimeLessThanEqualOrderByClient_ApproachTimeAsc(List<User> bySupervisor, LocalDate endDate);

    List<Contract> findByAuthorAndClient_ApproachTimeLessThanEqualOrderByClient_ApproachTimeAsc(User user, LocalDate endDate);
}
