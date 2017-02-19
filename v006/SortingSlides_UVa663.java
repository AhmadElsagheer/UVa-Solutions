package v006;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class SortingSlides_UVa663 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;
		while(true)
		{
			int n = sc.nextInt();

			if(n == 0)
				break;
			int[] match = new int[n];

			Slide[] slides = new Slide[n];
			for(int i = 0; i < n; ++i)
				slides[i] = new Slide(sc.nextInt(), sc.nextInt(), sc.nextInt(), sc.nextInt());

			for(int j = 0; j < n; ++j)
			{
				Label l = new Label(sc.nextInt(), sc.nextInt());
				for(int i = 0; i < n; ++i)
					if(slides[i].inside(l))
						match[j] |= 1<<i;
					
			}

			Pair[] ans = new Pair[n];
			int count = 0;
			boolean done = false;
			while(!done)
			{
				done = true;
				for(int j = 0; j < n; ++j)
				{
					int msk = match[j];
					if(msk != 0 && (msk & msk - 1) == 0)
					{
						int s = 0;
						while((msk & (1<<s)) == 0) ++s;
						msk = ~msk;
						for(int k = 0; k < n; ++k)
							match[k] &= msk;
						ans[count++] = new Pair(s, j);
						done = false;
					}
				}
				
				for(int j = 0; j < n; ++j)
				{
					int msk = match[j], c = Integer.bitCount(msk) - 1;
					if(c == -1)
						continue;
					for(int k = j + 1; k < n; ++k)
						if(match[k] == msk)
							--c;
					
					if(c == 0)
					{
						for(int k = 0; k < n; ++k)
						{
							int msk2 = match[k];
							if(msk2 != 0 && msk2 != msk && (msk2 & msk) != 0)
							{
								match[k] &= ~msk;
								done = false;
							}
						}
					}
				}
				
			}
			if(count == 0)
				out.printf("Heap %d\nnone\n\n", tc++);
			else
			{
				out.printf("Heap %d\n", tc++);
				Arrays.sort(ans, 0, count);
				for(int i = 0; i < count; ++i)
					out.printf("(%c,%d)%c", (char)(ans[i].x + 'A'), ans[i].y + 1, i == count - 1? '\n' : ' ');
				out.println();
			}

		}

		out.flush();
		out.close();
	}

	static class Pair implements Comparable<Pair>
	{
		int x, y;

		Pair(int a, int b) { x = a; y = b; }

		public int compareTo(Pair p) { return x - p.x; }
	}

	static class Slide
	{
		int x1, x2, y1, y2, occ;

		Slide(int a, int b, int c, int d) { x1 = a; x2 = b; y1 = c; y2 = d; }

		boolean inside(Label p) { return p.x > x1 && p.x < x2 && p.y > y1 && p.y < y2; }	
	}

	static class Label
	{
		int x, y;

		Label(int a, int b) { x = a; y = b; }
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