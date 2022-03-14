package com.example.creatuser.ui.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.creatuser.R
import com.example.creatuser.data.local.model.User
import com.example.creatuser.databinding.FragmentUsersBinding
import com.example.creatuser.di.App

class UsersFragment : Fragment(R.layout.fragment_users) {
    lateinit var binding: FragmentUsersBinding
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
        initViews()
//        getListOfUsers()
        searchingUser()
        clickListenerForEachItem()


    }

    private fun getListOfUsers() {
        viewModel.getUsersFromServer().observe(viewLifecycleOwner) {
            listUsers.clear()
            listUsers.addAll(it)
            recyclerAdaptor.notifyItemRangeInserted(0, it.size)

        }
    }

    private fun searchingUser() {
        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                @SuppressLint("NotifyDataSetChanged")
                override fun onQueryTextSubmit(query: String?): Boolean {

                    viewModel.searchFromUsers(query!!).observe(viewLifecycleOwner) {
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

    }

    private fun clickListenerForEachItem() {
        recyclerAdaptor.setItemUserClick(object : RecyclerAdaptor.ItemClick {
            override fun viewClick(position: Int, v: View?) {
                navController.navigate(
                    UsersFragmentDirections.actionUsersFragmentToShowInfoFragment(
                        listUsers[position]._id
                    )
                )
            }
        })
    }

    private fun initViews() {
        val recyclerView = binding.rc
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerAdaptor = RecyclerAdaptor(listUsers)
        recyclerView.adapter = recyclerAdaptor
    }
}

