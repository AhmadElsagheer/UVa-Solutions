package v119;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class ICanGuessTheDataStructure_UVa11995 {

	static PriorityQueue<Integer> pq;
	static Queue<Integer> queue;
	static Stack<Integer> stack;
	static boolean isQueue, isPQ, isStack;
	static void add(int x)
	{
		pq.add(x);
		queue.add(x);
		stack.push(x);
	}
	
	static void get(int x)
	{
		if(pq.isEmpty() || pq.remove() != x)
			isPQ = false;
		if(queue.isEmpty() || queue.remove() != x)
			isQueue = false;
		if(stack.isEmpty() || stack.pop() != x)
			isStack = false;
			
	}
	
	static int getDS()
	{
		int ds = -1;
		if(isPQ)
			if(ds==-1)
				ds = 1;
			else
				return 0;
		
		if(isQueue)
			if(ds==-1)
				ds = 2;
			else
				return 0;
		
		if(isStack)
			if(ds==-1)
				ds = 3;
			else
				return 0;
		return ds;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(br.ready())
		{
			int N = Integer.parseInt(br.readLine());
			pq = new PriorityQueue<Integer>(1000,new X());
			queue = new LinkedList<Integer>();
			stack = new Stack<Integer>();
			isQueue = isPQ = isStack = true;
			
			while(N-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int com = Integer.parseInt(st.nextToken());
				int x = Integer.parseInt(st.nextToken());
				switch(com)
				{
				case 1:add(x);break;
				case 2:get(x);
				}
			}
			switch(getDS())
			{
			case 0:sb.append("not sure\n");break;
			case 1:sb.append("priority queue\n");break;
			case 2:sb.append("queue\n");break;
			case 3:sb.append("stack\n");break;
				default:sb.append("impossible\n");
			}
		}
		System.out.print(sb);
		
	}
	
}

class X implements Comparator<Integer>
{

	@Override
	public int compare(Integer o1, Integer o2) {
		return o2 - o1;
	}
	
}