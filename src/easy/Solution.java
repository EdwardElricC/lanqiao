package easy;

import java.util.*;

public class Solution {
    /**
     * @date 2018/7/24 11:47
     * leetcode 1 easy
     * Two Sum || 两个数
     * Array && Hash Table || 队列 && 哈希表
     */
    public int[] twoSum(int[] nums, int target) {
//        n 数组
//        只含有一对符合要求的数
//        k:ni v:i
//        ni加上某个k值=target
//        判断k值是否存在，存在则获取v值作为索引返回
        Map<Integer, Integer> map=new HashMap<>(nums.length);
        for(int i=0;i<nums.length;i++) {
            if(map.containsKey(target-nums[i])) {
                return new int[]{map.get(target-nums[i]),i};
            }else {
                map.put(nums[i], i);
            }
        }
        return null;
    }

    /**
     * @date 2018/6/7 16:31
     * leetcode 13 easy
     * Roman to Integer || 罗马数字转整数
     * Math && String || 数学 && 字符串
     */
    public int romanToInt(String s) {
        int[] templm = new int[s.length()];
        int i=0;
        for(char cur: s.toCharArray()){
            switch (cur){
                case 'I':
                    templm[i++] = 1;
                    break;
                case 'V':
                    templm[i++] = 5;
                    break;
                case 'X':
                    templm[i++] = 10;
                    break;
                case 'L':
                    templm[i++] = 50;
                    break;
                case 'C':
                    templm[i++] = 100;
                    break;
                case 'D':
                    templm[i++] = 500;
                    break;
                case 'M':
                    templm[i++] = 1000;
                    break;
                default:
                    break;
            }
        }
        int num=0;
        for (i=1;i<s.length();i++){
            if (templm[i-1]<templm[i]){
                num-=templm[i-1];
            }else {
                num+=templm[i-1];
            }
        }
        num+=templm[i-1];
        return num;
    }

    /**
     * @date 2018/8/2 10:36
     * leetcode 26 easy
     * Remove Duplicates from Sorted Array || 从排序数组中删除重复项
     * Array && Two Pointers || 队列 && 两个指针
     */
    public int removeDuplicates(int[] nums) {
//左指针 cL 右指针 cR 指向最近非重复数字，和
//0 0 1 1 1 2 2 3 3 4
//l r lr一样，r右移
//l   r 对比，不一样，l+1<-r
//0 1 1 1 1 2 2 3 3 4
//  l r   lr一样，r右移
//  l       r
//0 1 2 1 1 2 2 3 3 4
//    l       r
//    l         r
//0 1 2 3 1 2 2 3 3 4
//      l         r
//      l           r
//0 1 2 3 4 2 2 3 3 4
//        l          r
        int curL = 0;
        int curR = 1;
        while (curR<nums.length){
            if (nums[curL]==nums[curR]){
                curR++;
            }else {
                curL++;
                nums[curL] = nums[curR++];
            }
        }
//        for (int i=0;i<curL+1;i++){
//            System.out.print(nums[i]+" ");
//        }
//        System.out.println();
        return curL+1;
    }

    /**
     * @date 2018/8/1 15:00
     * leetcode 27 easy
     * Remove Element || 删除元素
     * Array && Two Pointers || 队列 && 两个指针
     */
    public int removeElement(int[] nums, int val) {
        int len = nums.length;
        int curL = 0;
        int curR = len-1;
        while(curL<=curR){
            if (nums[curL]!=val){
                curL++;
            }else {
                while (curL<=curR && nums[curR]==val){
                    curR--;
                    len--;
                }
                if (curL<curR){
                    nums[curL] = nums[curR];
                    len--;
                    curR--;
                }
            }
        }
//        for (int i=0;i<len;i++){
//            System.out.print(nums[i]+" ");
//        }
//        nums = Arrays.copyOf(nums,len);
        return len;
    }

    /**
     * @date 2018/8/2 9:34
     * leetcode 35 easy
     * Search Insert Position || 搜索插入位置
     * Array && Binary Search || 队列 && 二进制搜索
     */
    public int searchInsert(int[] nums, int target) {
        int i;
        for(i=0;i<nums.length;i++){
            if (target<=nums[i]){
                break;
            }
        }
        return i;
    }

    /**
     * @date 2018/8/1 18:13
     * leetcode 66 easy
     * Plus One || 加一
     * Array && Math || 队列 && 数学
     */
    public int[] plusOne(int[] digits) {
//队尾标记curE
//dEnd+1  dEnd=10  curE--
//溢出位 dStart
//重新构造一个数组 判断dStart状态 判断前置0
        int curE = digits.length-1;
        int dStart = 0;
        while(true){
            if (curE<0){
                dStart = 1;
                break;
            }
            digits[curE]++;
            if (digits[curE]==10){
                digits[curE]=0;
                curE--;
            }else {
                break;
            }
        }
        int num0=0;
        for (int cur:digits){
            if (dStart!=0 || cur!=0){
                break;
            }else {
                num0++;
            }
        }
        int[] nDigits = new int[digits.length+dStart-num0];
        int curOld = 0;
        for (int i=0;i<nDigits.length;i++){
            if (dStart!=0&&i==0){
                nDigits[i] = dStart;
                continue;
            }
            nDigits[i] = digits[curOld+num0];
            curOld++;
        }
        return nDigits;
    }

