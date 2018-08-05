package com.infamous.ui.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.swt.widgets.Shell;

import com.infamous.utils.MessageDialogUtils;

public class AboutHandler {
	
	@Execute
	public void execute(IWorkbench workbench, Shell shell) {
		MessageDialogUtils.info(shell, "About", "Made by l m a o");
	}
}
