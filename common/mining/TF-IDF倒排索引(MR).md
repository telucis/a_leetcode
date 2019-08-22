> "倒排索引"是文档检索系统中最常用的数据结构，被广泛地应用于全文搜索引
它主要是用来存储某个单词（或词组）在一个文档或一组文档中的存储位置的映射，
即提供了一种根据内容来查找文档的方式。
由于不是根据文档来确定文档所包含的内容，
而是进行相反的操作，因而称为倒排索引（Inverted Index）。

### 详情描述
通常情况下，倒排索引由一个单词（或词组）以及相关的文档列表组成，文档列表中的文档或者是标识文档的ID号，或者是指文档所在位置的URL

更复杂的权重还可能要记录单词在多少个文档中出现过，以实现TF-IDF（Term Frequency-Inverse Document Frequency）算法，或者考虑单词在文档中的位置信息（单词是否出现在标题中，反映了单词在文档中的重要性）等。

### 设计思路

实现"倒排索引"只要关注的信息为：单词、文档URL及词频。但是在实现过程中，索引文件的格式会略有所不同，以避免重写OutPutFormat类。下面根据MapReduce的处理过程给出倒排索引的设计思路。

#### 1. Map过程

首先使用默认的TextInputFormat类对输入文件进行处理，得到文本中每行的偏移量及其内容。显然，Map过程首先必须分析输入的<key,value>对，得到倒排索引中需要的三个信息：**单词、文档URL和词频**。

这里存在两个问题：
    第一，<key,value>对只能有两个值，在不使用Hadoop自定义数据类型的情况下，需要根据情况将其中两个值合并成一个值，作为key或value值；
    第二，通过一个Reduce过程无法同时完成词频统计和生成文档列表，所以必须增加一个Combine过程完成词频统计。

这里讲单词和URL组成key值（如"MapReduce：file1.txt"），将词频作为value，这样做的好处是可以利用MapReduce框架自带的Map端排序，将同一文档的相同单词的词频组成列表，传递给Combine过程，实现类似于WordCount的功能。
#### 2. Combine过程

经过map方法处理后，Combine过程将key值相同的value值累加，得到一个单词在文档在文档中的词频。如果直接将输出作为Reduce过程的输入，在Shuffle过程时将面临一个问题：所有具有相同单词的记录（由单词、URL和词频组成）应该交由同一个Reducer处理，但当前的key值无法保证这一点，所以必须修改key值和value值。这次将单词作为key值，URL和词频组成value值（如"file1.txt：1"）。这样做的好处是可以利用MapReduce框架默认的HashPartitioner类完成Shuffle过程，将相同单词的所有记录发送给同一个Reducer进行处理。

#### 3. Reduce过程

经过上述两个过程后，Reduce过程只需将相同key值的value值组合成倒排索引文件所需的格式即可，剩下的事情就可以直接交给MapReduce框架进行处理了。

#### 4. 需要解决的问题

本实例设计的倒排索引在文件数目上没有限制，但是单词文件不宜过大（具体值与默认HDFS块大小及相关配置有关），要保证每个文件对应一个split。否则，由于Reduce过程没有进一步统计词频，最终结果可能会出现词频未统计完全的单词。可以通过重写InputFormat类将每个文件为一个split，避免上述情况。或者执行两次MapReduce，第一次MapReduce用于统计词频，第二次MapReduce用于生成倒排索引。除此之外，还可以利用复合键值对等实现包含更多信息的倒排索引。

### 具体实现

tf1.txt
```
    MapReduce is simple
```
tf2.txt
```
    MapReduce is powerful is simple
```
tf3.txt
```
    Hello MapReduce bye MapReduce
```
上传数据文件到HDFS上
```
[root@node1 ~]# hadoop fs -mkdir -p /usr/matrix/input/inverIndex/
[root@node1 ~]# cd /opt/modules/hadoop-2.5.1/data
[root@node1 data]# hadoop fs -put tf1.txt /usr/matrix/input/inverIndex/
[root@node1 data]# hadoop fs -put tf2.txt /usr/matrix/input/inverIndex/
[root@node1 data]# hadoop fs -put tf3.txt /usr/matrix/input/inverIndex/
[root@node1 data]# hadoop fs -ls /usr/matrix/input/inverIndex
```

