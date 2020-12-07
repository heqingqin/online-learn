package geteway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author hdoubleq
 * @Date 2020/12/4-9:58
 */
@SpringBootApplication
@EnableDiscoveryClient
public class GetewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GetewayApplication.class,args);
    }
}

