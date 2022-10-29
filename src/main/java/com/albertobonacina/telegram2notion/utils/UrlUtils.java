package com.albertobonacina.telegram2notion.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UrlUtils {

    /* https://stackoverflow.com/a/28269120
     * Return link contained in the input */
    public static String extractUrl(String text) {
        List<String> containedUrls = new ArrayList<>();
        Matcher urlMatcher = Pattern.compile("((https?):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)", Pattern.CASE_INSENSITIVE)
                                    .matcher(text);

        while (urlMatcher.find()) {
            containedUrls.add(text.substring(urlMatcher.start(0), urlMatcher.end(0)));
        }

        return containedUrls.get(0);
    }

    /* https://stackoverflow.com/a/10432620
     * Return a list with all hashtags contained
     *  Input: This is something very #important and #useful
     *  Output: [important, useful] */
    public static List<String> extractHashTags(String text) {
        List<String> containedHashTags = new ArrayList<>();
        Matcher urlMatcher = Pattern.compile("#(\\S+)", Pattern.CASE_INSENSITIVE)
                                    .matcher(text);

        while (urlMatcher.find()) {
            containedHashTags.add(text.substring(urlMatcher.start(0), urlMatcher.end(0)).replace("#",""));
        }

        return containedHashTags;
    }

    public static String getTitleFromWebPage(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc.title();
    }
}
