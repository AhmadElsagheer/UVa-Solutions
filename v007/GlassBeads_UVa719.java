package v007;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class GlassBeads_UVa719 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		while(tc-->0)
		{
			String s = sc.next();
			SuffixAutomaton sa = new SuffixAutomaton((s+s).toCharArray());
			out.println(sa.query(s.length()) - s.length() + 2);
		}
		out.flush();
		out.close();
	}
	
	static class SuffixAutomaton
	{
		int[] len, link, endPos;
		TreeMap<Character, Integer>[] next;
		int idx, lst;
		
		SuffixAutomaton(char[] s)
		{
			int n = s.length;
			len = new int[n<<1];
			link = new int[n<<1];
			endPos = new int[n<<1];
			next = new TreeMap[n<<1];
			next[0] = new TreeMap<>();
			for(int i = 0; i < n; ++i)
				addLetter(s[i], i);
		}
		
		void addLetter(char c, int i)
		{
			int cur = ++idx;
			
			int p = lst;
			while(!next[p].containsKey(c)) { next[p].put(c, cur); p = link[p]; }
			
			int q = next[p].get(c);
			if(q != cur)
				if(len[q] == len[p] + 1)
					link[cur] = q;
				else
				{
					int clone = ++idx;
					next[clone] = new TreeMap<>(next[q]);
					link[clone] = link[q];
					endPos[clone] = endPos[q];
					len[clone] = len[p] + 1;
					link[q] = link[cur] = clone;
					while(next[p].get(c) == q) { next[p].put(c, clone); p = link[p]; }
				}
			len[cur] = len[lst] + 1;
			endPos[cur] = i;
			next[cur] = new TreeMap<>();
			lst = cur;
		}
		
		int query(int k)
		{
			int u = 0;
			while(k-- > 0)
				u = next[u].firstEntry().getValue();
			return endPos[u];
		}
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


	}
}