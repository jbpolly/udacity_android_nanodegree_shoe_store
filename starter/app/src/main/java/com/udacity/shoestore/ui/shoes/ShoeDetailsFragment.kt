package com.udacity.shoestore.ui.shoes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailsBinding
import com.udacity.shoestore.models.Shoe
import com.udacity.shoestore.ui.ShoesViewModel

class ShoeDetailsFragment : Fragment() {

    lateinit var binding: FragmentShoeDetailsBinding
    private val shoesViewModel: ShoesViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_details, container, false)
        binding.shoeViewModel = shoesViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shoesViewModel.eventCancel.observe(viewLifecycleOwner) { cancel ->
            if (cancel) {
                findNavController().navigateUp()
                shoesViewModel.onCancelComplete()
            }
        }

        shoesViewModel.eventSaveShoe.observe(viewLifecycleOwner) { save ->
            if (save) {
                shoesViewModel.addShoeToList()
                goBackToList()
                shoesViewModel.onSaveShoeComplete()
            }
        }

    }

//    private fun getShoe(): Shoe {
//        return Shoe(
//            name = binding.shoeNameField.text.toString(),
//            size = binding.shoeSizeField.text.toString().toDoubleOrNull() ?: 0.0,
//            description = binding.descriptionField.text.toString(),
//            company = binding.companyField.text.toString()
//        )
//    }

    private fun goBackToList() {
        findNavController().navigate(ShoeDetailsFragmentDirections.actionShoeDetailsFragmentToShoeListFragment())
    }

}