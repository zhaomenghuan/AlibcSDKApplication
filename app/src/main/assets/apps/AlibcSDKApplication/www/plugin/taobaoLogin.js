document.addEventListener("plusready",  function()
{
// 声明的JS“扩展插件别名”
    var _BARCODE = 'TaobaoLogin',
        B = window.plus.bridge;

    window.plus.taobaoLogin = {
          login: function (successCallback, errorCallback) {
              var success = typeof successCallback !== 'function' ? null : function(args) {
                  successCallback(args);
              },
              fail = typeof errorCallback !== 'function' ? null : function(code) {
                  errorCallback(code);
              };
              callbackID = B.callbackId(success, fail);
              return B.exec(_BARCODE, "login", [callbackID]);
          },
          logout: function(successCallback, errorCallback) {
              var success = typeof successCallback !== 'function' ? null : function(args) {
                    successCallback(args);
              },
             fail = typeof errorCallback !== 'function' ? null : function(code) {
                   errorCallback(code);
             };
             callbackID = B.callbackId(success, fail);
            return B.exec(_BARCODE, "logout", [callbackID]);
          },
          isLogin: function() {
            return B.execSync(_BARCODE, "isLogin");
          }
    };
}, true );