package com.infamous.utils;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

public class MessageDialogUtils {

	public static void show(int level, Shell parent, String title, String message) {
		MessageDialog.open(level, parent, title, message, 0);
	}
	
	public static void info(Shell parent, String title, String message) {
		MessageDialog.openInformation(parent, title, message);
	}
	
	public static boolean confirm(Shell parent, String title, String message) {
		return MessageDialog.openConfirm(parent, title, message);
	}
}
