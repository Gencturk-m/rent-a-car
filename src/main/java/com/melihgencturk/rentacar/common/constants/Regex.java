package com.melihgencturk.rentacar.common.constants;

public class Regex {
    public final static String TurkiyeCarPlate = "^(0[1-9]|[1-7][0-9]|8[0-1])(([A-PR-VYZ]{1})(?!0{4,5}$)\\d{4,5}|([A-PR-VYZ]{2})(?!0{3,4}$)\\d{3,4}|([A-PR-VYZ]{3})(?!0{2,3}$)\\d{2,3})$";
    public final static String TurkiyeCarPlateWhiteSpaces = "^(0[1-9]|[1-7][0-9]|8[0-1])\\s(([A-PR-VYZ]{1})\\s(?!0{4,5}$)\\d{4,5}|([A-PR-VYZ]{2})\\s(?!0{3,4}$)\\d{3,4}|([A-PR-VYZ]{3})\\s(?!0{2,3}$)\\d{2,3})$";
}
