package v012;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;


public class Top10_UVa1254 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int N = sc.nextInt();
		Pair[] words = new Pair[N];
		for(int i = 0; i < N; ++i)
			words[i] = new Pair(i + 1, sc.next());
		Arrays.sort(words);
		SuffixAutomaton sa = new SuffixAutomaton(words);

		StringBuilder sb = new StringBuilder();
		int Q = sc.nextInt();
		while(Q-->0)
		{
			int[] a = sa.query(sc.next().toCharArray());
			if(a == null)
				sb.append("-1\n");
			else
			{
				boolean first = true;
				for(int x: a)
					if(x == 0)
						break;
					else
					{
						if(first)
							first = false;
						else
							sb.append(" ");
						sb.append(x);
					}
				sb.append("\n");
			}
		}
		out.print(sb);
		out.flush();
		out.close();
	}

	static class Pair implements Comparable<Pair>
	{
		int idx;
		String s;

		Pair(int a, String b) { idx = a; s = b; }

		public int compareTo(Pair p) 
		{ 
			if(s.length() != p.s.length())
				return s.length() - p.s.length();
			return s.compareTo(p.s); 		
		}
	}

	static class SuffixAutomaton
	{
		Pair[] words;
		int[] link, len;
		TreeMap<Character, Integer>[] next;
		int lst, idx;

		SuffixAutomaton(Pair[] words)
		{
			this.words = words;
			int n = words.length;
			for(Pair w: words)
				n += w.s.length();
			link = new int[n<<1];
			len = new int[n<<1];
			next = new TreeMap[n<<1];
			next[0] = new TreeMap<>();
			int i = 0;
			for(Pair w: words)
			{
				for(char c: w.s.toCharArray())
					addLetter(c);
				addLetter((char)(300 + i++));
			}
			set = new TreeSet[n<<1];
			dfs(0);
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

		TreeSet<Integer>[] set;

		void dfs(int u)
		{
			set[u] = new TreeSet<>();
			for(Entry<Character, Integer> e: next[u].entrySet())
				if(e.getKey() >= 300)
					set[u].add(e.getKey() - 300);
				else
				{
					if(set[e.getValue()] == null)
						dfs(e.getValue());

					int count = 0;
					for(int i: set[e.getValue()])
						if(count++ == 10)
							break;
						else
							set[u].add(i);
				}
		}

		int get(char[] c)
		{
			int u = 0;
			for(char cc: c)
				u = next[u].get(cc);
			return u;
		}

		int[] query(char[] s)
		{
			int a[] = new int[10];
			Integer u = 0;
			for(char c: s)
			{
				u = next[u].get(c);
				if(u == null)
					return null;
			}
			if(set[u].size() == 0)
				return null;
			int i = 0;
			for(int x: set[u])
				if(i == 10)
					break;
				else
					a[i++] = words[x].idx;
			return a;
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