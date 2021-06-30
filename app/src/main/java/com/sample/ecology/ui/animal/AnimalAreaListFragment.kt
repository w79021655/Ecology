package com.sample.ecology.ui.animal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sample.ecology.LoadingDialogUtil
import com.sample.ecology.R
import com.sample.ecology.data.animal.AnimalArea
import com.sample.ecology.databinding.FragmentAnimalAreaListBinding
import com.sample.ecology.ui.animal.adapter.AnimalAreaListAdapter
import com.sample.ecology.ui.index.MainActivity

class AnimalAreaListFragment : Fragment(), IAnimalAreaContract.IAnimalAreaView {
    private var binding: FragmentAnimalAreaListBinding? = null
    private lateinit var mAdapter: AnimalAreaListAdapter
    private lateinit var presenter: AnimalAreaListPresenter

    object KEY {
        const val E_NAME: String = "eName"
        const val E_PIC_URL: String = "ePicURL"
        const val E_INFO: String = "eInfo"
        const val E_MEMO: String = "eMemo"
        const val E_CATEGORY: String = "eCategory"
        const val E_URL: String = "eURL"
    }

    companion object {
         private var fragment: AnimalAreaListFragment? = null
        @JvmStatic
        fun newInstance() : AnimalAreaListFragment {
            if (fragment == null) {
                fragment = AnimalAreaListFragment()
            }

            return fragment!!
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (!hidden) {
            (activity as MainActivity?)!!.supportActionBar!!.title = getString(R.string.taipei_city_Zoo)
            (activity as MainActivity?)!!.currentFragment = newInstance()
        } else {
            presenter.onDestroy()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAnimalAreaListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUI()
        fetchAnimalArea()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun setupUI() {
        (activity as MainActivity?)!!.supportActionBar!!.title = getString(R.string.taipei_city_Zoo)

        binding!!.recyclerView.apply {
            val layoutManager = LinearLayoutManager(requireActivity())
            layoutManager.orientation = RecyclerView.VERTICAL
            binding!!.recyclerView.layoutManager = layoutManager
            mAdapter = AnimalAreaListAdapter(listener)
            binding!!.recyclerView.adapter = mAdapter
        }
    }

    private var listener = object : AnimalAreaListAdapter.OnItemClickListener {
        override fun onItemClick(
            eName: String,
            ePicURL: String,
            eInfo: String,
            eMemo: String,
            eCategory: String,
            eURL: String
        ) {
            val fragment: AnimalAreaInfoFragment = AnimalAreaInfoFragment.newInstance()
            val bundle = Bundle()
            bundle.putString(KEY.E_NAME, eName)
            bundle.putString(KEY.E_PIC_URL, ePicURL)
            bundle.putString(KEY.E_INFO, eInfo)
            bundle.putString(KEY.E_MEMO, eMemo)
            bundle.putString(KEY.E_CATEGORY, eCategory)
            bundle.putString(KEY.E_URL, eURL)
            fragment.arguments = bundle
            (activity as MainActivity?)!!.addFragment(fragment)
        }
    }

    override fun showProgress() {
        LoadingDialogUtil.showProgressDialog(requireActivity())
    }

    override fun hideProgress() {
        LoadingDialogUtil.dismiss()
    }

    override fun fetchAnimalArea() {
        presenter = AnimalAreaListPresenter(this)
        presenter.getAnimalArea()
    }

    override fun onAnimalAreaResult(animalArea: AnimalArea?) {
        if (animalArea != null) {
            if (animalArea.result.results.isEmpty()) {
                binding!!.includeDataEmpty.empty.visibility = View.VISIBLE
            } else {
                mAdapter.setAnimalArea(animalArea.result.results)
                mAdapter.notifyDataSetChanged()
            }
        }
    }

    override fun onResponseFailure(throwable: Throwable?) {
        Toast.makeText(requireActivity(),getString(R.string.other_error),Toast.LENGTH_SHORT).show()
    }

    override fun onServerError() {
        Toast.makeText(requireActivity(),getString(R.string.system_error),Toast.LENGTH_SHORT).show()
    }
}