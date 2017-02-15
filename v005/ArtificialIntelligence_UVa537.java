package v005;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class ArtificialIntelligence_UVa537 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		for(int t = 1; t <= tc; ++t)
		{
			String problem = sc.nextLine();
			Double[] vars = new Double[3];
			for(int i = 0; i < problem.length(); ++i)
				if(problem.charAt(i) == '=')
				{
					int idx = problem.charAt(i - 1) == 'U' ? 0 : problem.charAt(i - 1) == 'I' ? 1 : 2;
					int j = i + 1;
					while(problem.charAt(j) == '.' || problem.charAt(j) >= '0' && problem.charAt(j) <= '9')
						++j;
					vars[idx] = Double.parseDouble(problem.substring(i + 1, j));
					if(problem.charAt(j) == 'm')
						vars[idx] /= 1e3;
					else if(problem.charAt(j) == 'k')
						vars[idx] *= 1e3;
					else if(problem.charAt(j) == 'M')
						vars[idx] *= 1e6;
				}
			if(vars[0] == null)
				out.printf("Problem #%d\nU=%.2fV\n\n", t, vars[2] / vars[1]);
			else if(vars[1] == null)
				out.printf("Problem #%d\nI=%.2fA\n\n", t, vars[2] / vars[0]);
			else 
				out.printf("Problem #%d\nP=%.2fW\n\n", t, vars[0] * vars[1]);
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