package indi.ncee;

import indi.ncee.entity.University;

import static org.junit.jupiter.api.Assertions.*;

class SchoolCrawlerTest {

    @org.junit.jupiter.api.Test
    void getHttpSchool() {
        int[] testCode = {0};
        University newUniversity = SchoolCrawler.getHttpSchool(105, testCode);
        System.out.print(newUniversity);
    }
}