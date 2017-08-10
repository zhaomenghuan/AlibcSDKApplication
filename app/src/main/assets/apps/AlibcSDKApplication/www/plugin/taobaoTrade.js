document.addEventListener( "plusready",  function()
{
// 声明的JS“扩展插件别名”
    var _BARCODE = 'TaobaoTrade',
        B = window.plus.bridge;

    window.plus.taobaoTrade = {
          openWindow: function (url, successCallback, errorCallback) {
                var success = typeof successCallback !== 'function' ? null : function(args) {
                    successCallback(args);
                },
                fail = typeof errorCallback !== 'function' ? null : function(code) {
                    errorCallback(code);
                };
                callbackID = B.callbackId(success, fail);
                return B.exec(_BARCODE, "openWindow", [callbackID, url]);
          },
          openDetail: function (url, successCallback, errorCallback) {
              var success = typeof successCallback !== 'function' ? null : function(args) {
                  successCallback(args);
              },
              fail = typeof errorCallback !== 'function' ? null : function(code) {
                  errorCallback(code);
              };
              callbackID = B.callbackId(success, fail);
              return B.exec(_BARCODE, "openDetail", [callbackID, url]);
          }
    };
}, true );