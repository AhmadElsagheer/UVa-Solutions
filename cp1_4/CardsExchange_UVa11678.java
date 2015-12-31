package cp1_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class CardsExchange_UVa11678 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			if(a==0 && b==0)
				break;
			ArrayList<Integer> A = new ArrayList<Integer>(a);
			TreeSet<Integer> A_set = new TreeSet<Integer>();
			ArrayList<Integer> B = new ArrayList<Integer>(b);
			TreeSet<Integer> B_set = new TreeSet<Integer>();
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < a; i++)
			{
				int cur = Integer.parseInt(st.nextToken());
				if(!A_set.contains(cur))
				{
					A.add(cur);
					A_set.add(cur);
				}
			}
			st = new StringTokenizer(br.readLine());
			for(int i = 0; i < b; i++)
			{
				int cur = Integer.parseInt(st.nextToken());
				if(!B_set.contains(cur))
				{
					B.add(cur);
					B_set.add(cur);
				}
			}
			
			int c1 = 0,c2 = 0;
			for(int i = 0; i < A.size(); i++)
				if(!B_set.contains(A.get(i)))
					c1++;
			for(int i = 0; i < B.size(); i++)
				if(!A_set.contains(B.get(i)))
					c2++;
			sb.append(Math.min(c1, c2)).append("\n");
		}
		System.out.print(sb);
	}
}
