package com.exercice.cruduser.repositories;

import com.exercice.cruduser.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
