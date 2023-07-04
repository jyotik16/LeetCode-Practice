package dsa;
import java.util.*;

public class ArrayQ2 {
    public static void main(String[] args) {

    }

        public static int solve(int[] A) {
            int size = 100001;
            HashSet<Integer> set = new HashSet<>();
            HashSet<Integer> ans = new HashSet<>();
            for(int i=0; i<A.length; i++)
                set.add(A[i]);
            for(int i=2; i<size; i++){
                if(isPrime(i)==1) {
                    for(int j=i; j<size; j+=i) {
                        if(set.contains(j)) {
                            ans.add(i);
                        }
                    }
                }
            }
            return ans.size();
        }
        public static int isPrime(int A){
            if(A==2 || A==3){
                return 1;
            }
            if(A==1){
                return 0;
            }
            for(int i=2; i*i<=A; i++){
                if(A%i==0){
                    return 0;
                }
            }
            return 1;
        }

        public static int[] countOfDivisor(int [] A){
            int[] ans = new int[A.length];
            int n = A.length;
            int max = Integer.MIN_VALUE;
            for(int i=0; i<n; i++) {
                max = Math.max(max, A[i]);
            }
            int[] temp = new int[max + 1];
            Arrays.fill(temp, 2);
            for(int i=2; i<=max; i++) {
                for(int j=2*i; j<=max; j+=i) {
                    temp[j]+=1;
                }
            }
            for(int i=0;i<n; i++) {
                if(A[i]==1) {
                    ans[i] = 1;
                }
                else {
                    ans[i] = temp[A[i]];
                }
            }
            return ans;
        }
    }

