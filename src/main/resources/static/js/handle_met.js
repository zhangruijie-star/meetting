$(function(){
    // $('#m_next_deal_uuid').combotree({
    //     url: 'getEmpMenuTree',
    //     method:'post',
    //     required: true
    // });
    // 审批会议，改变状态值
    $('#btn_handle').click(function () {
        var jsonstr={};
        jsonstr.metuuid=$('#met_uuid').val();
        jsonstr.dealuuid=$('#m_create_uuid').val();
        jsonstr.dealtime=new Date();
        jsonstr.dealway=$('#handleway').val();
        jsonstr.dealresult="";
        jsonstr.comment=$('#dealcomment').val();
        // var met_handlerecord=$('#handle_met').serializeJSON();
        alert("提交后台");
        // var obj = eval('(' + jsonstr + ')');//由JSON字符串转换为JSON对象
        // var obj = JSON.parse(jsonstr); //由JSON字符串转换为JSON对象
        console.log("jsonstr..."+jsonstr);
        var form_jsonstr=JSON.stringify(jsonstr);//这句话极端重要
        console.log("form_jsonstr..."+form_jsonstr);
        $.ajax({
            url:'handle_met',
            data:form_jsonstr,
            dataType:'json',
            contentType:"application/json",//这句话极端重要
            type:'post',
            success:function(rtn){
                $.alert.messager('提示',rtn.messag,'info');
                console.log("提交成功");
            }
        });
    });

});