import com.home.util.CrowdUtil;
import org.junit.Test;

/**
 * 文件描述
 *
 * @author 冯根源
 * @date 2021/1/28 0:19
 */
public class StringTest {
    @Test
    public void testMd5(){
        String source = "123123";
        System.out.println(CrowdUtil.md5(source));
    }
}
