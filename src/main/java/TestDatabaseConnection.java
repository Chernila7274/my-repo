import java.sql.Connection;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        // 获取数据库连接
        Connection connection = DatabaseConnection.getConnection();
        
        // 验证连接是否成功
        if (connection != null) {
            System.out.println("连接对象：" + connection);
        }
        
        // 关闭数据库连接
        DatabaseConnection.closeConnection();
    }
}