package com.n3n4.im.imcore;

import com.n3n4.im.imcore.config.NettyProperties;
import com.n3n4.im.imcore.config.SslProperties;
import com.n3n4.im.imcore.config.TcpServerStarter;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.DefaultLifecycleProcessor;

@SpringBootApplication
@ImportAutoConfiguration({NettyProperties.class, SslProperties.class})
public class ImCoreApplication implements ApplicationRunner {

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(ImCoreApplication.class, args);

        ctx.getBean(TcpServerStarter.class).start();
	}

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("UniqueLove");
    }




    //************************************************************************
    //为了实现在程序销毁（被kill 进程号命令杀死）后，能执行某些销毁前的清除动作
    //************************************************************************

    /**
     * 重写timeout时间
     * 配合tcpServerStarter的销毁前清理数据
     * 防止销毁动作执行时间长导致没执行完就timeout了。
     * @return
     */
    @Bean(name = "lifecycleProcessor")
    public DefaultLifecycleProcessor defaultLifecycleProcessor() {
        DefaultLifecycleProcessor defaultLifecycleProcessor = new DefaultLifecycleProcessor();
        defaultLifecycleProcessor.setTimeoutPerShutdownPhase(10 * 60 * 1000);
        return defaultLifecycleProcessor;
    }
}
