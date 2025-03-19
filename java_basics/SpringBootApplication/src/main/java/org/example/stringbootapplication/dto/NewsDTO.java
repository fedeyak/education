package org.example.stringbootapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
@Getter
@Setter
@AllArgsConstructor
public class NewsDTO {
    private Long id;
    private final String title;
    private final String text;
    private final Instant date = Instant.now();

//    public boolean isValid() {
//        return title != null || text != null || date != null;
//    }

}
