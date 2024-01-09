package Data;

import java.util.*;

public class Learn {
    String list;
    Learn(String list){
        this.list = list;
    }
    public void learn_w(){ // preparation to the quiz to learn words
        Just_word justWord = new Just_word();

        Map<Integer, String> fr_word_l = justWord.word_sel(list, "fr_word");
        Map<Integer, String> en_word_l = justWord.word_sel(list, "en_word");

        Map<Integer, Integer> lvl = justWord.int_sel(list, "level");
        Map<Integer, Integer> train = justWord.int_sel(list, "train");

        ArrayList<Integer> lvl_list = new ArrayList<>();
        for (int i = 0; i < lvl.size(); i++) {
            lvl_list.add(lvl.get(i));
        }
        Learn_algo learn_algo = new Learn_algo(list, fr_word_l, en_word_l, lvl, train, lvl_list);
        learn_algo.learn_algo_center();
    }
}

