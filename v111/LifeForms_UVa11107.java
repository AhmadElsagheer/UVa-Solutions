package v111;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;


public class LifeForms_UVa11107 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		boolean first = true;
		while(true)
		{
			int n = sc.nextInt();
			if(n == 0)
				break;
			if(first)
				first = false;
			else
				out.println();
			String[] s = new String[n];
			int len = 0;
			for(int i = 0; i < n; ++i)
			{
				s[i] = sc.next();
				len += s[i].length();
			}
			SuffixArray sa = new SuffixArray(s, len);
			out.print(sa.lcs());
		}
		out.flush();
		out.close();
	}
	
	static class ModifiedQueue
	{
		Queue<Integer> q = new LinkedList<Integer>();
		Deque<Integer> d = new LinkedList<Integer>();
		
		void add(int x)
		{
			q.add(x);
			while(!d.isEmpty() && d.getLast() > x)
				d.removeLast();
			d.addLast(x);
		}
		
		void remove()
		{
			int x = q.remove();
			if(d.peek() == x)
				d.removeFirst();
		}
		
		int query() { return d.peek(); }
	}
	static class SuffixArray
	{
		int n, k, SA[], idxOf[];
		char[] s, ss;
		
		SuffixArray(String[] in, int N)
		{
			s = merge(in, N);
			ss = new char[n];
			for(int i = 0; i < n; ++i)
				ss[i] = (char)(s[i] - 5);
			SA = new int[n];
			int[] RA = new int[n];
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
				if(RA[SA[n-1]] == n-1)
					break;
			}
		}
		
		char[] merge(String[] in, int N)
		{
			n = N + (k = in.length);
			idxOf = new int[n];
			StringBuilder sb = new StringBuilder();
			for(int i = 0, k = 0; i < in.length; ++i)
			{
				String s = in[i];
				for(int j = 0; j < s.length(); ++j)
				{
					idxOf[k++] = i;
					sb.append((char)(s.charAt(j) + 5));
				}
				idxOf[k++] = i;
				sb.append((char)(i));
			}
			return sb.toString().toCharArray();
		}
		
		void sort(int[] SA, int[] RA, int n, int k)
		{
			int maxi = Math.max(n, 300), c[] = new int[maxi];
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
		
		String lcs()
		{
			StringBuilder ans = new StringBuilder();
			int[] phi = new int[n];
			phi[SA[0]] = -1;
			for(int i = 1; i < n; ++i)
				phi[SA[i]] = SA[i-1];
			int[] PLCP = new int[n], LCP = new int[n];
			for(int i = 0, L = 0; i < n; ++i)
			{
				if(phi[i] == -1)
					continue;
				while(s[i + L] == s[phi[i] + L])
					++L;
				PLCP[i] = L;
				if(L != 0)
					--L;
			}
			
			for(int i = 0; i < n; ++i)
				LCP[i] = PLCP[SA[i]];
			
			ModifiedQueue mq = new ModifiedQueue();
			Queue<Integer> q = new LinkedList<Integer>();
			String lst = null;
			int[] in = new int[k];
			int count = 0, maxL = 0;
			for(int i = 0; i < n; ++i)
			{
				int idx = idxOf[SA[i]];
				mq.add(LCP[i]);
				q.add(idx);
				if(in[idx]++ == 0)
					++count;
				
				while(count > k / 2)
				{
					mq.remove();
					int x = q.remove();
					if(--in[x] == 0)
						--count;
					int L = mq.query();
					if(L > maxL)
					{
						maxL = L;
						ans = new StringBuilder();
					}
					
					if(maxL != 0 && L == maxL)
					{
						String cur = new String(Arrays.copyOfRange(ss, SA[i], SA[i] + L));
						if(lst == null || !lst.equals(cur))
						{
							ans.append(cur).append("\n");
							lst = cur;
						}
					}
				}
				
			}
			if(ans.length() == 0)
				return "?\n";
			return ans.toString();
		}
	}
	

