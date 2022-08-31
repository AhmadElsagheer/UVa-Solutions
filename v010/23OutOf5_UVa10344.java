package v010;

import java.io.*;
import java.util.*;
public class Main {
    static void getnums(ArrayList<Integer> cur, ArrayList<List<Integer>> nums, int avail[]){
        if(cur.size()==5) {
            nums.add((List<Integer>) cur.clone());
            return;
        }
        for (int i = 0; i < 5; i++) {
            if(avail[i]==-1) continue;
            int tmp = avail[i];
            cur.add(tmp);
            avail[i] = -1;
            getnums(cur, nums, avail);
            cur.remove(cur.size()-1);
            avail[i] = tmp;
        }
    }
    static void getops(StringBuilder cur, ArrayList<String> ops, char[] op){
        if(cur.length()==3){
            StringBuilder sb1 = new StringBuilder(cur);
            StringBuilder sb2 = new StringBuilder(cur);
            StringBuilder sb3 = new StringBuilder(cur);
            sb1.append('+'); sb2.append('-'); sb3.append('*');
            ops.add(sb1.toString()); ops.add(sb2.toString()); ops.add(sb3.toString());

            return;
        }
        for (int i = 0; i < 3; i++) {
            cur.append(op[i]);
            getops(cur, ops, op);
            cur.deleteCharAt(cur.length()-1);
        }
    }

    public static void main(String[] args) throws IOException {

        Scanner sc = new Scanner(new BufferedInputStream(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        ArrayList<String> ops = new ArrayList<>();
        char[] op = new char[]{'+','-','*'};
        getops(new StringBuilder(), ops, op);

        while (true){
            int[] ar = new int[5];
            ar[0] = sc.nextInt();ar[1] = sc.nextInt();ar[2] = sc.nextInt();ar[3] = sc.nextInt();ar[4] = sc.nextInt();
            if(ar[0]==0) break;

           ArrayList<List<Integer>> nums = new ArrayList<>();
           getnums(new ArrayList<>(), nums, ar);
            boolean f = false;
            for (List<Integer> list: nums
                 ) {

                    for (String s: ops
                         ) {
                        char[] arr= s.toCharArray();
                        int res = list.get(0);
                        int index = 1;
                        for (char x: arr
                             ) {
                            if(x=='+') res +=list.get(index);
                            else if (x=='-') res -=list.get(index);
                            else res *=list.get(index);
                            index++;
                        }
                        if(res==23) {f=true; break;}

                    }
                    if(f) break;


            }
            if(f) pw.println("Possible");
            else pw.println("Impossible");

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
