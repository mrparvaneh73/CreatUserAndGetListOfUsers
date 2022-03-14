package com.example.creatuser.ui.showinfo

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import com.example.creatuser.R
import com.example.creatuser.databinding.FragmentShowinfoBinding
import com.example.creatuser.di.App
import com.example.creatuser.ui.users.UserFragmentFactory
import com.example.creatuser.ui.users.UsersFragmentViewModel

class ShowInfoFragment:Fragment(R.layout.fragment_showinfo) {
    lateinit var binding: FragmentShowinfoBinding
    private val args by navArgs<ShowInfoFragmentArgs>()
    private val viewModel by viewModels<ShowInfoViewModel>(factoryProducer = {
        ShowInfoFactory((requireActivity().application as App).serviceLocator.repository)
    })
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentShowinfoBinding.bind(view)

        viewModel.showDetails(args.id).observe(viewLifecycleOwner, Observer{
            binding.firstname.text = it.firstName
            binding.lastname.text = it.lastName
            binding.nationalcode.text = it.nationalCode
        })

    }
}