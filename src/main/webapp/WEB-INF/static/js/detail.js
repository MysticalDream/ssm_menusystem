import {$, getUrlParamObject} from "./common.js";
import {getMenuDetail} from "./api/menu.js";
import {getAllCategories} from "./api/category.js";


const urlObj = getUrlParamObject(window.location.search);

const menuId = urlObj.id;

layui.use(['rate', 'jquery', 'form', 'layer'], function () {
        const rate = layui.rate, $1 = layui.$, form = layui.form, layer = layui.layer;

        const formWrap = $1('.form_wrap select');

        getAllCategories().then((list) => {
            let option = "";
            Array.from(list).forEach((e) => {
                option += `<option value="${e.id}">${e.name}</option>`;
            })
            formWrap.append(option)
            form.render('select')
        })

        getMenuDetail(menuId).then(function (data) {
            rate.render({
                elem: '#test4'
                , value: data.score
                , half: true
                , text: true
                , length: 10
                , readonly: true
            })
            $1('#uploadDemoView img').attr('src', data.coverUrl)
            form.val('displayFilter', data)

            $1('#menuForm > div:nth-child(5) > div > div > div > i').hide()
        })

        $('#menuForm > div:nth-child(6) > button').addEventListener('click', function (e) {

            $1('#test5').html('');
            rate.render({
                elem: '#test5'
                , value: 0.0
                , half: true
                , text: true
                , length: 10,
                theme: '#FF8000' //自定义主题色
            })

            layer.open({
                type: 1,
                title: "评分",
                content: $1('#test5_wrap'),
                btn: ['确定', '取消'],
                yes: function () {

                },
                btn2: function () {

                }
            })
        })

    }
)





