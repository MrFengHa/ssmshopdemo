import com.home.entity.Admin;
import com.home.mapper.AdminMapper;
import com.home.service.AdminService;
import lombok.Data;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2021/1/17 19:54
 */
//在类上标记必要的注解 spring 整合junit
@Data
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Test
    public void TestTx(){
        Admin admin = new Admin(null, "jerry", "123456", "杰瑞", "110@qq.com", null);
        adminService.saveAdmin(admin);
    }
    @Test
    public void testLog(){
        //1.想获取Logger对象
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);
        //2.根据不同的日志级别打印日志
        logger.debug("debug！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        logger.debug("debug！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");
        logger.debug("debug！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！");

        logger.info("info!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        logger.info("info!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        logger.info("info!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        logger.warn("worn!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        logger.warn("worn!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        logger.warn("worn!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        logger.error("error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        logger.error("error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        logger.error("error!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
    }

    @Test
    public void testInsertAdmin(){
     Admin admin=   new Admin(null,"tom","123123","汤姆","tom@qq.com",null);
      int count =   adminMapper.insert(admin);
        System.out.println("受影响的行数=       "+count);
    }
    @Test
    public void testConnection() throws SQLException {
        System.out.println(dataSource.getConnection());
    }
}
