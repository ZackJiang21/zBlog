package com.zack.zblog.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BlogUtil {
    private static final Pattern PATTERN = Pattern.compile("[^!]\\[\\]\\([\\s\\S]*\\)");

    private static final int COMMENT_LENGTH = 300;

    public static String getComment(String content) {
        Matcher matcher = PATTERN.matcher(content);
        if (matcher.find()) {
            String comment = matcher.group(0);
            comment = comment.substring(3, comment.length() - 1);
            if (comment.length() > COMMENT_LENGTH) {
                comment = comment.substring(0, COMMENT_LENGTH);
                comment = comment + "...";
            }
            return comment;
        }
        return "";
    }

}