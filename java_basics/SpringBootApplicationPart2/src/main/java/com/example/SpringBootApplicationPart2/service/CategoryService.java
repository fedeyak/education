package com.example.SpringBootApplicationPart2.service;

import com.example.SpringBootApplicationPart2.converter.CategoryConverter;
import com.example.SpringBootApplicationPart2.dto.CategoryDTO;
import com.example.SpringBootApplicationPart2.entity.Category;
import com.example.SpringBootApplicationPart2.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryConverter categoryConverter;

    public CategoryDTO findCategoryByID(Long Id) {
        Category category = categoryRepository.findById(Id).orElseThrow();
        return categoryConverter.mapToCategoryDTO(category);
    }

    public Collection<CategoryDTO> getAllCategories() {
        return categoryRepository.findAll().stream()
                .map(categoryConverter::mapToCategoryDTO)
                .collect(Collectors.toList());
    }

    public void createNewCategory(CategoryDTO categoryDTO) {
        Category category = categoryConverter.mapToCategory(categoryDTO);
        categoryRepository.save(category);
    }


    public void updateCategory(CategoryDTO categoryDTO) {
        Category category = categoryConverter.mapToCategory(categoryDTO);
        categoryRepository.save(category);
    }

    public void deleteCategoryByID(Long Id) {
        categoryRepository.deleteById(Id);
    }

    public boolean containsID(Long id) {
        return categoryRepository.existsById(id);
    }
}
