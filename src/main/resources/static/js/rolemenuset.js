$(function () {
    // $('#m_next_deal_uuid').combotree({
    //     url: 'getEmpMenuTree',
    //     method:'post',
    //     required: true
    // });
    $('#editDepDlg').dialog({
        title: '参会部门',
        width: 300,
        height: 250,
        closed: true,  //窗口初始化状态，true：关闭
        modal: true
    });
    $('#editEmpDlg').dialog({
        title: '与会人员',
        width: 300,
        height: 350,
        closed: true,  //窗口初始化状态，true：关闭
        modal: true
    });
    /* 加载部门Tree列表数据 */
    $('#add_met_dep').click(function () {
        $('#editDepDlg').dialog('open');
        //加载数据
        $('#editDeptree').tree({
            url:'getDepMenuTree',
            method:'post',
            animate:true,
            checkbox:true
        });
    });
    /*保存部门Tree列表数据到input框*/
    $('#btnDepSave').click(function () {
        //得到所有勾选的节点
        var nodes=$('#editDeptree').tree('getChecked');
        //拼接每个节点的name，也就是menuname
        var checkedStr=new Array();
        $.each(nodes,function (i,node) {
            checkedStr.push(node.text);
        });
        //拼接每个节点的id，也就是menuid
        var checkedId=new Array();
        $.each(nodes,function (i,node) {
            checkedId.push(node.id);
        });
        //展示给用户看的input，赋值
        $('#user_met_dep').val(checkedStr);
        //发到后台用的input，属性隐藏，
        var nodes_str=JSON.stringify(nodes);
        console.log("checkedId..."+checkedId);
        $('#met_dep').val(checkedId);
        $('#editDepDlg').dialog('close');

    });

    /* 加载员工Tree列表数据 */
    $('#add_met_emp').click(function () {
        $('#editEmpDlg').dialog('open');
        //加载数据
        $('#editEmptree').tree({
            url:'getEmpMenuTree',
            method:'post',
            animate:true,
            checkbox:true
        });
    });
    /*保存员工Tree列表数据到input框*/
    $('#btnEmpSave').click(function () {
        //得到所有勾选的节点
        var nodes=$('#editEmptree').tree('getChecked');
        //拼接每个节点的name，也就是menuname
        var checkedStr=new Array();
        $.each(nodes,function (i,node) {

                checkedStr.push(node.text);


        });
        //拼接每个节点的id，也就是menuid
        var checkedId=new Array();
        $.each(nodes,function (i,node) {

                checkedId.push(node.id);


        });
        //展示给用户看的input，赋值
        $('#user_met_emp').val(checkedStr);
        //发到后台用的input，属性隐藏，
        var nodes_str=JSON.stringify(nodes);
        console.log("checkedId..."+checkedId);
        $('#met_emp').val(checkedId);
        $('#editEmpDlg').dialog('close');

    });
    //审核人列表..............
    $('#editDealDlg').dialog({
        title: '审核人员',
        width: 300,
        height: 350,
        closed: true,  //窗口初始化状态，true：关闭
        modal: true
    });
    $('#add_met_deal').click(function () {
        $('#editDealDlg').dialog('open');
        //加载数据
        $('#editDealtree').tree({
            url:'getEmpMenuTree',
            method:'post',
            animate:true,
            checkbox:true
        });
    });
    /*保存员工Tree列表数据到input框*/
    $('#btnDealSave').click(function () {
        //得到所有勾选的节点
        var nodes=$('#editDealtree').tree('getChecked');
        //拼接每个节点的name，也就是menuname
        var checkedStr=new Array();
        $.each(nodes,function (i,node) {
            checkedStr.push(node.text);
        });
        //拼接每个节点的id，也就是menuid
        var checkedId=new Array();
        $.each(nodes,function (i,node) {

            checkedId.push(node.id);


        });
        //展示给用户看的input，赋值
        $('#m_next_deal_name').val(checkedStr);
        //发到后台用的input，属性隐藏，
        var nodes_str=JSON.stringify(nodes);
        console.log("checkedId..."+checkedId);
        $('#m_next_deal_uuid').val(checkedId);
        $('#editDealDlg').dialog('close');

    });

    //。。。。。。。。。。。。。。。。。。。。。。。。
    //角色列表
    $('#grid').datagrid({
        url:'getRoleList',
        method:'post',
        columns:[[
            {field:'uuid',title:'角色编号',width:100},
            {field:'name',title:'角色名称',width:100}
        ]],
        singleSelect:true,
        onClickRow:function (rowIndex,rowData) {
            $('#tree').tree({
                url:'getRoleMenu/'+rowData.uuid,
                method:'post',
                animate:true,
                checkbox:true
            });
        }
    });
    //权限及选中更新列表
    $('#btnSave').click(function () {
        //得到所有勾选的节点
        var nodes=$('#tree').tree('getChecked');
        var str=JSON.stringify(nodes);
        console.log("选中的str..."+str);
        //拼接每个节点的id，也就是menuid
        var checkedStr=new Array();
        $.each(nodes,function (i,node) {
            checkedStr.push(node.id);
        });
        //把数组转化成以逗号分割的字符串
        checkedStr=checkedStr.join(',');
        //提交的数据
        var formdata={};
        formdata.id=$('#grid').datagrid('getSelected').uuid;
        formdata.checkedStr=checkedStr;
        console.log("formdata..."+formdata);
        $.ajax({
            url:'updateRoleMenu',
            data:formdata,
            type:'post',
            dataType:'json',
            success:function (rtn) {
                $.messager.alert('提示',rtn.message,'info');
            }
        });
    });
});

// $('#tree').tree({
//     //     url:'json/tree.json'
//     // });
//
//     // $('#tree').tree({
//     //     url:'getRoleMenu',
//     //     method:'post',
//         // data: [{
//         //     id:'100',
//         //     text: '基础数据',
//         //     state: 'closed',
//         //     children: [{
//         //         id:'101',
//         //         text: '部门管理'
//         //     },{
//         //         id:'102',
//         //         text: '员工管理',
//         //         checked:true
//         //     }]
//         // },{
//         //     text: 'Item2'
//         // }],
//     //     animate:true,
//     //     checkbox:true
//     // });