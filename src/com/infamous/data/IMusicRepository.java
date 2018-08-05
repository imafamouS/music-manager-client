package com.infamous.data;

import java.util.List;

import com.infamous.model.Song;

public interface IMusicRepository {
	List<Song> findAll();

	boolean save(Song song);

	boolean delete(Song song);

	boolean delete(long id);
}
