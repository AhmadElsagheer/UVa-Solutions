package v001;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class StackingBoxes_UVa103 {

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in); 
		PrintWriter out = new PrintWriter(System.out);
		StringBuilder sb = new StringBuilder();
		
		while(sc.ready())
		{
			int m = sc.nextInt(), n = sc.nextInt();
			Box[] boxes = new Box[m];
			for(int i = 0; i < m; ++i)
			{
				int[] a = new int[n];
				for(int j = 0; j < n; ++j)
					a[j] = sc.nextInt();
				boxes[i] = new Box(a, i + 1);
			}
			Arrays.sort(boxes);
			int lis = 0, lisEnd = -1, L[] = new int[m], P[] = new int[m];
			for(int i = 0; i < m; ++i)
			{
				Box curBox = boxes[i];
				int curLis = 1, curP = -1;
				for(int j = 0; j < i; ++j)
					if(curBox.fit(boxes[j]) && L[j] + 1 > curLis)
						curLis = L[curP = j] + 1;
				L[i] = curLis;
				P[i] = curP;
				if(curLis > lis)
				{
					lis = curLis;
					lisEnd = i;
				}
			}
			sb.append(lis + "\n");
			while(lisEnd != -1)
			{
				sb.append(boxes[lisEnd].idx);
				if((lisEnd = P[lisEnd]) != -1)
					sb.append(" ");
			}
			sb.append("\n");
		}
		out.print(sb);
		out.flush();
		out.close();
	}
	
	static class Box implements Comparable<Box>
	{
		int idx, d[];
		
		Box(int[] a, int b) 
		{
			Arrays.sort(a);
			d = a;
			idx = b;
		}
		
		public int compareTo(Box b)
		{
			for(int i = 0; i < d.length; ++i)
				if(d[i] < b.d[i])
					return 1;
				else if(d[i] > b.d[i])
					return -1;
			return 0;
		}
		
		boolean fit(Box b)
		{
			for(int i = 0; i < d.length; ++i)
				if(d[i] >= b.d[i])
					return false;
			return true;
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

		public boolean nextEmpty() throws IOException
		{
			String s = nextLine();
			st = new StringTokenizer(s);
			return s.isEmpty();
		}
	}
}