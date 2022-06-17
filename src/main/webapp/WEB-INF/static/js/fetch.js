const TIME_OUT = 6000; // 超时时间
const TOKEN = 'token'; // 自定义token
const ContentType = { // ContentType 映射表
    json: 'application/json;charset=UTF-8', // json数据格式
    form: 'application/x-www-form-urlencoded; charset=UTF-8', // 表单数据格式
    download: 'application/octet-stream' // 二进制文件流格式，用于download
};

/**
 * @name 基础fetch
 * @param {string} url 请求path
 * @param {Object} options feich请求配置
 * @returns
 */
function baseFetch(url, options) {
    // 基础配置
    const baseOptions = {
        method: "GET", // 默认GET请求
        headers: { // 默认请求头
            "Content-type": ContentType.json // 默认json数据格式
        },
        /**
         * include:
         * 默认不论是不是跨域的请求
         * 总是发送请求资源域在本地的 cookies、HTTP Basic authentication 等验证信息
         * omit: 从不发送cookies
         * same-origin: 同源发送cookies
         */
        credentials: 'include'
    };

    // merge options
    options = Object.assign(baseOptions, options);

    const {method, body} = options;

    // get请求没有请求体，需要将参数拼接到url上
    if (method === 'GET' && typeof body === 'object') {
        const paramsArray = [];
        Object.keys(body).forEach((key) =>
            paramsArray.push(key + "=" + encodeURIComponent(body[key]))
        );
        if (url.search(/\?/) === -1) {
            url += "?" + paramsArray.join("&");
        } else {
            url += "&" + paramsArray.join("&");
        }

        // 删除请求体属性
        delete options.body;
    }

    // request interceptors
    // 查看当前本地是否有token, 如果有，设置自定义headers中TOKEN
    const token = localStorage.getItem(TOKEN);
    if (token) {
        options.headers["TOKEN"] = token;
    }

    // 利用 Promise.race 实现超时拦截
    return Promise.race([
        new Promise((resolve, reject) => {
            setTimeout(() => {
                reject(new Error('request timeout'));
            }, TIME_OUT);
        }),
        new Promise((resolve, reject) => {
            window.fetch(url, options)
                .then(async res => {
                    // response interceptors 状态码拦截，对应异常状态吗do something
                    if (!/^(2|3)\d{2}$/.test(res.status)) {
                        switch (res.status) {
                            case 401:     //当前用户需要验证（一般是未登陆）
                                break    //一般可以弹出遮盖层，或者回到登陆页面
                            case 403:     // 服务器理解请求，但是拒绝执行，一般是token，session过期
                                localStorage.removeItem(TOKEN)
                                break
                            case 404:     // 找不到页面(请求失败，资源在服务器上未找到)，可以给一个友好的提示
                                break
                        }
                        return Promise.reject(res)
                    }
                    // response interceptors cookies拦截器，存入token
                    if (res.headers['set-cookies']) {
                        localStorage.setItem(TOKEN, res.headers['set-cookies']);
                    }

                    // return data
                    const data = options.headers['Content-type'] === ContentType.download ? res.blob() : res.json();
                    resolve(data);
                })
                .catch(err => {
                    reject(err);
                });
        })
    ])
}

export const fetchGet = (url, params) => {
    return baseFetch(url, {body: params});
}

export const fetchPost = (url, params) => {
    return baseFetch(url, {method: 'POST', body: JSON.stringify(params)});
}

export const fetchDownload = (url, params) => {
    return baseFetch(url, {
        method: 'POST',
        body: JSON.stringify(params),
        headers: {
            "Content-type": ContentType.download
        }
    });
}