/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import {NativeModules, NativeAppEventEmitter} from 'react-native';

const RongIM = NativeModules.RongIM;
const rongIMConnectionStatus = 'rongIMConnectionStatus';

NativeAppEventEmitter.addListener('rongIMConnectionStatus', msg => {
    eventEmitter.emit('connectionStatus', msg);
});
export default class YRongIM {
    static connect(token,  promise){
        RongIM.connect(token,promise);
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