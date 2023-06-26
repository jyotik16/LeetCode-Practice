package dsa;

import java.util.*;
public class sprial {

        public static Scanner scn = new Scanner(System.in);

        public static void takeInput(int[][] arr) {
            for(int i = 0; i < arr.length; i++) {
                for(int j = 0; j < arr[0].length; j++) {
                    arr[i][j] = scn.nextInt();
                }
            }
        }



        public static void spiralDisplay(int[][] arr) {
            int n = arr.length;
            int m = arr[0].length;

            int rmin = 0;
            int rmax = n - 1;
            int cmin = 0;
            int cmax = m - 1;

            int tele = n * m;
            while(tele > 0) {
                // left wall,
                for(int r = rmin; r <= rmax && tele > 0; r++) {
                    System.out.println(arr[r][cmin]);
                    tele--;
                }
                cmin++;

                // bottom wall,
                for(int c = cmin; c <= cmax && tele > 0; c++) {
                    System.out.println(arr[rmax][c]);
                    tele--;
                }
                rmax--;

                // right wall,
                for(int r = rmax; r >= rmin && tele > 0; r--) {
                    System.out.println(arr[r][cmax]);
                    tele--;
                }
                cmax--;

                // top wall,
                for(int c = cmax; c >= cmin && tele > 0; c--) {
                    System.out.println(arr[rmin][c]);
                    tele--;
                }
                rmin++;
            }
        }

        public static void main(String[] args) {
            int n = scn.nextInt();
            int m = scn.nextInt();
            int[][] arr = new int[n][m];
           // takeInput(arr);
            int [][] brr = {{1,2,3},{4,5,6},{7,8,9}};
           // spiralDisplay(brr);
            spiralPrint(3,3,brr);
        }

    static void spiralPrint(int m, int n, int a[][])
    {
        int i, k = 0, l = 0;

        /*  k - starting row index
        m - ending row index
        l - starting column index
        n - ending column index
        i - iterator
        */

        while (k < m && l < n) {
            // Print the first row from the remaining rows
            for (i = l; i < n; ++i) {
                System.out.print(a[k][i] + " ");
            }
            k++;

            // Print the last column from the remaining
            // columns
            for (i = k; i < m; ++i) {
                System.out.print(a[i][n - 1] + " ");
            }
            n--;

            // Print the last row from the remaining rows */
            if (k < m) {
                for (i = n - 1; i >= l; --i) {
                    System.out.print(a[m - 1][i] + " ");
                }
                m--;
            }

            // Print the first column from the remaining
            // columns */
            if (l < n) {
                for (i = m - 1; i >= k; --i) {
                    System.out.print(a[i][l] + " ");
                }
                l++;
            }
        }
    }
    }

