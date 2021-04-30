import java.lang.Exception

fun main(){
    var db = DatabaseObject()
    var menu = MenuFunctions()
    var customer1 = Customer("Muhammed Güven")
    var manager = ProductTracking("X Company")

    println("Who are you?\n 1- Customer \n 2- Manager")
    var request = readLine()
    var isLogin = true
    while (isLogin) {
        when (request) {
            "1" -> {
                println("Welcome \n" +
                        " 1-Buy Product \n" +
                        " 2-Give Back Product \n" +
                        " 3-Show All Products \n" +
                        " 4-Search Product \n" +
                        " 5-Quit")
                println("What you want to do?")
                var request2 = readLine()
                when (request2) {
                    "1" -> {
                        println("Product Name : ")
                        var productName = readLine()
                        println("How many : ")
                        var numOfProduct = readLine()
                        try {
                            if (productName != null && numOfProduct != null) {
                                customer1.buyProduct(productName, db, numOfProduct.toInt())
                            }
                        } catch (e: Exception) {
                            println(e)
                        }
                    }
                    "2" -> {
                        println("Product Name: ")
                        var productName = readLine()
                        println("How many : ")
                        var numOfProduct = readLine()
                        try {
                            if (productName != null && numOfProduct != null) {
                                customer1.giveBackProduct(productName, db, numOfProduct.toInt())
                            }
                        } catch (e: Exception) {
                            println(e)
                        }
                    }
                    "3" -> menu.showProducts(db)
                    "4" -> {
                        println("Product Name: ")
                        var product = readLine()
                        if (product != null) {
                            menu.showSpecialProduct(product, db)
                        }
                    }
                    "5" ->{
                        println("Bye bye!!")
                        isLogin = false
                    }
                }
            }

            "2" ->{
                println("Welcome \n" +
                        " 1-Remove Product \n" +
                        " 2-Add Product\n" +
                        " 3-Show All Products \n" +
                        " 4-Search Product \n" +
                        " 5-Quit")
                println("What you want to do?")
                var request2 = readLine()
                when (request2) {
                    "1" -> {
                        println("Product Id : ")
                        var productId = readLine()
                        println("How many : ")
                        var numOfProduct = readLine()
                        try {
                            if (productId != null && numOfProduct != null) {
                                manager.removeProduct(productId.toInt(), numOfProduct.toInt(), db)
                            }
                        } catch (e: Exception) {
                            println(e)
                        }
                    }
                    "2" -> {
                        println("Product Id: ")
                        var productId = readLine()
                        println("How many : ")
                        var numOfProduct = readLine()
                        try {
                            if (productId != null && numOfProduct != null) {
                                manager.addProduct(productId.toInt(), numOfProduct.toInt(),db)
                            }
                        } catch (e: Exception) {
                            println(e)
                        }
                    }
                    "3" -> menu.showProducts(db)
                    "4" -> {
                        println("Product Name: ")
                        var product = readLine()
                        if (product != null) {
                            menu.showSpecialProduct(product, db)
                        }
                    }
                    "5" ->{
                        println("Bye bye!!")
                        isLogin = false
                    }
                }
            }
        }
    }
}