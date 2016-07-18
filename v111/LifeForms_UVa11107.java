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