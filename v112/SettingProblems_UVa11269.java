package v112;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * Another Solution
 * ================
 * - Partition the tasks into two list. Those with s <= g put into list 1 and those with s > g put into list 2.
 * - Sort list 1 in ascending values of S and sort list 2 in descending values of G. 
 * - Add up all the S values together to a total. 
 * - For the G values, , if a G value is greater than the S value of the task after it, 
 * then add the difference to the G of the task that comes after it.
 */
public class SettingProblems_UVa11269 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			int N = sc.nextInt();
			Pair[] problems = new Pair[N];
			for(int i = 0; i < N; ++i)
				problems[i] = new Pair(sc.nextInt());
			for(int i = 0; i < N; ++i)
				problems[i].G = sc.nextInt();
			Arrays.sort(problems);
			int time = 0, g = 0;
			for(Pair p: problems)
			{
				time += p.S;
				g  = Math.max(0, g - p.S) + p.G;
			}
			time += g;
			out.println(time);
		}
		out.flush();
		out.close();
	}

	static class Pair implements Comparable<Pair>
	{
		int S, G;
		
		Pair(int x) { S = x; }
		
		public int compareTo(Pair p)
		{
			int t1 = S + Math.max(p.S, G) + p.G;
			int t2 = p.S + Math.max(S, p.G) + G;
			return t1 - t2;
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

		public boolean nextEmpty() throws IOException
		{
			String s = br.readLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}


	}
}