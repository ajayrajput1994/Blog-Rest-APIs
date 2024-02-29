package com.bloging.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bloging.entity.UserActivities;
@Repository
public interface UserActiveRepository extends JpaRepository<UserActivities, UUID>{

}
