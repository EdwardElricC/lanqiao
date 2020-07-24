package medium;

import util.ListNode;
import util.TreeNode;

import java.util.*;

public class Solution {

    /**
     * @date 2020/6/23 17:44
     * leetcode 2 medium
     * Add Two Numbers || 两个数字
     * Math && Linked List || 数学 && 链表
     * 思路：遍历两个链表，相加，进位
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode requestList = new ListNode();
        ListNode cur1 = l1;
        ListNode cur2 = l2;
        ListNode reCur = requestList;
        int flag = 0;
        while(!(cur1 == null && cur2 == null)){
            ListNode test = new ListNode();
            test.val = (cur1==null?0:cur1.val) + (cur2==null?0:cur2.val) + flag;
            flag = test.val/10;
            test.val %= 10;
            reCur.next = test;
            reCur = test;
            cur1 = cur1==null?null:cur1.next;
            cur2 = cur2==null?null:cur2.next;
        }
        if (flag!=0){
            ListNode test = new ListNode();
            test.val = flag;
            reCur.next = test;
        }

        return requestList.next;
    }

    /**
     * @date 2020/6/24 17:29
     * leetcode 3 mudium
     * Longest Substring Without Repeating Characters || 最长子串，无重复字符
     * Hash Table && Two Pointers && String && Sliding Window || 哈希表 && 双指针 && 字符串 && 滑动窗口
     * 思路：子串开始的地方设置curL，字串最右侧设置curR，字符串遍历charC
     * cur游标，从curL开始到curR,与charC作比较，如果没有curR+1，如果有记录max curL+1,
     * test: '' 'b' 'ab' 'aab' 'abac'
     */
    public int lengthOfLongestSubstring(String s) {
//        abcabcbb ->3
//        abc
//       pwwkew pwwkew ->3
//          wke
//        循环主体:子串 循环条件:子串右侧字符是否与游标相同
//        循环失败:出现相同,从相同字符右侧开始重新规划子串 循环成功:子串向右扩张
//        主串游标就是子串右侧界限
        int curL=0,curR=1;
        int max=0;
        for (int curS = 1;curS<s.length();curS++){
            for (int curT = curL;curT<curS;curT++){
                if (s.charAt(curS) == s.charAt(curT)){
                    //发现重复内容
                    //记录最大值
                    max = max>(curS-curL)?max:(curS-curL);
//                    把重复的点T右侧设置为新的L,比较新的LR是否重合
                    curL = curT+1;
                    break;
                }
            }
        }

        return max>(s.length()-curL)?max:(s.length()-curL);
    }

    /**
     * @date 2020/6/29 8:44
     * leetcode 5 medium
     * Longest Palindromic Substring || 最长回文子串
     * String && Dynamic Programming || 字符串 && 动态规划
     * 新思路：LR从S游标开始对比，越界按不同算，LR相同，R+，此时子串长度为偶
     *      不同，L-判断子串，此时子串长度为奇
     *          LR不同，结束
     *          LR相同，按奇数子串的规则继续判断子串
     *      相同，L-判断子串，此时子串长度为奇
     *          LR不同，结束
     *          LR相同，继续按此规则循环
     *
     * 旧思路：游标左右对比，相同就扩大子串边界，不同就用边界相减得长度对比max，记录左边界下标
     * test: bb bbb bbbb
     */
    //针对可能的偶数子串
    private int evenString(String s,int curS,int curL,int curR){
        //越界
        if (curL<0 || curR>=s.length()){
            curL++;curR--;
            return curR-curL+1;
        }
        if (s.charAt(curL)==s.charAt(curR)){
            curR++;
            curL--;
            if (s.charAt(curL) == s.charAt(curR)) {
                if (s.charAt(curL) == s.charAt(curR)){
                    return evenString(s,curS,curL,curR);
                }else{
                    curL++;
                    return curR-curL+1;
                }
            }else{
                if (s.charAt(curL) == s.charAt(curR)){
                    return obbString(s,curS,curL,curR);
                }else{
                    curL++;
                    return curR-curL+1;
                }
            }
        }
        return curR-curL+1;
    }
    //针对奇数子串
    private int obbString(String s,int curS,int curL,int curR){
        while(true){
            //左右越界 左右不同
            if (curL<0 || curR>=s.length() || s.charAt(curL)!=s.charAt(curR)){
                //回滚
                curL++;curR--;
                return curR-curL+1;
            }
            //左右相同
            curL--;curR++;
        }
    }

    public String longestPalindrome(String s) {
        int max=0;
        int begin=0;
        for (int curS = 0; curS < s.length(); curS++) {
            int len = evenString(s,curS,curS,curS);
            if (len>max){
                if (len%2==0){
                    begin = curS+1-len/2;
                }else{
                    begin = curS-len/2;
                }
            }
        }
        return s.substring(begin,begin+max);

//        int beginCur = 0;
//        int max = 0;
//        for (int curS = 0; curS < s.length(); curS++) {
//            int curL = curS;
//            int curR = curS;
//            //只能筛选出奇数子串
//            //需要在curS上加判断，判断是否与右侧相同，然后启动偶数子串模式
//            if (curS+1<s.length()){
//                if (s.charAt(curS)==s.charAt(curS+1)){
//                    curR = curS+1;
//                }
//                //如果与左侧右侧均相同，还是按奇数子串处理
//                if (curS>0 && s.charAt(curS)==s.charAt(curS-1)){
//                    curL = curS-1;
//                }
//            }
//            while(true){
//                //左右越界 左右不同
//                if (curL<0 || curR>=s.length() || s.charAt(curL)!=s.charAt(curR)){
//                    //回滚
//                    curL++;curR--;
//                    if (max<(curR-curL+1)){
//                        max = curR-curL+1;
//                        beginCur = curL;
//                    }
//                    break;
//                }
//                //左右相同
//                curL--;curR++;
//            }
//
//        }

//        return s.substring(beginCur,beginCur+max);
    }

