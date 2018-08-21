package com.gxy.bytebuddy.agent;/*
 * Created by GXY on 2018/8/20
 */

import com.gxy.bytebuddy.interceptor.mainInterceptor;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

public class MainAgent {


    private static String clazz = "com.ly";//要拦截的类（包）

    private static String method = "aiXin";//要拦截的方法名


    public MainAgent(String clazz, String method) {
        MainAgent.clazz = clazz;
        MainAgent.method = method;
    }

    /*agentArgs 是 premain 函数得到的程序参数，随同 “– javaagent”一起传入*/
    /*Inst 是一个 java.lang.instrument.Instrumentation 的实例，由 JVM 自动传入*/
    public static void premain(String agentArgs, Instrumentation inst) {
        System.out.println("监控正在启动");

        AgentBuilder.Transformer mainTransformer = new AgentBuilder.Transformer() {
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader) {
                return builder.method(ElementMatchers.nameStartsWith(method)).intercept(MethodDelegation.to(mainInterceptor.class));
            }
        };

        /*AgentBuilder.Listener mainlistener = new AgentBuilder.Listener() {
            long start = System.currentTimeMillis();
            long end = 0;
            List<Long> list = new ArrayList<>();
            int i = 0;

            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, DynamicType dynamicType) {
            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {

            }

            @Override
            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, Throwable throwable) {
                System.out.println(s + " occuerd exception：" + throwable.getMessage());
            }

            @Override
            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule) {
                if (i == 0) {
                    list.add(start);
                    end = System.currentTimeMillis();
                    long take = end - start;
                    list.add(end);
                    System.out.println(s + " token " + take);
                    i++;
                } else {
                    end = System.currentTimeMillis();
                    long take = end - list.get(list.size() - 1);
                    list.add(end);
                    System.out.println(s + " token " + take + "ms");
                }
            }

        };*/

        /*PrintStream ps = new PrintStream(System.out);
        AgentBuilder.Listener.StreamWriting streamWriting = new AgentBuilder.Listener.StreamWriting(ps);*/

        AgentBuilder.Listener mainlistener = new AgentBuilder.Listener() {


            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, DynamicType dynamicType) {
            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {

            }

            @Override
            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, Throwable throwable) {
            }

            @Override
            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule) {

            }

        };

        new AgentBuilder.Default().type(ElementMatchers.nameStartsWith(clazz)).transform(mainTransformer).with(mainlistener).installOn(inst);
    }

}
