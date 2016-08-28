package v116;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FamilyTree_UVa11615 {

	static int N, A, B;
	static int c1, c2;
	
	static void find(int val) 
	{
		if(val > (1<<N) - 1)
			return;
			
		if(val == A)
		{
			int level = (int) (Math.log(val)/Math.log(2));
			c1 = (1<<(N - level)) - 2;
			return; 
		}
		
		if(val == B)
		{
			int level = (int) (Math.log(val)/Math.log(2));
			
			c2 = (1<<(N - level)) - 2;
			return; 
		}
		
		find(val<<1);
		find((val<<1)+1);
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		
		while(tc-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			A = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			c1 = c2 = 0;
			
			find(1);
			
			sb.append((1<<N)-1-Math.min(c1, c2)+"\n");
		}
		System.out.print(sb);
	}
	
	
}

