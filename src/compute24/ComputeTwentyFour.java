package compute24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ComputeTwentyFour {
	

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("请输入4个整数，以空格隔开：");
		
		String[] lines = br.readLine().split(" ");
		
		int[] in = new int[9];
		in[1] = Integer.parseInt(lines[0]);
		in[2] = Integer.parseInt(lines[1]);
		in[4] = Integer.parseInt(lines[2]);
		in[8] = Integer.parseInt(lines[3]);
	
		String[] op = new String[]{"+","-","*","/"};
		
		float sum;
		
		System.out.println("以下是所有的结果：");
		
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
										System.out.println("(" + in[i1] + op[o1] + in[i2] + ")" + op[o3] 
													+ "(" + in[i3] + op[o2] + in[i4] + ") ");
									}
									
									//tree2 (((1 2) 3) 4)
									sum = compute(o3, compute(o2, compute(o1, in[i1], in[i2]), in[i3]), in[i4]);
									if(sum==24){
										System.out.println("((" + in[i1] + op[o1] + in[i2] + ")" + op[o2] 
													+ in[i3] + ")" + op[o3] + in[i4] );
									}
									
									//tree3 (1 ((2 3) 4))
									sum = compute(o3, in[i1], compute(o2, compute(o1, in[i2], in[i3]), in[i4]));
									if(sum==24){
										System.out.println(in[i1] + op[o3] + "((" + in[i2] + op[o1] 
													+ in[i3] + ")" + op[o2] + in[i4] + ")" );
									}
									
									//tree4 (1 (2 (3 4)))
									sum = compute(o3, in[i1], compute(o2, in[i2], compute(o1, in[i3], in[i4])));
									if(sum==24){
										System.out.println(in[i1] + op[o3] + "(" + in[i2] + op[o2] 
													+ "(" + in[i3] + op[o1] + in[i4] + "))");
									}
									
									//tree5 ((1 (2 3)) 4)
									sum = compute(o3, compute(o2, in[i1], compute(o1, in[i2], in[i3])), in[i4]);
									if(sum==24){
										System.out.println("(" + in[i1] + op[o2] + "(" + in[i2] + op[o1] 
													+ in[i3] + "))" + op[o3] + in[i4]);
									}
								}
							}
						}
					}
				}
			}
		}

	}
	
	public static float compute(int op, float a, float b){
		float m = 0;
		
		if(op == 0) m = a+b;
		if(op == 1) m = a-b;
		if(op == 2) m = a*b;
		if(op == 3) m = a/b;
		
		return m;
	}
	
	

}
