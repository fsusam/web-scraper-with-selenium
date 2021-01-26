package com.fet.tutorial.web;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class Injurie {
    public static void main(String[] args) {
        try (final WebClient webClient = new WebClient()) {
            final HtmlPage page = webClient.getPage("https://injuriesandsuspensions.com/");
            System.out.println("page " + page.getTitleText());

            //final String pageAsXml = page.asXml();
            //System.out.println("page as xml " + pageAsXml);

            //final String pageAsText = page.asText();
            //System.out.println("page as xml " + pageAsText);
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
