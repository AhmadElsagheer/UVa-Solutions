package v111;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;


public class TheTrip2700_UVa11100 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		while(true)
		{
			int N = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
			if(N==0)
				break;
			
			if(first)
				first = false;
			else
				sb.append("\n");
			
			int[] bags = new int[N];
			int last = -1,count = 0, max= 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < N; i++)
			{
				while(!st.hasMoreTokens())
					st = new StringTokenizer(br.readLine());
				bags[i] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(bags);
			for(int i = 0; i < N; i++)
			{
				int bag = bags[i];
				if(bag==last)
					count++;
				else
				{
					max = Math.max(count, max);
					count = 1;
				}
				last = bag;
				
			}
			max = Math.max(count, max);
			sb.append(max).append("\n");
			Stack<Integer>[] nestedBags = new Stack[max];
			for(int i = 0; i < max; i++)
				nestedBags[i] = new Stack<Integer>();
			for(int i = 0, j = 0; i < N; i++, j = (j+1)%max)
				nestedBags[j].push(bags[i]);
			for(int i = 0; i < max; i++)
			{
				Stack<Integer> cur = nestedBags[i];
				while(!cur.isEmpty())
					sb.append(cur.pop()).append(cur.isEmpty()?"\n":" ");
			}
		}
		System.out.print(sb);
	}
}
