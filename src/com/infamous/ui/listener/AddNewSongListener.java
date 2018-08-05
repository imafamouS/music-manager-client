package com.infamous.ui.listener;

import java.util.Date;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.Shell;

import com.infamous.data.MusicRepository;
import com.infamous.model.Song;
import com.infamous.ui.part.SongPart;
import com.infamous.utils.MessageDialogUtils;
import com.infamous.utils.TextUtils;

@Creatable
@Singleton
public class AddNewSongListener {

	MusicRepository musicRepository = MusicRepository.getInstance();
	
	@Inject  
	EPartService partService;

	public void add(Shell parent, String name) {

		boolean isValid = validate(name);

		if (!isValid) {
			MessageDialogUtils.info(parent, "Failure", "Name can not be empty");
			return;
		}
		
		Song song = new Song(name, new Date().toString());

		boolean result = musicRepository.save(song);

		if (result) {
			reloadData();
		} else {
			MessageDialogUtils.info(parent, "Failure", "Failure to save song");
		}
	}

	private boolean validate(String name) {
		return !TextUtils.isEmpty(name);
	}
	
	private void reloadData() {
		SongPart songPart = (SongPart) partService.findPart(SongPart.PART_ID).getObject();
		songPart.loadData();
	}
}
