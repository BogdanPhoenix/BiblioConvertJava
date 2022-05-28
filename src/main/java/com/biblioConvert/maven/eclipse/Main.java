package com.biblioConvert.maven.eclipse;

import java.awt.EventQueue;

import com.biblioConvert.maven.eclipse.Class.Function;
import com.biblioConvert.maven.eclipse.Form.StartProgram;

public class Main {
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(Function.systemCheck()) {
						StartProgram window = new StartProgram();
						window.getJFrame().setVisible(true);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
