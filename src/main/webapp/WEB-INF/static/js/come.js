import {axios, $} from "./common.js";

const signInBtn = $("#signIn");
const signUpBtn = $("#signUp");
const fistForm = $("#form1");
const secondForm = $("#form2");
const container = $(".container");

signInBtn.addEventListener("click", () => {
    container.classList.remove("right-panel-active");
    window.history.replaceState({page: "login"}, "title", "login");
});

signUpBtn.addEventListener("click", () => {
    container.classList.add("right-panel-active");
    window.history.replaceState({page: "register"}, "title", "register");
});


fistForm.addEventListener("submit", (e) => e.preventDefault());
secondForm.addEventListener("submit", (e) => e.preventDefault());


//核心函数
const msg1 = $('#regi_msg');
const msg2 = $('#log_msg');
const register_btn = $('#reg_btn');
const log_btn = $('#log_btn');
let flag = true;

const homeBtn = $('.btn.home');
homeBtn.forEach(e => {
    e.addEventListener('click', (e1) => {
        let target = e1.target;
        window.location.href = target.dataset.home;
    });
});
//注册
register_btn.onclick = function () {
    if (!flag) return;
    const password1 = $('#form1 > input:nth-child(4)').value.trim();
    const password2 = $('#form1 > input:nth-child(5)').value.trim();
    const userName = $('#form1 > input:nth-child(3)').value.trim();
    if (userName === "") {
        msg1.innerText = '用户名不能为空';
        return;
    }
    if (!userName.match(/^(\w){1,32}$/)) {
        msg1.innerText = '用户名只能由长度为1-32位数字、26个英文字母或者下划线组成';
        return;
    }
    if (password1 === "") {
        msg1.innerText = "密码不能为空";
        return;
    }
    if (!password1.match(/^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])[a-zA-Z0-9]{8,20}$/)) {
        msg1.innerText = "密码必须包含大小写字母和数字的组合，不能使用特殊字符，长度在 8-20 之间";
        return;
    }
    if (password1 !== password2) {
        msg1.innerText = "两次输入的密码不相等";
        return;
    }
    msg1.innerText = "";

    var data = {
        username: userName,
        password1: password1,
        password2: password2
    };
    flag = false;


    // postData({
    //     url: "http://localhost:8080/blog/add.c",
    //     data: data,
    //     callback: function (data) {
    //         if (data.code === 302) {
    //             window.location.href = data.data;
    //         } else {
    //             if (!data.success) {
    //                 msg1.innerText = data.message;
    //             }
    //         }
    //         flag = true;
    //     }
    // });
}
//登录
log_btn.onclick = function () {
    if (!flag) return;
    const userName = $('#form2 > input:nth-child(3)').value.trim();
    const password = $('#form2 > input:nth-child(4)').value.trim();

    if (userName === "") {
        msg2.innerText = '用户名不能为空';
        return;
    }
    if (password === "") {
        msg2.innerText = '密码不能为空';
        return;
    }

    msg2.innerText = "";

    const data = {
        username: userName,
        password: password
    };
    flag = false;

    axios.post('/sessions', data)
        .then(function (response) {
            const data1 = response.data;
            if (data1.code === 200) {
                window.location.href = '/';
            } else {
                msg2.innerText = data1.message;
            }
            flag = true;
        })
        .catch(function (error) {
            console.log(error);
            flag = true;
        });
    // postData({
    //     url: "http://localhost:8080/blog/login.c",
    //     data: data,
    //     callback: function (data) {
    //         if (data.code === 302) {
    //             window.location.href = data.data;
    //         }
    //         else {
    //             if (!data.success) {
    //                 msg2.innerText = data.message;
    //             }
    //         }
    //         flag = true;
    //     }
    // });
}


