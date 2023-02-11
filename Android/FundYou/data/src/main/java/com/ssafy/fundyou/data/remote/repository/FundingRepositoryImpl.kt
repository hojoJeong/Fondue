package com.ssafy.fundyou.data.remote.repository

import com.ssafy.fundyou.data.remote.datasource.funding.FundingDataSource
import com.ssafy.fundyou.data.remote.datasource.funding.dto.FundingCreateRequestDto
import com.ssafy.fundyou.data.remote.mappers.toDomainModel
import com.ssafy.fundyou.domain.model.funding.FundingInfoModel
import com.ssafy.fundyou.domain.repository.FundingRepository
import javax.inject.Inject

internal class FundingRepositoryImpl @Inject constructor(
    private val fundingDataSource: FundingDataSource
) : FundingRepository {
    override suspend fun getFundingInfo(fundingId: Long) : FundingInfoModel = fundingDataSource.getFundingInfo(fundingId).toDomainModel()
    override suspend fun createFunding(endDate: Long) : Long{
        val request = FundingCreateRequestDto(endDate)
        return fundingDataSource.createFunding(request)
    }
}