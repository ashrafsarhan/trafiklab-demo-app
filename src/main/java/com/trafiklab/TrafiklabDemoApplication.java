package com.trafiklab;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RequiredArgsConstructor
@SpringBootApplication
@Slf4j
public class TrafiklabDemoApplication {

    public static void main(String[] args) throws UnknownHostException {
        SpringApplication app = new SpringApplication(TrafiklabDemoApplication.class);
        app.setBannerMode(Banner.Mode.OFF);
        ApplicationContext ctx = app.run(args);
        Environment env = ctx.getEnvironment();
        log.info("{} URL:\n----------------------------------------------------------\n\t" +
                        "\thttp://{}:{}{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                InetAddress.getLocalHost().getHostAddress(),
                env.getProperty("server.port"),
                env.getProperty("springdoc.swagger-ui.path"));

    }

}
