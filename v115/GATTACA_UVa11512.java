package v115;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class GATTACA_UVa11512 {

	/*
	 * Solution 1: Suffix Array
	 * Solution 2: Suffix Trie - AC 2130 ms
	 */
	
// Solution 1 ==============================================================================
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			String s = sc.next() + "$";
			SuffixArray sa = new SuffixArray(s.toCharArray());
			out.println(sa.LCP());
		}
		out.flush();
		out.close();
	}
	
	static class SuffixArray
	{
		int[] SA;
		char[] s;
		
		SuffixArray(char[] s)
		{
			this.s = s;
			int n = s.length, RA[] = new int[n];
			SA = new int[n];
			for(int i = 0; i < n; ++i) { SA[i] = i; RA[i] = s[i]; }
			
			for(int k = 1; k < n; k <<= 1)
			{
				sort(SA, RA, n, k);
				sort(SA, RA, n, 0);
				
				int[] tmp = new int[n];
				for(int i = 1, r = 0, s1 = SA[0], s2; i < n; ++i)
				{
					s2 = SA[i];
					tmp[s2] = RA[s1] == RA[s2] && RA[s1 + k] == RA[s2 + k] ? r : ++r;
					s1 = s2;
				}
				
				for(int i = 0; i < n; ++i)
					RA[i] = tmp[i];
				
				if(RA[SA[n-1]] == n - 1)
					break;
			}
		}
		
		void sort(int[] SA, int[] RA, int n, int k)
		{
			int maxi = Math.max(256, n), c[] = new int[maxi];
			for(int i = 0; i < n; ++i)
				c[i + k < n ? RA[i + k] : 0]++;
			for(int i = 0, sum = 0; i < maxi; ++i)
			{
				int t = c[i];
				c[i] = sum;
				sum += t;
			}
			int[] tmp = new int[n];
			for(int i = 0; i < n; ++i)
			{
				int j = SA[i] + k;
				tmp[c[j < n ? RA[j] : 0]++] = SA[i];
			}
			
			for(int i = 0; i < n; ++i)
				SA[i] = tmp[i];
		}
		
		String LCP()
		{
			int n = s.length;
			int maxL = 0, idx = -1, LCP[] = new int[n], phi[] = new int[n], PLCP[] = new int[n];
			phi[SA[0]] = -1;
			for(int i = 1; i < n; ++i)
				phi[SA[i]] = SA[i-1];
			
			for(int i = 0, L = 0; i < n; ++i)
			{
				if(phi[i] == -1)
					continue;
				int j = phi[i];
				while(s[i + L] == s[j + L])
					++L;
				maxL = Math.max(maxL, L);
				PLCP[i] = L;
				if(L != 0)
					--L;
			}
			for(int i = 0; i < n; ++i)
			{
				LCP[i] = PLCP[SA[i]];
				if(idx == -1 && LCP[i] == maxL)
					idx = i;
			}

			if(maxL == 0)
				return "No repetitions found!";
			else
			{
				String ret = new String(Arrays.copyOfRange(s, SA[idx], SA[idx++] + maxL)); 
				int occ = 2;
				while(idx < n && LCP[idx] == LCP[idx-1])
				{
					++occ;
					++idx;
				}
				return ret + " " + occ;
			}
		}
	}
	
// Solution 2 ==============================================================================
	
	public static void sol2() throws IOException
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			char[] s = sc.next().toCharArray();
			Trie t = new Trie();
			t.put(s);
			Pair ans = t.longestString();
			if(ans.len == 0)
				out.println("No repetitions found!");
			else
			{
				StringBuilder sb = new StringBuilder();
				while(ans.nxt != null)
				{
					sb.append((char)(ans.c + 'A'));
					ans = ans.nxt;
				}
				out.println(sb + " " + ans.occ);
			}
		}
		out.flush();
		out.close();
	}

	static class Node { Node[] next = new Node[26]; int count; }
	
	static class Trie
	{
		
		Node root = new Node();
		
		void put(char[] s)
		{
			for(int i = 0; i < s.length; ++i)
				put(s, i);
		}
		
		void put(char[] s, int idx)
		{
			Node cur = root;
			root.count++;
			while(idx < s.length)
			{
				Node nxt = cur.next[s[idx]-'A'];
				if(nxt == null)
					nxt = cur.next[s[idx]-'A'] = new Node();
				++idx;
				nxt.count++;
				cur = nxt;
			}
		}
		
		Pair longestString() { return longestString(root, 0); }
		
		Pair longestString(Node cur, int depth)
		{
			if(cur.count < 2)
				return new Pair(0, 0, -1, null);
			Pair ret = new Pair(depth, cur.count, -1, null);
			
			for(int i = 0; i < 26; ++i)
			{
				Node nxt = cur.next[i];
				if(nxt != null)
				{
					Pair p = longestString(nxt, depth + 1);
					if(p.len > ret.len)
						ret = new Pair(p.len, p.occ, i, p);
				}
			}
			return ret;
		}
	}

	static class Pair 
	{ 
		int len, occ, c; Pair nxt; 
		
		Pair(int a, int b, int d, Pair e) { len = a; occ = b; c = d; nxt = e; }
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}


	}
}