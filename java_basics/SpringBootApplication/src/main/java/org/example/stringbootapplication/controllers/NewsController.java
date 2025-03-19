package org.example.stringbootapplication.controllers;//package controllers;

import lombok.AllArgsConstructor;
import org.example.stringbootapplication.dto.NewsDTO;
import org.example.stringbootapplication.dto.NoNewsDTO;
import org.example.stringbootapplication.service.NewsCRUDService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/news")
@AllArgsConstructor
public class NewsController {

    private final NewsCRUDService newsService = new NewsCRUDService();

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id) {
        if (!newsService.containsID(id)) {
            return new ResponseEntity(new NoNewsDTO(id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(newsService.getById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity getAll() {
        return new ResponseEntity(newsService.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody NewsDTO item) {
        if (item == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        newsService.create(item);
        return new ResponseEntity(item, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable Long id, @RequestBody NewsDTO item) {
        if(!newsService.containsID(id)) {
            return new ResponseEntity(new NoNewsDTO(id), HttpStatus.NOT_FOUND);
        } else if (item == null) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        newsService.update(id, item);
        return new ResponseEntity(item, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteByID(@PathVariable Long id) {
        if(!newsService.containsID(id)) {
            return new ResponseEntity(new NoNewsDTO(id), HttpStatus.NOT_FOUND);
        }
        newsService.deleteByID(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
