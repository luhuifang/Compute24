package gui;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AwtDome {
	
	public static void main(String[] args){
		Frame f = new Frame("My First Windows");
		
		f.setSize(500, 400);
		f.setLocation(300, 300);
		f.setLayout(new FlowLayout());
		
		Button b = new Button("Submit");
		
		f.add(b);
		
		f.addWindowListener(new WindowAdapter() {

			public void windowOpened(WindowEvent e) {
				System.out.println("I am opened");
			}

			public void windowClosing(WindowEvent e) {
				System.out.println("Closing");
				System.exit(0);
			}

			public void windowActivated(WindowEvent e) {
				System.out.println("Activate!");
			}
			
		});
		f.setVisible(true);
				
	}

}
