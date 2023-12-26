package cl.mcortesr.android.ev1p2


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputLayout
import java.text.NumberFormat
import java.util.Locale
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private lateinit var etCantidadPastel: EditText
    private lateinit var etCantidadCazuela: EditText
    private lateinit var swPropina: Switch
    private lateinit var tvTotalPastel: TextView
    private lateinit var tvTotalCazuela: TextView
    private lateinit var tvTotal: TextView
    private lateinit var tvPropina: TextView
    private lateinit var tvTotalCompra: TextView
    private lateinit var pedido: Pedido
    private val formatoMoneda = NumberFormat.getCurrencyInstance(Locale("es", "CL"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        etCantidadPastel = findViewById(R.id.etCantidadPastel)
        etCantidadCazuela = findViewById(R.id.etCantidadCazuela)
        swPropina = findViewById(R.id.swPropina)
        tvTotalPastel = findViewById(R.id.tvTotalPastel)
        tvTotalCazuela = findViewById(R.id.tvTotalCazuela)
        tvTotal = findViewById(R.id.tvTotal)
        tvPropina = findViewById(R.id.tvPropina)
        tvTotalCompra = findViewById(R.id.tvTotalCompra)

        pedido = Pedido()

        etCantidadPastel.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                actualizarTotales()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        etCantidadCazuela.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                actualizarTotales()
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        swPropina.setOnCheckedChangeListener { _, _ -> actualizarTotales() }

        actualizarTotales()
    }

    private fun actualizarTotales() {
        pedido.cantidadPastel = obtenerCantidad(etCantidadPastel)
        pedido.cantidadCazuela = obtenerCantidad(etCantidadCazuela)

        tvTotalPastel.text = "Total Pastel de Choclo: ${formatoMoneda.format(pedido.calcularSubtotalPastel())}"
        tvTotalCazuela.text = "Total Cazuela: ${formatoMoneda.format(pedido.calcularSubtotalCazuela())}"
        tvTotal.text = "Comida:     ${formatoMoneda.format(pedido.calcularTotalSinPropina())}"

        if (swPropina.isChecked) {
            tvPropina.text = "${formatoMoneda.format(pedido.calcularPropina())}"
            tvTotalCompra.text = "${formatoMoneda.format(pedido.calcularTotalConPropina())}"
        } else {
            tvPropina.text = "${formatoMoneda.format(BigDecimal.ZERO)}"
            tvTotalCompra.text = "${formatoMoneda.format(pedido.calcularTotalSinPropina())}"
        }
    }

    private fun obtenerCantidad(editText: EditText): Int {
        return editText.text.toString().toIntOrNull() ?: 0
    }
}