    /**
     * @date 2018/8/6 11:13
     * leetcode 88 easy
     * Merge Sorted Array || 合并排序数组
     * Array && Two Pointers || 队列 && 两个指针
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        //n1 n2 数组
        //c1 c2 cr游标
        //c1=m c2=n cr=n+m
        //比较n1c和n2c 将大的放置在最后 对应c--
        //特别注意c1走完c2还没走完的情况

//        nums1为空数组
        if (m==0){
            System.arraycopy(nums2,0,nums1,0,n);
//            nums1 = nums2;
            return;
        }
        int cur1 = m-1;
        int cur2 = n-1;
        int curR = m+n-1;
        while(cur1>=0 && cur2>=0){
            nums1[curR--] = (nums1[cur1]>nums2[cur2])?nums1[cur1--]:nums2[cur2--];
        }

        for (int i=0;i<=cur2;i++){
            nums1[i] = nums2[i];
        }

        for (int cur: nums1){
            System.out.print(cur+" ");
        }
    }

    /**
     * @date 2018/6/7 17:47
     * leetcode 118 easy
     * Pascal's Triangle || 杨辉三角
     * Array || 队列
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> listList = new ArrayList<>();
        for (int i=0;i<numRows;i++){
            List<Integer> list = new ArrayList<>();
            for (int j=0;j<=i;j++){
                list.add(cN2M(i,j));
            }
            listList.add(list);
        }
        return listList;
    }
    /**
     * 选择函数 Cn中取m
     * leetcode 118 function
     * @param N 底数
     * @param M 取数
     * @return 可能的结果
     */
    private int cN2M(int N,int M){
        if (M>(N/2)){
            M = N-M;
        }

        int temp = 1;
        int up = N;
        int down = 1;
        for(int i=0; i<M; i++) {
            temp = (int)((long)temp*up/down);
            up--;
            down++;
        }
        return temp;
    }

    /**
     * @date 2018/6/8 13:30
     * leetcode 119 easy
     * Pascal's Triangle II || 杨辉三角 II
     * Array || 队列
     */
    public List<Integer> getRow(int rowIndex) {
        List<Integer> result = new ArrayList<>();
        if (rowIndex<0){
            return result;
        }
        int temp = 1;
        int up = rowIndex;
        int down = 1;
        result.add(1);
        for(int i=1; i<rowIndex+1; i++) {
            temp = (int)((long)temp*up/down);
            up--;
            down++;
            result.add(temp);
        }
        return result;
    }

    /**
     * @date 2018/7/27 11:30
     * leetcode 122 easy
     * Best Time to Buy and Sell Stock II || 最佳买卖时机II
     * Array && Greedy || 队列 && 贪心
     */
    public int maxProfit(int[] prices) {
        boolean hold = false;
        int sum = 0;
        int money = 0;
        int now = 0;
        int next = 0;
        for (int i=0;i<prices.length-1;i++){
            now = prices[i];
            next = prices[i+1];
            if (!hold){//没有持股，可以买入
                if (now<next){
                    money = now;
                    hold = true;
                }
            }else {//持股，只能卖出
                if (now>next){
                    sum+=(now-money);
                    hold = false;
                }
            }
        }
        if (hold){
            sum+=(next-money);
        }
        return sum;
    }

    /**
     * @date 2018/7/27 16:41
     * leetcode 167 easy
     * Two Sum II - Input array is sorted || 两数和 II - 输入数组已排序
     * Array && Two Pointers && Binary Search || 队列 && 两个指针 && 二进制搜索
     */
    public int[] twoSumII(int[] numbers, int target) {
        int left=0;
        int right=numbers.length-1;
        int sum;
        while(left<right){
            sum=numbers[left]+numbers[right];
            if(sum==target){
                return new int[]{left+1, right+1};
            }else if(sum<target){
                left++;
            }else{
                right--;
            }
        }
        return null;
    }

    /**
     * @date 2018/6/7 17:28
     * leetcode 168 easy
     * Excel Sheet Column Title || Excel表列名称
     * Math || 数学
     */
    public String convertToTitle(int n) {
        StringBuilder stringBuilder = new StringBuilder();
        while (n>0){
            int mod = n%26;
            char tempS = (mod==0)?'Z':(char)(mod+'A'-1);
            stringBuilder.append(tempS);
            n -= (mod==0)?26:mod;
            n/=26;
        }
        return stringBuilder.reverse().toString();
    }

    /**
     * @date 2018/7/26 16:57
     * leetcode 169 easy
     * Majority Element || 多数元素
     * Array && Divide and Conquer && Bit Manipulation || 队列 && 分而治之 && 位操作
     */
    public int majorityElement(int[] nums) {
//        Map<Integer,Integer> map = new HashMap<>();
//        遍历并记录每个元素的值
//        for (int cur:nums){
//            //a:cur对应的value b:1
//            map.merge(cur,1,(a,b)->a+b);
//        }
//        int max = 0;
//        int flag = 0;
//        for (Map.Entry<Integer,Integer> entry:map.entrySet()){
//            int value = entry.getValue();
//            if (value>max){
//                max = value;
//                flag = entry.getKey();
//            }
//        }
//        return flag;

        /**
         * 当题目所找寻的主要元素的数量大于n/2时使用
         * 如果要找寻的主要元素数量可能等于n/2时存在bug
         * 时间复杂度O(n) 空间复杂度O(1)
         */
        int flag=nums[0],count=0;
        for(int cur:nums){
            if (flag==cur){
                count++;
            }else {
                if (count>0){
                    count--;
                }else {
                    flag=cur;
                    count=1;
                }
            }
        }
//        如果不能确保主要元素一定存在时
//        if (count>0){
//            count = 0;
//            for (int cur:nums){
//                if (flag==cur){
//                    count++;
//                }
//            }
//        }
//        if (count<=nums.length/2){
//            return -1;
//        }
        return flag;

        /**
         * 问题本身bug：测试数据中只有两个不同变量，可以采用以下方法解决
         */
//        Arrays.sort(nums);
//        return nums[nums.length/2];
    }

    /**
     * @date 2018/6/7 16:45
     * leetcode 171 easy
     * Excel Sheet Column Number || Excel表列序号
     * Math || 数学
     */
    public int titleToNumber(String s) {
        if (s == null || s.length()==0){
            return 0;
        }
        int num=0;
        for (char cur : s.toCharArray()) {
            num*=26;
            num+=cur-'A'+1;
        }
        return num;
    }

