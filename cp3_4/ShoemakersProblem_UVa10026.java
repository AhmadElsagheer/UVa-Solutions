package cp3_4;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class ShoemakersProblem_UVa10026 {

	public static void main(String[] args) throws IOException 
	{
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		int tc = sc.nextInt();
		
		while(tc-->0)
		{
			int n = sc.nextInt();
			Job[] jobs = new Job[n];
			for(int i = 0; i < n; i++) jobs[i] = new Job(i + 1, sc.nextInt(), sc.nextInt());
			
			Arrays.sort(jobs);
			for(int i = 0; i < n - 1; i++)
				sb.append(jobs[i].idx).append(" ");
			sb.append(jobs[n-1].idx).append("\n");
			if(tc != 0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
	
	static class Job implements Comparable<Job>
	{
		int idx, t, s;
		
		Job(int i, int j, int k) { idx = i; t = j; s = k; }
		
		public int compareTo(Job j)
		{
			if(t * j.s != j.t * s)
				return t * j.s - j.t * s;
			return idx - j.idx;
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

		int countTokens() throws IOException
		{
			st = new StringTokenizer(br.readLine());
			return st.countTokens();
		}
	}
}
