package com.mine.other;

/**
 * @author CaoY
 * @date 2023-03-23 17:16
 * @description 求两个字符串的最长公共子字符串
 * 动态规划
 * 这个代码首先定义了一个二维数组dp来存储状态，
 * 然后遍历两个字符串的每一个字符，
 * 如果它们相等，就更新dp数组，
 * 并记录最长公共子串的长度和结束位置。
 * 最后，根据结束位置和长度返回最长公共子串。
 */
public class LongestSizeSubStrDemo {
    public static void main(String[] args) {

        String str1 = "helloworld";
        String str2 = "below";
        System.out.println(LTS(str1, str2));
    }

    public static String LTS(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() == 0 || str2.length() == 0) {
            return "";
        }

        // dp[i][j] -> 以str1的第i个元素 和 str2 的第j个元素结尾的字符串的长度
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        int maxLen = 0;
        int endIndex = 0;

        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLen) {
                        maxLen = dp[i][j];
                        endIndex = i;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }

        return str1.substring(endIndex - maxLen, endIndex);
    }
}
