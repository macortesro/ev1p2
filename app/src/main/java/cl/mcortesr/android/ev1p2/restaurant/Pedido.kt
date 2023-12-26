
package cl.mcortesr.android.ev1p2

import java.math.BigDecimal

class Pedido {
    var cantidadPastel: Int = 0
    var cantidadCazuela: Int = 0

    fun calcularSubtotalPastel(): BigDecimal {
        val precioPastel = BigDecimal(12000)
        return BigDecimal(cantidadPastel).multiply(precioPastel)
    }

    fun calcularSubtotalCazuela(): BigDecimal {
        val precioCazuela = BigDecimal(10000)
        return BigDecimal(cantidadCazuela).multiply(precioCazuela)
    }

    fun calcularTotalSinPropina(): BigDecimal {
        return calcularSubtotalPastel() + calcularSubtotalCazuela()
    }

    fun calcularPropina(): BigDecimal {
        val porcentajePropina = BigDecimal(0.1)
        return calcularTotalSinPropina().multiply(porcentajePropina)
    }

    fun calcularTotalConPropina(): BigDecimal {
        return calcularTotalSinPropina() + calcularPropina()
    }
}
