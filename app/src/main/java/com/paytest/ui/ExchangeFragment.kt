package com.paytest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.paytest.R
import com.paytest.databinding.FragmentExchangeBinding
import com.paytest.utils.BaseFragment
import javax.inject.Inject

class ExchangeFragment @Inject constructor(

) : BaseFragment() {

    private lateinit var binding: FragmentExchangeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exchange, container, false)
        return binding.root
    }
}