package us.mattgreen;

import java.util.ArrayList;
import java.util.List;

public class Cookbook {

    // Hold all the meals that are read in from the file
    // private Meal[] meals = new Meal[100];
    private List<Meal> meals = new ArrayList<Meal>();
    
    // Hold the next (empty) index in the array
    private int i = 0;
    
    // Variables used for incrementing counts and amounts for control break
    private int totalCalories = 0;
    private int minCalories = 0;
    private int maxCalories = 0;
    private int itemCount = 0;
    private double meanCalories = 0.0;

    public void addElementWithStrings(String mealTypeStr, String mealNameStr, String caloriesStr) {
        MealType mealType;

            // Find the correct enum using a switch? Or use .fromValue() instead?
            switch (mealTypeStr) {
                case "Breakfast":
                    mealType = MealType.BREAKFAST;
                    break;
                case "Lunch":
                    mealType = MealType.LUNCH;
                    break;
                case "Dinner":
                    mealType = MealType.DINNER;
                    break;
                case "Dessert":
                    mealType = MealType.DESSERT;
                    break;
                default:
                    mealType = MealType.DINNER;
                    System.out.println("Meal Creation Error: Invalid Meal Type " + mealTypeStr + ", defaulted to Dinner.");
            }

            int calories;

            if (caloriesStr.matches("-?\\d+(\\.\\d+)?")) {
                calories = Integer.parseInt(caloriesStr);
            } else {
                calories = 100;
                System.out.println("Meal Creation Error: Invalid Calories " + caloriesStr + ", defaulted to 100.");
            }  
            meals.add( new Meal(mealType, mealNameStr, calories));    
    }

    public void addElementWithStrings2(String mealTypeStr, String mealNameStr, String caloriesStr) {
        MealType mealType;

            // Find the correct enum using a switch? Or use .fromValue() instead?
            switch (mealTypeStr) {
                case "Breakfast":
                    mealType = MealType.BREAKFAST;
                    break;
                case "Lunch":
                    mealType = MealType.LUNCH;
                    break;
                case "Dinner":
                    mealType = MealType.DINNER;
                    break;
                case "Dessert":
                    mealType = MealType.DESSERT;
                    break;
                default:
                    mealType = MealType.DINNER;
                    System.out.println("Meal Creation Error: Invalid Meal Type " + mealTypeStr + ", defaulted to Dinner.");
            }

            int calories;

            if (caloriesStr.matches("-?\\d+(\\.\\d+)?")) {
                calories = Integer.parseInt(caloriesStr);
            } else {
                calories = 100;
                System.out.println("Meal Creation Error: Invalid Calories " + caloriesStr + ", defaulted to 100.");
            }  

            // determine location to add new object
            int index = 0;
            if (meals.size() > 0) {
                boolean endLoop = false;
                while (!endLoop) {
                    if (index == meals.size()) {
                        endLoop = true;
                    } else { if (calories < meals.get(index).getCalories()) {
                        endLoop = true;
                    } else {
                        index++;
                    }
                    }   
                }
            }
            // add new object and increment counts and totals
            meals.add(index, new Meal(mealType, mealNameStr, calories));
            totalCalories += calories;
            if (minCalories ==0 || calories < minCalories) {
                minCalories = calories;
            }
            if (maxCalories ==0 || calories > maxCalories) {
                maxCalories = calories;
            }
            itemCount++;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void printAllMeals() {
        for (Meal item : meals) {
            if (item != null) {
                System.out.println(item);
            }
        }
    }

    public void printMealsByType(MealType mealType) {
        for (Meal item : meals) {
            if (item != null && item.getMealType() == mealType) {
                System.out.println(item);
            }
        }
    }

    public void printByNameSearch(String s) {
        // Maybe add a message if no match found?
        for (Meal item : meals) {
            // Maybe make this case-insensitive?
            if (item != null && item.getItem().indexOf(s) >= 0) {
                System.out.println(item);
            }
        }
    }
    
    public void controlBreakHeader() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%-15s", "Meal Type"));
        str.append(String.format("Total\t"));
        str.append(String.format("Mean\t\t"));
        str.append(String.format("Min\t"));
        str.append(String.format("Max\t"));
        str.append(String.format("Median"));
        System.out.println(str);
    }
    
    public void controlBreakLine(String prevMealType) {
        StringBuilder str = new StringBuilder();
        str.append(String.format("%-15s", prevMealType));
        str.append(totalCalories);
        str.append("\t");
        meanCalories = (double) totalCalories/itemCount;
        str.append(String.format("%.4f", meanCalories));
        str.append("\t");
        str.append(minCalories);
        str.append("\t");
        str.append(maxCalories);
        str.append("\t");
        str.append(meals.get(itemCount/2).getCalories());
        System.out.println(str);
    }

}
