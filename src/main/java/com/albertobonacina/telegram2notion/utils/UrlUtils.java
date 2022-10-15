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
     * Returns a list with all links contained in the input */
    public static List<String> extractUrls(String text) {
        List<String> containedUrls = new ArrayList<>();
        Pattern pattern = Pattern.compile("((https?):((//)|(\\\\))+[\\w\\d:#@%/;$()~_?\\+-=\\\\\\.&]*)", Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

        while (urlMatcher.find()) {
            containedUrls.add(text.substring(urlMatcher.start(0), urlMatcher.end(0)));
        }

        return containedUrls;
    }

    /* https://stackoverflow.com/a/10432620
     * Returns a list with all hashtags contained
     *  Input: This is something very #important and #useful
     *  Output: [important, useful] */
    public static List<String> extractHashTags(String text) {
        List<String> containedHashTags = new ArrayList<>();
        Pattern pattern = Pattern.compile("#(\\S+)", Pattern.CASE_INSENSITIVE);
        Matcher urlMatcher = pattern.matcher(text);

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
