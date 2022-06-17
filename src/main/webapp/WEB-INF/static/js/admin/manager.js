import {$, omit} from "../common.js";
import * as CategoryApi from "../api/category.js";
import * as MenuApi from "../api/menu.js";


layui.use(['upload', 'element', 'layer', 'table', 'form'], function () {

    const table = layui.table,
        upload = layui.upload
        , element = layui.element
        , layer = layui.layer,
        form = layui.form,
        $1 = layui.$
    ;


    //生成select列表

    const formWrap = $1('.form_wrap select');

    function loadOption(selected) {
        CategoryApi.getAllCategories().then(function (list) {
            let option = "";
            Array.from(list).forEach((e) => {
                option += `<option value="${e.id}" `;
                if (e.id === selected) {
                    option += `selected`;
                }
                option += `>${e.name}</option>`;
            })
            console.log(option, formWrap)
            formWrap.append(option)
            form.render('select')
        })
    }

    loadOption(1);
    //表格渲染
    table.render({
            id: "menu_manager",
            elem: '#table_container',
            url: '/menus/list',
            height: $('body > main > section > div:first-child').clientHeight - $('body > main > section > div:first-child > div.custom_wrap').clientHeight,
            request: {
                pageName: 'pageNum' //页码的参数名称，默认：page
                , limitName: 'pageSize' //每页数据量的参数名，默认：limit
            },
            response: {
                statusName: 'code' //规定数据状态的字段名称，默认：code
                , statusCode: 200 //规定成功的状态码，默认：0
            },
            parseData: function (res) { //res 即为原始返回的数据
                return {
                    "code": res.code, //解析接口状态
                    "msg": res.message, //解析提示文本
                    "count": res.data.total, //解析数据长度
                    "data": res.data.list //解析数据列表
                };
            },
            toolbar: '#toolbarDemo' //开启头部工具栏，并为其绑定左侧模板
            , defaultToolbar: ['filter', 'exports', 'print', { //自定义头部工具栏右侧图标。如无需自定义，去除该参数即可
                title: '提示'
                , layEvent: 'LAYTABLE_TIPS'
                , icon: 'layui-icon-tips'
            }]
            , title: '菜谱数据表'
            , cols: [[
                {type: 'checkbox', fixed: 'left'}
                , {field: 'id', title: 'ID', width: 120, sort: true, fixed: true}
                , {field: 'menuName', title: '菜谱名', width: 120}
                , {
                    field: 'coverUrl',
                    title: '图片',
                    align: "center",
                    templet: `<div><img src ="{{ d.coverUrl }}" style = "width: 100%;height: 100%"></div>`
                }
                , {field: 'intro', title: '介绍', width: 200, sort: true}
                , {field: 'score', title: '分数'}
                , {field: 'createTime', title: '创建时间', sort: true, width: 200}
                , {field: 'updateTime', title: '最后更新时间', sort: true, width: 200}
                , {field: 'category', title: '菜谱类别', width: 120}
                , {field: 'userName', title: '创建者', sort: true, width: 135}
                , {fixed: 'right', title: '操作', toolbar: '#barDemo', width: 178}
            ]]
            ,
            page: {
                groups: 7
            }
            ,
            error: function (errorObj, content) {
                console.log(errorObj, content)
            }
        }
    );


    //头工具栏按钮激活事件
    let active = {
        reload: function () {
            const demoReload = $1('#demoReload');

            //执行重载
            table.reload('menu_manager', {
                page: {
                    curr: 1 //重新从第 1 页开始
                }
                , where: {
                    key: {
                        id: demoReload.val()
                    }
                }
            });
        },
        setTop: function () {
            const that = this;
            //多窗口模式，层叠置顶
            layer.open({
                type: 1 //此处以iframe举例
                ,
                title: '上传菜谱',
                resize: true
                // ,
                // area: ['550px']
                ,
                shade: 0
                ,
                maxmin: true,

                content: $1('.form_wrap')

                ,
                btn: ['确认', '取消'] //只是为了演示
                ,
                yes: function () {
                    $1('#submitBtn').click()
                }
                ,
                btn2: function () {
                    layer.closeAll();
                }
                ,
                zIndex: layer.zIndex //重点1
                ,
                success: function (layero, index) {
                    layer.full(index);
                    layer.setTop(layero); //重点2. 保持选中窗口置顶
                    $1('.form_wrap').find('input,textarea,select').prop('disabled', false);
                    if ($1('#click_wrap').hasClass('layui-hide')) {
                        $1('#click_wrap').removeClass('layui-hide');
                    }
                    layui.$('#uploadDemoView').addClass('layui-hide').find('img').attr('src', '');
                    $1('#menuForm')[0].reset();
                    form.render();
                }
                ,
                end: function () {

                }
            });
        }
    };

    //表格重载
    $1('.custom_wrap .layui-btn').on('click', function () {
        const type = $1(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

    //头工具栏事件
    table.on('toolbar(test)', function (obj) {
        console.log('头工具栏事件', obj)
        const checkStatus = table.checkStatus(obj.config.id);
        console.log('头工具栏事件', checkStatus)
        switch (obj.event) {
            case 'getCheckData':
                let data = checkStatus.data;
                layer.alert(JSON.stringify(data));
                break;
            case 'deleteSelected': {
                let data = checkStatus.data;

                const ids = data.map((e) => e.id);
                if (data.length) {
                    layer.confirm('确定删除选中的菜谱吗?', function (index) {
                        MenuApi.deleteMenuBatch(ids).then((opt) => {
                            if (!opt) {
                                layer.msg('删除失败');
                            }
                            table.reload('menu_manager');
                            layer.close(index);
                        })

                    })
                } else {
                    layer.msg('没有选中菜谱');
                }
            }
                break;
            case 'isAll':
                layer.msg(checkStatus.isAll ? '全选' : '未全选');
                break;

            //自定义头工具栏右侧图标 - 提示
            case 'LAYTABLE_TIPS':
                layer.alert('这是工具栏右侧自定义的一个图标按钮');
                break;
        }
        ;
    });


    //表格操作
    table.on('tool(test)', function (obj) {
        const data = obj.data;
        console.log(data, obj)
        if (obj.event === 'detail') {
            MenuApi.getMenuDetail(data.id).then((menuDetail) => {
                layer.open({
                    type: 1 //此处以iframe举例
                    ,
                    title: '查看菜谱',
                    resize: true,
                    shade: 0
                    ,
                    maxmin: true,
                    content: $1('.form_wrap'),
                    success: function (layero, index) {
                        layer.full(index);
                        layer.setTop(layero); //重点2. 保持选中窗口置顶
                        const coverUrl = menuDetail.coverUrl;
                        menuDetail = omit('coverUrl', menuDetail);
                        form.val('formFilter', menuDetail)
                        if (!$1('#click_wrap').hasClass('layui-hide')) {
                            $1('#click_wrap').addClass('layui-hide');
                        }
                        layui.$('#uploadDemoView').removeClass('layui-hide').find('img').attr('src', coverUrl);
                        $1('.form_wrap').find('input,textarea,select').prop('disabled', true);
                        form.render();
                    }
                });

            });

            // layer.msg('ID：' + data.id + ' 的查看操作');
        } else if (obj.event === 'del') {
            layer.confirm(`确定删除{${data.menuName}}菜谱吗?`, function (index) {
                MenuApi.deleteMenu(data.id).then(
                    (isSuccess) => {
                        if (isSuccess) {
                            table.reload('menu_manager');
                            layer.close(index);
                        }
                    }
                )
            });
        } else if (obj.event === 'edit') {
            MenuApi.getMenuDetail(data.id).then((menuDetail) => {
                layer.open({
                    type: 1 //此处以iframe举例
                    ,
                    title: '修改菜谱',
                    resize: true,
                    shade: 0
                    ,
                    maxmin: true,
                    btn: ['更新', '取消'] //只是为了演示
                    ,
                    yes: function () {
                        layer.msg()
                    }
                    ,
                    btn2: function () {
                        layer.closeAll();
                    },
                    content: $1('.form_wrap'),
                    success: function (layero, index) {
                        layer.full(index);
                        layer.setTop(layero); //重点2. 保持选中窗口置顶
                        const coverUrl = menuDetail.coverUrl;
                        menuDetail = omit('coverUrl', menuDetail);
                        form.val('formFilter', menuDetail)
                        if (!$1('#click_wrap').hasClass('layui-hide')) {
                            $1('#click_wrap').addClass('layui-hide');
                        }
                        layui.$('#uploadDemoView').removeClass('layui-hide').find('img').attr('src', coverUrl);
                        $1('.form_wrap').find('input,textarea,select').prop('disabled', false);
                        form.render();
                    }
                });

            });
            // layer.alert('编辑行：<br>' + JSON.stringify(data))
        }
    });

    //拖拽上传
    upload.render({
        elem: $1('#test10')
        , url: '/upload/menu_img' //此处用的是第三方的 http 请求演示，实际使用时改成您自己的上传接口即可。
        , accept: "images"
        , exts: "jpg|png|jpeg"
        , field: "coverUrl"
        , done: function (res) {
            layer.msg('上传成功');
            console.log(res)
            if (!$1('#click_wrap').hasClass('layui-hide')) {
                $1('#click_wrap').addClass('layui-hide');
            }
            layui.$('#uploadDemoView').removeClass('layui-hide').find('img').attr('src', res.data);
        }
    });

    //监听导航点击
    element.on('nav(nav)', function (elem) {
        console.log(elem)
        layer.msg(elem.text());
        if (elem.text() === '菜谱管理') {
            $1('#tab2').addClass('layui-hide')
            $1('#tab1').removeClass('layui-hide')
        } else {
            $1('#tab2').removeClass('layui-hide')
            $1('#tab1').addClass('layui-hide')
        }
    });

    //表单提交
    form.on('submit(go)', function (data) {
        const field = data.field;
        console.log(field) //当前容器的全部表单字段，名值对形式：{name: value}
        if (!field.coverUrl) {
            layer.msg("请先上传图片", {zIndex: layer.zIndex});
        }
        return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    });

});
