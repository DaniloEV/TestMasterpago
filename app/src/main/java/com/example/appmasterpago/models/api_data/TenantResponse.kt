package com.example.appmasterpago.models.api_data

data class TenantResponse(var totalCount:Int,
                          var items:MutableList<TenantItemData>)


data class TenantItemData(var tenancyName:String,
                          var customCssJson:String,var image:String)
