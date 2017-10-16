# tianchi-aliyun-security-competition

第二届阿里云安全算法挑战赛 MJ_3DSUN 队解题方法

赛题介绍详见: [第二届阿里云安全算法挑战赛 | 赛题与数据](https://tianchi.aliyun.com/competition/information.htm?raceId=231612)

共两道题, 一是"扫描爆破拦截", 二是"网页风险识别".
* 第一题由队友 @liang2713020 主要负责. 思路在答辩幻灯里有介绍, 代码这里就不具体给出了.
* 第二题主要由我负责. 思路介绍/PAI模型/UDF代码见 [content_risk](content_risk/) 子文件夹. 
  * 说明: Java 代码写得不好, 欢迎指正; 另外比赛时一直以为平台上没有办法完整统计 2-gram, 因此采用了一种权宜的方法; 后来才知道可以自己写 map-reduce 来实现.
* 答辩幻灯见 [presentation.pptx](presentation.pptx)
