import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentManager {
    private Connection connection;

    // 构造方法，初始化数据库连接
    public StudentManager() {
        this.connection = DatabaseConnection.getConnection();
    }

    // 添加学生信息到数据库
    public boolean addStudent(Student student) {
        String sql = "INSERT INTO students (name, gender, class, math_score, java_score) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, student.getName());
            pstmt.setString(2, student.getGender());
            pstmt.setString(3, student.getClassName());
            pstmt.setDouble(4, student.getMathScore());
            pstmt.setDouble(5, student.getJavaScore());
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("添加学生失败：" + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    // 根据ID查询学生信息
    public Student getStudentById(int id) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setClassName(rs.getString("class"));
                student.setMathScore(rs.getDouble("math_score"));
                student.setJavaScore(rs.getDouble("java_score"));
                return student;
            }
        } catch (SQLException e) {
            System.out.println("查询学生失败：" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    // 显示所有学生信息
    public void showAllStudents() {
        String sql = "SELECT * FROM students";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            System.out.println("所有学生信息：");
            System.out.println("ID\t姓名\t性别\t班级\t\t高数成绩\tJava成绩");
            System.out.println("------------------------------------------------------------------");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String gender = rs.getString("gender");
                String className = rs.getString("class");
                double mathScore = rs.getDouble("math_score");
                double javaScore = rs.getDouble("java_score");
                System.out.printf("%d\t%s\t%s\t%s\t%.2f\t%.2f\n", id, name, gender, className, mathScore, javaScore);
            }
        } catch (SQLException e) {
            System.out.println("显示学生信息失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 计算学生各科目的平均分数
    public void calculateAverageScores() {
        String sql = "SELECT AVG(math_score) AS avg_math, AVG(java_score) AS avg_java FROM students";
        try (PreparedStatement pstmt = connection.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                double avgMath = rs.getDouble("avg_math");
                double avgJava = rs.getDouble("avg_java");
                System.out.println("\n学生科目平均分数：");
                System.out.println("------------------------------------------------------------------");
                System.out.printf("高数平均分：%.2f\n", avgMath);
                System.out.printf("Java平均分：%.2f\n", avgJava);
            }
        } catch (SQLException e) {
            System.out.println("计算平均分数失败：" + e.getMessage());
            e.printStackTrace();
        }
    }
}