package complete.design.system;

import java.util.*;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 设计推特
 *
    设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：

    postTweet(userId, tweetId): 创建一条新的推文
    getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
    follow(followerId, followeeId): 关注一个用户
    unfollow(followerId, followeeId): 取消关注一个用户
    示例:

    Twitter twitter = new Twitter();

    // 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
    twitter.postTweet(1, 5);

    // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
    twitter.getNewsFeed(1);

    // 用户1关注了用户2.
    twitter.follow(1, 2);

    // 用户2发送了一个新推文 (推文id = 6).
    twitter.postTweet(2, 6);

    // 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
    // 推文id6应当在推文id5之前，因为它是在5之后发送的.
    twitter.getNewsFeed(1);

    // 用户1取消关注了用户2.
    twitter.unfollow(1, 2);

    // 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
    // 因为用户1已经不再关注用户2.
    twitter.getNewsFeed(1);

 */
public class design_twitter_355 {

    public class Twitter {
        private int timeStamp=0;

        // sequence to find if user exist
        private Map<Integer, User> userMap;

        // Tweet link to next Tweet so that we can save a lot of time
        // when we execute getNewsFeed(userId)
        private class Tweet{
            public int id;
            public int time;
            public Tweet next;

            public Tweet(int id){
                this.id = id;
                time = timeStamp++;
                next=null;
            }
        }


        // OO design so User can follow, unfollow and post itself
        public class User{
            public int id;
            public Set<Integer> followed;
            public Tweet tweet_head;

            public User(int id){
                this.id=id;
                followed = new HashSet<>();
                follow(id); // first follow itself
                tweet_head = null;
            }

            public void follow(int id){
                followed.add(id);
            }

            public void unfollow(int id){
                followed.remove(id);
            }


            // everytime user post a new tweet, add it to the hard of tweet list.
            public void post(int id){
                Tweet t = new Tweet(id);
                t.next=tweet_head;
                tweet_head=t;
            }
        }




        /** Initialize your data structure here. */
        public Twitter() {
            userMap = new HashMap<Integer, User>();
        }

        /** Compose a new tweet. */
        public void postTweet(int userId, int tweetId) {
            if(!userMap.containsKey(userId)){
                User u = new User(userId);
                userMap.put(userId, u);
            }
            userMap.get(userId).post(tweetId);

        }



        // Best part of this.
        // first get all tweets lists from one user including itself and all people it followed.
        // Second add all heads into a max heap. Every time we poll a tweet with
        // largest time stamp from the heap, then we add its next tweet into the heap.
        // So after adding all heads we only need to add 9 tweets at most into this
        // heap before we get the 10 most recent tweet.
        public List<Integer> getNewsFeed(int userId) {
            List<Integer> res = new LinkedList<>();

            if(!userMap.containsKey(userId))   return res;

            Set<Integer> users = userMap.get(userId).followed;
            PriorityQueue<Tweet> q = new PriorityQueue<Tweet>(users.size(), (a,b)->(b.time-a.time));
            for(int user: users){
                Tweet t = userMap.get(user).tweet_head;
                // very imporant! If we add null to the hard we are screwed.
                if(t!=null){
                    q.add(t);
                }
            }
            int n=0;
            while(!q.isEmpty() && n<10){
                Tweet t = q.poll();
                res.add(t.id);
                n++;
                if(t.next!=null)
                    q.add(t.next);
            }

            return res;

        }

        /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
        public void follow(int followerId, int followeeId) {
            if(!userMap.containsKey(followerId)){
                User u = new User(followerId);
                userMap.put(followerId, u);
            }
            if(!userMap.containsKey(followeeId)){
                User u = new User(followeeId);
                userMap.put(followeeId, u);
            }
            userMap.get(followerId).follow(followeeId);
        }

        /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
        public void unfollow(int followerId, int followeeId) {
            if(!userMap.containsKey(followerId) || followerId==followeeId)
                return;
            userMap.get(followerId).unfollow(followeeId);
        }
    }

}
