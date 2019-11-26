//提交的方法
var method = "";
$(function(){
    $('#grid').datagrid({
        url:'find_'+name,
        method:'post',
        // headers: { 'Content-Type': 'application/json;charset=UTF-8' },
        columns:columns,
        nowrap: true,
        pagination : true,
        singleSelect : true,
        height: window.innerHeight-50 ,
        pageSize:20,
        pageList: [10,20,50,100],//可以设置每页记录条数的列表
        fit:false,
        toolbar: [{
            text:'新增',
            iconCls: 'icon-add',
            handler: function(){
                method = "add";
                $('#editDlg').dialog('open');
            }
        },'-',{
            iconCls: 'icon-help',
            handler: function(){alert('帮助按钮')}
        }]
    });
    /* 查询部门数据 */
    $('#btnSearch').bind('click',function(){
        //把表单转换程json对象
        var formData=$('#serachForm').serializeJSON();
        // var formData1=JSON.stringify(formData);
        console.log("btnSearch中的formData......"+formData);
        $('#grid').datagrid('load',formData);
        // alert(JSON.stringify($('#grid').datagrid('getData')));
        // alert(JSON.stringify(formData));
        // $.ajax({
        //     url:'find_'+name,
        //     // data:formData,
        //     data:JSON.stringify(formData),//这句话极端重要
        //     contentType :"application/json",//这句话极端重要
        //     dataType:'json',
        //     type:'post',
        //     success:function(rtn){
        //         //grid加载数据
        //         //datagrid(参数1，参数2)
        //         $('#grid').datagrid('loadData',rtn);
        //     }
        // });
    });
    $('#editDlg').dialog({
        title: '编辑',
        width: 300,
        height: 200,
        closed: true,  //窗口初始化状态，true：关闭
        modal: true
    });
    /*新增数据  */
    $('#btnSave').bind('click',function(){
        var formData = $('#editForm').serializeJSON();
        console.log("btnSave中的formData......"+formData);
        $.ajax({
            url:method+'_'+name,
            data:JSON.stringify(formData),//这句话极端重要
            dataType:'json',
            contentType :"application/json",//这句话极端重要
            type:'post',
            success:function(rtn){
                // $.messager.alert('提示',rtn.message,'info',function(){
                    //成功的hua我们要关闭窗口
                    $('#editDlg').dialog('close');
                    //刷新表格数据
                    $('#grid').datagrid('reload');
                // });
            }
        });
    });

});
/* 删除数据 */
function del(uuid){
    $.messager.confirm('确认','您确认想要删除记录吗？',function(yes){
        if (yes){
            $.ajax({
                url: name+'_delete?id=' + uuid,
                dataType: 'json',
                type: 'post',
                success:function(rtn){
                    // $.messager.alert("提示",rtn.message,'info',function(){
                        //刷新表格数据
                        $('#grid').datagrid('reload');
                    // });
                }
            });
        }
    });
}
/* 修改数据 */
function edit(uuid){
    /*打开编辑框  */
    $('#editDlg').dialog('open');
    /*  */
    //清空表单内容
    $('#editForm').form('clear');
    method = "update";
    //加载数据
    $('#editForm').form('load','get_'+name+'?id='+ uuid);
}

/* 详细数据 */
function detail(uuid){
    //加载数据
    $('#editForm').form('load','get_'+name+'?id='+ uuid);
}