//	public static void main(String[] args) throws IOException 
//	{
//		Scanner sc = new Scanner(System.in);
//		PrintWriter out = new PrintWriter(System.out);
//
//		boolean first = true;
//		while(true)
//		{
//			int k = sc.nextInt();
//			if(k == 0)
//				break;
//			if(first)
//				first = false;
//			else
//				out.println();
//			char[][] s = new char[k][];
//			for(int i = 0; i < k; ++i)
//				s[i] = sc.next().toCharArray();
//			SuffixAutomaton sa = new SuffixAutomaton(s, k);
//			out.print(sa.print());
//		}
//		out.flush();
//		out.close();
//	}
//
//	static class SuffixAutomaton
//	{
//		int[] len, link, endPos, strIdx, strStart, validLen;
//		int idx, lst, count;
//		TreeMap<Character, Integer>[] next;
//		BitSet[] terminal;
//		StringBuilder str;
//
//		SuffixAutomaton(char[][] s, int k)
//		{
//			int n = k;
//			for(char[] t: s)
//				n += t.length;
//			/*
//			 * These 3 arrays are to handle the substrings that cross delimiters
//			 * for example: aacd#cdaa. cd and #cd will exist in the same state
//			 * and we want to consider the state len to be equal to 2 not 3
//			 */
//			strIdx = new int[n];
//			strStart = new int[k];
//			validLen = new int[n<<1];
//			Arrays.fill(validLen, -1);
//			count = k;
//			len = new int[n<<1];
//			link = new int[n<<1];
//			endPos = new int[n<<1];
//			terminal = new BitSet[n<<1];
//			next = new TreeMap[n<<1];
//			next[0] = new TreeMap<>();
//			str = new StringBuilder();
//			int i = 0, j = 0;
//			for(char[] t: s)
//			{
//				strStart[i] = j;
//				for(char c: t)
//				{
//					addLetter(c, j++);
//					strIdx[j] = i;
//				}
//				addLetter((char)(300 + i++), j++);
//			}
//			dp = new int[n<<1];
//			Arrays.fill(dp, -1);
//		}
//		
//		int validLen(int v)
//		{
//			if(validLen[v] == -1)
//				validLen[v] = Math.min(len[v], endPos[v] - strStart[strIdx[endPos[v]]] + 1);
//			return validLen[v];
//		}
//
//		int[] dp;
//
//		int dfs(int u)
//		{
//			if(dp[u] != -1)
//				return dp[u];
//			int ans = 0;
//			terminal[u] = new BitSet(count);
//			for(Entry<Character, Integer> e: next[u].entrySet())
//				if(e.getKey() >= 300)
//					terminal[u].set(e.getKey() - 300);
//				else
//				{
//					ans = Math.max(ans, dfs(e.getValue()));
//					terminal[u].or(terminal[e.getValue()]);
//				}
//			if(terminal[u].cardinality() > count / 2)		
//				ans = Math.max(ans, validLen(u));
//
//			return dp[u] = ans;
//		}
//				
//		String print()
//		{
//			int ans = dfs(0);
//			if(ans == 0)
//				return "?\n";
//			ArrayList<String> sol = new ArrayList<String>();
//			for(int u = 0; u <= idx; ++u)
//				if(terminal[u] != null && terminal[u].cardinality() > count / 2 && validLen(u) == ans)
//					sol.add(str.substring(endPos[u] - validLen(u) + 1, endPos[u] + 1));
//				
//			Collections.sort(sol);	
//			StringBuilder sb = new StringBuilder();
//			for(String s: sol)
//				sb.append(s).append("\n");
//			return sb.toString();
//		}
//				
//		void addLetter(char c, int i)
//		{
//			str.append(c);
//			int cur = ++idx, p = lst;
//
//			while(!next[p].containsKey(c)) { next[p].put(c, cur); p = link[p]; }
//
//			int q = next[p].get(c);
//			if(q != cur)
//				if(len[q] == len[p] + 1)
//					link[cur] = q;
//				else
//				{
//					int clone = ++idx;
//					len[clone] = len[p] + 1;
//					link[clone] = link[q];
//					endPos[clone] = endPos[q];
//					next[clone] = new TreeMap<>(next[q]);
//					link[q] = link[cur] = clone;
//					while(next[p].get(c) == q) { next[p].put(c, clone); p = link[p]; }
//				}
//
//			endPos[cur] = i;
//			len[cur] = len[lst] + 1;
//			next[cur] = new TreeMap<>();
//			lst = cur;
//		}
//	}

	
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