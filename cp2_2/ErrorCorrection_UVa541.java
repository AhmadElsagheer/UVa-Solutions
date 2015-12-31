package cp2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class ErrorCorrection_UVa541 {

	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		while(true)
		{
			int n = Integer.parseInt(br.readLine());
			if(n==0)
				break;
			int[][] matrix = new int[n][n];
			for(int i = 0; i < n; i++)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < n; j++)
					matrix[i][j] = Integer.parseInt(st.nextToken());
			}
			int a = - 1; int b = -1; int corruptRow = 0, corruptCol = 0;
			for(int i = 0; i < n; i++)
			{
				int sumRow = 0;
				for(int j = 0; j < n; j++)
					sumRow += matrix[i][j];
				if(sumRow%2==1)
				{
					corruptRow++;
					a = i + 1;
				}
			}
			for(int i = 0; i < n; i++)
			{
				int sumCol = 0;
				for(int j = 0; j < n; j++)
					sumCol += matrix[j][i];
				if(sumCol%2==1)
				{
					corruptCol++;
					b = i + 1;
				}
			}
			if(corruptRow==0 && corruptCol==0)
				sb.append("OK\n");
			else
				if(corruptRow==1 && corruptCol==1)
					sb.append("Change bit ("+a+","+b+")\n");
				else
					sb.append("Corrupt\n");
			
		}
		
		System.out.print(sb);
		
	}
}
