package com.toyblock.toyblockserver.structure.tool;

import java.util.Objects;

public class View {
    public static int view(String view) {
        if(Objects.equals(view, "S")) {
            return 0;
        }
        if(Objects.equals(view, "W")) {
            return 270;
        }
        if(Objects.equals(view, "N")) {
            return 180;
        }
        if(Objects.equals(view, "E")) {
            return 90;
        }
        return 0;
    }
}
