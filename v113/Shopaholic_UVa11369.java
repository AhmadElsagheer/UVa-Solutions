package v113;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Shopaholic_UVa11369 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
		while(tc-->0)
		{
			int n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			int[] p = new int[n];
			StringTokenizer st= new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++)
				p[i] = Integer.parseInt(st.nextToken());
			Arrays.sort(p);
			int max = 0;
			for(int i = n - 3; i>= 0; i -= 3)
				max += p[i];
			sb.append(max+"\n");			
		}
		
		System.out.print(sb);
		
	}
}
