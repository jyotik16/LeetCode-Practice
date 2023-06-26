package dsa;

import java.util.*;

public class GreedyAlgo {

    public static void main(String[] args) {
       // System.out.println(breakPalindrome("aabaa"));
      //  String [] ar = {"lc","cl","gg","gg","gg"};
      //  System.out.println(longestPalindrome(ar));
         int [] costs = {1,3,2,4,1};
       // System.out.println(maxIceCream(costs,7));
       // System.out.println(maximum69Number(96));
        int [] tasks = {2,2,3,3,2,4,4,4,4,4};
      //  System.out.println(minimumRounds2(tasks));
        int [] gas={4,5,2,6,5,3}; int [] cost = {3,2,7,3,2,9};
        System.out.println(gasStation(gas,cost));
    }
    //134
    public static int gasStation(int[] gas,int[]cost){
        int n = gas.length;
        for(int i=0;i<n;i++) {
            if (gas[i]<cost[i]) continue;
            int j =(i+1)%n;
            int costMovingFromStation = cost[i];
            int earnGasFromStation = gas[j];
            int currGas = gas[i]-costMovingFromStation + earnGasFromStation;
            while(j!=i){
                if (currGas<cost[j]){
                    break;
                }
                int costMovingFromStationJ = cost[j];
                j = (j+1)%n;
                int earnGasFromStationJ = gas[j];
                currGas = currGas - costMovingFromStationJ + earnGasFromStationJ;
            }
            if (j==i) return i;
        }
        return -1;
    }
    public int canCompleteCircuit(int[] gas, int[] cost) {

        int currentGaining = 0;
        int totalGaining = 0;
        int candidate = 0;

        for(int i= 0; i < gas.length; i++) {

            currentGaining += gas[i] - cost[i];
            totalGaining += gas[i] - cost[i];

            if (currentGaining < 0) {
                candidate = i+1;
                currentGaining = 0;
            }

        }

        return totalGaining >= 0 ? candidate : -1;

    }
    //2244
    private static final int limit = 100001;
    private static final int[] dp = new int[limit];
    public static int minimumRounds2(int[] tasks) {
        dp[1] = -1;
        dp[2] = 1;
        dp[3] = 1;
        dp[4] = 2;
        for (int i = 5; i < limit; i++) {
            dp[i] = Math.min(dp[i - 3], dp[i - 2]) + 1;
        }

        Map<Integer, Integer> count = new HashMap<>();
        for (int task : tasks) {
            count.merge(task, 1, Integer::sum);
        }

        int result = 0;
        for (int frequency : count.values()) {
            if (frequency == 1) {
                return -1;
            }
            result += dp[frequency];
        }

        return result;
    }

    public int minimumRounds(int[] tasks) {
        int round = 0;
        Map<Integer, Integer> taskMap = new HashMap<>(); // map of <task,  number of each task>
        for (int i = 0; i < tasks.length; i++) {
            taskMap.put(tasks[i], taskMap.getOrDefault(tasks[i], 0) + 1);
        }

        for (Map.Entry<Integer, Integer> entry : taskMap.entrySet()) {
            if (entry.getValue() == 1) {
                return -1;
            }
            // try to take as many 3's as possible
            round += entry.getValue() / 3;

            /*
				We can have 1 or 2 tasks remaining. We're not supposed to take task of count 1, but we can 'borrow' 1 from the previous
				ex. [5,5,5,5,5,5,5] -> [5,5,5][5,5,5][5]
				In this example, treat the last [5,5,5], [5] as [5,5], [5,5]
            */
            if (entry.getValue() % 3 != 0) {
                round++;
            }
        }
        return round;
    }
    //2279
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int count = 0;
        int i = 0;
        int [] req_rock = new int[capacity.length];

