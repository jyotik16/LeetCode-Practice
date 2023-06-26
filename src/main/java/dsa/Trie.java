package dsa;

import java.util.*;
public class Trie {

    public static void main(String[] args) {
        WordReplace obj = new WordReplace();
        List<String> dictionary = List.of("cat","bat","rat");
        String s = "the cattle was rattled by the battery";
        System.out.println(obj.replaceWords(dictionary,s));
    }

    private class Node{
        Node[] childern;
        boolean isEnd;
        Node(){
            childern = new Node[26];
            isEnd = false;
        }
    }
    private static Node root = null;
    public Trie() {
        this.root = new Node();
    }
    //208
    public void insert(String word) {
        Node ptr = root;
        for (int i=0;i<word.length();i++){
            char ch = word.charAt(i);
                 if (ptr.childern[ch-'a'] == null){
                     Node newnode = new Node();
                     ptr.childern[ch-'a'] = newnode;
                 }
                 ptr = ptr.childern[ch-'a'];
        }
        ptr.isEnd = true;
    }

    public boolean search(String word) {
        Node ptr = root;
        for (int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if (ptr.childern[ch-'a'] == null){
                return false;
            }
            ptr = ptr.childern[ch-'a'];
        }
        return ptr.isEnd;
    }

    public boolean startsWith(String word) {
        Node ptr = root;
        for (int i=0;i<word.length();i++){
            char ch = word.charAt(i);
            if (ptr.childern[ch-'a'] == null){
                return false;
            }
            ptr = ptr.childern[ch-'a'];
        }
        return true;
    }
    //211
    static class WordDictionary {
        private class Node{
            Node[] childern;
            boolean isEnd;
            Node(){
                childern = new Node[26];
                isEnd = false;
            }
        }
        private static WordDictionary.Node root = null;

        public WordDictionary() {
           this.root = new Node();
        }

        public void addWord(String word) {
            Node ptr = root;
            for (int i=0;i<word.length();i++){
                char ch = word.charAt(i);
                if (ptr.childern[ch-'a'] == null){
                    Node newnode = new Node();
                    ptr.childern[ch-'a'] = newnode;
                }
                ptr = ptr.childern[ch-'a'];
            }
            ptr.isEnd = true;
        }

        public boolean search(String word) {
            return find(root,word,0);
        }

        private boolean find(Node node,String word,int indx){
            if (indx == word.length()){
                return node.isEnd;
            }
            char ch = word.charAt(indx);
            if (ch == '.'){
                for (int i=0;i<26;i++){
                    Node child = node.childern[i];
                    if (child!=null && find(child,word,indx+1))
                        return true;
                }
            }else if(node.childern[ch-'a'] != null){
                return find(node.childern[ch-'a'], word,indx+1);
            }
            return false;
        }
    }
    //212
    static class WordSearch{
        private class Node{
            Node[] childern;
            boolean isEnd;
            int freq;
            Node(){
                childern = new Node[26];
                isEnd = false;
                freq = 0;
            }
        }
        private void insert(String word,Node root){
        Node ptr = root;
            for (int i=0;i<word.length();i++){
                char ch = word.charAt(i);
                if(ptr.childern[ch-'a'] ==null){
                    Node nn = new Node();
                    ptr.childern[ch-'a'] = nn;
                }
                ptr = ptr.childern[ch-'a'];
                ptr.freq++;
            }
            ptr.isEnd = true;
        }

        public List<String> findWords(char[][] board, String[] words) {
            List<String> res = new ArrayList<>();
            Node root = new Node();
            for (String word:words){
                insert(word,root);
            }
            boolean [][] vis = new boolean[board.length][board[0].length];
            for(int i=0;i<board.length;i++){
                for (int j=0;j<board[0].length;j++){
                    StringBuilder str = new StringBuilder();
                    travelAndAdd(board, i, j, vis, root, res, str);
                }
            }
            return res;
        }
        static private int[] xdir = {-1, 0, 1, 0};
        static private int[] ydir = {0, -1, 0, 1};
        private static int travelAndAdd(char[][] board, int i, int j, boolean[][] vis, Node root, List<String> res, StringBuilder str){
            char ch = board[i][j];
            if (root.childern[ch-'a'] == null) return 0;

            root = root.childern[ch-'a'];
            if (root.freq == 0){
                return 0;
            }
            int count = 0;
            str.append(ch);
            vis[i][j] = true;
            if(root.isEnd == true) {
                res.add(str.toString());
                root.isEnd = false;
                count = 1;
            }
            for(int d = 0; d < 4; d++) {
                int r = i + xdir[d];
                int c = j + ydir[d];

                if(r >= 0 && r < board.length && c >= 0 && c < board[0].length && vis[r][c] == false) {
                    count += travelAndAdd(board, r, c, vis, root, res, str);
                }
            }
            str.deleteCharAt(str.length() - 1);
            vis[i][j] = false;
            root.freq -= count;
            return count;
        }
    }
    // leetcode 648, https://leetcode.com/problems/replace-words/
    static class WordReplace{

        private class Node{
            Node[] children;
            String isEnd;
            Node(){
                children = new Node[26];
                isEnd = "";
            }
        }
        private void insert(Node node, String word) {
            if(word.length() == 0) return;
            for(int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);

                if(node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new Node();
                }
                node = node.children[ch - 'a'];
            }
            node.isEnd = word;
        }

        public String replaceWords(List<String> dictionary, String sentence) {
            List<String> res = new ArrayList<>();
            String [] words = sentence.split(" ");
            Node root = new Node();
            for (String word:dictionary){
                insert(root,word);
            }

            for (int i=0;i< words.length;i++){
                words[i] = replaceWord(words[i],res,root);
            }
            StringBuilder sb = new StringBuilder();
            sb.append(words[0]);
            for (int i = 1; i < words.length; i++) {
                sb.append(" ");
                sb.append(words[i]);
            }
            return sb.toString();
        }

        private String replaceWord(String word, List<String> res,Node root) {
            Node temp = root;
            if (root.children[word.charAt(0)-'a'] == null)
                return word;
            for (int i=0;i<word.length();i++){
                char ch = word.charAt(i);
                if (root.children[ch-'a'] == null)
                    return word;
                else if(root.children[ch-'a'] != null){
                    root = root.children[ch-'a'];
                }
                if (root.isEnd.length()>0)
                    return root.isEnd;
            }
            return word;
        }
        //Brute force HashSet
        public String replaceWords2(List<String> roots, String sentence) {
            Set<String> set = new HashSet();
            for (String root: roots)
                set.add(root);
            
            StringBuilder ans = new StringBuilder();
            String word[] = sentence.split(" ");
            for (int j=0;j<word.length;j++) {
                String prefix = "";
                for (int i=1; i<=word[j].length();++i) {
                    prefix = word[j].substring(0, i);
                    if (set.contains(prefix))
                        break;
                }
                if(ans.length()>0)
                    ans.append(" ");
                ans.append(prefix);
            }
            return ans.toString();
        }
    }

}
