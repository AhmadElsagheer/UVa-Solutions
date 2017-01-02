package v100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Interpreter_UVa10033 {

	static int[] RAM, reg;
	static int counter;
	
	static void go(int idx)
	{
		counter++;
		int x = RAM[idx];
		int d = x/10%10, n = x%10;
		switch(x/100)
		{
		case 0:	if(reg[n] == 0) break; go(reg[d]); return;
		case 2:	reg[d] = n; break;
		case 3: reg[d] = (reg[d]+n)%1000;break;
		case 4: reg[d] = (reg[d]*n)%1000;break;
		case 5: reg[d] = reg[n];break;
		case 6: reg[d] = (reg[n] + reg[d])%1000;break;
		case 7:	reg[d] = (reg[n] * reg[d])%1000;break;
		case 8: reg[d] = RAM[reg[n]];break;
		case 9: RAM[reg[n]] = reg[d];break;
			default: return;
		}
		go(idx+1);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		br.readLine();
		while(tc-->0)
		{
			RAM = new int[1000];
			reg = new int[10];
			String line = br.readLine();
			int idx = 0;
			while(line != null && !line.isEmpty())
			{
				RAM[idx++] = Integer.parseInt(line);
				line = br.readLine();
			}
			counter = 0;
			go(0);
			sb.append(counter+"\n");
			if(tc!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
}
