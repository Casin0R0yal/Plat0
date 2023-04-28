package Monopoly;

import java.util.ArrayList;

interface action {
    void perform(board b, ArrayList<player> p, int i);
}

public class box {
    String name;
    type type;
    action action;

    public box(type Type, String Name, action Action) {
        type = Type;
        name = Name;
        action = Action;
    }
    public int morgage(Boolean fake) {return 0; }
    // public Boolean isMortgaged() { return false; }
}