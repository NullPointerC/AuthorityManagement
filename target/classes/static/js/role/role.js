var table = layui.table;
var tableIns = table.render(
    {
        id: 'test',
        elem: '#roleList',
        url: '/role/list',
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
                field: 'roleName',
                title: '角色名称'
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
                    roleName: $("#roleName").val()
                },
            page:
                {
                    curr: 1//重新从第1页开始
                }
        }
    );
}


/**
 * 进入新增页
 */
function intoAdd() {
    openLayer("/role/toAdd", "新增角色");

    showTree('/role/listResource', 'resource');

    mySubmit('addSubmit', 'post', addIds);
}

/**
 *
 * 通用的资源show方法
 * @param url
 * @param id
 * @param showCheckbox
 */
function showTree(url, id, showCheckbox) {

    if (typeof (showCheckbox) == 'undefined') {
        showCheckbox = true;
    }
    $.ajax(
        {
            url: url,
            async: false,
            type: 'get',
            success: function (res) {
                if (res.code == 0) {
                    layui.tree.render({
                        elem: '#' + id,
                        data: res.data,
                        id: id,
                        showCheckbox: showCheckbox
                    });
                }
            }
        }
    );
}


var addIds = function (field) {
    let checkedData = layui.tree.getChecked('resource');
    field.resourceIds = getIds(checkedData, []);
}

function getIds(checkedData, arr) {
    for (let i in checkedData) {
        arr.push(checkedData[i].id);
        arr = getIds(checkedData[i].children, arr);
    }
    return arr;
}


table.on('tool(test)', function (obj) {
    var data = obj.data;
    var layEvent = obj.event;

    let roleId = data.roleId;
    if (layEvent === 'detail') {

        openLayer("/role/toDetail/" + roleId, "角色详情");
        // var url = '/role/listResource/' + roleId + '/1';
        // console.log(url);
        showTree('/role/listResource/' + roleId + '/1', 'resource', false);
    } else if (layEvent === 'del') {

        layer.confirm('真的删除改角色么', function (index) {

            layer.close(index);

            myDelete('/role/' + roleId);

        });
    } else if (layEvent === 'edit') { //编辑

        openLayer('/role/toUpdate/' + roleId, '编辑角色');
        // var url = '/role/listResource/' + roleId + '/0';
        // console.log(url);
        showTree('/role/listResource/' + roleId + '/0', 'resource');

        mySubmit('updateSubmit', 'put', addIds);
    }
});
