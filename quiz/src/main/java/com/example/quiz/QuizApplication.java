package com.example.quiz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;




@CrossOrigin(origins = "http://localhost:3000")

@SpringBootApplication

public class QuizApplication{
    private final int[] playerScores;
    private final double[] categoryExpectedScores;
    private final double[] averageScores;
    private final double bonusFactor;
    private final double penaltyFactor;
    private final double guessCorrectionFactor;
    private final int[] categoryWeights;
    private final int[] levelThresholds;

    public QuizApplication(int[] playerScores) {
        this.playerScores = playerScores;
        this.averageScores = fetchAverageScoresFromDatabase();
        this.categoryExpectedScores = new double[playerScores.length];
        this.bonusFactor = 1.2;
        this.penaltyFactor = 0.8;
        this.guessCorrectionFactor = 0.8;
        this.categoryWeights = new int[]{1, 2, 3};
        this.levelThresholds = new int[]{10, 20};

        for (int i = 0; i < categoryExpectedScores.length; i++) {
            categoryExpectedScores[i] = (double) playerScores[i] * 0.8;
        }
    }

    private double[] fetchAverageScoresFromDatabase() {
        String url = "http://localhost:8083/api/all";
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                JSONArray jsonArray = new JSONArray(response.toString());
                List<Double> categoryAScores = new ArrayList<>();
                List<Double> categoryBScores = new ArrayList<>();
                List<Double> categoryCScores = new ArrayList<>();

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    JSONArray marksCategory = jsonObject.getJSONArray("marksCategory");
                    categoryAScores.add(marksCategory.getDouble(0));
                    categoryBScores.add(marksCategory.getDouble(1));
                    categoryCScores.add(marksCategory.getDouble(2));
                }

                double categoryAAverage = calculateAverage(categoryAScores);
                double categoryBAverage = calculateAverage(categoryBScores);
                double categoryCAverage = calculateAverage(categoryCScores);

                return new double[]{categoryAAverage, categoryBAverage, categoryCAverage};
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new double[]{0, 0, 0}; // Return default values if fetching data fails
    }

    private double calculateAverage(List<Double> scores) {
        double sum = 0;
        for (double score : scores) {
            sum += score;
        }
        return sum / scores.size();
    }

    public double calculateTotalWeightedScore() {
        double totalWeightedScore = 0;
        for (int i = 0; i < playerScores.length; i++) {
            double categoryRelativePerformance = (double) playerScores[i] / averageScores[i];
            double categoryAdjustedScore = playerScores[i] * (categoryRelativePerformance < 1 ? bonusFactor : penaltyFactor);
            double categoryWeightedScore = categoryAdjustedScore * categoryWeights[i];
            double categoryFinalScore = categoryWeightedScore * guessCorrectionFactor;
            totalWeightedScore += categoryFinalScore;
        }
        return totalWeightedScore;
    }

    public String determineLevelOfKnowledge() {
        double totalWeightedScore = calculateTotalWeightedScore();
        if (totalWeightedScore < levelThresholds[0]) {
            return "Beginner";
        } else if (totalWeightedScore < levelThresholds[1]) {
            return "Intermediate";
        } else {
            return "Expert";
        }
    }

    public static void main(String[] args) {

        SpringApplication.run(QuizApplication.class, args);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter A category correct answer count: ");
        int aCategoryScore = scanner.nextInt();
        System.out.print("Enter B category correct answer count: ");
        int bCategoryScore = scanner.nextInt();
        System.out.print("Enter C category correct answer count: ");
        int cCategoryScore = scanner.nextInt();

        QuizApplication quizApplication = new QuizApplication(new int[]{aCategoryScore, bCategoryScore, cCategoryScore});
        System.out.println("Total weighted score: " + quizApplication.calculateTotalWeightedScore());
        System.out.println("Level of knowledge: " + quizApplication.determineLevelOfKnowledge());

        scanner.close();
    }
}
