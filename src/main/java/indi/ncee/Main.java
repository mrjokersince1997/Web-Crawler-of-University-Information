package indi.ncee;

import indi.ncee.entity.University;

public class Main {
    public static void main(String args[]) throws InterruptedException {
        University[] newUniversity = new University[100];
        for (int i = 30; i < 40; i++) {
            for (int j = 0; j < 100; j++) {
                newUniversity[j] = SchoolCrawler.getHttpSchool(i * 100 + j);
            }
            MySqlConn.insertData(newUniversity);
        }
    }

}
