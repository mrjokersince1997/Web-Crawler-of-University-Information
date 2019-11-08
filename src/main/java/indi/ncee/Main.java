package indi.ncee;

import indi.ncee.entity.University;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        //循环结束标志
        int [] flag_over = {0};
        //实体类
        University[] newUniversity = new University[100];

        for (int i = 0; flag_over[0] < 50 ; i++) {
            for (int j = 0; j < 100; j++) {
                //抓取数据
                newUniversity[j] = SchoolCrawler.getHttpSchool(i * 100 + j, flag_over);
            }
            //载入数据库
            MySqlConn.insertData(newUniversity);
        }
        System.out.print("程序运行结束。");
    }

}
