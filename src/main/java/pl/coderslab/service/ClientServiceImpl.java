package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.AuthenticationFacade;
import pl.coderslab.entity.Client;
import pl.coderslab.entity.User;
import pl.coderslab.repository.ClientRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

	@Autowired
	private AuthenticationFacade authenticationFacade;

	@Autowired
	private ClientRepository clientRepository;

	// Find client by ID
	@Override
	public Client findById(Long id) {
		return clientRepository.findOne(id);
	}

	// Find client by name
	@Override
	public Client findByName(String name) {
		return clientRepository.findByNameIgnoreCase(name);
	}

	// Find all clients
	@Override
	public List<Client> findAll() {
		return clientRepository.findAll();
	}

	// Save new client with authenticated user
	@Override
	public Long saveClientWithLoggedUser(Client client) {
		client.setUser(authenticationFacade.getAuthenticatedUser());
		client.setCreated(LocalDate.now());
		clientRepository.save(client);
		return client.getId();
	}

	// Save or update client
	@Override
	public Long saveClient(Client client) {
		client.setCreated(LocalDate.now());
		clientRepository.save(client);
		return client.getId();
	}

	// Delete client
	@Override
	public void deleteClient(Long id) {
		clientRepository.delete(id);
	}

	// Find clients by user
	@Override
	public List<Client> findByUser(User user) {
		return clientRepository.findByUser(user);
	}

	// Dropdown values – these should match your @NotBlank fields
	@Override
	public List<String> getChanceOptions() {
		return Arrays.asList("Tiềm năng", "Chắc chắn", "Rủi ro", "Đạt mục tiêu");
	}

	@Override
	public List<String> getCategoryOptions() {
		return Arrays.asList("Doanh nghiệp","Nội bộ","Chính phủ", "Ngoài");
	}

	@Override
	public List<String> getSourceOptions() {
		return Arrays.asList("Tự nhiên", "Marketing", "Giới thiệu", "Sự kiện", "Khác");
	}

	@Override
	public List<String> getFieldOptions() {
		return Arrays.asList("Y tế", "Giáo dục", "Tài chính", "Viễn thông", "Khác");
	}

	@Override
	public List<String> getStatusOptions(){
		return Arrays.asList("Mới", "Đang xúc tiến", "Đã hợp tác");
	}

	@Override
	public List<Client> searchByDateRange(LocalDate startDate, LocalDate endDate) {
		if (startDate != null && endDate != null) {
			return clientRepository.findByApproachTimeBetween(startDate, endDate);
		} else if (startDate != null) {
			return clientRepository.findByApproachTimeGreaterThanEqual(startDate);
		} else if (endDate != null) {
			return clientRepository.findByApproachTimeLessThanEqual(endDate);
		} else {
			return clientRepository.findAll(); // or return empty list
		}
	}
}
