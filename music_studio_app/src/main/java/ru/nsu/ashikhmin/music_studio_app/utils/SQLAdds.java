package ru.nsu.ashikhmin.music_studio_app.utils;

public class SQLAdds {
    public static final String SELECT = "SELECT";
    public static final String FROM = "FROM";
    public static final String WHERE = "WHERE";
    public static final String AND = "AND";
    public static final String DISTRIBUTION_SERVICE_TABLE = "distribution_services";
    public static final String VISIT_SCHEDULE_TABLE = "visit_schedule";
    public static final String USER_TABLE = "users";
    public static final String CLIENT_TABLE = "clients";

    public static final String MIN = "MIN";
    public static final String GROUP_BY = "GROUP BY";
    public static final String HAVING = "HAVING";
    public static final String BETWEEN = "BETWEEN";
    public static final String ILIKE = "ILIKE";
    public static final String OR = "OR";
    public static final String ON = "ON";
    public static final String AS = "AS";
    public static final String INNER_JOIN = "INNER JOIN";

    public static void addBarParameter(StringBuilder stringBuilder, Boolean[] isNotFirst,
                                boolean isNotEmpty, boolean isAnd, Double lowestValue,
                                Double highestValue, String columnName, String tableName) {
        if (!isNotEmpty) {
            return;
        }
        if (isNotFirst[0]) {
            stringBuilder.append(" ")
                    .append(SQLAdds.AND);
        } else {
            isNotFirst[0] = true;
        }
        stringBuilder.append(" ")
                .append(tableName)
                .append(columnName);
        if (isAnd) {
            stringBuilder.append(" ")
                    .append(SQLAdds.BETWEEN)
                    .append(" ")
                    .append(lowestValue)
                    .append(" ")
                    .append(SQLAdds.AND)
                    .append(" ")
                    .append(highestValue);
        } else {
            if (lowestValue != null) {
                stringBuilder.append(" >= ")
                        .append(lowestValue);
            } else {
                stringBuilder.append(" <= ")
                        .append(highestValue);
            }
        }
    }

    public static void addInnerJoin(StringBuilder stringBuilder, String expendableTable, String joinableTable,
                                    String expendableColumn, String joinableColumn){
        stringBuilder.append(SQLAdds.INNER_JOIN)
                .append(" ")
                .append(joinableTable)
                .append(" ")
                .append(SQLAdds.ON)
                .append(" (")
                .append(expendableTable)
                .append(".")
                .append(expendableColumn)
                .append(" = ")
                .append(joinableTable)
                .append(".")
                .append(joinableColumn)
                .append(")");
    }
}