InvertedIndexMapper.java
```java
package com.matrix.InverseIndex;

import java.io.IOException;
import java.util.StringTokenizer;

import javax.xml.crypto.dsig.keyinfo.KeyValue;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

/**
 * 倒序索引
 * 
 * InvertedIndexMapper
 */
public class InvertedIndexMapper extends Mapper<Object, Text, Text, Text> {

    // map要完成的任务：将输入的<key,Value>,转换成如下格式
    // MapReduce：file1.txt 1
    private Text keyInfo = new Text(); // 存储单词和URL组合

    private Text valueInfo = new Text(); // 存储词频

    private FileSplit split; // 存储Split对象

    @Override
    protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        // 获取<key,value>对所属的split的对象
        split = (FileSplit) context.getInputSplit();

        System.out.println(value.toString());

        StringTokenizer itr = new StringTokenizer(value.toString());

        while (itr.hasMoreTokens()) {
            // key值由单词和url组成，如：MapReduce：file1.txt
            // 获取文件的完整路径
            int splitIndex = split.getPath().toString().indexOf("tf");
            System.out.println(splitIndex);

            System.out.println(split.getPath().toString());

            keyInfo.set(itr.nextToken() + ":" + split.getPath().toString().substring(splitIndex));
            valueInfo.set("1");

            context.write(keyInfo, valueInfo);
        }
    }
}
```

InverseIndexCombine.java
```java
package com.matrix.InverseIndex;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InverseIndexCombine extends Reducer<Text, Text, Text, Text> {

    // Combine阶段：统计词频,输入<key,value>形式：MapReduce：file1.txt 1
    // key:MapReduce：file1.txt value:1
    private Text info = new Text();

    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 统计词频
        int sum = 0;

        for (Text value : values) {
            System.out.println("combine:" + value.toString());
            sum += Integer.parseInt(value.toString());
        }
        int splitIndex = key.toString().indexOf(":");
        System.out.println("combine splitIndex:" + splitIndex);
        System.out.println("combine splitIndex :" + key.toString().substring(splitIndex + 1));

        // 重新设置value值由URL和词频组成
        info.set(key.toString().substring(splitIndex + 1) + ":" + sum);
        System.out.println("combine 单词 :" + key.toString().substring(0, splitIndex));
        // 重新设置key值为单词
        key.set(key.toString().substring(0, splitIndex));

        context.write(key, info);
    }
}
```

InverseIndexReducer.java
```java
package com.matrix.InverseIndex;

import java.io.IOException;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class InverseIndexReducer extends Reducer<Text, Text, Text, Text> {

    private Text result = new Text();

    // 实现reduce函数
    @Override
    protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        // 生成文档列表
        String fileList = new String();

        for (Text value : values) {
            fileList += value.toString() + ";";
        }

        System.out.println("fileList:" + fileList);
        result.set(fileList);

        context.write(key, result);
    }
}
```

InverseIndexTest.java
```java
package com.matrix.InverseIndex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class InverseIndexTest {

    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://node1:8020");

        FileSystem fs = FileSystem.get(conf);

        Job job = Job.getInstance(conf);

        job.setJarByClass(InverseIndexTest.class);

        job.setMapperClass(InvertedIndexMapper.class);
        job.setCombinerClass(InverseIndexCombine.class);
        job.setReducerClass(InverseIndexReducer.class);

        // 设置Mapper输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        // 设置reducer输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.setInputPaths(job, "/usr/matrix/input/inverIndex");

        Path outer = new Path("/usr/matrix/output/inverIndex/");

        if (fs.exists(outer)) {
            fs.delete(outer, true);
        }

        FileOutputFormat.setOutputPath(job, outer);

        boolean f = job.waitForCompletion(true);

        if (f) {
            System.out.println("程序执行成功！");
        }
    }
}
```


