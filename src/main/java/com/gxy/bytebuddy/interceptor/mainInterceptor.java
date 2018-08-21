package com.gxy.bytebuddy.interceptor;/*
 * Created by GXY on 2018/8/20
 */

import net.bytebuddy.implementation.bind.annotation.AllArguments;
import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;

import java.lang.reflect.Method;
import java.util.concurrent.Callable;

public class mainInterceptor {

    /**
     * @param callable 原线程的callble接口对象，执行call()方法,返回原函数运行结束的返回对象
     * @param method   reflect的方法,可以获取原方法各类信息
     * @param args     method传入的参数
     * @Autuor:xy.Gao
     * @Description:
     * @Date: 2018/8/21 15:44
     */

    @RuntimeType
    public static Object interceptor(@SuperCall Callable callable, @Origin Method method, @AllArguments Object[] args) throws Exception {
        long start = System.currentTimeMillis();//调用前
        try {
            return callable.call();
        } finally {
            System.out.println("拦截" + method.getName() + " >>>>>>>> SUCCESS，token" + (System.currentTimeMillis() - start) + "ms");//调用后
        }
    }
}
