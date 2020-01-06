package com.itstudy.jdbc;

import javax.xml.transform.Result;
import java.sql.*;

/**
 * 程序的耦合
 *      耦合：程序间的依赖关系
 *      包括：
 *          类之间的依赖
 *          方法间的依赖
 *      解耦：
 *          降低程序间的依赖关系
 *      实际开发中：
 *          应该做到：编译期不依赖，运行时才依赖
 *      解耦的思路：
 *          第一步：使用反射来创建对象，而避免使用new关键字
 *          第二步：通过读取配置文件来获取要创建的对象全限定类名
 *
 *
 */

/**
 * 这里不需要这样写DriverManager.registerDriver(new com.mysql.jdbc.Driver()),
 * 原因是com.mysql.jdbc.Driver类的静态代码快里面已经进行了修改的操作。
 * 我们通过Driver类的源码可以了解到，Driver类中就有一个静态的代码块，
 * 只要我们执行了Driver类中的静态代码块，并把驱动的实例放入到Drivers的一个数组列表中，
 * 我们再调用方法registerDrever就相当于又向drivers列表中放了一次driver驱动，
 * 虽然这并不影响我们程序，但是这样做实在是没有必要，还会影响程序的运行。
 */
public class JdbcDemo1 {
    public static void main(String[] args) throws SQLException {
        //1.注册驱动
        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        //2.获取连接
        Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/eesy",
                "root","937518986");
        //3.获取操作数据库的预处理对象
        PreparedStatement pstm=conn.prepareStatement("select * from account");
        //4.执行SQL，得到结果集
        ResultSet rs=pstm.executeQuery();
        //5.遍历结果集
        while (rs.next()){
            System.out.println(rs.getString("name")+"-->"+rs.getString("money"));
        }
        //6.释放资源
        rs.close();
        pstm.close();
        conn.close();
    }

}
