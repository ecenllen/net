package com.viewstreetvr.net.net;

import android.util.Log;

import com.viewstreetvr.net.net.common.CommonApiService;
import com.viewstreetvr.net.net.common.dto.DownloadFileDto;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author: liaohaiping
 * Time: 2019-06-15
 * Description:
 */
public class HttpUtils {
    public static final String BASE_URL = "https://api.xgkjdytt.cn";
    public static final String API_PREFIX = "/xly/webcloud/";
    private static final int DEFAULT_TIMEOUT = 6;
    private OkHttpClient httpClient;
    private Retrofit retrofit;

    private HttpUtils() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);
        clientBuilder.interceptors().clear();
        clientBuilder.addInterceptor(new CommonInterceptor());

        httpClient = clientBuilder.build();
        retrofit = new Retrofit.Builder()
                .client(httpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(new CommonCallAdapterFactory())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    private static class SingleTon {
        static HttpUtils retrofitClient = new HttpUtils();
    }

    public static HttpUtils getInstance() {
        return SingleTon.retrofitClient;
    }

    public <T> T getService(Class<T> tClass) {
        return retrofit.create(tClass);
    }


    public static boolean writeResponseBodyToDisk(ResponseBody body, String savePath) {
        try {
            File futureStudioIconFile = new File(savePath);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                    Log.d("lhp", "file download: " + fileSizeDownloaded + " of " + fileSize);
                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }


    public static DataResponse<Long> uploadFile(File file) {
        // ?????? RequestBody??????????????? ??????RequestBody
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("file", file.getName(), requestFile);

//        // ????????????
//        String descriptionString = "hello, ??????????????????";
//        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), descriptionString);

        //????????????
        DataResponse<Long> response = getInstance().getService(CommonApiService.class).uploadFile(body);
        return response;
    }

    public static ApiResponse downloadFile(long fileId, String savePath) {

        //????????????
        Call<ResponseBody> response = getInstance().getService(CommonApiService.class).downloadFile(new DownloadFileDto(fileId));
        try {
            Response<ResponseBody> execute = response.execute();
            if (execute.isSuccessful()) {
                if (writeResponseBodyToDisk(execute.body(), savePath)) {
                    return ApiResponse.ok();
                } else {
                    return ApiResponse.fail("????????????");
                }
            } else {
                return ApiResponse.fail("????????????");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return ApiResponse.fail("???????????????" + e.getMessage());
        }
    }


}
