package v113;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BeyBattle_UVa11378 {

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(System.in);
        PrintWriter out = new PrintWriter(System.out);

        while(sc.ready())
        {
            int N = sc.nextInt();
            int[] X = new int[N + 2], Y = new int[N + 2];
            Integer[] ids = new Integer[N];
            for(int i = 0; i < N; ++i) {
                ids[i] = i;
                X[i] = sc.nextInt();
                Y[i] = sc.nextInt();
            }
            Arrays.sort(ids, 0, N, (a, b)-> X[a] != X[b] ? X[a] - X[b] : Y[a] - Y[b]);
            TreeSet<Integer> cands = new TreeSet<>((a, b)-> Y[a] != Y[b] ? Y[a] - Y[b] : X[a] - X[b]);
            int ans = 100_000_000;
            for(int i = 0, j = 0; i < N; ++i) {
                while(j < i && X[ids[i]] - X[ids[j]] >= ans) cands.remove(ids[j++]);
                X[N] = X[N + 1] = X[ids[i]];
                Y[N] = Y[ids[i]] - ans;
                Y[N + 1] = Y[ids[i]] + ans;
                for(int k: cands.subSet(N, N + 1)) {
                    ans = Math.min(ans, Math.max(X[ids[i]] - X[k], Math.abs(Y[k] - Y[ids[i]])));
                }

                cands.add(ids[i]);
            }
            out.println(ans);
        }
        out.close();
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

        public double nextDouble() throws IOException { return Double.parseDouble(next()); }

        public String nextLine() throws IOException {return br.readLine();}

        public boolean ready() throws IOException { return br.ready(); }

    }
}
