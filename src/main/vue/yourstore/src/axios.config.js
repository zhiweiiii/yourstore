import axios from 'axios'

//设置请求前的参数
axios.interceptors.request.use(config => {
    //设置为ajax请求（异步http请求），若 'X-Requested-With' 的值为null，则为同步；
    // config.header['X-Requested-With'] = 'XMLHttpRequest';
    config.url = "api/" + config.url;
    return config;
})
//
////设置响应后的状态处理
//axios.interceptors.response.use((response) => {
//    const data = response.data;
//    if (data.status == 0) {
//        return data
//    } else if (data.stateCode == 'jb_000000') {
//        return data.data
//    } else {
//        const err = new Error(data.description)
//        err.data = data
//        err.response = response
//
//        throw err
//    }
//}, (err) => {
//    if (err && err.response) {
//        switch (err.response.status) {
//            case 400:
//                err.message = '请求错误'
//                break
//            case 403:
//                err.message = '拒绝访问'
//                break
//            case 404:
//
//                break
//        }
//        return Promise.reject(err)
//    }
//})
export default axios