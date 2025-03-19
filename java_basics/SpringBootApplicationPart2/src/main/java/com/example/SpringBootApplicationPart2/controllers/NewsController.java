package com.example.SpringBootApplicationPart2.controllers;

import com.example.SpringBootApplicationPart2.dto.NewsDTO;
import com.example.SpringBootApplicationPart2.dto.NoNewsDTO;
import com.example.SpringBootApplicationPart2.service.NewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/news")
@RequiredArgsConstructor
public class NewsController {

    private final NewsService newsService;

    @GetMapping("/{Id}")
    public ResponseEntity getNewsById(@PathVariable Long Id) {
        if (!newsService.containsID(Id)) {
            return new ResponseEntity(new NoNewsDTO(Id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(newsService.findNewsByID(Id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAllNews() {
        return new ResponseEntity(newsService.getAllNews(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createNews(@RequestBody NewsDTO newsDTO) {
        if (newsDTO == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        newsService.createNews(newsDTO);
        return new ResponseEntity(newsDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{Id}")
    public ResponseEntity updateNews(@PathVariable Long Id, @RequestBody NewsDTO newsDTO) {
        if (!newsService.containsID(Id)) {
            return new ResponseEntity(new NoNewsDTO(Id), HttpStatus.NOT_FOUND);
        } else if (newsDTO == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        newsDTO.setId(Id);
        newsService.updateNews(newsDTO);
        return new ResponseEntity(newsDTO, HttpStatus.OK);
    }

    @DeleteMapping("/{Id}")
    public ResponseEntity deleteNewsByID(@PathVariable Long Id) {
        if(!newsService.containsID(Id)) {
            return new ResponseEntity(new NoNewsDTO(Id), HttpStatus.NOT_FOUND);
        }
        newsService.deleteNewsByID(Id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
