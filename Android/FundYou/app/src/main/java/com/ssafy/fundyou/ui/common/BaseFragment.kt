package com.ssafy.fundyou.ui.common

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.NavHostFragment

/** Fragment 대신 상속 */
abstract class BaseFragment<T : ViewDataBinding>(
    @LayoutRes private val layoutRes: Int
) : Fragment() {

    // binding 객체
    private var _binding: T? = null
    val binding: T get() = _binding!!

    protected val navController: NavController
        get() = NavHostFragment.findNavController(this)

    /** 화면 inflate */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        return binding.root
    }

    /** view 생성 작업 */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
    }

    /** 프래그먼트 간 이동 */
    fun navigate(directions: NavDirections) {
        navController.navigate(directions)
    }

    /** 뒤로가기 */
    fun popBackStack() {
        navController.popBackStack()
    }

    /** view 초기화 작업 */
    abstract fun initView()
    /** viewModel 초기화 작업 */
    abstract fun initViewModels()

    /** 사용하지 않는다면 null 처리 */
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}