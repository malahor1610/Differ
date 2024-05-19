package com.github.malahor.differ;

import com.github.malahor.differ.lcs.LcsMatrix;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = "spring.shell.interactive.enabled=false")
public class LcsMatrixTest {

    @Test
    void test1(){
    var result = new LcsMatrix("ABCDEF", "ABCDEF").lcs();
        Assertions.assertEquals("ABCDEF", result);
    }
    @Test
    void test2(){
        var result = new LcsMatrix("ABC", "XYZ").lcs();
        Assertions.assertEquals("", result);
    }
    @Test
    void test3(){
        var result = new LcsMatrix("AABCXY", "XYZ").lcs();
        Assertions.assertEquals("XY", result);
    }
    @Test
    void test4(){
        var result = new LcsMatrix("", "").lcs();
        Assertions.assertEquals("", result);
    }

    @Test
    void test5(){
        var result = new LcsMatrix("ABCD", "AC").lcs();
        Assertions.assertEquals("AC", result);
    }

    @Test
    void test6(){
        var result = new LcsMatrix("this is text to be changed", "this is changed text").lcs();
        Assertions.assertEquals("this is changed", result);
    }

}
