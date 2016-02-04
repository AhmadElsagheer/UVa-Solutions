package cp3_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.StringTokenizer;

public class Bandwidth_UVa140 {

	static ArrayList<Integer>[] adjList;
	static int V;
	
	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(true)
		{
			String line = sc.next();
			if(line.equals("#"))
				break;
			StringTokenizer st = new StringTokenizer(line, ";");
			BitSet valid = new BitSet(26);
			adjList = new ArrayList[26];
			for(int i = 0; i < 26; ++i)
				adjList[i] = new ArrayList<Integer>();
			
			while(st.hasMoreTokens())
			{
				String s = st.nextToken();
				int u = s.charAt(0) - 'A';
				valid.set(u);
				
				for(int j = 2; j < s.length(); ++j)
				{	
					int v = s.charAt(j) - 'A';
					adjList[u].add(v);
					valid.set(v);
				}
			}
			V = valid.cardinality();
			int[] nodes = new int[V];
			for(int i = 0, j = 0; i < 26; ++i)
				if(valid.get(i))
					nodes[j++] = i;
			Arrays.sort(nodes);
			int min = V;
			int[] ans = new int[V];
			do
			{
				int[] pos = new int[26];
				for(int i = 0; i < V; ++i)
					pos[nodes[i]] = i;
				int cur = 0;
				for(int u = 0; u < 26; ++u)
					for(int v: adjList[u])
						cur = Math.max(cur, Math.abs(pos[u] - pos[v]));
				if(cur < min)
				{
					min = cur;
					ans = Arrays.copyOf(nodes, V);
				}
			}
			while(nextPermutation(nodes));
			for(int u: ans)
				out.format("%c ", (char) (u + 'A'));
			out.format("-> %d\n", min);
		}
		out.flush();
		out.close();
	}	
	
    static boolean nextPermutation(int[] c) 
    {
        // 1. finds the largest k, that c[k] < c[k+1]
        int first = getFirst(c);
        if ( first == -1 )
            return false;

        // 2. find last index toSwap, that c[k] < c[toSwap]
        int toSwap = c.length - 1;
        while (c[first] >= c[toSwap])
            --toSwap;

        // 3. swap elements with indexes first and last
        swap(c, first++, toSwap);

        // 4. reverse sequence from k+1 to n (inclusive) 
        toSwap = c.length - 1;
        while (first < toSwap)
            swap(c, first++, toSwap--);
        return true;
    }

    static void swap(int[] c, int i, int j)
    {
        int tmp = c[i];
        c[i] = c[j];
        c[j] = tmp;
    }
    // finds the largest k, that c[k] < c[k+1]
    // if no such k exists (there is not greater permutation), return -1
    static int getFirst(int[] c) 
    {
        for ( int i = c.length - 2; i >= 0; i--)
            if (c[i] < c[i + 1])
                return i;
        return -1;
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

		public boolean ready() throws IOException {return br.ready();}

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


	}
}
