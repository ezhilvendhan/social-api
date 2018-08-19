package io.vendhan.social.dao.constant;

public enum QueryConstant {
    ;
    public static final String GET_COMMON_FRIENDS;

    public static final String GET_SUBSCRIBERS;

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

        StringBuilder getSubscribers =
                new StringBuilder("SELECT DISTINCT EMAIL FROM PERSON WHERE ID IN (")
                .append("    (SELECT friend_two FROM FRIENDSHIP")
                .append("        LEFT JOIN PERSON ON FRIENDSHIP.friend_one = PERSON.id")
                .append("        AND PERSON.email = :publisher")
                .append("        WHERE FRIENDSHIP.friend_one NOT IN (")
                .append("            SELECT publisher FROM SUBSCRIPTION")
                .append("                LEFT JOIN STATUS ON SUBSCRIPTION.status = STATUS.id")
                .append("                                    AND STATUS.label <> 'ACTIVE'")
                .append("                WHERE SUBSCRIPTION.subscriber = FRIENDSHIP.friend_two")
                .append("     ))")
                .append("    UNION")
                .append("    (SELECT subscriber FROM SUBSCRIPTION")
                .append("        LEFT JOIN PERSON ON SUBSCRIPTION.publisher = PERSON.id")
                .append("        LEFT JOIN STATUS ON SUBSCRIPTION.status = STATUS.id")
                .append("        WHERE PERSON.email = :publisher")
                .append("        AND STATUS.label = 'ACTIVE')")
                .append(")");
        GET_SUBSCRIBERS = getSubscribers.toString();
    }
}
