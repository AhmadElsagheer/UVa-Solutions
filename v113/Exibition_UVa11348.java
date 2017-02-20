package v113;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.util.StringTokenizer;


public class Exibition_UVa11348 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int TC = Integer.parseInt(br.readLine());
		for(int k = 1; k <= TC; k++)
		{
			int[] stamps = new int[10001];
			int N = Integer.parseInt(br.readLine());
			int[][] friends = new int[N][];
			for(int i = 0; i < N; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				int m = Integer.parseInt(st.nextToken());
				friends[i] = new int[m];
				for(int j = 0; j < m; j++)
				{
					friends[i][j] = Integer.parseInt(st.nextToken());
					stamps[friends[i][j]]++;
				}
			}
			int[] unique = new int[N];
			int total = 0;
			for(int i = 0; i < N; i++)
			{
				for(int j = 0; j < friends[i].length; j++)
					stamps[friends[i][j]]--;
				int cur = 0;
				for(int j = 0; j < friends[i].length; j++)
				{
					if(stamps[friends[i][j]]==0)
						cur++;
					stamps[friends[i][j]]++;
				}
					
				unique[i] = cur;
				total += cur;
			}
			sb.append("Case "+k+":");
			if(total!=0)
				for(int i = 0; i < N; i++)
					sb.append(roundDouble(unique[i]*1.0/total));
			sb.append("\n");
		}
		System.out.print(sb);
	}
	
	static String roundDouble(double x)
	{
		return " "+new DecimalFormat("0.000000").format(x*100) + "%";
	}
}
