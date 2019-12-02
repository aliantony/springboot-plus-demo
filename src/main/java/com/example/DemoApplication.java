package com.example;

import com.example.spring.CustomizeApplicationContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.filters.RequestDumperFilter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

@SpringBootApplication
@EnableAsync
@EnableTransactionManagement()
/**解决prent调用child（REQUIRES_NEW）时由于动态代理导致内部事务失效
 *实现内部事务回滚，外部事务正常提交
 **/
@EnableAspectJAutoProxy(exposeProxy = true)
public class DemoApplication {

    public static void main(String[] args) {
        //SpringApplication.run(DemoApplication.class, args);
        SpringApplication springApplication = new SpringApplication(DemoApplication.class);
        springApplication.setApplicationContextClass(CustomizeApplicationContext.class);
        springApplication.run(args);
    }

    /**
     * 自定义线程池执行异步任务
     */
    @Configuration
    class TaskPoolConfig {
        @Bean("taskExecutor")
        public Executor taskExecutor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(10);
            executor.setMaxPoolSize(20);
            executor.setQueueCapacity(200);
            executor.setKeepAliveSeconds(60);
            executor.setThreadNamePrefix("taskExecutor-");
            executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
            //等待任务完成后销毁线程池
            executor.setWaitForTasksToCompleteOnShutdown(true);
            //等待关闭的时间
            executor.setAwaitTerminationSeconds(10);
            return executor;
        }
    }

    /**
     * 为测试环境添加相关的 Request Dumper information，便于调试
     * @return
     */
    @Profile("!cloud")
    @Bean
    RequestDumperFilter requestDumperFilter() {
        return new RequestDumperFilter();
    }

    @Bean
    public DataLoader dataLoader() {
        return new DataLoader();
    }

    @Slf4j
    static class DataLoader implements CommandLineRunner {

        @Override
        public void run(String... strings) throws Exception {
            log.info("loading order: prepared -> started -> command-line -> read");
            log.info("Loading data...");
        }
    }

}
