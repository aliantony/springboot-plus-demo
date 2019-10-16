package com.example.powermock;

import org.springframework.beans.factory.annotation.Autowired;

//本地负责实现具体业务的业务类
public class LocalServiceImpl implements ILocalService {

    //外部依赖
    @Autowired
    private IRemoteService remoteService;

    //具体业务处理方法
    @Override
    public Node getRemoteNode(int num) {
        return remoteService.getRemoteNode(num);
    }
    //以下忽略其他业务调用方法，在后面例子中补充

    @Override
    public Node getLocalNode(int num, String name) {
        return new Node(num, name);
    }
}