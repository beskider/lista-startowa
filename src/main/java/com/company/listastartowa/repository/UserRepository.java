package com.company.listastartowa.repository;

import com.company.listastartowa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
