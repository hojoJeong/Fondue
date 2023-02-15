package com.ssafy.fundyou.ui.point

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.ssafy.fundyou.R
import com.ssafy.fundyou.databinding.FragmentPointLoadBinding
import com.ssafy.fundyou.ui.common.BaseFragment
import com.ssafy.fundyou.ui.point.model.PointModel

class PointLoadFragment : BaseFragment<FragmentPointLoadBinding>(R.layout.fragment_point_load) {
    private val pointViewModel by activityViewModels<PointViewModel>()
    private val userPoint: PointLoadFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initViewModels()
    }

    override fun initView() {
        pointViewModel.setPoint(0)
        binding.point = PointModel(userPoint.balancePoint, userPoint.balancePoint)
        initPointInput()
        btnClickListener()
    }

    override fun initViewModels() {
        initPointObserve()
    }

    private fun initPointInput() {
        binding.editPointLoad.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(s?.length!! > 0 ){
                    pointViewModel.setPoint(s.toString().toInt())
                }
            }
            override fun afterTextChanged(s: Editable?) {}

        })

        binding.btnPointLoad10000.setOnClickListener {
            refreshLoadingPoint(10000)
        }

        binding.btnPointLoad50000.setOnClickListener {
            refreshLoadingPoint(50000)
        }
    }

    private fun refreshLoadingPoint(point: Int){
        val editTvPoint = binding.editPointLoad
        val loadingPoint = if(editTvPoint.text.toString() == "") 0 else editTvPoint.text.toString().toInt()
        editTvPoint.setText("${loadingPoint + point}")
        pointViewModel.setPoint(loadingPoint + point)
    }

    private fun initPointObserve() {
        pointViewModel.loadingPoint.observe(viewLifecycleOwner) {
            val point = PointModel(
                userPoint.balancePoint,
                userPoint.balancePoint + pointViewModel.loadingPoint.value!!.toInt()
            )
            binding.point = point
        }
    }

    private fun btnClickListener(){
        binding.btnPointLoadCancel.setOnClickListener {
            popBackStack()
        }
        binding.btnPointLoadConfirm.setOnClickListener {
            if(binding.editPointLoad.text.toString() == ""){
                Snackbar.make(requireView(), "충전할 포인트 금액을 입력해주세요.", Snackbar.LENGTH_SHORT).show()
            } else {
                pointViewModel.loadPoint(pointViewModel.loadingPoint.value!!.toInt())
                navigate(PointLoadFragmentDirections.actionPointLoadFragmentToPointResultFragment())
            }
        }
    }
}