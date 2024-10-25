package com.wearei.finalsamplecode.domain.user;

import com.wearei.finalsamplecode.domain.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

}
