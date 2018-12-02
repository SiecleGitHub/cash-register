package cashregister

/**
 * Register, die Kasse
 */
class Register {
    private var price = 0
    private var amountGiven = 0
    private var register = emptyMap<Int, Int>()
    private var changePossible = true


    fun setBill(price: Int) {
        this.price = price
    }

    fun setPayment(amountGiven: Int) {
        this.amountGiven = amountGiven
    }

    fun setChange(mutableMap: MutableMap<Int, Int>) {
        this.register = mutableMap
    }

    fun isChangePossible(): Boolean {
        return changePossible
    }

    /*
     * calculateChange enthält den Algorithmus für die Berechnung
     * des Wechselgeldes in Geldstücken.
     */
    fun calculateChange(): Map<Int, Int> {
        var changeValue = amountGiven - price
        val initialChangeValue = changeValue

        //println("changeValue: $changeValue")
        val changeInCoins = mutableMapOf<Int, Int>()
        var valueOfAllCoins = 0

        for ((valueOfCoin, numberOfCoins) in register) {
            val coinsCount = changeValue / valueOfCoin
            if (coinsCount > 0) {
                if (numberOfCoins >= coinsCount) {
                    changeValue -= coinsCount * valueOfCoin
                    changeInCoins[valueOfCoin] = coinsCount
                    valueOfAllCoins += valueOfCoin * coinsCount
                } else if (numberOfCoins > 0) {
                    changeValue -= numberOfCoins * valueOfCoin
                    changeInCoins[valueOfCoin] = numberOfCoins
                    valueOfAllCoins += valueOfCoin * numberOfCoins
                }
            }
        }

        /*
         * Auswertung, ob kein Wechselgeld möglich ist
         */
        if (initialChangeValue > valueOfAllCoins) {
            changePossible = false
        }

        return changeInCoins
    }
}
