package com.alen.zuul;

import com.ctrip.framework.apollo.Config;
import com.ctrip.framework.apollo.ConfigChangeListener;
import com.ctrip.framework.apollo.model.ConfigChangeEvent;
import com.ctrip.framework.apollo.spring.annotation.ApolloConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 阿波罗项目启动监听
 * @author alen
 * @create 2019-09-16 20:33
 **/
@Component
@Slf4j
public class MyCommandLineRunner implements CommandLineRunner {
    @ApolloConfig
    private Config config;

    @Override
    public void run(String... args) throws Exception {
        config.addChangeListener(new ConfigChangeListener() {

            @Override
            public void onChange(ConfigChangeEvent changeEvent) {
                log.debug("####分布式配置中心监听#####" + changeEvent.changedKeys().toString());
            }
        });
    }

}
