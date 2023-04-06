# bjee

Boilerplate project for JavaEE with JSF.

# How to create connection pool,data source and jdbc realm by command line (asadmin)

``` 
asadmin start-domain

asadmin create-jdbc-connection-pool --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource --restype javax.sql.ConnectionPoolDataSource --property portNumber=3306:password=password:user=bjeeus:serverName=127.0.0.1:databaseName=bjee:encoding=utf8:connectionCollation=utf8mb4_unicode_ci:serverTimezone=Europe/Rome:characterEncoding=utf8 BjeeConnectionPool

asadmin create-jdbc-resource --connectionpoolid BjeeConnectionPool jdbc/bjee

asadmin create-auth-realm --classname com.sun.enterprise.security.auth.realm.jdbc.JDBCRealm --property jaas-context=jdbcRealm:datasource-jndi=jdbc/bjee:user-table=users_access:user-name-column=username:password-column=password:group-table=users_access:group-name-column=group_name:digestrealm-password-enc-algorithm=SHA-512 BjeeRealm
```
