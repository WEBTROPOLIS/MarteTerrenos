package com.example.marteterrenos.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.marteterrenos.R
import com.example.marteterrenos.databinding.FragmentMarsDetailsBinding
import com.example.marteterrenos.model.local.MarsData
import com.example.marteterrenos.viewmodel.MarsViewModel
import kotlinx.coroutines.launch


class MarsDetailsFragment : Fragment() {

    private lateinit var mBinding : FragmentMarsDetailsBinding
    private val viewModel : MarsViewModel by viewModels()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mBinding = FragmentMarsDetailsBinding.inflate(inflater,container,false)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val stateId = arguments?.getString("stateID","-1") ?: "-1"
        var stateDetails : MarsData? = null

        viewLifecycleOwner.lifecycleScope.launch {
            stateDetails = viewModel.getOneState(stateId)
            val imgUrl : String
            if (stateDetails != null){
                mBinding.tvId.text = "Identificador del Terreno: ${stateDetails?.id}"

                mBinding.tbDetails.title = "ID Propiedad:${stateDetails?.id}"
                mBinding.tvTypeDetails.text = "Tipo de Operación: ${stateDetails?.type}"
                mBinding.tvPrice.text = "Precio $${stateDetails?.price.toString()}"
                imgUrl = stateDetails?.img.toString()
                Glide.with(mBinding.root)
                    .load(imgUrl)
                    .centerCrop()
                    .placeholder(R.drawable.ic_img)
                    .error(R.drawable.ic_not_image)
                    .into(mBinding.img)
            }
        }

        mBinding.fabBack.setOnClickListener { parentFragmentManager.popBackStack() }
    }


}