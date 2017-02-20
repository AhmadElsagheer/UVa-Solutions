package v005;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Rails_UVa514 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		while(true)
		{
			int N = Integer.parseInt(br.readLine());
			if(N==0)
				break;
			while(true)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int first = Integer.parseInt(st.nextToken());
				if(first==0)
					break;
				
				Queue<Integer> B = new LinkedList<Integer>();
				B.add(first);
				for(int i = 1; i < N; i++)
					B.add(Integer.parseInt(st.nextToken()));
				
				Stack<Integer> station = new Stack<Integer>();
				int A = 0;
				while(!B.isEmpty())
				{
					int cur = B.remove();
					while((station.isEmpty() || station.peek() != cur) && A <= N)
						station.push(++A);
					if(A>N)
						break;
					station.pop();
				}
				if(A>N)
					sb.append("No\n");
				else
					sb.append("Yes\n");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}
}
