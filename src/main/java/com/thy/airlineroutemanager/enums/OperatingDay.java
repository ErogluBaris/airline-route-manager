package com.thy.airlineroutemanager.enums;

public enum OperatingDay {
    MON,
    TUE,
    WED,
    THU,
    FRI,
    SAT,
    SUN;

    public static OperatingDay valueOf(int day) {
        return OperatingDay.values()[day];
    }
}
