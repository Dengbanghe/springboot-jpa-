$(function () {
    $('.btn').on('click',function () {
        $.ajax({
            url:'getUser',
            success:function (data) {
                var html =  "<ul>";
                html += $.map(data,function (item) {
                    return "<li>"+item.id+"---"+item.content+"</li>";
                });
                html += "</ul>";
                $('.cont').append(html);
            },
            error:function (err) {
                alert(JSON.stringify(err))
            }
        })
    })
})