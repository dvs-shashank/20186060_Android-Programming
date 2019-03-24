package com.google.engedu.anagrams;

public class Students {
    private String studentId;
    private String name;
    private String count;
    Students(String id, String name, String count) {
        studentId = id;
        this.name = name;
        this.count = count;
    }
//    Students(int count) {
//        this.count = count;
//    }


    public void setCount(String count) {
        this.count = count;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCount() {
        return count;
    }
}
