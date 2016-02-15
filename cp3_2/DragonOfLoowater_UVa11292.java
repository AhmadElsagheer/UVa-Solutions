package cp3_2;


import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class DragonOfLoowater_UVa11292 {
		

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if(n==0)
				break;
			int k = Integer.parseInt(st.nextToken());
			
			int[] heads = new int[n+1];
			for(int i = 1; i <= n; i++)
				heads[i] = Integer.parseInt(br.readLine());
			
			int[] knights = new int[k+1];
			for(int i = 1; i <= k; i++)
				knights[i] = Integer.parseInt(br.readLine());
			
			Arrays.sort(heads);
			Arrays.sort(knights);
			
			int headPointer = 1;
			int knightPointer = 1;
			int coins = 0;
			
			while(headPointer<=n)
			{
				while(knightPointer<=k && heads[headPointer]>knights[knightPointer])
					knightPointer++;
				if(knightPointer>k)
					break;             //not possible
				coins += knights[knightPointer];
				headPointer++;
				knightPointer++;
			}
			if(headPointer==n+1)
				sb.append(coins+"\n");
			else
				sb.append("Loowater is doomed!\n");
		}

		System.out.print(sb);
	}
	

}

