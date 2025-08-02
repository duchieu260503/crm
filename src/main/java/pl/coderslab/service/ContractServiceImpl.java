package pl.coderslab.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.log4j.Log4j;
import pl.coderslab.AuthenticationFacade;
import pl.coderslab.entity.*;
import pl.coderslab.repository.ContractRepository;

@Log4j
@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractRepository contractRepository;

    @Override
    public Contract findById(Long id) {
        return contractRepository.findOne(id);
    }

    @Override
    public void save(Contract contract) {
        contractRepository.save(contract);
    }

    @Override
    public List<Contract> findByClient(Client client) {
        return contractRepository.findByClient(client);
    }

    @Override
    public List<Contract> findByAuthor(User user) {
        return contractRepository.findByAuthor(user);
    }

    @Override
    public List<String> getStatusOfContract() {
        return Arrays.asList("Đang xúc tiến", "Đã hợp tác");
    }

    @Override
    public List<String> getProductOfContract() {
        return Arrays.asList("CSKH trọn gói", "DV CSKH", "Reputa", "DV hệ thống IPCC");
    }

    @Override
    public void prepareAndSave(Contract contract) {
        if (contract.getType() == ContractType.IN_PROGRESS) {
            contract.setContractCode(null);
            contract.setSignedDate(null);
            contract.setStartDate(null);
            contract.setEndDate(null);
            contract.setDurationInMonths(null);
            contract.setTotalValue(null);
            contract.setRevenueStatus(null);
            contract.setPakdCode(null);
            contract.setPakdContent(null);
            contract.setLastClient(null);
            contract.setSellingChannel(null);
            contract.setContractValueNoVat(null);
            contract.setVat(null);
            contract.setServicePlatform(null);
            contract.setNote(null);
        } else if (contract.getType() == ContractType.DONE_DEAL) {
            contract.setExpectedValue(null);
            contract.setExpectedLaunch(null);
            contract.setNextPlan(null);
            contract.setDifficulties(null);
            contract.setExpectedSeatsPerMonth(null);
            contract.setExpectedRevenuePerMonth(null);
            contract.setGeneralProgress("100%");
            contract.setStatus("Đã ký");
        }

        save(contract);
    }
}
