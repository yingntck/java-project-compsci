package models;

public class Subject {
    private String subjectId;
    private String subjectName;
    private int credit;
    private int year;
    private String semester;
    private String difficulty;
    private String preRequire;
    private boolean status;

    public Subject(String subjectId, String subjectName,int credit, int year, String semester, String difficulty,String preRequire) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credit = credit;
        this.year = year;
        this.semester = semester;
        this.difficulty = difficulty;
        this.preRequire = preRequire;
    }

    public Subject(String subjectId, String subjectName, int credit, int year, String semester, String difficulty, String preRequire, boolean status) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.credit = credit;
        this.year = year;
        this.semester = semester;
        this.difficulty = difficulty;
        this.preRequire = preRequire;
        this.status = status;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public int getCredit() {
        return credit;
    }

    public int getYear() {
        return year;
    }

    public String getSemester() {
        return semester;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getPreRequire() {
        return preRequire;
    }

    public String getStatus(){
        return this.status ? "Pass" : "✖";
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getStatusInteger() {
        return this.status ? 1 : 0;
    }

    public boolean getStatusBoolean() {
        return this.status;
    }

}
