package com.example.mp;

import com.example.mp.dao.UserMapper;
import com.example.mp.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class MpApplicationTests {

	@Test
	void contextLoads() {
	}


	@Resource

	private UserMapper userMapper;

	@Test
	public  void queryAll(){
		List <User> users = userMapper.selectList(null);
		System.out.println(users);

	}

}
