package com.infamous.ui.part;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.e4.ui.di.Focus;
import org.eclipse.e4.ui.di.Persist;
import org.eclipse.e4.ui.model.application.ui.MDirtyable;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.TableItem;

import com.infamous.action.MusicManagerAction;
import com.infamous.data.MusicRepository;
import com.infamous.model.Song;
import com.infamous.ui.listener.DeleteSongListener;

public class SongPart {
	
	public static final String PART_ID = "music-manager-client.part.0";
	
	private TableViewer tableViewer;
	private int lastIndexSelected;

	@Inject
	private MDirtyable dirty;
	
	@Inject
	private DeleteSongListener deleteSongListener;


	private MusicRepository musicRepository = MusicRepository.getInstance();
	

	@PostConstruct
	public void createComposite(Composite parent) {
		parent.setLayout(new GridLayout(1, false));

		tableViewer = new TableViewer(parent);

		tableViewer.getTable().setLayoutData(new GridData(GridData.FILL_BOTH));

		loadData();
		
		tableViewer.addDoubleClickListener(new IDoubleClickListener() {
			@Override
			public void doubleClick(DoubleClickEvent event) {
				lastIndexSelected = tableViewer.getTable().getSelectionIndex();
				long id = (long) tableViewer.getTable().getItem(lastIndexSelected).getData("id");
				deleteSongListener.delete(parent.getShell(), id);
			}
		});
	}
	


	@Focus
	public void setFocus() {
		tableViewer.getTable().setFocus();
	}

	@Persist
	public void save() {
		dirty.setDirty(false);
	}
	
	public void removeAtLastIndexSelected() {
		tableViewer.getTable().remove(lastIndexSelected);
	}
	
	public void loadData() {
		tableViewer.getTable().removeAll();
		Job job = new Job(MusicManagerAction.FIND_ALL.getId()) {

			@Override
			protected IStatus run(IProgressMonitor arg0) {
				List<Song> songs = musicRepository.findAll();
				Display.getDefault().asyncExec(new Runnable() {
					@Override
					public void run() {
						for (Song song : songs) {
							TableItem item = new TableItem(tableViewer.getTable(), SWT.VIRTUAL);
							item.setData("id", song.getId());
							item.setText(song.getName());
						}
					}
				});
				return Status.OK_STATUS;
			}
		};

		job.setUser(true);
		job.schedule();
	}
}
