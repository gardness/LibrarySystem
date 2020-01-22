package com.ascending.training.service;

import com.ascending.training.init.AppInitializer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppInitializer.class)
public class FileServiceTest {
    private String path;

    @Autowired
    private Logger logger;
    @Autowired
    private FileService fileService;

    @Before
    public void before() {}

    @After
    public void after() {}

    @Test
    public void delete() {
        fileService.deleteAnUnversionedBucket("gardnessbucket");
    }

}
