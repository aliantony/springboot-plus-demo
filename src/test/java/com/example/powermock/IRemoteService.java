package com.example.powermock;

/**
 * @author wangqian
 * created on 2019-10-15
 * @version 1.0.0
 * @program demo1
 * @description
 */
public interface IRemoteService {
    public Node getRemoteNode(int num);

    public Node getRemoteNode(String name) throws MockException;

    public void doSometing();

    public Node getFinalNode();

    public Node getPrivateNode();

    public Node getSystemPropertyNode();
}
