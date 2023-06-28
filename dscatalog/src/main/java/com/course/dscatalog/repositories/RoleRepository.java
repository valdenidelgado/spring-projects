package com.course.dscatalog.repositories;

import com.course.dscatalog.entities.Role;
import com.course.dscatalog.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
