import * as fetchModule from "./fetch.js";
import axios from "./axios.js";

axios.default.baseURL = "http://localhost:8080";

const baseUrl = '/';

/**
 * 获取元素
 *
 * @param matcher
 * @returns {*|NodeListOf<*>}
 */
export function $(matcher) {
    const doms = document.querySelectorAll(matcher);
    return doms.length === 0 ? null : doms.length === 1 ? doms[0] : doms;
}

/**
 * 防抖函数 执行最后一次
 * @param {*} func 业务代码
 * @param {*} delay 延时
 * @returns
 */
export function debounce(func, delay) {
    let t = null;
    return function () {
        if (t !== null) {
            clearTimeout(t);
        }
        t = setTimeout(() => {
            func.call(this);
        }, delay);
    };
}

/**
 * 节流函数 减少执行次数
 * @param {*} func 业务代码
 * @param {*} delay 延时
 */
export function throttle(func, delay) {
    let flag = true;
    return function () {
        if (flag) {
            setTimeout(() => {
                func.call(this);
                flag = true;
            }, delay);
        }
        flag = false;
    };
}

/**
 * 校验只要是数字（包含正负整数，0以及正负浮点数）就返回true
 * @param val
 * @returns {boolean}
 */
export function isNumber(val) {
    const reg = /^[0-9]+\.?[0-9]*$/;
    return reg.test(val);
}

/**
 * 获取url的参数
 * @param url
 * @returns {Object}
 */

export function getUrlParamObject(url) {
    const params = url.slice(url.indexOf('?') + 1, url.length);
    const group = params.split('&');
    const data = {};
    for (const index in group) {
        const arr = group[index].split('=');
        data[arr[0]] = isNumber(arr[1]) ? parseFloat(arr[1]) : arr[1];
    }
    return data;
}


const omit = (prop, {[prop]: _, ...rest}) => rest;

export {
    fetchModule, axios, omit, baseUrl
}

