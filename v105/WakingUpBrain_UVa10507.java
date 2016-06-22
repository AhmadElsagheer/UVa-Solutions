package v105;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.StringTokenizer;


public class WakingUpBrain_UVa10507 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		while(sc.ready())
		{
			int N = sc.nextInt(), M = sc.nextInt();
			boolean[][] adjMat = new boolean[26][26];
			int[] wakeUp = new int[26];
			for(char c: sc.next().toCharArray())
				wakeUp[c-'A'] = 2;
			while(M-->0)
			{
				char[] e = sc.next().toCharArray();
				int u = e[0] - 'A', v = e[1] - 'A';
				adjMat[u][v] = adjMat[v][u] = true;
				wakeUp[u] |= 1;
				wakeUp[v] |= 1;
			}
			
			int W = 3, ans = 0;
			Stack<Integer> stack = new Stack<Integer>();
			while(W != N)
			{
				for(int i = 0; i < 26; ++i)
					if(wakeUp[i] == 1)
					{
						int supp = 0;
						for(int j = 0; j < 26; ++j)
							if(adjMat[i][j] && (wakeUp[j] & 2) != 0)
								++supp;
						if(supp >= 3)
							stack.push(i);
					}
				if(stack.isEmpty())
					break;
				W += stack.size();
				while(!stack.isEmpty())
					wakeUp[stack.pop()] |= 2;
				++ans;
			}
			
			if(W != N)
				out.println("THIS BRAIN NEVER WAKES UP");
			else
				out.printf("WAKE UP IN, %d, YEARS\n", ans);
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