package compute24;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Com24GUI {
	private Frame f;
	private TextField first, second, third, fourth;
	private Button submit;
	private Label lab;
	private TextArea result;
	private Panel bigN, bigS, smallN, smallS;
	
	Com24GUI(){
		init();
	}
	
	private void init(){
		f = new Frame("Compute 24");
		f.setBounds(300, 200, 600, 500);
		
		bigN = new Panel(new BorderLayout());
		smallN = new Panel();
		smallS = new Panel(new BorderLayout());
		bigS = new Panel(new BorderLayout());
		
		first = new TextField(10);
		second = new TextField(10);
		third = new TextField(10);
		fourth = new TextField(10);
		
		submit = new Button("Run");
		lab = new Label("Run", Label.LEFT);
		result = new TextArea();

		bigN.add(smallN, BorderLayout.NORTH);
		bigN.add(smallS, BorderLayout.CENTER);
		smallN.add(first);
		smallN.add(second);
		smallN.add(third);
		smallN.add(fourth);
		smallN.add(submit);
		smallS.add(lab,BorderLayout.WEST);
		bigS.add(result);
		
		f.add(bigN,BorderLayout.NORTH);
		f.add(bigS,BorderLayout.CENTER);
		
		myEvent();
		f.setVisible(true);
	}
	
	private void myEvent(){
		f.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
			
		});
		
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resCom();
			}
		});
		
		submit.addKeyListener(new KeyAdapter(){
			public void keyPressed(KeyEvent e) {
				enterCompute(e);
			}
		});
		
		first.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				enterCompute(e);
			}
			public void keyTyped(KeyEvent e) {
				numEnter(e);
			}
		});
		
		second.addKeyListener(new KeyAdapter() {
			
			public void keyPressed(KeyEvent e) {
				enterCompute(e);
			}

			public void keyTyped(KeyEvent e) {
				numEnter(e);
			}
			
			
		});
		
		third.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				enterCompute(e);
			}
			public void keyTyped(KeyEvent e) {
				numEnter(e);
			}
		});
		
		fourth.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				enterCompute(e);
			}
			public void keyTyped(KeyEvent e) {
				numEnter(e);
			}
		});
	}
	

	
	private void numEnter(KeyEvent e){
		char chr = e.getKeyChar();
		if(chr < '0' || chr > '9'){
			if(chr == KeyEvent.VK_DELETE){
			}else{
				e.consume();
			}
		}
	}

	
	private void enterCompute(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER){
			resCom();
		}
	}

	private void resCom() {
		result.setText("");
		compute24();
	}
	
	
	private void compute24(){
		
		StringBuilder sb = new StringBuilder();
		
		String str1 = first.getText();
		String str2 = second.getText();
		String str3 = third.getText();
		String str4 = fourth.getText();
		
		if(str1.equals("") || str2.equals("") || str3.equals("") || str4.equals("")){
			Error e = new Error();
			e.setLabel("Input a number between 1 and 13");
			e.show();
			return;
		}else{
			int a = Integer.parseInt(first.getText());
			if(!check(a)) return;
			int b = Integer.parseInt(second.getText());
			if(!check(b)) return;
			int c = Integer.parseInt(third.getText());
			if(!check(c)) return;
			int d = Integer.parseInt(fourth.getText());
			if(!check(d)) return;
			
			
			int[] in = new int[9];
			in[1] = a;
			in[2] = b;
			in[4] = c;
			in[8] = d;
		
			String[] op = new String[]{"+","-","*","/"};
			
			float sum;
			
			for(int i1 = 1; i1 <= 8; i1 *= 2){
				for(int i2 = 1; i2 <= 8; i2 *= 2){
					for(int i3 = 1; i3 <= 8; i3 *= 2){
						for(int i4 = 1; i4 <= 8; i4 *= 2){
							
							if((i1|i2|i3|i4) != 15){continue; }
							for(int o1 = 0; o1 < op.length; o1 ++){
								for(int o2 = 0; o2 < op.length; o2 ++){
									for(int o3 = 0; o3 < op.length; o3 ++){
										
										//tree1 ((1 2) (3 4))
										sum = compute(o3, compute(o1, in[i1], in[i2]), compute(o2, in[i3], in[i4]));
										if(sum==24){
											String res = "(" + in[i1] + op[o1] + in[i2] + ")" + op[o3] 
													+ "(" + in[i3] + op[o2] + in[i4] + ")\n";
											if(sb.indexOf(res)==-1)
												sb.append(res);
										}
										
										//tree2 (((1 2) 3) 4)
										sum = compute(o3, compute(o2, compute(o1, in[i1], in[i2]), in[i3]), in[i4]);
										if(sum==24){
											String res = "((" + in[i1] + op[o1] + in[i2] + ")" + op[o2] 
														+ in[i3] + ")" + op[o3] + in[i4] + "\n";
											if(sb.indexOf(res)==-1)
												sb.append(res);
										}
										
										//tree3 (1 ((2 3) 4))
										sum = compute(o3, in[i1], compute(o2, compute(o1, in[i2], in[i3]), in[i4]));
										if(sum==24){
											String res = in[i1] + op[o3] + "((" + in[i2] + op[o1] 
														+ in[i3] + ")" + op[o2] + in[i4] + ")\n" ;
											if(sb.indexOf(res)==-1)
												sb.append(res);
										}
										
										//tree4 (1 (2 (3 4)))
										sum = compute(o3, in[i1], compute(o2, in[i2], compute(o1, in[i3], in[i4])));
										if(sum==24){
											String res = in[i1] + op[o3] + "(" + in[i2] + op[o2] 
														+ "(" + in[i3] + op[o1] + in[i4] + "))\n";
											if(sb.indexOf(res)==-1)
												sb.append(res);
										}
										
										//tree5 ((1 (2 3)) 4)
										sum = compute(o3, compute(o2, in[i1], compute(o1, in[i2], in[i3])), in[i4]);
										if(sum==24){
											String res = "(" + in[i1] + op[o2] + "(" + in[i2] + op[o1] 
														+ in[i3] + "))" + op[o3] + in[i4] + "\n";
											if(sb.indexOf(res)==-1)
												sb.append(res);
										}
									}
								}
							}
						}
					}
				}
			}
			
			if(sb.length() == 0){
				Error e = new Error();
				e.setLabel("û�н�����޷����24��");
				e.show();
				return;
			}else{
				result.append(sb.toString());
			}
		}
	}
	
	private boolean check(int x){
		if(x <= 0 || x > 13 ){
			Error e = new Error();
			e.setLabel(x + " �ǷǷ��ģ�������1��13������");
			e.show();
			return false;
		}
		return true;
	}
	
	
	private float compute(int op, float a, float b){
		float m = 0;
		
		if(op == 0) m = a+b;
		if(op == 1) m = a-b;
		if(op == 2) m = a*b;
		if(op == 3) m = a/b;
		
		return m;
	}
	
	
	private class Error{
		
		private Dialog dialog;
		private Label lab;
		private Button okbut;
		
		Error(){
			init();
		}
		
		private void init(){
			dialog = new Dialog(f, "Error",	true);
			dialog.setBounds(400, 300, 300, 100);
			dialog.setLayout(new FlowLayout());
			lab = new Label();
			okbut = new Button("OK");
			
			dialog.add(lab);
			dialog.add(okbut);
			
			Event();
		}
		
		private void Event(){
			
			okbut.addActionListener(new ActionListener() {
				
				public void actionPerformed(ActionEvent e) {
					dialog.setVisible(false);
				}
			});
			
			okbut.addKeyListener(new KeyAdapter() {

				public void keyPressed(KeyEvent e) {
					if(e.getKeyCode() == KeyEvent.VK_ENTER){
						dialog.setVisible(false);
					}
				}
				
			});
			
			dialog.addWindowListener(new WindowAdapter() {

				public void windowClosing(WindowEvent e) {
					dialog.setVisible(false);
				}
				
			});
			
		}
		
		public void show(){
			dialog.setVisible(true);
		}
		
		public void setLabel(String info){
			lab.setText(info);
		}
	}
	
	
	public static void main(String[] args){
		new Com24GUI();
	}
}
