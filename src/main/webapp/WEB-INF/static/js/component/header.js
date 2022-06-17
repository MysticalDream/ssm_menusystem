import {axios, $, baseUrl} from "../common.js";
import {loadInfo} from "../index.js";


let header = {
    flag: {
        type: 0
    }
};

let $category = $('.category');

$category.addEventListener('click', function (e) {
    let target = e.target;
    if (target.tagName === 'A') {
        clearCurrent($category);
        target.className = 'current';
        header.flag = JSON.parse(target.dataset.flag);
        loadInfo();
    }
})

axios.get('/category/all').then(function (res) {
    const category = res.data.data;
    Array.from(category).forEach((e) => {
        let htmlAnchorElement = document.createElement('a');
        htmlAnchorElement.href = 'javascript:void(0)';
        htmlAnchorElement.textContent = e.name;
        htmlAnchorElement.dataset.flag = `{"type": 3, "id": ${e.id}}`;
        $category.appendChild(htmlAnchorElement);
    })

})

/**
 * 清除但前下划线
 * @param parent
 */
function clearCurrent(parent) {
    let children = parent.children;
    Array.from(children).forEach(e => {
        e.className = '';
    })
}

export default header;

const $logout = $('#logout');

if ($logout) {
    $logout.addEventListener('click', function (e) {
        axios.post("/sessions/destroy").then((res) => {
            if (res.data.code === 200) {
                window.location.href = '/login';
            }
        })
    })
}

$('body > header > nav > span:nth-child(1)').addEventListener('click', () => {
    window.location.href = baseUrl;
})
