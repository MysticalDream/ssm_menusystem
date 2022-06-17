import {$, debounce, axios} from './common.js';
import header from "./component/header.js";
//页面展示数量
let pageSize = 12;
//项目卡片固定宽高
let itemWidth = 200;
let itemHeight = 200;
//容器可容纳横向数量和纵向数量
let w, h;
//已经加载的项目数
let loadedItemNum = 0n;

/**
 * 获取项目布局
 * @param data
 * @returns {string}
 */
function getItemLayout(data) {
    let str = `<a class="item" href="/detailPage?id=${data.id}">
            <div class="item_img">
                <img lay-src="${data.coverUrl}">
            </div>
            <div class="item_title_wrap">
                <span class="item_title">${data.menuName}</span>
            </div>
            <div class="item_corner">
                <span>${data.score}</span>
            </div>
        </a> `;
    return str;
}

/**
 * 获取空白项目布局
 * @returns {string}
 */
function getBlankItemLayout() {
    let str = `<a class="item blank_item" href="javascript:void(0)" style="visibility: hidden;"></a> `;
    return str;
}

/**
 * 计算每页展示的数量
 * @returns {number}
 */
function computedWH() {
    let main = $('main');
    w = Math.floor(main.clientWidth / itemWidth);
    h = Math.ceil(main.clientHeight / itemHeight);
    replenish();
}

/**
 * 添加空白占位
 */
function replenish() {
    clearBlank();
    let bw = BigInt(w);
    if (!bw) {
        bw = 1n;
    }
    let r = loadedItemNum % bw;
    let $div = $('div.item_wrap');
    const parser = new DOMParser();
    if (r) {
        let s = bw - r;
        for (let i = 0; i < s; i++) {
            $div.insertBefore(parser
                    .parseFromString(getBlankItemLayout(), "text/html").body.firstChild,
                $div.lastElementChild);
        }
    }
}

/**
 * 清除空白项目
 */
function clearBlank() {
    let $div = $('div.item_wrap');
    let sibling = $div?.lastElementChild?.previousElementSibling;
    while (sibling && sibling.className.includes("blank_item")) {
        $div.removeChild(sibling);
        sibling = $div.lastElementChild.previousElementSibling;
    }
}

window.addEventListener("load", computedWH);
window.addEventListener("resize", debounce(computedWH, 500));


let loadInfo = function () {
    clearInfo();
    layui.use('flow', function () {
        let flow = layui.flow;
        flow.load({
            elem: '#item_wrap' //流加载容器
            , scrollElem: '#main'
            , isAuto: false
            , isLazyimg: true
            , done: function (page, next) { //加载下一页
                //模拟插入
                getItems(page, pageSize).then(r => {
                    clearBlank();
                    const lis = [];
                    Array.from(r.list).forEach(data => {
                        lis.push(getItemLayout(data));
                    })
                    next(lis.join(''), page < r.pages); //假设总页数为 6
                    loadedItemNum += BigInt(r.list.length);
                    computedWH();
                });
            }
        });

    })
}

loadInfo();

export {loadInfo};

// async function getItems(num, pageSize) {
//     let item = {
//         url: '/menusystem/resources/img/test.png',
//         name: '爆炒龙虾',
//         score: ''
//     }
//     let items = [];
//     for (let i = 0; i < pageSize; i++) {
//         item.score = (Math.random() * 10).toFixed(1);
//         items.push(JSON.parse(JSON.stringify(item)));
//     }
//     //模拟延时
//     return await new Promise(function (resolve, reject) {
//         setTimeout(() => {
//             resolve(items);
//         }, Math.floor(Math.random() * 401) + 100)
//     })
//     // return items;
// }

async function getItems(num, pageSize) {
    const param = {
        pageNum: num,
        pageSize: pageSize
    }
    Object.assign(param, header.flag)
    let data = await axios.get('/menus/condition', {params: param});
    data = data.data.data;
    return data;
}

export function clearInfo() {
    $('#item_wrap') && ($('#item_wrap').innerHTML = '');
}