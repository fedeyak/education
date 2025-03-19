package org.example.stringbootapplication.service;

import org.example.stringbootapplication.dto.NewsDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class NewsCRUDService implements CRUDService<NewsDTO>{

    private final ConcurrentHashMap<Long, NewsDTO> newsCollection= new ConcurrentHashMap<>();

    @Override
    public NewsDTO getById(Long id) {
        return newsCollection.get(id);
    }

    @Override
    public Collection<NewsDTO> getAll() {
        return newsCollection.values();
    }

    @Override
    public void create(NewsDTO item) {
        Long newID = Long.valueOf(newsCollection.size() + 1);
        item.setId(newID);
        newsCollection.put(newID, item);
    }

    @Override
    public void update(Long id, NewsDTO item) {
        if(!newsCollection.containsKey(id)) return;
        item.setId(id);
        newsCollection.put(id, item);
    }

    @Override
    public void deleteByID(Long id) {
        newsCollection.remove(id);
    }

    public boolean containsID(Long id) {
        return newsCollection.containsKey(id);
    }
}
