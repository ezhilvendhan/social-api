package io.vendhan.social.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum StringUtil {
    ;

    public static List<String> getEmailsInText(String text) {
        List<String> emails = new ArrayList<>();
        String regex = "[a-zA-Z0-9_.+-]+@[a-zA-Z0-9-]+\\.[a-zA-Z0-9-.]+";
        final Pattern EMAIL_PATTERN = Pattern.compile(regex);
        Matcher matcher = EMAIL_PATTERN.matcher(text);
        while (matcher.find()) {
            emails.add(matcher.group());
        }
        return emails;
    }
}
