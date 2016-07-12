package v100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class EditStepLadders_UVa10029 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		Trie trie = new Trie();
		ArrayList<String> dic = new ArrayList<String>();
		int n = 0;

		while(sc.ready())
		{
			String s = sc.nextLine().toLowerCase();
			dic.add(s);
			trie.put(s, n++);

		}

		int ans = 0;
		int[] dp = new int[n];
		Arrays.fill(dp, 1);
		for(int i = 0; i < n; ++i)
		{
			ans = Math.max(ans, dp[i]);
			sol = new ArrayList<Integer>();
			trie.search(dic.get(i));

			for(int j: sol)
				dp[j] = Math.max(dp[j], dp[i] + 1);
		}		
		out.println(ans);
		out.flush();
		out.close();
	}

	static ArrayList<Integer> sol;

	static class Trie
	{
		static class Node { int idx = -1; Node next[] = new Node[26]; }

		Node root;

		void put(String s, int val) { root = put(root, s.toCharArray(), 0, val); }

		Node put(Node cur, char[] s, int idx, int val)
		{
			if(cur == null)
				cur = new Node();

			if(idx == s.length)
				cur.idx = val;
			else
				cur.next[s[idx]-'a'] = put(cur.next[s[idx]-'a'], s, idx + 1, val);

			return cur;
		}

		void search(String s)
		{
			search(root, s.toCharArray(), 0, false);
		}

		void search(Node cur, char[] s, int idx, boolean change)
		{
			if(cur == null)
				return;
			if(idx == s.length)
			{
				if(change && cur.idx != -1)
					sol.add(cur.idx);
				if(!change)
					for(int cc = 0; cc < 26; ++cc)
						search(cur.next[cc], s, idx, true);
				return;
			}
			int c = s[idx] - 'a';
			//continue
			search(cur.next[c], s, idx + 1, change);

			if(change)
				return;

			for(int cc = c + 1; cc < 26; ++cc)
			{
				//alter
				search(cur.next[cc], s, idx + 1, true);
				//add
				search(cur.next[cc], s, idx, true);
			}

			//delete
			search(cur, s, idx + 1, true);
		}
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

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}

	}
}