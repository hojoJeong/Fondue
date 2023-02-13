package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.remote.datasource.ar.ArDataSource
import com.ssafy.fundyou.data.remote.mappers.toDomainModel
import com.ssafy.fundyou.domain.model.ar.ArImageModel
import com.ssafy.fundyou.domain.repository.ArRepository
import javax.inject.Inject

internal class ArRepositoryImpl @Inject constructor(private val arDataSource: ArDataSource) :
    ArRepository {
    override suspend fun getArImageList(fundingId: Long, itemId: Long): List<ArImageModel> =
        arDataSource.getArImageList(fundingId, itemId).map { it.toDomainModel() }


        override suspend fun saveArImage(fundingId: Long, itemId: Long, url: String): ArImageModel =
            arDataSource.saveArImage(ArImageSaveRequestDto(fundingId, itemId, url)).toDomainModel()
    }