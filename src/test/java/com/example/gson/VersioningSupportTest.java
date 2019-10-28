package com.example.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Since;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class VersioningSupportTest {
    @Test
    public void test() {
        VersionedClass versionedObject = new VersionedClass();
        Gson gson = new GsonBuilder().setVersion(1.0).create();
        String jsonOutput = gson.toJson(versionedObject);
        System.out.println(jsonOutput); // {"newField":"new","field":"old"}
    }
}

class VersionedClass {
    @Since(1.1)
    private final String newerField;
    @Since(1.0)
    private final String newField;
    private final String field;

    public VersionedClass() {
        this.newerField = "newer";
        this.newField = "new";
        this.field = "old";
    }
}
