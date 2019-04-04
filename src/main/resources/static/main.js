/**
* jet-sdk script
* ======
* @author 洪波
* @version 19.04.03
*/

/**
 * 建立ajax异步网络请求
 * ======
 * @param url           访问地址 http://127.0.0.1/test
 * @param formData      post表单数据（若该字段存在，则表示为post请求方法）
 * @param callback      响应结果回调
 * @param resultType    响应结果格式 text（默认） | json
 */
const httpRequest = function(url, formData, callback, resultType) {
    let xmlHttp = new XMLHttpRequest();
    let params = '';
    let method = 'GET';
    if (formData) {
        method = 'POST';
    }
    xmlHttp.onreadystatechange = function(){
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200) {
            if (resultType == 'json') {
                return callback(JSON.parse(xmlHttp.responseText));
            } else {
                return callback(xmlHttp.responseText);
            }
        }
    };
    xmlHttp.open(method, url, true);
    if (method == 'POST') {
        xmlHttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
        for (let key in formData) {
            params += '&' + key + '=' + formData[key];
        }
    }
    if (params != '') {
        xmlHttp.send(params.substring(1));
    } else {
        xmlHttp.send();
    }
};
/**
 * 格式化时间
 * @param {*} format    y-m-d h:i:s
 * @param {*} time      毫秒时间戳
 * @param {*} timezone  时区 
 */
const dateFormat = function(format, time){
    let date;
    if (time != undefined) {
        date = new Date(time);
    } else {
        date = new Date();
    }
    let dateDict = {
        'y': date.getFullYear(),
        'm': date.getMonth() + 1,
        'd': date.getDate(),
        'h': date.getHours(),
        'i': date.getMinutes(),
        's': date.getSeconds()
    };
    if (format == undefined) {
        format = 'y-m-d h:i:s';
    }
    let dateStr = '';
    for(let i=0; i<format.length; i++) {
        let s = format.charAt(i);
        if (dateDict[s]) {
            dateStr += dateDict[s] < 10 ? '0'+dateDict[s] : dateDict[s];
        } else if (dateDict[s] == 0) {
            dateStr += '00';
        } else {
            dateStr += s;
        }
    }
    return dateStr;
};