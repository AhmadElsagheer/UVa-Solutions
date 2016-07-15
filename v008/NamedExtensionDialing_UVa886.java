package v008;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class NamedExtensionDialing_UVa886 {

	static int[] map = new int[] { 2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9};
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<String> extensions = new ArrayList<String>();
		TreeSet<String> ext = new TreeSet<String>();
		
		String s;
		while(true)
		{
			s = sc.nextLine();
			if(s == null)
				break;
			if(s.isEmpty())
				continue;
			StringTokenizer st = new StringTokenizer(s);
			s = st.nextToken();
			if(s.charAt(0) >= '0' && s.charAt(0) <= '9')
				break;
			StringBuilder sb = new StringBuilder();
			sb.append(map[Character.toLowerCase(s.charAt(0))-'a']);
			s = st.nextToken().toLowerCase();
			for(int i = 0; i < s.length(); ++i)
				sb.append(map[s.charAt(i)-'a']);
			names.add(sb.toString());
			String e = st.nextToken();
			extensions.add(e);
			ext.add(e);
		}
		
		while(s != null)
		{
			s = s.trim();
			if(ext.contains(s))
				out.println(s);
			else
			{
				ArrayList<String> ans = getMatches(s, names, extensions);
				if(ans.isEmpty())
					out.println(0);
				else
				{
					StringBuilder sb = new StringBuilder();
					for(String x: ans)
					{
						if(sb.length() != 0)
							sb.append(" ");
						sb.append(x);
					}
					out.println(sb);
				}				
			}
			
			do
			{
				s = sc.nextLine();
			}
			while(s != null && s.trim().isEmpty());
		}
		out.flush();
		out.close();
	}
	
	static ArrayList<String> getMatches(String t, ArrayList<String> names, ArrayList<String> ext)
	{
		StringBuilder sb = new StringBuilder(t);
		char c = 0;
		for(String name: names)
		{	
			while(c >= '0' && c <= '9')
				++c;
			sb.append(c++).append(name);
		}
		char[] s = sb.toString().toCharArray();
		int n = s.length, pi[] = new int[n];
		for(int i = 1, j = 0; i < n; ++i)
		{
			while(j > 0 && s[i] != s[j])
				j = pi[j-1];
			if(s[i] == s[j])
				++j;
			pi[i] = j;
		}
		ArrayList<String> ret = new ArrayList<String>();
		int m = t.length(), end = names.size();
		for(int i = 0, j = m; i < end; ++i)
		{
			int l = names.get(i).length();
			if(l >= m && pi[j + m] == m)
				ret.add(ext.get(i));
			j += 1 + l;
		}
		return ret;
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