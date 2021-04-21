fun main() {
    var cruds = CrudOperations()
    while (true) {
        println("----------MENU----------")
        println("1-ADD FOOD")
        println("2-SEARCH FOOD")
        println("3-DELETE FOOD")
        println("4-EDIT RECIPE")
        println("5-LIST FOODS")
        println("6-QUIT")
        println("------------------------")
        val choice = readLine()
        var fname: String
        var recipe: String
        when (choice!!.toInt()) {
            1 -> {
                println("Write food name : ")
                fname = readLine().toString()
                println("Write the recipe : ")
                recipe = readLine().toString()
                cruds.addFood(fname, recipe)
            }
            2 -> {
                println("Write food name : ")
                fname = readLine().toString()
                var result = cruds.search(fname)
                println("RECIPE : $result")
            }
            3 -> {
                println("Write food name : ")
                fname = readLine().toString()
                var result = cruds.find(fname)
                if (result) {
                    cruds.deleteFood(fname)
                    println("Fodd is deleted successfully")
                } else println("Food has not found!")
            }
            4 -> {
                println("Write food name : ")
                fname = readLine().toString()
                var result = cruds.find(fname)
                if (result) {
                    println("Food has found write new recipe : ")
                    recipe = readLine().toString()
                    cruds.editRecipe(fname, recipe)
                } else println("Food has not found!")
            }
            5 -> {
                println("------FOODS-------")
                cruds.listFoods()
                println("------------------")
            }
            6 -> {
                println("Bye bye!!")
                break
            }
        }
    }
}



