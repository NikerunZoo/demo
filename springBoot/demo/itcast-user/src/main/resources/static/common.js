(function () {
    var oldAjax = jQuery.ajax;
    var EMPTY = {};
    var defaultOpts = {
        contentType: 'application/json',
        dataType: 'json',
        processData: false,
        beforeSend: function (jqXHR, options) {
            if (options.contentType === 'application/json' && typeof options.data !== 'string') {
                options.data = JSON.stringify(options.data);
            }
        }
    };
    jQuery.ajax = function (url, options) {
        options = options || EMPTY;

        if (typeof url === 'object') options = url;
        else if (typeof url === 'string') options.url = url;
        if (!options.url) throw new Error('bad args.');
        return new Promise(function (resolve, reject) {
            //这里的链式调用使用的是原生promise?

            oldAjax(Object.assign({}, defaultOpts, options)).then(function (obj) {
                if (obj.statusCode === 0) {
                    // 如果接口返回成功直接 resolve 成功数据
                    return resolve(obj.data);
                }

                // 将 messages 默认 join 成 message，方便业务层直接使用。
                obj.message = obj.messages.join('\n');
                reject(obj);
            }).catch(function (xhr) {
                var obj = xhr.responseJSON;
                if (!obj) {
                    obj = {
                        statusCode: xhr.statusCode,
                        messages: [xhr.statusText]
                    };
                }
                obj.message = obj.messages.join('\n');
                reject(obj);
            });
        });
    };


    /**
     * 获取表单验证码,
     * 传入设置src的图片的jquery对象,
     * 传入需要再window上用于存储图片验证码的uuid的属性名
     * @param elm  jquery对象
     * @param string  window[属性名]
     */
    window.getValid = function (elm, string) {
        var count = 0;
        _getValid(count);

        function _getValid() {
            if (count >= 10) {
                anertTip({content: '获取验证码失败, 请检查您的网络, 或联系管理员'});
            }
            $.get('/verification_code').then(function (data) {
                elm.attr('src', data.url);
                window[string] = data.id;
            }).catch(function () {
                _getValid(++count);
            });
        }
    };


    /**
     *  传入元素选择器, 获取改元素的value, 用于获取元素最原始的值,(没有想getValuse哪样会判断other input的逻辑)
     * @param seletor
     * @returns {*}
     */
    window.getBaseValue = function getBaseValue(seletor) {
        var type = $(seletor).attr('type') ? $(seletor).attr('type').toLowerCase() : $(seletor)[0].tagName.toLowerCase();
        if (type === 'radio') {
            return $(seletor + ':checked').val();
        } else if (type === 'text' || type === 'tel' || type === 'email' || type === 'hidden' || type === 'textarea') {
            return $(seletor).val();
        } else if (type === 'checkbox') {
            return window.getCheckboxValues(seletor);
        } else if (type === 'select') {
            return $(seletor + ' option:selected').val();
        }
    };

    /**
     * 用于得到多选框的值
     *
     * @param {*} string jquery选择器
     * @returns 返回多选框的值组成的数组
     */
    window.getCheckboxValues = function getCheckboxValues(string) {
        var CheckboxValues = [];
        $(string).each(function (index, item) {
            if (item.checked) {
                CheckboxValues.push($(item).val());
            }
        });
        return CheckboxValues;
    };

    /**
     * 传入数组,使用getValue方法,获取表单元素的值
     * @param secletors  [{name: 'name', selector: 'selector'},/...]
     * @returns {name: value, name1: value1,...}
     */
    window.getValues = function getValues(selectors) {
        var map = {};
        selectors.forEach(function (item) {
            map[item.name] = window.getValue(item.selector);
        });
        return map;
    };

    /**
     * 用于获取一个问题的值, 传入一个选择其, 判断是否有data-ohter这个自定义属性;
     * 如果有, 获取data-other的值,并凭借判断当前值是否等于data-other的值, 如果等于就判断为,需要获取other input的值.
     */
    window.getValue = function getValue(selector) {
        var value, otherData;
        value = window.getBaseValue(selector);
        otherData = $(selector).data('other');
        if (otherData && value == otherData) {//如果元素没有data-other的值, 或在值为空(单选情况)
            value = window.getBaseValue(selector.slice(0, -1) + 'Value]');
        } else if (otherData && $.isArray(value) && $.inArray(otherData, value) !== -1) {//如果存在, 或otherData被包含在了getValue中(多选情况)
            for (var i = 0; i < value.length; i++) {
                if (value[i] === otherData) {
                    value[i] = window.getBaseValue(selector.slice(0, -1) + 'Value]');
                    break;
                }
            }
        }
        return value;
    };

    /**
     * 下载文件
     * @param url
     */
    window.downloadFile = function downloadFile(url) {

        //以a标签加download实现, 兼容性不确定
        /*  var a = $('<a download="">');
          a.attr('href', url);
          $('body').append(a);
          a[0].click();
          a.remove();
      */
        //使用新开窗口实现, 只能下载不可被浏览器解析的文件
        window.open(url, '_blank');

        //使用iframe
        // var iframe = $('<iframe>');
        // $('body').append(iframe);
        // iframe[0].src = url;
    };

    /**
     * 点击下一步的时候， 提示waringNext
     *
     * @param {*} seletor
     */
    window.waringNext = function waringNext(seletor) {

        var type = $(seletor).attr('type') ? $(seletor).attr('type').toLowerCase() : $(seletor)[0].tagName.toLowerCase();

        if (type === 'radio') {
            // 判断是否需要提示other input是否为空
            var baseValue = window.getBaseValue(seletor),
                otherData = $(seletor).data('other');
            if (otherData && baseValue == otherData) {
                var otherValue = window.getValue(seletor.slice(0, -1) + 'Value]'),
                    otherElm = $(seletor.slice(0, -1) + 'Value]');
                if (otherValue) {
                    otherElm.css('border', '1px solid #d7dbe0');
                } else {
                    otherElm.css('border', '1px solid #ff0000');
                    otherElm[0].focus();
                }
            }
            // 通用的判断空
            if (!window.getValue(seletor)) {
                $('.' + ($(seletor).attr('name') + 'Tip')).show();
                $('.' + ($(seletor).attr('name') + 'Tip'))[0].scrollIntoView();
                return false;
            } else {
                $('.' + ($(seletor).attr('name') + 'Tip')).hide();
                return true;
            }
        } else if (type === 'text' || type === 'tel' || type === 'email') {
            if ($(seletor).data('type') === 'first') {
                var reg = new RegExp($(seletor).data('valid').slice(1, -1));
                if (!reg.test($(seletor).val())) {
                    $('.' + ($(seletor).attr('name') + 'Tip')).show();
                    return false;
                } else {
                    $('.' + ($(seletor).attr('name') + 'Tip')).hide();
                    return true;
                }
            } else if ($(seletor).data('type') === 'second' || $(seletor).data('type') === 'three') {
                if ($(seletor).val() === '') {
                    $('.' + ($(seletor).attr('name') + 'Tip')).show();
                    $('.' + ($(seletor).attr('name') + 'Tip'))[0].scrollIntoView();
                    return false;
                } else {
                    $('.' + ($(seletor).attr('name') + 'Tip')).hide();
                    return true;
                }
            }
        } else if (type === 'checkbox') {
            var checkboxValue = window.getCheckboxValues(seletor);
            if ($.inArray('其他', checkboxValue) != -1 && !window.getValue(seletor.slice(0, -1) + 'Value]')) {
                //需要判断值other input是否为空
                $(seletor.slice(0, -1) + 'Value]').css('border', '1px solid #ff0000');
                $(seletor.slice(0, -1) + 'Value]')[0].focus();
                $('.' + ($(seletor).attr('name') + 'Tip')).show();
                $('.' + ($(seletor).attr('name') + 'Tip'))[0].scrollIntoView();
                return false;
            } else {
                $('.' + ($(seletor).attr('name') + 'Tip')).hide();
                $(seletor.slice(0, -1) + 'Value]').css('border', '1px solid #d7dbe0');
            }
            if (window.getCheckboxValues(seletor).length === 0) {
                $('.' + ($(seletor).attr('name') + 'Tip')).show();
                $('.' + ($(seletor).attr('name') + 'Tip'))[0].scrollIntoView();
                return false;
            } else {
                $('.' + ($(seletor).attr('name') + 'Tip')).hide();
                return true;
            }
        } else if (type === 'select') {

            if ($(seletor + ' option:selected').val() === '请选择') {
                $('.' + ($(seletor).attr('name') + 'Tip')).show();
                $('.' + ($(seletor).attr('name') + 'Tip'))[0].scrollIntoView();
                return false;
            } else {
                $('.' + ($(seletor).attr('name') + 'Tip')).hide();
                return true;
            }
        }
    };

    /**
     *
     * @returns {boolean}
     */
    window.is_weixin = function is_weixin() {
        var ua = navigator.userAgent.toLowerCase();
        if (ua.indexOf('micromessenger') != -1 && ua.indexOf('android') != -1) {
            return true;
        } else {
            return false;
        }
    };

    /**
     * 限制输入的函数
     */
    window.limitTextLength = function limitTextLength(targetParent, length) {
        $(targetParent + '> .input').on('input', function () {
            var value = $(this).val();
            if (value.length > length) {
                $(this).val(value.substring(0, length));
            } else {
                $(targetParent + '> span').text((length - value.length) + '/' + length);
            }
        });
    };
    /**
     * 禁止使用回车
     * @param event
     */
    window.nowrap = function noWrap(event) {
        if (event.keyCode==13) event.returnValue = false;
    };
})();

