package com.example.creatuser.ui.users

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.creatuser.R
import com.example.creatuser.data.Mapper
import com.example.creatuser.data.local.model.User
import com.example.creatuser.data.remote.model.UserResponse
import com.example.creatuser.databinding.FragmentUsersBinding
import com.example.creatuser.di.App
import com.example.creatuser.di.ServiceLocator

class UsersFragment:Fragment(R.layout.fragment_users) {
    lateinit var binding: FragmentUsersBinding
    companion object {
        const val TAG = "FRAGMENT"
    }
    private val viewModel by viewModels<UsersFragmentViewModel>(factoryProducer = {
        UserFragmentFactory((requireActivity().application as App).serviceLocator.repository)
    })

    private var listUsers = mutableListOf<User>()
    val navController by lazy { findNavController() }
    private lateinit var recyclerAdaptor: RecyclerAdaptor

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUsersBinding.bind(view)
          val recyclerView = binding.rc
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        viewModel.getUsersFromServer().observe(viewLifecycleOwner){
            listUsers.clear()
            listUsers.addAll(it)
            recyclerAdaptor.notifyItemRangeInserted(0,it.size)


        }





        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    viewModel.searchFromUsers(query!!).observe(viewLifecycleOwner){
                        listUsers.clear()
                        listUsers.addAll(it)
                        recyclerAdaptor.notifyDataSetChanged()
                    }


                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    return false
                }
            }
        )


        recyclerAdaptor = RecyclerAdaptor(listUsers)
        recyclerView.adapter = recyclerAdaptor

        recyclerAdaptor.setItemUserClick(object :RecyclerAdaptor.ItemClick{
            override fun viewClick(position: Int, v: View?) {
                navController.navigate(UsersFragmentDirections.actionUsersFragmentToShowInfoFragment(listUsers[position]._id))
            }
        } )






    }
}