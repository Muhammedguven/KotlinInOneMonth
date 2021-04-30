data class Customer(var Name:String) {

    fun buyProduct(productName: String, database: DatabaseObject, numOfProducts:Int){
        var product = database.allProducts.find { it.name==productName }
        if (product != null)
            product.stock -= numOfProducts
    }
    fun giveBackProduct(productName: String, database: DatabaseObject, numOfProducts: Int){
        var result = database.allProducts.find { it.name == productName }
        if (result != null)
            result.stock += numOfProducts
    }
}