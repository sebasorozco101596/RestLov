package com.sebasorozcob.www.restlov.ui.fragments.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sebasorozcob.www.restlov.R
import com.sebasorozcob.www.restlov.databinding.FragmentRestaurantMenuBinding
import com.sebasorozcob.www.restlov.model.Menu
import com.sebasorozcob.www.restlov.model.RestaurantItem
import com.sebasorozcob.www.restlov.model.TypeProduct
import com.sebasorozcob.www.restlov.ui.adapters.MenuItemsAdapter
import com.sebasorozcob.www.restlov.ui.dialogs.RestaurantOverviewDialog
import com.sebasorozcob.www.restlov.util.Constants.Companion.RESTAURANT_RESULT_KEY

class RestaurantMenuFragment : Fragment() {

    private var _binding: FragmentRestaurantMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRestaurantMenuBinding.inflate(layoutInflater, container, false)

        val args = arguments
        val myBundle: RestaurantItem? = args?.getParcelable(RESTAURANT_RESULT_KEY)

        with(binding) {
            restaurantImageView.load(myBundle?.imageUrl)
            overviewRestaurantTextView.setOnClickListener {
                RestaurantOverviewDialog.newInstance(myBundle!!)
                    .show(parentFragmentManager, "dialogOverview")
            }
        }

        printMenu(myBundle?.menu?.get(0))

        return binding.root
    }

    private fun setupRecyclerView(recyclerView: RecyclerView, mAdapter: MenuItemsAdapter) {
        recyclerView.adapter = mAdapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //showShimmerEffect()
    }

    private fun printMenu(menu: Menu?) {
        for (products in menu?.typeProducts!!) {
            addChild(products)
        }
    }

    // TODO SE ESTA SOLO PINTANDO LOS ULTIMMOS PRODUCTOS DEL ULTIMO TYPEPRODUCT

    private fun addChild(typeProduct: TypeProduct) {

        val layout = binding.layoutMenuDynamic
        val mAdapter by lazy { MenuItemsAdapter() }

        val textViewName = TextView(requireContext())
        textViewName.text = typeProduct.type
        textViewName.setTextAppearance(R.style.boldText)
        val textViewDescription = TextView(requireContext())
        textViewDescription.text = typeProduct.information
        val recyclerViewProducts = RecyclerView(requireContext())
        setupRecyclerView(recyclerViewProducts, mAdapter)
        mAdapter.setData(typeProduct.products)

        if (textViewName.parent != null) {
            (textViewName.parent as ViewGroup).removeView(textViewName) // <- fix
        }
        if (textViewDescription.parent != null) {
            (textViewDescription.parent as ViewGroup).removeView(textViewDescription) // <- fix
        }
        if (recyclerViewProducts.parent != null) {
            (recyclerViewProducts.parent as ViewGroup).removeView(recyclerViewProducts) // <- fix
        }

        layout.addView(textViewName)
        layout.addView(textViewDescription)
        layout.addView(recyclerViewProducts)

        // The next lines of code, made the recyclerview no scrollable, remove the fade, and made the
        // Recycler view adaptive to the quantity of items that have

        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1F //for that is necessary the Weight 1F
        )
        recyclerViewProducts.layoutParams = layoutParams
        recyclerViewProducts.overScrollMode = DrawerLayout.OVER_SCROLL_NEVER // For remove the fade of the scroll
    }
}