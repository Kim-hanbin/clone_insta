$(document).ready(function (){
    //Layer popup open
    $('.btn_lyr_open').on('click',function(){
        $('.lyrWrap').show();
    });
    //Layer popup close
    $('.btn_lyr_close, .lyr_bg').on('click',function(){
        $('.lyrWrap').hide();
        $('.lyrBox').hide();
    });

});


var isEmpty = function(value){
    if( value == null || value == undefined || value.trim()== "" || ( value != null && typeof value == "object" && !Object.keys(value).length ) ){
        return true;
    }else{
        return false
    }
};
