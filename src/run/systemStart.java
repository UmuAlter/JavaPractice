package run;

import MovieBuy.Customer;
import MovieBuy.Movie;
import MovieBuy.User;
import MovieBuy.merchant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class systemStart {
    /*
    设置全局的静态变量可以避免传参
     */

    //只需要存在一个所以是static
    //存储用户（商家+顾客） 多态
    public static  List<User> AllUser = new ArrayList<>();
    //商家-排片信息_一对多的关系
    public static Map<merchant,List<Movie>> AllMovies= new HashMap<>();
    //  扫描器
    public static final Scanner SC = new Scanner(System.in);
    //正在登陆的用户——一次只允许一个用户登录
    public static User loginuser;
    //日期格式化
    public static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd hh:mm:ss");
    //记录日志
    public static Logger logger = LoggerFactory.getLogger("systemStart.class");

    public static void main(String[] args) {
        //show();
       // main();
    }
    /*public static void main(){
        System.out.println("第二个main函数");
    }*/
    private static void show(){
        /*
        提供初始界面菜单
         */
    }
    //用户登录功能
    public static void login(){
        while (true)
        {
            System.out.print("用户名：");
            String username = SC.nextLine();
            System.out.print("密码：");
            String password = SC.nextLine();
            User user = getTheUser(username);
            //校验
            if (user != null) {
                //判断密码是否正确
                if (user.getPasswd().equals(password)) {
                    //成功
                    //判断用户类型  ***********instanceof*********************************************
                    if(user instanceof Customer){
                        //客户登录
                        loginuser = user;        //将user传出
                        shwoCust();
                        //记录登录信息
                        logger.info(loginuser.getLogname()+"……");
                    }else{
                        //商家登录
                        loginuser = user;
                        showMer();
                        logger.info(loginuser.getLogname()+".....");
                    }
                    break;
                } else {
                    System.out.println("PW REE-OR");
                }
            } else {
                System.out.println("用户不存在");
            }
        }
    }
    //根据用户名查询是否存在此用户
    public static User getTheUser(String username){
        for (User u : AllUser){
            if(u.getLogname().equals(username)){
                return u;
            }
        }
        return null;
    }
    //客户操作界面
    private static void showMer() {
        //

    }
    //用户购票
    public static void BuyMovie(){
        System.out.println("请输入从哪个店铺购买：");
        while (true) {
            String shopname = SC.nextLine();
            //查询是否存在该商家
            merchant mername = getMerchantByshopname(shopname);
            if(mername != null){
                //显示店铺影片
                List<Movie> list = AllMovies.get(mername);
                if (list.size() >0) {
                    for(Movie movie : list ){
                        System.out.println("评分："+movie.getScore() + "...");
                    }
                    while (true) {
                        System.out.println("请输入要买的电影：");
                        String moviename = SC.nextLine();
                        //显示找到的电影
                        Movie movie = BuyMovie(moviename,list);
                        if (movie != null) {
                            System.out.println(movie.getName()+"...");
                            while (true) {
                                System.out.println("请输入要买的数量：");
                                int num = Integer.parseInt(SC.nextLine());
                                if(num <= movie.getNumber()){
                                    //BigDecimal计算精度更加准确
                                    double price = BigDecimal.valueOf(movie.getPrice())
                                            .multiply(BigDecimal.valueOf(num)).doubleValue();
                                    if(loginuser.getMoney() > price ){
                                        //钱够
                                        loginuser.setMoney(loginuser.getMoney() - price);
                                        mername.setMoney(mername.getMoney() + price);
                               //结束        ******************
                                        return;
                                    }else{
                                        //钱不够
                                    }
                                }else{
                                    System.out.println("票数不够");
                                    System.out.println("是否继续");
                                    String s = SC.nextLine();
                                    if("y".equals(s)){
                                        continue;
                                    }else{
                                        break;
                                    }
                                }
                            }
                        } else {
                            System.out.println("未找到该电影");
                            System.out.println("是否继续");
                            String s = SC.nextLine();
                            if("y".equals(s)){
                                continue;
                            }else{
                                break;
                            }
                        }
                    }
                } else {
                    System.out.println("NO Movies");
                }
            }else{
                System.out.println("不存在该商家");
                System.out.println("是否继续");
                String s = SC.nextLine();
                if("y".equals(s)){
                    continue;
                }else{
                    break;
                }
            }
        }
    }
    //返回（找）电影
    public static Movie BuyMovie(String moviename,List<Movie> list){
        for(Movie movie : list){
            if(movie.getName().contains(moviename)){
                return  movie;
            }
        }
        return null;
    }
    //根据商家名找店铺
    public static merchant getMerchantByshopname(String shopname){
        Set<merchant> set = AllMovies.keySet();
        for(merchant m : set){
            if(m.getShopname().equals(shopname)){
                System.out.println("找到该商家");
                return m;
            }
        }
        return null;
    }
    //遍历商家-影片//
    public static void showAll(){
        AllMovies.forEach((merchant, movies) -> {
            System.out.println(merchant.getShopname()+"\t"+merchant.getAddress()+"......");
            //movies是List
            for(Movie m : movies){
                System.out.println("...");
            }
        });
    }
//商家操作*******************************************************************************************************
    //商家操作界面
    private static void shwoCust() {
        //
        showBussinessinfo();
    }
    //商家登陆后——展示商家信息
    private static void showBussinessinfo(){
        merchant m = (merchant)loginuser;
        System.out.println( m.getShopname() );    //必须先有转换才能输出子类函数
        List<Movie> lm = AllMovies.get(m);
        System.out.println("片名\t主演\t评分\t……时间\t");
        if(lm.size()>0){
            for(Movie movie : lm){
                System.out.println(
                        movie.getName()+"\t"
                                +movie.getActor()+"\t"
                                +movie.getScore()+"\t"
                                + simpleDateFormat.format( movie.getStartTime())   //格式化输出时间
                        //……
                );
            }
        }
    }
    //添加电影
    public static void MovieAdd(){
        //创建电影对象然后放到商家对应的List
        merchant m = (merchant)loginuser;
        List<Movie> lm = AllMovies.get(m);
        Movie movie = new Movie();
        //商家输入各种参数
        //.....
        //字符串时间转Data
        while(true) {
            try {
                String stime = SC.nextLine();
                movie.setStartTime(simpleDateFormat.parse(stime));
                lm.add(movie);
                return;
            } catch (Exception e) {
                System.out.println("时间解析失败");
                throw new RuntimeException(e);
            }
        }
    }
    //删除电影
    public static void MovieDel(){
        merchant m = (merchant)loginuser;
        List<Movie> lm = AllMovies.get(m);
        String DelName = SC.nextLine();
        if(lm.size() == 0){
            System.out.println("NOT found");
            return;
        }else{
            Movie movie = getMocie(DelName);
            if(movie != null){
                lm.remove(movie);
                return;
            }else{
                System.out.println("NOT found");
            }
        }
    }
    //查找电影并返回
    public static Movie getMocie(String name){
        merchant m = (merchant)loginuser;
        List<Movie> lm = AllMovies.get(m);
        for(Movie movie : lm ){
            //***模糊匹配***
            if(movie.getName().contains(name)){
                return movie;
            }
        }
        return null;
    }
    //修改电影
    public static void MovieChange(){
        merchant m = (merchant)loginuser;
        List<Movie> lm = AllMovies.get(m);
        String ClName = SC.nextLine();
        if(lm.size() == 0){
            System.out.println("NOT found");
            return;
        }else{
            Movie movie = getMocie(ClName);
            if(movie != null){
                //使用SET方法修改
                //movie.setActor();
                return;
            }else{
                System.out.println("NOT found");
            }
        }
    }
}
