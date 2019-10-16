package com.example.powermock;

/**
 * @author wangqian
 * created on 2019-10-15
 * @version 1.0.0
 * @program demo1
 * @description
 */
public interface ILocalService {

     Node getRemoteNode(int num);

     Node getLocalNode(int num, String name);
}
