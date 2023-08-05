package dsa;

import java.util.Arrays;

public class DP1 {
    public static void main(String[] args) {
        coinChange();
    }
    public static void coinChange(){
        int [] coin = {1,2,5}; int target = 11;
        int [] coin2 = {2}; int target2 = 3;
       int res = coinChangeBottomUpDp(coin,target);
       System.out.println(res);
       
    }
    public int memoization(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, -1);
        int res = recursion(coins, amount, 0, dp);
        return res == Integer.MAX_VALUE  ? -1 : res;
    }
    public int recursion(int[] coins, int amount, int number, int[] dp){
        if(amount == 0)
            return 0;
        int res = Integer.MAX_VALUE;
        for(int i=0; i<coins.length; i++){
            int temp = 0;
            if(amount-coins[i]>=0){
                if(dp[amount-coins[i]] != -1)
                    temp = dp[amount-coins[i]];
                else
                    temp = recursion(coins, amount-coins[i], number+1, dp);
                if(temp != Integer.MAX_VALUE)
                    res = Math.min(res, temp+1);
            }
        }
        return dp[amount] = res;
    }

    private static int recCoinChange(int []coins, int amount,int number){
        if(amount == 0)
            return number;
        if(amount<0)
            return Integer.MAX_VALUE;
        int res = Integer.MAX_VALUE;
        for(int i=0; i<coins.length; i++)
            res = Math.min(res, recCoinChange(coins, amount-coins[i], number+1));
        return res;
    }

    private static int coinChangeBottomUpDp(int []coins, int amount){
        /*
           0 1 1 2 2 1 2 2 3 3 2 3 3   (minimum coin required tp achieve target)
         */
        int dp[] = new int[amount+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        if(dp[amount] != Integer.MAX_VALUE){
            return dp[amount];
        }
        for(int i=1; i<=amount; i++){
            int min = Integer.MAX_VALUE;
            for(int j=0; j<coins.length; j++){
                int res = 0;
                if(i-coins[j]>=0){
                    res = dp[i-coins[j]];
                    if(res != Integer.MAX_VALUE){
                        min = Math.min(min, res+1);
                    }
                }
            }
            dp[i] = min;
        }
        System.out.println("****DP*****");
        for (int i = 0; i < dp.length; i++) {
            System.out.print(dp[i]+" ");
        }
        return dp[amount]!=Integer.MAX_VALUE?dp[amount]:-1;
    }
}
