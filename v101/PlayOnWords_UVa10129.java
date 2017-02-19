package v101;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PlayOnWords_UVa10129 {

	static ArrayList<Integer>[] adjList;
	static int[][] words;
	static boolean[] visited;
	
	static boolean connected(int u)
	{
		if(u == -2)
			return false;
		if(u == -1)
			for(int i = 0; i < 26; ++i)
				if(adjList[i].size() > 0)
				{
					u = i;
					break;
				}
		visited = new boolean[words.length + 26];
		dfs(u);
		for(int i = 26; i < visited.length; ++i)
			if(!visited[i])
				return false;
		return true;
	}
	
	static void dfs(int u)
	{
		visited[u] = true;
		for(int v: adjList[u])
		{
			visited[v] = true;
			if(!visited[words[v - 26][1]])
				dfs(words[v - 26][1]);
		}
	}
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int n = sc.nextInt();
			int[] deg = new int[26];
			adjList = new ArrayList[26];
			for(int i = 0; i < 26; ++i)
				adjList[i] = new ArrayList<Integer>();
			words = new int[n][2];
			for(int i = 0; i < n; ++i)
			{
				String x = sc.next();
				words[i][0] = x.charAt(0) - 'a';
				words[i][1] = x.charAt(x.length() - 1) - 'a';
				deg[words[i][0]]++;
				deg[words[i][1]]--;
				adjList[words[i][0]].add(i + 26);
			}
			int root = -1;
			for(int i = 0; root != -2 && i < 26; ++i)
				if(deg[i] != 0)
					if(deg[i] == 1)
						if(root == -1)
							root = i;
						else
							root = -2;
					else
						if(deg[i] != -1)
							root = -2;
			
			if(connected(root))
				out.println("Ordering is possible.");
			else
				out.println("The door cannot be opened.");
		}
		out.flush();
		out.close();
	}	

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready();}
		
		public double nextDouble(String x) 
		{

			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}


	}
}
