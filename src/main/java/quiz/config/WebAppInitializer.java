package quiz.config;

import org.h2.server.web.WebServlet;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

/**
 * Configure/register servlets.
 */
public class WebAppInitializer implements WebApplicationInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(AppConfig.class);

        ServletRegistration.Dynamic servlet = servletContext
                .addServlet("app", new DispatcherServlet(context));
        servlet.setLoadOnStartup(1);
        servlet.addMapping("/");

        ServletRegistration.Dynamic h2Console = servletContext
                .addServlet("h2-console", new WebServlet());
        h2Console.setInitParameter("-webAllowOthers", "");
        h2Console.setLoadOnStartup(1);
        h2Console.addMapping("/h2-console/*");

    }
}
