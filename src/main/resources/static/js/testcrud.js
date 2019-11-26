//提交的方法
var method = "";
$(function(){
    $('#grid').datagrid({
        url:'selectEmployee',
        method:'post',
        columns:columns,
        pagination : true,
        singleSelect : true,
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
        console.log("btnSearch中的formData......"+formData);
        $('#grid').datagrid('load',formData);
        //alert(JSON.stringify($('#grid').datagrid('getData')));
        /*  alert(JSON.stringify(formData)); */
        /* $.ajax({
            url:'dep_getList',
            data:formData,
            dataType:'json',
            type:'post',
            success:function(rtn){
                //grid加载数据
                //datagrid(参数1，参数2)
                $('#grid').datagrid('loadData',rtn);
            }
        });*/
    });
    $('#editDlg').dialog({
        title: '部门编辑',
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
            data:formData,
            dataType:'json',
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
