package ru.nsu.ashikhmin.music_studio_app.utils;

import java.util.List;

public class SQLAdds {
    public static final String SELECT = "SELECT";
    public static final String FROM = "FROM";
    public static final String WHERE = "WHERE";
    public static final String AND = "AND";
    public static final String MIN = "MIN";
    public static final String GROUP_BY = "GROUP BY";
    public static final String ORDER_BY = "ORDER BY";
    public static final String HAVING = "HAVING";
    public static final String BETWEEN = "BETWEEN";
    public static final String ON = "ON";
    public static final String AS = "AS";
    public static final String INNER_JOIN = "INNER JOIN";
    public static final String RIGHT_OUTER_JOIN = "RIGHT OUTER JOIN";
    public static final String UNION = "UNION";

    public static final String DISTRIBUTION_SERVICE_TABLE = "distribution_services";
    public static final String VISIT_SCHEDULE_TABLE = "visit_schedule";
    public static final String USER_TABLE = "users";
    public static final String CLIENT_TABLE = "clients";
    public static final String ARTIST_PAGES_TABLE = "artist_pages";
    public static final String ARTIST_TABLE = "artists";
    public static final String GROUP_TABLE = "groups";
    public static final String EVENT_TABLE = "events";
    public static final String ARTISTS_AND_GROUPS_ON_EVENT_TABLE = "artists_and_groups_on_event";

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
                .append(".")
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

    public static void addJoin(StringBuilder stringBuilder, String joinType, String expendableTable,
                               String joinableTable, String expendableColumn, String joinableColumn){
        stringBuilder.append(joinType)
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
                .append(") ");
    }

    public static void addSelectLine(StringBuilder stringBuilder, List<String> fields){
        stringBuilder.append(SQLAdds.SELECT)
                .append(" ");
        fields.forEach(x-> stringBuilder.append(x)
                .append(","));
        stringBuilder.deleteCharAt(stringBuilder.length()-1)
                .append(" ");
    }

    public static void addFromLine(StringBuilder stringBuilder, List<String> tables){
        stringBuilder.append(SQLAdds.FROM)
                .append(" ");
        tables.forEach(x-> stringBuilder.append(x)
                .append(","));
        stringBuilder.deleteCharAt(stringBuilder.length()-1)
                .append(" ");
    }

    public static void addGroupBy(StringBuilder stringBuilder, List<String> fields){
        stringBuilder
                .append(SQLAdds.GROUP_BY)
                .append(" ");
        fields.forEach(x-> stringBuilder.append(x)
                .append(","));
        stringBuilder.deleteCharAt(stringBuilder.length()-1)
                .append(" ");
    }

    public static void addAggregationFunction(StringBuilder stringBuilder, String aggregateFunction, String table,
                                              String column, String rename){
        stringBuilder.append(" ")
                .append(aggregateFunction)
                .append("(")
                .append(table)
                .append(".")
                .append(column)
                .append(") ")
                .append(SQLAdds.AS)
                .append(" \"")
                .append(rename)
                .append("\" ");
    }

    public static String refactorToColumnName(String name){
        return null;
    }
}

