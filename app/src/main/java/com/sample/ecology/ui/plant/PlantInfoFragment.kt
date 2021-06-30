package com.sample.ecology.ui.plant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sample.ecology.databinding.FragmentPlantInfoBinding
import com.sample.ecology.ui.animal.AnimalAreaInfoFragment
import com.sample.ecology.ui.index.MainActivity

class PlantInfoFragment : Fragment() {
    private var binding: FragmentPlantInfoBinding? = null
    private var fPic01URL: String? = null
    private var fNameCh: String? = null
    private var fNameEn: String? = null
    private var fAlsoKnown: String? = null
    private var fBrief: String? = null
    private var fFeature: String? = null
    private var fFunction: String? = null
    private var fUpdate: String? = null

    companion object {
        private var fragment: PlantInfoFragment? = null
        @JvmStatic
        fun newInstance() : PlantInfoFragment {
            if (fragment == null) {
                fragment = PlantInfoFragment()
            }

            return fragment!!
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)

        if (!hidden) {
            (activity as MainActivity?)!!.supportActionBar!!.title = fNameCh
            (activity as MainActivity?)!!.currentFragment = newInstance()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentPlantInfoBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fPic01URL = requireArguments().getString(AnimalAreaInfoFragment.KEY.F_PIC1_URL)
        fNameCh = requireArguments().getString(AnimalAreaInfoFragment.KEY.F_NAME_CH)
        fNameEn = requireArguments().getString(AnimalAreaInfoFragment.KEY.F_NAME_EN)
        fAlsoKnown = requireArguments().getString(AnimalAreaInfoFragment.KEY.F_AISO_KNOWN)
        fBrief = requireArguments().getString(AnimalAreaInfoFragment.KEY.F_BRIEF)
        fFeature = requireArguments().getString(AnimalAreaInfoFragment.KEY.F_FEATURE)
        fFunction = requireArguments().getString(AnimalAreaInfoFragment.KEY.F_FUNCTION)
        fUpdate = requireArguments().getString(AnimalAreaInfoFragment.KEY.F_UPDATE)

        setPlantInfo()
    }

    private fun setPlantInfo() {
        (activity as MainActivity?)!!.supportActionBar!!.title = fNameCh
        (activity as MainActivity?)!!.supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        Glide.with(requireActivity()).load(fPic01URL).into(binding!!.includeImage.image)
        binding!!.fNameCh.text = fNameCh
        binding!!.fNameEn.text = fNameEn
        binding!!.fAlsoKnown.text = fAlsoKnown
        binding!!.fBrief.text = fBrief
        binding!!.fFeature.text = fFeature
        binding!!.fFunction.text = fFunction
        binding!!.fUpdate.text = fUpdate
    }
}