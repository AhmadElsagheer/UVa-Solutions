package cp4_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class AvoidingYourBoss_UVa10354 {

	
	
	static final int INF = 100000000;
	static int[][] boss, me;
	static int P;
	

	static void floyd(int[][] adjMat)
	{
		//adjMat contains: directed edges, zero for i=j, INF (1B) otherwise
		for(int k = 0; k < P; k++)
			for(int i = 0; i < P; i++)
				for(int j = 0; j < P; j++)
					if(adjMat[i][j] > adjMat[i][k] + adjMat[k][j])
						adjMat[i][j] = adjMat[i][k] + adjMat[k][j];
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
	
		while(br.ready())
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			P = Integer.parseInt(st.nextToken());
			int  R = Integer.parseInt(st.nextToken()),	
				 BH = Integer.parseInt(st.nextToken()) - 1,
			     OF = Integer.parseInt(st.nextToken()) - 1,	
			     YH = Integer.parseInt(st.nextToken()) - 1, 
				 M = Integer.parseInt(st.nextToken()) - 1;
			
			boss = new int[P][P];
			me = new int[P][P];
			for(int i = 0; i < P; i++)
			{
				Arrays.fill(boss[i], INF);
				Arrays.fill(me[i], INF);
				me[i][i] = boss[i][i] = 0;
			}
			while(R-->0)
			{
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken()) - 1, P = Integer.parseInt(st.nextToken()) - 1, d = Integer.parseInt(st.nextToken());
				boss[u][P] = me[u][P] = boss[P][u] = me[P][u] = d;
			}
			floyd(boss);
			boolean[] forb = new boolean[P];
			for(int i = 0; i < P; i++)
				if(boss[BH][OF] == boss[BH][i] + boss[i][OF])
					forb[i] = true;
			for(int i = 0; i < P; i++)
				if(forb[i])
					for(int j = 0; j < P; j++)
						me[i][j] = me[j][i] = INF;
			floyd(me);
			sb.append(me[YH][M]==INF?"MISSION IMPOSSIBLE.":me[YH][M]).append("\n");
			
		}
		
		System.out.print(sb);
	}
}

