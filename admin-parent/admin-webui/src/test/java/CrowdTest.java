import com.home.entity.Admin;
import com.home.mapper.AdminMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:mybatis/spring-persist-mybatis.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

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
