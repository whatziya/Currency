package uz.ziyaprof.currency.view.currencies

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import dagger.hilt.android.AndroidEntryPoint
import uz.ziyaprof.currency.R
import uz.ziyaprof.currency.base.BaseFragment
import uz.ziyaprof.currency.databinding.FragmentCurrenciesBinding
import uz.ziyaprof.currency.model.CurrencyUiModel
import uz.ziyaprof.currency.viewmodel.CurrenciesViewModel

@AndroidEntryPoint
class FragmentCurrencies : BaseFragment(R.layout.fragment_currencies) {
    private val binding by viewBinding(FragmentCurrenciesBinding::bind)
    private val viewModel: CurrenciesViewModel by viewModels()
    private lateinit var adapter: CurrencyAdapter

    override fun setup() {
        viewModel.currencies.observe(viewLifecycleOwner) { currencyList ->
            binding.infoDate.text = currencyList[0].date
            setupRecyclerView(currencyList)
        }
        viewModel.getCurrency()
    }

    private fun setupRecyclerView(currencyList: List<CurrencyUiModel>) {
        adapter = CurrencyAdapter(currencyList, onItemClick = { currency ->
            val bundle = Bundle().apply {
                putParcelable("ccy", currency)
                putParcelableArrayList("list", ArrayList(currencyList))
            }
            findNavController().navigate(R.id.action_fragmentCurrencies_to_fragmentCurrencyCalculator, bundle)
        })
        binding.currencyRecyclerView.adapter = adapter
    }
}
