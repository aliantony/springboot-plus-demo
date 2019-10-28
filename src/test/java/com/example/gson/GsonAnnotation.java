package com.example.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

/**
 * @program demo1
 * @description 注解测试
 * @author wangqian
 * created on 2019-10-28
 * @version  1.0.0
 */
public class GsonAnnotation {

    @Test
    public void test_expose() {
        MySubClass subclass = new MySubClass(42L, "the answer", "Verbose field not to serialize");
        MyClass source = new MyClass(1L, "foo", "bar", subclass);
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        String s = gson.toJson(source);
        System.out.println(s);
        // {"name":"foo","subclass":{"id":42,"description":"the answer","otherVerboseInfo":"Verbose field not to serialize"}}
    }

    @Data
    @AllArgsConstructor
    class MyClass {
        private long id;
        //不序列化，要反序列化
        @Expose(serialize = false, deserialize = true)
        private String name;
        //不序列化
        private transient String other;
        @Expose
        private MySubClass subclass;
    }

    @Data
    @AllArgsConstructor
    class MySubClass {
        @Expose
        private long id;
        @Expose
        private String description;
        @Expose
        private String otherVerboseInfo;
    }
}
