package com.example.login_system;

import com.aliyuncs.exceptions.ClientException;
import com.example.login_system.utils.MessageUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class IdCodeTest {

    @Test
    public void send() throws ClientException {
        MessageUtil.sendSms("13773039933","123456");

    }
}
