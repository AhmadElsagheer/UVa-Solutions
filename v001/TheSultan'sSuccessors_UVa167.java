package v001;

import java.io.*;
import java.util.*;
public class Main {
    static boolean isValid(int row, int col, Integer[] curSol){
        for (int i = 0; i <= row ; i++) {
            if(curSol[i]!=null && (curSol[i]==col || Math.abs(row-i) == Math.abs(col-curSol[i]))) return false;
        }
        return true;
    }
    static void solve(ArrayList<Integer[]> sol, int curRow, Integer[] curSol){
        if(curRow==8) {sol.add(curSol); return;}
        for (int col = 0; col < 8; col++) {
            if(isValid(curRow, col, curSol)){
                curSol[curRow] = col;
                solve(sol, curRow+1, curSol.clone());
            }
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        ArrayList<Integer[]> sol = new ArrayList<>();
        solve(sol, 0, new Integer[8]);

        int k = sc.nextInt();
       while (k-->0){
            int[][] scores = new int[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    scores[i][j] = sc.nextInt();
                }
            }
            Integer max = 0;
           for (Integer[] ar: sol
                ) {
               int cur = 0, row = 0;
               for (int col: ar
                    ) {
                   cur+=scores[row++][col];
               }
               max = Math.max(max, cur);
           }
           int spaces = 5-max.toString().length();
           while (spaces-->0) pw.print(" ");
           pw.println(max);
        }





        pw.flush();
    }
    static class Scanner {
        StringTokenizer st;
        BufferedReader br;

        public Scanner(InputStream s) {
            br = new BufferedReader(new InputStreamReader(s));
        }

        public String next() throws IOException {
            while (st == null || !st.hasMoreTokens())
                st = new StringTokenizer(br.readLine());
            return st.nextToken();
        }

        public int nextInt() throws IOException {
            return Integer.parseInt(next());
        }

        public int[] nextIntArray(int n) throws IOException {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = nextInt();
            }

            return a;
        }


        public long nextLong() throws IOException {
            return Long.parseLong(next());
        }

        public String nextLine() throws IOException {
            return br.readLine();
        }

        public double nextDouble() throws IOException {
            String x = next();
            StringBuilder sb = new StringBuilder("0");
            double res = 0, f = 1;
            boolean dec = false, neg = false;
            int start = 0;
            if (x.charAt(0) == '-') {
                neg = true;
                start++;
            }
            for (int i = start; i < x.length(); i++)
                if (x.charAt(i) == '.') {
                    res = Long.parseLong(sb.toString());
                    sb = new StringBuilder("0");
                    dec = true;
                } else {
                    sb.append(x.charAt(i));
                    if (dec)
                        f *= 10;
                }
            res += Long.parseLong(sb.toString()) / f;
            return res * (neg ? -1 : 1);
        }

        public boolean ready() throws IOException {
            return br.ready();
        }


    }


}
