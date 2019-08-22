
### 简介
MapReduce是简化的并行计算的编程模型，其编程思想简单来说就是：分散任务，汇总结果！

* 将一个大任务变成多个小任务并行执行（Map阶段）
* 将多个小任务的结果汇总起来（Reduce阶段）

下图为MapReduce的编程的WordCount的编程模型，从输入到输出，中间大致分为`Split`、`Map`、`Shuffle`、`Reduce`阶段，需要我们编程的只有`Map`阶段和`Reduce`阶段，`Split`和`Shuffle`阶段`MapReduce`框架会帮我们做，简化了我们写并行计算的难度。

* Split：将大文件划分为很多个小文件，也就是大任务变成多个小任务
* Map：并行执行多个小任务
* Shuffle：洗牌阶段，也就是通过网络传输的方式对中间结果进行聚合排序
* Reduce：将多个小任务的结果汇总起来

## 如何编写

例如：写一个统计单词出现次数的MapReduce程序，该如何下手？

#### 1. 编程模型分析：

通过编程模型分析，我们要弄清楚的是：

1. 明确数据的变化情况。例如：Map的输入数据（k1,v1）是什么？我们需要Map帮我们干什么？Map的输出数据（k2,v2）又是什么？
2. Map和Reduce的输入输出类型，例如：Text、IntWritable等

#### 2. 明确编程思路：

**Map()方法的功能：把各个单词分开，并且标记为1，表示这个单词出现了1次**
* 由v1 可得到 k2, v2    `(1, Hello World -> Hello, 1 | World, 1)`
* v1由Text类型转换为String：用toString()方法
* 按空格进行分词：split(" ")方法
* 输出k2, v2：context.write()方法

**Reduce方法：汇总每个单词的出现次数**
* 由k3, v3 可得到k4, v4 `(Hello, <1, 1> -> Hello, 2)`
* k4 = k3
* v4 = v3元素的和：遍历v3求和
* 输出k4, v4：context.write()方法

**编写Main方法（程序入口）：**
* 创建一个job和任务入口(指定主类)
* 指定job的mapper和输出的类型< k2， v2 >
* 指定job的reducer和输出的类型< k4， v4 >
* 指定job的输入和输出路径
* 执行job
* **注意：所有MapReduce程序的Main方法都是这个套路**

#### 3. 编写代码

> WordCountMapper.java

```java
package com.MRWordCount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

    @Override
    protected void map(LongWritable k1, Text v1, Context context)
            throws IOException, InterruptedException {
        //将v1有Text转换为Java的String类型，方便处理
        String data = v1.toString();
        //分词：v1为“hello world”,按空格来划分为多个单词,即： hello 和 world
        String[] words = data.split(" ");
        //输出k2  , v2
        for (String word : words) {
            context.write(new Text(word), new IntWritable(1));
        }
    }   
}
```

> WordCountReducer.java

```java
package com.MRWordCount;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    protected void reduce(Text k3, Iterable<IntWritable> v3, Context context) throws IOException, InterruptedException {
        //定义K4,并赋值为k3
        Text k4 = k3;
        //定义一个变量来放求和值
        int total=0;
        for (IntWritable v : v3) {
            total = total + v.get();//get()方法是将IntWritable数据变为Int类型
        }
        //输出 k4, v4
        context.write(k4, new IntWritable(total));   
    }   
}
```

> WordCountMain.java

```java
package com.MRWordCount;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class WordCountMain {
    public static void main(String[] args) throws Exception {
//      1. 创建一个job和任务入口(指定主类)
        Job job = Job.getInstance(new Configuration());
        job.setJarByClass(WordCountMain.class);
        
//      2. 指定job的mapper和输出的类型<k2 v2>
        job.setMapperClass(WordCountMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        
//      3. 指定job的reducer和输出的类型<k4  v4>
        job.setReducerClass(WordCountReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
//      4. 指定job的输入和输出路径
        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        
//      5. 执行job
        job.waitForCompletion(true);
    }
}
```

写好代码之后，之后就是打包工程，提交到Hadoop去执行了。

总结一下：编写MapReduce程序的方法（套路）是：  
1. 编程模型分析（最重要）：明确输入输出数据及其类型。
2. 明确编程思路（只要把一两个程序的思路研究透了，其他程序也就自然会写）
3. 编写代码，一般包括三个类，分别是： 
    * Mapper类，继承Mapper()方法，并重写其mapper()方法；
    * Reducer类，继承Reducer方法，并重写其reducer()方法；
    * Main类，实现程序入口main()方法，主要功能是把定义一个Job作业，把Mapper类和Reducer类整合起来。

