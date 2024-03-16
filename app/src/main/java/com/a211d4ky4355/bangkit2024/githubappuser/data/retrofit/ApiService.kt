package com.a211d4ky4355.bangkit2024.githubappuser.data.retrofit

import com.a211d4ky4355.bangkit2024.githubappuser.data.response.GithubResponse
import com.a211d4ky4355.bangkit2024.githubappuser.data.response.UserDetailResponse
import com.a211d4ky4355.bangkit2024.githubappuser.data.response.UserItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    fun searchUser(
        @Query("q") query: String
    ): Call<GithubResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String
    ): Call<UserDetailResponse>

    @GET("users/{username}/followers")
    fun getFollowers(
        @Path("username") username: String
    ): Call<List<UserItem>>

    @GET("users/{username}/following")
    fun getFollowing(
        @Path("username") username: String
    ): Call<List<UserItem>>
}