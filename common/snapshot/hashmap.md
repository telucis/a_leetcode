> size : 40
* 模型替换
    - 同构字符串  205  (双map)
    - 单词模式  290  (map&set)
    - 查找和替换模式  890  (双buckets)
    - 有效的字母异位词  242  (双bucket)
    - 字母异位词分组  49  (map<list>, 排序)
* 组合哈希
    - 回旋镖的数量  447  (map)
    - 适龄的朋友  825  (map)
* 桶计数
    - 存在重复元素III  220  (map[key存bucket值, value存真实值])
    - 存在重复元素II  219  (map[存index])
    - 存在重复元素  217  (set)
    - 最大交换  670  (bucket[0-9, 记录last position])
    - 最小时间差  539  (bucket[24*60])
* 中等哈希
    - 二倍数对数组  954  (treemap)
    - 猜数字游戏  299  (set[bulls], map[cows])
    - 自定义字符串排序  791  (buckets)
    - 字符串中的查找和替换  833  (map)
    - 在系统中查找重复文件  609  (map)
    - 无重复字符的最长子串  3  (map[字符最新索引])
    - 最小矩形面积  939  (map<set>[key存x,set存y])
    - 匹配子序列的单词数  792  (map<deque>[key存a-z,deque存word])
    - 森林中的兔子  781  (map[key存容积,value计数])
    - 有效的数独  36  (set[row|col|chunk])
    - 元音拼写检查器  966  (set[word], map[cap], map[vowel])
    - 单词子集  916  (buckets[a-z计数])
* 简单哈希
    - 数组的度  697  (map<list>[存index])
    - 总持续时间可被60整除的歌曲  1010  (map[存index])
    - 最长和谐子序列  594  (map)
    - 最长回文串  409  (map)
    - 两个列表的最小索引总和  599  (map)
    - 最短完整词  748  (map)
    - 两句话中的不常见单词  884  (map)
    - 验证外星语词典  953  (map)
    - 两个数组的交集  349  (set)
    - 两个数组的交集II  350  (map)
    - N天后的牢房  957  (set)
    - 分糖果  575  (set)
    - 快乐数  202  (set)
    - 强整数  970  (set)
* 罗马数字
    - 整数转罗马数字  12
    - 罗马数字转整数  13
