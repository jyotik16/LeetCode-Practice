package dsa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class HashingAndHeap {
    public static void main(String[] args) {
        long a1[] = {10, 5, 2, 23, 19,3};
        long a2[] = {19, 5, 3};
        //System.out.println(isSubset(a1,a2,a1.length,a2.length));
    }
    //https://practice.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1
    private static boolean match(HashMap<Character, Integer> map1, HashMap<Character, Integer> map2) {
        for(Character key : map1.keySet()) {
            int val1 = map1.getOrDefault(key, -1);
            int val2 = map2.getOrDefault(key, -1);
            if(val1 != val2) return false;
        }
        return true;
    }
    public static void findAnagrams(String s, String p) {
        HashMap<Character, Integer> pmap = new HashMap<>(); // pattern map
        for(int i = 0; i < p.length(); i++) {
            char ch = p.charAt(i);
            pmap.put(ch, pmap.getOrDefault(ch, 0) + 1);
        }

        HashMap<Character, Integer> smap = new HashMap<>(); // source map
        // acquire first window of length =  p.length
        for(int i = 0; i < p.length(); i++) {
            char ch = s.charAt(i);
            smap.put(ch, smap.getOrDefault(ch, 0) + 1);
        }
        ArrayList<Integer> res = new ArrayList<>();
        for(int i = p.length(); i < s.length(); i++) {
            // match
            if(match(smap, pmap) == true) {
                res.add(i - p.length());
            }
            // we can use equals function too
            // if(smap.equals(pmap) == true) {
            // }

            // acquire
            char ch = s.charAt(i);
            smap.put(ch, smap.getOrDefault(ch, 0) + 1);
            // release
            char relCh = s.charAt(i - p.length()); // relCh - > releasing character
            smap.put(relCh, smap.get(relCh) - 1);
            if(smap.get(relCh) == 0) smap.remove(relCh);
        }
        if(match(smap, pmap) == true) {
            res.add(s.length() - p.length());
        }
        System.out.println(res.size());
        for(int val : res) System.out.print(val + " ");
    }

    //https://practice.geeksforgeeks.org/problems/intersection-of-two-linked-list/1
    public static Node findIntersection(Node head1, Node head2) {
        // add your code here
        // return the head of intersection list
        //NOTE:we need to maintain the order according to first LL that'why adding
        // second LL into the HaspMap
        HashMap<Integer,Integer> map = new HashMap<>();
        Node t1 = head2; // 2LL
        while (t1!=null){
            if(!map.containsKey(t1.data)){
                map.put(t1.data,1);
            }else{
                int c = map.get(t1.data);
                map.put(t1.data,c+1);
            }
            t1 =  t1.next;
        }
        Node head3 = null;
        Node t2 = head3;
        t1 = head1; //1LL
        while (t1!=null){
            if(map.containsKey(t1.data)){
                int c = map.get(t1.data);
                if(head3 == null){
                    if ( c > 0) {
                        head3 = t1;
                        t2 = head3;
                        map.put(t1.data,c-1);
                    }
                }else{
                    if ( c > 0){
                        t2.next = t1;
                        t2 = t1;
                        map.put(t1.data,c-1);
                    }
                }
            }
            t1 = t1.next;
        }
        t2.next = null;
        return head3;
    }

    static void displayLL(Node head){
        Node t = head;
        while(t!=null){
            System.out.println(t.data);
        }
    }
    //https://www.geeksforgeeks.org/union-and-intersection-of-two-linked-lists/
    static class Node {
        int data;
        Node next;
        Node(int d) {data = d; next = null; }
    }
    public static Node findUnion(Node head1,Node head2) { // O((n+m) * log(n+m))
        TreeMap<Integer,Node> map = new TreeMap<>();
        //iterate the first LL add in the map
        Node t1 = head1;
        while (t1 != null){  // O(n * log(n))
            Node node = t1;
            if(!map.containsKey(node.data)){
                map.put(node.data,node);
            }else{
                map.put(node.data,node);
            }
            t1 = t1.next;
        }
        //iterate the second LL add in the map
        Node t2 = head2;
        while (t2 != null){  // O(m * log(m))
            Node node = t2;
            if(!map.containsKey(node.data)){
                map.put(node.data,node);
            }
            t2 = t2.next;
        }

        Node head3 = new Node(0);
        Node temp = head3;
        //iterate the hashmap make new LL
        for (Integer data:map.keySet()){
            Node nn = new Node(data);
            temp.next = nn;
            temp = nn;
        }
        return head3.next;
    }

    //https://practice.geeksforgeeks.org/problems/array-subset-of-another-array2317/1
    public static String isSubset( long a1[], long a2[], long n, long m) {

        HashMap<Long,Integer> map = new HashMap<>();
        for (long element : a1) {
            if (!map.containsKey(element))
                map.put(element, 1);
            else {
                int count = map.get(element);
                map.put(element, count + 1);
            }
        }

        for (long val : a2) {
            if (!map.containsKey(val)) {
                return "No";
            } else {
                int count = map.get(val);
                if (count == 0) {
                    return "No";
                } else {
                    map.put(val, count - 1);
                }
            }
        }
        return "Yes";
    }
}
