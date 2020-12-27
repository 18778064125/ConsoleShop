import java.io.InputStream;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws ClassNotFoundException {
        boolean bool=true;
        while (bool) {
            System.out.println("请输入用户名");

            Scanner sc = new Scanner(System.in);
            String username = sc.next();//阻塞方法
            System.out.println("你输入的用户名为" + username);

            System.out.println("请输入用密码");
            String password = sc.next();
            System.out.println("你输入的密码为" + password);

            //File file = new File("C:\\Users\\lenovo\\IdeaProjects\\ConsoleShop\\src\\Users.xlsx");
            InputStream in = Class.forName("Test").getResourceAsStream("/Users.xlsx");
            ReadUserExcel readExcel = new ReadUserExcel();//创建对象
            User users[] = readExcel.readExcel(in);
            System.out.println("kkkkk: " + users[0].getPassword());
            for (int i = 0; i < users.length; i++) {
                String pass = users[i].getPassword();
                pass = String.valueOf(pass);
                System.out.println("fffffffff: " + pass);
                if (username.equals(users[i].getUsername()) && password.equals(pass)) {
                    break;
                } else {
                    System.out.println("登入失败");
                }
            }
        }
    }
}
