package com.example.SpringBootApplicationPart2.converter;

import com.example.SpringBootApplicationPart2.dto.NewsDTO;
import com.example.SpringBootApplicationPart2.entity.Category;
import com.example.SpringBootApplicationPart2.entity.News;
import com.example.SpringBootApplicationPart2.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.Instant;

@Component
@RequiredArgsConstructor
public class NewsConverter {

    private final CategoryRepository categoryRepository;

    public NewsDTO mapToNewsDTO(News news) {
        Long id = news.getId();
        Long categoryId = news.getCategory().getId();
        String title = news.getTitle();
        String text = news.getText();
        return new NewsDTO(id, categoryId, title, text);
    }

    public News mapToNewsEntity(NewsDTO newsDTO) {
        Long id = newsDTO.getId();
        Long categoryId = newsDTO.getCategoryId();
        Category category = categoryRepository.findById(categoryId).orElseThrow();
        String title = newsDTO.getTitle();
        String text = newsDTO.getText();
        return new News(id, category, title, text, Instant.now());
    }
}
