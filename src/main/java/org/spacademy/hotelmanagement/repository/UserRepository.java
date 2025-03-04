package org.spacademy.hotelmanagement.repository;

import java.io.Serializable;
import java.util.Optional;

import org.spacademy.hotelmanagement.entity.User;
import org.spacademy.hotelmanagement.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
	
	Optional<User> findFirstByEmail(String email);
	Optional<User> findByUserRole(UserRole userRole);
}
