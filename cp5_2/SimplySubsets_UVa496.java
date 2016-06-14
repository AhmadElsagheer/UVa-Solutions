package cp5_2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;


public class SimplySubsets_UVa496 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			TreeSet<Integer> set = new TreeSet<Integer>();
			while(st.hasMoreTokens())
				set.add(Integer.parseInt(st.nextToken()));
			int missing = 0, intersect = 0;
			st = new StringTokenizer(sc.nextLine());
			while(st.hasMoreTokens())
				if(set.remove(Integer.parseInt(st.nextToken())))
					++intersect;
				else
					++missing;
			if(missing == 0)
				if(set.isEmpty())
					out.println("A equals B");
				else
					out.println("B is a proper subset of A");
			else 
				if(set.isEmpty())
					out.println("A is a proper subset of B");
				else if(intersect == 0)
					out.println("A and B are disjoint");
				else
					out.println("I'm confused!");
		}
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