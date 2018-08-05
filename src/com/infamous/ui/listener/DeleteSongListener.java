package com.infamous.ui.listener;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.swt.widgets.Shell;

import com.infamous.data.MusicRepository;
import com.infamous.ui.part.SongPart;
import com.infamous.utils.MessageDialogUtils;

@Creatable
@Singleton
public class DeleteSongListener {

	private MusicRepository musicRepository = MusicRepository.getInstance();
	
	@Inject  
	EPartService partService;
	
	public void delete(Shell parent, long id) {
		boolean resultDialog = MessageDialogUtils.confirm(parent, "Wait a second", "Do you want to delete this song");

		if (!resultDialog) {
			return;
		}
		
		boolean result = musicRepository.delete(id);
		if (result) {
			reloadData(); 
		} else {
			MessageDialogUtils.info(parent, "Failure", "Failure to delete song");
		}
	}
	
	private void reloadData() {
		SongPart songPart = (SongPart) partService.findPart(SongPart.PART_ID).getObject();

		songPart.loadData();
	}

}
