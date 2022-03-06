package com.example.creatuser.ui.creataccount

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.creatuser.R
import com.example.creatuser.databinding.FragmentAcountBinding
import com.example.creatuser.model.User

class AccountFragment:Fragment(R.layout.fragment_acount) {
    val viewModelHome by viewModels<AccountFragmentViewModel>()
    var hobbies:MutableList<String> = mutableListOf()
    lateinit var binding:FragmentAcountBinding
    val navController by lazy {
        findNavController()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding= FragmentAcountBinding.bind(view)
             createuser()
            gotouploadphoto()
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
                val user = User(
                    etname.text.toString(),
                    listOf("Movie", "Sport"),
                    etfamily.text.toString(),
                    etnacode.text.toString()
                )
                viewModelHome.createUser(user)

            }
        }
    }
    fun gotouploadphoto(){
        viewModelHome.userId.observe(viewLifecycleOwner, Observer {
            navController.navigate(AccountFragmentDirections.actionAccountFragmentToUploadImageFragment(it))
        })
    }

}