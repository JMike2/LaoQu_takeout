package apex.legend.laoqu.common;

import java.util.TreeMap;

/*
* 基于TreadLocal封装工具类，用于保存当前登录用户id
* */
public class BaseContext {
    private  static ThreadLocal<Long> threadLocal = new ThreadLocal<>();
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }
    public static  Long getCurrentId(){
        return threadLocal.get();
    }
}
