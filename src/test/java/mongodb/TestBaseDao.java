package mongodb;

import java.util.ArrayList;  
import java.util.Date;  
import java.util.List;  
  


import javax.annotation.Resource;  
  


import org.junit.Test;  
import org.springframework.data.mongodb.core.query.Criteria;  
import org.springframework.data.mongodb.core.query.Query;  

import com.xdy.monogodb.dao.MongoDBBaseDao;
import com.xdy.monogodb.dao.User;
  
  
  
public class TestBaseDao extends BaseTest {  
  
    @Resource(name = "mongoDBBaseDao")  
    MongoDBBaseDao mongoDBBaseDao;  
  
    /** 
     * 插入单条数据，id自定义 
     */  
    public void testAdd() {  
        User user = new User();  
        Date creatTime = new Date();  
        user.setCreateTime(creatTime);  
        user.setAge(10);  
        user.setUsername("福東江a");  
        this.mongoDBBaseDao.add(user);  
    }  
  
    /** 
     * 插入100万条数据，id自定义 
     */  
    @Test  
    public void testAddCollection() {  
        List<User> userList = new ArrayList<User>();  
        for (int j = 0; j < 10; j++) {  
            for (int i = 0; i < 100000; i++) {  
                User user = new User();  
                Date creatTime = new Date();  
                user.setCreateTime(creatTime);  
                user.setAge(10);  
                user.setUsername("冊南");  
                userList.add(user);  
            }  
        }  
        this.mongoDBBaseDao.add(userList);  
    }  
  
    @Test  
    public void testFindAll() {  
        List<User> userList = this.mongoDBBaseDao.findAll(User.class);  
        for (User user : userList) {  
            System.out.println("id:" + user.getId() + " username:" + user.getUsername() + "   age:" + user.getAge());  
        }  
        System.out.println("获取全部的数据----------------------");  
    }  
  
    @Test  
    public void testFindById() {  
        User user = this.mongoDBBaseDao.findById(User.class, "1234567");  
        System.out.println(user.getUsername());  
        System.out.println("获取单个对象----------------------");  
    }  
  
    @Test  
    public void testUpdate() {  
        User user = this.mongoDBBaseDao.findById(User.class, "1234567");  
        user.setUsername("反倒是淮");  
        this.mongoDBBaseDao.saveOrUpdate(user);  
        User newUser = this.mongoDBBaseDao.findById(User.class, "1234567");  
        System.out.println(newUser.getUsername());  
        System.out.println("修改数据成功");  
        this.mongoDBBaseDao.saveOrUpdate(user);  
    }  
  
    @Test  
    public void testRemove() {  
        User user = this.mongoDBBaseDao.findById(User.class, "1234567");  
        this.mongoDBBaseDao.remove(user);  
        User oldUser = this.mongoDBBaseDao.findById(User.class, "1234567");  
        if (oldUser == null) {  
            System.out.println(oldUser == null);  
            System.out.println("删除对象成功");  
        }  
        this.mongoDBBaseDao.add(user);  
    }  
  
    @Test  
    public void testCount() {  
        Query query = new Query();  
        Criteria criteria = new Criteria();  
        criteria.and("username").is("福東");  
        query.addCriteria(criteria);  
        long total = this.mongoDBBaseDao.count(User.class, query);  
        System.out.println("用户总数:" + total);  
    }  
      
      
}  