package com.ascending.training.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.ascending.training.init.AppInitializer;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class MessageServiceTest {
    @Autowired
    private Logger logger;
    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private AmazonSQS amazonSQS;
    @InjectMocks
    private MessageService messageService;

    private String queueName = "training_queue_ascending_com";
    private String fakeQueueUrl = "www.fakeQueueUrl.com/abc/123/fake";

    @Before
    public void before() {
        logger.debug(">>>>>>>>> Start testing ...");
    }

    @After
    public void tearDown() {
        logger.debug(">>>>>>>>> End test");
    }

    @Test
    public void getQueueUrl() {
        when(amazonSQS.getQueueUrl(anyString()).getQueueUrl()).thenReturn(fakeQueueUrl);
        String queueUrl = messageService.getQueueUrl(queueName);

        Assert.assertEquals(queueUrl, fakeQueueUrl);
        verify(amazonSQS, times(2)).getQueueUrl(anyString());
    }
}
