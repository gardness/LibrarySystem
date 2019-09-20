//package com.ascending.training.service;
//
//import com.amazonaws.services.s3.AmazonS3;
//import com.ascending.training.init.AppInitializer;
//import org.junit.Before;
//import org.junit.runner.RunWith;
//import org.mockito.*;
//import org.slf4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.net.MalformedURLException;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = AppInitializer.class)
//public class FileServiceMockAWSTest {
//    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
//    private AmazonS3 amazonS3;
//    @Spy
//    @Autowired
//    private Logger logger;
//    @InjectMocks
//    private FileService fileService;
//
//    private String buckName = "training ";
//
//    @Before
//    public void setUp() throws MalformedURLException, FileNotFoundException, IOException {
//        logger.info(">>>>>> Start testing ... ");
//
//        MockitoAnnotations.initMocks(this);
//
//        fakeFileUrl = new ("")
//
//
//    }
//
//}
