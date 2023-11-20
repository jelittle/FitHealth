package Controller.DietLogic;

import DietLogs.NutrientInfo;

import java.util.ArrayList;

public class ActiveNutrients {

    private DietDataManager db = DietDataManager.getInstance();

    private ArrayList<NutrientInfo> ActiveNutrients = new ArrayList<>();

    public ArrayList<NutrientInfo> GetActiveNutrientInfoByIngredientId(int ingredientId){
        ActiveNutrients =db.getNutrientInfoByIngredientId(ingredientId);
        return ActiveNutrients;
    }

    public ArrayList<NutrientInfo> getActiveNutrients() {
        return ActiveNutrients;
    }

}