    /**
     * @date 2018/6/7 15:35
     * leetcode 12 medium
     * Integer to Roman || 整数转罗马数字
     * Math && String || 数学 && 字符串
     */
    private String[] lmArr = new String[]{   "M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
    private int[] numArr = new int[]{1000,900,500, 400, 100,90, 50,  40,  10,  9,  5,   4,  1};
    StringBuilder ans = new StringBuilder("");
    public String intToRoman(int num) {
        String str="";
        for (int cur=0;cur<numArr.length;cur++){
            while(num>=numArr[cur]){
                num-=numArr[cur];
//                str+=lmArr[cur];
                ans.append(lmArr[cur]);
            }
        }
//        return str;
        return ans.toString();
    }

    /**
     * @date 2018/7/16 17:03
     * leetcode 654 medium
     * Maximum Binary Tree || 最大二叉树
     * Tree
     */
    public TreeNode constructMaximumBinaryTree(int[] nums){
        int max=0;
        int flag=0;
        for (int i=0;i<nums.length;i++){
            if (max<nums[i]){
                max = nums[i];
                flag = i;
            }
        }
        int[] left = new int[flag];
        int[] right = new int[nums.length-flag-1];
        System.arraycopy(nums,0,left,0,flag);
        System.arraycopy(nums,flag+1,right,0,nums.length-flag-1);

        TreeNode root = new TreeNode(max);
        if (left.length!=0){
            root.setLeft(constructMaximumBinaryTree(left));
        }
        if (right.length!=0){
            root.setRight(constructMaximumBinaryTree(right));
        }
        return root;
    }

    /**
     * @date 2018/7/20 17:25
     * leetcode 763 medium
     * Partition Labels || 分区标签
     * Two Pointers && Greedy || 两个指针 && 贪心
     */
    public class Element{
        public int start;
        public int end;
    }
    public List<Integer> partitionLabels(String S) {
        List<Integer> list = new ArrayList<>();
        Element[] elements = new Element[26];
        for (int i=0;i<26;i++){
            elements[i] = new Element();
            elements[i].start=-1;
            elements[i].end = -1;
        }
        for (int i=0;i<S.length();i++){
            int cur = S.charAt(i)-'a';
            if (elements[cur].start==-1){
                elements[cur].start=i;
            }
            if (elements[cur].end<i){
                elements[cur].end = i;
            }
        }
        int left =0;
        int right =-1;
        for (int i=0;i<S.length();i++){
            int cur = S.charAt(i)-'a';
            int start = elements[cur].start;
            int end = elements[cur].end;
            if (start>right){
                list.add(right-left+1);
                left = start;
            }
            if (end>right){
                right = end;
            }
        }
        list.add(right-left+1);
        list.remove(0);
        return list;
    }

    /**
     * @date 2018/7/16 13:44
     * leetcode 807 medium
     * Max Increase to Keep City Skyline || 保持城市天际线的最大增量
     * null
     */
    public int maxIncreaseKeepingSkyline(int[][] grid) {
        int colSize = grid.length;
        int rowSize = grid[0].length;
        int num = 0;
        int[] rowMax = new int[rowSize];
        int[] colMax = new int[colSize];
        for (int i=0;i<colSize;i++){
            for (int j=0;j<rowSize;j++){
                rowMax[j] = rowMax[j]>grid[i][j]?rowMax[j]:grid[i][j];
                colMax[i] = colMax[i]>grid[i][j]?colMax[i]:grid[i][j];
            }
        }
        for (int i=0;i<colSize;i++){
            for (int j=0;j<rowSize;j++){
                int cha = colMax[i]>rowMax[j]?rowMax[j]:colMax[i];
                int den = cha-grid[i][j];
                if (den>=0){
                    num+=den;
                }
            }
        }
        return num;
    }

    /**
     * @date 2019/1/15 13:47
     * leetcode 950 medium
     * Reveal Cards In Increasing Order || 以递增的顺序显示卡片
     * Array || 数列
     **/
    public int[] deckRevealedIncreasing(int[] deck) {
//        先排序，然后间隔插入在一个新数组中
//        先制作一个序列数组，然后将deck中的数按序列输入
//        选取偶数，奇数放在最后
        Arrays.sort(deck);
        int len = deck.length;
        int[] index = new int[len];
        int[] reDeck = new int[len];
        int[] temp = new int[len*2];
        for (int i = 0; i < len; i++) {
            temp[i] = i;
        }
        int cur = 0;
        for (int i = 0; i < len * 2; i++) {
            if (i%2==0){
                index[cur++] = temp[i];
            }else {
                temp[len-1+cur] = temp[i];
            }
        }

        for (int i = 0; i < len; i++) {
            cur = index[i];
            reDeck[cur] = deck[i];
        }
        return reDeck;
    }
}
