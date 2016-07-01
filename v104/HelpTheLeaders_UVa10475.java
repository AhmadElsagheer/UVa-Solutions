package v104;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class HelpTheLeaders_UVa10475 {

	static String[] topics;
	static boolean[][] forb;
	static StringBuilder sb;
	static int N, K, taken[];
	
	static void bt(int i, int c)
	{
		if(c == K)
		{
			for(int j = 0; j < c - 1; ++j)
				sb.append(topics[taken[j]]+" ");
			sb.append(topics[taken[c-1]]+"\n");	
			return;
		}
		
		if(i == N)
			return;
		
		boolean canTake = true;
		for(int j = 0; j < c; ++j)
			if(forb[i][taken[j]])
				canTake = false;
		if(canTake)
		{
			taken[c] = i;
			bt(i + 1, c + 1);
		}
		bt(i + 1, c);
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		sb = new StringBuilder();
		
		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			N = sc.nextInt();
			int M = sc.nextInt();
			K = sc.nextInt();
			
			topics = new String[N];
			for(int i = 0; i < N; ++i)
				topics[i] = sc.next().toUpperCase();
			
			Arrays.sort(topics, new Sorter());
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			for(int i = 0; i < N; ++i)
				map.put(topics[i], i);
			
			forb = new boolean[N][N];
			while(M-->0)
			{
				int x = map.get(sc.next().toUpperCase()), y = map.get(sc.next().toUpperCase());
				forb[x][y] = forb[y][x] = true;
			}
			taken = new int[K];
			sb.append("Set " + t + ":\n");
			bt(0, 0);
			sb.append("\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}
	
	static class Sorter implements Comparator<String>
	{
		public int compare(String x, String y)
		{
			if(x.length() != y.length())
				return x.length() > y.length() ? -1 : 1;
			return x.compareTo(y);	
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