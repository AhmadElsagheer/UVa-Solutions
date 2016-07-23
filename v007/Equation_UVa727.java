package v007;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;


public class Equation_UVa727 {
	
	static int pr(char c)
	{
		switch(c)
		{
		case '+': case '-': return 1;
		case '*': case '/': return 2;
		}
		return 0;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		sc.nextLine();
		while(tc-->0)
		{
			ArrayList<Character> syms = new ArrayList<Character>();
			while(true)
			{
				String line = sc.nextLine();
				if(line == null || line.isEmpty())
					break;
				syms.add(line.charAt(0));
			}
			Stack<Character> ops = new Stack<Character>();
			StringBuilder sb = new StringBuilder();
			for(char c: syms)
				if(c >= '0' && c <= '9')
					sb.append(c);
				else
				{
					if(c == '(')
						ops.push(c);
					else
					{
						while(!ops.isEmpty() && ops.peek() != '(' && pr(c) <= pr(ops.peek()))
							sb.append(ops.pop());
						if(c == ')')
							ops.pop();
						else
							ops.push(c);
					}
				}
			while(!ops.isEmpty())
				sb.append(ops.pop());
			out.println(sb);
			if(tc != 0)
				out.println();
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