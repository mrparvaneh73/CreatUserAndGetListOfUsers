package com.example.creatuser.ui.users

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.creatuser.R
import com.example.creatuser.databinding.FragmentUsersBinding
import com.example.creatuser.model.UserFromServer

class UsersFragment:Fragment(R.layout.fragment_users) {
    lateinit var binding: FragmentUsersBinding

    private val viewModel by activityViewModels<UsersFragmentViewModel>()

    private var listUsers = mutableListOf<UserFromServer>()
    val navController by lazy { findNavController() }


    private lateinit var recyclerAdaptor: RecyclerAdaptor

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentUsersBinding.bind(view)
        var recyclerView = binding.rc

        viewModel.getUsersFromServer()

        viewModel._listUsers.observe(viewLifecycleOwner) {
            listUsers.clear()
            listUsers.addAll(it)
            recyclerAdaptor.notifyDataSetChanged()
        }
        recyclerAdaptor = RecyclerAdaptor(listUsers)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdaptor
        viewModel.searchResult.observe(viewLifecycleOwner, Observer {
            listUsers.clear()
            listUsers.addAll(it)
            recyclerAdaptor.notifyDataSetChanged()

        })

        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {

                    viewModel.getUserFromFirstName(query!!)

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {

                    return false
                }
            }
        )

        recyclerAdaptor = RecyclerAdaptor(listUsers)
        recyclerAdaptor.setItemUserClick(object :RecyclerAdaptor.ItemClick{
            override fun viewClick(position: Int, v: View?) {

                navController.navigate(UsersFragmentDirections.actionUsersFragmentToShowInfoFragment(listUsers[position]._id))
            }
        } )
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = recyclerAdaptor




    }
}