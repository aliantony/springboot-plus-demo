package com.example.queue;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@Data
public class DelayedUser implements Delayed {
    private String name;
    private long avaibleTime;

    public DelayedUser(String name, long delayTime){
        this.name=name;
        //avaibleTime = 当前时间+ delayTime
        this.avaibleTime=delayTime + System.currentTimeMillis();

    }

    /**
     * DelayQueue的底层存储是一个PriorityQueue，在之前的文章中我们讲过了，
     * PriorityQueue是一个可排序的Queue，其中的元素必须实现Comparable方法。
     * 而getDelay方法则用来判断排序后的元素是否可以从Queue中取出,只有delay时间小于0的元素才能够被取出
     * @param unit
     * @return
     */
    @Override
    public long getDelay(TimeUnit unit) {
        //判断avaibleTime是否大于当前系统时间，并将结果转换成MILLISECONDS
        long diffTime= avaibleTime- System.currentTimeMillis();
        return unit.convert(diffTime,TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        //compareTo用在DelayedUser的排序
        return (int)(this.avaibleTime - ((DelayedUser) o).getAvaibleTime());
    }
}