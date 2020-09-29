package com.tawe.crowd.test;

import com.tawe.crowd.util.CrowdUtil;
import org.junit.Test;

/**
 * @ClassName CorwdTest
 * @Description TODO
 * @Author Administrator
 * @Date 9/29/2020 3:38 PM
 * @Version 1.0
 **/
public class CorwdTest {

    @Test
    public void md5Test() {
        String encoded = CrowdUtil.md5("123123");
        System.out.println(encoded);
    }
}
