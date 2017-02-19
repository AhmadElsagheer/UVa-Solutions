package v114;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class FillTheContainers_UVa11413 {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		while(sc.ready())
		{
			int n = sc.nextInt(), m = sc.nextInt();
			int[] c = new int[n];
			for(int i = 0; i < n; ++i)
				c[i] = sc.nextInt();
//			ans = -1: to indicate that no valid answer so far
//			in this problem, there will always be a valid answer
//			in other problems, we may end with no valid answers
//			lo = 1: minimum possible capacity
//			hi = 1e9: maximum possible capacity, in the worst case
//			we will have too many vessels with large capacities
//			and only one container
			int ans = -1, lo = 1, hi = (int)1e9;
			while(lo <= hi)
			{
				int mid = lo + (hi - lo) / 2;	//capacity to test
				if(possible(c, n, m, mid))
				{
					ans = mid;		//mid is the best answer so far
					hi = mid - 1;	//try to find a better one (we need min capacity)
				}
				else
					lo = mid + 1;	//[lo,mid] are not valid, increase lo
			}
			out.println(ans);
		}
		
		out.flush();
		out.close();
	}	
	
	//check whether the capacity being test is valid or not
	static boolean possible(int[] c, int vessels, int containers, int testedCapacity)
	{
		int vesselIndex = 0,  curCap = testedCapacity;
		while(vesselIndex < vessels && containers > 0)
			if(c[vesselIndex] <= curCap)
				curCap -= c[vesselIndex++];
			else
			{
				curCap = testedCapacity;
				--containers;
			}
		return vesselIndex == vessels;
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

		public double nextDouble(String x) 
		{

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
