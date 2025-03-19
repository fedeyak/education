package com.example.SpringBootApplicationPart2.controllers;

import com.example.SpringBootApplicationPart2.dto.CategoryDTO;
import com.example.SpringBootApplicationPart2.dto.NoCategoryDTO;
import com.example.SpringBootApplicationPart2.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/category")
@RequiredArgsConstructor
public class  CategoryController {

    private final CategoryService categoryService;

    @GetMapping("/{Id}")
    public ResponseEntity getCategoryById(@PathVariable Long Id) {
        if (!categoryService.containsID(Id)) {
            return new ResponseEntity(new NoCategoryDTO(Id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(categoryService.findCategoryByID(Id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllCategory() {
        return new ResponseEntity(categoryService.getAllCategories(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createCategory(@RequestBody CategoryDTO CategoryDTO) {
        if (CategoryDTO == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        categoryService.createNewCategory(CategoryDTO);
        return new ResponseEntity(CategoryDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{Id}")
    public ResponseEntity updateCategory(@PathVariable Long Id, @RequestBody CategoryDTO CategoryDTO) {
        if (!categoryService.containsID(Id)) {
            return new ResponseEntity(new NoCategoryDTO(Id), HttpStatus.NOT_FOUND);
        } else if (CategoryDTO == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        CategoryDTO.setId(Id);
        categoryService.updateCategory(CategoryDTO);
        return new ResponseEntity(CategoryDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity deleteCategoryByID(@PathVariable Long Id) {
        if(!categoryService.containsID(Id)) {
            return new ResponseEntity(new NoCategoryDTO(Id), HttpStatus.NOT_FOUND);
        }
        categoryService.deleteCategoryByID(Id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    
}
