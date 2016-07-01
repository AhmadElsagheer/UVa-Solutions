package v106;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class SQFProblem_UVa10686 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int C = sc.nextInt();
			String[] names = new String[C];
			int[] p = new int[C];
			TreeMap<String, ArrayList<Integer>> map = new TreeMap<String, ArrayList<Integer>>();
			for(int i = 0; i < C; ++i)
			{
				names[i] = sc.next();
				int W = sc.nextInt();
				p[i] = sc.nextInt();
				TreeSet<String> set = new TreeSet<String>();
				while(W-->0)
				{
					String s = sc.next();
					if(set.contains(s))
						continue;
					set.add(s);
					ArrayList<Integer> cats = map.get(s);
					if(cats == null)
						map.put(s, cats = new ArrayList<Integer>());
					cats.add(i);
				}
			}
			int[] f = new int[C];
			TreeSet<String> set = new TreeSet<String>();
			while(true)
			{
				String s = sc.nextLine();
				if(s == null || s.isEmpty())
					break;
				int i = 0, j = 0;
				while(j <= s.length())
				{
					if(j == s.length() || !isCharacter(s.charAt(j)))
					{
						String t = s.substring(i, j);
						if(!t.isEmpty() && !set.contains(t))
						{
							set.add(t);
							ArrayList<Integer> cats = map.get(t);
							if(cats != null)
								for(int x: cats)
									f[x]++;
						}
						i = j + 1;
					}
					++j;
				}
			}
			StringBuilder ans = new StringBuilder();
			for(int i = 0; i < C; ++i)
				if(f[i] >= p[i])
				{
					if(ans.length() != 0)
						ans.append(",");
					ans.append(names[i]);
				}
			if(ans.length() == 0)
				ans.append("SQF Problem.");
			out.println(ans);
		}
		out.flush();
		out.close();
	}
	
	static boolean isCharacter(char c)
	{
		return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z'; 
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