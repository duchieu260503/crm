package pl.coderslab.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import pl.coderslab.entity.Address;
import pl.coderslab.entity.Client;
import pl.coderslab.service.ClientService;
import pl.coderslab.service.UserService;

@Component
public class ClientMapper {

	@Autowired
	private UserService userService;
	@Autowired
	private ClientService clientService;

	public ClientDto toDto(Client client) {
		ClientDto clientDto = new ClientDto();

		clientDto.setName(client.getName());
		clientDto.setUserEmail(client.getUser().getEmail());

		return clientDto;
	}

	public Client toEntity(ClientDto clientDto) {
		Client client = new Client();

		client.setName(clientDto.getName());
		Address address = new Address();
		address.setCountry(clientDto.getCountry());
		address.setRegion(clientDto.getRegion());
		address.setCity(clientDto.getCity());
		address.setStreet(clientDto.getStreet());

		try {
			client.setUser(userService.findByEmail(clientDto.getUserEmail()));
		} catch (NullPointerException e) {
			e.getMessage();
			e.printStackTrace();
		}

		return client;
	}

}
