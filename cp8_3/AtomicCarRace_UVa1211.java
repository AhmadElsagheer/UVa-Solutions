package cp8_3;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.StringTokenizer;


public class AtomicCarRace_UVa1211 {

	static double[][] memo;
	static int[] isCP;
	static int[]a;
	static int n, r;
	static double v, e, f, b;
	
	static double dp(int last_cp, int dist)
	{
		if(a[n] == dist)
			return 0;
		if(memo[last_cp][dist] > - 5)
			return memo[last_cp][dist];
		int x = dist - a[last_cp];
		double minTime = 1.0 / (v - (x < r?(f*(r-x)):(e*(x-r))))  + dp(last_cp, dist + 1);
		if(isCP[dist] != -1)
		{
			x = 0;
			minTime = Math.min(minTime,b +  1.0 / (v - (x < r?(f*(r-x)):(e*(x-r))))  + dp(isCP[dist], dist + 1));
		}
		
		return memo[last_cp][dist] = minTime;
	}
	
	public static void sol() throws NumberFormatException, IOException{
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			n = Integer.parseInt(br.readLine());
			if(n == 0)
				break;
			StringTokenizer st = new StringTokenizer(br.readLine());
			a = new int[n+1];
			isCP = new int[10001];
			Arrays.fill(isCP, -1);
			for(int i = 1; i <= n; i++)
			{
				int c = Integer.parseInt(st.nextToken());
				a[i] = c;
				isCP[c] = i;
			}
			b = getDouble(br.readLine());
			st = new StringTokenizer(br.readLine());
			r = Integer.parseInt(st.nextToken());
			v = getDouble(st.nextToken());
			e = getDouble(st.nextToken());
			f = getDouble(st.nextToken());
			
			memo = new double[n+1][a[n] + 1];
			for(int i = 0; i <= n; i++)
				Arrays.fill(memo[i], -10);
			if(a[n] > 1000)
				for(int i = a[n] - a[n]/10; i > 0; i -= a[n]/10)
						dp(0,i);
						

			
			sb.append(new DecimalFormat("0.0000").format(Math.round(dp(0,0)*10000)/10000.0)+"\n");
		}
		System.out.print(sb);
	}
	
	static double getDouble(String x)
	{
		String[] y = x.split("\\.");
		if(y.length == 1)
			return Integer.parseInt(y[0]);
		return Integer.parseInt(y[0]) + Integer.parseInt(y[1])/(Math.pow(10, y[1].length()));
	}
	
	public static void main(String[] args) {
		new Thread(null, new Runnable() {
			
			
			public void run() {
				try {
					sol();
				} catch (NumberFormatException | IOException e) {
					
					e.printStackTrace();
				}
				
			}
		}, "2",1<<26).start();
	}
	

}
