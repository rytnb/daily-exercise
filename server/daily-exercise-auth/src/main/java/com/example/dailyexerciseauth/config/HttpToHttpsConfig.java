package com.example.dailyexerciseauth.config;

// import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
// import org.apache.catalina.connector.Connector;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

/**
 * HTTP to HTTPS 重定向配置
 * 当前注释掉以避免端口冲突
 * 需要启用 HTTPS 时取消注释并配置证书
 */
// @Configuration
// public class HttpToHttpsConfig {
// 
//     @Bean
//     public TomcatServletWebServerFactory servletContainer() {
//         TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
//         tomcat.addAdditionalTomcatConnectors(httpConnector());
//         return tomcat;
//     }
// 
//     private Connector httpConnector() {
//         Connector connector = new Connector(TomcatServletWebServerFactory.DEFAULT_PROTOCOL);
//         connector.setScheme("http");
//         connector.setPort(8080);        // 监听原来的 HTTP 端口
//         connector.setSecure(false);
//         connector.setRedirectPort(8443); // 重定向到 HTTPS 端口
//         return connector;
//     }
// }
