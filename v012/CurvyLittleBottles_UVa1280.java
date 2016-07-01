package v012;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;


public class CurvyLittleBottles_UVa1280 {

	static final double EPS = 1e-9;

	public static void main(String[] args) throws Exception 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int tc = 1;

		while(sc.ready())
		{
			int n = sc.nextInt();
			Polynomial p = new Polynomial();
			for(int i = 0; i <= n; ++i)
				p.coeffs[i] = sc.nextDouble();

			p = p.square();

			double xLow = sc.nextDouble(), xHigh = sc.nextDouble(), inc = sc.nextDouble();
			double[] marks = new double[8];
			int idx = 0;
			double xCur = xLow;
			while(idx < 8)
			{

				double mark = placeMark(p, xCur, xHigh, inc);
				if(mark < -1000)
					break;
				xCur = mark;
				marks[idx++] = mark - xLow;
			}

			out.printf("Case %d: %.2f\n", tc++, p.rotationVolume(xLow, xHigh));
			if(idx == 0)
				out.println("insufficient volume");
			else
			{
				for(int i = 0; i < idx - 1; ++i)
					out.printf("%.2f ", marks[i]);
				out.printf("%.2f\n", marks[idx-1]);
			}
		}
		out.flush();
		out.close();
	}

	static double placeMark(Polynomial p, double xLow, double xHigh, double inc)
	{
		double base = xLow, v = 0.0;
		while(Math.abs(xHigh - xLow) > EPS)
		{
			double mid = (xLow + xHigh) / 2;
			v = p.rotationVolume(base, mid);
			if(v < inc)
				xLow = mid;
			else
				xHigh = mid;
		}
		if(Math.abs(v - inc) > 1e-5)
			return -10000;
		
		return xLow;
	}

	static class Polynomial
	{
		double[] coeffs = new double[21];

		Polynomial square()
		{
			Polynomial p = new Polynomial();
			for(int i = 0; i <= 10; ++i)
			{
				double a = coeffs[i];
				for(int j = 0; j <= 10; ++j)
					p.coeffs[i+j] += a * coeffs[j];
			}
			return p;
		}

		double rotationVolume(double xLow, double xHigh)
		{
			double v = 0.0;
			for(int i = 0; i <= 20; ++i)
				v += coeffs[i] * compute(i + 1, xLow, xHigh) / (i + 1);
			return v * Math.PI;
		}

		double compute(int pow, double a, double b)
		{
			return Math.pow(b, pow) - Math.pow(a, pow);
		}
	}

	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

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