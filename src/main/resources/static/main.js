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
 * @param multipart     是否使用formdata格式上传
 */
const httpRequest = function(url, formData, callback, resultType, multipart) {
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
        if (multipart) {
            xmlHttp.send(formData);
        } else {
            xmlHttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');
            for (let key in formData) {
                params += '&' + key + '=' + formData[key];
            }
            if (params != '') {
                xmlHttp.send(params.substring(1));
            } else {
                xmlHttp.send();
            }
        }
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
/**
 * Vue组件 分页视图
 */
var Pagination = {
    data: function(){
        return {
            current: 1,
            size: 5,
            pages: [],
            allPage: 0,
            limit: 10,
            callback: null
        }
    },
    methods: {
        init(limit, size) {
            this.limit = limit || 10;
            this.size = size || 5;
        },
        onPage: function(page){
            this.current = parseInt(page);
            this.render();
            if (this.page != 0 && this.callback != null) {
                this.callback(page);
            }
        },
        setCallback: function(callback){
            this.callback = callback;
        },
        setData: function(allCount) {
            this.allPage = Math.ceil(allCount / this.limit);
            if (this.current > this.allPage) {
                this.current = 1;
            }
            this.render();
        },
        render: function(){
            if (this.allPage > 0) {
                let pages = [];
                if (this.current == 1) {
                    let num = 1;
                    let c = 1;
                    while (num <= this.allPage) {
                        pages.push(num);
                        num++;
                        if (++c > this.size) {
                            break;
                        }
                    }
                } else if (this.current == this.allPage) {
                    let num = this.allPage;
                    let c = 1;
                    while (num > 0) {
                        pages.push(num);
                        num--;
                        if (++c > this.size) {
                            break;
                        }
                    }
                    pages = pages.reverse();
                } else {
                    let num = this.current;
                    let c = 1;
                    while (num > 0) {
                        pages.push(num);
                        num--;
                        if (++c > this.size) {
                            break;
                        }
                    }
                    pages = pages.reverse();
                    num = this.current + 1;
                    c = 1;
                    while (num <= this.allPage) {
                        pages.push(num);
                        num++;
                        if (++c > this.size) {
                            break;
                        }
                    }
                }
                this.pages = pages;
            }
        }
    },
    template: '<div class="pagination"><ul><li v-on:click="onPage(1)"><a href="javascript:;">&lt;</a></li>'
        + '<li v-on:click="onPage(item)" v-bind:class="{active: current==item}" v-for="item in pages"><a href="javascript:;">{{item}}</a></li>'
        + '<li v-on:click="onPage(allPage)"><a href="javascript:;">&gt;</a></li></ul></div>'
};