package com.lxf.test;
import com.lxf.dao.IUserDao;
import com.lxf.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class mybatisTest {
    private InputStream is;
    private SqlSession session;
    private IUserDao userDao;
    @Before
    public void init() throws Exception {
        //读取配置文件
        is = Resources.getResourceAsStream("SqlMapConfig.xml");
        //2.创建SqlSessionFactory工厂
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        SqlSessionFactory factory = builder.build(is);
        //3.使用工厂生产SqlSession对象
        session = factory.openSession();
        //4.使用SqlSession创建Dao接口的代理对象
        userDao = session.getMapper(IUserDao.class);
    }
    @After
    public void destroy() throws Exception {
        //提交事务
        session.commit();
        //6.释放资源
        session.close();
        is.close();

    }

    @Test
    public void findAll() throws IOException {

        //5.使用代理对象执行方法
        List<User> users = userDao.findAll();
        for (User user :users ){
            System.out.println(user);
        }

    }
    @Test
    public void testsava(){
        User user =new User();
        user.setUsername("水水水");
        user.setAddress("广东深圳");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.savaUser(user);
    }
    //更新操作
    @Test
    public void testUpdate(){
        User user =new User();
        user.setId(48);
        user.setUsername("李四");
        user.setAddress("广东深圳");
        user.setSex("男");
        user.setBirthday(new Date());

        userDao.updataUser(user);
    }
    //删除user
    @Test
    public void testDel(){
        userDao.delUser(46);
    }
}
