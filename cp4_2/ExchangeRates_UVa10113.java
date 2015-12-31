package cp4_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class ExchangeRates_UVa10113 {

	static PrintWriter out = new PrintWriter(System.out);
	static Pair[][] adjMatrix;
	static boolean[] visited;
	static int N;
	static TreeMap<String, Integer> map;
	
	static void assertion(int n, String aitem, int m, String bitem)
	{
		if(!map.containsKey(aitem))
			map.put(aitem, N++);
		if(!map.containsKey(bitem))
			map.put(bitem, N++);
		int u = map.get(aitem);
		int v = map.get(bitem);
		int gcd = gcd(n,m);
		adjMatrix[u][v] = new Pair(n/gcd,m/gcd);
		adjMatrix[v][u] = new Pair(m/gcd,n/gcd);
	}
	
	static void query(String aitem, String bitem)
	{
		if(!map.containsKey(aitem) || !map.containsKey(bitem))
			out.printf("? %s = ? %s\n",aitem,bitem);
		else
		{
			int u = map.get(aitem);
			int v = map.get(bitem);
			visited = new boolean[N];
			Pair res = dfs(u,v);
			if(res == null)
				out.printf("? %s = ? %s\n",aitem,bitem);
			else
				out.printf("%d %s = %d %s\n",res.a,aitem,res.b,bitem);
		}
		
	}
	
	static Pair dfs(int u, int v)
	{
		visited[u] = true;
		for(int i = 0; i < N; i++)
			if(adjMatrix[u][i]!=null && !visited[i])
			{
				if(i==v)
					return adjMatrix[u][i];
				Pair cur = adjMatrix[u][i];
				Pair next = dfs(i,v);
				if(next==null)
					continue;
				int middle = lcm(cur.b,next.a);
				int first = cur.a * (middle/cur.b);
				int second = next.b * (middle/next.a);
				int gcd = gcd(first,second);
				return new Pair(first/gcd,second/gcd);
			}
		return null;
	}
	
	static int gcd(int a, int b) {return b==0? a : gcd(b,a%b);}
	
	static int lcm(int a, int b) {return a*(b/gcd(a,b));}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new TreeMap<String, Integer>();
		adjMatrix = new Pair[60][60];
		while(true)
		{
			String line = br.readLine();
			if(line.equals("."))
				break;
			StringTokenizer st = new StringTokenizer(line);
			if(st.nextToken().equals("!"))
			{
				int n = Integer.parseInt(st.nextToken());
				String a = st.nextToken();
				st.nextToken();
				int m = Integer.parseInt(st.nextToken());
				String b = st.nextToken();
				assertion(n,a,m,b);
			}
			else
			{
				String a = st.nextToken();
				st.nextToken();
				String b = st.nextToken();
				query(a,b);
			}
		}
		out.flush();
	}
}

class Pair
{
	int a,b;
	Pair(int x, int y){a = x; b = y;}
}
