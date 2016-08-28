package v111;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class CloseRelatives_UVa11131 {

	static TreeMap<String, Integer> map = new TreeMap<String, Integer>();
	static String[] unmap = new String[5000];
	static Stack<Integer> stack = new Stack<Integer>();

	static int[][] p = new int[5000][10];
	static int N;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		boolean oneList = true;
		while(sc.ready())
		{
			String[] r = sc.nextLine().split(",");
			Integer x = find(r[0]), y = find(r[1]);
			p[x][0] = y;
			for(int i = 2; i < r.length; ++i)
			{
				Integer z = find(r[i]);
				p[x][i-1] = z;
				oneList = false;
			}
		}
		if(oneList)
		{
			out.print("1\n\n");
			dfs(0, true);
			printStack(out);
		}
		else
		{
			out.print("2\n\n");
			dfs(0, true);
			printStack(out);
			out.println();
			dfs(0, false);
			printStack(out);
		}
		out.flush();
		out.close();

	}
	
	static void dfs(int u, boolean goLeft)
	{
		stack.push(u);
		if(goLeft)
		{
			for(int i = 0; i < 10; ++i)
				if(p[u][i] != 0)
					dfs(p[u][i], goLeft);
		}
		else
		{
			for(int i = 9; i >= 0; --i)
				if(p[u][i] != 0)
					dfs(p[u][i], goLeft);
		}
		
		
		
	}
	
	static void printStack(PrintWriter out)
	{
		while(!stack.isEmpty())
			out.println(unmap[stack.pop()]);
	}
	
	static Integer find(String s)
	{
		Integer x = map.get(s);
		if(x == null)
		{
			map.put(s, x = N);
			unmap[N++] = s;
		}
		return x;
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