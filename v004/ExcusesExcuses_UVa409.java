package v004;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ExcusesExcuses_UVa409 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = 1;
		while(sc.ready())
		{
			out.printf("Excuse Set #%d\n", tc++);
			int K = sc.nextInt(), E = sc.nextInt();
			String[] keys = new String[K];
			for(int i = 0; i < K; ++i)
				keys[i] = sc.next().toLowerCase();
			int max = 0;
			String[] lines = new String[E];
			for(int i = 0; i < E; ++i)
			{
				lines[i] = sc.nextLine();
				int cnt = 0;
				for(String s: lines[i].toLowerCase().split("[^a-z]"))
					for(String t: keys)
						if(s.equals(t))
							++cnt;
				max = Math.max(max, cnt);
			}
			
			for(int i = 0; i < E; ++i)
			{
				int cnt = 0;
				for(String s: lines[i].toLowerCase().split("[^a-z]"))
					for(String t: keys)
						if(s.equals(t))
							++cnt;
				if(cnt == max)
					out.println(lines[i]);
			}
			out.println();
		}
		out.flush();
		out.close();
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();} 
	}
} 