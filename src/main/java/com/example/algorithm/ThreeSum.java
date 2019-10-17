package com.example.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @program demo1
 * @description 从数组中找出3数之和等于0的
 * @author wangqian
 * created on 2019-10-11
 * @version  1.0.0
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] arr = {9, -20, 30, -7, 21, -1, 11,32, 4, 3, 3, 3 , -6, 2, 2, 2};
        Arrays.sort(arr);
        int len = arr.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            //当前数字大于0，三数之和一定大于0
            if (arr[i] > 0) {
                break;
            }
            if(i > 0 && arr[i] == arr[i-1]) {
                continue;
            }
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = arr[i] + arr[L] + arr[R];
                if (arr[i] == arr[L] || arr[i] == arr[R] || arr[L] ==arr[R]) {
                    L++;
                    R--;
                    continue;
                }
                if (sum == 0) {
                    ans.add(Arrays.asList(arr[i], arr[L], arr[R]));
                    while (L<R && arr[L] == arr[L+1]) {
                        L++; // 去重
                    }
                    while (L<R && arr[R] == arr[R-1]) {
                        R--; // 去重
                    }
                    L++;
                    R--;
                }
                else if (sum < 0) {
                    L++;
                } else if (sum > 0) {
                    R--;
                }
            }
        }
        System.out.println(ans.toString());
    }
}
