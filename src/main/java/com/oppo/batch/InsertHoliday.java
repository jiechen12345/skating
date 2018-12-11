package com.oppo.batch;

/**
 * Created by JieChen on 2018/11/7.
 */

import java.util.Scanner;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by lime on 2016/7/31.
 */
//reallyStrongPwd123
public class InsertHoliday {
    public static String START = "20181101";
    public static String END = "20190330";
    public static String HolidayTitle = "假日";
    public static String FLAG = "H"; //假日代號
    public static String FLAG2 = "N"; //平日
    public static Integer NUM = 150; //假日預設人數
    public static Integer NUM2 = 100;//平日預設人數
    public static String[] sessionName = {"假日及寒假加開場次", "第一場", "第二場", "第三場"};
    public static String[] startTime = {"14:00", "16:00", "18:00", "20:00"};
    public static String[] endTime = {"15:30", "17:30", "19:30", "21:30"};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("請輸入DB username：");
        String user = sc.nextLine();
        if (!"".equals(user)) {
            System.out.println("請輸入DB password：");
            String password = sc.nextLine();
            System.out.println("請輸入STARTDAT(20181101)：");
            if (!"".equals(sc.nextLine())) {
                START = sc.nextLine();
            }
            System.out.println("請輸入ENDDAT(20190330)：");
            if (!"".equals(sc.nextLine())) {
                END = sc.nextLine();
            }
            System.out.println("假日人數(150)：");
            if (!"".equals(sc.nextLine())) {
                NUM = sc.nextInt();
            }
            System.out.println("平日人數(100)：");
            if (!"".equals(sc.nextLine())) {
                NUM2 = sc.nextInt();
            }
            System.out.println("你的信息如下：");
            System.out.println("username：" + user + "\n" + "password：" + password + "\n");
            System.out.println("STARTDAT：" + START + "\n" + "ENDDAT：" + END + "\n");
            System.out.println("假日人數：" + NUM + "\n" + "平日人數：" + NUM2 + "\n");
            //驱动程序名
            String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            //String driver = "oracle.jdbc.driver.OracleDriver";
            //要插入的数据库
            //String url = "jdbc:oracle:thin:@127.0.0.1:1521:orcl";
            //String url = "jdbc:mysql://127.0.0.1:3306/skating";
            //String url = "jdbc:mysql://127.0.0.1:3306/skating";
            String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=skating";

//        String user = "root";
//        String password = "asd";
            try {
                //加载驱动程序
                Class.forName(driver);
                //连接数据库
                Connection conn = DriverManager.getConnection(url, user, password);
                if (!conn.isClosed())
                    System.out.println("[batch]Succeeded connecting to the Database!");
                //statement用来执行SQL语句
                Statement statement = conn.createStatement();

                //---清空
//                String truncateSql = "TRUNCATE TABLE holiday";
//                statement.execute(truncateSql);
//                truncateSql = "TRUNCATE TABLE  accommodate";
//                statement.execute(truncateSql);
//                truncateSql = "TRUNCATE TABLE SESSIONS";
//                statement.execute(truncateSql);
//                truncateSql = "TRUNCATE TABLE status";
//                statement.execute(truncateSql);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
                Date start = sdf.parse(START);//开始时间
                Date end = sdf.parse(END);//结束时间
                List<Date> lists = dateSplit(start, end);
//            Date nextWorkDate = sdf.parse("20180108");//下一个工作日，默认1月2日，1日为元旦
//            Date lastWorkDate = sdf.parse("20181106");//上一个工作日
                Date nextWorkDate;
                Date lastWorkDate;//上一个工作日
                //1:送出表單 2:OTP申請 3:OTP通過 4:審核不通過 5:審核通過 6:到場
                String insertSql = "";
                String status_name = "";
                for (int i = 1; i <= 6; i++) {
                    switch (i) {
                        case 1:
                            status_name = "送出表單";
                            break;
                        case 2:
                            status_name = "OTP申請";
                            break;
                        case 3:
                            status_name = "OTP通過";
                            break;
                        case 4:
                            status_name = "審核不通過";
                            break;
                        case 5:
                            status_name = "審核通過";
                            break;
                        case 6:
                            status_name = "到場";
                            break;

                    }
                    insertSql = "INSERT INTO status (status_name) " +
                            "VALUES('"+  status_name  +"')";
                    System.out.println(insertSql);
                    statement.execute(insertSql);
                }

                //-設定容納人數

                insertSql = "INSERT INTO accommodate (flag,num) " +
                        "VALUES('" + FLAG + "'," + NUM + ")";
                System.out.println(insertSql);
                statement.execute(insertSql);
                insertSql = "INSERT INTO accommodate (flag,num) " +
                        "VALUES('" + FLAG2 + "'," + NUM2 + ")";
                System.out.println(insertSql);
                statement.execute(insertSql);
                //-設定例假日
                if (!lists.isEmpty()) {
                    for (Date date : lists) {
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(date);
                        //System.out.println("********插入日期:" + sdf.format(date) + "***********");

                        int year = cal.get(Calendar.YEAR);
                        int month = cal.get(Calendar.MONTH) + 1;
                        int day = cal.get(Calendar.DATE);
                        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;

                        String monthStr = "", dayStr = "";
                        if (month / 10 == 0) {
                            monthStr = "0" + String.valueOf(month);
                        } else {
                            monthStr = String.valueOf(month);
                        }
                        if (day / 10 == 0) {
                            dayStr = "0" + String.valueOf(day);
                        } else {
                            dayStr = String.valueOf(day);
                        }

                        lastWorkDate = getLastWorkDay(date);

                        //T_CMM_TCLD表字段：年，月，日，日期，标识（1为假日，0为工作日），周几，上一个工作日，下一个工作日

                        // System.out.println(year+","+monthStr+","+dayStr+","+sdf.format(date));
//                    System.out.println(insertSql);
                        if (isHoliday(date) == 0) {
                            nextWorkDate = date;
                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                            String dateString = sdf2.format(nextWorkDate);
                            System.out.println(dateString);
                            for (int i = 1; i <= 3; i++) {
                                insertSql = "insert into sessions (dat,reserved,sessions_name,start_time,end_time,accommodate_flag,extra)\n" +
                                        "VALUES('" + dateString + "'," + 0 + ",'" + sessionName[i] + "','" + startTime[i] + "','" + endTime[i] + "','" + FLAG2 + "'," + 0 + ")";
                                System.out.println("平日 " + insertSql);
                                statement.execute(insertSql);
                            }
                        }
                        if (isHoliday(date) != 0) {
                            nextWorkDate = date;
                            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                            String dateString = sdf2.format(nextWorkDate);
                            System.out.println(dateString);
                            insertSql = "INSERT INTO holiday (holidat, title,accommodate_flag) " +
                                    "VALUES('" + dateString + "','" + HolidayTitle + "','" + FLAG + "')";
                            statement.execute(insertSql);
                            for (int i = 0; i <= 3; i++) {
                                insertSql = "insert into sessions (dat,reserved,sessions_name,start_time,end_time,accommodate_flag,extra)\n" +
                                        "VALUES('" + dateString + "'," + 0 + ",'" + sessionName[i] + "','" + startTime[i] + "','" + endTime[i] + "','" + FLAG + "'," + 0 + ")";
                                System.out.println("假日 " + insertSql);
                                statement.execute(insertSql);
                            }


                        }
                    }
                }

                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static List<Date> dateSplit(Date start, Date end) throws Exception {
        if (!start.before(end))
            throw new Exception("开始时间应该在结束时间之后");
        Long spi = end.getTime() - start.getTime();
        Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

        List<Date> dateList = new ArrayList<Date>();
        dateList.add(end);
        for (int i = 1; i <= step; i++) {
            dateList.add(new Date(dateList.get(i - 1).getTime() - (24 * 60 * 60 * 1000)));// 比上一天减一
        }
        return dateList;
    }

    /**
     * 判断是否为节假日，若是返回1，否则返回0
     *
     * @param date
     * @return
     */
    private static int isHoliday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        List<String> holidays = getHolidays();
        List<String> workdays = getWorkDays();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
//        System.out.println(sdf.format(date));
        if (((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) && !workdays.contains(sdf.format(date))) || holidays.contains(sdf.format(date))) {
            return 1;
        }
        return 0;
    }

    private static Date getLastWorkDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date dateTemp = cal.getTime();

        while (isHoliday(dateTemp) != 0) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
            dateTemp = cal.getTime();
        }
        return dateTemp;
    }

    private static List<String> getHolidays() {
        List<String> holidays = new ArrayList<String>();
        /*holidays.add("20170101");//元旦
        holidays.add("20170102");
        holidays.add("20170103");
        holidays.add("20170218");//春节
        holidays.add("20170219");
        holidays.add("20170220");
        holidays.add("20170221");
        holidays.add("20170222");
        holidays.add("20170223");
        holidays.add("20170224");
        holidays.add("20170404");//清明节
        holidays.add("20170405");
        holidays.add("20170406");
        holidays.add("20170429");//劳动节
        holidays.add("20170430");
        holidays.add("20170501");
        holidays.add("20170620");//端午节
        holidays.add("20170621");
        holidays.add("20170622");
        holidays.add("20170927");//中秋节
        holidays.add("20171001");//国庆节
        holidays.add("20171002");
        holidays.add("20171003");
        holidays.add("20171004");
        holidays.add("20171005");
        holidays.add("20171006");
        holidays.add("20171007");*/
        return holidays;
    }

    private static List<String> getWorkDays() {
        List<String> workDays = new ArrayList<String>();
        /*workDays.add("20170104");//补班
        workDays.add("20170215");
        workDays.add("20170228");
        workDays.add("20171010");*/
        return workDays;
    }
}