    /**
     * @date 2018/8/3 12:41
     * leetcode 189 easy
     * Rotate Array || 旋转阵列
     * Array || 队列
     * 方案零：比较k值，k<len/2，数组右移，重复k次（超时）
     * 方案一：新建一个数组，错位填充
     * 方案二：0右移k位 k右移k位 >len -len 循环 直到回归0
     * 方案三：整体反转，[0，k-1]反转，[k,len]反转
     */
    public void rotate1(int[] nums, int k) {
        int[] chNums = new int[nums.length];
        int len = nums.length;
        for (int i=0;i<len;i++){
            if (i<(len-k)){
                chNums[i+k] = nums[i];
            }else {
                int cur = i+k-len;
                while(cur>len-1){
                    cur-=len;
                }
                chNums[cur] = nums[i];
            }
        }
        for (int i=0;i<len;i++){
            nums[i] = chNums[i];
        }
        for (int cur:nums){
            System.out.print(cur+" ");
        }
    }

    public void rotate2(int[] nums, int k) {
        int len = nums.length;
        int cur = 0;
        int lastCur = cur;
        int prev = nums[0];
        int next;
        int flag = 0;
        while(flag<len){
            next = nums[(cur+k)%len];
            nums[(cur+k)%len] = prev;
            prev = next;
            cur+=k;
            cur%=len;
            if (cur==lastCur && flag<=len){
                cur++;
                cur%=len;
                lastCur = cur;
                prev = nums[cur];
            }
            flag++;
        }

        for (int cur1:nums){
            System.out.print(cur1+" ");
        }
    }

    public void rotate3(int[] nums, int k) {
        int temp;
        int len = nums.length;
        for (int i=0;i<len/2;i++){
            temp = nums[i];
            nums[i] = nums[len-1-i];
            nums[len-1-i] = temp;
        }
        k%=len;
        for (int i=0;i<k/2;i++){
            temp = nums[i];
            nums[i] = nums[k-1-i];
            nums[k-1-i] = temp;
        }
        for (int i=k;i<((len-k)/2+k);i++){
            temp = nums[i];
            nums[i] = nums[len-1+k-i];
            nums[len-1+k-i] = temp;
        }

        for (int cur1:nums){
            System.out.print(cur1+" ");
        }
    }

