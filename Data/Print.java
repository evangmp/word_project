package Data;

import java.sql.*;
import java.util.Scanner;

public class Print {
    String url;
    Print() { // optimization
        this.url = "jdbc:sqlite:C:\\Users\\evang\\IdeaProjects\\testencore\\database.db";
    }
    public void pre_set(String list, int which) { // main function with : print all the lists, add word, delete
        // and add a list (or table in DB)
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);

            switch(which) {
            case (0):
                Print_list printList = new Print_list();
                printList.word_list_new(connection);
                break;

            case (1):
                Add add_word = new Add(list);
                add_word.word_add(connection);
                break;

            case (2):
                Delete delete  = new Delete(list);
                delete.word_delete(connection);
                break;

            case (3):
                Add_table add_table = new Add_table();
                Scanner scanner_name = new Scanner(System.in);

                System.out.println("\nAdd a table, name : ");
                String name = scanner_name.nextLine();
                add_table.table_add(name, connection);
                break;
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error connecting");
            e.printStackTrace();
        }
    }
}

class Print_list extends Print { // print all the lists
    public void word_list_new(Connection connection) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        ResultSet resultSet = metaData.getTables(null, null, "%", null);

        while (resultSet.next()) {
            String name = resultSet.getString("TABLE_NAME");
            System.out.println(name);
        }
    }
}

class Add extends Print { // add word in a list
    String list;
    Add(String list) { // optimization
        super();
        this.list = list;
    }
    public void word_add(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Add word part :\n");

        System.out.println("French translation : ");
        String fr_word = scanner.nextLine();

        System.out.println("\nEnglish translation : ");
        String en_word = scanner.nextLine();

        String query = "insert into %s(fr_word, en_word) values (?, ?);".formatted(list);

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, fr_word);
        preparedStatement.setString(2, en_word);
        preparedStatement.executeUpdate();

        System.out.println("\nThe word has been added !\n");
    }
}

class Delete extends Print { // delete word in a list
    String list;
    Delete(String list){ // optimization
        super();
        this.list = list;
    }
    public void word_delete(Connection connection) throws SQLException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nFrench translation of the word : ");
        String fr_word = scanner.nextLine();
        String query = "delete from %s where fr_word=?".formatted(list);

        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, fr_word);
        preparedStatement.executeUpdate();

        System.out.println("\nThe word has been deleted !\n");
    }
}

class Add_table extends Print { // create a new list or table
    Add_table(){ // optimization
        super();
    }
    public void table_add(String name, Connection connection) throws SQLException {
        String query = ("create table %s (fr_word TEXT not NULL, en_word TEXT not NULL, level INTEGER NOT NULL " +
                "DEFAULT (0), train INTEGER NOT NULL DEFAULT (0))").formatted(name);

        Statement preparedStatement = connection.createStatement();
        preparedStatement.executeUpdate(query);

        System.out.println("\nTable created !\n");
    }
}
