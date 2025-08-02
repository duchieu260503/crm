package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	// ✅ Replaces deprecated findOne
	Optional<Role> findById(Long id);

	// ✅ Custom method to find role by exact name
	Optional<Role> findByName(String name);

	// Optional: partial name search (e.g., for admin UI filtering)
	List<Role> findByNameContainingIgnoreCase(String keyword);

	void deleteById(Long id);
}
