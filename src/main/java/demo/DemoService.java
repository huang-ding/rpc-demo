package demo;

/**
 * @author huangding
 * @date 2020/5/16 14:37
 */
public interface DemoService {

    /**
     *
     */
    String sayHello(String name);

    /**
     *
     * @param id
     * @return
     */
    User getUserInfo(Integer id);
}
