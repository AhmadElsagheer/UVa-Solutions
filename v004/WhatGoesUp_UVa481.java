package v004;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class WhatGoesUp_UVa481 {

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int[] array = new int[100000];
		int N = 0;
		while(br.ready())
			array[N++] = Integer.parseInt(br.readLine());
		
		int[] parent = new int[100000];
		int[] L_id = new int[100000];
		ArrayList<Integer> L = new ArrayList<Integer>();
		
		int lis = 0, lis_end = -1;	
		for(int i = 0; i < N; i++)
		{
			int pos = Collections.binarySearch(L, array[i]);
			if(pos<0)
				pos = -(pos+1);
			if(pos>=L.size())
				L.add(array[i]);
			else
				L.set(pos, array[i]);
			L_id[pos] = i;
			parent[i] = pos > 0?L_id[pos-1]:-1;
			if(pos + 1 >= lis)
			{
				lis = pos + 1;
				lis_end = i;
			}
		}
		out.printf("%d\n-\n",lis);
		Stack<Integer> stack = new Stack<Integer>();
		while(lis_end!=-1)
		{
			stack.push(array[lis_end]);
			lis_end = parent[lis_end];
		}
		while(!stack.isEmpty())
			out.println(stack.pop());
		out.flush();
	}
}
