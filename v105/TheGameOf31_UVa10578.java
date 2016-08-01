package v105;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class TheGameOf31_UVa10578 {

	static Boolean[] memo;
	
	static boolean win(int a, int b, int c, int d, int e, int f, int s)
	{
		int state = getState(a, b, c, d, e, f, s);
		if(memo[state] != null)
			return memo[state];
		boolean win = false;
		int[] arr = new int[] {a, b, c, d, e, f};
		for(int i = 0; i < 6; ++i)
			if(arr[i] > 0 && i + 1 <= s)
			{
				arr[i]--;
				win |= !win(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], s - i - 1);
				arr[i]++;
			}
		return memo[state] = win;
	}
	
	static int getState(int a, int b, int c, int d, int e, int f, int s)
	{
		return (((((a * 5 + b) * 5 + c) * 5 + d) * 5 + e) * 5 + f) * 32 + s;
	}
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		memo = new Boolean[5 * 5 * 5 * 5 * 5 * 5 * 32];
		while(sc.ready())
		{
			int[] arr = new int[6];
			Arrays.fill(arr, 4);
			String s = sc.next();
			int sum = 31;
			for(int i = 0; i < s.length(); ++i)
			{
				sum -= s.charAt(i)-'0';
				arr[s.charAt(i)-'0'-1]--;
			}
			boolean res = win(arr[0], arr[1], arr[2], arr[3], arr[4], arr[5], sum);
			out.printf("%s %c\n", s, s.length()%2 == 0 && res || s.length()%2 == 1 && !res ? 'A' : 'B');
		}
		out.flush();
		out.close();
	}

	static void debug(int...x)
	{
		for(int a: x)
			System.out.print(a + " ");
		System.out.println();
	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

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

		public boolean hasNext() throws IOException
		{
			while(br.ready() && (st == null || !st.hasMoreTokens()))
				st = new StringTokenizer(br.readLine());
			return st.hasMoreTokens();
		}


	}
}