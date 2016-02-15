package cp3_4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Collections;

public class Bridge_UVa10037 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			br.readLine();
			
			int N = Integer.parseInt(br.readLine());
			LinkedList<Integer> left = new LinkedList<Integer>();
			LinkedList<Integer> right = new LinkedList<Integer>();
			for(int i = 0; i < N; i++)
				left.add(Integer.parseInt(br.readLine()));
			Collections.sort(left);
			StringBuilder x = new StringBuilder();
			int min = 0;
			while(left.size() > 2)
			{
				int a = left.removeFirst();
				if(right.isEmpty())
				{
					int b = left.removeFirst();
					x.append(a+" "+b+"\n"+a+"\n");
					right.add(b);
					left.addFirst(a);
					min += a + b;
				}
				else
				{
					 int yy = left.removeLast();
					 int xx = left.removeLast();
					 if(yy + (right.getFirst()<<1) > xx + yy + a)
					 {
						 left.add(xx);left.add(yy);
						 int b = left.removeFirst();
						 x.append(a+" "+b+"\n"+a+"\n");
						 right.add(b);
						 left.addFirst(a);
						 min += a + b;
					 }
					 else
					 {
						 int z = right.removeFirst();
						 x.append(xx+" "+yy+"\n"+z+"\n");
						 left.addFirst(a);left.add(z);right.add(xx);right.add(yy);
						 min += yy + z;
					 }
				}
				
				
				Collections.sort(left);
				Collections.sort(right);
			}
			if(left.size()==2)
			{
				x.append(left.get(0)+" "+left.get(1)+"\n");
				min += left.get(1);
			}
			else
				if(left.size()==1)
				{
					x.append(left.get(0)+"\n");
					min += left.get(0);
				}
			sb.append(min+"\n").append(x);
			if(TC!=0)
				sb.append("\n");
		}
		System.out.print(sb);
	}
}
