package com.rongyun;

import android.util.Log;

import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Message;

/**
 * Created by yanghaibo on 2017/3/27.
 */

public class IMModule extends ReactContextBaseJavaModule implements  RongIMClient.ConnectionStatusListener {
    static boolean isIMClientInited = false;

    boolean hostActive = true;

    public IMModule(ReactApplicationContext reactContext) {
        super(reactContext);

        if (!isIMClientInited) {
            isIMClientInited = true;
            RongIM.init(reactContext.getApplicationContext());
        }

    }

    @Override
    public String getName() {
        return "RCTRongIM";
    }


    RongIM client = null;

    @ReactMethod
    public void connect(String token){
        if (client != null) {
            return;
        }
        client = RongIM.connect(token, new RongIMClient.ConnectCallback() {
            /**
             * Token 错误，在线上环境下主要是因为 Token 已经过期，您需要向 App Server 重新请求一个新的 Token
             */
            @Override
            public void onTokenIncorrect() {
            }

            /**
             * 连接融云成功
             * @param userid 当前 token
             */
            @Override
            public void onSuccess(String userid) {
                Log.i("------------","--------"+userid);
            }

            /**
             * 连接融云失败
             * @param errorCode 错误码，可到官网 查看错误码对应的注释
             */
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
            }
        });
    }

    @ReactMethod
    public void openCustomerService(String token,String serviceId){
        try{
            Log.i("------------","--------"+serviceId);
            if(client!=null) {
                //connect(token,promisse);
                RongIM.getInstance().startConversation(getCurrentActivity(), Conversation.ConversationType.CUSTOMER_SERVICE, serviceId, "客服");
            }
        }catch(Exception e){
            throw new JSApplicationIllegalArgumentException(
                    "无法打开activity页面: "+e.getMessage());
        }
    }


    @ReactMethod
    public void logout(final Promise promise){
        if (client == null) {
            return;
        }
        client.logout();
        client = null;
    }

    @ReactMethod
    public void disconnect(final Promise promise){
        if (client == null) {
            return;
        }
        client.disconnect();
    }

    @ReactMethod
    public void getLatestMessages(String type, String targetId, int count, final Promise promise) {
        if (client == null) {
            return;
        }
        client.getLatestMessages(Conversation.ConversationType.valueOf(type.toUpperCase()), targetId, count, new RongIMClient.ResultCallback<List<Message>>() {

            @Override
            public void onSuccess(List<Message> messages) {
            }

            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
            }
        });
    }

    @ReactMethod
    public void removeConversation(final String type, final String targetId, final Promise promise) {
        if (client == null) {
            return;
        }
        client.removeConversation(Conversation.ConversationType.valueOf(type.toUpperCase()), targetId, new RongIMClient.ResultCallback<Boolean>(){
            @Override
            public void onError(RongIMClient.ErrorCode errorCode) {
            }

            @Override
            public void onSuccess(Boolean message) {
            }
        });
    }

    @Override
    public void onChanged(ConnectionStatus connectionStatus) {

    }
}
