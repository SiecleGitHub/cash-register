package cashregister

/**
 * Haupteintrittspunkt in das Programm
 */
fun main(args: Array<String>) {
    // Warenkörbe, der Preis
    val bills: List<Bill> = listOf(
            Bill(457),
            Bill(228),
            Bill(353))

    // Bezahlungen, der gegebene Betrag in EUR
    val payments: List<Payment> = listOf(
            Payment(600),
            Payment(500),
            Payment(400))

    // Wechselgelder in der Kasse
    val change: List<MutableMap<Int, Int>> = listOf(
            mutableMapOf(Pair(100,5), Pair(50, 5), Pair(20, 5), Pair(10, 5), Pair(5, 5), Pair(2, 5), Pair(1, 5)),
            mutableMapOf(Pair(100,1), Pair(50, 5), Pair(20, 5), Pair(10, 5), Pair(5, 5), Pair(2, 0), Pair(1, 5)),
            mutableMapOf(Pair(100,5), Pair(50, 5), Pair(20, 1), Pair(10, 1), Pair(5, 1), Pair(2, 5), Pair(1, 1))
    )

    val myRegister = Register()
    var changeInCoins: Map<Int, Int>

    /*
     * Schleife zur Simulation der drei Einkäufe
     */
    for (i in bills.indices) {
        myRegister.setBill(bills[i].price)
        myRegister.setPayment(payments[i].payment)
        myRegister.setChange(change[i])
        changeInCoins = myRegister.calculateChange()
        if (myRegister.isChangePossible()) {
            changeInCoins.forEach {
                var valueToBePrinted = ""
                when(it.key) {
                    100 -> valueToBePrinted = "1"
                    50 -> valueToBePrinted = "0.50"
                    20 -> valueToBePrinted = "0.20"
                    10 -> valueToBePrinted = "0.10"
                    5 -> valueToBePrinted = "0.05"
                    2 -> valueToBePrinted = "0.02"
                    1 -> valueToBePrinted = "0.01"
                }

                println("${it.value}x ${valueToBePrinted} €")
            }
        } else {
            println("Kein Wechsel möglich")
        }
        println("")
    }
}
