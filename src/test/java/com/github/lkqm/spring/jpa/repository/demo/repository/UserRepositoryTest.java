package com.github.lkqm.spring.jpa.repository.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.github.lkqm.spring.jpa.repository.demo.domain.User;
import java.util.Date;
import javax.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void selectByAccount() {
        User user = new User();
        user.setAccount("luokaiqiongmou@foxmail.com");
        user.setPassword("998");
        user.setName("罗开");
        user.setCreateTime(new Date());
        userRepository.save(user);

        User result = userRepository.selectByAccount(user.getAccount());
        assertNotNull(result);
    }

    @Test
    public void insert() {
        User user = new User();
        user.setId(1);
        user.setAccount("luokaiqiongmou@foxmail.com");
        user.setPassword("998");
        user.setName("罗开");
        user.setCreateTime(new Date());
        int row = userRepository.insert(user);
        assertEquals(1, row);
        assertNotNull(user.getId());
    }
}
