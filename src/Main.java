

import medium.Solution;
import util.ListNode;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Solution solution = new Solution();
//        int[] A0 = new int[]{6,4,5};//123  3->2->1
//        int[] B0 = new int[]{4,6,5};
//        ListNode A = makeList(A0);
//        ListNode B = makeList(B0);
        System.out.print(solution.longestPalindrome("bbbb"));
//        while(A!=null){
//            System.out.print(A.val+" ");
//            A = A.next;
//        }

//        for (int cur : A) {
//            System.out.print(cur+" ");
//        }

//        System.out.println(solution.checkPossibility(A));
//        int[][] B = new int[][]{
//                {1,8,6},
//                {10,5,0},
//                {4,2,9}
//        };
//        int[][] B = new int[][]{
//                {0,0,1,0,0,0,0,1,0,0,0,0,0},
//                {0,0,0,0,0,0,0,1,1,1,0,0,0},
//                {0,1,1,0,1,0,0,0,0,0,0,0,0},
//                {0,1,0,0,1,1,0,0,1,0,1,0,0},
//                {0,1,0,0,1,1,0,0,1,1,1,0,0},
//                {0,0,0,0,0,0,0,0,0,0,1,0,0},
//                {0,0,0,0,0,0,0,1,1,1,1,1,1},
//                {0,0,0,0,0,0,0,1,1,0,0,0,0}};
//        List<Integer> C = new ArrayList<>();
//        List<List<Integer>> D = solution.threeSum(A);

//        System.out.println(solution.maxDistToClosest(A));
    }

    private static ListNode makeList(int[] A){
        ListNode request = new ListNode();
        ListNode last = null;
        for (int cur:A){
            ListNode test = new ListNode();
            test.val = cur;
            test.next = last;
            request.next = test;
            last = test;
        }

        return request.next;
    }
}
