package Controller.DietLogic;

public interface ServingsCalculator {
    float calculateServings(int age, String foodGroup);
    float getServingsForAgeGroup(String foodGroup);
}


class ServingsCalculatorForAdults implements ServingsCalculator {
    @Override
    public float calculateServings(int age, String foodGroup) {
        if (age >= 19 && age <= 50) {return getServingsForAgeGroup(foodGroup);}
        return  getServingsForAgeGroup2(foodGroup);
    }

    @Override
    public float getServingsForAgeGroup(String foodGroup) {
        return switch (foodGroup) {
            case "Vegetables and Fruits" -> 7;
            case "Grain Products" -> 6;
            case "Milk and Alternatives" -> 3;
            case "Meat and Alternatives" -> 2;
            default -> 0;
        };
    }

    public float getServingsForAgeGroup2(String foodGroup) {
        return switch (foodGroup) {
            case "Vegetables and Fruits" -> 7;
            case "Grain Products" -> 6;
            case "Milk and Alternatives", "Meat and Alternatives" -> 3;
            default -> 0;
        };
    }
}


class ServingsCalculatorForTeens implements ServingsCalculator {
    @Override
    public float calculateServings(int age, String foodGroup) {
        return getServingsForAgeGroup(foodGroup);
    }

    @Override
    public float getServingsForAgeGroup(String foodGroup) {
        return switch (foodGroup) {
            case "Vegetables and Fruits" -> 7;
            case "Grain Products" -> 6;
            case "Milk and Alternatives", "Meat and Alternatives" -> 3;
            default -> 0;
        };
    }
}


class ServingsCalculatorForChildren implements ServingsCalculator {
    @Override
    public float calculateServings(int age, String foodGroup) {
        if (age >= 2 && age <= 3) {return getServingsForAgeGroup(foodGroup);}
        if (age >= 4 && age <= 8) {return getServingsForAgeGroup2(foodGroup);}

        return getServingsForAgeGroup3(foodGroup);
    }

    @Override
    public float getServingsForAgeGroup(String foodGroup) {
        return switch (foodGroup) {
            case "Vegetables and Fruits" -> 7;
            case "Grain Products" -> 6;
            case "Milk and Alternatives" -> 3;
            case "Meat and Alternatives" -> 2;
            default -> 0;
        };
    }

    public float getServingsForAgeGroup2(String foodGroup) {
        return switch (foodGroup) {
            case "Vegetables and Fruits" -> 7;
            case "Grain Products" -> 6;
            case "Milk and Alternatives", "Meat and Alternatives" -> 3;
            default -> 0;
        };
    }

    public float getServingsForAgeGroup3(String foodGroup) {
        return switch (foodGroup) {
            case "Vegetables and Fruits" -> 7;
            case "Grain Products" -> 6;
            case "Milk and Alternatives" -> 3;
            case "Meat and Alternatives" -> 2;
            default -> 0;
        };
    }

}
