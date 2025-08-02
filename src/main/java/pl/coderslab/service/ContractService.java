// ContractService.java (Interface)
package pl.coderslab.service;

import pl.coderslab.entity.*;
import java.time.LocalDate;
import java.util.List;

public interface ContractService {

    Contract findById(Long id);

    void save(Contract contract);

    List<Contract> findByClient(Client client);

    List<Contract> findByAuthor(User user);

    List<String> getStatusOfContract();

    List<String> getProductOfContract();

    void prepareAndSave(Contract contract);
}
