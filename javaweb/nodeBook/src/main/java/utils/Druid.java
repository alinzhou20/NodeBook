package utils;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.FileInputStream;
import java.util.Properties;

public class Druid {

    private static DataSource druid;

    static {
        //静态代码块会随着类的加载而自动执行，且只执行一次

        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/main/resources/druid.properties"));
            druid = DruidDataSourceFactory.createDataSource(prop);
        }catch (Exception e) {
            System.out.println("druid_err");
        }

    }
    public static DataSource getDruid() {
        return druid;
    }

}
