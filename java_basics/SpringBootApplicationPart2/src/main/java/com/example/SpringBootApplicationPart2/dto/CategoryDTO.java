package com.example.SpringBootApplicationPart2.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class CategoryDTO {
    private Long id;
    private final String category;
    private List<NewsDTO> newsDTO;
}
