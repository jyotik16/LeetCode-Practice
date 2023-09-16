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

    public static void main(String args[])
    {
        String txt = "ABCABAABC";
        String pat = "ABC";
       // KMPSearch(pat, txt);
        LSP(pat,txt);
    }


}
