package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;

public class FileList {
	
	private Frame f;
	private TextField tf;
	private TextArea ta;
	private Button bu;
	
	private Dialog dialog;
	private Label lab;
	private Button okbut;
	
	FileList(){
		init();
	}
	
	private void init(){
		f = new Frame("List All Files");
		f.setBounds(300, 200, 600, 500);
		f.setLayout(new FlowLayout());
		
		bu = new Button("Submit");
		tf = new TextField(40);
		ta = new TextArea(22, 60);
		
		dialog = new Dialog(f, "Error",	true);
		dialog.setBounds(400, 300, 300, 100);
		dialog.setLayout(new FlowLayout());
		lab = new Label();
		okbut = new Button("OK");
		
		dialog.add(lab);
		dialog.add(okbut);
		
		f.add(tf);
		f.add(bu);
		f.add(ta);
		
		Event();
		
		f.setVisible(true);
	}
	
	private void Event(){
		
		okbut.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dialog.setVisible(false);
			}
		});
		
		
		dialog.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				dialog.setVisible(false);
			}
			
		});
		
		
		f.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		tf.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER){
					showDir();
				}
			}
			
		});
		
		bu.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				showDir();
			}
		});
	}
	
	private void showDir(){
		String dirPath = tf.getText();
		File dir = new File(dirPath);
		if(dir.exists() && dir.isDirectory()){
			ta.setText("");
			
			String[] dirlist = dir.list();
			for(int i = 0; i < dirlist.length; i++){
				ta.append(dirPath + "/" + dirlist[i] + "\r\n");
			}
		}else{
			String info = "Not Found : " + dirPath + ", Please check it!";
			lab.setText(info);
			dialog.setVisible(true);
		}
	}
	
	
	/*
	public class Error{
		
		private Frame f;
		private TextField tf;
		
		Error(){
			init();
		}
		
		private void init(){
			f = new Frame("Error");
			f.setBounds(400, 300, 200, 100);
			
			tf = new TextField();
			
			f.add(tf);
			ErrorEvent();
			f.setVisible(true);
		}
		
		private void ErrorEvent(){
			f.addWindowListener(new WindowAdapter() {

				public void windowClosing(WindowEvent e) {
					System.exit(0);
				}
				
			});
		}
		
	}
	
	*/
	
	public static void main(String[] args){
		new FileList();
	}

}
