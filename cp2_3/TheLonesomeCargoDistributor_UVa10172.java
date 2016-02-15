package cp2_3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class TheLonesomeCargoDistributor_UVa10172 {

	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt(), S = sc.nextInt(), Q = sc.nextInt(), totalRem = 0;
			Stack<Integer> cargo = new Stack<Integer>();
			Queue<Integer>[] B = new Queue[N];
			for(int i = 0; i < N; ++i)
			{
				B[i] = new LinkedList<Integer>();
				int k = sc.nextInt();
				totalRem += k;
				while(k-->0)
					B[i].add(sc.nextInt() - 1);
			}
			int totalTime = 0, curPos = 0;
			while(true)
			{
				//unload
				while(!cargo.isEmpty())
				{
					int item = cargo.pop();
					if(item == curPos)
					{
						++totalTime;
						--totalRem;
					}
					else
						if(B[curPos].size() < Q)
						{
							++totalTime;
							B[curPos].add(item);
						}
						else
						{
							cargo.push(item);
							break;
						}
				}
				//load
				while(!B[curPos].isEmpty() && cargo.size() < S)
				{
					cargo.push(B[curPos].remove());
					++totalTime;
				}
				if(totalRem == 0)
					break;
				totalTime += 2;
				curPos = (curPos + 1)%N;
			}
			out.println(totalTime);
			
			
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

		public boolean ready() throws IOException {return br.ready();}

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


	}
}