        while(i<capacity.length){
            req_rock[i] = capacity[i]-rocks[i]; i++;
        }
        Arrays.sort(req_rock);
        for (i=0;i< capacity.length;i++){
            if (req_rock[i] == 0)
            {
                count++;
            }
            else {
                if (additionalRocks >= req_rock[i]){
                    count++; additionalRocks -=req_rock[i];
                }else{
                    break;
                }
            }
        }
        return count;
    }
    //1323
    public static int maximum69Number (int num) {
        int count = -1; int num2 = num; int i=0;
        while(num>0){
            int rem = num %10;
            num = num /10;
            if (rem == 6) {
                count = i;
            }
            i++;
        }
        num2 += 3*Math.pow(10,count);
        return num2;
    }

    public static int maximum69Number2(int num){
        String s = Integer.toString(num);
        char[] ch = s.toCharArray();
        for(int i=0; i< ch.length; i++) {
            if(ch[i] == '6') {
                ch[i]='9';
                return Integer.parseInt(String.valueOf(ch));
            }
        }
        return num;
    }
    //1833
    public static int maxIceCream(int[] costs, int coins) {
        Arrays.sort(costs);
        int count = 0;
        int i = 0;
        while(i<costs.length) {
            if (costs[i] <= coins){
                count++;
                coins -=costs[i];
            }else {
                return count;
            }
            i++;
        }
        return count;
    }
    //2131
    public static int longestPalindrome(String[] words) {
        int result=0;
        Map<String,Integer> map=new HashMap<>();
        for(String s:words)
        {
            map.put(s,map.getOrDefault(s,0)+1);
        }
        int c=0;
        for(String s:map.keySet())
        {
            String s2=reverse(s);
            if(s.equals(s2)){
                int val=map.get(s);
                if(val%2!=0){val-=1;c=1;}

                result+=2*val;
                map.put(s,map.get(s)-val);

            }
            else if(map.containsKey(s2)){
                int occ=Math.min(map.get(s),map.get(s2));
                result+=(4*occ);

                map.put(s,map.get(s)-occ);
                map.put(s2,map.get(s2)-occ);
            }
        }
        return c==1?result+2:result;

    }
    public static String reverse(String str){
        StringBuilder sb=new StringBuilder(str);
        return sb.reverse().toString();
    }
    //2136
    public int earliestFullBloom(int[] plantTime, int[] growTime) {
            int n = plantTime.length;
            Pair[] flowerTime = new Pair[n];
            for (int i = 0; i < n; i++) {
                flowerTime[i] = new Pair(plantTime[i], growTime[i]);
            }
            Arrays.sort(flowerTime, (a, b) -> b.growTime - a.growTime);
            int plantingDays = 0;
            int totalDays = 0;
            for (Pair current : flowerTime) {
                totalDays = Math.max(totalDays, plantingDays + current.plantTime + current.growTime);
                plantingDays += current.plantTime;
            }
            return totalDays;
        }
        class Pair {
            public int plantTime;
            public int growTime;

            Pair(int plantTime, int growTime) {
                this.plantTime = plantTime;
                this.growTime = growTime;
            }
        }
    //1578
    public static int minCost(String colors, int[] neededTime) {
        int time = 0; int prev = 0; int curr = 0;
        for(int i=0;i<colors.length();i++){
            if(i>0 && colors.charAt(i) != colors.charAt(i-1)){
                prev = 0;
            }
            curr = neededTime[i];
            time += Integer.min(prev,curr);
            prev = Integer.max(prev,curr);
        }
        return time;
    }
    //991
    public static int brokenCalc(int startValue, int target) {
        if(startValue >= target)
            return startValue-target;

        if(target%2 == 0){
            return 1+brokenCalc(startValue,target/2);
        }
        return 1+brokenCalc(startValue,target+1);
    }
    //1328
    public static String breakPalindrome(String palindrome) {
        int n = palindrome.length();
        if(n == 1) return "";
        for (int i=0;i<n/2;i++){
            if (palindrome.charAt(i) != 'a'){
                StringBuilder string = new StringBuilder(palindrome);
                string.setCharAt(i, 'a');
                return string.toString();
            }
        }
        StringBuilder string = new StringBuilder(palindrome);
        string.setCharAt(n-1, 'b');
        return string.toString();
    }
    //881
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int left = 0,right = people.length-1;
        int count = 0;
        while(left<=right){
            int sum = people[left] + people[right];
            if(sum <= limit){
                count++;
                left++; right--;
            }else if(sum>limit){
                count++; right--;
            }
        }
        return count;
    }
    //948
    public static int bagOfTokensScore(int[] tokens, int power) {
        int n = tokens.length;
        int maxScore = 0;
        Arrays.sort(tokens);
        int score = 0; int i=0; int j=n-1;
        while(i<=j){
            if (power>=tokens[i]){
                power -=tokens[i];
                score +=1;
                maxScore = Integer.max(maxScore,score);
                i++;
            }else if(score>=1){
                score -=1;
                power +=tokens[j];
                j--;
            }else{
                return maxScore;
            }
        }
        return maxScore;
    }
}
