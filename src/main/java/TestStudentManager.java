public class TestStudentManager {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();

        // 1. 添加学生信息到数据库
        System.out.println("1. 添加学生信息：");
        Student student1 = new Student("张三", "男", "计算机1班", 85.5, 90.0);
        Student student2 = new Student("李四", "女", "计算机2班", 78.0, 82.5);
        Student student3 = new Student("王五", "男", "计算机1班", 92.0, 88.5);

        boolean addResult1 = studentManager.addStudent(student1);
        boolean addResult2 = studentManager.addStudent(student2);
        boolean addResult3 = studentManager.addStudent(student3);

        System.out.println("添加学生1结果：" + (addResult1 ? "成功" : "失败"));
        System.out.println("添加学生2结果：" + (addResult2 ? "成功" : "失败"));
        System.out.println("添加学生3结果：" + (addResult3 ? "成功" : "失败"));

        // 2. 根据ID查询学生信息
        System.out.println("\n2. 根据ID查询学生信息：");
        Student queriedStudent = studentManager.getStudentById(1);
        if (queriedStudent != null) {
            System.out.println("查询到的学生信息：" + queriedStudent);
        } else {
            System.out.println("未找到ID为1的学生");
        }

        // 3. 显示所有学生信息
        System.out.println("\n3. 显示所有学生信息：");
        studentManager.showAllStudents();

        // 4. 计算学生各科目的平均分数
        System.out.println("\n4. 计算学生各科目的平均分数：");
        studentManager.calculateAverageScores();

        // 关闭数据库连接
        DatabaseConnection.closeConnection();
    }
}