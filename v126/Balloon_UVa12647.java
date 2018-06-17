package v126;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Balloon_UVa12647 {

	static final int INF = -1, DONE = -2;

	static class Point implements Comparable<Point>
	{
		int x, y, idx; 	// < 0 for queries, even for segment leftEnd and odd for segment rightEnd

		Point(int a, int b, int c) { x = a; y = b; idx = c; }

		public int compareTo(Point p)
		{
			if(x != p.x)
				return x - p.x;
			return pr(idx) - pr(p.idx);
		}
	}

	static int pr(int v)
	{
		int ret = 0;
		if(v > 0)
			if((v & 1) == 0)
				ret = -1;
			else
				ret = 1;
		return ret;
	}

	static class Segment implements Comparable<Segment>
	{
		Point left, right;
		int idx;

		Segment(Point p, Point q, int x) { left = p; right = q; idx = x; }

		public int compareTo(Segment s)
		{
			if(left.equals(s.left) && right.equals(s.right))
				return 0;
			if(between(left, s.left, s.right))
				return below(left, s.left, s.right);
			if(between(right, s.left, s.right))
				return below(right, s.left, s.right);
			return below(s.left, left, right) * -1;
		}

		int upperEnd() { return left.y > right.y ? -1 : left.y < right.y ? 1 : 0; }
	}


	static boolean between(Point p, Point q, Point r) { return q.x < p.x && p.x < r.x; }

	static int below(Point p, Point q, Point r)
	{
		return ccw(q, r, p) ? 1 : -1;
	}

	static boolean ccw(Point a, Point b, Point c)
	{
		int x1 = b.x - a.x, y1 = b.y - a.y;
		int x2 = c.x - a.x, y2 = c.y - a.y;
		return 1l * x1 * y2 - 1l * x2 * y1 > 0;
	}

	static Point find(Point[] next, int idx)
	{
		if(next[idx].idx == INF || next[idx].idx == DONE)
			return next[idx];
		if(next[next[idx].idx] == null)
			return next[idx] = new Point(next[idx].x, next[idx].y, DONE);
		return next[idx] = find(next, next[idx].idx);
	}

	public static void main(String[] args) throws Exception {

		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready())
		{
			int N = sc.nextInt(), C = sc.nextInt();
			Segment[] segs = new Segment[N];
			Point[] evs = new Point[N * 2 + C];
			for(int i = 0; i < N; ++i)
			{
				int x1 = sc.nextInt(), y1 = sc.nextInt(), x2 = sc.nextInt(), y2 = sc.nextInt();
				if(x1 > x2) { x1 ^= x2; x2 ^= x1; x1 ^= x2; y1 ^= y2; y2 ^= y1; y1 ^= y2; }

				Point p = new Point(x1, y1, i << 1), q = new Point(x2, y2, i << 1 | 1);
				segs[i] = new Segment(p, q, i);
				evs[i << 1] = p;
				evs[i << 1 | 1] = q;
			}

			for(int i = 0; i < C; ++i)
				evs[N * 2 + i] = new Point(sc.nextInt(), 0, -(i + 1));


				Point[] next = new Point[N + C];
				TreeSet<Segment> activeSet = new TreeSet<>();
				Arrays.sort(evs);

				for(Point ev: evs)
					if(ev.idx < 0)
					{
						Segment hiSeg = activeSet.isEmpty() ? null : activeSet.first();
						if(hiSeg == null)
							next[-ev.idx - 1 + N] = new Point(ev.x, ev.y, INF);
						else
							next[-ev.idx - 1 + N] = new Point(ev.x, hiSeg.left.y, hiSeg.idx);
					}
					else if((ev.idx & 1) == 0)
					{
						// left end
						int segIdx = ev.idx >> 1;
						if(segs[segIdx].upperEnd() == -1)
						{
							Segment hiSeg = activeSet.higher(segs[segIdx]);
							if(hiSeg == null)
								next[segIdx] = new Point(ev.x, ev.y, INF);
							else
								next[segIdx] = new Point(ev.x, hiSeg.left.y, hiSeg.idx);
						}
						activeSet.add(segs[segIdx]);
					}
					else {
						// right end
						int segIdx = ev.idx >> 1;
						if (segs[segIdx].upperEnd() == 1) {
							Segment hiSeg = activeSet.higher(segs[segIdx]);
							if (hiSeg == null)
								next[segIdx] = new Point(ev.x, ev.y, INF);
							else
								next[segIdx] = new Point(ev.x, hiSeg.left.y, hiSeg.idx);
						}
						activeSet.remove(segs[segIdx]);

					}

				Point[] ans = new Point[C];
				for(Point ev: evs)
					if(ev.idx < 0)
					{
						int idx = -ev.idx - 1;
						ans[idx] = find(next, idx + N);
					}
				for(Point p: ans)
					if(p.idx == INF)
						out.println(p.x);
					else
						out.println(p.x + " " + p.y);

		}
		out.close();
	}

	static class Scanner
	{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader s) throws FileNotFoundException {	br = new BufferedReader(s);}

		public String next() throws IOException
		{
			while (st == null || !st.hasMoreTokens())
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}