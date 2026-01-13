import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        StudentManager studentManager = new StudentManager();
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("========================================");
        System.out.println("          学生管理系统 v1.0");
        System.out.println("========================================");

        while (!exit) {
            System.out.println("\n请选择要执行的操作：");
            System.out.println("1. 添加学生信息");
            System.out.println("2. 根据ID查询学生信息");
            System.out.println("3. 显示所有学生信息");
            System.out.println("4. 计算学生各科目的平均分数");
            System.out.println("0. 退出系统");
            System.out.print("请输入您的选择（0-4）：");

            int choice;
            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // 消费换行符
            } catch (Exception e) {
                System.out.println("输入无效，请输入数字！");
                scanner.nextLine(); // 消费无效输入
                continue;
            }

            switch (choice) {
                case 0:
                    exit = true;
                    System.out.println("感谢使用学生管理系统，再见！");
                    break;
                case 1:
                    addStudent(studentManager, scanner);
                    break;
                case 2:
                    queryStudent(studentManager, scanner);
                    break;
                case 3:
                    studentManager.showAllStudents();
                    break;
                case 4:
                    studentManager.calculateAverageScores();
                    break;
                default:
                    System.out.println("输入无效，请输入0-4之间的数字！");
                    break;
            }
        }

        scanner.close();
        DatabaseConnection.closeConnection();
    }

    // 添加学生信息
    private static void addStudent(StudentManager studentManager, Scanner scanner) {
        System.out.println("\n========== 添加学生信息 ==========");
        
        System.out.print("请输入学生姓名：");
        String name = scanner.nextLine();
        
        String gender;
        while (true) {
            System.out.print("请输入学生性别（男/女）：");
            gender = scanner.nextLine();
            if (gender.equals("男") || gender.equals("女")) {
                break;
            } else {
                System.out.println("性别输入无效，请输入'男'或'女'！");
            }
        }
        
        System.out.print("请输入学生班级：");
        String className = scanner.nextLine();
        
        double mathScore;
        while (true) {
            System.out.print("请输入高数成绩：");
            try {
                mathScore = scanner.nextDouble();
                scanner.nextLine(); // 消费换行符
                if (mathScore >= 0 && mathScore <= 100) {
                    break;
                } else {
                    System.out.println("成绩必须在0-100之间！");
                }
            } catch (Exception e) {
                System.out.println("成绩输入无效，请输入数字！");
                scanner.nextLine(); // 消费无效输入
            }
        }
        
        double javaScore;
        while (true) {
            System.out.print("请输入Java成绩：");
            try {
                javaScore = scanner.nextDouble();
                scanner.nextLine(); // 消费换行符
                if (javaScore >= 0 && javaScore <= 100) {
                    break;
                } else {
                    System.out.println("成绩必须在0-100之间！");
                }
            } catch (Exception e) {
                System.out.println("成绩输入无效，请输入数字！");
                scanner.nextLine(); // 消费无效输入
            }
        }
        
        Student student = new Student(name, gender, className, mathScore, javaScore);
        boolean success = studentManager.addStudent(student);
        if (success) {
            System.out.println("学生信息添加成功！");
        } else {
            System.out.println("学生信息添加失败！");
        }
    }

    // 根据ID查询学生信息
    private static void queryStudent(StudentManager studentManager, Scanner scanner) {
        System.out.println("\n========== 查询学生信息 ==========");
        
        System.out.print("请输入要查询的学生ID：");
        int id;
        try {
            id = scanner.nextInt();
            scanner.nextLine(); // 消费换行符
        } catch (Exception e) {
            System.out.println("ID输入无效，请输入数字！");
            scanner.nextLine(); // 消费无效输入
            return;
        }
        
        Student student = studentManager.getStudentById(id);
        if (student != null) {
            System.out.println("查询到的学生信息：");
            System.out.println("ID：" + student.getId());
            System.out.println("姓名：" + student.getName());
            System.out.println("性别：" + student.getGender());
            System.out.println("班级：" + student.getClassName());
            System.out.println("高数成绩：" + student.getMathScore());
            System.out.println("Java成绩：" + student.getJavaScore());
        } else {
            System.out.println("未找到ID为" + id + "的学生！");
        }
    }
}