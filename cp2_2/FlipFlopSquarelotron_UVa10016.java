package cp2_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class FlipFlopSquarelotron_UVa10016 {

	public static void downUp(int[][] matrix, int ring)
	{
		
		for(int k = ring; k < matrix.length - ring; k++)
		{
			if(k==ring || k == matrix.length - ring - 1)
			{
				int i = ring, j = matrix.length - ring - 1;
				while(i < j)
				{
					int tmp = matrix[i][k];
					matrix[i][k] = matrix[j][k];
					matrix[j][k] = tmp;
					i++; j--;
				}
			}
			else
			{
				int tmp = matrix[ring][k];
				matrix[ring][k] = matrix[matrix.length - ring - 1][k];
				matrix[matrix.length - ring - 1][k] = tmp;
			}
		
		}

	}
	public static void leftRight(int[][] matrix, int ring)
	{
		for(int k = ring; k < matrix.length - ring; k++)
		{
			if(k==ring || k == matrix.length - ring - 1)
			{
				int i = ring, j = matrix.length - ring - 1;
				while(i < j)
				{
					int tmp = matrix[k][i];
					matrix[k][i] = matrix[k][j];
					matrix[k][j] = tmp;
					i++; j--;
				}
			}
			else
			{
				int tmp = matrix[k][ring];
				matrix[k][ring] = matrix[k][matrix.length - ring - 1];
				matrix[k][matrix.length - ring - 1] = tmp;
			}
		
		}

	}
	public static void inverseMainDiagonl(int[][] matrix, int ring)
	{
		for(int k = ring; k < matrix.length - ring; k++)
		{
			int tmp = matrix[ring][k];
			matrix[ring][k] = matrix[matrix.length - k - 1][matrix.length - ring - 1];
			matrix[matrix.length - k - 1][matrix.length - ring - 1] = tmp;
		
		}
		for(int k = ring; k < matrix.length - ring - 1; k++)
		{
			int tmp = matrix[matrix.length - ring - 1][k];
			matrix[matrix.length - ring - 1][k] = matrix[matrix.length - 1 - k][ring];
			matrix[matrix.length  - 1 - k][ring] = tmp;
		
		}
	}
	public static void mainDiagonal(int[][] matrix, int ring)
	{
		for(int k = ring; k < matrix.length - ring; k++)
		{
			int tmp = matrix[ring][k];
			matrix[ring][k] = matrix[k][ring];
			matrix[k][ring] = tmp;
		
		}
		for(int k = ring + 1; k < matrix.length - ring; k++)
		{
			int tmp = matrix[matrix.length - ring - 1][k];
			matrix[matrix.length - ring - 1][k] = matrix[k][matrix.length - ring - 1];
			matrix[k][matrix.length - ring - 1] = tmp;
		
		}

	}
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		int tc = Integer.parseInt(br.readLine());
		while(tc-->0)
		{
			int N = Integer.parseInt(br.readLine());
			int[][] matrix = new int[N][N];
			for(int i = 0 ; i< N; i++)
			{
				StringTokenizer st= new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++)
					matrix[i][j] = Integer.parseInt(st.nextToken());
			}
			int M = (N+1)/2;
			for(int i = 0; i < M; i++)
			{
				StringTokenizer st= new StringTokenizer(br.readLine());
				int T = Integer.parseInt(st.nextToken());
				
				while(T-->0)
				{
					int x = Integer.parseInt(st.nextToken());
					if(x==1)
						downUp(matrix, i);
					
					if(x==2)
						leftRight(matrix, i);
					
					if(x==3)
						mainDiagonal(matrix, i);

					if(x==4)
						inverseMainDiagonl(matrix, i);
				}
	
			}
			for(int i = 0; i < N; i++)
			{
				String row = "";
				for(int j = 0; j < N; j++)
					row += " "+matrix[i][j];
				sb.append(row.substring(1)+"\n");
			}
		}
		System.out.print(sb);
		
	}
}
