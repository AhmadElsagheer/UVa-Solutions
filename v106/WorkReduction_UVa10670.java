package v106;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class WorkReduction_UVa10670 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int k = 1; k <= TC; k++)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			ArrayList<Agency> sol = new ArrayList<Agency>();
			for(int i = 0; i < L ; i++)
			{
				st = new StringTokenizer(br.readLine(),":");
				Agency cur = new Agency(st.nextToken());
				st = new StringTokenizer(st.nextToken(),",");
				int A = Integer.parseInt(st.nextToken());
				int B = Integer.parseInt(st.nextToken());
				
				int X = N;
				while(X!=M)
				{
					if(X/2 < M)
					{
						cur.cost += (X-M)*A;
						break;
					}
					else
					{
						cur.cost += Math.min(B, (X-X/2)*A);
						X = X/2;
					}
				}
				sol.add(cur);
			}
			Collections.sort(sol);
			sb.append("Case "+k+"\n");
			for(int i = 0; i < L; i++)
				sb.append(sol.get(i).name+" "+sol.get(i).cost+"\n");
		}
		System.out.print(sb);
		
	}
}

class Agency implements Comparable<Agency>
{
	String name;
	int cost;
	Agency(String x) {name = x;}
	
	public int compareTo(Agency x)
	{
		if(cost!=x.cost)
			return cost - x.cost;
		return name.compareTo(x.name);
	}
}