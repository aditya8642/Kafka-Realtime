package com.consumer.database.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.consumer.database.entity.WikimediaData;

@Repository
public interface WikimediaRepository extends JpaRepository<WikimediaData, Long>{

}
