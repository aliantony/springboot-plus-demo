package com.example.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.lang.reflect.Type;

import static org.junit.Assert.assertEquals;

/**
 * @program demo1
 * @description Gson高级用法，泛型等的支持
 * @author wangqian
 * created on 2019-10-28
 * @version  1.0.0
 */
@RunWith(MockitoJUnitRunner.class)
public class GsonAdvanceTest {

    

    class Result<T> {
        private int code;
        private String message;
        private T data;

        public Result(int code, String message, T data) {
            this.code = code;
            this.message = message;
            this.data = data;
        }
    }

    @Test
    public void test_genric_object() {
        String json = "{\"code\":200,\"message\":\"操作成功\",\"data\":{\"username\": \"one\",\"avater\": \"image.jpg\"" +
                "}}";
        Type type = new TypeToken<Result<User>>(){}.getType();
        Result<User> result = new Gson().fromJson(json, type);
        assertEquals(200, result.code);
        assertEquals("one", result.data.getUsername());
        assertEquals("image.jpg", result.data.getAvater());
    }

    class User {
        private String username;
        private String avater;

        public String getUsername() {
            return username;
        }

        public String getAvater() {
            return avater;
        }
    }
}


