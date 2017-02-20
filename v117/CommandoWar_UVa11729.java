package v117;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class CommandoWar_UVa11729 {
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int k = 1;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			Soldier[] soldiers = new Soldier[n];
			for(int i = 0; i < n; ++i)
				soldiers[i] = new Soldier(sc.nextInt(), sc.nextInt());
			Arrays.sort(soldiers);
			int last = 0, sum = 0;
			for(int i = 0; i < n; ++i)
			{
				sum += soldiers[i].B;
				last = Math.max(last - soldiers[i].B, soldiers[i].J);
			}
			out.printf("Case %d: %d\n", k++, sum + last);
		}
		out.flush();
	}
	
	static class Soldier implements Comparable<Soldier>
	{
		int B, J;
		
		Soldier(int x, int y) { B = x; J = y; }
		
		public int compareTo(Soldier s)
		{
			int x = B + Math.max(J, s.B + s.J);
			int y = s.B + Math.max(s.J, B + J);
			return x - y;
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
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
