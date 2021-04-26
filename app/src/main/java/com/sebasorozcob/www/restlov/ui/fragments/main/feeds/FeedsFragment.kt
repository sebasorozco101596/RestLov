package com.sebasorozcob.www.restlov.ui.fragments.main.feeds

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.sebasorozcob.www.restlov.databinding.FragmentFeedsBinding
import com.sebasorozcob.www.restlov.model.Location
import com.sebasorozcob.www.restlov.model.RestaurantItem
import com.sebasorozcob.www.restlov.model.social.Comment
import com.sebasorozcob.www.restlov.model.social.Feed
import com.sebasorozcob.www.restlov.ui.adapters.feeds.FeedsAdapter
import com.sebasorozcob.www.restlov.util.Constants
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FeedsFragment : Fragment() {

    private var _binding: FragmentFeedsBinding? = null
    val binding get() = _binding!!

    private val mAdapter by lazy { FeedsAdapter(requireContext(), this.requireActivity()) }
    private var feeds = ArrayList<Feed>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFeedsBinding.inflate(inflater, container, false)

        fillFeeds()
        setupRecyclerView()
        mAdapter.setData(feeds)

        return binding.root
    }

    private fun setupRecyclerView() {
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        //showShimmerEffect()
    }

    private fun fillFeeds() {

        val location = Location(
            "43 maple av",
            "Dover",
            "United States",
            "12awd",
            129129,
            129129,
            "New Jersey",
            0,
            "07801"
        )
        val photos = ArrayList<String>()
        val photos2 = ArrayList<String>()
        val photos3 = ArrayList<String>()
        photos.add("https://imagenesnoticias.canalrcn.com/ImgNoticias/gastronomia_colombiana_master_chef.jpg")
        photos.add("https://www.restaurantecocorafusion.co/wp-content/uploads/2018/05/restaurante-cocora-cocina-colombiana-3-e1559790973593-800x480.jpg")
        photos.add("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/BB15poFi.img?h=768&w=1080&m=6&q=60&o=t&l=f")
        photos.add("https://tecnohotelnews.com/wp-content/uploads/2018/04/siete-claves-para-ofrecer-platos-saludables-atractivos-a-los-comensales.jpg")
        photos.add("https://media-cdn.tripadvisor.com/media/photo-s/10/e4/ad/3a/comida-bien-presentada.jpg")
        photos.add("https://lh3.googleusercontent.com/proxy/rE6jERd_v00N6w0ASF47X7SVi6gtRPyMpnqD2IBKzIy-9qsXGjbfbnJC4Eagx3mZAw5kowO518KGqU9zY1Fmser5dpRPSFIkAKaMJ1wZ3H2U6ljZ")
        photos.add("https://i.blogs.es/166c1c/chirashizushi2/1366_2000.jpg")
        photos.add("https://media-cdn.tripadvisor.com/media/photo-s/11/8c/f0/4e/comida-bien-elaborada.jpg")
        photos.add("https://www.clara.es/medio/2016/11/10/canapes-variados-receta-navidad_ab5c9a3e.jpg")
        photos.add("https://thumbs.dreamstime.com/b/comida-estacional-vegetariana-sana-de-la-ca%C3%ADda-que-cocina-el-fondo-composici%C3%B3n-vertical-127863552.jpg")
        photos2.add("https://thumbs.dreamstime.com/b/comida-estacional-vegetariana-sana-de-la-ca%C3%ADda-que-cocina-el-fondo-composici%C3%B3n-vertical-127863552.jpg")
        photos2.add("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/BB15poFi.img?h=768&w=1080&m=6&q=60&o=t&l=f")
        photos3.add("https://img-s-msn-com.akamaized.net/tenant/amp/entityid/BB15poFi.img?h=768&w=1080&m=6&q=60&o=t&l=f")
        // For Delete Later, Is just for local test
        val restaurant1 = RestaurantItem(
            Constants.Currency.Dollar.toString(),
            "This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer, This restaurant have an excellent service to the customer ",
            null,
            "1129",
            "https://imagenesnoticias.canalrcn.com/ImgNoticias/gastronomia_colombiana_master_chef.jpg",
            location,
            null,
            "Sebas Delicias",
            "973 465 7855",
            3,
            0,
            null
        )

        val comments = ArrayList<Comment>()

        val comment1 =
            Comment(
                1,
                1,
                null,
                restaurant1,
                "The food on this restaurant is the best on the area",
                null,
                2
            )
        val comment2 =
            Comment(
                1,
                1,
                null, restaurant1,
                "Here you can eat all you can",
                null,
                2
            )

        comments.add(comment1)
        comments.add(comment2)

        val feed1 = Feed(1, restaurant1, "Today the restaurant gonna stay close", photos3, 1, null)
        val feed2 = Feed(
            1,
            restaurant1,
            "Today the restaurant gonna stay close until 12 january",
            photos,
            19,
            comments
        )
        val feed3 = Feed(1, restaurant1, "Today the restaurant gonna stay open", null, 2, null)
        val feed4 = Feed(
            1,
            restaurant1,
            "Today the restaurant gonna stay close until 11 pm",
            photos2,
            15,
            comments
        )

        feeds.add(feed1)
        feeds.add(feed2)
        feeds.add(feed3)
        feeds.add(feed4)
    }
}