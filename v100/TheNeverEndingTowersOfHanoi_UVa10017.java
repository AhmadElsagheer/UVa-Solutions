package v100;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;


public class TheNeverEndingTowersOfHanoi_UVa10017 {

	static Stack<Integer>[] s;
	static int move, m;
	
	static void solve(int n, int from, int thru, int to)
	{
		if(move >= m)
			return;
		
		if(n == 1)
		{
			s[to].push(s[from].pop());
			print();
			++move;
			return;
		}
		
		solve(n - 1, from, to, thru);
		solve(1, from, thru, to);
		solve(n - 1, thru, from, to);
	}
	
	static StringBuilder sb;
	
	static void print() { print(s[0], 'A');  print(s[1], 'B');  print(s[2], 'C'); sb.append("\n"); }
	
	static void print(Stack<Integer> s, char c)
	{
		if(s.isEmpty())
			sb.append(c + "=>\n");
		else
		{
			sb.append(c + "=>  ");
			for(int x: s)
				sb.append(" " + x);
			sb.append("\n");
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		sb = new StringBuilder();
		int tc = 1;
		while(true)
		{
			int n = sc.nextInt();
			m = sc.nextInt();
			if(n == 0 && m == 0)
				break;
			move = 0;
			s = new Stack[3];
			for(int i = 0; i < 3; ++i)
				s[i] = new Stack<Integer>();
			for(int i = n; i >= 1; --i)
				s[0].push(i);
			sb.append("Problem #" + tc++ + "\n\n");
			print();
			solve(n, 0, 1, 2);
			
		}
		out.print(sb);
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}


	}
}