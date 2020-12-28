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
            InputStream inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
            ReadUserExcel readExcel = new ReadUserExcel();//创建对象
            User users[] = readExcel.readExcel(in);
            System.out.println("kkkkk: " + users[0].getPassword());
            for (int i = 0; i < users.length; i++) {
                String pass = users[i].getPassword();
                pass = String.valueOf(pass);
                System.out.println("fffffffff: " + pass);
                if (username.equals(users[i].getUsername()) && password.equals(pass)) {
                    System.out.println("登入成功");
                    bool=false;
                    /*x显示商品*/
                    ReadProductExcel readProductExcel=new ReadProductExcel();
                    Product products[]=readProductExcel.getAllProduct(inProduct);
                    for (Product product:products){
                        System.out.print(product.getId());
                        System.out.print("\t"+product.getName());
                        System.out.print("\t"+product.getPrice());
                        System.out.print("\t"+product.getDesc());
                    }
                    System.out.println("请输入商品id把该商品加入购物车");
                    String id=sc.next();
                    int count = 0;
                    /*
                    创建一个购物车的数组：存的是商品
                     */
                    Product carts[] = new Product[3];
                    /*
                    根据此ID去Excel中去查找是否有该ID的商品信息，如果有则返回该商品即可
                     */
                    inProduct = null;
                    inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
                    Product product = readProductExcel.getProductById(id, inProduct);
                    System.out.println("要购买的商品价格：" + product.getPrice());
                    if(product!=null){
                        carts[count++]=product;
                    }
                    System.out.println("继续购买商品请按1");
                    System.out.println("查看购物车请按2");
                    int choose=sc.nextInt();
                    if(choose==1){
                        inProduct = null;
                        inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
                        readProductExcel = new ReadProductExcel();
                        products = readProductExcel.getAllProduct(inProduct);
                        for (Product p : products) {
                            System.out.print(p.getId());
                            System.out.print("\t" + p.getName());
                            System.out.print("\t" + p.getPrice());
                            System.out.println("\t" + p.getDesc());
                        }
                        System.out.println("请输入商品ID把该商品加入购物车");
                        id = sc.next();
                        inProduct = null;
                        inProduct = Class.forName("Test").getResourceAsStream("/product.xlsx");
                        product = readProductExcel.getProductById(id, inProduct);
                        System.out.println("要购买的商品价格：" + product.getPrice());
                        if(product!=null){
                            carts[count++]=product;
                        }
                    }else if(choose==2){
                        /*
                        查看购物车
                         */
                    }

                    break;
                } else {
                    System.out.println("登入失败");
                }
            }
        }
    }
}
