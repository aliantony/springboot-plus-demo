package com.example.demo.jmx;

public interface AppMXBean {
    public void welcome();
    public WatchDog getWatchDog();
    public void addWatchDog(WatchDog dog);
}