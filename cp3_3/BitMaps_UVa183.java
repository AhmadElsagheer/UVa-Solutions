package cp3_3;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BitMaps_UVa183 {

	static int[][] mat;
	static StringBuilder sb;
	static int idx;
	static void BtoD(int Ax, int Ay, int Bx, int By)
	{
//		System.out.println(Ax + " " + Ay + " " + Bx + "  " + By);
		int c = check(Ax, Ay, Bx, By);
		if(c != -1)
			sb.append(c);
		else
		{
			sb.append('D');
			int Cx = Ax + Bx + 1>>1, Cy = Ay + By + 1>>1;
		
			BtoD(Ax, Ay, Cx, Cy);
			if(By - Ay > 1)
				BtoD(Ax, Cy, Cx, By);
			if(Bx - Ax > 1)
				BtoD(Cx, Ay, Bx, Cy);
			if(By - Ay > 1 && Bx - Ax > 1)
				BtoD(Cx, Cy, Bx, By);
		
		}
	}
	
	static void DtoB(int Ax, int Ay, int Bx, int By, String in)
	{
//		System.out.println(Ax + " " + Ay + " " + Bx + " " + By + " : "+in.charAt(idx));
		idx++;
		if(in.charAt(idx) == '1')
			fill(Ax, Ay, Bx, By);
		else
			if(in.charAt(idx) == 'D')
			{
				int Cx = Ax + Bx + 1>>1, Cy = Ay + By + 1>>1;
				
				DtoB(Ax, Ay, Cx, Cy, in);
				if(By - Ay > 1)
					DtoB(Ax, Cy, Cx, By, in);
				if(Bx - Ax > 1)
					DtoB(Cx, Ay, Bx, Cy, in);
				if(By - Ay > 1 && Bx - Ax > 1)	
					DtoB(Cx, Cy, Bx, By, in);	
				
			}
	}
	
	static int check(int Ax, int Ay, int Bx, int By)
	{
		int c = mat[Ax][Ay];
		for(int i = Ax; i < Bx; ++i)
			for(int j = Ay; j < By; ++j)
				if(mat[i][j] != c)
					return -1;
		return c;
	}
	
	static void fill(int Ax, int Ay, int Bx, int By)
	{
		for(int i = Ax; i < Bx; ++i)
			for(int j = Ay; j < By; ++j)
				mat[i][j] = 1;
		
	}
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		String in = sc.next();
		while(true)
		{
			char c = in.charAt(0);
			if(c == '#')
				break;
			int R = sc.nextInt(), C = sc.nextInt();
			if(c == 'B')
			{
				sb = new StringBuilder();
				mat = new int[R][C];
				StringBuilder tt = new StringBuilder();
				while(true)
				{
					String x = sc.next();
					if(x.charAt(0) == '#' || sc.st.countTokens() == 2)
					{
						in = x;
						break;
					}
					tt.append(x);
				}
				
				String line = tt.toString();
				for(int i = 0, k = 0; i < R; ++i)
					for(int j = 0; j < C; ++j, ++k)
						mat[i][j] = line.charAt(k) - '0';
				BtoD(0, 0, R, C);
			}
			else
			{
				mat = new int[R][C]; idx = -1;
				StringBuilder tt = new StringBuilder();
				while(true)
				{
					String x = sc.next();
					if(x.charAt(0) == '#' || sc.st.countTokens() == 2)
					{
						in = x;
						break;
					}
					tt.append(x);
				}
				
				String line = tt.toString();
				DtoB(0, 0, R, C, line);
				sb = new StringBuilder();
				for(int i = 0; i < R; ++i)
					for(int j = 0; j < C; ++j)
						sb.append(mat[i][j]);
			}
			out.format("%c%4d%4d\n", c == 'B' ? 'D' : 'B', R, C);
			String line = sb.toString();
			sb = new StringBuilder();
			for(int i = 0, cc = 50; i < line.length(); ++i, --cc)
			{
				if(cc == 0)
				{
					cc = 50;
					sb.append("\n");
				}
				sb.append(line.charAt(i));
			}
			out.println(sb);
		}
		out.flush();

	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}
		
		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}
			
		public boolean ready() throws IOException {return br.ready();}


	}
}
