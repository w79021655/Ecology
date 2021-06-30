package com.sample.ecology.ui.animal

import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.*
import android.widget.Toast
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.ecology.LoadingDialogUtil
import com.sample.ecology.R
import com.sample.ecology.data.plant.PlantArea
import com.sample.ecology.data.plant.PlantItem
import com.sample.ecology.databinding.FragmentAnimalAreaInfoBinding
import com.sample.ecology.ui.animal.adapter.PlantAreaListAdapter
import com.sample.ecology.ui.index.MainActivity
import com.sample.ecology.ui.plant.IPlantAreaContract
import com.sample.ecology.ui.plant.PlantAreaListPresenter
import com.sample.ecology.ui.plant.PlantInfoFragment

class AnimalAreaInfoFragment: Fragment(), IPlantAreaContract.IPlantAreaView {
    private var binding: FragmentAnimalAreaInfoBinding? = null
    private lateinit var plantArea: PlantArea
    private lateinit var mAdapter: PlantAreaListAdapter
    private lateinit var presenter: PlantAreaListPresenter
    private var mPlantItem = ArrayList<PlantItem>()
    private var eName: String? = null
    private var ePicURL: String? = null
    private var eInfo: String? = null
    private var eMemo: String? = null
    private var eCategory: String? = null
    private var eURL: String? = null
    private var offset: Int = 0
    private var count: Int = 0

    object KEY {
        const val F_PIC1_URL: String = "fPic01URL"
        const val F_NAME_CH: String = "fNameCh"
        const val F_NAME_EN: String = "fNameEn"
        const val F_AISO_KNOWN: String = "fAlsoKnown"
        const val F_BRIEF: String = "fBrief"
        const val F_FEATURE: String = "fFeature"
        const val F_FUNCTION: String = "fFunction"
        const val F_UPDATE: String = "fUpdate"
    }

    object PARAM {
        const val LIMIT: Int = 10
    }

    companion object {
        private var fragment: AnimalAreaInfoFragment? = null
        @JvmStatic
        fun newInstance() : AnimalAreaInfoFragment {
            if (fragment == null) {
                fragment = AnimalAreaInfoFragment()
            }

            return fragment!!
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (!hidden) {
            (activity as MainActivity?)!!.supportActionBar!!.title = eName
            (activity as MainActivity?)!!.currentFragment = newInstance()
        } else {
            presenter.onDestroy()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAnimalAreaInfoBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        eName = requireArguments().getString(AnimalAreaListFragment.KEY.E_NAME)
        ePicURL = requireArguments().getString(AnimalAreaListFragment.KEY.E_PIC_URL)
        eInfo = requireArguments().getString(AnimalAreaListFragment.KEY.E_INFO)
        eMemo = requireArguments().getString(AnimalAreaListFragment.KEY.E_MEMO)
        eCategory = requireArguments().getString(AnimalAreaListFragment.KEY.E_CATEGORY)
        eURL = requireArguments().getString(AnimalAreaListFragment.KEY.E_URL)

        init()
        setupUI()
        fetchPlantArea()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun init() {
        count = 0
        offset = 0
        mPlantItem.clear()
    }

    private fun setupUI() {
        (activity as MainActivity?)!!.supportActionBar!!.title = eName
        (activity as MainActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding!!.layout.visibility = View.INVISIBLE

        binding!!.recyclerView.apply {
            val layoutManager = LinearLayoutManager(requireActivity())
            layoutManager.orientation = RecyclerView.VERTICAL
            binding!!.recyclerView.layoutManager = layoutManager
            mAdapter = PlantAreaListAdapter(listener)
            binding!!.recyclerView.adapter = mAdapter
        }

        binding!!.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (recyclerView.computeVerticalScrollExtent() + recyclerView.computeVerticalScrollOffset() >= recyclerView.computeVerticalScrollRange()) {
                    if (plantArea.result.results.isNotEmpty()) {
                        if (plantArea.result.results.size < count)
                            fetchPlantArea()
                    }
                }
            }
        })
    }

    private var listener = object : PlantAreaListAdapter.OnItemClickListener {
        override fun onItemClick(
            fPic01URL: String,
            fNameCh: String,
            fNameEn: String,
            fAlsoKnown: String,
            fBrief: String,
            fFeature: String,
            fFunction: String,
            fUpdate: String
        ) {
            val fragment: PlantInfoFragment = PlantInfoFragment.newInstance()
            val bundle = Bundle()
            bundle.putString(KEY.F_PIC1_URL, fPic01URL)
            bundle.putString(KEY.F_NAME_CH, fNameCh)
            bundle.putString(KEY.F_NAME_EN, fNameEn)
            bundle.putString(KEY.F_AISO_KNOWN, fAlsoKnown)
            bundle.putString(KEY.F_BRIEF, fBrief)
            bundle.putString(KEY.F_FEATURE, fFeature)
            bundle.putString(KEY.F_FUNCTION, fFunction)
            bundle.putString(KEY.F_UPDATE, fUpdate)
            fragment.arguments = bundle
            (activity as MainActivity?)!!.addFragment(fragment)
        }
    }

    private fun setPlantAreaInfo() {
        Glide.with(requireActivity()).load(ePicURL).into(binding!!.includeImage.image)
        binding!!.info.text = eInfo
        binding!!.category.text = eCategory

        if (eMemo == "") {
            binding!!.restInfo.text = getString(R.string.not_info)
        } else {
            binding!!.restInfo.text = eMemo
        }

        val webUrl = "<a href='" + eURL + "'>" + getString(R.string.web_open) + "</a>"
        binding!!.webUrl.text = HtmlCompat.fromHtml(webUrl, HtmlCompat.FROM_HTML_MODE_COMPACT)
        binding!!.webUrl.movementMethod = LinkMovementMethod.getInstance()

        binding!!.layout.visibility = View.VISIBLE
    }

    override fun fetchPlantArea() {
        presenter = PlantAreaListPresenter(this)
        presenter.getPlantArea(eName!!, PARAM.LIMIT, offset)
    }

    override fun showProgress() {
        LoadingDialogUtil.showProgressDialog(requireActivity())
    }

    override fun hideProgress() {
        LoadingDialogUtil.dismiss()
    }

    override fun onPlantAreaResult(plantArea: PlantArea?) {
        if (plantArea != null) {
            mPlantItem.addAll(plantArea.result.results)

            if (mPlantItem.isEmpty()) {
                binding!!.recyclerView.visibility = View.GONE
                binding!!.includeDataEmpty.empty.visibility = View.VISIBLE
            } else {
                binding!!.recyclerView.visibility = View.VISIBLE
                binding!!.includeDataEmpty.empty.visibility = View.GONE

                this.plantArea = plantArea
                count = plantArea.result.count
                offset += PARAM.LIMIT
                mAdapter.setPlantList(mPlantItem)
                mAdapter.notifyDataSetChanged()
            }

            setPlantAreaInfo()
        }
    }

    override fun onResponseFailure(throwable: Throwable?) {
        Toast.makeText(requireActivity(),getString(R.string.other_error), Toast.LENGTH_SHORT).show()
    }

    override fun onServerError() {
        Toast.makeText(requireActivity(),getString(R.string.system_error), Toast.LENGTH_SHORT).show()
    }
}