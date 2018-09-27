启动方式：
搜索maven project:
双击jetty:run
在浏览器输入localhost:8000/bbs/index.html访问

问题：
1、com.yaoyao.domain.User：
    implements Serializable为什么要序列化呢？什么是序列化？

2、com.yaoyao.dao.UserDao.setJdbcTemlate:@Autowired
    还可以在方法上注入？

3、com.yaoyao.dao.UserDao.findUserByUserName:
    final String userName为什么要加个final呢？

4、com.yaoyao.dao.UserDao.findUserByUserName：
    为什么user不是final，这里就会有红叉叉呢？

5、@Service、@Repository、@Autowired等这些注解有些什么不一样的含义么？

6、com.yaoyao.service.UserService#loginSuccess
    @Transactional 保持事务一致性？

7、com.yaoyao.service.UserServiceTest
    //@ContextConfiguration("classpath*:/applicationContext.xml") 在项目中没有搜到这个文件applicationContext.xml，怎么用到它的呢？