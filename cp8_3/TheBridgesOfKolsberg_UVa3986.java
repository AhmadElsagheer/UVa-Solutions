package cp8_3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TheBridgesOfKolsberg_UVa3986 {

	static Pair[][] memo;
	static int[] north_type, south_type, north_value, south_value;
	
	static Pair dp(int i, int j)
	{
		if(i == -1 || j == -1)
			return new Pair(0,0);
		if(memo[i][j] != null)
			return memo[i][j];
		
		Pair best = new Pair(-1, Integer.MAX_VALUE), nxt = null;
		if(north_type[i] == south_type[j])
		{
			int val = north_value[i] + south_value[j];
			nxt = dp(i - 1, j - 1);
			if(nxt.val + val >= best.val)
			{
				if(nxt.val + val == best.val)
					best.bridges = Math.min(best.bridges, nxt.bridges + 1);
				else
				{
					best.val = nxt.val + val;
					best.bridges = nxt.bridges + 1;
				}
			}
		}
		
		nxt = dp(i, j - 1);
		if(nxt.val >= best.val)
		{
			if(nxt.val == best.val)
				best.bridges = Math.min(best.bridges, nxt.bridges);
			else
			{
				best.val = nxt.val;
				best.bridges = nxt.bridges;
			}
		}
		
		nxt = dp(i - 1, j);
		if(nxt.val >= best.val)
		{
			if(nxt.val == best.val)
				best.bridges = Math.min(best.bridges, nxt.bridges);
			else
			{
				best.val = nxt.val;
				best.bridges = nxt.bridges;
			}
		}
		return memo[i][j] = best;
		
	}
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		while(tc-->0)
		{
			int t = 0;
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();

			int n = Integer.parseInt(br.readLine());
			north_type = new int[n]; north_value = new int[n];
			for(int i = 0; i < n; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();
				String x = st.nextToken();
				Integer xx = map.get(x);
				if(xx == null)
				{
					xx = t++;
					map.put(x, xx);
				}
				north_type[i] = xx;
				north_value[i] = Integer.parseInt(st.nextToken());
			}
			
			int m = Integer.parseInt(br.readLine());
			south_type = new int[m]; south_value = new int[m];
			for(int i = 0; i < m; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();
				String x = st.nextToken();
				Integer xx = map.get(x);
				if(xx == null)
				{
					xx = t++;
					map.put(x, xx);
				}
				south_type[i] = xx;
				south_value[i] = Integer.parseInt(st.nextToken());
			}
		memo = new Pair[n][m];	
			
		Pair sol = dp(n-1,m-1);
		sb.append(sol.val + " " + sol.bridges+"\n");
		}
		System.out.print(sb);
		
	}
}

class Pair
{
	int val, bridges;
	Pair(int x, int y) {val = x; bridges = y;}
}