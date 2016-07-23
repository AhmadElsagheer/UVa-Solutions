package v007;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


public class DNASequencing_UVa760 {
	
	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		boolean first = true;
		while(sc.ready())
		{
			if(first)
				first = false;
			else
				out.println();
			String s1 = sc.next(), s2 = sc.next();
			Trie t = new Trie();
			t.put(s1, 1);
			t.put(s2, 2);
			sb = new StringBuilder();
			max = 0;
			t.find();
			if(max > 0)
				out.print(sb);
			else
				out.println("No common sequence.");
		}
		out.flush();
		out.close();
	}
	
	static int max;
	static StringBuilder sb = new StringBuilder();
	
	static class Trie
	{
		static class Node { Node[] next = new Node[26]; int val; }
		
		Node root = new Node();
		
		void put(String s, int val)
		{
			char[] t = s.toCharArray();
			for(int i = 0; i < t.length; ++i)
				put(t, i, val);
		}
		
		void put(char[] s, int idx, int val)
		{
			Node cur = root;
			cur.val |= val;
			while(idx < s.length)
			{
				Node nxt = cur.next[s[idx]-'a'];
				if(nxt == null)
					nxt = cur.next[s[idx]-'a'] = new Node();
				cur = nxt;
				cur.val |= val;
				++idx;
			}
		}
		
		static char[] s; 
		
		void find() { s = new char[310]; find(root, 0); }
		
		void find(Node cur, int depth)
		{
			if(cur.val != 3)
				return;
			for(int i = 0; i < 26; ++i)
			{
				Node nxt = cur.next[i];
				if(nxt != null)
				{
					s[depth] = (char)(i + 'a');
					find(nxt, depth + 1);
				}
			}
			
			if(depth > max)
			{
				sb = new StringBuilder();
				max = depth;
			}
			if(depth == max)
				sb.append(Arrays.copyOf(s, depth)).append("\n");
		}
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