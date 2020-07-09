package com.example.movieapp.model.Cast

import com.google.gson.annotations.SerializedName

data class CastResponse(

	@field:SerializedName("cast")
	val cast: List<CastItem>,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("crew")
	val crew: List<CrewItem>
)

data class CastItem(

	@field:SerializedName("cast_id")
	val castId: Int,

	@field:SerializedName("character")
	val character: String,

	@field:SerializedName("gender")
	val gender: Int,

	@field:SerializedName("credit_id")
	val creditId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("profile_path")
	val profilePath: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("order")
	val order: Int
)

data class CrewItem(

	@field:SerializedName("gender")
	val gender: Int,

	@field:SerializedName("credit_id")
	val creditId: String,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("profile_path")
	val profilePath: String,

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("department")
	val department: String,

	@field:SerializedName("job")
	val job: String
)
