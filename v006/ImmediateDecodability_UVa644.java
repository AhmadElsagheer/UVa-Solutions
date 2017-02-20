package v006;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class ImmediateDecodability_UVa644 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(sc.ready())
		{
			ArrayList<String> s = new ArrayList<>();
			while(true)
			{
				String t = sc.next();
				if(t.equals("9"))
					break;
				s.add(t);
			}
			Collections.sort(s);
			boolean bad = false;
			for(int i = 0; i < s.size() - 1; ++i)
			{
				String t1 = s.get(i), t2 = s.get(i + 1);
				bad |= t2.startsWith(t1); 
			}
			out.printf("Set %d is %simmediately decodable\n", tc++, bad ? "not " : "");
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