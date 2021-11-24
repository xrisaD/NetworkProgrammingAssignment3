package quiz;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.File;

public class Application {
    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);
        tomcat.getConnector();
        tomcat.setBaseDir(System.getProperty("java.io.tmpdir"));
        tomcat.addWebapp("", new File("src/main/resources").getAbsolutePath());
        tomcat.start();
        tomcat.getServer().await();
    }
}
