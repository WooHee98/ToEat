package com.example.toeat

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.toeat.databinding.FragmentPostBinding


class PostFragment : Fragment() {

    private lateinit var binding: FragmentPostBinding
    private lateinit var contactViewModel: ContactViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPostBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = PostAdapter({ contact ->
            val intent = Intent(context, PostAddActivity::class.java)
            intent.putExtra(PostAddActivity.EXTRA_CONTACT_NAME, contact.name)
            intent.putExtra(PostAddActivity.EXTRA_CONTACT_NUMBER, contact.number)
            intent.putExtra(PostAddActivity.EXTRA_CONTACT_ID, contact.id)
            startActivity(intent)
        }, { contact ->
            deleteDialog(contact)
        })


        binding.mainRecycleview.adapter = adapter
        binding.mainRecycleview.layoutManager = LinearLayoutManager(context)
        binding.mainRecycleview.setHasFixedSize(true)

        contactViewModel = ViewModelProvider(this)[ContactViewModel::class.java]
        contactViewModel.getAll().observe(viewLifecycleOwner, Observer<List<Contact>> { contacts ->
            adapter.setContacts(contacts!!)
        })


        binding.mainButton.setOnClickListener {
            val intent = Intent(context, PostAddActivity::class.java)
            startActivity(intent)
        }
    }

    private fun deleteDialog(contact: Contact) {
        val builder = context?.let { AlertDialog.Builder(it) }
        builder?.setMessage("Delete selected contact?")?.setNegativeButton("NO") { _, _ -> }
            ?.setPositiveButton("YES") { _, _ ->
                contactViewModel.delete(contact)
            }
        builder?.show()
    }

}