import {$, axios} from '../common.js';
//主要逻辑
let itemWidth = 200;
let itemHeight = 200;
//页面展示数量
let pageSize;
//当前页数
let pageNow = 1;
//已经加载的项目数
let loadedItemNum = 0n;
//所有的项目数
let allItemNum;

/**
 * 入口
 */
// window.onload = function () {
//     pageSize = computedPageSize();
//     let loadingAnimation = createLoadingAnimation($('div.item_wrap'));
//     getItems(pageNow, pageSize).then(
//         result => {
//             loadingAnimation.parentNode.removeChild(loadingAnimation);
//             loadItem($('div.item_wrap'), result)
//         }
//     )
// }

// window.onresize = function () {
//     pageSize = computedPageSize();
// }


async function getItems(num, pageSize) {
    let item = {
        url: '/menusystem/resources/img/test.png',
        name: '爆炒龙虾',
        score: ''
    }
    let items = [];
    for (let i = 0; i < pageSize - 1; i++) {
        item.score = (Math.random() * 10).toFixed(1);
        items.push(JSON.parse(JSON.stringify(item)));
    }
    //已经加载的项目数量
    loadedItemNum += BigInt(items.length);
    //当前页加1
    pageNow++;

    //模拟延时
    return await new Promise(function (resolve, reject) {
        setTimeout(() => {
            resolve(items);
        }, Math.floor(Math.random() * 4001) + 1000)
    })
    // return items;
}

/**
 * 计算每页展示的数量
 * @returns {number}
 */
function computedPageSize() {
    let main = $('main');
    let w = Math.floor(main.clientWidth / itemWidth);
    let h = Math.ceil(main.clientHeight / itemHeight);
    return w * h;
}

/**
 * 加载项目
 *
 * @param target
 * @param items
 */
function loadItem(target, items) {
    let templateItem = $('#template_item');
    let content = templateItem.content;
    let imgEle = content.querySelector('a img');
    let itemTitle = content.querySelector('a .item_title');
    let score = content.querySelector('a .item_corner>span');

    Array.from(items).forEach(item => {
        imgEle.src = item.url;
        itemTitle.textContent = item.name;
        score.textContent = item.score;
        let clone = document.importNode(content, true);
        target.appendChild(clone);
    })

    if (items.length < pageSize) {
        for (let i = 0; i < pageSize - items.length; i++) {
            let htmlAnchorElement = document.createElement('a');
            htmlAnchorElement.className = 'item';
            htmlAnchorElement.style.visibility = 'hidden';
            target.appendChild(htmlAnchorElement);
        }
    }
    createLoadMoreButton(target);
}

/**
 * 加载按钮生成
 *  <button class="load_more">加载更多</button>
 */
function createLoadMoreButton(target) {
    //创建按钮
    let buttonElement = document.createElement('button');
    buttonElement.className = 'load_more';
    buttonElement.textContent = '加载更多';
    buttonElement.onclick = function () {
        this.parentNode.removeChild(this);
        let loadingAnimation = createLoadingAnimation(target);
        getItems(pageNow, pageSize).then(
            result => {
                loadingAnimation.parentNode.removeChild(loadingAnimation);
                loadItem($('div.item_wrap'), result)
            }
        )
    }
    target.appendChild(buttonElement);
}

/**
 * 创建加载动画
 * @param target
 * @returns {any}
 */
function createLoadingAnimation(target) {
    let $templateLoading = $('#template_loading');
    let content = $templateLoading.content;
    let loadingNode = document.importNode(content, true);
    target.appendChild(loadingNode);
    return target.querySelector('.loading');
}


