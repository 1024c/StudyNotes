# SpringMVC 搭建 SpringSecurity 环境

## 加入 SpringSecurity 环境

### 在 pom.xml 中加入 SpringSecurity 依赖

```xml
<!-- SpringSecurity 对 Web 应用进行权限管理 -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-web</artifactId>
    <version>4.2.10.RELEASE</version>
</dependency>
<!-- SpringSecurity 配置 -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-config</artifactId>
    <version>4.2.10.RELEASE</version>
</dependency>
<!-- SpringSecurity 标签库 -->
<dependency>
    <groupId>org.springframework.security</groupId>
    <artifactId>spring-security-taglibs</artifactId>
    <version>4.2.10.RELEASE</version>
</dependency>
```

### 在 web.xml 中配置 DelegatingFilterProxy

```xml
<filter>
    <filter-name>springSecurityFilterChain</filter-name>
    <filter-class>org.springframework.web.filter.DelegatingFilterProxy</filter-class>
</filter>
<filter-mapping>
    <filter-name>springSecurityFilterChain</filter-name>
    <url-pattern>/*</url-pattern>
</filter-mapping>
```

> 注意:
> SpringSecurity 会根据 DelegatingFilterProxy 的 filter-name 到 IOC 容器中查找所需要的 bean, 所以 filter-name 必须为 springSecurityFilterChain .

### 创建基于注解的配置类

```java
// 注意！这个类一定要放在自动扫描的包下，否则所有配置都不会生效！
// 将当前类标记为配置类
@Configuration
// 启用 Web 环境下权限控制功能
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
    // 与 SpringSecurity 环境下用户登录相关
    }
    @Override
    protected void configure(HttpSecurity security) throws Exception {
    // 与 SpringSecurity 环境下请求授权相关
    }
}
```

## 登录页和静态资源无需认证

```java
security
    .authorizeRequests()                            // 对请求进行授权
    .antMatchers("/index.jsp", "/layui/**")         // 针对/index.jsp 以及 /layui/ 路径下资源进行授权
    .permitAll()                                    // 可以无条件访问
    .anyRequest()                                   // 任意请求
    .authenticated()                                // 需要登录以后才可以访问
```

> 注意
> 小范围的授权需要放在前面, 大范围的放在后面, 否则, 小范围授权会被覆盖.

## 未认证请求调转到登录页

```java
security
    ...
    .and()
    .formLogin()                            // 使用表单形式登录
    // 关于 loginPage()方法的特殊说明
    // 指定登录页的同时会影响到：“提交登录表单的地址”、“退出登录地址”、“登录失败地址”
    // /index.jsp GET - the login form 去登录页面
    // /index.jsp POST - process the credentials and if valid authenticate the user 提交登录表单
    // /index.jsp?error GET - redirect here for failed authentication attempts 登录失败
    // /index.jsp?logout GET - redirect here after successfully logging out 退出登录
    .loginPage("/index.jsp")                // 指定登录页面（如果没有指定会访问 SpringSecurity 自带的登录页）
    // loginProcessingUrl()方法指定了登录地址，就会覆盖 loginPage()方法中设置的默认值/index.jsp POST
    .loginProcessingUrl("/do/login.html")   // 指定提交登录表单的地址
    .usernameParameter("loginAcct")         // 定制登录账号的请求参数名
    .passwordParameter("userPswd")          // 定制登录密码的请求参数名
    .defaultSuccessUrl("/main.html")        // 登录成功后前往的地址
```

## 完整的登录流程

### 设置表单

```html
<p>${SPRING_SECURITY_LAST_EXCEPTION.message}</p>
<form action="${pageContext.request.contextPath }/do/login.html" method="post">
<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
……
</form>
```

> 注意:
> 表单中需要添加 csrf 防止跨站请求.

### 设置正确的账号及密码

```java
builder
    .inMemoryAuthentication()           // 在内存中完成账号、密码的检查
    .withUser("tom")                    // 指定账号
    .password("123123")                 // 指定密码
    .roles("ADMIN","学徒")              // 指定当前用户的角色
    .and()
    .withUser("jerry")                  // 指定账号
    .password("123123")                 // 指定密码
    .authorities("UPDATE","内门弟子")    // 指定当前用户的权限
;

```

