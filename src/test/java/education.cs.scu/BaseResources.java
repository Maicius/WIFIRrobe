package education.cs.scu;

/**
 * Created by maicius on 2017/7/25.
 */

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "classpath*: /applicationContext-service.xml",
"classpath*: /applicationContext-dao.xml", "classpath*: /applicationContext-transaction.xml",
        "classpath*: /springMVC.xml"})
public class BaseResources {
}
