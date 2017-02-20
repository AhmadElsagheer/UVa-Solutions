package v113;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SymmetricMatrix_UVa11349 {

	static int N; static long matrix[][];
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
		int TC = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int k = 1; k <= TC; k++)
		{
			st = new StringTokenizer(br.readLine());
			st.nextToken();st.nextToken();
			N = Integer.parseInt(st.nextToken());
			boolean flag = true;
			matrix = new long[N][N];
			for(int i = 0; i < N; i++)
			{
				st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++)
				{
					matrix[i][j] = Long.parseLong(st.nextToken());
					if(matrix[i][j]<0)
						flag = false;
				}
			}
			out.printf("Test #%d: %s.\n",k,flag&&check()?"Symmetric":"Non-symmetric");
		}
		out.flush();
		out.close();
	}
	
	public static boolean check()
	{
		int x,y;
		for(int i = 0; i < (N + 1) >> 1; i++)
			for(int j = 0; j < N; j++)
			{
				x = N - 1 - i;
				y = N - 1 - j;
				if(matrix[x][y]!=matrix[i][j])
					return false;
			}
			
		return true;
	}
}
