package com.example.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class JSONFieldNamingSupportTest {
    private class SomeObject {
        @SerializedName("custom_naming")
        private final String someField;
        private final String someOtherField;

        public SomeObject(String a, String b) {
            this.someField = a;
            this.someOtherField = b;
        }
    }

   @Test
   public void test() {
        Gson gson = new GsonBuilder().create();
        SomeObject someObject = new SomeObject("first", "second");
        String jsonRepresentation = gson.toJson(someObject);
        System.out.println(jsonRepresentation);
          // {"custom_naming":"first","someOtherField":"second"}
        SomeObject someObject1 = gson.fromJson(jsonRepresentation, SomeObject.class);
        System.out.println(someObject1);
          // SomeObject{someField='first', someOtherField='second'}
    }
}
