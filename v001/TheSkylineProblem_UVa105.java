package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class TheSkylineProblem_UVa105 {

	
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int end = 0;
		LinkedList<Pair>[] line = new LinkedList[10000];
		for(int i = 0; i < 10000; i++)
			line[i] = new LinkedList<Pair>();
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int left = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			int right = Integer.parseInt(st.nextToken());
			line[left].add(new Pair(h,1));
			line[right].add(new Pair(h,0));
			end = Math.max(end, right);
		}
		TreeMap<Integer,Integer> on = new TreeMap<Integer,Integer>();
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		q.add(0);
		on.put(0, 1);
		int curHeight = 0;
		for(int i = 0; i <= end; i++)
		{
			int preHeight = curHeight;
			while(!line[i].isEmpty())
			{
				Pair change = line[i].remove();
				if(change.dir==1)
				{
					if(change.h>curHeight)
					{
						curHeight = change.h;
						//record output
					}
					q.add(-change.h);
					if(on.containsKey(change.h))
					{	
						int val  = on.get(change.h)+1;
						on.put(change.h, val);
					}
					else
						on.put(change.h, 1);
				}
				else
				{
					on.put(change.h, on.get(change.h)-1);
					while(on.get(-q.peek())==0) q.remove();
					curHeight = -q.peek();
				}
			}
			if(preHeight!=curHeight)
				out.printf("%d %d"+(i==end?"\n":" "),i,curHeight);
		}
		out.flush();
	}
	
	static class Pair
	{
		int h, dir;
		Pair(int x, int y){h = x; dir = y;}
	}
}