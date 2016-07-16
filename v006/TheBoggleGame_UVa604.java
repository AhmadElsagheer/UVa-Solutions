package v006;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class TheBoggleGame_UVa604 {

	static int[] dx = new int[] {0, 0, -1, -1, -1, 1, 1, 1};
	static int[] dy = new int[] {-1, 1, -1, 0, 1, -1, 0, 1};
	static boolean[] vowel = new boolean[26];
	static TreeSet<String> set;
	static char[][] grid;

	static boolean valid(int x, int y) { return x != -1 && y != -1 && x != 4 && y != 4 && grid[x][y] != '$'; }

	static TreeSet<String> generate(char[][] x)
	{
		grid = x;
		set = new TreeSet<String>();
		for(int i = 0; i < 4; ++i)
			for(int j = 0; j < 4; ++j)
				generate(i, j, "");
		return set;
	}

	static void generate(int x, int y, String s)
	{
		if(s.length() == 4)
		{
			int v = 0;
			for(int i = 0; i < 4; ++i)
				if(vowel[s.charAt(i)-'A'])
					++v;
			if(v == 2)
				set.add(s);
			return;
		}
		if(!valid(x, y))
			return;
		char c = grid[x][y];
		s += c;
		grid[x][y] = '$';
		for(int k = 0; k < 8; ++k)
		{
			int i = x + dx[k], j = y + dy[k];
			generate(i, j, s);
		}
		grid[x][y] = c;
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb  = new StringBuilder();
		
		vowel[0] = vowel[4] = vowel[8] = vowel[14] = vowel[20] = vowel[24] = true;
		boolean first = true;
		while(true)
		{
			char c = sc.next().charAt(0);
			if(c == '#')
				break;
			if(first)
				first = false;
			else
				sb.append("\n");
			char[][] x = new char[4][4], y = new char[4][4];
			for(int i = 0; i < 4; ++i)
			{
				for(int j = 0; j < 4; ++j)
				{
					x[i][j] = c;
					c = sc.next().charAt(0);
				}
				
				for(int j = 0; j < 4; ++j)
				{
					y[i][j] = c;
					if(i != 3 || j != 3)
						c = sc.next().charAt(0);
				}
			}				

			TreeSet<String> s1 = generate(x), s2 = generate(y);
			
			boolean found = false;
			for(String s: s1)
				if(s2.contains(s))
				{
					sb.append(s).append("\n");
					found = true;
				}
			if(!found)
				sb.append("There are no common words for this pair of boggle boards.\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){    br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){    br = new BufferedReader(r);}

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