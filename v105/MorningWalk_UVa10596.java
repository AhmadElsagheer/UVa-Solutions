package v105;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class MorningWalk_UVa10596 {

	static ArrayList<Integer>[] adjList;
	
	static boolean seen(int N)	//check if all edges can be visited 
	{
		for(int i = 0; i < N; ++i)
			if(adjList[i].size() != 0)
			{
				dfs(i);
				break;
			}
		for(int i = 0; i < N; ++i)
			for(int j = 0; j < adjList[i].size(); ++j)
				if(adjList[i].get(j) != null)
					return false;
		return true;
	}
	
	static void dfs(int u)
	{
		for(int i = 0; i < adjList[u].size(); ++i)
		{
			Integer v = adjList[u].get(i);
			if(v != null)
			{
				adjList[u].set(i, null);
				dfs(v);
			}
		}
	}
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			int N = sc.nextInt(), E = sc.nextInt();
			boolean possible = E == 0 ? false : true;	//'single' walk
			adjList = new ArrayList[N];
			for(int i = 0; i < N; ++i)
				adjList[i] = new ArrayList<Integer>();
			while(E-->0)
			{
				int u = sc.nextInt(), v = sc.nextInt();
				adjList[u].add(v);
				adjList[v].add(u);
			}
			for(int i = 0; possible && i < N; ++i)
				if((adjList[i].size() & 1) != 0)
					possible = false;
			out.println(possible&&seen(N)?"Possible":"Not Possible");
		}
		out.flush();
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

		public boolean nextIsEmpty() throws IOException
		{
			String line = br.readLine();
			if(line.isEmpty())
				return true;
			st = new StringTokenizer(line);
			return false;
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public boolean ready() throws IOException {return br.ready(); }


	}

}
