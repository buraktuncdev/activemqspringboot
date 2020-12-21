package com.buraktuncdev.activemqspringboot;

import static org.assertj.core.api.Assertions.assertThat;
import com.buraktuncdev.activemqspringboot.receiver.Receiver;
import com.buraktuncdev.activemqspringboot.sender.Sender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class ActivemqspringbootApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private Sender sender;

    @Autowired
    private Receiver receiver;

    @Test
    public void testReceive() throws Exception {
        sender.send("Hello Spring JMS ActiveMQ!");

        receiver.getLatch().await(10000, TimeUnit.MILLISECONDS);
        assertThat(receiver.getLatch().getCount()).isEqualTo(0);
    }

}
