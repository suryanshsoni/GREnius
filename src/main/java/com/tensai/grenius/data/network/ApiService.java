package com.tensai.grenius.data.network;

import com.tensai.grenius.data.network.response.BookmarkWordsResponse;
import com.tensai.grenius.data.network.response.LoginResponse;
import com.tensai.grenius.data.network.response.ProfileDetailResponse;
import com.tensai.grenius.data.network.response.ProfileResponse;
import com.tensai.grenius.model.Category;
import com.tensai.grenius.model.Institute;
import com.tensai.grenius.model.Titleinstitute;
import com.tensai.grenius.model.Word;

import java.util.ArrayList;
import java.util.List;
import com.tensai.grenius.model.Articles;
import com.tensai.grenius.model.WordOfDay;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Pavilion on 21-06-2017.
 */

public interface ApiService {
    @POST("/register")
    @FormUrlEncoded
    Observable<LoginResponse> login(@Field("fbId") String userLoginId, @Field("username") String username,
                                    @Field("accessToken") String accessToken, @Field("emailId") String emailId,
                                    @Field("city") String city);

    @POST("/register")
    @FormUrlEncoded
    Observable<LoginResponse> register(@Field("name") String name, @Field("password") String password,
                                       @Field("city") String city,
                                       @Field("emailId") String emailId);

    @POST("/words")
    @FormUrlEncoded
    Observable<List<Word>> downloadWords(@Field("index") int index,@Field("emailId") String emailId,@Field("sessionId") String sessionId);

    @POST("/articles")
    @FormUrlEncoded
    Observable<List<Articles>> getArticles(@Field("emailId") String emailId,@Field("sessionId") String sessionId);

    @POST("/dashboardArticles")
    @FormUrlEncoded
    Observable<List<Articles>> getDashboardArticles(@Field("emailId") String emailId,@Field("sessionId") String sessionId);

    @POST("/category")
    @FormUrlEncoded
    Observable<List<Category>> getCategory(@Field("emailId") String emailId,@Field("sessionId") String sessionId);

    @POST("/wordOfDay")
    @FormUrlEncoded
    Observable<WordOfDay> getWordOfDay(@Field("emailId") String emailId,@Field("sessionId") String sessionId);

    @POST("/addBookmark")
    Observable<BookmarkWordsResponse> sendBookmarkWords(@Body BookmarkBody bookmarkBody);

    @POST("/bookmarks")
    @FormUrlEncoded
    Observable<List<Word>> downloadBookmarkWords(@Field("userId") String emailId, @Field("sessionId") String sessionId);

    @POST("/login")
    @FormUrlEncoded
    Observable<LoginResponse> signIn(@Field("emailId") String emailId, @Field("password") String password);

    @POST("/monthlyWordOfDay")
    @FormUrlEncoded
    Observable<List<WordOfDay>> wordOfDays(@Field("emailId") String emailId,@Field("sessionId") String sessionId);

    @POST("/generatePasscode")
    @FormUrlEncoded
    Observable <BookmarkWordsResponse> generatePasskey(@Field("emailId") String emailId);

    @POST("/verifyPasscode")
    @FormUrlEncoded
    Observable <BookmarkWordsResponse>  verifyPasskey(@Field("emailId") String emailId, @Field("passcode") String passkey);

    @POST("/updatePassword")
    @FormUrlEncoded
    Observable <BookmarkWordsResponse> updatePassword(@Field("emailId") String emailId, @Field("password") String password, @Field("passcode") String passkey);

    @POST("/institutes")
    @FormUrlEncoded
    Observable<List<Institute>> getInstitutes(@Field("emailId") String emailId,@Field("sessionId") String sessionId);

    @POST("/titleinstitute")
    @FormUrlEncoded
    Observable<List<Titleinstitute>> getTitleInstitute(@Field("emailId") String emailId, @Field("sessionId") String sessionId);

    @POST("/updateProfile")
    @FormUrlEncoded
    Observable<ProfileResponse> updateProfile(@Field("emailId") String emailId, @Field("gender") String gender, @Field("dob") String dob, @Field("mobile") String mobile, @Field("city") String city, @Field("motive") String motive, @Field("work") String work);

    @POST("/getProfile")
    @FormUrlEncoded
    Observable<ProfileDetailResponse> getProfile(@Field("emailId") String emailId);
}
