package com.infamous.data;

import java.util.Collections;
import java.util.List;

import com.infamous.data.connector.IRMIConnector;
import com.infamous.data.connector.RMIConnector;
import com.infamous.logging.Log;
import com.infamous.model.Song;
import com.infamous.service.MusicManagerService;

public class MusicRepository extends DataRepository<MusicManagerService> implements IMusicRepository {

	private static volatile MusicRepository sInstance;

	public static final String MBEAN_NAME = "com.infamous:name=musicManagerService";

	private MusicRepository(IRMIConnector connector) {
		this.connector = connector;
		try {
			this.connector.open();
			this.service = connector.getBean(MusicManagerService.class, MBEAN_NAME, true);
		} catch (Exception ex) {
			Log.i("Failure to init MusicRepository");
			Log.e(ex.getMessage(), ex);
		}
	}

	public static synchronized MusicRepository getInstance() {
		if (sInstance == null) {
			synchronized (MusicRepository.class) {
				sInstance = new MusicRepository(RMIConnector.getInstance());
			}
		}

		return sInstance;
	}

	@Override
	public List<Song> findAll() {
		
		Log.i("Get songs");
		if(this.service == null) {
			return Collections.emptyList();
		}
		return this.service.findAll();
	}

	@Override
	public boolean save(Song song) {
		Log.i("Save song");
		if(this.service == null) {
			return false;
		}
		return this.service.save(song);
	}

	@Override
	public boolean delete(Song song) {
		Log.i("Delete song");
		if(this.service == null) {
			return false;
		}
		return this.service.delete(song);
	}

	@Override
	public boolean delete(long id) {
		Log.i("Delete song by id");
		if(this.service == null) {
			return false;
		}
		return this.service.delete(id);
	}

}
