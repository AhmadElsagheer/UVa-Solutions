package cp3_5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class StrategicDefenseInitiative_UVa497 {

	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		br.readLine();
		while(tc-->0)
		{
			ArrayList<Integer> A = new ArrayList<Integer>();
			String x = br.readLine();
			while(x != null && !x.isEmpty())
			{
				A.add(Integer.parseInt(x));
				x = br.readLine();
			}
			
			ArrayList<Integer> L = new ArrayList<Integer>(A.size());
			int[] p = new int[A.size()];
			int[] L_id = new int[A.size()];
			
			int lis = 0, lis_end = -1;
			for(int i = 0; i < A.size(); i++)
			{
				int cur = A.get(i);
				int pos = Collections.binarySearch(L, cur);
				if(pos < 0) pos = -(pos+1);
				
				if(pos < L.size()) L.set(pos, cur);
				else L.add(cur);
				
				if(pos + 1 > lis)
				{
					lis = pos + 1;
					lis_end = i;
				}
				L_id[pos] = i;
				p[i] = pos > 0 ?L_id[pos-1]:-1;
			}
			
			sb.append("Max hits: "+lis+"\n");
			Stack<Integer> stack = new Stack<Integer>();
			while(lis_end != -1)
			{
				stack.push(lis_end);
				lis_end = p[lis_end];
			}
			while(!stack.isEmpty())
				sb.append(A.get(stack.pop())+"\n");
			if(tc != 0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
}
