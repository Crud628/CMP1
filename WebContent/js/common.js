//全局路径
var url="http://localhost:8080/CMP/";
var common={
				find:function(data,key){
					//取得?之后的字符串
					str2=data.substr(1);
					//字符串按&分割成字符串数组?cid=2&ye=1
				    arr=str2.split("&");
					for(var i=0;i<arr.length;i++){
						//将每个字符串按=分割
						arr2=arr[i].split("=");
						if (arr2[0]==key) {
							return arr2[1];
						}
					}
				}
			};