layui.laydate.render(
    {
        elem: '#createTimeRange',
        range: true
    }
);


var table = layui.table;
var tableIns = table.render(
    {
        id: 'test',
        elem: '#accountList',
        url: '/account/list',
        page: true,
        parseData: function (res) {
            return {
                "code": res.code,
                "msg": res.msg,
                "count": res.data.count,
                "data": res.data.records
            };
        },
        cols: [[
            {
                field: 'username',
                title: '用户名'
            },
            {
                field: 'realName',
                title: '真实姓名'
            },
            {
                field: 'roleName',
                title: '角色名称'
            },
            {
                field: 'sex',
                title: '性别'
            },
            {
                field: 'createTime',
                title: '创建时间'
            },
            {
                field: '操作',
                toolbar: '#barDemo'
            }
        ]]
    }
);

/**
 * 按条件查询方法
 */
function query() {
    tableIns.reload(
        {
            where: //设定异步数据接口的额外参数
                {
                    realName: $("#realName").val(),
                    phone: $("#email").val(),
                    createTimeRange: $("#createTimeRange").val()
                },
            page:
                {
                    curr: 1//重新从第1页开始
                }
        }
    );
}

function intoAdd() {
    openLayer("/account/toAdd", "新增账号");
    let form = layui.form;
    form.render();
    mySubmit('addSubmit', 'post');
}

table.on('tool(test)', function (obj) {
    var data = obj.data;
    var layEvent = obj.event;

    let accountId = data.accountId;
    if (layEvent === 'detail') {
        openLayer("/account/toDetail/" + accountId, "账号详情");
    } else if (layEvent === 'del') {
        layer.confirm('真的删除行么', function (index) {
            layer.close(index);
            myDelete('/account/' + accountId);
        });
    } else if (layEvent === 'edit') { //编辑
        openLayer('/account/toUpdate/' + accountId, '编辑账号');
        layui.form.render();
        mySubmit('updateSubmit', 'put');
    }
});


layui.form.verify({
    checkUsername: function (value) { //value：表单的值、item：表单的DOM对象

        let error = null;
        let url = '/account/' + value;
        let accountId = $("input[name='accountId']").val();

        if (typeof (accountId) != 'undefined') {
            url += '/' + accountId;
        }

        $.ajax(
            {
                url: url,
                async: false,
                type: 'get',
                success: function (res) {
                    if (res.code == 0) {
                        if (res.data > 0) {
                            error = "用户名已经存在";
                        }
                    } else {
                        error = "用户名检测出错";
                    }
                },
                error: function () {
                    error = "用户名检测出错";
                }
            }
        );
        if (error != null) {
            return error;
        }
    }
});