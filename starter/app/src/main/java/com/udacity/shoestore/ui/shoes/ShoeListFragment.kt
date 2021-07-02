package com.udacity.shoestore.ui.shoes

import android.content.res.Resources
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.ui.ShoesViewModel
import kotlin.math.roundToInt

class ShoeListFragment: Fragment() {

    lateinit var binding: FragmentShoeListBinding
    private val shoesViewModel: ShoesViewModel by activityViewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_shoe_list, container, false)
        binding.viewModel = shoesViewModel
        binding.lifecycleOwner = viewLifecycleOwner
        setHasOptionsMenu(true)
        return binding.root

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.shoe_list_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.logout->{
                logout()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        shoesViewModel.shoeList.observe(viewLifecycleOwner){shoesList ->
            binding.shoeListLayout.removeAllViews()
            shoesList.forEach {shoe->
                val myView = AppCompatTextView(requireContext()).apply {
                    text = getString(R.string.shoe_item, shoe.name, shoe.company, shoe.size, shoe.description)
                }
                LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT).apply {
                    val margin = (16 * Resources.getSystem().displayMetrics.density).roundToInt()
                    setMargins(margin, margin, margin, margin)
                    myView.layoutParams = this
                }
                binding.shoeListLayout.addView(myView)
            }

        }

        shoesViewModel.eventAddShoe.observe(viewLifecycleOwner){navigate->
            if(navigate){
                findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailsFragment())
                shoesViewModel.onShoeAddNavigated()
            }
        }

    }

    private fun logout(){
        shoesViewModel.clearData()
        findNavController().navigate(ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment())
    }

}