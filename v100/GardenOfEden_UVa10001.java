package v100;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class GardenOfEden_UVa10001 {

	
	static int[] state;
	static int N;
	static int id;
	
	static boolean dp(int idx, int last, int cur, int start, int end)
	{
		if(idx==N)
			return cur==start && last == end;
		for(int i = 0; i < 8; i++)
			if((id >> i)%2==state[idx] && last == (i >> 2)%2 && cur == (i >> 1)%2)
				if(dp(idx+1,cur,i%2,start,end))
					return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			id = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			state = new int[N];
			String x = st.nextToken();
			for(int i = 0; i < N; i++)
				state[i] = x.charAt(i) - '0';
			boolean reachable = false;
			for(int i = 0; i < 8; i++)
				if((id >> i)%2==state[0])
					if(reachable = dp(1,(i>>1)%2,i%2,(i>>1)%2,(i>>2)%2))
						break;
				
			if(!reachable)
				sb.append("GARDEN OF EDEN\n");
			else
				sb.append("REACHABLE\n");
		}
		System.out.print(sb);
	}
}
