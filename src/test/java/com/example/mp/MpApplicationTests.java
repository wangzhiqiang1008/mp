package com.example.mp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.mp.dao.UserMapper;
import com.example.mp.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@SpringBootTest
class MpApplicationTests {

	@Test
	void contextLoads() {
	}


	@Resource

	private UserMapper userMapper;
//查询操作
	@Test
	public  void queryAll(){
		List <User> users = userMapper.selectList(null);
		System.out.println(users);

	}
	//添加操作
	@Test
	public  void  addUser(){

		User user = new User();
		user.setAge(20);
		user.setName("孙悟空");
		user.setEmail("sunwukong@qq.com");
//		user.setCreateTime(new Date());
//		user.setUpdateTime(new Date());
		int insert = userMapper.insert(user);
		System.out.println("----insert--"+insert);

	}

	@Test

	public  void  updateuser(){

		User user = new User();
		user.setId(2L);
		user.setAge(10000);
		int i = userMapper.updateById(user);
		System.out.println(i);

	}

	//测试乐观锁

	@Test

	public void optislock(){

		User user = userMapper.selectById(3L);

		user.setAge(1000);

		userMapper.updateById(user);

	}

	//测试多个id查询

	@Test

	public void querytest1(){

		List <User> users = userMapper.selectBatchIds(Arrays.asList(1, 2, 3));
		System.out.println("----多个ids的查查询---"+users);

	}

	//根据条件查询

	@Test
	public  void queryByMap(){

		HashMap <String, Object> objectObjectHashMap = new HashMap <>();
		objectObjectHashMap.put("name","jack");
		objectObjectHashMap.put("age",18);
		List <User> users = userMapper.selectByMap(objectObjectHashMap);
		System.out.println(users);

	}


	@Test
	public void queryByPage(){

		Page <User> objectPage = new Page <>(1,3);
		userMapper.selectPage(objectPage,null);

		System.out.println("-当前页---"+objectPage.getCurrent());
		System.out.println("--每页数据list--"+objectPage.getRecords());
		System.out.println("-每页记录数---"+objectPage.getSize());
		System.out.println("--总记录数--"+objectPage.getTotal());
		System.out.println("--总页数--"+objectPage.getPages());

		System.out.println("---是否有下一页---"+objectPage.hasNext());

		System.out.println("--是否有上一页--"+objectPage.hasPrevious());

	}

	@Test
	public  void  dele(){

		int i = userMapper.deleteById(1);
		System.out.println(i);

	}

	//查询复杂 sql
	@Test
	public void query(){
		QueryWrapper <User> objectQueryWrapper = new QueryWrapper <>();

		//大于
	//	objectQueryWrapper.ge("age",100);
		//等于 eq 不等于 ne
	//	objectQueryWrapper.eq("name","mike");
		// between  区间

		//objectQueryWrapper.between("age",0,10);

		//模糊匹配 like

	//	objectQueryWrapper.like("name","孙");
		//降序排列
		//objectQueryWrapper.orderByDesc("id");

		//拼接语句
		objectQueryWrapper.last("limit 1");
//查询指定的列
		objectQueryWrapper.select("id","name");

		List <User> users = userMapper.selectList(objectQueryWrapper);
		System.out.println(users);

	}
}
