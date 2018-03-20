package idv.kuma.calcualtor;

public enum TypeParser {

    NORMAL_DAY_OVERTIME,
    REST_DAT_OVERTIME,
    HOLIDAY_OVERTIME,
    UNKNOWN;

    public static TypeParser parseString(String typeString) {
        if (typeString.contains("平日加班換加班費")) {
            return NORMAL_DAY_OVERTIME;
        } else if (typeString.contains("假日加班換加班費")) {
            return HOLIDAY_OVERTIME;
        } else if (typeString.contains("休息日換加班費")) {
            return REST_DAT_OVERTIME;
        } else {
            return UNKNOWN;
        }
    }
}
