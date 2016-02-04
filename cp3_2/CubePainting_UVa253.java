package cp3_2;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CubePainting_UVa253 {

	public static void main(String[] args) throws IOException {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			String input = sc.next();

			String c1 = input.substring(0, 6), c2 = input.substring(6);
			Cube cube1 = new Cube(c1), cube2 = new Cube(c2);

			boolean same = false;
			for(int k = 0; !same && k < 2; ++k)
			{
				for(int i = 0; !same && i < 4; ++i)
				{
					for(int j = 0; !same && j < 4; ++j)
						if(cube1.equals(cube2))
							same = true;
						else
							cube1.rotateHor();
					cube1.rotateVer();
				}
				cube1.rotateHor();
			}
			out.println(same?"TRUE":"FALSE");
		}

		out.flush();
		out.close();
	}	

	static class Cube
	{
		int[] faces;
		String colors;

		Cube(String x)
		{
			colors = x;
			faces = new int[]{0, 1, 3, 4, 2, 5};
		}

		void rotateHor()
		{
			int tmp = faces[1];
			for(int i = 1; i < 4; ++i)
				faces[i] = faces[i + 1];
			faces[4] = tmp;
		}

		void rotateVer()
		{
			int tmp = faces[5];
			faces[5] = faces[4];
			faces[4] = faces[0];
			faces[0] = faces[2];
			faces[2] = tmp;
		}

		boolean equals(Cube c)
		{
			for(int i = 0; i < 6; ++i)
				if(colors.charAt(faces[i]) != c.colors.charAt(c.faces[i]))
					return false;
			return true;
		}


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

		public double nextDouble(String x) 
		{

			StringBuilder sb = new StringBuilder("0");
			double res = 0, f = 1;
			boolean dec = false, neg = false;
			int start = 0;
			if(x.charAt(0) == '-')
			{
				neg = true;
				start++;
			}
			for(int i = start; i < x.length(); i++)
				if(x.charAt(i) == '.')
				{
					res = Long.parseLong(sb.toString());
					sb = new StringBuilder("0");
					dec = true;
				}
				else
				{
					sb.append(x.charAt(i));
					if(dec)
						f *= 10;
				}
			res += Long.parseLong(sb.toString()) / f;
			return res * (neg?-1:1);
		}


	}
}
