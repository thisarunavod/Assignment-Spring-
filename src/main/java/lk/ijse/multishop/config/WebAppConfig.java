package lk.ijse.multishop.config;

import jakarta.servlet.annotation.MultipartConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages = "lk.ijse.multishop")
@EnableWebMvc
@EnableTransactionManagement
@MultipartConfig(        /* <-----   meken limitations dila tynna pluwn  */
        fileSizeThreshold = 1024 * 1024 * 2 ,   //2MB   --- primary and secondry memory manage karanawaa 2mb wlta adu unoth tmp eke save wenawa
        maxFileSize = 1024 * 1024 * 10,  //10MB
        maxRequestSize = 1024 * 1024 * 50  // 50MB
)
public class WebAppConfig {}
