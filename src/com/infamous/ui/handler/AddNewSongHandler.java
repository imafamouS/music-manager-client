package com.infamous.ui.handler;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.eclipse.e4.core.di.annotations.Creatable;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;

import com.infamous.ui.dialog.AddNewSongDialog;
import com.infamous.ui.listener.AddNewSongListener;

@Creatable
@Singleton
public class AddNewSongHandler {
	
	@Inject
	AddNewSongListener addnewSongLisener;

	@Execute
	public void execute(Shell shell) {
		AddNewSongDialog dialog = new AddNewSongDialog(shell);
		dialog.create();
		//Add to database

		if (dialog.open() == Window.OK) {
			String name = dialog.getNameSong();
			addnewSongLisener.add(shell, name);
		}
	}
}
