//package cn.unicom.met.config;//package cn.itcast.erp.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class MySpringMvcConfigurer {
//
//
//    @Bean
//    public WebMvcConfigurer webMvcConfigurer() {
//        return new WebMvcConfigurer(){
//            //添加视图控制
//            @Override
//            public void addViewControllers(ViewControllerRegistry registry) {
//                registry.addViewController("/dep.html").setViewName("/dep");
//                registry.addViewController("/index.html").setViewName("index");
//                registry.addViewController("/emp.html").setViewName("/emp");
//            }
//
//            @Override
//            public void addInterceptors(InterceptorRegistry registry) {
//                registry.addInterceptor(new LoginHandlerInterceptor())
//                        //指定要拦截的请求 /** 表示拦截所有请求
//                        .addPathPatterns("/**")
//                        //排除不需要拦截的请求路径
//                        .excludePathPatterns("/", "/index.html", "/login")
//                        //springboot2+之后需要将静态资源文件的访问路径 也排除
//                        .excludePathPatterns("/css/*", "/img/*","/js/*");
//            }
//        };
//    }
//
//}
