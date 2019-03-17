package cn.edu.nju.software.storymapping.map.netty.server;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * netty随项目启动
 */
@WebListener
public class NettyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.err.println("nettyListener Startup!");
        new Thread() {
            @Override
            public void run() {
                int i = 0;
                new NettyServer().start();
            }
        }.start();
    }
}
