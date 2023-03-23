function upImg(){
    var fileObj=document.getElementById("file").file[0];
    var FileController="/profilePhotoUpload";
    if(!fileObj.type.match('image.*')){
        alert("請選擇正確圖片");
    }else {
        var form =new FormData();
        form.append("file",fileObj);
        var xhr=new XMLHttpRequest();
        xhr.onreadystatechange=function (){
            if(this.readyState==4&& this.status==200){
                var b=this.responseText
                if(b=="success"&&(fileObj.type.match('image.*'))){
                    alert("上傳成功");
                }

            }
        };
        xhr.open("post",FileController,true);
        xhr.send(form);
    }

}