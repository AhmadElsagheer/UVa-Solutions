package v012;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;


public class Editor_UVa1223 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		while(tc-->0)
		{
			SuffixAutomaton sa = new SuffixAutomaton(sc.next().toCharArray());
			out.println(sa.query());
		}
		out.flush();
		out.close();
	}
	
	static class SuffixAutomaton
	{
		int[] len, link, terminal;
		int idx, lst;
		TreeMap<Character, Integer>[] next;
		
		SuffixAutomaton(char[] s)
		{
			int n = s.length;
			len = new int[n<<1];
			link = new int[n<<1];
			terminal = new int[n<<1];
			next = new TreeMap[n<<1];
			next[0] = new TreeMap<>();
			for(char c: s)
				addLetter(c);
			while(lst != 0)
			{
				terminal[lst] = 1;
				lst = link[lst];
			}
			occ = new int[n<<1];
			Arrays.fill(occ, -1);
		}
		
		int[] occ;
		
		int occ(int u)
		{
			if(occ[u] != -1)
				return occ[u];
			int ret = terminal[u];
			for(int v: next[u].values())
				ret += occ(v);
			return occ[u] = ret;
		}
		
		int query()
		{
			int ans = 0;
			for(int i = 0; i <= idx; ++i)
				if(occ(i) >= 2)
					ans = Math.max(ans, len[i]);
			return ans;
		}
		
		void addLetter(char c)
		{
			int cur = ++idx, p = lst;
			
			while(!next[p].containsKey(c)) { next[p].put(c, cur); p = link[p]; }
			
			int q = next[p].get(c);
			if(q != cur)
				if(len[q] == len[p] + 1)
					link[cur] = q;
				else
				{
					int clone = ++idx;
					len[clone] = len[p] + 1;
					link[clone] = link[q];
					next[clone] = new TreeMap<>(next[q]);
					link[q] = link[cur] = clone;
					while(next[p].get(c) == q) { next[p].put(c, clone); p = link[p]; }
				}
			
			len[cur] = len[lst] + 1;
			next[cur] = new TreeMap<>();
			lst = cur;
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