/**
 * 文本框根据输入内容自适应高度
 * @param                {HTMLElement}        输入框元素
 * @param                {Number}                设置光标与输入框保持的距离(默认0)
 * @param                {Number}                设置最大高度(可选)
 */
window.autoTextarea = function (elem, extra, maxHeight) {
    extra = extra || 0;
    var isFirefox = !!document.getBoxObjectFor || 'mozInnerScreenX' in window,
        isOpera = !!window.opera && !!window.opera.toString().indexOf('Opera'),
        addEvent = function (type, callback) {
            elem.addEventListener ?
                elem.addEventListener(type, callback, false) :
                elem.attachEvent('on' + type, callback);
        },
        getStyle = elem.currentStyle ? function (name) {
            var val = elem.currentStyle[name];
            if (name === 'height' && val.search(/px/i) !== 1) {
                var rect = elem.getBoundingClientRect();
                return rect.bottom - rect.top -
                    parseFloat(getStyle('paddingTop')) -
                    parseFloat(getStyle('paddingBottom')) + 'px';
            }
            return val;
        } : function (name) {
            return getComputedStyle(elem, null)[name];
        },
        minHeight = parseFloat(getStyle('height'));
    elem.style.resize = 'none';
    var change = function () {
        var scrollTop, height,
            padding = 0,
            style = elem.style;
        if (elem._length === elem.value.length) return;
        elem._length = elem.value.length;
        if (!isFirefox && !isOpera) {
            padding = parseInt(getStyle('paddingTop')) + parseInt(getStyle('paddingBottom'));
        }
        scrollTop = document.body.scrollTop || document.documentElement.scrollTop;
        elem.style.height = minHeight + 'px';
        if (elem.scrollHeight > minHeight) {
            if (maxHeight && elem.scrollHeight > maxHeight) {
                height = maxHeight - padding;
                style.overflowY = 'auto';
            } else {
                height = elem.scrollHeight - padding;
                style.overflowY = 'hidden';
            }
            style.height = height + extra + 'px';
            scrollTop += parseInt(style.height) - elem.currHeight;
            document.body.scrollTop = scrollTop;
            document.documentElement.scrollTop = scrollTop;
            elem.currHeight = parseInt(style.height);
        }
    };
    addEvent('propertychange', change);
    addEvent('input', change);
    addEvent('focus', change);
    change();
};
