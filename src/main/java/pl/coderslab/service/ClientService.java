package pl.coderslab.service;

import pl.coderslab.entity.Client;
import pl.coderslab.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface ClientService {

	/**
	 * Finds a client by its database ID.
	 * @param id The client ID.
	 * @return The corresponding Client object or null if not found.
	 */
	Client findById(Long id);

	/**
	 * Finds a client by its name.
	 * @param name The name of the client.
	 * @return The Client object, or null if not found.
	 */
	Client findByName(String name);

	/**
	 * Retrieves all clients from the system.
	 * @return List of all Client objects.
	 */
	List<Client> findAll();

	/**
	 * Saves a new or updated client.
	 * @param client The client to be saved.
	 * @return The generated ID of the saved client.
	 */
	Long saveClient(Client client);

	/**
	 * Deletes a client by ID.
	 * @param id The ID of the client to delete.
	 */
	void deleteClient(Long id);

	/**
	 * Saves the client and assigns the currently authenticated user as its owner.
	 * @param client The client to save.
	 * @return The saved client ID.
	 */
	Long saveClientWithLoggedUser(Client client);

	/**
	 * Finds clients assigned to a specific user.
	 * @param user The user who manages the clients.
	 * @return List of Clients.
	 */
	List<Client> findByUser(User user);

	// ======= Static Options for Dropdown Filters ======= //

	/**
	 * Returns possible values for project "chance" ratings.
	 * @return List of chance evaluation options.
	 */
	List<String> getChanceOptions();

	/**
	 * Returns possible category types (e.g., "Nội bộ", "Ngoại").
	 * @return List of categories.
	 */
	List<String> getCategoryOptions();

	/**
	 * Returns known sources for how the client was approached.
	 * @return List of client sources.
	 */
	List<String> getSourceOptions();

	/**
	 * Returns possible fields of operation.
	 * @return List of sectors.
	 */
	List<String> getFieldOptions();

	List<String> getStatusOptions();

	List<Client> searchByDateRange(LocalDate startDate, LocalDate endDate);
}
