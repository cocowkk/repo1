import com.itheima.mybatis.dao.UserDao;
import com.itheima.mybatis.dao.UserDaoImpl;
import com.itheima.mybatis.pojo.User;
import org.junit.Test;

import java.util.List;

public class MainTest {

    @Test
    public void testJdbc() throws Exception {
        UserDao userDao = new UserDaoImpl();
        List<User> userList = userDao.queryUserList();
        if(userList != null && userList.size() > 0) {
            for (int i = 0; i < userList.size(); i++) {
                User user =  userList.get(i);
                System.out.println(user);
            }
        }
    }
}
