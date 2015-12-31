package cp3_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class ForeignExchange_UVa10763 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			int n = Integer.parseInt(br.readLine());
			if(n==0)
				break;
			int count  = 0;
			TreeMap<Pair,Integer> map = new TreeMap<Pair,Integer>();
			while(n-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int S = Integer.parseInt(st.nextToken());
				int T = Integer.parseInt(st.nextToken());
				Integer x = map.get(new Pair(T,S));
				if(x==null || x == 0)
				{
					Integer y = map.get(new Pair(S,T));
					if(y==null)
						map.put(new Pair(S,T), 1);
					else
						map.put(new Pair(S,T), y + 1);
					count++;
				}
				else
				{
					map.put(new Pair(T,S), x - 1);
					count--;
				}
			}
			if(count==0)
				sb.append("YES\n");
			else
				sb.append("NO\n");
		}
		System.out.print(sb);
		
	}
}

class Pair implements Comparable<Pair>
{
	int T,S;
	Pair(int x, int y){T = y; S = x;}
	@Override
	public int compareTo(Pair o) {
		if(S!=o.S)
			return S - o.S;
		return T - o.T;
	}
	
	
}