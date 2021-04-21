open class CrudOperations {
    var myDatabase: MutableList<Food> = mutableListOf<Food>()

    open fun find(foodName: String): Boolean {
        var result = myDatabase.find { f -> f.FoodName == foodName }
        return if (result != null) true else false
    }

    open fun addFood(foodName: String, recipe: String) {
        myDatabase.add(Food(foodName, recipe))
    }

    open fun search(foodName: String): String {
        var food = myDatabase.find { f -> f.FoodName.equals(foodName) }
        if (food != null) {
            return food.Recipe
        } else return "Food not found!"
    }

    open fun deleteFood(foodName: String) {
        var food = myDatabase.find { f -> f.FoodName == foodName }
        myDatabase.remove(food)
    }

    open fun editRecipe(foodName: String, recipe: String) {
        var result = myDatabase.find { f -> f.FoodName == foodName }
        result!!.Recipe = recipe
    }

    open fun listFoods() {
        for (food in myDatabase) {
            println(food.FoodName)
        }
    }

}