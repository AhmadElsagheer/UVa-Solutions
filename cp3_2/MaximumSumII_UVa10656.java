package cp3_2;


import java.io.*;

public class MaximumSumII_UVa10656 {
		

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		
		while(true)
		{
			int n = Integer.parseInt(br.readLine());
			if(n==0)
				break;
			int[] sequence = new int[n];
			boolean started = false;
			int zeros = 0; 
			int k = 0;
			for(int i = 0; i < n; i++)
			{
				int current = Integer.parseInt(br.readLine());
				if(!started)
					if(current==0)
						continue;
					else
					{
						sequence[k++] = current;
						started = true;
					}
				else
					if(current==0)
						zeros++;
					else
					{
						k += zeros;
						sequence[k++] = current;
						zeros = 0;
					}
			}
			if(k==0)
				sb.append("0");
			else
				for(int i = 0; i < k; i++)
					sb.append((i==0?"":" ")+sequence[i]);
			
			sb.append("\n");
		}
		
		
		System.out.print(sb);
	}
	
	
	

}
