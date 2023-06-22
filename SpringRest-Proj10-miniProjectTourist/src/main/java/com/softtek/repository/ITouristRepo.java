package com.softtek.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.softtek.entity.Tourist;

public interface ITouristRepo extends JpaRepository<Tourist, Integer> {

}
