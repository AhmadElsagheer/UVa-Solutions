package v103;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class WateringGrass_UVa10382 {

	static final double EPS = 1e-7;
	
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();
		

		while(sc.ready())
		{
			int n = sc.nextInt(), l = sc.nextInt(), w = sc.nextInt();
			
			Interval[] sprinkles = new Interval[n];
			
			for(int i = 0; i < n; i++)
			{
				int pos = sc.nextInt(), r = sc.nextInt();
				double d = Math.sqrt((double)r * r - w * w / 4.0);
				sprinkles[i] = new Interval(pos - d, pos + d);
				
			}
			
			Arrays.sort(sprinkles);
			
			double a = 0, b = l;
			int cur = 0, count = 0;
			
			while(a + EPS < b)
			{
				double maxB = a;
				while(cur < n && sprinkles[cur].left <= a + EPS)
					maxB = Math.max(maxB, sprinkles[cur++].right);
				if(Math.abs(maxB - a) < EPS)	
					break;
				count++;
				a = maxB;
			}
			if(a + EPS < b)
				sb.append(-1).append("\n");
			else
				sb.append(count).append("\n");
		}
		
		System.out.print(sb);
	}
	
	
	
	static class Interval implements Comparable<Interval>
	{
		double left, right;
		
		Interval(double x, double y) {left = x; right = y;}

		
		public int compareTo(Interval o) 
		{	
			if(Math.abs(left - o.left) < EPS) 
				return right - o.right > 0 ? 1 : -1;
			
			return left - o.left > 0 ? 1 : -1;
		}
		
		
	}
	
	
	static class Scanner {
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


	}
}
