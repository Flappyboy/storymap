package cn.edu.nju.software.storymapping.map.AOP;

import cn.edu.nju.software.storymapping.map.netty.config.NettyConfig;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class LockAOP {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Pointcut("execution(* cn.edu.nju.software.storymapping.map.controller..*.*(..))")
    public void pointCut() {

    }

    public void getLock() {
        for (; ; ) {
            //获取当前storymapId的值
            String id = redisTemplate.opsForValue().get("storyMapId");
            //如果值为1，说明已经被占用，自旋
            if (id == "1") {
                continue;
            }
            //增加一个监控
            redisTemplate.watch("storyMapId");
            //开启一个事务
            redisTemplate.multi();
            //尝试去抢占该锁
            redisTemplate.opsForValue().set("storyMapId", "1");
            List<Object> result = redisTemplate.exec();
            //说明修改成功，跳出循环
            if (result != null) {
                break;
            }
        }
    }

    public void releaseLock() {
        //将redis中的值设置成0,并不需要开线程
        redisTemplate.opsForValue().set("storyMapId", "0");
        //将命令发送个其他
    }

    /**
     * 遇到异常的情况为删除在前，修改在后
     * 对于这样的情况，就不需要将修改的command转发给其他人了。
     * 直接释放锁
     */
    public void handException() {
        releaseLock();
    }

    /**
     * 处理完后，需要在释放锁之前将command发送给各个客户端
     */
    public void afterProcess(String command) {
        NettyConfig.group.writeAndFlush(command);
        releaseLock();
    }

    @Around("pointCut()")
    public void around(ProceedingJoinPoint joinPoint) {
        //在之前需要先去获取锁
        getLock();
        try {
            //调用方法执行
            joinPoint.proceed();
            //如果没有异常，则释放锁
        } catch (Throwable throwable) {
            //如果有异常，则调用处理异常的方法
            handException();
        } finally {

        }
    }

}
