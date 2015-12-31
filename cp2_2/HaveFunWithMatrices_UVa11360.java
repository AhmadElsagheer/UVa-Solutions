package cp2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HaveFunWithMatrices_UVa11360 {

	static void row(int[][] matrix, int a, int b)
	{
		for(int i = 0; i < matrix.length; i++)
		{
			int tmp = matrix[a][i];
			matrix[a][i] = matrix[b][i];
			matrix[b][i] = tmp;
		}
	}
	
	static void col(int[][] matrix, int a, int b)
	{
		for(int i = 0; i < matrix.length; i++)
		{
			int tmp = matrix[i][a];
			matrix[i][a] = matrix[i][b];
			matrix[i][b] = tmp;
		}
	}
	
	static void inc(int[][] matrix, int val)
	{
		for(int i = 0; i < matrix.length; i++)
			for(int j = 0; j < matrix.length; j++)
				matrix[i][j] = (matrix[i][j]+val+10)%10;
	}
	
	static void transpose(int[][] matrix)
	{
		for(int i = 0; i < matrix.length; i++)
			for(int j = i + 1; j < matrix.length; j++)
			{
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[j][i];
				matrix[j][i] = tmp;
			}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int TC = Integer.parseInt(br.readLine());
		for(int k = 1; k <= TC; k++)
		{
			int n  = Integer.parseInt(br.readLine());
			int[][] matrix = new int[n][n];
			for(int i = 0; i < n; i++)
			{
				String line = br.readLine();
				for(int j = 0; j < n; j++)
					matrix[i][j] = line.charAt(j) - '0';
			}
			int q = Integer.parseInt(br.readLine());
			while(q-->0)
			{
				StringTokenizer st = new StringTokenizer(br.readLine());
				String op  = st.nextToken();
				switch(op.charAt(0))
				{
				case 'r':row(matrix,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1);break;
				case 'c':col(matrix,Integer.parseInt(st.nextToken())-1,Integer.parseInt(st.nextToken())-1);break;
				case 'i':inc(matrix, 1);break;
				case 'd':inc(matrix, -1);break;
					default:transpose(matrix);
				}
			}
			sb.append("Case #"+k+"\n");
			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < n; j++)
					sb.append(matrix[i][j]);
				sb.append("\n");
			}
			sb.append("\n");
		}
		
		System.out.print(sb);
	}
}
