package demo.impl;

import demo.DemoService;
import demo.User;

/**
 * @author huangding
 * @date 2020/5/16 16:19
 */
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name) {
        System.out.println("调用了sayHello");
        return "你好：" + name;
    }

    @Override
    public User getUserInfo(Integer id) {
        System.out.println("调用了getUserInfo");
        User user = new User();
        user.setName("哈哈哈");
        user.setAddress("嘻嘻嘻嘻");
        return user;
    }
}
