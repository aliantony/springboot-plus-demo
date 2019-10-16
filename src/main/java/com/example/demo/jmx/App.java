package com.example.demo.jmx;

public class App implements AppMXBean {

    private WatchDog watchDog;
    @Override
    public void welcome() {
        System.out.println("JMX 初体验，So easy！");
        if (watchDog != null) {
            System.out.println("看门狗信息" + watchDog);
        }
    }

    @Override
    public WatchDog getWatchDog() {
        return watchDog;
    }

    @Override
    public void addWatchDog(WatchDog watchDog) {
        this.watchDog = watchDog;
    }
}

