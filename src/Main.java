import java.util.*;

class Food {
    private String name;
    private List<Integer> ratings;

    public Food(String name) {
        this.name = name;
        this.ratings = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addRating(int rating) {
        if (rating >= 1 && rating <= 5) {  // rating scale: 1-5
            ratings.add(rating);
        } else {
            System.out.println("Invalid rating! Please give between 1 and 5.");
        }
    }

    public double getAverageRating() {
        if (ratings.isEmpty()) return 0.0;
        int sum = 0;
        for (int r : ratings) sum += r;
        return (double) sum / ratings.size();
    }

    public void displayRatings() {
        System.out.println("Food: " + name);
        System.out.println("Ratings: " + ratings);
        System.out.printf("Average Rating: %.2f\n", getAverageRating());
    }
}

class FoodRatingSystem {
    private Map<String, Food> foods;

    public FoodRatingSystem() {
        foods = new HashMap<>();
    }

    public void addFood(String name) {
        if (!foods.containsKey(name)) {
            foods.put(name, new Food(name));
            System.out.println(name + " added to the system.");
        } else {
            System.out.println(name + " already exists.");
        }
    }

    public void rateFood(String name, int rating) {
        Food food = foods.get(name);
        if (food != null) {
            food.addRating(rating);
            System.out.println("Rated " + name + " with " + rating + " stars.");
        } else {
            System.out.println("Food not found! Please add it first.");
        }
    }

    public void showFoodRatings(String name) {
        Food food = foods.get(name);
        if (food != null) {
            food.displayRatings();
        } else {
            System.out.println("Food not found!");
        }
    }

    public void showAllFoods() {
        if (foods.isEmpty()) {
            System.out.println("No foods available.");
        } else {
            for (Food food : foods.values()) {
                food.displayRatings();
                System.out.println("----");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FoodRatingSystem system = new FoodRatingSystem();

        while (true) {
            System.out.println("\n=== Food Rating System ===");
            System.out.println("1. Add Food");
            System.out.println("2. Rate Food");
            System.out.println("3. Show Food Ratings");
            System.out.println("4. Show All Foods");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter food name: ");
                    String foodName = sc.nextLine();
                    system.addFood(foodName);
                    break;

                case 2:
                    System.out.print("Enter food name: ");
                    String foodToRate = sc.nextLine();
                    System.out.print("Enter rating (1-5): ");
                    int rating = sc.nextInt();
                    system.rateFood(foodToRate, rating);
                    break;

                case 3:
                    System.out.print("Enter food name: ");
                    String foodToShow = sc.nextLine();
                    system.showFoodRatings(foodToShow);
                    break;

                case 4:
                    system.showAllFoods();
                    break;

                case 5:
                    System.out.println("Exiting...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
