package v101;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class IsBiggerSmarter_UVa10131 {

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
	
		ArrayList<Elephant> elephants = new ArrayList<Elephant>();
		int e = 0;
		
		while(sc.ready())
			elephants.add(new Elephant(sc.nextInt(), sc.nextInt(), e++));
		int n = elephants.size();
		Collections.sort(elephants);

		int[] P = new int[n], L = new int[n];
		int lis = 0, lisEnd = -1;
		for(int i = 0; i < n; ++i)
		{
			Elephant cur = elephants.get(i);
			int curP = -1, curL = 1;
			for(int j = 0; j < i; ++j)
			{
				Elephant prev = elephants.get(j);
				if(prev.w < cur.w && prev.q > cur.q && L[prev.idx] + 1 > curL)
					curL = L[curP = prev.idx] + 1; 
			}
			L[cur.idx] = curL;
			P[cur.idx] = curP;
			if(curL > lis)
			{
				lis = curL;
				lisEnd = cur.idx;
			}
		}

		Stack<Integer> stack = new Stack<Integer>();
		while(lisEnd != -1)
		{
			stack.push(lisEnd + 1);
			lisEnd = P[lisEnd];
		}
		out.println(lis);
		while(!stack.isEmpty())
			out.println(stack.pop());
		out.flush();
		out.close();
	}

	static class Elephant implements Comparable<Elephant>
	{
		int w, q, idx;
		
		Elephant(int a, int b, int c) { w = a; q = b; idx = c; }
		
		public int compareTo(Elephant e) { return w - e.w; }
		
		public String toString()
		{
			return w + " " + q;
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
	}
}