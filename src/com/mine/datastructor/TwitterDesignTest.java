package com.mine.datastructor;

import java.util.*;

/**
 * @author CaoY
 * @date 2023-03-02 21:17
 * @description 355. 设计推特
 * 链接：https://leetcode.cn/problems/design-twitter/
 */
public class TwitterDesignTest {

    public static void main(String[] args) {
        Twitter twitter = new Twitter();
        twitter.postTweet(1, 2000);
        twitter.follow(1, 2);
        for (int i = 0; i < 32; i++) {
            if (i % 3 == 0) {
                twitter.postTweet(1, 2001 + i);
            } else if (i % 3 == 1) {
                twitter.postTweet(2, 2001 + i);
            } else {
                twitter.postTweet(3, 2001 + i);
            }
        }
        twitter.follow(1, 3);
        // 可以通过注释掉下面这句来测试unfollow()的效果
        twitter.unfollow(1, 3);
        List<Integer> tweets = twitter.getNewsFeed(1);
        tweets.forEach(System.out::println);
    }
}

class Twitter {
    // 标记时间的全局量，因为获取推文的时候要求按时间排序
    private static int timestamp = 0;

    // 推文类
    private static class Tweet {
        private int id;
        private int time;
        private Tweet next;

        public Tweet(int id, int time) {
            this.id = id;
            this.time = time;
        }
    }

    // 用户类
    private static class User {
        private int id;
        public Set<Integer> followed;
        // 用户发表的推文链表头结点
        public Tweet head;

        public User(int userId) {
            followed = new HashSet<>();
            this.id = userId;
            this.head = null;
            // 关注一下自己，因为每个用户可以看到自己的推文，可以认为是默认关注自己的
            this.follow(this.id);
        }

        // 关注id为userId的用户
        public void follow(int userId) {
            followed.add(userId);
        }

        // 取关id为userId的用户，注意：绝对不能取关自己
        public void unFollowed(int userId) {
            if (userId != this.id) {
                followed.remove(userId);
            }
        }

        // 将新建的推文插入表头，故越靠前的推文time值越大
        public void post(int tweetId) {
            Tweet tweet = new Tweet(tweetId, timestamp++);
            tweet.next = head;
            head = tweet;
        }
    }

    // 需要一个映射将 userId 和 User 对象对应起来
    private Map<Integer, User> userMap = null;

    public Twitter() {
        userMap = new HashMap<>();
    }

    // 根据给定的 tweetId 和 userId 创建一条新推文。每次调用此函数都会使用一个不同的 tweetId
    public void postTweet(int userId, int tweetId) {
        // 若userId不存在，则新建
        if (!userMap.containsKey(userId)) {
            userMap.put(userId, new User(userId));
        }
        User u = userMap.get(userId);
        u.post(tweetId);
    }

    // ID 为 followerId 的用户开始关注 ID 为 followeeId 的用户。
    public void follow(int followerId, int followeeId) {
        // 若follower不存在，则新建
        if (!userMap.containsKey(followerId)) {
            User user = new User(followerId);
            userMap.put(followerId, user);
        }

        // 若followee不存在，则新建
        if (!userMap.containsKey(followeeId)) {
            User user = new User(followeeId);
            userMap.put(followeeId, user);
        }
        userMap.get(followerId).follow(followeeId);
    }

    // ID 为 followerId 的用户不再关注 ID 为 followeeId 的用户。如果id不存在，则什么都不做
    public void unfollow(int followerId, int followeeId) {
        if (userMap.containsKey(followerId)) {
            userMap.get(followerId).unFollowed(followeeId);
        }
    }

    // 检索当前用户新闻推送中最近 10 条推文的 ID 。
    // 新闻推送中的每一项都必须是由用户关注的人或者是用户自己发布的推文。
    // 推文必须 按照时间顺序由最近到最远排序 。
    // 体现算法的一个方法 - 利用优先队列合并 time 值最大的推文
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> result = new ArrayList<>();
        if (!userMap.containsKey(userId)) {
            return result;
        }
        // 关注列表的用户id
        Set<Integer> followedUsers = userMap.get(userId).followed;
        // 自动通过 time 属性从大到小排序，容量为上面 followedUsers 的大小
        PriorityQueue<Tweet> pq = new PriorityQueue<>(followedUsers.size(), (a, b) -> (b.time - a.time));

        // 先将链表头结点插入优先队列
        for (Integer id : followedUsers) {
            Tweet tweet = userMap.get(id).head;
            if (tweet == null) {
                continue;
            }
            pq.add(tweet);
        }

        while (!pq.isEmpty()) {
            // 最多返回 10 条就够了
            if (result.size() == 10) {
                break;
            }
            // 弹出 time 值最大的（最近发表的）
            Tweet tweet = pq.poll();
            result.add(tweet.id);
            // 将下一篇 Tweet 插入进行排序
            if (tweet.next != null) {
                pq.add(tweet.next);
            }
        }
        return result;
    }
}
