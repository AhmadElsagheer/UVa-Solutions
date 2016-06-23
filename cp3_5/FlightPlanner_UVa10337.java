package cp3_5;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class FlightPlanner_UVa10337 {

	static final int UNCAL = -1;
	static final int INF = 100000000;
	static int memo[][];
	static int[][] wind;
	static int X;
	public static int dp(int i, int alt)
	{
		if(alt>9 || alt < 0 || i==X && alt != 0)
			return INF;
		if(i==X)
			return 0;
		if(memo[alt][i]!=UNCAL)
			return memo[alt][i];
		int up = 60 + wind[alt][i] + dp(i+1,alt+1);
		int hold = 30 + wind[alt][i] + dp(i+1,alt);
		int down = 20 + wind[alt][i] + dp(i+1,alt-1);
		return memo[alt][i] = Math.min(up,Math.min(down, hold));
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int TC = Integer.parseInt(br.readLine());
		while(TC-->0)
		{
			br.readLine();
			X = Integer.parseInt(br.readLine())/100;
			memo = new int[10][X];
			for(int i = 0; i < 10; i++)
				Arrays.fill(memo[i], UNCAL);
			wind = new int[10][X];
			for(int i = 9; i >= 0; i--)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < X; j++)
					wind[i][j] = -Integer.parseInt(st.nextToken());
			}
			int min = dp(0,0);
			out.println(min+"\n");
		}
		
		out.flush();
	}
}
