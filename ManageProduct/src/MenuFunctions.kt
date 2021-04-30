open class MenuFunctions{
    open fun showProducts(database: DatabaseObject){
        for (product in database.allProducts){
            println("Product Name : ${product.name} Product Category : ${product.category} Product Stock : ${product.stock}")
        }
    }
    open fun showSpecialProduct(productName:String, database: DatabaseObject){
        var result = database.allProducts.find { it.name == productName }
        if (result != null) println("${result.name} ${result.stock}")
    }
}