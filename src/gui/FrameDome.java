package gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameDome {
	
	private Frame f;
	private Button but;
	
	FrameDome(){
		init();
	}
	
	public void init(){
		f = new Frame("Frame Dome");
		f.setBounds(300, 200, 500, 400);
		f.setLayout(new FlowLayout());
		
		but = new Button("Exit");
		
		
		f.add(but);
		
		myEvent();
		
		f.setVisible(true);
		
	}
	
	private void myEvent(){
		f.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		//�ð�ť�߱��˳�����Ĺ���
		
		but.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button");
				System.exit(0);
			}
		});
	}
	
	public static void main(String[] args){
		new FrameDome();
	}

}
