/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import {NativeModules, NativeAppEventEmitter} from 'react-native';

const RongIM = NativeModules.RongIM;
const rongIMConnectionStatus = 'rongIMConnectionStatus';

export default class YRongIM {
    static connect(token){
        RongIM.connect(token);
    }

    static openCustomerService(serviceId){
        RongIM.openCustomerService(serviceId);
    }

    static addEventListener(handler){
        const listener = NativeAppEventEmitter.addListener(
            rongIMConnectionStatus,
            handler
        )
        return listener;
    }
}