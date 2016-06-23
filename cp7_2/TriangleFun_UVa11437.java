package cp7_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class TriangleFun_UVa11437 {
	
	static final double EPS = 1e-11;
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
	
		int tc = sc.nextInt();
		while(tc-->0)
		{
			Point A = new Point(sc.nextDouble(), sc.nextDouble());
			Point B = new Point(sc.nextDouble(), sc.nextDouble());
			Point C = new Point(sc.nextDouble(), sc.nextDouble());
			
			double AB = A.dist(B), BC = B.dist(C), CA = C.dist(A);
			
			double AF = AB / 3.0, CE = CA / 3.0, BD = BC / 3.0;
			
			double BAC = acos(AB, CA, BC), BCA = acos(BC, CA, AB), ABC = Math.PI - (BAC + BCA);
			
			double CF = cos(CA, AF, BAC), BE = cos(BC, CE, BCA), AD = cos(AB, BD, ABC);
			
			double ACF = acos(CA, CF, AF), AFC = Math.PI - (ACF + BAC);
			double EBC = acos(BC, BE, CE), BEC = Math.PI - (EBC + BCA);
			double BAD = acos(AB, AD, BD), BDA = Math.PI - (BAD + ABC);
			
			double RQ = CF - (CE * Math.sin(BEC) / Math.sin(Math.PI - (BEC + ACF)) + AF * Math.sin(BAD) / Math.sin(Math.PI - (BAD + AFC)));
			double RP = AD - (AF * Math.sin(AFC) / Math.sin(Math.PI - (BAD + AFC)) + BD * Math.sin(EBC) / Math.sin(Math.PI - (BDA + EBC)));
			double QP = BE - (CE * Math.sin(ACF) / Math.sin(Math.PI - (BEC + ACF)) + BD * Math.sin(BDA) / Math.sin(Math.PI - (BDA + EBC)));
			
			double s = (RQ + RP + QP) / 2.0;
			out.println(Math.round(Math.sqrt(s * (s - RQ) * (s - RP) * (s - QP))));
			
//			s = (AB + BC + CA) / 2.0;
//			out.println(Math.round(Math.sqrt(s * (s - AB) * (s - BC) * (s - CA)) / 7.0));
		}
		out.flush();
	}
	
	static double cos(double a, double b, double angle)
	{
		return Math.sqrt(a * a + b * b - 2 * a * b * Math.cos(angle));
	}
	
	static double acos(double a, double b, double c)
	{
		return Math.acos((a * a + b * b - c * c) / (2 * a * b));
	}
			
	static class Point
	{
		double x, y;
		
		Point(double a, double b) { x = a; y = b; }
		
		double dist(Point p)
		{
			return Math.sqrt((p.x - x) * (p.x - x) + (p.y - y) * (p.y - y));
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
		
		public boolean nextEmpty() throws IOException
		{
			st = new StringTokenizer(br.readLine());
			return st.countTokens() == 0;
		}
		
		public double nextDouble() throws IOException
		{
			String x = next();
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
		
		public boolean ready() throws IOException {return br.ready();}


	}
}
