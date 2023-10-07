package dsa;

public class GCD {
    public static void main(String[] args) {
        DeleteOne();
    }

    private static void DeleteOne() {
        int [] A = {12, 15, 18};
        deleteOne(A);
        //pfgcd [12,3,3]
        //sfgcd [3,3,18]
    }

    private static void deleteOne(int[] A) {
        int n = A.length;

        int pfgcd[] = new int[n];
        pfgcd[0] = A[0];
        for(int i=1;i<n;i++){
            pfgcd[i] = gcd(pfgcd[i-1], A[i]);
        }

        int sufgcd[] = new int[n];
        sufgcd[n-1] = A[n-1];
        for(int i=n-2;i>=0;i--){
            sufgcd[i] = gcd(sufgcd[i+1], A[i]);
        }

        int ans=0;
        //if i is zero
        ans = Math.max(ans, sufgcd[1]);
        //if i n-1
        ans = Math.max(ans, pfgcd[n-2]);

        for(int i=1;i<n-1;i++){
            int left = pfgcd[i-1];
            int right = sufgcd[i+1];
            int finalgcd = gcd(left, right);
            ans = Math.max(ans,finalgcd);
        }
      //  return ans;
        System.out.println("ans="+ans);
    }
    public static int gcd(int A, int B) {
        if(A==0){
            return B;
        }
        int temp = gcd(B%A, A);
        return temp;
    }

}
