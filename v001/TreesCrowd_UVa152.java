package v001;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class TreesCrowd_UVa152 {

	public static void main(String[] args) throws IOException 
	{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		ArrayList<Point> points = new ArrayList<Point>(5000);
		
		while(true)
		{
			int x = sc.nextInt(), y = sc.nextInt(), z = sc.nextInt();
			if(x + y + z == 0) break;
			points.add(new Point(x, y, z));
		}
		int[] a = new int[10];
		for(int i = 0; i < points.size(); i++)
		{
			int cur = 10;
			Point p = points.get(i);
			for(int j = 0; j < points.size(); j++)
				if(i != j)
					cur = Math.min(cur, p.dist(points.get(j)));
			if(cur < 10)
				a[cur]++;
		}
		for(int i = 0; i < 10; i++)
			out.printf("%4d",a[i]);
		out.println();
		out.flush();

	}

	static class Point
	{
		int x, y, z;
		Point(int a, int b, int c) { x = a; y = b; z = c; }
		
		int dist(Point p) { return (int) Math.sqrt((x - p.x) * (x - p.x) + (y - p.y) * (y - p.y) + (z - p.z) * (z - p.z)); }
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
