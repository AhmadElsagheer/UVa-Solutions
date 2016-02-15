package cp1_3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SubPrime {

	public static void main(String[] args) throws IOException
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		while(br.ready())
		{
			st = new StringTokenizer(br.readLine());
			int b = Integer.parseInt(st.nextToken()); 
			int n = Integer.parseInt(st.nextToken());
			if(b==0)
				break;
			st = new StringTokenizer(br.readLine());
			int[] reserves = new int[b];
			for(int i = 0; i < b; i++)
				reserves[i] = Integer.parseInt(st.nextToken());
			boolean possible = true;
			for(int i = 0; i < n; i++)
			{
				st = new StringTokenizer(br.readLine());
				int db = Integer.parseInt(st.nextToken())-1;
				int cb = Integer.parseInt(st.nextToken())-1;
				int v = Integer.parseInt(st.nextToken());
				reserves[db] -= v;reserves[cb] += v;
			}
			for(int i = 0; i < b; i++)
				if(reserves[i]<0)
				{
					possible = false;break;
				}
			if(possible)
				sb.append("S\n");
			else
				sb.append("N\n");
		}
		System.out.print(sb);
	}
}
