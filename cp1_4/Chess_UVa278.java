package cp1_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Chess_UVa278 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			char c = st.nextToken().charAt(0);
			int m = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());
			int count = 0;
			switch(c)
			{
			case 'k':count = (m*n+1)/2;break;
			case 'K':count = ((m+1)/2)*((n+1)/2);break;
				default:count = Math.min(m, n);
			}
			sb.append(count).append("\n");
		}
		System.out.print(sb);
	}
}
