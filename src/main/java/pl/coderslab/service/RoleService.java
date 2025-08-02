package pl.coderslab.service;

import pl.coderslab.entity.Role;

import java.util.List;

public interface RoleService {

    // ✅ Get all roles (e.g., for dropdowns)
    List<Role> getAllRoles();

    // ✅ Get one by ID
    Role getRoleById(Long id);

    // ✅ Get one by name (useful for security or seeding)
    Role getRoleByName(String name);

    // Optional: create role if your admin panel supports this
    Role createRole(Role role);

    // Optional: update role if editable
    Role updateRole(Long id, Role role);

    // Optional: delete role (be careful if it's linked to users)
    void deleteRole(Long id);
}
