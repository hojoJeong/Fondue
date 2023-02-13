package com.ssafy.fundyou.domain.repository

import com.ssafy.fundyou.domain.model.ar.ArImageModel

interface ArRepository {
    suspend fun getArImageList(fundingId: Long, itemId: Long): List<ArImageModel>
    suspend fun saveArImage(fundingId: Long, itemId: Long, url: String): ArImageModel
}