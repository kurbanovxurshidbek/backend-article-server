package com.article.article.repository;


import com.article.article.model.entity.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleTypeRepository extends JpaRepository<RoleType, Long> {

    Optional<RoleType> findByName(String name);
}
