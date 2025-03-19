package com.example.SpringBootApplicationPart2.repository;

import com.example.SpringBootApplicationPart2.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsRepository extends JpaRepository<News, Long> {

}
