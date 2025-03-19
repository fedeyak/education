package org.example.stringbootapplication.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NoNewsDTO {
    private String message;

    public NoNewsDTO(Long id) {
        message = "Новость с ID " + id + " не найдена";
    }
}
