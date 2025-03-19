package com.example.SpringBootApplicationPart2.converter;

import com.example.SpringBootApplicationPart2.dto.CategoryDTO;
import com.example.SpringBootApplicationPart2.dto.NewsDTO;
import com.example.SpringBootApplicationPart2.entity.Category;
import com.example.SpringBootApplicationPart2.entity.News;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategoryConverter {

    private final NewsConverter newsConverter;

    public CategoryDTO mapToCategoryDTO(Category category) {
        Long id = category.getId();
        String categ = category.getCategory();
        List<NewsDTO> newsDTO = category.getNews().stream()
                .map(newsConverter::mapToNewsDTO)
                .toList();
        return new CategoryDTO(id, categ, newsDTO);
    }

    public Category mapToCategory(CategoryDTO categoryDTO) {
        Long id = categoryDTO.getId();
        String category = categoryDTO.getCategory();
        List<News> news = categoryDTO.getNewsDTO().stream()
                .map(newsConverter::mapToNewsEntity)
                .toList();
        return new Category(id, category, news);
    }
}
