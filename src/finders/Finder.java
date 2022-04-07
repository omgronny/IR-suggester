package finders;

import requests.DataSet;
import requests.Request;

import java.util.*;
import similarity.*;
import similarity.Dictionary;

public class Finder {

    private static SortedSet<Request> findPopular(String prefix, SortedSet<Request> set, int count) {

        int newCount = count + 5;

        SortedSet<Request> found = new TreeSet<>(Request.comparator);

        for (Request currentRequest : set) {
            if (currentRequest.getRequest().startsWith(prefix)) {
                String req = currentRequest.getRequest();
                found.add(new Request(req, 1 - JaccardSimilarity.distance(prefix, req)));
                newCount--;
            }
            if (newCount <= 0) {
                break;
            }
        }

        SortedSet<Request> results = new TreeSet<>(Request.comparator);
        for (Request currentRequest : found) {

            results.add(currentRequest);
            count--;

            if (count <= 0) {
                break;
            }
        }

        return results;
    }

    public static void printSuggestions(String request, int depth) {

        SortedSet<Request> results = findPopular(request, DataSet.getRequests(), 5);
        SortedSet<Request> userResults = findPopular(request, DataSet.getUserRequests(), 3);

        if (!userResults.isEmpty() && depth == 0) {
            System.out.println("Вы вводили ранее:");
            for (Request r : userResults) {
                System.out.println(r.getRequest());
            }
        }

        if (!results.isEmpty()) {

            System.out.println("\nПопулярные запросы:");
            for (Request r : results) {
                System.out.println(r.getRequest());
            }
            System.out.println();

        } else {
            // Ищем позожие запросы пытаясь исправить опечатки (берем все слова по отдельности и исправляем их и ищем то что получилось)
            if (depth < 2) {
                printSuggestions(createGoodRequest(request), ++depth);
            } else {
                System.out.println("Похожих популярных запросов не найдено");
            }
        }

        DataSet.insertUser(request);
    }

    private static String createGoodRequest(String request) {

        String[] requestWords = request.split(" ");

        String newRequest = "";

        for (int i = 0; i < requestWords.length; ++i) {

            newRequest += Dictionary.replaceWord(requestWords[i]);

            if (i != requestWords.length - 1) {
                newRequest += " ";
            }

        }

        return newRequest;

    }

}
