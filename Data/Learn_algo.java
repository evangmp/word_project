package Data;

import java.sql.*;
import java.util.*;

public class Learn_algo {
    String url; String list; Map<Integer, Integer> train; Map<Integer, String> fr_word_l;
    Map<Integer, String> en_word_l; Map<Integer, Integer> lvl; ArrayList<Integer> lvl_list;
    Learn_algo(String list, Map<Integer, String> fr_word_l, Map<Integer, String> en_word_l, Map<Integer, Integer> lvl,
               Map<Integer, Integer> train, ArrayList<Integer> lvl_list) {
        this.train = train; this.fr_word_l = fr_word_l; this.lvl = lvl;
        this.en_word_l = en_word_l; this.lvl_list = lvl_list; this.list = list;
        this.url = "jdbc:sqlite:C:\\Users\\evang\\IdeaProjects\\testencore\\database.db";
    }
    public Learn_algo() {  }

    public void learn_algo_center() {
        Map<Integer, Integer> sorted = new HashMap<>();
        Map<Integer, Integer> copy = new HashMap<>();
        Map<Integer, Integer> lvl_sorted = new HashMap<>();
        DB_com db_com = new DB_com(list, url);

        int min_train = min_or_max(0, train, copy); int max_train = min_or_max(1, train, copy);
        int gap = max_train - min_train;

        if (gap > 3) {
            while (gap > 3 || sorted.size() > 11) {
                Map<Integer, Integer> first = sort(train, sorted);
                for (int m = 0; m < train.size()+sorted.size(); m++) {
                    if (Objects.equals(train.get(m), first.get(m))) {
                        if (Objects.equals(first.get(m), min_train)) {
                            sorted.put(m, train.get(m)); lvl_sorted.put(m, lvl.get(m));
                            train.remove(m, train.get(m)); lvl.remove(m, lvl.get(m));
                        } }
                }
                min_train = min_or_max(0, train, sorted); max_train = min_or_max(1, train, sorted);
                gap = max_train - min_train;
            }

        }
        int comp = 0;
        if(sorted.size() < 11) {
            while (sorted.size() < 11) {
                Map<Integer, Integer> lvl_ind = sort(lvl, lvl_sorted);
                for (int i = 0; i < lvl.size()+lvl_sorted.size(); i++) {
                    if (!Objects.equals(null, lvl_ind.get(i))) {
                        if (Objects.equals(lvl.get(i), lvl_ind.get(i))) {
                            sorted.put(i, lvl.get(i));
                            lvl_sorted.put(i, lvl.get(i));
                            lvl.remove(i, lvl.get(i));
                        }  }
                    if (sorted.size() > 10) {break;}
                }
                comp +=1;
                if (comp>50) {
                    break;
                }
            }
        }
        String choice;
        for (int i = 0; i < fr_word_l.size(); i++) {
            Scanner scanner = new Scanner(System.in);
            if(!Objects.equals(null, sorted.get(i))) {
                if (lvl_list.get(i)%2 != 1) {
                    System.out.println("Give english trad of : " + fr_word_l.get(i));
                    choice = scanner.nextLine();
                    if (Objects.equals(choice, en_word_l.get(i))) {
                        System.out.println("GG"); System.out.println();
                        db_com.dbc_lvl("level+1", fr_word_l.get(i));
                    }
                    else {
                        System.out.println("Nope\nCorrection : "+ en_word_l.get(i)); System.out.println();
                        db_com.dbc_lvl("level-1", fr_word_l.get(i));}

                }
                else {
                    System.out.println("Give french trad of : " + en_word_l.get(i));
                    choice = scanner.nextLine();
                    if (Objects.equals(choice, fr_word_l.get(i))) {
                        System.out.println("GG"); System.out.println();
                        db_com.dbc_lvl("level+1", fr_word_l.get(i));
                    }
                    else {
                        System.out.println("Nope \nCorrection : "+ fr_word_l.get(i)); System.out.println();
                        db_com.dbc_lvl("level-1", fr_word_l.get(i));}
                }
            }
        }
    }

    public Map<Integer, Integer> sort(Map<Integer, Integer> base, Map<Integer, Integer> sorted) {
        Map<Integer, Integer> base_sorted = new HashMap<>(); int comp = 0; int min = 1000;
        for (int i = 0; i < base.size()+sorted.size(); i++) {
            if(!Objects.equals(base.get(i), null)) {
                if (base.get(i) < min) {
                    min = base.get(i);
                }}
        }
        Map<Integer, Integer> base_rep1 = new HashMap<>(); int comp_2 = 0;
        for (int i = 0; i < base.size()+sorted.size(); i++) {
            if (!Objects.equals(base.get(i), null)) {
                if (base.get(i) == min) {
                    base_rep1.put(comp_2, i);
                    comp_2 +=1;
                }}} // mix the two "for" loops?
        for (int i = 0; i < base.size()+sorted.size(); i++) {
            if (!Objects.equals(base.get(i), null)){
                if (base.get(i) == min) {
                    base_sorted.put(base_rep1.get(comp), base.get(i));
                    comp += 1;
                }}
        } return base_sorted;
    }

    public int min_or_max(int choice, Map<Integer, Integer> base, Map<Integer, Integer> sorted ) {
        int min_or_max = 0;
        switch(choice) {
            case (0): // test for lowest value
                min_or_max= 1000;
                for (int i = 0; i < base.size()+sorted.size(); i++) {
                    if (!Objects.equals(null, base.get(i))) {
                        if (base.get(i) < min_or_max) {
                            min_or_max = base.get(i); } } }
                if (min_or_max<0) { min_or_max = -min_or_max;}
                break;
            case (1): // test for highest value
                min_or_max = -500;
                for (int i = 0; i<base.size()+sorted.size(); i++) {
                    if(!Objects.equals(null, base.get(i))) {
                        if (base.get(i) > min_or_max) {
                            min_or_max = base.get(i); } } }
                break;
        }
        return min_or_max; }
}

class DB_com extends Learn_algo{
    String url; String list;
    DB_com(String list, String url) {
        super();
        this.list = list;
        this.url = url;
    }
    public void dbc_lvl(String change, String fr_word) { // better way : an integer and if test or the string chain?
        try {
            Class.forName("org.sqlite.JDBC");
            Connection connection = DriverManager.getConnection(url);

            String query = "update %s set level=%s, train=train+1 where fr_word=?".formatted(list, change);

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, fr_word);
            preparedStatement.executeUpdate();

            connection.close();
        } catch (Exception e) {
            System.out.println("Error connecting");
            e.printStackTrace();
        }
    }
}
