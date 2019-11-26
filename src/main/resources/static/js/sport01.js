/**
 * Created by Administrator on 2017/11/8.
 */

$("#addr").combobox({
        url:'json/addr.json',
        valueField:'id',
        textField:'text'
});
$("#work").combobox({
        url:'json/addr.json',
        valueField:'id',
        textField:'text'
});
$("#con").blur(function () {
        $(this).val("请输入会议内容");
        $(this).css('color','#999');

});
$.fn.datebox.defaults.formatter = function(date){
        var y = date.getFullYear();
        var m = date.getMonth()+1;
        var d = date.getDate();
        return y+'年'+m+'月'+d+'日';
};