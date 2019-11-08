package indi.ncee;

import indi.ncee.entity.University;

import static org.junit.jupiter.api.Assertions.*;

class SchoolCrawlerTest {

    @org.junit.jupiter.api.Test
    void getHttpSchool() {
        University newUniversity = SchoolCrawler.getHttpSchool(105);
        System.out.print(newUniversity);
    }
}