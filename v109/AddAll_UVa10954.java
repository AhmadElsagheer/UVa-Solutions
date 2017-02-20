package v109;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class AddAll_UVa10954 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			int n = Integer.parseInt(br.readLine());
			if(n==0)
				break;
			PriorityQueue<Integer> q = new PriorityQueue<Integer>();
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 0; i < n; i++)
				q.add(Integer.parseInt(st.nextToken()));
			
			long cost = 0;
			while(q.size()>1)
			{
				int x = q.remove();
				int y = q.remove();
				cost += x + y;
				q.add(x+y);
			}
			sb.append(cost).append("\n");
		}
		System.out.print(sb);
	}
}
