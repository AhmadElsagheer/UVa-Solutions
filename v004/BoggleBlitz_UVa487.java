package v004;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class BoggleBlitz_UVa487 {

	static int[] dx = new int[] {0, 0, -1, 1, -1, -1, 1, 1};
	static int[] dy = new int[] {-1, 1, 0, 0, 1, -1, -1, 1};
	static char[][] grid;
	static TreeSet<String> ans;
	static int N;
	static boolean[][] visited;
	
	static boolean valid(int x, int y)
	{
		return x != -1 && y != -1 && x != N && y != N && !visited[x][y]; 
	}
	
	static void dfs(int x, int y, String s)
	{
		char c = grid[x][y];
		s += c;
		if(s.length() >= 3)
		ans.add(s);
		visited[x][y] = true;
		for(int k = 0; k < 8; ++k)
		{
			int i = x + dx[k], j = y + dy[k];
			if(valid(i, j) && c < grid[i][j])
				dfs(i, j, s);
		}
		visited[x][y] = false;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			N = sc.nextInt();
			grid = new char[N][N];
			for(int i = 0; i < N; ++i)
				grid[i] = sc.next().toCharArray();
			visited = new boolean[N][N];
			ans = new TreeSet<String>(new Sorter());
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					dfs(i, j, "");
			for(String s: ans)
				sb.append(s + "\n");
			if(tc != 0)
				sb.append("\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}
	
	static class Sorter implements Comparator<String>
	{

		public int compare(String o1, String o2) 
		{
			if(o1.length() != o2.length())
				return o1.length() - o2.length();
			return o1.compareTo(o2);
		}
		
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

		public double nextDouble() throws IOException
		{
			String x = next();
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

		public boolean ready() throws IOException {return br.ready();}


	}
}