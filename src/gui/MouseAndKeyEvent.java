package gui;

import java.awt.*;
import java.awt.event.*;


public class MouseAndKeyEvent {
	
	private Frame f;
	private Button but1;
	private Button but2;
	
	MouseAndKeyEvent(){
		init();
	}
	
	public void init(){
		f = new Frame("Frame Dome");
		f.setBounds(300, 200, 500, 400);
		f.setLayout(new FlowLayout());
		
		but1 = new Button("Exit");
		but2 = new Button("Submit");
		
		f.add(but1);
		f.add(but2);
		
		myEvent();
		
		f.setVisible(true);
		
	}
	
	private void myEvent(){
		f.addWindowListener(new WindowAdapter() {

			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		but1.addKeyListener(new KeyAdapter() {

			public void keyPressed(KeyEvent e) {
				System.out.println(e.getKeyChar() + "......." + e.getKeyCode());
				//System.out.println(e.getKeyCode());
			}
			
		});
		
		/*
		//让按钮具备退出程序的功能
		
		but1.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button");
				System.exit(0);
			}
		});
		
		but2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				System.out.println("Action ok");
			}
		});
		
		but2.addMouseListener(new MouseAdapter() {

			private int counter = 1;
			public void mouseEntered(MouseEvent e) {
				System.out.println("Enter" + counter++);
			}
			
			private int clickcount = 1; 
			public void mouseClicked(MouseEvent e) {
				if(e.getClickCount() == 2){
					System.out.println("Double click");
				}else if(e.getClickCount() == 1){
					System.out.println("Click" + clickcount++);
				}
			}
			
		});
		*/
	}
	
	
	
	public static void main(String[] args){
		new MouseAndKeyEvent();
	}

}
