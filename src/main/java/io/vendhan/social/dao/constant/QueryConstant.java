package io.vendhan.social.dao.constant;

public enum QueryConstant {
    ;
    public static final String GET_COMMON_FRIENDS;

    static {
        StringBuilder getCommonFriends =
                new StringBuilder("SELECT EMAIL FROM PERSON WHERE ID IN (")
                .append("    (SELECT F1.friend_two FROM FRIENDSHIP F1")
                .append("        LEFT JOIN PERSON ON F1.friend_one = PERSON.id")
                .append("           AND PERSON.email = :emailone )")
                .append("    INTERSECT")
                .append("    (SELECT F2.friend_two FROM FRIENDSHIP F2")
                .append("        LEFT JOIN PERSON ON F2.friend_one = PERSON.id")
                .append("           AND PERSON.email = :emailtwo )")
                .append(") AND EMAIL NOT IN (:emailone, :emailtwo)");
        GET_COMMON_FRIENDS = getCommonFriends.toString();
    }
}
