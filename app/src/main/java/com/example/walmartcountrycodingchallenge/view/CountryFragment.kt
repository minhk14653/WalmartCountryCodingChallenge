package com.example.walmartcountrycodingchallenge.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walmartcountrycodingchallenge.databinding.FragmentCountryBinding
import com.example.walmartcountrycodingchallenge.countryviewmodel.CountryViewModel
import com.example.walmartcountrycodingchallenge.model.Country
import com.example.walmartcountrycodingchallenge.util.State


class CountryFragment: Fragment() {

    private lateinit var binding: FragmentCountryBinding

    private val countryViewModel by lazy {
        ViewModelProvider(this)[CountryViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCountryBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = CountryListAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                context,
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.retryButton.setOnClickListener {
            countryViewModel.getCountry()
        }
        countryViewModel.isLoading.observe(viewLifecycleOwner) {state->
            when (state) {
                true -> binding.progressCircular.visibility = VISIBLE
                false -> binding.progressCircular.visibility = GONE
            }
        }
        countryViewModel.countryLiveData.observe(viewLifecycleOwner) { state ->
            when (state) {
                is State.ERROR -> {
                    binding.retryButton.visibility = VISIBLE
                }

                is State.LOADING -> {
                    binding.retryButton.visibility = GONE
                }

                is State.SUCCESS<*> -> {
                    binding.retryButton.visibility = GONE
                    (binding.recyclerView.adapter as CountryListAdapter).submitList(state.country as MutableList<Country>?)
                }
            }
        }
        countryViewModel.getCountry()
    }
}