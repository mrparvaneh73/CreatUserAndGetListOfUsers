package com.example.creatuser.ui.creataccount

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.creatuser.R
import com.example.creatuser.data.local.model.User
import com.example.creatuser.data.remote.model.CreateUser
import com.example.creatuser.databinding.FragmentAcountBinding
import com.example.creatuser.di.App
import com.example.creatuser.ui.users.UserFragmentFactory
import com.example.creatuser.ui.users.UsersFragmentViewModel

class AccountFragment:Fragment(R.layout.fragment_acount) {
    private val viewModelHome by viewModels<AccountFragmentViewModel>(factoryProducer = {
        AccountFragmentFactory((requireActivity().application as App).serviceLocator.repository)
    })

    var hobbies:MutableList<String> = mutableListOf()
    lateinit var binding:FragmentAcountBinding
    val navController by lazy {
        findNavController()
    }
private lateinit var user: CreateUser
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding= FragmentAcountBinding.bind(view)
             createuser()
    }


    private fun createuser() {
        if(binding.checkone.isChecked){
            hobbies.add(binding.checkone.text.toString())
        }
        if (binding.check2.isChecked){
            hobbies.add(binding.check2.text.toString())
        }
        if (binding.check3.isChecked){
            hobbies.add(binding.check3.text.toString())
        }
        with(binding) {
            btcr.setOnClickListener {
                user = CreateUser(
                    etname.text.toString(),
                    etfamily.text.toString(),
                    etnacode.text.toString()
                )
                viewModelHome.createUser(user)
                viewModelHome.createUser(user).observe(viewLifecycleOwner, Observer {
                    navController.navigate(AccountFragmentDirections.actionAccountFragmentToUploadImageFragment(it))
                })

            }
        }
    }


}