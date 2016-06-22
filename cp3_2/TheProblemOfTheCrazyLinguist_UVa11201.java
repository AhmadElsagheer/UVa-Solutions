package cp3_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class TheProblemOfTheCrazyLinguist_UVa11201 {

	static final double EPS = 1e-9;
	static char[] vowels, cons, t;
	static double sum[][], count[][], avg[][], prob[];
	static int[] used;

	static void bt(int i, double s)
	{
		if(i != 0)
		{
			sum[i][t[0]-'a'] += s;
			count[i][t[0]-'a']++;
		}

		if(i == 7)
			return;

		char[] f = i%2 == 0 ? cons : vowels;
		for(char c: f)
			if(used[c-'a'] < 2)
			{
				t[i] = c;
				used[c-'a']++;
				bt(i + 1, s + (i + 1) * prob[c-'a']);
				used[c-'a']--;
			}
	}

	static void pre()
	{
		prob = new double[] { 12.53, 1.42, 4.68, 5.86, 13.68, 0.69, 1.01, 0.70, 6.25, 0.44, 0.00, 4.97, 3.15,
				6.71, 8.68, 2.51, 0.88, 6.87, 7.98, 4.63, 3.93, 0.90, 0.02, 0.22, 0.90, 0.52 };
		vowels = "aeiou".toCharArray();
		cons = "bcdfghjklmnpqrstvwxyz".toCharArray();
		sum = new double[8][26];
		count = new double[8][26];
		avg = new double[8][26];
		t = new char[8];
		used = new int[26];
		bt(0, 0.0);
		for(int i = 1; i < 8; ++i)
			for(int j = 0; j < 26; ++j)
				avg[i][j] = sum[i][j] / count[i][j];
	}

	static double compute(char[] s)
	{
		double sum = 0.0;
		for(int i = 0; i < s.length; ++i)
			sum += (i + 1) * prob[s[i] - 'a'];
		return sum;
	}

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		pre();

		int tc = sc.nextInt();
		while(tc-->0)
		{
			char[] s = sc.next().toCharArray();
			if(compute(s) + EPS >= avg[s.length][s[0]-'a'])
				out.println("above or equal");
			else
				out.println("below");
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