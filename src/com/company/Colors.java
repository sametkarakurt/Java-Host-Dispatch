package com.company;

import java.util.ArrayList;

public class Colors {
    public ArrayList getAllColors() {
        ArrayList<String> renkler = new ArrayList();
        for (int k = 0; k < 256; k++) {
            renkler.add("\u001b[38;5;" + k * 5 + "m ");
        }
        return renkler;
    }
}
