package v010;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Network_UVa1064 {

	static int min, N, M, size[], order[];
	static Packet[] seq;

	static void findMin(int idx, int msk)
	{
		if(idx == N)
		{
			min = Math.min(min, simulate());
			return;
		}
		for(int i = 0; i < N; ++i)
			if((msk & 1<<i) == 0)
			{
				order[idx] = i;
				findMin(idx + 1, msk | 1<<i);
			}
	}

	static int simulate()
	{
		int curSize = 0, minSize = 0, msgIdx = 0, curMsg = order[0], curByte = 0, bytes[][] = new int[N][];
		for(int i = 0; i < N; ++i)
			bytes[i] = new int[size[i]];
		for(Packet p: seq)
		{
			for(int i = p.l; i <= p.r; ++i)
				bytes[p.m][i] = 1;
			curSize += p.r - p.l + 1;
			while(msgIdx < N)
			{
				while(curByte < bytes[curMsg].length && bytes[curMsg][curByte] == 1)
				{
					++curByte;
					--curSize;
				}
				if(curByte != bytes[curMsg].length || msgIdx == N - 1)
					break;

				curByte = 0;
				curMsg = order[++msgIdx];

			}
			minSize = Math.max(minSize, curSize);
		}
		return minSize;
	}

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			N = sc.nextInt(); M = sc.nextInt();
			if(N == 0)
				break;
			size = new int[N];
			for(int i = 0; i < N; ++i)
				size[i] = sc.nextInt();
			seq = new Packet[M];
			for(int i = 0; i < M; ++i)
				seq[i] = new Packet(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt() - 1);
			min = Integer.MAX_VALUE;
			order = new int[N];
			findMin(0, 0);
			out.printf("Case %d: %d\n\n", tc++, min);
		}
		out.flush();
		out.close();
	}

	static class Packet
	{
		int l, r, m;

		Packet(int x, int y, int z) { m = x; l = y; r = z; }
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