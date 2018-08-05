package com.infamous.service;

import java.util.List;

import com.infamous.model.Song;


public interface MusicManagerService extends Service{
    
    List<Song> findAll();
    
    boolean save(Song song);
    
    boolean delete(Song song);
    
    boolean delete(long id);
}
