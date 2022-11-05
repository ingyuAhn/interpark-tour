package com.assignment.interpark.config.database;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.ScriptResolver;
import com.wix.mysql.SqlScriptSource;
import com.wix.mysql.config.Charset;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.distribution.Version;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.ZoneId;
import java.util.Arrays;
import java.util.TimeZone;

@Configuration
@Profile("prod")
public class MysqlDataSource {

    private static String ID = "prod";
    private static String KEY = "1234";

    static {
        MysqldConfig mysqldConfig = MysqldConfig.aMysqldConfig(Version.v5_7_18)
                .withCharset(Charset.UTF8)
                .withPort(13306)
                .withUser(ID, KEY)
                .withTimeZone(TimeZone.getTimeZone(ZoneId.systemDefault()))
                .build();

        SqlScriptSource citySql = ScriptResolver.classPathScript("db/city.sql");
        SqlScriptSource tourSql = ScriptResolver.classPathScript("db/tour.sql");
        SqlScriptSource foreignKey = ScriptResolver.classPathScript("db/foreignKey.sql");

        EmbeddedMysql.anEmbeddedMysql(mysqldConfig)
                .addSchema("interpark", Arrays.asList(citySql, tourSql, foreignKey))
                .start();
    }
}
