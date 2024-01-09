package Data;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Print_list_word { // center to have complete dictionaries of a line or all the line
    String url; String list;
    Print_list_word() { // optimization
        this.url = "jdbc:sqlite:C:\\Users\\evang\\IdeaProjects\\real_pr\\database.db";
    }

    public void database(String list, String word) { // print all the fr_word or en_word of a list
        this.list = list;
        Just_word just_word = new Just_word();
        Print_list_word print_list_word = new Print_list_word();

        if (Objects.equals(word, "fr_word") || Objects.equals(word, "en_word") ) {
            Map<Integer, String> dict_word = just_word.word_sel(list, word);
            print_list_word.print_words(dict_word);
        }
        if (Objects.equals(word, "all")) {
            Map<Integer, String> fr_word = just_word.word_sel(list, "fr_word");
            Map<Integer, String> en_word = just_word.word_sel(list, "en_word");
            Map<Integer, Integer> lvl = just_word.int_sel(list, "level");
            Map<Integer, Integer> train = just_word.int_sel(list, "train");
            print_list_word.print_stats(fr_word, en_word, lvl, train);

        }
        else {
            Main main = new Main();
            main.second_main();
        }
    }
    public void print_words(Map<Integer, String> dict_word) {
        for(int i = 0; i <dict_word.size(); i++) { System.out.println(dict_word.get(i)); } }

    public void print_stats(Map<Integer, String> fr_word, Map<Integer, String> en_word, Map<Integer, Integer> lvl,
                            Map<Integer, Integer> train) {
        System.out.println();
        for(int i = 0; i < fr_word.size(); i++) {

            System.out.println("'"+ fr_word.get(i) +"'"+ " with " + "'"+en_word.get(i) +"'"+ " : level = " + lvl.get(i)
                    + " and number of repetitions = " + train.get(i));
            System.out.println();
        }
        Learn_Menu learn = new Learn_Menu();
        learn.main_menu(list);
    }

}

class Just_word extends Print_list_word { // connection with DB
    Just_word() {
        super();
    }

    public Map<Integer, String> word_sel(String list_choice, String which_word) { // intermediate with DB for string
        Map<Integer, String> dict_word = new HashMap<>();
        int i = 0;

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from %s".formatted(list_choice));

            while (resultSet.next()) {
                String word = resultSet.getString(which_word);
                dict_word.put(i, word);
                i+=1;
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error connecting");
            e.printStackTrace();
        }
        return dict_word; }

    public Map<Integer, Integer> int_sel(String list_choice, String which_word) { // for int
        Map<Integer, Integer> dict_int = new HashMap<>();
        int i = 0;

        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from %s".formatted(list_choice));

            while (resultSet.next()) {
                int word = resultSet.getInt(which_word);
                dict_int.put(i, word);
                i+=1;
            }
            connection.close();
        } catch (Exception e) {
            System.out.println("Error connecting");
            e.printStackTrace();
        }
        return dict_int; }
}
