package com.example.demo.java8;

import lombok.Builder;

/**
 * @program demo1
 * @description 
 * @author wangqian
 * created on 2019-09-18
 * @version  1.0.0
 */
@Builder
public class Student {
    private String name = "zhangsan";
    private Integer age = 39;
    private Integer score;

    public Student() {
    }

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, Integer age, Integer score) {
        this.name = name;
        this.age = age;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    /**
     * 根据学生成绩进行升序排序
     */
    public static int comparedStudentByScore(Student student1, Student student2) {
        return student1.getScore() - student2.getScore();
    }

    /**
     * 根据学生名字的ASCII码进行升序排序
     */
    public static int comparedStudentByName(Student student1, Student student2) {
        return student1.getName().compareToIgnoreCase(student2.getName());
    }

    /**
     * 根据学生成绩进行升序排序
     */
    public int comparedStudent(Student student1) {
        return this.getScore() - student1.getScore();
    }
}
