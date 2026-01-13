import com.itheima.config.SpringConfig;
import com.itheima.config.SpringMvcConfig;
import com.itheima.controller.UserController;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class App {
    public static void main(String[] args) {
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
//        System.out.println(ctx.getBean(UserController.class));
        AnnotationConfigApplicationContext ctx2 = new AnnotationConfigApplicationContext(SpringConfig.class);
        System.out.println(ctx2.getBean(UserController.class));
        //只有SpringMvcConfig配置类，才能创建SpringMVC容器，并且SpringMVC容器中才能创建表现层UserController 的bean
    }
}
