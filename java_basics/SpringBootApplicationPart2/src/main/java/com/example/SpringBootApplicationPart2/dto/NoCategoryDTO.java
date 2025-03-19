package com.example.SpringBootApplicationPart2.dto;

import lombok.Getter;
import lombok.Setter;

public class NoCategoryDTO {
    @Getter
    @Setter
    private String message;

    public NoCategoryDTO(Long id) {
        message = "Категория с ID " + id + " не найдена";
    }
}
