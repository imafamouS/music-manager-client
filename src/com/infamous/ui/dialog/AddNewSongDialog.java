package com.infamous.ui.dialog;

import org.eclipse.jface.dialogs.IMessageProvider;
import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class AddNewSongDialog extends TitleAreaDialog {

	private Text txtNameSong;

	private String nameSong;

	public AddNewSongDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	public void create() {
		super.create();
		setTitle("Add new song Dialog");
		setMessage("Please fill name", IMessageProvider.INFORMATION);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite area = (Composite) super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		container.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));
		GridLayout layout = new GridLayout(2, false);
		container.setLayout(layout);

		createNameSong(container);

		return area;
	}

	private void createNameSong(Composite container) {
		Label lblNameSong = new Label(container, SWT.NONE);
		lblNameSong.setText("Name");

		GridData dataName = new GridData();
		dataName.grabExcessHorizontalSpace = true;
		dataName.horizontalAlignment = GridData.FILL;

		txtNameSong = new Text(container, SWT.BORDER);
		txtNameSong.setLayoutData(dataName);
	}

	@Override
	protected boolean isResizable() {
		return false;
	}

	@Override
	protected void okPressed() {
		nameSong = txtNameSong.getText();

		super.okPressed();
	}

	@Override
	protected Point getInitialSize() {
		return new Point(350, 220);
	}

	public String getNameSong() {
		return nameSong;
	}
}
