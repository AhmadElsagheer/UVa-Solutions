package v101;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.StringTokenizer;

public class WhatIsTheMedian_UVa10107 {

	public static void main(String[] args) throws IOException {
		
		IndexableSkipList<Integer> x = new IndexableSkipList<Integer>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(br.ready())
		{
			int n = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			x.add(n);
			if(x.size%2==0)
				sb.append((x.getElementAtIndex(x.size/2)+x.getElementAtIndex(x.size/2 - 1))/2+"\n");
			else
				sb.append((x.getElementAtIndex(x.size/2))+"\n");
			
		}
		System.out.print(sb);
	}
	
	//0- based indexable skip list.
	static class IndexableSkipList <E extends Comparable<E>> {	
		
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

		public IndexableSkipList() 
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

		public E getElementAtIndex(int index) 
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
}