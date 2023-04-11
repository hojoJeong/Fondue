package com.ssafy.fundyou.ui.token

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ssafy.fundyou.data.local.prefs.AuthSharePreference
import com.ssafy.fundyou.databinding.ActivityDeleteTokenBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DeleteTokenActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDeleteTokenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDeleteTokenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        deleteToken()
    }

    /** 토큰 삭제 */
    private fun deleteToken(){
        binding.btnDeleteToken.setOnClickListener {
            val sp = AuthSharePreference(this)
            sp.clearAuthPreference()
        }
    }
}