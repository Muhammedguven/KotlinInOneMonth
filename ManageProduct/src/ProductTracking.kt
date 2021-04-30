open class ProductTracking(companyName:String) {
    open fun searchProduct (id:Int, database: DatabaseObject): Product? {
        return database.allProducts.find { it.id == id }
    }

    open fun addProduct(id:Int, numOfProducts:Int, database: DatabaseObject){
        var result = searchProduct(id,database)
        if (result != null)
            result.stock += numOfProducts
    }

    open fun removeProduct(id:Int, numOfProducts:Int, database: DatabaseObject){
        var result = searchProduct(id,database)
        if (result != null)
            result.stock -= numOfProducts
    }
}