package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.remote.datasource.ar.ArDataSource
import com.ssafy.fundyou.data.remote.mappers.toDomainModel
import com.ssafy.fundyou.domain.model.ar.ArImageModel
import com.ssafy.fundyou.domain.repository.ArRepository
import javax.inject.Inject

internal class ArRepositoryImpl @Inject constructor(private val arDataSource: ArDataSource) :
    ArRepository {
    override suspend fun getArImageList(fundingItemId: Long): List<ArImageModel> =
        arDataSource.getArImageList(fundingItemId).map { it.toDomainModel() }


    override suspend fun saveArImage(fundingItemId: Long, url: String): ArImageModel =
        arDataSource.saveArImage(fundingItemId, url).toDomainModel()

}