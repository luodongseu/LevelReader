# LevelReader
An app for readers learning English words by levels

#App要求
这是一个简易的分级阅读程序， 基本功能是：
1. 打开后看到文章列表
2. 点击列表某项，打开文章
3. 文章界面有一个按钮，点击则在文章中高亮在单词列表中出现的单词
4. 文章界面有一个slide-bar，从0到6（对应单词表中每个单词信息的level)，可以拖动过滤高亮的单词。
当然你可以发挥自己的想象力来改进这些功能和交互。

#解释
譬如 nce4_words的内容如下：
单词              级别
compare         3
backward        2
technology      2
alien                1

#例如
文本内容是Compared with the alien, our technology is backward。
如果slide-bar为2， 那么只高亮级别在2及以下的词，包括backward, technology,
alien；如果slide-bar为3，那么高亮级别小于等于3的词。


# 说明
目前完成了文章列表界面、文章阅读界面、分级高亮单词、生词列表界面等功能。但部分功能尚有缺陷。未完待续。