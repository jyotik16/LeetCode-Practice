package dsa;

public class KMP {

    static void LSP(String pat,String txt){
        int n = txt.length(); int m = pat.length();
        int i = 1,len=0;
        String str = pat.concat("#");
        str = str.concat(txt);
        int[] lps = new int[str.length()];
        while (i < str.length()) {
            if (str.charAt(i) == str.charAt(len)) {
                len++; lps[i] = len;  i++;
            }
            else {
                if (len>0){
                    len = lps[len-1];
                }else{
                    lps[i] = 0;
                    i++;
                }
            }
        }
        int count = 0;
        //count the occurence of the pat in the txt via lps
        for (int j = 0; j < lps.length; j++) {
            if (lps[j] == m){
                count++;
                System.out.println("found the index:"+(j-2*m+1));
            }
        }
        System.out.println("times:"+count);
    }

    private static void rabinKrap() {
        String A="ABBCABB";
        String B="ABB";
        // System.out.println(solve(A.toLowerCase(),B.toLowerCase()));
        search(B.toLowerCase(),A.toLowerCase(),29);
    }

    public static int solve(String A, String B) {
        int n = A.length();
        int m = B.length();
        int prime = 29;
        int mod = (int)(1e9 + 7);

        long[] p_pow = new long[n];
        p_pow[0] = 1;
        for (int i = 1; i < n; i++) {
            p_pow[i] = (p_pow[i - 1] * prime) % mod;
        } //[1, 29, 841, 24389, 707281, 20511149, 594823321, 249876190]

        long[] h = new long[n + 1];
        for (int i = 0; i < n; i++){
            h[i + 1] = (h[i] + (A.charAt(i) - 'a' + 1) * p_pow[i]) % mod;
        } //[0, 1, 59, 1741, 74908, 782189, 41804487, 231451122] // A B B C A B B
        long hash_B = 0;
        for (int i = 0; i < m; i++) {
            hash_B = (hash_B + (B.charAt(i) - 'a' + 1) * p_pow[i]) % mod;
        } //[1,58,1683] = 1741 [A,B,B]

        int ans = 0;
        for (int i = 0; i + m - 1 < n; i++) {
            long curr_hash = (h[i + m] + mod - h[i]) % mod;
            if (curr_hash == hash_B * p_pow[i] % mod)
                ans += 1;
        }

        return ans;
    }

    public final static int d = 256;

    static void search(String pat, String txt, int primeNum) {
        int M = pat.length();
        int N = txt.length();
        int i, j;
        int pat_hashvalue = 0; // hash value for pattern
        int txt_hashvalue = 0; // hash value for txt
        int pow = 1;

        // The value of h would be "pow(d, M-1)%q"
        for (i = 0; i < M - 1; i++)
            pow = (pow * d) % primeNum;
        // iterate over pat length
        for (i = 0; i < M; i++) {
            pat_hashvalue = (d * pat_hashvalue + pat.charAt(i)) % primeNum;
            txt_hashvalue = (d * txt_hashvalue + txt.charAt(i)) % primeNum;
        }

        for (i = 0; i <= N - M; i++) {

            // Check the hash values of current window of // text and pattern. If the hash values match
            // then only check for characters one by one
            if (pat_hashvalue == txt_hashvalue) {
                for (j = 0; j < M; j++) {
                    if (txt.charAt(i + j) != pat.charAt(j))
                        break;
                }
                if (j == M)
                    System.out.println("Pattern found at index " + i);
            }
            // Calculate hash value for next window of text:
            // Remove leading digit, add trailing digit
            if (i < N - M) {
                txt_hashvalue = (d * (txt_hashvalue - txt.charAt(i) * pow) + txt.charAt(i + M)) % primeNum;
                if (txt_hashvalue < 0)
                    txt_hashvalue = (txt_hashvalue + primeNum);
            }
        }
    }
    static void makePalidrome(String str){
        String palin = str;
        int [] lps = new int[palin.length()];
        createLSP(lps,palin);
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < lps.length; i++) {
            max = Math.max(max,lps[i]);
        }
        System.out.println("max:"+max);
        if(str.length()%max == 0){
            System.out.println(max);
        }else{
            System.out.println(str.length()%max);
        }

    }

    private static void createLSP(int [] lps,String str) {
        int i=1,len =0;
        while (i < str.length()) {
            if (str.charAt(i) == str.charAt(len)) {
                len++; lps[i] = len;  i++;
            }
            else {
                if (len>0){
                    len = lps[len-1];
                }else{
                    lps[i] = 0;
                    i++;
                }
            }
        }
        System.out.println();
    }

    public static void main(String args[]) {
        String txt = "ABCABAABC";
        String pat = "ABC";
       // KMPSearch(pat, txt);
       // LSP(pat,txt);
       // rabinKrap();
        makePalidrome("abababababb"); //abababab  len=8 max=6 8%6  [0, 0, 1, 2, 3, 4, 5, 6]
        //hqhq  len=4 max=2  2%4 [0 0 1 2]
    }


}
