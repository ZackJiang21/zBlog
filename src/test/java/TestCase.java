import com.zack.zblog.dao.CategoryDao;
import com.zack.zblog.dao.UserDao;
import com.zack.zblog.model.Category;
import com.zack.zblog.model.User;
import com.zack.zblog.util.TokenUtil;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;

/**
 * Created by ZackJiang on 2018/5/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.zack.zblog.Zblog.class)
public class TestCase {
    @Autowired
    private UserDao userDao;

    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void test() {
        User user = userDao.getUser("admin", "123");
        Assert.assertNotNull(user);
    }

    @Test
    public void testAddCategory() {
        Category category = new Category();
        category.setName("aaa");
        categoryDao.addCategory(category);
    }

    @Test
    public void testToken() {
        try {
            String token = TokenUtil.createJwt();
            System.out.println(token);
            TokenUtil.verifyJwt(token);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}
