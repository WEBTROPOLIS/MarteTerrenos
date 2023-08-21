package com.example.marteterrenos.view

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marteterrenos.R
import com.example.marteterrenos.databinding.FragmentStateBinding
import com.example.marteterrenos.viewmodel.MarsViewModel


class StateFragment : Fragment(), OnCLickListenerState {

    private lateinit var mBinding: FragmentStateBinding
    private lateinit var adapter: StateAdapter
    private val viewModel : MarsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentStateBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = StateAdapter(mutableListOf(),this,this)
        mBinding.rv.adapter = adapter
        mBinding.rv.layoutManager = GridLayoutManager(requireContext(),2)

        viewModel.getAllState().observe(viewLifecycleOwner){ stateList ->
            adapter.updateCount(stateList)
        }

        mBinding.fabExit.setOnClickListener { showDialogExit() }


    }

    private fun showDialogExit(){
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Salir")
        builder.setIcon(R.drawable.ic_question)
        builder.setMessage("Estas seguro que desas salir?")
        builder.setPositiveButton("SI") {dialog, _ ->
            requireActivity().finish()
            dialog.dismiss()
        }
        builder.setNegativeButton("NO") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    override fun onClick(stateId: String) {
        val fragment = MarsDetailsFragment()
        val bundle = Bundle()
        bundle.putString("stateID", stateId)
        fragment.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame,fragment)
            .addToBackStack(null)
            .commit()
    }


}