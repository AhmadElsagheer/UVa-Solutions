package v109;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.StringTokenizer;


public class RockPaperScissorsTournament_UVa10903 {

	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		boolean first = true;
		while(true)
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			if(n == 0)
				break;
			if(first)
				first = false;
			else
				sb.append("\n");
			int k = Integer.parseInt(st.nextToken()) * n * (n-1) / 2;
			
			int[] w =new int[n], l = new int[n];
			while(k-->0)
			{
				st = new StringTokenizer(br.readLine());
				int p1 = Integer.parseInt(st.nextToken()) - 1;
				char c1 = st.nextToken().charAt(0);
				int p2 = Integer.parseInt(st.nextToken()) - 1;
				char c2 = st.nextToken().charAt(0);
				if(c1 != c2)
					if(c1 == 's' && c2 == 'p' || c1 == 'p' && c2 == 'r' || c1 == 'r' && c2 == 's') {w[p1]++; l[p2]++;}
					else {w[p2]++; l[p1]++;}
			}
			for(int i = 0; i < n; i++)
				if(w[i] + l[i] == 0) sb.append("-\n");
				else
					sb.append(new DecimalFormat("0.000").format(Math.round((1.0*w[i]/(w[i]+l[i]))*1000)/1000.0)+"\n");
		}

		
		System.out.print(sb);
	}
}