### 退出登录

```java
security
    ...
    .logout() // 开启退出功能
    .logoutUrl("/do/logout.html") // 指定处理退出请求的 URL 地址
    .logoutSuccessUrl("/index.jsp") // 退出成功后前往的地址
```

## 基于角色或权限的访问控制

```java
security
    ...
    .antMatchers("/level1/**")      // 针对/level1/**路径设置访问要求
    .hasRole("学徒")                // 要求用户具备“学徒”角色才可以访问
    .antMatchers("/level2/**")      // 针对/level2/**路径设置访问要求
    .hasAuthority("内门弟子")        // 要求用户具备“内门弟子”权限才可以访问
```

> 注意:
> SpringSecurity 会在角色前添加 "ROLE_" 前缀, 用以区分是角色还是权限.

## 指定 403 页面

### 方式一: 直接返回自定义 403 页面

```java
security
    ...
    .exceptionHandling()                            // 指定异常处理器
    .accessDeniedPage("/to/no/auth/page.html")      // 访问被拒绝时前往的页面
```

### 方式二: 携带需要的信息或者相关处理后返回自定义 403 页面

```java
security
    ...
    .exceptionHandling()                            // 指定异常处理器
    // 通过内部匿名类实现 AccessDeniedHandler 接口
    .accessDeniedHandler( new AccessDeniedHandler() {
        @Override
        public void handle( HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException ) 
            throws IOException, ServletException {
            request.setAttribute("message", "抱歉！您无法访问这个资源！☆☆☆");
            request.getRequestDispatcher("/WEB-INF/views/no_auth.jsp").forward(request, response);
        }
    })
```

## 记住我 功能

### 内存版:

```html
<input type="checkbox" name="remember-me" lay-skin="primary" title="记住我"/>
```

```java
security
    ...
    .rememberMe() // 开启记住我功能
```

-------------- 以上内容为在内存中完成的认证功能 -------------

----------------------------------------------------------

-------------- 以下内容为在数据库中完成认证功能 -------------

### 数据库版:

1. 加入数据库依赖:

   ```xml
   <!-- https://mvnrepository.com/artifact/com.alibaba/druid -->
    <dependency>
        <groupId>com.alibaba</groupId>
        <artifactId>druid</artifactId>
        <version>1.1.12</version>
    </dependency>
    <!-- mysql 驱动 -->
    <!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java -->
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.47</version>
    </dependency>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-orm</artifactId>
        <version>4.3.20.RELEASE</version>
    </dependency>
   ```

2. 创建数据库

    ```sql
    CREATE DATABASE `security` CHARACTER SET utf8;
    ```

3. 配置数据源

    ```xml
    <!-- 配置数据源 -->
    <bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
        <property name="username" value="root"></property>
        <property name="password" value="root"></property>
        <property name="url" value="jdbc:mysql://localhost:3306/security?useSSL=false"></property>
        <property name="driverClassName" value="com.mysql.jdbc.Driver"></property>
    </bean>
    <!-- jdbcTemplate-->
    <bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
        <property name="dataSource" ref="dataSource"></property>
    </bean>
    ```

4. 在配置类中装配数据源

    ```java
    @Autowired
    private DataSource dataSource;
    ```

5. 创建数据库表用于记录登录信息

    ```sql
    create table `persistent_logins` (
        username varchar(64) not null,
        series varchar(64) primary key,
        token varchar(64) not null,
        last_used timestamp not null
    );
    ```

6. SpringSecurity 中开启记住我功能

    ```java
    // 准备 JdbcTokenRepositoryImpl 对象
    JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
    tokenRepository.setDataSource(dataSource);

    security
        ...
        .tokenRepository(tokenRepository)
    ```

## 使用数据库的方式完成认证

