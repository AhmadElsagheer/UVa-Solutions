package v124;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Scarecrow_UVa12405 {
		

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int t = Integer.parseInt(br.readLine());
		for(int k = 1; k <= t; k++)
		{
			int n = Integer.parseInt(br.readLine());
			String field = br.readLine();
			int count = 0;
			for(int i = 0; i < n; i++)
				if(field.charAt(i)=='.')
				{
					i+=2;
					count++;
				}
			
			
			sb.append("Case "+k+": "+count+"\n");
		}

		System.out.print(sb);
	}
	

}