    /**
     * @date 2018/7/30 10:49
     * leetcode 217 easy
     * Contains Duplicate || 包含重复
     * Array && Hash Table || 队列 && 数组
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>(nums.length);
        for (int cur: nums){
            if (set.contains(cur)){
                return true;
            }
            set.add(cur);
        }
        return false;
    }

    /**
     * @date 2018/8/6 11:31
     * leetcode 219 easy
     * Contains Duplicate II || 包含重复II
     * Array && Hash Table || 队列 && 哈希表
     */
    public boolean containsNearbyDuplicate(int[] nums, int k) {
//HashMap
//k:ni v:i
//判断ni是否存在 存在则取出v值和i比较
        Map<Integer,Integer> map = new HashMap<>(nums.length);
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(nums[i]) && (i-map.get(nums[i]))<=k){
                return true;
            }else {
                map.put(nums[i],i);
            }
        }
        return false;
    }

    /**
     * @date 2018/7/28 11:31
     * leetcode 260 easy
     * Single Number III || 单号III
     * Bit Manipulation || 位运算
     */
    public int[] singleNumber(int[] nums) {
        Arrays.sort(nums);
        int first = -1;
        int last = -1;
        for (int i=0;i<nums.length;i+=2){
            if (i+1==nums.length){
                last = nums[i];
                break;
            }
            if (nums[i]!=nums[i+1]){
                if (first==-1){
                    first = nums[i];
                }else {
                    last = nums[i];
                    break;
                }
                i--;
            }
        }
        return new int[]{first,last};
        //    没看懂位运算解法
//        int diff = 0;
//        for(int i:nums) {
//            diff ^= i;
//            System.out.println(diff);
//        }
//        int rec = diff;
//        System.out.println(Integer.toBinaryString(6));
//        System.out.println(Integer.toBinaryString(-6));
//        diff &= -diff;
//        System.out.println();
//        System.out.println(diff);
//        System.out.println();
//        int[] res = new int[2];
//        System.out.println(res[0]);
//        for(int cur:nums) {
//            System.out.println("i:"+cur);
//            if((cur & diff)==0){
//                res[0] ^= cur;
//                System.out.println("if:"+res[0]);
//            }
//        }
//        res[1] = rec ^ res[0];
//        System.out.println(res[1]);
//        return res;
    }

    /**
     * @date 2018/7/31 10:41
     * leetcode 268 easy
     * Missing Number || 遗失号码
     * Array && Math && Bit Manipulation || 队列 && 数学 && 位运算
     */
    public int missingNumber(int[] nums) {
        boolean[] flag = new boolean[nums.length+1];
        for (boolean cur:flag){
            cur = false;
        }
        for (int cur: nums){
            flag[cur] = true;
        }
        for (int i=0;i<flag.length;i++){
            if (!flag[i]){
                return i;
            }
        }
        return 0;

//        求和，用差
//        int n = nums.length;
//        int total = (0 + n) * (n + 1) / 2;
//        for (int num : nums) {
//            total -= num;
//        }
//        return total;
    }

    /**
     * @date 2018/7/26 10:14
     * leetcode 283 easy
     * Move Zeroes || 移动零
     * Array && Two Pointers || 队列 && 两个指针
     */
    public void moveZeroes(int[] nums) {
        int zeroCur = 0;
        for (int i=0;i<nums.length;i++){
            if (nums[i]==0){
                zeroCur++;//游标尺：标记最远的0的下标
            }else{
                if (zeroCur>0){
                    //非0数赋值到最远0的位置
                    nums[i-zeroCur] = nums[i];
                    nums[i] = 0;
                }
            }
        }
    }

    /**
     * @date 18-05-30 16:17
     * leetcode 371 easy
     * Sum of Two Integers || 两整数之和
     * Bit Manipulation || 位运算
     */
    public int getSum(int a, int b) {
        while(b!=0){
            int c = a;
            a = a^b;
            b = c&b;
            b = b<<1;
        }
        return a;
    }

    /**
     * @date 2018/8/3 14:15
     * leetcode 414 easy
     * Third Maximum Number || 第三个最大数量
     * Array || 队列
     * 记录最大值 次大值 三大值 且数字相等不递推
     */
    public int thirdMax(int[] nums) {
//        c>max1 c->max1->max2->max3 num[1]++
//        c==max1 c==min num[1]++
//        c>max2 c->max2->max3 num[2]++
//        c==max2 c==min num[2]++
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int max3 = Integer.MIN_VALUE;
        boolean f1 = false;
        boolean f2 = false;
        boolean f3 = false;

        for (int cur:nums){
            if (cur>=max1){
                if (cur!=max1){
                    max3 = max2;
                    f3 = f2;
                    max2 = max1;
                    f2 = f1;
                    max1 = cur;
                }
                f1 = true;
            }else if (cur>=max2){
                if (cur!=max2){
                    max3 = max2;
                    f3 = f2;
                    max2 = cur;
                }
                f2 = true;
            }else if (cur>=max3){
                if (cur!=max3){
                    max3 = cur;
                }
                f3 = true;
            }
        }
        if (f3){
            return max3;
        }else {
            return max1;
        }
    }

    /**
     * @date 2018/7/26 9:38
     * leetcode 448 easy
     * Find All Numbers Disappeared in an Array || 查找数组中消失的所有数字
     * Array || 队列
     */
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> request = new ArrayList<>();
        int[] newArray = new int[nums.length];

        for (int cur:nums){
            newArray[cur-1]++;
        }
        for (int i=0;i<newArray.length;i++){
            if (newArray[i]==0){
                request.add(i+1);
            }
        }
        return request;
    }

    /**
     * @date 2018/7/17 9:22
     * leetcode 461 easy
     * Hamming Distance || 汉明距离
     * Bit Manipulation || 位运算
     */
    public int totalHammingDistance(int[] nums) {
        int request = 0;
        for (int i=0;i<nums.length;i++){
            for (int j=0;j<i;j++){
                request+=Integer.bitCount(nums[i]^nums[j]);
            }
        }
        return request;
    }

    /**
     * @date 2018/7/25 11:52
     * leetcode 485 easy
     * Max Consecutive Ones || 最高连续一次
     * Array || 队列
     */
    public int findMaxConsecutiveOnes(int[] nums) {
        int len=0;
        int max=0;
        for(int cur:nums){
            if (cur==1){
                len++;
            }else{
                if (len>max){
                    max = len;
                }
                len = 0;
            }
        }
        if (len>max){
            max = len;
        }
        return max;
    }

    /**
     * @date 2019/1/14 17:09
     * leetcode 509 easy
     * Fibonacci Number || 斐波那契数
     * Array || 数列
     **/
    public int fib(int N) {
//        递归 分支树
//        if (N==0) return 0;
//        if (N==1) return 1;
//        return fib(N-1)+fib(N-2);
//        递推 记录上值
        if (N<2) return N;
        int now = 1;
        int pre = 0;
        for (int i=2;i<=N;i++){
            now+=pre;
            pre = now-pre;
        }
        return now;
    }

    /**
     * @date 2018/7/20 17:35
     * leetcode 561 easy
     * Array Partition I || 数组分区I
     * Array || 队列
     */
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int s=0;
        for (int i=0;i<nums.length;i+=2){
            s+=nums[i];
        }
        return s;
    }

    /**
     * @date 2018/7/24 17:40
     * leetcode 566 easy
     * Reshape the Matrix || 重塑矩阵
     * Array || 队列
     */
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int row = nums.length;
        int col = nums[0].length;
        if (row*col!=r*c){
            return nums;
        }
        int lr=0,lc=0;
        int[][] newNums = new int[r][c];
        for (int i=0;i<r;i++){
            for (int j=0;j<c;j++){
                newNums[i][j] = nums[lr][lc++];
                if (lc>=col){
                    lc=0;
                    lr++;
                }
            }
        }
        return newNums;
    }

    /**
     * @date 2018/8/6 9:49
     * leetcode 605 easy
     * Can Place Flowers || 可以放花
     * Array || 队列
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
//    -1 length 视为0
//    i i-1 i+1 均为0 i=1 n--
        for (int i=0;i<flowerbed.length;i++){
            int left = (i-1>=0)?flowerbed[i-1]:0;
            int right = (i+1<flowerbed.length)?flowerbed[i+1]:0;
            if (left==0 && flowerbed[i]==0 && right==0){
                flowerbed[i] = 1;
                n--;
            }
        }
        return n<=0;
    }

    /**
     * @date 2018/8/2 16:37
     * leetcode 643 easy
     * Maximum Average Subarray I || 最大平均子阵列I
     * Array || 队列
     */
    public double findMaxAverage(int[] nums, int k) {
//    滑动窗口模式
//    计算前k个和,丢弃一个新增一个，比较前后大小
//    [0,k-1] [1,k] [2,k+1]
        int sum = 0;
        for (int i=0;i<k;i++){
            sum+=nums[i];
        }
        int max = sum;
        for (int i=0;i<nums.length-k;i++){
            sum-=nums[i];
            sum+=nums[i+k];
            if (sum>max){
                max = sum;
            }
        }
        return (double)max/k;
    }

    /**
     * @date 2018/7/17 11:03
     * leetcode 657 easy
     * Judge Route Circle || 法官路线圈
     * String || 字符串
     */
    public boolean judgeCircle(String moves) {
        int x=0,y=0;
        for (char chCur:moves.toCharArray()){
            switch (chCur){
                case 'U':
                    y++;
                    break;
                case 'D':
                    y--;
                    break;
                case 'L':
                    x--;
                    break;
                case 'R':
                    x++;
                    break;
                default:break;
            }
        }
        return (x==0 && y==0);
    }

    /**
     * @date 2018/7/30 15:53
     * leetcode 661 easy
     * Image Smoother || 图像更流畅
     * Array || 队列
     */
    public int[][] imageSmoother(int[][] M) {
        //行数，不是行内元素数
        int row = M.length;
        //列数
        int col = M[0].length;
        int[][] N = new int[row][col];
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                int num=0;
                int sum=0;
                for(int nr=i-1;nr<=i+1;nr++){
                    for (int nc=j-1;nc<=j+1;nc++){
                        if (nr>=0 && nr<row && nc>=0 && nc<col){
                            sum+=M[nr][nc];
                            num++;
                        }
                    }
                }
                N[i][j] = sum/num;
            }
        }
        return N;
    }

    /**
     * @date 2018/8/1 15:29
     * leetcode 674 easy
     * Longest Continuous Increasing Subsequence || 最长的连续增加子序列
     * Array || 队列
     */
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length==0){
            return 0;
        }
        int len = 1;
        int maxLen = 0;
        for (int i=1;i<nums.length;i++){
            if (nums[i]>nums[i-1]){
                len++;
            }else {
                if (len>maxLen){
                    maxLen = len;
                }
                len = 1;
            }
        }
        if (len>maxLen){
            maxLen = len;
        }
        return maxLen;
    }

    /**
     * @date 18-05-30 17:17
     * leetcode 682 easy
     * Baseball Game || 棒球比赛
     * Stack || 栈
     */
    public int calPoints(String[] ops) {
        Stack<Integer> myStack = new Stack<>();
        int tempNum;
        for (String l_str:ops){
            switch (l_str.charAt(0)){
                case '+':
                    int lsNum = myStack.pop();
                    tempNum = lsNum + myStack.peek();
                    myStack.push(lsNum);
                    myStack.push(tempNum);
                    break;
                case 'D':
                    tempNum = myStack.peek()*2;
                    myStack.push(tempNum);
                    break;
                case 'C':
                    myStack.pop();
                    break;
                default:
                    tempNum = Integer.parseInt(l_str);
                    myStack.push(tempNum);
                    break;
            }
        }
        int num = 0;
        while(!myStack.empty()){
            num+=myStack.peek();
            myStack.pop();
        }
        return num;
    }

    /**
     * @date 18-06-15 09:27
     * leetcode 693 easy
     * Binary Number with Alternating Bits || 交替位二进制数
     * Bit Manipulation || 位运算
     */

    public boolean hasAlternatingBits(int n) {
        String source = Integer.toBinaryString(n);
        for (int i=1;i<source.length();i++){
            if (source.charAt(i)==source.charAt(i-1)){
                return false;
            }
        }
        return true;
//        for (int last=-1; n>0; n>>=1) {
//            if (n%2 == last) return false;
//            last = n%2;
//        }
//        return true;
    }

    /**
     * @date 2018/8/1 12:39
     * leetcode 697 easy
     * Degree of an Array || 数组的度数
     * Array || 队列
     */
    public int findShortestSubArray(int[] nums) {
//        K:数字  V:对象
        Map<Integer,numSub> curMap = new HashMap<>();
        int max = Integer.MIN_VALUE;
        int cur = 0;
        for (int i=0;i<nums.length;i++){
            numSub curN = curMap.get(nums[i]);
            if (curN==null){
                curN = new numSub(i,1);
            }else {
                curN.countAdd();
                curN.setLength(i);
            }
            curMap.put(nums[i],curN);
        }
        for (Map.Entry<Integer,numSub> entry:curMap.entrySet()){
            int key = entry.getKey();
            numSub value = entry.getValue();

            int count = value.count;
            if (count > max){
                max = count;
                cur = key;
            }else if (count == max){
                numSub valuePrev = curMap.get(cur);
                int v1 = valuePrev.length;
                int v2 = value.length;
                if (v1>v2){
                    cur = key;
                    max = count;
                }
            }
        }
        return curMap.get(cur).length+1;
    }

    /**
     * 697的工具类
     */
    private class numSub{
        private int left;
        private int length;
        private int count;

        public numSub(int left, int count) {
            this.left = left;
            this.length = 0;
            this.count = count;
        }

        public void setLength(int right) {
            this.length = right-this.left;
        }

        public void countAdd(){
            this.count++;
        }
    }

    /**
     * @date 2018/7/16 14:10
     * leetcode 709 easy
     * To Lower Case || 降低案例
     * null
     */
    public String toLowerCase(String str) {
        char[] chArray = str.toCharArray();
        for (int i=0;i<chArray.length;i++){
            if (chArray[i]>='A' && chArray[i]<='Z'){
                chArray[i]+=32;
            }
        }
        return new String(chArray);
    }

    /**
     * @date 2018/7/26 10:57
     * leetcode 717 easy
     * 1-bit and 2-bit Characters || 1位和2位字符
     * Array || 队列
     */
    public boolean isOneBitCharacter(int[] bits) {
//00：true
//11：false
//10：false
//01：null 不存在
//倒序
//1: 必 false
//00: 必 true
//010 F
//0110 T
//01110 F
//遇到 0 或 边界
        int len = bits.length;
        //0
        if (len==1){
            return true;
        }
        //1
        if (bits[len-1]==1){
            return false;
        }
        //00
        if (bits[len-1]==0 && bits[len-2]==0){
            return true;
        }
        //1的数量
        int num1 = 1;
        //01...
        for (int i=bits.length-3;i>=0;i--){
            if (bits[i]==1){
                num1++;
            }else {
                break;
            }
        }
        if (num1%2==0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * @date 2018/8/1 17:45
     * leetcode 724 easy
     * Find Pivot Index || 查找数据透视索引
     * Array || 队列
     */
    public int pivotIndex(int[] nums) {
//    特殊值处理len: 0 1
//    f0z游标
//    curL左游标 将f0z向左偏移 只能-
//    curR右游标 将f0z向右偏移 只能+
//    f0z<0 需要向右偏移
//        NL<0 f0z-NL
//        NR>0 f0z+NR
//        判断绝对值||随意？？
//    f0z>0 需要向左偏移
//        NL>0 f0z-NL
//        NR<0 f0z+NR
//    fz=0 需要判断
//        curL=curR 正确结束
//        curL>curR 失败结束
//        curL<curR 继续
//    以上思路错误 无法处理 ...0,-1... 或者 ...-1,0...的情况

//    计算总和S
//    计算[0~cur)的和，S-L可以得到R+cur
//    对比L和R
        int sum=0;
        for (int cur:nums){
            sum+=cur;
        }
        int sumLeft = 0;
        for (int i=0;i<nums.length;i++){
            if (sumLeft == sum-sumLeft-nums[i]){
                return i;
            }
            sumLeft+=nums[i];
        }
        return -1;
    }

    /**
     * @date 2018/7/20 18:05
     * leetcode 728 easy
     * Self Dividing Numbers || 自我分割数
     * Math || 数学
     */
    public List<Integer> selfDividingNumbers(int left, int right) {
        List<Integer> list = new ArrayList<>();
        for (int i=left;i<=right;i++){
            int cur = i;
            int max = 1;
            int flag= 0;
            while(cur != 0){
                cur/=10;
                max*=10;
                flag++;
            }
            int j;
            for (j=0;j<flag;j++){
                int temp = i%max/(max/10);
                if (temp == 0 || i%temp!=0){
                    break;
                }
                max/=10;
            }
            if (j==flag){
                list.add(i);
            }
        }
        return list;
    }

    /**
     * @date 2018/8/2 9:49
     * leetcode 747 easy
     * Largest Number At Least Twice of Others || 最少的人数至少两次
     * Array || 队列
     */
    public int dominantIndex(int[] nums) {
//    算出最大值 次大值
//    比较大小
        int max1 = Integer.MIN_VALUE;
        int max2 = Integer.MIN_VALUE;
        int flag = -1;
        for (int i=0;i<nums.length;i++){
            if (nums[i] > max1){
                max2 = max1;
                max1 = nums[i];
                flag = i;
            }else if (nums[i] > max2){
                max2 = nums[i];
            }
        }
        if (max1>=max2*2){
            return flag;
        }
        return -1;
    }

    /**
     * @date 2018/7/24 11:53
     * leetcode 766 easy
     * Toeplitz Matrix || Toeplitz矩阵
     * Array || 队列
     */
    public boolean isToeplitzMatrix(int[][] matrix) {
        int[] row;
        int[] nextRow;
        for (int i=0;i<matrix.length-1;i++){
            row = matrix[i];
            nextRow = matrix[i+1];
            for (int j=0;j<row.length-1;j++){
                if (row[j]!=nextRow[j+1]){
                    return false;
                }
            }
        }
        return true;

//        for (int i = 1; i < matrix.length; i++) {
//            int j=1;
//            for (; j < i && j < matrix[0].length; j++) {
//                if(matrix[i][j]!=matrix[i-j][0]) return false;
//            }
//            for (;j < matrix[0].length; j++) {
//                if(matrix[i][j]!=matrix[0][j-i]) return false;
//            }
//        }
//        return true;

//        if(matrix == null || matrix.length == 0 || matrix[0].length == 0){
//            return false;
//        }
//        int m = matrix.length;
//        int n = matrix[0].length;
//        for(int i = 0; i < m; i++){
//            for(int j = 0; j < n; j++){
//                if(i > 0 && j > 0 && matrix[i][j] != matrix[i - 1][j - 1]){
//                    return false;
//                }
//            }
//        }
//        return true;
    }

    /**
     * @date 2018/7/16 12:03
     * leetcode 771 easy
     * Jewels and Stones || 宝石和石头
     * Hash Table || 哈希表
     */
    public int numJewelsInStones(String J, String S) {
        int num = 0;
        for(char chCur: J.toCharArray()){
            for (char chScur: S.toCharArray()){
                if (chCur == chScur){
                    num++;
                }
            }
        }
        return num;
    }

    /**
     * @date 2018/7/16 14:44
     * leetcode 804 easy
     * Unique Morse Code Words || 独特的摩尔斯电码字
     * String || 字符串
     */
    static String[] strArray = new String[]{".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
    public int uniqueMorseRepresentations(String[] words) {
        Set<String> stringSet = new HashSet<>();
        for (String strCur:words){
            String newString = "";
            for (char chCur:strCur.toCharArray()){
                newString += strArray[chCur-'a'];
            }
//            System.out.println(newString);
            stringSet.add(newString);
        }
        return stringSet.size();
    }

    /**
     * @date 2018/7/21 9:25
     * leetcode 806 easy
     * Number of Lines To Write String || 要写字符串的行数
     * null
     */
    public int[] numberOfLines(int[] widths, String S) {
        int length = 100;
        int row = 1;
        for (char cur:S.toCharArray()){
            int temp = widths[cur-'a'];
            length-=temp;
            if (length<0){
                row++;
                length=100-temp;
            }
        }
        return new int[]{row,100-length};
    }

    /**
     * @date 2018/7/30 17:50
     * leetcode 830 easy
     * Positions of Large Groups || 大型团体的位置
     * Array || 队列
     */
    public List<List<Integer>> largeGroupPositions(String S) {
        List<List<Integer>> listList = new ArrayList<>();
        List<Integer> list = null;
        for (int i=2;i<S.length();i++){
            if (S.charAt(i-2)==S.charAt(i-1) && S.charAt(i-1)==S.charAt(i)){
                if (list==null){
                    list = new ArrayList<>(2);
                    list.add(i-2);
                }
            }else if (S.charAt(i-2)==S.charAt(i-1) && S.charAt(i-1)!=S.charAt(i)){
                if (list != null){
                    list.add(i-1);
                    listList.add(list);
                    list = null;
                }
            }
        }
        if (list != null){
            list.add(S.length()-1);
            listList.add(list);
        }
        return listList;
    }

    /**
     * @date 2018/7/17 9:42
     * leetcode 832 easy
     * Flipping an Image || 翻转图像
     * Array || 队列
     */
    public int[][] flipAndInvertImage(int[][] A) {
        int rowL = A.length;
        int colL = A[0].length;
        for (int i=0;i<rowL;i++){
            for (int j=0;j<(colL+1)/2;j++){
                int temp = A[i][j];
                A[i][j] = A[i][colL-1-j] ^1;
                A[i][colL-1-j] = temp ^1;
            }
        }
        return A;
    }

    /**
     * @date 2018/8/7 15:33
     * leetcode 840 easy
     * Magic Squares In Grid || 网格中的魔方
     * Array || 队列
     */
    public int numMagicSquaresInside(int[][] grid) {
//    grid 遍历范围 [0,len-3]
//    1-9不重复且 行 列 对角线 和相等
//    中心必为5 和必为15
        int row = grid.length;
        int col = grid[0].length;
        int num = 0;
        for (int i=0;i<=row-3;i++){
            for (int j=0;j<=col-3;j++){
                if (grid[i+1][j+1]!=5){
                    continue;
                }
                if (magic(grid[i][j],   grid[i][j+1],   grid[i][j+2],
                        grid[i+1][j], grid[i+1][j+1], grid[i+1][j+2],
                        grid[i+2][j], grid[i+2][j+1], grid[i+2][j+2])){
                    num++;
                }
            }
        }
        return num;
    }

    /**
     * 840 function
     * @param vals
     * @return
     */
    private boolean magic(int... vals){
        boolean[] unqi = new boolean[10];
        for (boolean cur:unqi){
            cur = false;
        }
        for (int cur:vals){
            if (cur<1||cur>9){
                return false;
            }
            unqi[cur] = true;
        }
        for (int i=1;i<unqi.length;i++){
            if (!unqi[i]){
                return false;
            }
        }
        return (vals[0] + vals[1] + vals[2] == 15 &&
                vals[3] + vals[4] + vals[5] == 15 &&
                vals[6] + vals[7] + vals[8] == 15 &&
                vals[0] + vals[3] + vals[6] == 15 &&
                vals[1] + vals[4] + vals[7] == 15 &&
                vals[2] + vals[5] + vals[8] == 15 &&
                vals[0] + vals[4] + vals[8] == 15 &&
                vals[2] + vals[4] + vals[6] == 15);
    }

    /**
     * @date 2018/8/8 11:54
     * leetcode 849 easy
     * Maximize Distance to Closest Person || 最大化距离最近的人
     * Array || 队列
     */
    public int maxDistToClosest(int[] seats) {
//    计算最长连续0的开始位置 end，长度 len
//    len+1/2
//    考虑左边界 右边界
//    左侧0长度 l0 右侧0长度 r0
        int l0 = 0;
        int r0 = 0;
        boolean flag = true;
        int maxLen = 0;
        int len = 0;
        int i;
        for (i=0;i<seats.length;i++){
            if (seats[i]==0){
                if (flag){
                    l0++;
                    len++;
                }else {
                    len++;
                }
            }else {
                flag = false;
                if (len>maxLen){
                    maxLen = len;
                }
                len = 0;
            }
        }

        if (seats[i-1]==0){
            if (len>maxLen){
                maxLen = len;
            }
            r0 = len;
        }

        int max = (maxLen+1)/2;
        if (l0>max){
            max = l0;
        }
        if (r0>max){
            max = r0;
        }

        return max;
    }

    /**
     * @date 2018/7/20 9:55
     * leetcode 852 easy
     * Peak Index in a Mountain Array || 山脉阵列中的峰值指数
     * Binary Search || 二进制搜索
     */
    public int peakIndexInMountainArray(int[] A) {
        if(A == null || A.length == 0){
            return 0;
        }
        int left = 0;
        int right = A.length -1;
        while(left < right){
            int mid = left + (right-left)/2;
            if (A[mid]<A[mid+1]){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return left;
    }

    /**
     * @date 2018/7/20 11:51
     * leetcode 867 easy
     * Transpose Matrix || 转置矩阵
     * Array || 队列
     */
    public int[][] transpose(int[][] A) {
        int row = A[0].length;
        int col = A.length;
        int[][] array = new int[row][col];
//        for (int i=0;i<col;i++){
//            for (int j=0;j<row;j++){
//                System.out.print(A[i][j]+" ");
//            }
//            System.out.println();
//        }
        for (int i=0;i<row;i++){
            for (int j=0;j<col;j++){
                array[i][j] = A[j][i];
            }
        }
//        for (int i=0;i<row;i++){
//            for (int j=0;j<col;j++){
//                System.out.print(array[i][j]+" ");
//            }
//            System.out.println();
//        }
        return array;
    }

    /**
     * @date 2018/12/26 16:27
     * leetcode 888 easy
     * Fair Candy Swap || 公平的糖果交换
     * Array || 队列
     **/
    public int[] fairCandySwap(int[] A, int[] B) {
//        A数组之和-B数组之和/2=A付出-B付出。
//        优化，遍历A数组，压缩元素到一维数组中，然后再遍历二维数组，变二重遍历为两个一重遍历
        int sumA = 0;
        int sumB = 0;
        for (int cur :
                A) {
            sumA += cur;
        }
        for (int cur :
                B) {
            sumB += cur;
        }
        int mark = (sumA-sumB)/2;
        if (mark==0){
            return new int[]{};
        }
        boolean compress[] = new boolean[200001];
        for (int i = 0; i < 200001; i++) {
            compress[i] = false;
        }
        for (int i = 0; i < A.length; i++) {
            compress[A[i]] = true;
        }
        for (int cur :
                B) {
            int temp = cur + mark;
            if (temp>0 && compress[temp]){//A-B=mark;A=B+mark存在
                return new int[]{temp,cur};
            }
        }
        return null;
    }

    /**
     * @date 2018/12/26 16:47
     * leetcode 896 easy
     * Monotonic Array || 单调数列
     * Array || 队列
     **/
    public boolean isMonotonic(int[] A) {
//        设置一个数区分递增递减。
//        设置一个数区分是否大于小于。

//        或者遍历，后者比前者大则+1，最后计算数量
        boolean flag = false;
        boolean add = false;
        int mark = A[0];
        for (int cur :
                A) {
            if (cur>mark){
                if (!flag){//还没有判断过
                    flag = true;
                    add = true;
                }else if (!add){//递增列出现减数
                    return false;
                }
            }else if (cur<mark){
                if (!flag){//还没有判断过
                    flag = true;
                    add = false;
                }else if (add){//递减列出现增数
                    return false;
                }
            }
            mark = cur;
        }
        return true;
    }

    /**
     * @date 2018/12/25 23:52
     * leetcode 905 easy
     * Sort Array By Parity || 按奇偶校验排序数组
     * Array || 队列
     **/
    public int[] sortArrayByParity(int[] A) {
//        外层循环，遍历数组，遇到奇数就和cur交换
//        当cur指向奇数时，内层循环，找到最前偶数

//        if (A.length<2){
//            return A;
//        }
//        int cur = 1;
//        int temp;
//        for (int i=0;i<A.length;i++){
//            while (cur<=i || A[cur]%2!=0){//找到最前的偶数
//                cur++;
//                if (cur==A.length) return A;
//            }
//            if (A[i]%2!=0){//是奇数
//                temp = A[i];
//                A[i] = A[cur];
//                A[cur] = temp;
//            }
//        }
//        return A;

//        新建一个数组，偶数存储到开头、奇数存储到结尾。最后把新建的数组抛出
        int[] B = new int[A.length];
        int even = 0;
        int odd = A.length-1;
        for (int i=0;i<A.length;i++){
            if (A[i]%2==0){
                B[even++] = A[i];
            }else {
                B[odd--] = A[i];
            }
        }
        return B;
    }

    /**
     * @date 2018/12/26 16:56
     * leetcode 922 easy
     * Sort Array By Parity II || 按奇偶校验排序数组II
     * Array && Sort || 队列 && 排序
     **/
    public int[] sortArrayByParityII(int[] A) {
        int[] B = new int[A.length];
        int odd = 1;
        int even = 0;
        for (int cur :
                A) {
            if (cur%2==0){
                B[even] = cur;
                even+=2;
            }else {
                B[odd] = cur;
                odd+=2;
            }
        }
        return B;
    }

    /**
     * @date 2019/1/14 19:31
     * leetcode 976 easy
     * Largest Perimeter Triangle || 最大的周边三角形
     * Array && Math || 队列 && 数学
     **/
    public int largestPerimeter(int[] A) {
//        取出能组成三角形的最大的三边
//        排序后，取出三个数比较，不成就下调一个
        Arrays.sort(A);
        int len = A.length;
        int edge[] = new int[3];
//        A数组游标和三角游标
        int flag = 0;
        int cur = 0;
        while(true){
            edge[(flag)%3] = A[len-flag-1];
            flag++;
            if (flag>=3){
//                最大边大于两小边之和
                if (edge[(cur)%3]>=edge[(cur+1)%3]+edge[(cur+2)%3]){
//                    舍弃最大边
                    cur++;
                }else {
                    break;
                }
            }
            if (flag==len) return 0;
        }
        return edge[0] + edge[1] + edge[2];
    }

    /**
     * @date 2020/6/12 17:01
     * leetcode 1108 easy
     * Defanging an IP Address || 清除IP地址
     *
     * 思路:遍历找到三个.的下标，再通过stringBuilder.insert(6,"(")操作
     */
    public String defangIPaddr(String address) {
        int a[] = new int[3];
        int cur = 0;
        for (int i=0;i<address.length() ;i++ ) {
            if (address.charAt(i) == '.') {
                a[cur] = i;
                cur++;
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(address);

        for (int i=cur-1;i>=0 ;i-- ) {
            stringBuilder.insert(a[i]+1,']');
            stringBuilder.insert(a[i],'[');
        }

        return stringBuilder.toString();
    }

    /**
     * @date 2020/6/15 13:24
     * leetcode 1365 easy
     * How Many Numbers Are Smaller Than the Current Number || 多少个数字小于当前数字
     *
     * 思路:遍历
     */
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] request = new int[nums.length];
        for (int i=0;i<nums.length ;i++ ) {
            request[i] = 0;
        }

        for (int i=0;i<nums.length ;i++ ) {
            //节约一半时间
            for (int j=i+1;j<nums.length ;j++ ) {
                if (nums[i]>nums[j]) {
                    request[i]++;
                }else if (nums[i]<nums[j]) {
                    request[j]++;
                }
            }
        }
        return request;
    }

    /**
     * @date 2020/6/12 16:53
     * leetcode 1431 easy
     * Kids With the Greatest Number of Candies || 糖果数量最多的孩子
     *
     * 思路:先找最多的孩子，遍历各自+n，看能不能大于等于max
     */
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        List<Boolean> flagList = new ArrayList<Boolean>();
        int max = 0;
        for (int i=0;i<candies.length ;i++ ) {
            if (candies[i] > max) {
                max = candies[i];
            }
        }

        for (int i=0;i<candies.length ;i++ ) {
            if (candies[i]+extraCandies >= max) {
                flagList.add(true);
            }else{
                flagList.add(false);
            }
        }

        return flagList;
    }

    /**
     * @date 2020/6/12 16:39
     * leetcode 1470 easy
     * Shuffle the Array || 随机排列
     *
     * 思路:hash的冲突解决，选定一个数之后，循环数n个，如果未被选定则输出，如果已选定则+1
     */
    public int[] shuffle(int[] nums, int n) {
        int cur = 0;
        int[] request = new int[nums.length];
        int requestCur = 0;
        boolean[] numsFlag = new boolean[nums.length];
        for (int i=0;i<nums.length ;i++ ) {
            numsFlag[i] = true;
        }
        for (int i=0;i<nums.length ;i++ ) {
            request[requestCur++] = nums[cur];
            numsFlag[cur] = false;
            cur+=n;
            cur%=nums.length;
            //已选定
            while (!numsFlag[cur] && requestCur!=nums.length) {
                cur++;
            }
        }
        return request;
    }


}
