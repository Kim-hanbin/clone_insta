$(function(){

var boardikeval = ${boardLike};

let board_id = ${Detail.board_id};
let user_id = '${login.user_id}';
if(likeval > 0){
    console.log(likeval + "좋아요 누름");
    $('.LikeBtn').html("좋아요 취소");
    $('.LikeBtn').click(function() {
        $.ajax({
            type :'post',
            url : '/Board/likeDown',
            contentType: 'application/json',
            data : JSON.stringify(
                {
                    "board_id" : board_id,
                    "user_id" : user_id
                }
            ),
            success : function(data) {
                alert('취소 성공');
            }
        })// 아작스 끝
    })

}else{
    console.log(likeval + "좋아요 안 누름")
    console.log(user_id);
    $('.LikeBtn').click(function() {
        $.ajax({
            type :'post',
            url : '/Board/likeUp',
            contentType: 'application/json',
            data : JSON.stringify(
                {
                    "board_id" : board_id,
                    "user_id" : user_id
                }
            ),
            success : function(data) {
                alert('성공!');
            }
        })// 아작스 끝
    })}}