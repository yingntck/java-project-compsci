package database;

import models.Subject;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;


public class HomeConnector {
    private static String dbURL = "jdbc:sqlite:DatabaseRegisTEXT.db";
    private static String dbName = "org.sqlite.JDBC";

    public static ObservableList<Subject> getSubject() {
        ObservableList<Subject> subjects = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select subject.id, subject.name, subject.credit, subject.year, subject.semester, subject.difficulty, subject.status, subject_dependency.id_prereq from subject left join subject_dependency on subject.id = subject_dependency.id_main";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String subjectId = resultSet.getString(1);
                    String subjectName = resultSet.getString(2);
                    int credit = resultSet.getInt(3);
                    int year = resultSet.getInt(4);
                    String semester = resultSet.getString(5);
                    String difficulty = resultSet.getString(6);
                    String preRequire = resultSet.getString(8);
                    String status = resultSet.getString(7);

                    boolean statusBoolean;
                    if (status.equals("1")) {
                        statusBoolean = true;
                    } else {
                        statusBoolean = false;
                    }
                    subjects.add(new Subject(subjectId, subjectName, credit, year, semester, difficulty, preRequire, statusBoolean));
                }
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subjects;
    }

        public static void resetSubject() {
            try {
                Class.forName(dbName);
                Connection connection = DriverManager.getConnection(dbURL);
                if (connection != null) {
                    String query = "update subject set syatus = '" + 0 + "'";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.executeUpdate();
                    Statement statement = connection.createStatement();
                    connection.close();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static void updateSubject(String oldId, String newId, String name, String year, String semester, String previousSubject, String difficultly) {
            try {
                Class.forName(dbName);
                Connection connection = DriverManager.getConnection(dbURL);
                if (connection != null) {
                    String query = "update subject set subjectID = '" + newId + "', subjectName = '" + name + "', year = '" + year + "', semester = '" + semester + "', previousSubject = '" + previousSubject + "', difficultly = '" + difficultly + "' where subject.subjectID=='" + oldId + "'";
                    PreparedStatement p = connection.prepareStatement(query);
                    p.executeUpdate();
                    Statement statement = connection.createStatement();
                    connection.close();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static void createSubject(String id, String name, String year, String semester, String previousSubject, String difficultly) {
            try {
                Class.forName(dbName);
                Connection connection = DriverManager.getConnection(dbURL);
                if (connection != null) {
                    String query = "insert into subject (subjectID, subjectName, year, semester, previousSubject, difficultly) values ('" + id + "', '" + name + "', '" + year + "', '" + semester + "', '" + previousSubject + "' , '" + difficultly + "')";
                    PreparedStatement p = connection.prepareStatement(query);
                    p.executeUpdate();
                    Statement statement = connection.createStatement();
                    connection.close();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    public static ObservableList<Subject> getSubjectFilter(String yearString, String semesterString) {
        ObservableList<Subject> subjects = FXCollections.observableArrayList();
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null) {
                String query = "select subject.id, subject.name, subject.credit, subject.year, subject.semester, subject.difficulty, subject.status, subject_dependency.id_prereq from subject left join subject_dependency on subject.id = subject_dependency.id_main where subject.year == '" + yearString + "' and subject.semester == '" + semesterString + "' ";
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(query);
                while (resultSet.next()) {
                    String subjectId = resultSet.getString(1);
                    String subjectName = resultSet.getString(2);
                    int credit = resultSet.getInt(3);
                    int year = resultSet.getInt(4);
                    String semester = resultSet.getString(5);
                    String difficulty = resultSet.getString(6);
                    String status = resultSet.getString(7);
                    String preRequire = resultSet.getString(8);

                    boolean statusBoolean;
                    if (status.equals("1")) {
                        statusBoolean = true;
                    } else {
                        statusBoolean = false;
                    }

                    subjects.add(new Subject(subjectId, subjectName, credit, year, semester, difficulty, preRequire, statusBoolean));
                }
                connection.close();
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return subjects;
    }

    public static void saveSubject(String subjectId, int status) {
        try {
            Class.forName(dbName);
            Connection connection = DriverManager.getConnection(dbURL);
            if (connection != null){
                String query = "update Subject set status = '" + status + "' where subject.id == '" + subjectId + "' ";
                PreparedStatement p = connection.prepareStatement(query);
                p.executeUpdate();
                connection.close();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}