package com.infamous.ui.handler;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.e4.ui.workbench.IWorkbench;
import org.eclipse.swt.widgets.Shell;

import com.infamous.utils.MessageDialogUtils;

public class ExitApplicationHandler {
	@Execute
	public void execute(IWorkbench workbench, Shell shell) {
		if (MessageDialogUtils.confirm(shell, "Confirmation", "Do you want to exit?")) {
			workbench.close();
		}
	}
}
