package v002;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;


public class Palinwords_UVa257 {

	static int n;
	static char[] s;
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			String line = sc.nextLine();
			if(line.isEmpty())
				continue;
			StringTokenizer st = new StringTokenizer(line);
			while(st.hasMoreTokens())
			{
				s = st.nextToken().toCharArray();
				n = s.length;

				ArrayList<Integer> three = new ArrayList<Integer>(), four = new ArrayList<Integer>();
				for(int i = 0; i + 3 <= n; ++i)
				{
					if(s[i] == s[i + 2])
						three.add(i);
					if(i + 4 <= n && s[i] == s[i + 3] && s[i + 1] == s[i + 2])
						four.add(i);
				}
				
				boolean found = false;
				for(int x: three)
					for(int y: three)
						if(s[x] != s[y] || s[x + 1] != s[y + 1])
							found = true;
				for(int x: four)
					for(int y: four)
						if(s[x] != s[y] || s[x + 1] != s[y + 1])
							found = true;
				for(int x: three)
					for(int y: four)
						if(!match(x, y))
							found = true;
					
				if(found)
					out.println(s);
			}
		}
		out.flush();
		out.close();
	}
	
	static boolean match(int x, int y)
	{
		for(int j = 0; j < 2; ++j)
		{
			boolean match = true;
			for(int i = 0; i < 3; ++i)
				match &= s[x + i] == s[y + i + j];
			if(match)
				return true;
		}
		return false;
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