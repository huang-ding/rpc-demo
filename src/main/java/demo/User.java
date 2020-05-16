package demo;

import java.io.Serializable;

/**
 * @author huangding
 * @date 2020/5/16 14:40
 */
public class User implements Serializable {

    private static final long serialVersionUID = -1221268239932915488L;

    private String name;

    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
