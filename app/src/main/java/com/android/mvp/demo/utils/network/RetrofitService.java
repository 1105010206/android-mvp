package com.android.mvp.demo.utils.network;




import com.android.mvp.demo.data.protocol.request.base.GalbsMsg;
import com.android.mvp.demo.data.protocol.response.LogInResponse;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by win764-1 on 2016/12/12.
 */

public interface RetrofitService {
//    @GET("book/search")
//    Observable<Book> getSearchBooks(@Query("q") String name,
//                                    @Query("tag") String tag, @Query("start") int start,
//                                    @Query("count") int count);

    @POST("API/UserManage.ashx")
    Observable<LogInResponse> appLogIn(@Body GalbsMsg data);
}
