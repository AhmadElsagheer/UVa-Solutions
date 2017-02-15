package v004;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;

public class MatrixChainMultiplication_UVa442 {

	static int[] r, c, match;
	static int ans;
	static boolean error;
	
	static Pair eval(char[] s, int idx)
	{
		if(s[idx] >= 'A' && s[idx] <= 'Z')
			return new Pair(r[s[idx] - 'A'], c[s[idx] - 'A']);
		++idx;
		int leftIndex = idx, rightIndex = idx + 1;
		if(s[idx] < 'A' || s[idx] > 'Z')
			rightIndex = match[idx] + 1;
		Pair x = eval(s, leftIndex), y = eval(s, rightIndex);
		if(x.m != y.n)
			error = true;
		ans += x.n * x.m * y.m;
		return new Pair(x.n, y.m);	
	}
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt();
		r = new int[26];
		c = new int[26];
		while(n-->0)
		{
			int ch = sc.next().charAt(0) - 'A';
			r[ch] = sc.nextInt();
			c[ch] = sc.nextInt();
		}
		
		while(sc.ready())
		{
			char[] s = sc.next().toCharArray();
			match = new int[s.length];
			Stack<Integer> st = new Stack<>();
			for(int i = 0; i < s.length; ++i)
				if(s[i] == '(')
					st.push(i);
				else if(s[i] == ')')
					match[st.pop()] = i;
			ans = 0;
			error = false;
			eval(s, 0);
			if(error)
				out.println("error");
			else
				out.println(ans);
		}
		out.flush();
		out.close();
	}
	
	static class Pair { int n, m; Pair(int x, int y) { n = x; m = y; } }
	
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