package uz.ziyaprof.currency.view.currencies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import coil.load
import uz.ziyaprof.currency.R
import uz.ziyaprof.currency.databinding.ItemCurrencyBinding
import uz.ziyaprof.currency.model.CurrencyUiModel

class CurrencyAdapter(
    private val currencies: List<CurrencyUiModel>,
    private val onItemClick: (CurrencyUiModel) -> Unit
) : RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CurrencyViewHolder {
        val binding = ItemCurrencyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CurrencyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CurrencyViewHolder, position: Int) {
        holder.bind(currencies[position], onItemClick)
    }

    override fun getItemCount(): Int = currencies.size

    class CurrencyViewHolder(private val binding: ItemCurrencyBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(currency: CurrencyUiModel,onItemClick: (CurrencyUiModel) -> Unit) {
            with(binding) {
                root.setOnClickListener {
                    onItemClick(currency)
                }
                ccy.text = currency.ccy
                ccyName.text = currency.ccyNmEN
                rate.text = currency.rate
                val drawableRight = if (currency.diff[0] != '-') {
                    ContextCompat.getDrawable(itemView.context, R.drawable.ic_increase)
                } else {
                    ContextCompat.getDrawable(itemView.context, R.drawable.ic_decrease)
                }
                diff.setCompoundDrawablesWithIntrinsicBounds(null, null, drawableRight, null)
                diff.text = currency.diff
                countryIcon.load("https://flagcdn.com/w40/${currency.ccy.substring(0, 2).lowercase()}.png")
            }
        }
    }
}
