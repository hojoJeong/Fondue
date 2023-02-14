package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.ar.ArImageModel

interface ArRepository {
    suspend fun getArImageList(fundingItemId: Long): List<ArImageModel>
    suspend fun saveArImage(fundingItemId: Long, url: String): ArImageModel
}