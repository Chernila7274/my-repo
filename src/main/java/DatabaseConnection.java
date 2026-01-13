import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/student_management?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "";
    private static Connection connection = null;

    private DatabaseConnection() {
        // 私有构造方法，防止实例化
    }

    public static Connection getConnection() {
        if (connection == null) {
            synchronized (DatabaseConnection.class) {
                if (connection == null) {
                    try {
                        // 加载MySQL驱动
                        Class.forName("com.mysql.cj.jdbc.Driver");
                        // 获取数据库连接
                        connection = DriverManager.getConnection(URL, USER, PASSWORD);
                        System.out.println("数据库连接成功！");
                    } catch (ClassNotFoundException e) {
                        System.out.println("找不到MySQL驱动类：" + e.getMessage());
                        e.printStackTrace();
                    } catch (SQLException e) {
                        System.out.println("数据库连接失败：" + e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
        return connection;
    }

    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                connection = null;
                System.out.println("数据库连接已关闭！");
            } catch (SQLException e) {
                System.out.println("关闭数据库连接失败：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}