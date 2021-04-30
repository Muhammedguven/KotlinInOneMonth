open class DatabaseObject {
    var allProducts: MutableList<Product> = mutableListOf<Product>()

    init {
        allProducts.add(Product(id=1, name="Asus Notebook", category = "Computers", stock = 50))
        allProducts.add(Product(id=2, name="Monster Notebook", category = "Computers", stock = 30))
        allProducts.add(Product(id=3, name="Lenovo Notebook", category = "Computers", stock = 20))
    }
}