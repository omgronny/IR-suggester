package finders;

import requests.DataSet;
import requests.Request;

import java.util.*;
import similarity.*;
import similarity.Dictionary;

public class Finder {

    public static SortedSet<Request> findPopular(String prefix, SortedSet<Request> set, int count) {

        SortedSet<Request> results = new TreeSet<>(Request.comparator);

        for (Request currentRequest : set) {

            if (currentRequest.getRequest().startsWith(prefix)) {
                results.add(currentRequest);
                count--;
            }

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
            System.out.println("Предлагалось ранее:");
            for (Request r : userResults) {
                System.out.println(r.getRequest() + " " + r.getRelevace());
            }
        }

        if (!results.isEmpty()) {
            System.out.println("\nПопулярные запросы:");
            for (Request r : results) {
                System.out.println(r.getRequest() + " " + r.getRelevace());
            }
            System.out.println();

        } else {
            // Ищем позожие запросы пытаясь исправить опечатки (берем все слова по отдельности и исправляем их и ищем то что получилось)
            if (depth < 2) {

                String[] requestWords = request.split(" ");

                String newRequest = "";

                for (int i = 0; i < requestWords.length; ++i) {

                    newRequest += Dictionary.replaceWord(requestWords[i]);

                    if (i != requestWords.length - 1) {
                        newRequest += " ";
                    }
                }

                printSuggestions(newRequest, depth + 1);

            } else {
                System.out.println("Похожих популярных запросов не найдено");
            }

        }

        DataSet.insertUser(request);
    }

}
