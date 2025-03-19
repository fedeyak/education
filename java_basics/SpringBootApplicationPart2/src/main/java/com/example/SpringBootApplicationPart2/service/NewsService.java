package com.example.SpringBootApplicationPart2.service;

import com.example.SpringBootApplicationPart2.converter.NewsConverter;
import com.example.SpringBootApplicationPart2.dto.NewsDTO;
import com.example.SpringBootApplicationPart2.entity.News;
import com.example.SpringBootApplicationPart2.repository.NewsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NewsService {
    private final NewsRepository newsRepository;
    private final NewsConverter newsConverter;

    public NewsDTO findNewsByID(Long Id) {
        News news = newsRepository.findById(Id).orElseThrow();
        return newsConverter.mapToNewsDTO(news);
    }

    public Collection<NewsDTO> getAllNews() {
        return newsRepository.findAll().stream()
                .map(newsConverter::mapToNewsDTO)
                .collect(Collectors.toList());
    }

    public void createNews(NewsDTO newsDTO) {
        News news = newsConverter.mapToNewsEntity(newsDTO);
        newsRepository.save(news);
    }

    public void updateNews(NewsDTO newsDTO) {
        News news = newsConverter.mapToNewsEntity(newsDTO);
        newsRepository.save(news);
    }

    public void deleteNewsByID(Long Id) {
        newsRepository.deleteById(Id);
    }

    public boolean containsID(Long id) {
        return newsRepository.existsById(id);
    }
}
