package cp3_4;
//package cp3_4;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.util.ArrayList;
//import java.util.StringTokenizer;
//
//public class MatrixTranspose_UVa10895 {
//
//	public static void main(String[] args) throws IOException {
//		
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		StringBuilder sb = new StringBuilder();
//		
//		while(br.ready())
//		{
//			StringTokenizer st = new StringTokenizer(br.readLine());
//			int M = Integer.parseInt(st.nextToken());
//			int N = Integer.parseInt(st.nextToken());
//			ArrayList<Pair>[] columns = new ArrayList[N+1];
//			for(int i = 1; i <= N; i++)
//				columns[i] = new ArrayList<Pair>();
//			for(int i = 1; i <= M; i++)
//			{
//				st = new StringTokenizer(br.readLine());
//				int x = Integer.parseInt(st.nextToken());
//				int[] array = new int[x];
//				for(int j = 0; j < x; j++)
//					array[j] = Integer.parseInt(st.nextToken());
//				st = new StringTokenizer(br.readLine());
//				for(int j = 0; j < x; j++)
//					columns[array[j]].add(new Pair(i, Integer.parseInt(st.nextToken())));	
//			}
//			sb.append(N+" "+M+"\n");
//			for(int i = 1; i <= N; i++)
//				if(columns[i].size()==0)
//					sb.append("0\n\n");
//				else
//				{
//					int size = columns[i].size();
//					sb.append(size);
//					for(int j = 0; j < size; j++)
//						sb.append(" "+columns[i].get(j).row);
//					sb.append("\n");
//					for(int j = 0; j < size - 1; j++)
//						sb.append(columns[i].get(j).val+" ");
//					sb.append(columns[i].get(size-1).val+"\n");
//				}
//			
//		
//		}
//		System.out.print(sb);
//	}
//}
//
//class Pair
//{
//	int row, val;
//	Pair(int x, int y)
//	{
//		row = x; val = y;
//	}
//}
