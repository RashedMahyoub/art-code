package com.snipertech.cryptobud.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager

import com.snipertech.cryptobud.databinding.FragmentHistoryBinding
import com.snipertech.cryptobud.db.entities.Message
import com.snipertech.cryptobud.view.adapter.ItemRecyclerAdapter
import com.snipertech.cryptobud.viewModel.HistoryFragmentViewModel


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HistoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var historyFragmentViewModel: HistoryFragmentViewModel
    private lateinit var historyBinding: FragmentHistoryBinding
    private lateinit var itemAdapter: ItemRecyclerAdapter
    var data = MutableLiveData<List<Message>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        historyBinding = FragmentHistoryBinding.inflate(inflater, container, false)
        return historyBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        historyFragmentViewModel = ViewModelProvider(this)
            .get(HistoryFragmentViewModel::class.java)

        //init Recycler View
        historyBinding.messageRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext()).apply {
                stackFromEnd = true
                reverseLayout = true
            }
            setHasFixedSize(true)
        }

        historyFragmentViewModel.allMessages?.observe(viewLifecycleOwner, Observer {
            data.value = it
            itemAdapter =
                ItemRecyclerAdapter(data)
            historyBinding.messageRecycler.adapter = itemAdapter

            //onClick to show dialog with information
            itemAdapter.setOnClickLister(object : ItemRecyclerAdapter.OnItemClickListener{
                override fun onItemClick(item: Message) {
                    MessageDialog.showDialog(requireActivity(),
                        item.message,
                        item.type,
                        item.algo,
                        item.key
                    )
                }
            })
        })
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HistoryFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
