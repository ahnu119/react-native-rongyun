/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import {NativeModules, NativeAppEventEmitter} from 'react-native';

const RongIMYl = NativeModules.IMModule;
const rongIMConnectionStatus = 'rongIMConnectionStatus';

export default class YRongIM {
    static connect(token){
        console.log("---------",token);
        RongIMYl.connect(token);
    }

    static openCustomerService(serviceId){
        RongIMYl.openCustomerService(serviceId);
    }

    static addEventListener(handler){
        const listener = NativeAppEventEmitter.addListener(
            rongIMConnectionStatus,
            handler
        )
        return listener;
    }
}