package uz.ziyaprof.currency.view.currency_calculator

import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.widget.addTextChangedListener
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import uz.ziyaprof.currency.R
import uz.ziyaprof.currency.base.BaseFragment
import uz.ziyaprof.currency.databinding.FragmentCurrencyCalculatorBinding
import uz.ziyaprof.currency.model.CurrencyUiModel
import java.math.BigDecimal
import java.math.RoundingMode

class FragmentCurrencyCalculator : BaseFragment(R.layout.fragment_currency_calculator) {

    private val binding by viewBinding(FragmentCurrencyCalculatorBinding::bind)

    private var isUpdating: Boolean = false

    override fun setup() {
        val ccy = arguments?.getParcelable<CurrencyUiModel>("ccy")!!
        val list = arguments?.getParcelableArrayList<CurrencyUiModel>("list")!!
        setupInfo(ccy, list[0], list)
        setupListeners(ccy, list)
    }

    private fun setupInfo(ccyCurr: CurrencyUiModel, ccyOpp: CurrencyUiModel, list: List<CurrencyUiModel>) = with(binding) {
        val currencyCodes = list.map { it.ccy }

        val adapter = ArrayAdapter(requireContext(), R.layout.spinner_dropdown_item, currencyCodes) // Use spinner_dropdown_item.xml here
        adapter.setDropDownViewResource(R.layout.spinner_item) // Use spinner_item.xml for dropdown view

        destinationCurrencySpinner.adapter = adapter

        val position = currencyCodes.indexOf(ccyOpp.ccy)
        destinationCurrencySpinner.setSelection(position)

        sourceCountryIcon.load("https://flagcdn.com/w40/${ccyCurr.ccy.substring(0, 2).lowercase()}.png")
        sourceCurrencyCode.text = ccyCurr.ccy
        destinationCountryIcon.load("https://flagcdn.com/w40/${ccyOpp.ccy.substring(0, 2).lowercase()}.png")

        val initialResult = ccyCurr.rate.toFloat() / ccyOpp.rate.toFloat() * 1000
        resultOutput.hint = initialResult.toString()
        resultLabel.text = "1 ${ccyCurr.ccy} = ${ccyCurr.rate.toFloat() / ccyOpp.rate.toFloat()} ${ccyOpp.ccy}"
    }

    override fun clicks() = with(binding) {
        backButton.setOnClickListener {
            findNavController().navigate(R.id.action_fragmentCurrencyCalculator_to_fragmentCurrencies)
        }
    }

    private fun setupListeners(ccy: CurrencyUiModel, list: List<CurrencyUiModel>) = with(binding) {
        quantityInput.addTextChangedListener {
            if (!isUpdating) {
                isUpdating = true
                updateResult(ccy, list[destinationCurrencySpinner.selectedItemPosition], it.toString())
                isUpdating = false
            }
        }

        resultOutput.addTextChangedListener {
            if (!isUpdating) {
                isUpdating = true
                updateQuantity(ccy, list[destinationCurrencySpinner.selectedItemPosition], it.toString())
                isUpdating = false
            }
        }

        destinationCurrencySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedCurrency = list[position]

                destinationCountryIcon.load("https://flagcdn.com/w40/${selectedCurrency.ccy.substring(0, 2).lowercase()}.png")

                updateResult(ccy, selectedCurrency, quantityInput.text.toString())

                val conversionRate = ccy.rate.toFloat() / selectedCurrency.rate.toFloat()
                resultLabel.text = "1 ${ccy.ccy} = $conversionRate ${selectedCurrency.ccy}"
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Optional: Handle the case when nothing is selected
            }
        }
    }

    private fun updateResult(ccy: CurrencyUiModel, ccyOpp: CurrencyUiModel, quantityText: String) = with(binding) {
        if (quantityText.isEmpty()) {
            resultOutput.setText("")
            resultOutput.hint = roundToTwoDecimalPlaces(ccy.rate.toFloat() / ccyOpp.rate.toFloat() * 1000).toString()
        } else {
            val quantity = quantityText.toFloatOrNull() ?: 0f
            val conversionResult = ccy.rate.toFloat() / ccyOpp.rate.toFloat() * quantity
            resultOutput.setText(roundToTwoDecimalPlaces(conversionResult).toString())
        }
    }

    private fun updateQuantity(ccy: CurrencyUiModel, ccyOpp: CurrencyUiModel, resultText: String) = with(binding) {
        if (resultText.isEmpty()) {
            quantityInput.setText("")
            quantityInput.hint = "1000.00"
        } else {
            val resultValue = resultText.toFloatOrNull() ?: 0f
            val quantityValue = resultValue * ccyOpp.rate.toFloat() / ccy.rate.toFloat()
            quantityInput.setText(roundToTwoDecimalPlaces(quantityValue).toString())
        }
    }

    private fun roundToTwoDecimalPlaces(value: Float): BigDecimal {
        return BigDecimal(value.toString()).setScale(2, RoundingMode.HALF_UP)
    }
}