1. 实现 UserDetailsService 接口

    ```java
    @Component
    public class MyUserDetailsService implements UserDetailsService {
        @Autowired
        private JdbcTemplate jdbcTemplate;
        // 总目标：根据表单提交的用户名查询 User 对象，并装配角色、权限等信息

        @Override
        public UserDetails loadUserByUsername (
            // 表单提交的用户名
            String username
        ) throws UsernameNotFoundException {
            // 1.从数据库查询 Admin 对象 - 就是为了拿到 password
            String sql = "SELECT id,loginacct,userpswd,username,email FROM t_admin WHERE loginacct=?";
            List<Admin> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Admin.class), username);
            Admin admin = list.get(0);
            // 2.给 Admin 设置角色权限信息
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            authorities.add(new SimpleGrantedAuthority("UPDATE"));
            // 3.把 admin 对象和 authorities 封装到 UserDetails 中
            String userpswd = admin.getUserpswd();
            return new User(username, userpswd, authorities);
        }
    }
    ```

2. 把 UserDetailsService 装配到配置类中

    ```java
    @Autowired
    private MyUserDetailsService userDetailsService;
    ```

3. 使用 UserDetailsService 对象

    ```java
    /******************** 取消内存版认证 *********************
    // builder
    // .inMemoryAuthentication() // 在内存中完成账号、密码的检查
    // .withUser("tom") // 指定账号
    // .password("123123") // 指定密码
    // .roles("ADMIN","学徒") // 指定当前用户的角色
    // .and()
    // .withUser("jerry") // 指定账号
    // .password("123123") // 指定密码
    // .authorities("UPDATE","内门弟子") // 指定当前用户的权限
    ********************************************************/

    // 装配 userDetailsService 对象
    builder
    .userDetailsService(userDetailsService);
    ```

## 密码加密

### 使用自定义的加密方式 MD5

1. SpringSecurity 提供的密码加密接口:

   ```java
   public interface PasswordEncoder {
    /**
    * 加密
    * Encode the raw password. Generally, a good encoding algorithm applies a SHA-1 or * greater hash combined with an 8-byte or greater randomly generated salt. */

    String encode(CharSequence rawPassword);
    /**
    * 校验：检查一个明文密码是否和一个密文密码一致
    * Verify the encoded password obtained from storage matches the submitted raw
    * password after it too is encoded. Returns true if the passwords match, false if * they do not. The stored password itself is never decoded. *
    * @param rawPassword the raw password to encode and match
    * @param encodedPassword the encoded password from storage to compare with
    * @return true if the raw password, after encoding, matches the encoded password from
    * storage */
    boolean matches(CharSequence rawPassword, String encodedPassword);
    }
   ```

2. 创建 PasswordEncoder 实现类 - 自定义 MD5 加密

    ```java
    @Component
    public class MyPasswordEncoder implements PasswordEncoder {
        @Override
        public String encode(CharSequence rawPassword) {
            return privateEncode(rawPassword);
        }
        @Override
        public boolean matches(CharSequence rawPassword, String encodedPassword) {
            // 1.对明文密码进行加密
            String formPassword = privateEncode(rawPassword);
            // 2.声明数据库密码
            String databasePassword = encodedPassword;
            // 3.比较
            return Objects.equals(formPassword, databasePassword);
        }
        private String privateEncode(CharSequence rawPassword) {
            try {
                // 1.创建 MessageDigest 对象
            String algorithm = "MD5";
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            // 2.获取 rawPassword 的字节数组
            byte[] input = ((String)rawPassword).getBytes();
            // 3.加密
            byte[] output = messageDigest.digest(input);
            // 4.转换为 16 进制数对应的字符
            String encoded = new BigInteger(1, output).toString(16);
            return encoded;
            } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
            }
        }
    }
    ```

3. 配置类中装配 MyPasswordEncoder

    ```java
    @Autowired
    private MyPasswordEncoder passwordEncoder;
    ```

4. SpringSecurity 中加载 MyPasswordEncoder

    ```java
    builder
        .userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);
    ```

### SpringSecurity 提供的带盐值的加密

```java
builder
    .userDetailsService(userDetailsService)
    .passwordEncoder(new BCryptPasswordEncoder());
```
