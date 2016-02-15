package cp2_3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Random;
import java.util.StringTokenizer;

public class BlackBox_UVa501 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = sc.nextInt();
		while(tc-->0)
		{
			int N = sc.nextInt(), M = sc.nextInt(), k = 0;
			int[] added = new int[N], queries = new int[M];
			for(int i = 0; i < N; ++i)
				added[i] = sc.nextInt();
			for(int i = 0; i < M; ++i)
				queries[i] = sc.nextInt();
			SkipList<Integer> sl = new SkipList<Integer>();
			for(int i = 0, j = 0; i < M; )
				if(queries[i] == sl.size)
				{
					out.println(sl.get(k++));
					++i;
				}
				else
					sl.add(added[j++]);
			if(tc != 0)
				out.println();
		}
		
		out.flush();

	}



	static class SkipList <E extends Comparable<E>> {	

		class Node 
		{
			E value;
			int level;
			int length;
			Node next;
			Node down;

			public Node(E val, int lvl, int ps, Node nxt, Node dwn) 
			{
				value = val;
				level = lvl;
				length = ps;
				next = nxt;
				down = dwn;
			}
		}

		final static double p = 0.5;
		
		Node head;
		Random rand;
		int size;

		public SkipList() 
		{
			head = new Node(null, 0, -1, null, null);
			rand = new Random();
			size = 0;
		}

		public int randomizeLevel() 
		{
			int level = 0;
			while (level <= head.level && rand.nextDouble() < p)
				++level;
			return level;
		}

		public boolean add(E val) 
		{
			int index = getIndex(val);
			if (index < 0 || index > size)
				return false;
			
			int level = randomizeLevel();
			if (level > head.level)
				head = new Node(null, level, index, null, head);
			
			int cur_pos = -1;
			Node cur = head, last = null;
			while(cur != null)
			{
				if (cur.next != null && cur_pos + cur.length < index) 
				{
					cur_pos += cur.length;
					cur = cur.next;
				} 
				else 
				{
					cur.length++;
					if (cur.level <= level) 
					{
						Node toAdd = new Node(val, cur.level, 0, cur.next, null);
						
						toAdd.length = cur.length - (index - cur_pos);
						cur.length = index - cur_pos;
						
						if(last != null)
							last.down = toAdd;
						cur.next = toAdd;
						last = toAdd;
					}
					cur = cur.down;
				}
			}
			++size;
			return true;
		}

		public int getIndex(E val)
		{
			Node cur = head;int cur_pos = -1;
		    while(cur != null) 
		    {
		      Node next = cur.next;
		      if (next == null || next.value.compareTo(val) > 0)
		    	  cur = cur.down;
		  
		      else 
		      {
		    	  cur_pos += cur.length;
		    	  cur = cur.next;
		      }
		    }
		    return cur_pos + 1;
		}
		
		public E get(int index) 
		{
			if (index < 0 || index >= size)
				return null;
			Node cur = head;
			int cur_pos = -1;
			while (cur != null) 
			{
				if (cur_pos == index)
					return cur.value;
				if (cur.next == null || cur_pos + cur.length > index) 
					cur = cur.down;
				else 
				{
					cur_pos += cur.length;
					cur = cur.next;
				}
			}
			return null;
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
			
		public boolean ready() throws IOException {return br.ready(); }


	}
}
